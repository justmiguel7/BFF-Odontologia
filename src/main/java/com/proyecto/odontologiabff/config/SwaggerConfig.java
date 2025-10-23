package com.proyecto.odontologiabff.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microservicio de Tratamientos - Odontología")
                        .description("API para gestionar los tratamientos odontológicos (crear, listar, editar, eliminar).")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Soporte Odontología")
                                .email("soporte@odontologia.com")
                                .url("https://odontologia.com")));
    }
}