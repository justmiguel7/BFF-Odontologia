package com.proyecto.odontologiabff.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OdontologoDTO {
    
	private int idodontologo;
	
	private String nombre;

	private String apellido;
	
	private String dni;

	private String especializacion;

	private String matricula;

	private String telefono;

	private String email;

	private LocalDate fecharegistro;


	
	
	public OdontologoDTO() {
		
		
		
	}
	public OdontologoDTO(String nombre, String apellido, String especializacion, String matricula,
			String telefono, String email, LocalDate fecharegistro) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.especializacion = especializacion;
		this.matricula = matricula;
		this.telefono = telefono;
		this.email = email;
		this.fecharegistro = fecharegistro;
	}

	
	
	
}
