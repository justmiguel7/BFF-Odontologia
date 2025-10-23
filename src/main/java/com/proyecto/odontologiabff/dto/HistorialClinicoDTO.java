package com.proyecto.odontologiabff.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class HistorialClinicoDTO {
	private int idpaciente;
	private int idodontologo;
	private int idtratamiento;
	private String motivodeconsulta;
	private LocalDateTime fechadeconsulta;
	private String diagnostico;
	private String observaciones;
	private String alergias;
	private String antecedentesmedicos;

	public HistorialClinicoDTO(int idpaciente, int idodontologo, int idtratamiento, String motivodeconsulta,  LocalDateTime fechadeconsulta,
			String diagnostico, String observaciones, String alergias , String antecedentesmedicos) {
		super();

		this.idpaciente = idpaciente;
		this.idodontologo = idodontologo;
		this.idtratamiento = idtratamiento;
		this.motivodeconsulta = motivodeconsulta;
		this.fechadeconsulta = fechadeconsulta;
		this.diagnostico = diagnostico;
		this.observaciones = observaciones;
		this.alergias = alergias;
		this.antecedentesmedicos = antecedentesmedicos;
	}
	
	public HistorialClinicoDTO() {
		
		
	}
}
