package com.proyecto.odontologiabff.requester;

import lombok.Data;

import java.util.List;

import com.proyecto.odontologiabff.dto.LoginDTO;
import com.proyecto.odontologiabff.dto.RolUsuario;
import com.proyecto.odontologiabff.dto.UsuarioDTO;


public interface UsuarioRequester {

    public String registrarUsuario(UsuarioDTO usuarioDTO);
    String loginUsuario(LoginDTO loginDTO);
}