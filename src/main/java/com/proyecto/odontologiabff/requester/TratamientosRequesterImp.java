package com.proyecto.odontologiabff.requester;

import com.proyecto.odontologiabff.dto.TratamientosDTO;
import com.proyecto.odontologiabff.dto.PacienteDTO;
import com.proyecto.odontologiabff.dto.OdontologoDTO;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TratamientosRequesterImp implements TratamientosRequester{
	
	private final RestTemplate restTemplate;

	 @Value("${ms.tratamientos.url}")
	    private String urlBase;
	 
		private String pathAgregar ="/agregar";
		

		private String pathListar ="/listar";
	 
		private static final Logger log = LoggerFactory.getLogger(TratamientosRequesterImp.class);

	 
	  public TratamientosRequesterImp(RestTemplateBuilder builder) {
	        this.restTemplate = builder.build();
	    }

	    @Override
	    public void enviarNuevoTratamiento(TratamientosDTO tratamientosDTO) {
	    	
	        HttpEntity<TratamientosDTO> entity = new HttpEntity<>(tratamientosDTO);
			log.info("se envia datos {}", entity);
	        this.restTemplate.exchange(urlBase.concat(pathAgregar), HttpMethod.POST,entity , PacienteDTO.class, OdontologoDTO.class);
	    }
	}