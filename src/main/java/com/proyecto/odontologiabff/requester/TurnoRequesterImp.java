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
public class TurnoRequesterImp implements TurnoRequester{
	
	private final RestTemplate restTemplate;

    @Value("${ms.turno.url}")
    private String urlTurno;

    @Value("${ms.paciente.url}")
    private String urlPaciente;

    @Value("${ms.odontologo.url}")
    private String urlOdontologo;
	 
		private String pathAgregar ="/agregar";
		

		private String pathListar ="/listar";
	 
		private static final Logger log = LoggerFactory.getLogger(TurnoRequesterImp.class);

	 
	  public TurnoRequesterImp(RestTemplateBuilder builder) {
	        this.restTemplate = builder.build();
	    }

	  @Override
	    public void enviarNuevoTurno(TurnoDTO turnoDTO) {
	        HttpEntity<TurnoDTO> entity = new HttpEntity<>(turnoDTO);
	        log.info("Se envía turno al microservicio TURNOS: {}", entity);
	        this.restTemplate.exchange(urlTurno.concat(pathAgregar), HttpMethod.POST, entity, Void.class);
	    }
	    
	  @Override
	  public boolean existePaciente(String dnipaciente) {
	      try {
	          String url = urlPaciente + "/buscarPorDni/" + dnipaciente;
	          ResponseEntity<PacienteDTO> response = restTemplate.getForEntity(url, PacienteDTO.class);
	          return response.getStatusCode().is2xxSuccessful() && response.getBody() != null;
	      } catch (Exception e) {
	          log.warn("Paciente con DNI {} no encontrado: {}", dnipaciente, e.getMessage());
	          return false;
	      }
	  }

	  @Override
	  public boolean existeOdontologo(String dniodontologo) {
	      try {
	          String url = urlOdontologo + "/buscarPorDni/" + dniodontologo;
	          ResponseEntity<OdontologoDTO> response = restTemplate.getForEntity(url, OdontologoDTO.class);
	          return response.getStatusCode().is2xxSuccessful() && response.getBody() != null;
	      } catch (Exception e) {
	          log.warn("Odontólogo con DNI {} no encontrado: {}", dniodontologo, e.getMessage());
	          return false;
	      }
	  }


	}