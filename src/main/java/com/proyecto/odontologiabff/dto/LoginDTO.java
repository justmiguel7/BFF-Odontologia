package com.proyecto.odontologiabff.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {
    private String username; // o email si querés usar correo
    private String password;
}
