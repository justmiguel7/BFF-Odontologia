package com.proyecto.odontologiabff.requester;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.proyecto.odontologiabff.dto.DienteDTO;

@Service
public class DienteRequesterImp implements DienteRequester {

    private final RestTemplate restTemplate;

    @Value("${ms.diente.url}")
    private String urlDiente;

    public DienteRequesterImp(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public List<DienteDTO> crearDientesBase(String dniPaciente) {
        String url = urlDiente + "/paciente/" + dniPaciente + "/crear-base";
        ResponseEntity<DienteDTO[]> response = restTemplate.postForEntity(url, null, DienteDTO[].class);
        return Arrays.asList(response.getBody());
    }

    @Override
    public List<DienteDTO> listarDientesPorPaciente(String dniPaciente) {
        String url = urlDiente + "/paciente/" + dniPaciente;
        ResponseEntity<DienteDTO[]> response = restTemplate.getForEntity(url, DienteDTO[].class);
        return Arrays.asList(response.getBody());
    }
}
