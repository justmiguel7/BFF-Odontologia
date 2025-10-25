package com.proyecto.odontologiabff.service;

import com.proyecto.odontologiabff.dto.HistorialClinicoDTO;

public interface HistorialClinicoService {

	void crearHistorialClinico(HistorialClinicoDTO historialClinicoDTO) throws Exception;
	
	HistorialClinicoDTO obtenerHistorialClinicoPorDniPaciente(String dnipaciente);
	
	HistorialClinicoDTO obtenerHistorialClinicoPorDniOdontologo(String dniodontologo);
}
