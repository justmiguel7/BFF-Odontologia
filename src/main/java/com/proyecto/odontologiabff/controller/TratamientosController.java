package com.proyecto.odontologiabff.controller;

import com.proyecto.odontologiabff.dto.TratamientosDTO;
import com.proyecto.odontologiabff.requester.TratamientosRequesterImp;
import com.proyecto.odontologiabff.service.TratamientosService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tratamientos")
@CrossOrigin(origins = "http://localhost:4200")
public class TratamientosController {

    private static final Logger log = LoggerFactory.getLogger(TratamientosRequesterImp.class);

    @Autowired
    private TratamientosService tratamientosService;

    @PostMapping(value = "/agregarTratamiento", 
                 produces = MediaType.APPLICATION_JSON_VALUE, 
                 consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> CrearTratamiento(@RequestBody TratamientosDTO tratamientosDTO) {
        try {
            tratamientosService.crearTratamiento(tratamientosDTO);
            log.info("Se ingresa tratamiento: {}", tratamientosDTO);
            return ResponseEntity.ok("Tratamiento creado correctamente");
        } catch (Exception e) {
            log.error("Error al crear tratamiento: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarTratamientoPorId(@PathVariable int id) {
        try {
            TratamientosDTO tratamiento = tratamientosService.buscarPorId(id);
            return ResponseEntity.ok(tratamiento);
        } catch (Exception e) {
            log.error("Tratamiento no encontrado: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("El tratamiento con ID " + id + " no existe.");
        }
    }

}
