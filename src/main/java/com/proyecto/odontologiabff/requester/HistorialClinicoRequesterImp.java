package com.proyecto.odontologiabff.requester;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.proyecto.odontologiabff.dto.HistorialClinicoDTO;

@Service
public class HistorialClinicoRequesterImp implements HistorialClinicoRequester {

    private final RestTemplate restTemplate;

    @Value("${ms.historialclinico.url}")
    private String urlBase;

    private String pathAgregar = "/agregar";
    private String pathBuscarPorPaciente = "/buscarPaciente/";
    private String pathBuscarPorOdontologo = "/buscarOdontologo/";

    private static final Logger log = LoggerFactory.getLogger(HistorialClinicoRequesterImp.class);

    public HistorialClinicoRequesterImp(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public void enviarNuevoHistorialClinico(HistorialClinicoDTO historialClinicoDTO) throws Exception {
        HttpEntity<HistorialClinicoDTO> entity = new HttpEntity<>(historialClinicoDTO);
        log.info("Enviando datos: {}", entity);
        this.restTemplate.exchange(urlBase.concat(pathAgregar), HttpMethod.POST, entity, HistorialClinicoDTO.class);
    }

    @Override
    public HistorialClinicoDTO obtenerHistorialPorDniPaciente(String dniPaciente) {
        try {
            String url = urlBase + pathBuscarPorPaciente + dniPaciente;
            log.info("Consultando historial clínico por DNI de paciente: {}", dniPaciente);
            ResponseEntity<HistorialClinicoDTO> response = restTemplate.exchange(url, HttpMethod.GET, null, HistorialClinicoDTO.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Error al obtener historial clínico para el paciente con DNI {}: {}", dniPaciente, e.getMessage());
            return null;
        }
    }

    @Override
    public HistorialClinicoDTO obtenerHistorialPorDniOdontologo(String dniOdontologo) {
        try {
            String url = urlBase + pathBuscarPorOdontologo + dniOdontologo;
            log.info("Consultando historial clínico por DNI de odontólogo: {}", dniOdontologo);
            ResponseEntity<HistorialClinicoDTO> response = restTemplate.exchange(url, HttpMethod.GET, null, HistorialClinicoDTO.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Error al obtener historial clínico para el odontólogo con DNI {}: {}", dniOdontologo, e.getMessage());
            return null;
        }
    }
}
