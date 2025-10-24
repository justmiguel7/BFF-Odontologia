package com.proyecto.odontologiabff.service;

import java.util.List;

import com.proyecto.odontologiabff.dto.UsuarioDTO;

public interface UsuarioService {
    void registrarUsuario(UsuarioDTO usuarioDTO);
    String loginUsuario(UsuarioDTO usuarioDTO);
}