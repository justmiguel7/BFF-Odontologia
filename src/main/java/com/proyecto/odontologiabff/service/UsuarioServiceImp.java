package com.proyecto.odontologiabff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.odontologiabff.dto.LoginDTO;
import com.proyecto.odontologiabff.dto.PacienteDTO;
import com.proyecto.odontologiabff.dto.RegistroPacienteDTO;
import com.proyecto.odontologiabff.dto.RolUsuario;
import com.proyecto.odontologiabff.dto.UsuarioDTO;
import com.proyecto.odontologiabff.requester.UsuarioRequester;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRequester usuarioRequester;

    @Autowired
    private PacienteService pacienteService;
    
    @Autowired
    private DienteService dienteService;

    @Override
    public void registrarPaciente(RegistroPacienteDTO dto) {
        try {
            // Crear usuario con rol PACIENTE: solo username (correo) y password
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setUsername(dto.getUsername()); // o dto.getCorreo() si así lo espera el microservicio
            usuarioDTO.setPassword(dto.getPassword());
            usuarioDTO.setRol(RolUsuario.PACIENTE);
            usuarioDTO.setDni(dto.getDni()); // <--- el mismo DNI
            // Mandar al microservicio de usuarios
            usuarioRequester.registrarUsuario(usuarioDTO);

            // Crear paciente en el microservicio de pacientes, aquí sí mandamos el DNI
            PacienteDTO pacienteDTO = new PacienteDTO(
                dto.getNombre(),
                dto.getApellido(),
                dto.getDireccion(),
                dto.getDni(),
                dto.getTelefono(),
                dto.getUsername(),
                dto.getFecharegistro()
            );

            dienteService.crearDientesBase(dto.getDni());

            
            pacienteService.crearPaciente(pacienteDTO);

        } catch (Exception e) {
            throw new RuntimeException("Error al registrar paciente: " + e.getMessage(), e);
        }
    }
    @Override
    public String loginUsuario(LoginDTO loginDTO) {
        return usuarioRequester.loginUsuario(loginDTO);
    }
}
