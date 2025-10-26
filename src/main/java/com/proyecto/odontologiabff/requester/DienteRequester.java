package com.proyecto.odontologiabff.requester;

import java.util.List;
import com.proyecto.odontologiabff.dto.DienteDTO;

public interface DienteRequester {
	List<DienteDTO> crearDientesBase(String dniPaciente);
    List<DienteDTO> listarDientesPorPaciente(String dniPaciente);
}
