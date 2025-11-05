package com.proyecto.odontologiabff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.odontologiabff.dto.LoginDTO;
import com.proyecto.odontologiabff.dto.PacienteDTO;
import com.proyecto.odontologiabff.dto.RegistroPacienteDTO;
import com.proyecto.odontologiabff.dto.RolUsuario;
import com.proyecto.odontologiabff.dto.UsuarioDTO;
import com.proyecto.odontologiabff.modelo.Mail;
import com.proyecto.odontologiabff.requester.UsuarioRequester;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRequester usuarioRequester;

    @Autowired
    private PacienteService pacienteService;
    
    @Autowired
    private DienteService dienteService;
    
    @Autowired
    private MailService mailService;

    @Override
    public void registrarPaciente(RegistroPacienteDTO dto) {
        try {
            // 1️⃣ Crear usuario en microservicio de usuarios
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setUsername(dto.getUsername());
            usuarioDTO.setPassword(dto.getPassword());
            usuarioDTO.setRol(RolUsuario.PACIENTE);
            usuarioDTO.setDni(dto.getDni());

            // Este método ahora devuelve el token de verificación
            String tokenVerificacion = usuarioRequester.registrarUsuario(usuarioDTO);

            // 2️⃣ Crear paciente en microservicio de pacientes
            PacienteDTO pacienteDTO = new PacienteDTO(
                dto.getNombre(),
                dto.getApellido(),
                dto.getDireccion(),
                dto.getDni(),
                dto.getTelefono(),
                dto.getUsername(),
                dto.getFecharegistro()
            );
            pacienteService.crearPaciente(pacienteDTO);
            dienteService.crearDientesBase(dto.getDni());

            // 3️⃣ Enviar mail de verificación
            Mail mail = new Mail();
            mail.setTo(dto.getUsername());
            mail.setSubjet("Verificación de cuenta DentalHub");
            mail.setText("¡Bienvenido! Verifica tu cuenta haciendo clic en el siguiente enlace:\n"
                        + "http://localhost:8085/auth/verify?token=" + tokenVerificacion);
            mailService.enviar(mail);

        } catch (Exception e) {
            throw new RuntimeException("Error al registrar paciente: " + e.getMessage(), e);
        }
    }

    @Override
    public String loginUsuario(LoginDTO loginDTO) {
        return usuarioRequester.loginUsuario(loginDTO);
    }
}