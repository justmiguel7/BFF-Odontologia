package com.proyecto.odontologiabff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.proyecto.odontologiabff.dto.HistorialClinicoDTO;
import com.proyecto.odontologiabff.requester.HistorialClinicoRequester;

@Service
public class HistorialClinicoServiceImp implements HistorialClinicoService{

	@Autowired
	private HistorialClinicoRequester historialClinicoRequester;
	
	@Override
	public void crearHistorialClinico(HistorialClinicoDTO historialClinicoDTO) throws Exception{
		this.historialClinicoRequester.enviarNuevoHistorialClinico(historialClinicoDTO);
	}
	
    @GetMapping("/{dni}")
    public HistorialClinicoDTO obtenerHistorialClinicoPorDniPaciente(@PathVariable String dni) {
        return historialClinicoRequester.obtenerHistorialPorDniPaciente(dni);
    }
    
    @GetMapping("/{dni}")
    public HistorialClinicoDTO obtenerHistorialClinicoPorDniOdontologo(@PathVariable String dni) {
        return historialClinicoRequester.obtenerHistorialPorDniOdontologo(dni);
    }
}
