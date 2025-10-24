package com.proyecto.odontologiabff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.odontologiabff.dto.RegistroPacienteDTO;
import com.proyecto.odontologiabff.dto.LoginDTO;
import com.proyecto.odontologiabff.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarPaciente(@RequestBody RegistroPacienteDTO dto) {
        try {
            usuarioService.registrarPaciente(dto);
            return ResponseEntity.status(201).body("Paciente registrado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al registrar paciente: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        try {
            String jwt = usuarioService.loginUsuario(loginDTO);
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body("Error en login: " + e.getMessage());
        }
    }
}
