package com.proyecto.odontologiabff.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TurnoDTO {
    private int idturno;
    private String dnipaciente;
    private String dniodontologo;
    private LocalDateTime fechaYHora;
    private EstadoTurno estado;

    public TurnoDTO() {
    }

    public TurnoDTO(String dnipaciente, String dniodontologo, LocalDateTime fechaYHora, EstadoTurno estado) {
        this.dnipaciente = dnipaciente;
        this.dniodontologo = dniodontologo;
        this.fechaYHora = fechaYHora;
        this.estado = estado;
    }
}
