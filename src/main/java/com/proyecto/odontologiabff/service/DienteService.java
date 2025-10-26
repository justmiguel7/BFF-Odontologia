package com.proyecto.odontologiabff.service;

import java.util.List;
import com.proyecto.odontologiabff.dto.DienteDTO;

public interface DienteService {
    List<DienteDTO> crearDientesBase(String dniPaciente);
    List<DienteDTO> listarDientesPorPaciente(String dniPaciente);
}
