package com.proyecto.odontologiabff.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.proyecto.odontologiabff.dto.HistorialClinicoDTO;
import com.proyecto.odontologiabff.dto.HistorialConTratamientoDTO;
import com.proyecto.odontologiabff.requester.HistorialClinicoRequesterImp;
import com.proyecto.odontologiabff.service.HistorialClinicoService;

@RestController
@RequestMapping("/historialclinico")
@CrossOrigin(origins = "http://localhost:4200")
public class HistorialClinicoController {

    private static final Logger log = LoggerFactory.getLogger(HistorialClinicoRequesterImp.class);

    @Autowired
    private HistorialClinicoService historialClinicoService;

    /**
     * Crear un nuevo historial clínico
     */
    @PostMapping(
        value = "/agregar",
        
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> crearHistorialClinico(@RequestBody HistorialClinicoDTO historialClinicoDTO) {
        try {
            historialClinicoService.crearHistorialClinico(historialClinicoDTO);
            log.info("✅ Se registró historial clínico: {}", historialClinicoDTO);
            return ResponseEntity.ok("Historial clínico creado correctamente.");
        } catch (IllegalArgumentException e) {
            // Error por tratamiento inexistente
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            log.error("❌ Error al crear historial clínico", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el historial clínico: " + e.getMessage());
        }
    }

    /**
     * Obtener historial clínico (con tratamiento) por DNI del paciente
     */
    @GetMapping("/{dnipaciente}")
    public ResponseEntity<?> obtenerHistorialPorDni(@PathVariable String dnipaciente) {
        try {
            HistorialConTratamientoDTO historial =
                    historialClinicoService.obtenerHistorialConTratamiento(dnipaciente);

            if (historial != null) {
                return ResponseEntity.ok(historial);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró historial clínico para el paciente con DNI " + dnipaciente);
            }

        } catch (Exception e) {
            log.error("❌ Error al obtener historial clínico del paciente {}", dnipaciente, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el historial clínico: " + e.getMessage());
        }
    }
}
