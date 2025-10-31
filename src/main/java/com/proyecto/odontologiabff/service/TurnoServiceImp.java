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
    public TurnoDTO confirmarTurnoPorId(int idTurno, String dniOdontologo) throws Exception {
        // 🔹 Preparamos el body para enviar al microservicio (solo DNI del odontólogo)
        HttpEntity<String> entity = new HttpEntity<>(dniOdontologo);

        // 🔹 Llamamos al microservicio TURNOS para actualizar el turno
        TurnoDTO turnoActualizado = restTemplate.exchange(
                urlTurno + "/confirmar/" + idTurno,
                HttpMethod.PUT,
                entity,
                TurnoDTO.class
        ).getBody();

        if (turnoActualizado == null) {
            throw new IllegalArgumentException("No se pudo actualizar el turno en el microservicio");
        }

        // 🔹 Seteamos el estado CONFIRMADO en el BFF (opcional si el microservicio ya lo hace)
        turnoActualizado.setEstado(EstadoTurno.CONFIRMADO);
        turnoActualizado.setDniodontologo(dniOdontologo);

        // 🔹 Traemos datos del odontólogo para el mail
        OdontologoDTO odontologo = restTemplate.getForObject(
                urlOdontologo + "/buscarPorDni/" + dniOdontologo,
                OdontologoDTO.class
        );

        // 🔹 Enviar mail al paciente
        String emailPaciente = turnoRequester.obtenerEmailPaciente(turnoActualizado.getDnipaciente());
        if (emailPaciente != null && odontologo != null) {
            Mail mail = new Mail();
            mail.setTo(emailPaciente);
            mail.setSubjet("Turno confirmado");
            mail.setText("Tu turno ha sido confirmado por el odontólogo "
                    + odontologo.getNombre() + " " + odontologo.getApellido()
                    + " para el día " + turnoActualizado.getFechaYHora());
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

            // Enviar correo (si aplica)
            String emailPaciente = turnoRequester.obtenerEmailPaciente(turnoDTO.getDnipaciente());
            if (emailPaciente != null) {
                Mail mail = new Mail();
                mail.setTo(emailPaciente);
                mail.setSubjet("Turno solicitado");
                mail.setText("Tu turno ha sido solicitado correctamente. Fecha y hora: " + turnoDTO.getFechaYHora());
                mailService.enviar(mail);
            }

        } catch (IllegalArgumentException e) {
            // ⚠️ Llega acá cuando el microservicio turno devuelve "Ya existe un turno..."
            throw new IllegalArgumentException(e.getMessage());

        } catch (Exception e) {
            throw new Exception("Error al crear turno: " + e.getMessage());
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
    
    @Override
    public List<TurnoDTO> obtenerListadoTurnos() {
        return turnoRequester.obtenerTurnos();
    }
    
    
    
    
}