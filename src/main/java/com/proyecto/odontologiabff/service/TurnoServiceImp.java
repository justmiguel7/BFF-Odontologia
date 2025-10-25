package com.proyecto.odontologiabff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.odontologiabff.dto.TurnoDTO;
import com.proyecto.odontologiabff.modelo.Mail;
import com.proyecto.odontologiabff.requester.TurnoRequester;

@Service
public class TurnoServiceImp implements TurnoService {

    @Autowired
    private TurnoRequester turnoRequester;

    @Autowired
    private MailService mailService; // <-- inyectamos el servicio de correo

    @Override
    public void crearTurno(TurnoDTO turnoDTO) throws Exception {
        turnoRequester.enviarNuevoTurno(turnoDTO);
        // Opcional: enviar mail genérico
        String emailPaciente = turnoRequester.obtenerEmailPaciente(turnoDTO.getDnipaciente());
        if (emailPaciente != null) {
            Mail mail = new Mail();
            mail.setTo(emailPaciente);
            mail.setSubjet("Turno creado");
            mail.setText("Tu turno ha sido creado correctamente. Fecha y hora: " + turnoDTO.getFechaYHora());
            mailService.enviar(mail);
        }
    }

    @Override
    public void crearTurnoPaciente(TurnoDTO turnoDTO) throws Exception {
        // Validar existencia de paciente
        if (!turnoRequester.existePaciente(turnoDTO.getDnipaciente())) {
            throw new IllegalArgumentException("El paciente con DNI " + turnoDTO.getDnipaciente() + " no existe");
        }

        // Enviar turno al microservicio
        turnoRequester.enviarNuevoTurno(turnoDTO);

        // Obtener email del paciente y enviar mail
        String emailPaciente = turnoRequester.obtenerEmailPaciente(turnoDTO.getDnipaciente());
        if (emailPaciente != null) {
            Mail mail = new Mail();
            mail.setTo(emailPaciente);
            mail.setSubjet("Turno solicitado");
            mail.setText("Tu turno ha sido solicitado correctamente. Fecha y hora: " + turnoDTO.getFechaYHora());
            mailService.enviar(mail);
        }
    }

    @Override
    public void crearTurnoOdontologo(TurnoDTO turnoDTO) throws Exception {
        // Validar existencia de paciente y odontólogo
        if (!turnoRequester.existePaciente(turnoDTO.getDnipaciente())) {
            throw new IllegalArgumentException("El paciente con DNI " + turnoDTO.getDnipaciente() + " no existe");
        }
        if (!turnoRequester.existeOdontologo(turnoDTO.getDniodontologo())) {
            throw new IllegalArgumentException("El odontólogo con DNI " + turnoDTO.getDniodontologo() + " no existe");
        }

        // Enviar turno al microservicio
        turnoRequester.enviarNuevoTurno(turnoDTO);

        // Obtener email del paciente y enviar mail
        String emailPaciente = turnoRequester.obtenerEmailPaciente(turnoDTO.getDnipaciente());
        if (emailPaciente != null) {
            Mail mail = new Mail();
            mail.setTo(emailPaciente);
            mail.setSubjet("Turno confirmado");
            mail.setText("Tu turno ha sido confirmado por el odontólogo. Fecha y hora: " + turnoDTO.getFechaYHora());
            mailService.enviar(mail);
        }
    }

    @Override
    public boolean existePaciente(String dniPaciente) {
        return turnoRequester.existePaciente(dniPaciente);
    }

    @Override
    public boolean existeOdontologo(String dniOdontologo) {
        return turnoRequester.existeOdontologo(dniOdontologo);
    }
}