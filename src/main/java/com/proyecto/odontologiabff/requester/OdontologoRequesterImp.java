package com.proyecto.odontologiabff.requester;

import com.proyecto.odontologiabff.dto.TurnoDTO;
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
public class OdontologoRequesterImp implements OdontologoRequester{
	
	private final RestTemplate restTemplate;

	 @Value("${ms.odontologo.url}")
	    private String urlBase;
	 
		private String pathAgregar ="/agregar";
		

		private String pathListar ="/listar";
	 
		private static final Logger log = LoggerFactory.getLogger(OdontologoRequesterImp.class);

	 
	  public OdontologoRequesterImp(RestTemplateBuilder builder) {
	        this.restTemplate = builder.build();
	    }

	    @Override
	    public void enviarNuevoOdontologo(OdontologoDTO odontologoDTO) {
	        HttpEntity<OdontologoDTO> entity = new HttpEntity<>(odontologoDTO);
			log.info("se envia datos {}", entity);
	        this.restTemplate.exchange(urlBase.concat(pathAgregar), HttpMethod.POST,entity , OdontologoDTO.class);
	    }
	}