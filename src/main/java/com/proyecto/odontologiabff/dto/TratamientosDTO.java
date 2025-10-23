package com.proyecto.odontologiabff.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;
@Data
public class TratamientosDTO {

	private int idtratamientos;
	
	private String nombre;
	private String descripcion;
	
	private BigDecimal costoBase;
	
	private String duracionEstimado;
    private LocalDateTime	fechaCreacion;
    
    
    public TratamientosDTO() {
	
}


	public TratamientosDTO( String nombre, String descripcion, BigDecimal costoBase,
			String duracionEstimado, LocalDateTime fechaCreacion) {
		super();
		
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costoBase = costoBase;
		this.duracionEstimado = duracionEstimado;
		this.fechaCreacion = fechaCreacion;
	}
    


}
