package com.proyecto.odontologiabff;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@SpringBootApplication
@ComponentScan("com.proyecto.turno.*")
@ComponentScan("com.proyecto.paciente.*")
@ComponentScan("com.proyecto.odontologo.*")
@ComponentScan("com.proyecto.odontologiabff.*")
@ComponentScan("com.proyecto.facturacion.*")
@ComponentScan("com.proyecto.historialclinico.*")
@ComponentScan("com.proyecto.usuario.*")


public class OdontologiabffApplication {

	public static void main(String[] args) {
		SpringApplication.run(OdontologiabffApplication.class, args);
	}

}
