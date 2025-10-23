package com.proyecto.odontologiabff.controller;

import com.proyecto.odontologiabff.dto.TratamientosDTO;
import com.proyecto.odontologiabff.requester.TratamientosRequesterImp;
import com.proyecto.odontologiabff.service.TratamientosService;
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
@RequestMapping("/Tratamientos")
@CrossOrigin(origins = "http://localhost:4200")
public class TratamientosController {

	private static final Logger log = LoggerFactory.getLogger(TratamientosRequesterImp.class);

	
    @Autowired
    private TratamientosService tratamientosService;

    @PostMapping(value = "/agregarTratamiento", produces = {MediaType.APPLICATION_JSON_VALUE } , consumes = {MediaType.APPLICATION_JSON_VALUE } )
    public ResponseEntity<?> CrearTratamiento(@RequestBody TratamientosDTO tratamientosDTO) throws Exception {
    	tratamientosService.crearTratamiento(tratamientosDTO);
    		log.info("se ingresa {}", tratamientosDTO);
    		
        return ResponseEntity.ok("Turno creado correctamente");
    }}

