package com.proyecto.odontologiabff.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RegistroPacienteDTO {
    // Datos del usuario
    private String username; // será el email
    private String password;

    // Datos del paciente
    private String nombre;
    private String apellido;
    private String direccion;
    private String dni;
    private String telefono;
    private LocalDate fecharegistro;
}
