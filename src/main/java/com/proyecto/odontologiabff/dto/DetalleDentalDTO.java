package com.proyecto.odontologiabff.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DetalleDentalDTO {
    private int id;
    private int turnoId;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;
    private LocalDateTime fechaRegistro;
}
