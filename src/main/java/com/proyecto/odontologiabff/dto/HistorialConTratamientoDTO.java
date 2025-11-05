package com.proyecto.odontologiabff.dto;

import java.time.LocalDateTime;
import java.util.List;

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

    // Aquí definimos la lista de tratamientos
    private List<TratamientosDTO> tratamientos;  // tipo lista

    // Getter y Setter para la lista de tratamientos
    public List<TratamientosDTO> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<TratamientosDTO> tratamientos) {
        this.tratamientos = tratamientos;
    }
}
