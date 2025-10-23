package com.proyecto.odontologiabff.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proyecto.odontologiabff.dto.TratamientosDTO;
import com.proyecto.odontologiabff.requester.TratamientosRequester;

@Service
public class TratamientosServiceImp implements TratamientosService{
	
	@Autowired
	private TratamientosRequester tratamientoRequester;
	
	@Override
	public void crearTratamiento(TratamientosDTO tratamientosDTO) throws Exception{
		this.tratamientoRequester.enviarNuevoTratamiento(tratamientosDTO);
	}

}
