package br.com.cotiinformatica.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {            
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // permite CORS para todos os endpoints
                        .allowedOrigins("*") // permite todas as origens
                        .allowedMethods("*") // permite todos os métodos HTTP
                        .allowedHeaders("*"); // permite todos os cabeçalhos
            }
        };
    }

}
