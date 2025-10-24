package com.proyecto.odontologiabff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.odontologiabff.dto.UsuarioDTO;
import com.proyecto.odontologiabff.requester.UsuarioRequester;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRequester usuarioRequester;

    @Override
    public void registrarUsuario(UsuarioDTO usuarioDTO) {
        usuarioRequester.registrarUsuario(usuarioDTO);
    }

    @Override
    public String loginUsuario(UsuarioDTO usuarioDTO) {
        return usuarioRequester.loginUsuario(usuarioDTO);
    }
}