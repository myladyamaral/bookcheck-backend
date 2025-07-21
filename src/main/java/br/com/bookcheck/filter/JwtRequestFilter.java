package br.com.bookcheck.filter;

import br.com.bookcheck.component.SecurityTokenComponent;
import br.com.bookcheck.config.JwtConfigProperties;
import br.com.bookcheck.exception.StandardError;
import br.com.bookcheck.exception.UnauthorizedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtConfigProperties jwtConfigProperties;

    private final UserDetailsService userDetailsService;

    private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
    private final SecurityTokenComponent securityTokenComponent;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        final String requestURI = request.getRequestURI();

        log.info("Processing request for URI: {}", requestURI);

        if (requestURI.startsWith("/swagger-ui/") || requestURI.equals("/v3/api-docs") || requestURI.startsWith("/v3/api-docs/")) {
            log.info("Skipping JWT validation for Swagger/UI/OpenAPI route: {}", requestURI);
            chain.doFilter(request, response);
            return;
        }

        if (jwtConfigProperties.getOpenUrls().stream().anyMatch(openUrl -> matchesPattern(requestURI, openUrl))) {
            log.info("Skipping JWT validation for open URL: {}", requestURI);
            chain.doFilter(request, response);
            return;
        }

//        if (jwtConfigProperties.getOpenUrls().contains(requestURI)) {
//            chain.doFilter(request, response);
//            return;
//        }

        String registration = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                registration = securityTokenComponent.getRegistrationFromToken(jwt);
            } catch (Exception e) {
                errorResponse(request, response, new UnauthorizedException("Token inválido ou expirado"));
                return;
            }
        }

        if (registration == null && SecurityContextHolder.getContext().getAuthentication() == null) {
            errorResponse(request, response, new UnauthorizedException("Token inválido ou expirado"));
            return;
        }

        if (registration != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(registration);

            if (securityTokenComponent.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                errorResponse(request, response, new UnauthorizedException("Token inválido ou expirado"));
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private void errorResponse(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
        StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(), "Acesso não autorizado", e.getMessage(), request.getRequestURI());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(standardError));
    }

    private boolean matchesPattern(String requestURI, String pattern) {
        if (pattern.endsWith("/**")) {
            String basePattern = pattern.substring(0, pattern.length() - 3);
            return requestURI.startsWith(basePattern);
        }
        return requestURI.equals(pattern);
    }
}
