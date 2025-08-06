package br.com.bookcheck;

import br.com.bookcheck.config.JwtConfigProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfigProperties.class)
@EntityScan(basePackages = {"br.com.bookcheck.domain.entity"})
@EnableJpaRepositories(basePackages = {"br.com.bookcheck.domain.repository"})
public class BookcheckApplication {
	@Value("${app.allowedOrigins}")
	private String alowedOrigins;

	public static void main(String[] args) {
		SpringApplication.run(BookcheckApplication.class, args);
	}

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Fortaleza"));
	}

	/*@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
						.addMapping("/**")
						.allowedOrigins(alowedOrigins)
						.allowedMethods("POST", "PUT", "GET", "DELETE", "OPTIONS", "PATCH")
						.allowedHeaders("*");
			}
		};
	}*/
}
