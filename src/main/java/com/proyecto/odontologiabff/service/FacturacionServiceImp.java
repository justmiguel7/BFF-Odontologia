package com.proyecto.odontologiabff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proyecto.odontologiabff.dto.FacturacionDTO;
import com.proyecto.odontologiabff.requester.FacturacionRequester;

@Service
public class FacturacionServiceImp implements FacturacionService{
	
	@Autowired
	private FacturacionRequester facturacionRequester;
	
	@Override
	public void crearFactura(FacturacionDTO facturacionDTO) throws Exception{
		this.facturacionRequester.enviarNuevoFactura(facturacionDTO);
	}

}
