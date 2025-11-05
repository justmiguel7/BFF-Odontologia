package com.proyecto.odontologiabff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;
import com.proyecto.odontologiabff.dto.*;
import com.proyecto.odontologiabff.requester.HistorialClinicoRequester;
import java.util.List;

@Service
public class HistorialClinicoServiceImp implements HistorialClinicoService {

    @Autowired
    private HistorialClinicoRequester historialRequester;

    @Autowired
    private RestTemplate restTemplate;

    private final String HISTORIAL_URL = "http://localhost:8086/historialclinico";
    private final String HISTORIAL_TRATAMIENTO_URL = "http://localhost:8086/api/historial-tratamiento";
    private final String TRATAMIENTO_URL = "http://localhost:8087/tratamientos";

    @Override
    public void crearHistorialClinico(HistorialClinicoDTO dto) throws Exception {
        List<Integer> idTratamientos = dto.getIdtratamientos();

        // ✅ Validar que todos los tratamientos existan
        if (idTratamientos != null && !idTratamientos.isEmpty()) {
            for (Integer idTratamiento : idTratamientos) {
                try {
                    restTemplate.getForObject(TRATAMIENTO_URL + "/" + idTratamiento, TratamientosDTO.class);
                } catch (HttpClientErrorException.NotFound e) {
                    throw new IllegalArgumentException("❌ El tratamiento con ID " + idTratamiento + " no existe.");
                } catch (ResourceAccessException e) {
                    throw new Exception("⚠️ No se puede conectar con el microservicio de tratamientos.");
                }
            }
        }

        // ✅ Guardar historial clínico (sin tratamientos todavía)
        HistorialClinicoDTO historialGuardado = historialRequester.enviarNuevoHistorialClinico(dto);

        // Asegurarse que el historial guardado tenga un id asignado
        if (historialGuardado.getIdhistorial() == 0) {
            throw new Exception("❌ No se pudo crear el historial clínico. El ID no fue asignado.");
        }

        // ✅ Guardar relaciones en tabla intermedia historial_tratamiento
        if (idTratamientos != null && !idTratamientos.isEmpty()) {
            for (Integer idTratamiento : idTratamientos) {
                HistorialTratamientoDTO relacion = new HistorialTratamientoDTO();
                relacion.setIdhistorial(historialGuardado.getIdhistorial());
                relacion.setIdtratamiento(idTratamiento);

                try {
                    restTemplate.postForObject(HISTORIAL_TRATAMIENTO_URL, relacion, Void.class);
                } catch (Exception e) {
                    throw new Exception("⚠️ Error al guardar el tratamiento " + idTratamiento + " en el historial.");
                }
            }
        }
    }


    @Override
    public HistorialConTratamientoDTO obtenerHistorialConTratamiento(String dnipaciente) throws Exception {
        // Obtener el historial clínico
        HistorialClinicoDTO historial = restTemplate.getForObject(
                HISTORIAL_URL + "/buscar/" + dnipaciente,
                HistorialClinicoDTO.class
        );

        if (historial == null) return null;

        // Crear objeto DTO para retornar
        HistorialConTratamientoDTO resultado = new HistorialConTratamientoDTO();
        resultado.setDnipaciente(historial.getDnipaciente());
        resultado.setDniodontologo(historial.getDniodontologo());
        resultado.setMotivodeconsulta(historial.getMotivodeconsulta());
        resultado.setFechadeconsulta(historial.getFechadeconsulta());
        resultado.setDiagnostico(historial.getDiagnostico());
        resultado.setObservaciones(historial.getObservaciones());
        resultado.setAlergias(historial.getAlergias());
        resultado.setAntecedentesmedicos(historial.getAntecedentesmedicos());

        // ✅ Obtener los tratamientos asociados al historial
        List<HistorialTratamientoDTO> relaciones = List.of(restTemplate.getForObject(
                HISTORIAL_TRATAMIENTO_URL + "/" + historial.getIdhistorial(),
                HistorialTratamientoDTO[].class
        ));

        // ✅ Consultar los detalles de cada tratamiento desde el microservicio de tratamientos
        List<TratamientosDTO> tratamientos = relaciones.stream().map(rel -> {
            try {
                return restTemplate.getForObject(
                        TRATAMIENTO_URL + "/" + rel.getIdtratamiento(),
                        TratamientosDTO.class
                );
            } catch (Exception e) {
                return null; // En caso de error, no se agrega el tratamiento
            }
        }).filter(t -> t != null).toList(); // Filtra tratamientos nulos

        resultado.setTratamientos(tratamientos);

        return resultado;
    }
}