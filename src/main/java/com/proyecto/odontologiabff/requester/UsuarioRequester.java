package com.proyecto.odontologiabff.requester;

import lombok.Data;

import java.util.List;

import com.proyecto.odontologiabff.dto.RolUsuario;
import com.proyecto.odontologiabff.dto.UsuarioDTO;


public interface UsuarioRequester {

    void registrarUsuario(UsuarioDTO usuarioDTO);
    String loginUsuario(UsuarioDTO usuarioDTO); // Devuelve el JWT
}