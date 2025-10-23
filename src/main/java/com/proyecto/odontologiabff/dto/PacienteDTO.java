package com.proyecto.odontologiabff.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;

@Data
public class PacienteDTO {
    private int idpaciente;
    private String nombre;
    private String apellido;
    private String direccion;
    private String dni;
    private String telefono;
    private String email;
    private LocalDate fecharegistro;

    
    public PacienteDTO(){
    	
      }


	public PacienteDTO(String nombre, String apellido,String direccion , String dni, String telefono, String email, LocalDate fecharegistro) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion =  direccion;
		this.dni =  dni;
		this.telefono = telefono;
		this.email = email;
		this.fecharegistro =  fecharegistro;
	}
    
    
    

}
