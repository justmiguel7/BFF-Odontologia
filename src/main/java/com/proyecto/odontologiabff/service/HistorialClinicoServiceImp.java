package com.proyecto.odontologiabff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
