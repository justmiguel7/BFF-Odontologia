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
public class PacienteRequesterImp implements PacienteRequester{
	
	private final RestTemplate restTemplate;

	 @Value("${ms.paciente.url}")
	    private String urlPaciente;
	 
		private String pathAgregar ="/agregar";
		
	    private String pathBuscarPorId = "/buscar/";

		private String pathListar ="/listar";
	 
		private static final Logger log = LoggerFactory.getLogger(PacienteRequesterImp.class);

	 
	  public PacienteRequesterImp(RestTemplateBuilder builder) {
	        this.restTemplate = builder.build();
	    }

	    @Override
	    public void enviarNuevoPaciente(PacienteDTO pacienteDTO) {
	        HttpEntity<PacienteDTO> entity = new HttpEntity<>(pacienteDTO);
			log.info("se envia datos {}", entity);
	        this.restTemplate.exchange(urlPaciente.concat(pathAgregar), HttpMethod.POST,entity , PacienteDTO.class, OdontologoDTO.class);
	    }
	    
	    
	    public PacienteDTO obtenerPacientePorId(int idPaciente) {
	        try {
	            String url = urlPaciente + pathBuscarPorId + idPaciente;
	            log.info("Consultando paciente por ID: {}", idPaciente);
	            ResponseEntity<PacienteDTO> response = restTemplate.getForEntity(url, PacienteDTO.class);
	            return response.getBody();
	        } catch (Exception e) {
	            log.error("Error al obtener paciente con ID {}: {}", idPaciente, e.getMessage());
	            return null;
	        }
	    }
	    
	}