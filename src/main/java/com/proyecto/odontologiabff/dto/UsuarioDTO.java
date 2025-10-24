package com.proyecto.odontologiabff.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.proyecto.odontologiabff.dto.RolUsuario;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UsuarioDTO {
    private String username;
    private String password;
    private RolUsuario rol; 
    private String dni; 
}