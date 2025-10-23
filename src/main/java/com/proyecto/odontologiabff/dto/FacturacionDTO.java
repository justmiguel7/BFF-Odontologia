package com.proyecto.odontologiabff.dto;

import java.math.BigDecimal;

import java.time.LocalDateTime;

import lombok.Data;






@Data
public class FacturacionDTO {

	
	private int id;
	
	
	private String pacienteId;

	
	
	private BigDecimal montototal;

	private LocalDateTime fechaemision;
	
	private MetodoPago metodopago;
	
	
	public FacturacionDTO() {
		
		
	}


	public FacturacionDTO( String pacienteId, BigDecimal montototal, LocalDateTime fechaemision,
			MetodoPago metodopago) {
		super();
	
		this.pacienteId = pacienteId;
		this.montototal = montototal;
		this.fechaemision = fechaemision;
		this.metodopago = metodopago;
	}
	
	
	
	
}
