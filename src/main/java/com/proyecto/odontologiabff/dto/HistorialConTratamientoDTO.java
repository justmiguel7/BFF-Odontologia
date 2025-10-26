package com.proyecto.odontologiabff.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class HistorialConTratamientoDTO {

    private String dnipaciente;
    private String dniodontologo;
    private int idtratamiento;
    private String motivodeconsulta;
    private LocalDateTime fechadeconsulta;
    private String diagnostico;
    private String observaciones;
    private String alergias;
    private String antecedentesmedicos;

    private TratamientosDTO tratamiento; // Datos del microservicio de tratamientos
}
