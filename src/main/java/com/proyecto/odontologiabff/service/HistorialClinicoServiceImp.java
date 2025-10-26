package com.proyecto.odontologiabff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;
import com.proyecto.odontologiabff.dto.*;
import com.proyecto.odontologiabff.requester.HistorialClinicoRequester;

@Service
public class HistorialClinicoServiceImp implements HistorialClinicoService {

    @Autowired
    private HistorialClinicoRequester historialRequester;

    @Autowired
    private RestTemplate restTemplate;

    private final String HISTORIAL_URL = "http://localhost:8086/historialclinico";
    private final String TRATAMIENTO_URL = "http://localhost:8087/tratamientos";

    @Override
    public void crearHistorialClinico(HistorialClinicoDTO dto) throws Exception {
        int idTratamiento = dto.getIdtratamiento();

        if (idTratamiento > 0) {
            try {
                restTemplate.getForObject(TRATAMIENTO_URL + "/" + idTratamiento, TratamientosDTO.class);
            } catch (HttpClientErrorException.NotFound | HttpServerErrorException e) {
                throw new IllegalArgumentException("❌ El tratamiento con ID " + idTratamiento + " no existe.");
            } catch (ResourceAccessException e) {
                throw new Exception("⚠️ No se puede conectar con el microservicio de tratamientos.");
            }
        }

        historialRequester.enviarNuevoHistorialClinico(dto);
    }

    @Override
    public HistorialConTratamientoDTO obtenerHistorialConTratamiento(String dnipaciente) throws Exception {
        HistorialClinicoDTO historial = restTemplate.getForObject(HISTORIAL_URL + "/buscar/" + dnipaciente, HistorialClinicoDTO.class);
        if (historial == null) return null;

        HistorialConTratamientoDTO resultado = new HistorialConTratamientoDTO();
        resultado.setDnipaciente(historial.getDnipaciente());
        resultado.setDniodontologo(historial.getDniodontologo());
        resultado.setIdtratamiento(historial.getIdtratamiento());
        resultado.setMotivodeconsulta(historial.getMotivodeconsulta());
        resultado.setFechadeconsulta(historial.getFechadeconsulta());
        resultado.setDiagnostico(historial.getDiagnostico());
        resultado.setObservaciones(historial.getObservaciones());
        resultado.setAlergias(historial.getAlergias());
        resultado.setAntecedentesmedicos(historial.getAntecedentesmedicos());

        if (historial.getIdtratamiento() > 0) {
            try {
                TratamientosDTO tratamiento = restTemplate.getForObject(
                    TRATAMIENTO_URL + "/" + historial.getIdtratamiento(),
                    TratamientosDTO.class
                );
                resultado.setTratamiento(tratamiento);
            } catch (Exception e) {
                resultado.setTratamiento(null);
            }
        }

        return resultado;
    }
}
