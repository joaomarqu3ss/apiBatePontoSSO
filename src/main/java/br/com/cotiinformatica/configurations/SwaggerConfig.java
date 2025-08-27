package br.com.cotiinformatica.configurations;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	/* @Bean
     public OpenAPI customOpenAPI() {
         return new OpenAPI()
                 .info(new Info().title("API BATE PONTO").version("v1"))
                 .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication")) // Aplica globalmente
                 .components(new Components()
                         .addSecuritySchemes("Bearer Authentication", new SecurityScheme()
                                 .type(SecurityScheme.Type.HTTP)
                                 .scheme("bearer")
                                 .bearerFormat("JWT"))); // Informa o tipo e o formato
     } */
}
