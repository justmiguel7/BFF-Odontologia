package com.proyecto.odontologiabff.requester;


import com.proyecto.odontologiabff.dto.FacturacionDTO;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FacturacionRequesterImp implements FacturacionRequester{
	
	private final RestTemplate restTemplate;

	 @Value("${ms.facturacion.url}")
	    private String urlBase;
	 
		private String pathAgregar ="/agregar";
		

		private String pathListar ="/listar";
	 
		private static final Logger log = LoggerFactory.getLogger(OdontologoRequesterImp.class);

	 
	  public FacturacionRequesterImp(RestTemplateBuilder builder) {
	        this.restTemplate = builder.build();
	    }

	    @Override
	    public void enviarNuevoFactura(FacturacionDTO facturaDTO) {
	        HttpEntity<FacturacionDTO> entity = new HttpEntity<>(facturaDTO);
			log.info("se envia datos {}", entity);
	        this.restTemplate.exchange(urlBase.concat(pathAgregar), HttpMethod.POST,entity , FacturacionDTO.class);
	    }
	}