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

    private static final Logger log = LoggerFactory.getLogger(HistorialClinicoRequesterImp.class);

    public HistorialClinicoRequesterImp(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public HistorialClinicoDTO enviarNuevoHistorialClinico(HistorialClinicoDTO historialClinicoDTO) throws Exception {
        String url = urlBase.concat("/agregar");
        HttpEntity<HistorialClinicoDTO> entity = new HttpEntity<>(historialClinicoDTO);

        log.info("➡️ Enviando POST a URL: {}", url); 
        log.info("➡️ Datos del historial: {}", historialClinicoDTO);

        ResponseEntity<HistorialClinicoDTO> response = this.restTemplate.exchange(
            url, 
            HttpMethod.POST, 
            entity, 
            HistorialClinicoDTO.class
        );

        if (response.getStatusCode() == HttpStatus.CREATED) {
            log.info("✅ Historial clínico creado exitosamente.");
            return response.getBody();  // Devolvemos el HistorialClinicoDTO con el ID asignado.
        } else {
            throw new Exception("❌ Error al crear historial clínico.");
        }
    }
}
