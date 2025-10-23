package com.proyecto.odontologiabff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proyecto.odontologiabff.dto.OdontologoDTO;
import com.proyecto.odontologiabff.requester.OdontologoRequester;

@Service
public class OdontologoServiceImp implements OdontologoService{
	
	@Autowired
	private OdontologoRequester odontologoRequester;
	
	@Override
	public void crearOdontologo(OdontologoDTO odontologoDTO) throws Exception{
		this.odontologoRequester.enviarNuevoOdontologo(odontologoDTO);
	}

}
