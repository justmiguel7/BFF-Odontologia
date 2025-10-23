package com.proyecto.odontologiabff.requester;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.proyecto.odontologiabff.dto.HistorialClinicoDTO;

@Service
public class HistorialClinicoRequesterImp implements HistorialClinicoRequester{

	private final RestTemplate restTemplate;

	 @Value("${ms.historialclinico.url}")
	    private String urlBase;
	 
		private String pathAgregar ="/agregar";
		

		private String pathListar ="/listar";
	 
		private static final Logger log = LoggerFactory.getLogger(HistorialClinicoRequesterImp.class);

	 
	  public HistorialClinicoRequesterImp(RestTemplateBuilder builder) {
	        this.restTemplate = builder.build();
	    }

	    @Override
	    public void enviarNuevoHistorialClinico(HistorialClinicoDTO historialClinicoDTO) {
	        HttpEntity<HistorialClinicoDTO> entity = new HttpEntity<>(historialClinicoDTO);
			log.info("se envia datos {}", entity);
	        this.restTemplate.exchange(urlBase.concat(pathAgregar), HttpMethod.POST,entity , HistorialClinicoDTO.class);
	    }
	}
