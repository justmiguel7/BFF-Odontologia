package com.proyecto.odontologiabff.controller;

import com.proyecto.odontologiabff.dto.FacturacionDTO;


import com.proyecto.odontologiabff.requester.FacturacionRequesterImp;
import com.proyecto.odontologiabff.service.FacturacionService;
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
@RequestMapping("/Facturacion")
@CrossOrigin(origins = "http://localhost:4200")
public class FacturacionController {

	private static final Logger log = LoggerFactory.getLogger(FacturacionRequesterImp.class);

	
    @Autowired
    private FacturacionService facturacionService;

    @PostMapping(value = "/agregarFactura", produces = {MediaType.APPLICATION_JSON_VALUE } , consumes = {MediaType.APPLICATION_JSON_VALUE } )
    public ResponseEntity<?> crearFactura(@RequestBody FacturacionDTO facturacionDTO) throws Exception {
    	facturacionService.crearFactura(facturacionDTO);
    		log.info("se ingresa {}", facturacionDTO);
    	
        return ResponseEntity.ok("Factura creado correctamente");
    }}

