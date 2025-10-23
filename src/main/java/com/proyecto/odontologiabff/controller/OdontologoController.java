package com.proyecto.odontologiabff.controller;

import com.proyecto.odontologiabff.dto.OdontologoDTO;
import com.proyecto.odontologiabff.dto.PacienteDTO;
import com.proyecto.odontologiabff.requester.OdontologoRequesterImp;
import com.proyecto.odontologiabff.service.OdontologoService;

import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Odontologo")
@CrossOrigin(origins = "http://localhost:4200")
public class OdontologoController {

	private static final Logger log = LoggerFactory.getLogger(OdontologoRequesterImp.class);

	
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping(value = "/agregarOdontologo", produces = {MediaType.APPLICATION_JSON_VALUE } , consumes = {MediaType.APPLICATION_JSON_VALUE } )
    public ResponseEntity<?> crearOdontologo(@RequestBody OdontologoDTO odontologoDTO) throws Exception {
    	odontologoService.crearOdontologo(odontologoDTO);
    		log.info("se ingresa {}", odontologoDTO);
    	
        return ResponseEntity.ok("Odontologo creado correctamente");
    }}

