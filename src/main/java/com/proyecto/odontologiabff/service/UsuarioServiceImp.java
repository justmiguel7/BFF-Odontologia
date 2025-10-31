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
    private MailService mailService; // 🔹 Tu servicio de envío de correos

    @Override
    public void registrarPaciente(RegistroPacienteDTO dto) {
        try {
            // Crear usuario con rol PACIENTE
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setUsername(dto.getUsername());
            usuarioDTO.setPassword(dto.getPassword());
            usuarioDTO.setRol(RolUsuario.PACIENTE);
            usuarioDTO.setDni(dto.getDni());

            // 🔹 Registrar usuario y obtener token de verificación
            String token = usuarioRequester.registrarUsuario(usuarioDTO);

            // 🔹 Enviar mail de verificación
            String verificationLink = "http://localhost:8085/auth/verify?token=" + token;
            Mail mail = new Mail();
            mail.setTo(dto.getUsername());
            mail.setSubjet("Verifica tu cuenta - DentalHub");
            mail.setText("Hola " + dto.getNombre() + ",\n\n"
                + "Gracias por registrarte en DentalHub 🦷.\n\n"
                + "Por favor verifica tu cuenta haciendo clic en el siguiente enlace:\n"
                + verificationLink + "\n\n"
                + "Si no te registraste, ignora este correo.");

            mailService.enviar(mail);

            // 🔹 Crear paciente y sus dientes base
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
        try {
            return usuarioRequester.loginUsuario(loginDTO);
        } catch (Exception e) {
            throw new RuntimeException("No se puede iniciar sesión: " + e.getMessage());
        }
    }

}
