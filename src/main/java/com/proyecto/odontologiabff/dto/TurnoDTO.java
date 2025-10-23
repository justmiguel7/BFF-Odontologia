package com.proyecto.odontologiabff.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import com.proyecto.odontologiabff.dto.EstadoTurno;
import lombok.Data;

@Data
public class TurnoDTO {
    private int idturno;
    private int idpaciente;
    private int idodontologo;
    private LocalDateTime FechaYHora; // con mayúscula igual que en el MS
    private EstadoTurno estado;
    
    public TurnoDTO(){	
    }
    
    
    public TurnoDTO(int idpaciente, int idodontologo, LocalDateTime FechaYHora, LocalTime hora, EstadoTurno estado) {
    	super();
    	this.idpaciente = idpaciente;
    	this.idodontologo = idodontologo;
    	this.FechaYHora = FechaYHora;
    	this.estado = estado;
    	
    	
    }
    

    
}

