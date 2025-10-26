package com.proyecto.odontologiabff.service;

import com.proyecto.odontologiabff.dto.HistorialClinicoDTO;
import com.proyecto.odontologiabff.dto.HistorialConTratamientoDTO;

public interface HistorialClinicoService {
    void crearHistorialClinico(HistorialClinicoDTO historialClinicoDTO) throws Exception;
    HistorialConTratamientoDTO obtenerHistorialConTratamiento(String dnipaciente) throws Exception;
}
