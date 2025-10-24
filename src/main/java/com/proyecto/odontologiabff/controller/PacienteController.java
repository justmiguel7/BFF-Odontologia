package com.proyecto.odontologiabff.controller;

import com.proyecto.odontologiabff.dto.PacienteDTO;


import com.proyecto.odontologiabff.requester.PacienteRequesterImp;
import com.proyecto.odontologiabff.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Paciente")
@CrossOrigin(origins = "http://localhost:4200")
public class PacienteController {

	private static final Logger log = LoggerFactory.getLogger(PacienteRequesterImp.class);

	
    @Autowired
    private PacienteService pacienteService;

    @PostMapping(value = "/agregarPaciente", produces = {MediaType.APPLICATION_JSON_VALUE } , consumes = {MediaType.APPLICATION_JSON_VALUE } )
    public ResponseEntity<?> crearPaciente(@RequestBody PacienteDTO pacienteDTO) throws Exception {
        pacienteService.crearPaciente(pacienteDTO);
    		log.info("se ingresa {}", pacienteDTO);
    	
        return ResponseEntity.ok("Paciente creado correctamente");
    }

    @GetMapping("/{id}")
    public PacienteDTO obtenerPacientePorId(@PathVariable int id) {
        return pacienteService.obtenerPacientePorId(id);
    }
    
    

}

