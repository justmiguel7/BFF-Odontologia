package com.proyecto.odontologiabff.requester;

import com.proyecto.odontologiabff.dto.TurnoDTO;
import com.proyecto.odontologiabff.dto.PacienteDTO;
import com.proyecto.odontologiabff.dto.OdontologoDTO;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class TurnoRequesterImp implements TurnoRequester {

    private final RestTemplate restTemplate;

    @Value("${ms.turno.url}")
    private String urlTurno;

    @Value("${ms.paciente.url}")
    private String urlPaciente;

    @Value("${ms.odontologo.url}")
    private String urlOdontologo;

    private String pathAgregar = "/agregar";
    private String pathListar = "/listado";

    private static final Logger log = LoggerFactory.getLogger(TurnoRequesterImp.class);

    public TurnoRequesterImp(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public void enviarNuevoTurno(TurnoDTO turnoDTO) {
        try {
            HttpEntity<TurnoDTO> entity = new HttpEntity<>(turnoDTO);
            log.info("Enviando turno al microservicio TURNOS: {}", entity);
            restTemplate.exchange(urlTurno + "/agregar", HttpMethod.POST, entity, Void.class);

        } catch (HttpClientErrorException e) {
            String mensaje = e.getResponseBodyAsString();
            log.warn("Error recibido del microservicio turno: {}", mensaje);

            if (mensaje.contains("Ya existe un turno reservado")) {
                // ⚠️ Lanza excepción que el Service y Controller capturan
                throw new IllegalArgumentException("Ya existe un turno reservado para esa fecha y hora.");
            }

            throw new IllegalArgumentException("Error al crear turno: " + mensaje);

        } catch (Exception e) {
            log.error("Error inesperado al enviar turno", e);
            throw new RuntimeException("Error inesperado al crear el turno: " + e.getMessage(), e);
        }
    }


    @Override
    public List<TurnoDTO> obtenerTurnos() {
        String url = urlTurno.concat(pathListar);
        ResponseEntity<TurnoDTO[]> response = restTemplate.getForEntity(url, TurnoDTO[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public boolean existePaciente(String dnipaciente) {
        try {
            String url = urlPaciente + "/buscarPorDni/" + dnipaciente;
            ResponseEntity<PacienteDTO> response = restTemplate.getForEntity(url, PacienteDTO.class);
            return response.getStatusCode().is2xxSuccessful() && response.getBody() != null;
        } catch (Exception e) {
            log.warn("Paciente con DNI {} no encontrado: {}", dnipaciente, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean existeOdontologo(String dniodontologo) {
        try {
            String url = urlOdontologo + "/buscarPorDni/" + dniodontologo;
            ResponseEntity<OdontologoDTO> response = restTemplate.getForEntity(url, OdontologoDTO.class);
            return response.getStatusCode().is2xxSuccessful() && response.getBody() != null;
        } catch (Exception e) {
            log.warn("Odontólogo con DNI {} no encontrado: {}", dniodontologo, e.getMessage());
            return false;
        }
    }

    @Override
    public String obtenerEmailPaciente(String dnipaciente) {
        try {
            String url = urlPaciente + "/buscarPorDni/" + dnipaciente;
            ResponseEntity<PacienteDTO> response = restTemplate.getForEntity(url, PacienteDTO.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody().getEmail();
            }
        } catch (Exception e) {
            log.warn("No se pudo obtener email del paciente {}: {}", dnipaciente, e.getMessage());
        }
        return null;
    }
}