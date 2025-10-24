package com.proyecto.odontologiabff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.odontologiabff.dto.UsuarioDTO;
import com.proyecto.odontologiabff.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.registrarUsuario(usuarioDTO);
        return ResponseEntity.status(201).body("Usuario registrado correctamente");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioDTO usuarioDTO) {
        String jwt = usuarioService.loginUsuario(usuarioDTO);
        return ResponseEntity.ok(jwt);
    }
}