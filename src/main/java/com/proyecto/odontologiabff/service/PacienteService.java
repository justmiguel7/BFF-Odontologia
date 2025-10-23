package com.proyecto.odontologiabff.service;


import com.proyecto.odontologiabff.dto.PacienteDTO;


public interface PacienteService {

	void crearPaciente(PacienteDTO pacienteDTO) throws Exception;

    PacienteDTO obtenerPacientePorId(int idPaciente);

	
}
