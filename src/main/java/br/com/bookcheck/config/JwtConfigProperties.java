package br.com.bookcheck.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "security.jwt")
public class JwtConfigProperties {

    private String secretKey;
    private Long expirationSize;
    private List<String> openUrls;

}
