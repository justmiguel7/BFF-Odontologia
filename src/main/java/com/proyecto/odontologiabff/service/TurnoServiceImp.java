package com.proyecto.odontologiabff.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.proyecto.odontologiabff.dto.EstadoTurno;
import com.proyecto.odontologiabff.dto.OdontologoDTO;
import com.proyecto.odontologiabff.dto.TurnoDTO;
import com.proyecto.odontologiabff.modelo.Mail;
import com.proyecto.odontologiabff.requester.TurnoRequester;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

@Service
public class TurnoServiceImp implements TurnoService {
	
    @Value("${ms.odontologo.url}")
    private String urlOdontologo;

    @Value("${ms.turno.url}")
    private String urlTurno;

	private RestTemplate restTemplate;
    
    public TurnoServiceImp(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
    
    @Autowired
    private TurnoRequester turnoRequester;


    @Autowired
    private MailService mailService; // <-- inyectamos el servicio de correo

    @Override
    public void crearTurno(TurnoDTO turnoDTO) throws Exception {
        turnoRequester.enviarNuevoTurno(turnoDTO);

        String emailPaciente = turnoRequester.obtenerEmailPaciente(turnoDTO.getDnipaciente());
        String nombrePaciente = turnoRequester.obtenerNombrePaciente(turnoDTO.getDnipaciente());

        if (emailPaciente != null && nombrePaciente != null) {
            Mail mail = new Mail();
            mail.setTo(emailPaciente);
            mail.setSubjet("¡Tu turno ha sido creado exitosamente!");
            mail.setText(
                "Hola " + nombrePaciente + ",\n\n" +
                "Tu turno ha sido registrado correctamente en nuestro sistema.\n" +
                "📅 Fecha y hora: " + turnoDTO.getFechaYHora() + "\n\n" +
                "Te esperamos en nuestra clínica para brindarte la mejor atención.\n\n" +
                "¡Gracias por confiar en nosotros!\n" +
                "Saludos cordiales,\n" +
                "El equipo de Odontología"
            );
            mailService.enviar(mail);
        }
    }
    

    @Override
    public TurnoDTO confirmarTurnoPorId(int idTurno, String dniOdontologo) throws Exception {
        HttpEntity<String> entity = new HttpEntity<>(dniOdontologo);

        TurnoDTO turnoActualizado = restTemplate.exchange(
                urlTurno + "/confirmar/" + idTurno,
                HttpMethod.PUT,
                entity,
                TurnoDTO.class
        ).getBody();

        if (turnoActualizado == null) {
            throw new IllegalArgumentException("No se pudo actualizar el turno en el microservicio");
        }

        turnoActualizado.setEstado(EstadoTurno.CONFIRMADO);
        turnoActualizado.setDniodontologo(dniOdontologo);

        OdontologoDTO odontologo = restTemplate.getForObject(
                urlOdontologo + "/buscarPorDni/" + dniOdontologo,
                OdontologoDTO.class
        );

        String emailPaciente = turnoRequester.obtenerEmailPaciente(turnoActualizado.getDnipaciente());
        String nombrePaciente = turnoRequester.obtenerNombrePaciente(turnoActualizado.getDnipaciente());

        if (emailPaciente != null && odontologo != null && nombrePaciente != null) {
            Mail mail = new Mail();
            mail.setTo(emailPaciente);
            mail.setSubjet("✅ Tu turno ha sido confirmado");
            mail.setText(
                "Hola " + nombrePaciente + ",\n\n" +
                "Nos complace informarte que tu turno ha sido confirmado por el odontólogo " +
                odontologo.getNombre() + " " + odontologo.getApellido() + ".\n" +
                "📅 Fecha y hora: " + turnoActualizado.getFechaYHora() + "\n\n" +
                "Recuerda llegar 10 minutos antes de la cita y traer tu documentación.\n\n" +
                "¡Esperamos verte pronto!\n" +
                "Saludos cordiales,\n" +
                "El equipo de Odontología"
            );
            mailService.enviar(mail);
        }

        return turnoActualizado;
    }

    
    

    @Override
    public void crearTurnoPaciente(TurnoDTO turnoDTO) throws Exception {
        if (!turnoRequester.existePaciente(turnoDTO.getDnipaciente())) {
            throw new IllegalArgumentException("El paciente con DNI " + turnoDTO.getDnipaciente() + " no existe");
        }

        try {
            turnoRequester.enviarNuevoTurno(turnoDTO);

            String emailPaciente = turnoRequester.obtenerEmailPaciente(turnoDTO.getDnipaciente());
            String nombrePaciente = turnoRequester.obtenerNombrePaciente(turnoDTO.getDnipaciente());

            if (emailPaciente != null && nombrePaciente != null) {
                Mail mail = new Mail();
                mail.setTo(emailPaciente);
                mail.setSubjet("📌 Tu turno ha sido solicitado");
                mail.setText(
                    "Hola " + nombrePaciente + ",\n\n" +
                    "Tu solicitud de turno ha sido registrada exitosamente.\n" +
                    "📅 Fecha y hora: " + turnoDTO.getFechaYHora() + "\n\n" +
                    "Pronto recibirás la confirmación de tu turno.\n\n" +
                    "Gracias por confiar en nosotros.\n" +
                    "Saludos,\n" +
                    "El equipo de Odontología"
                );
                mailService.enviar(mail);
            }

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error al crear turno: " + e.getMessage());
        }
    }




    @Override
    public void crearTurnoOdontologo(TurnoDTO turnoDTO) throws Exception {
        if (!turnoRequester.existePaciente(turnoDTO.getDnipaciente())) {
            throw new IllegalArgumentException("El paciente con DNI " + turnoDTO.getDnipaciente() + " no existe");
        }
        if (!turnoRequester.existeOdontologo(turnoDTO.getDniodontologo())) {
            throw new IllegalArgumentException("El odontólogo con DNI " + turnoDTO.getDniodontologo() + " no existe");
        }

        turnoRequester.enviarNuevoTurno(turnoDTO);

        String emailPaciente = turnoRequester.obtenerEmailPaciente(turnoDTO.getDnipaciente());
        String nombrePaciente = turnoRequester.obtenerNombrePaciente(turnoDTO.getDnipaciente());

        if (emailPaciente != null && nombrePaciente != null) {
            Mail mail = new Mail();
            mail.setTo(emailPaciente);
            mail.setSubjet("✅ Tu turno ha sido confirmado por el odontólogo");
            mail.setText(
                "Hola " + nombrePaciente + ",\n\n" +
                "Tu turno ha sido confirmado por el odontólogo.\n" +
                "📅 Fecha y hora: " + turnoDTO.getFechaYHora() + "\n\n" +
                "Te esperamos en nuestra clínica para atenderte de la mejor manera.\n\n" +
                "Saludos cordiales,\n" +
                "El equipo de Odontología"
            );
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
    
    @Override
    public List<TurnoDTO> obtenerListadoTurnos() {
        return turnoRequester.obtenerTurnos();
    }
    
    
    @Override
    public TurnoDTO cancelarTurno(int idTurno) throws Exception {
        // 🔹 Llamamos al microservicio TURNOS para cancelar el turno
        TurnoDTO turnoCancelado = restTemplate.exchange(
                urlTurno + "/cancelar/" + idTurno,
                HttpMethod.PUT,
                null,
                TurnoDTO.class
        ).getBody();

        if (turnoCancelado == null) {
            throw new IllegalArgumentException("No se pudo cancelar el turno en el microservicio");
        }

        // 🔹 Seteamos el estado CANCELADO (por si el microservicio no lo devuelve actualizado)
        turnoCancelado.setEstado(EstadoTurno.CANCELADO);

        // 🔹 Traemos datos del paciente (para enviarle correo)
        String emailPaciente = turnoRequester.obtenerEmailPaciente(turnoCancelado.getDnipaciente());

        // 🔹 Enviar mail al paciente notificando cancelación
        if (emailPaciente != null) {
            Mail mail = new Mail();
            mail.setTo(emailPaciente);
            mail.setSubjet("Turno cancelado");
            mail.setText("Tu turno del día " + turnoCancelado.getFechaYHora()
                    + " ha sido cancelado. Si deseas reagendarlo, por favor comunícate con la clínica.");

            mailService.enviar(mail);
        }

        return turnoCancelado;
    }
    
}