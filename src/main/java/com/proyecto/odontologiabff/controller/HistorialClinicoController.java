package com.proyecto.odontologiabff.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.odontologiabff.dto.HistorialClinicoDTO;
import com.proyecto.odontologiabff.dto.TratamientosDTO;
import com.proyecto.odontologiabff.requester.HistorialClinicoRequesterImp;
import com.proyecto.odontologiabff.service.HistorialClinicoService;

@RestController
@RequestMapping("/historialclinico")
@CrossOrigin(origins = "http://localhost:4200")
public class HistorialClinicoController {

	
private static final Logger log = LoggerFactory.getLogger(HistorialClinicoRequesterImp.class);

	
    @Autowired
    private HistorialClinicoService historialClinicoService;


    @PostMapping(value = "/agregarHistorialClinico", produces = {MediaType.APPLICATION_JSON_VALUE } , consumes = {MediaType.APPLICATION_JSON_VALUE } )
    public ResponseEntity<?> CrearHistorialClinico(@RequestBody HistorialClinicoDTO historialClinicoDTO) throws Exception {
    	historialClinicoService.crearHistorialClinico(historialClinicoDTO);
    		log.info("se ingresa {}", historialClinicoDTO);
    		
        return ResponseEntity.ok("Historial Clinico creado correctamente");
    }}

