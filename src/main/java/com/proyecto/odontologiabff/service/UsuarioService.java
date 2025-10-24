package com.proyecto.odontologiabff.service;

import com.proyecto.odontologiabff.dto.LoginDTO;
import com.proyecto.odontologiabff.dto.RegistroPacienteDTO;

public interface UsuarioService {
    void registrarPaciente(RegistroPacienteDTO dto);
    String loginUsuario(LoginDTO loginDTO);
}
