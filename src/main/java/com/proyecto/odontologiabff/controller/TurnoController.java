package com.proyecto.odontologiabff.controller;

import com.proyecto.odontologiabff.dto.EstadoTurno;

import com.proyecto.odontologiabff.dto.TurnoDTO;
import com.proyecto.odontologiabff.service.TurnoService;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/turno")
@CrossOrigin(origins = "http://localhost:4200")
public class TurnoController {

    private static final Logger log = LoggerFactory.getLogger(TurnoController.class);

    @Autowired
    private TurnoService turnoService;

    @PostMapping(value = "/agregarTurno", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearTurno(@RequestBody TurnoDTO turnoDTO) throws Exception {
        turnoService.crearTurno(turnoDTO);
        log.info("Se ingresa turno: {}", turnoDTO);
        return ResponseEntity.ok("Turno creado correctamente");
    }
    
    
    @GetMapping("/listado")
    public ResponseEntity<List<TurnoDTO>> obtenerTurnosPaciente() {
        // Obtener todos los turnos desde el service
        List<TurnoDTO> todosLosTurnos = turnoService.obtenerListadoTurnos();

        // Filtrar solo los turnos de pacientes (sin odontólogo o pendientes)
        List<TurnoDTO> turnosPaciente = todosLosTurnos.stream()
                .filter(turno -> turno.getDniodontologo() == null 
                                || turno.getEstado() == EstadoTurno.PENDIENTE)
                .toList();

        return ResponseEntity.ok(turnosPaciente);
    }
    
    
    
 // Endpoint para que el front confirme un turno por ID
    @PutMapping("/confirmar/{idturno}")
    public ResponseEntity<?> confirmarTurno(
            @PathVariable int idturno,
            @RequestBody Map<String, String> payload) {

        String dniOdontologo = payload.get("dniOdontologo"); // recibimos DNI del odontólogo desde el front

        try {
            TurnoDTO turnoConfirmado = turnoService.confirmarTurnoPorId(idturno, dniOdontologo);
            return ResponseEntity.ok(turnoConfirmado);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    

    @PostMapping(value = "/agregarTurnoPaciente", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearTurnoPaciente(@RequestBody TurnoDTO turnoDTO) {
        try {
            log.info("Creando turno (PACIENTE): {}", turnoDTO);
            turnoDTO.setDniodontologo(null);
            turnoDTO.setEstado(EstadoTurno.PENDIENTE);

            // Llama al servicio que se encarga de comunicarse con el microservicio de turnos
            turnoService.crearTurnoPaciente(turnoDTO);

            return ResponseEntity.ok(Map.of("message", "Turno creado correctamente (pendiente de confirmación)"));

        } catch (IllegalArgumentException e) {
            // ⚠️ Este bloque captura el error que viene del microservicio turno (por ejemplo, turno duplicado)
            log.warn("Error de validación al crear turno: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));

        } catch (Exception e) {
            log.error("Error al crear turno para paciente", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("message", "Error interno del servidor"));
        }
    }



    // Endpoint para ODONTÓLOGO
    @PostMapping(value = "/agregarTurnoOdontologo", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearTurnoOdontologo(@RequestBody TurnoDTO turnoDTO) {
        try {
            log.info("Creando turno (ODONTÓLOGO): {}", turnoDTO);

            // Validar existencia de paciente
            if (!turnoService.existePaciente(turnoDTO.getDnipaciente())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El paciente con DNI " + turnoDTO.getDnipaciente() + " no existe");
            }

            // Validar existencia de odontólogo
            if (!turnoService.existeOdontologo(turnoDTO.getDniodontologo())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El odontólogo con DNI " + turnoDTO.getDniodontologo() + " no existe");
            }

            // Estado CONFIRMADO
            turnoDTO.setEstado(EstadoTurno.CONFIRMADO);

            turnoService.crearTurnoOdontologo(turnoDTO);
            return ResponseEntity.ok(Map.of("message", "Turno creado correctamente (confirmado por odontólogo)"));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("Error al crear turno para odontólogo", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
        
        
    }
    
    @PutMapping("/cancelar/{idturno}")
    public ResponseEntity<?> cancelarTurno(@PathVariable int idturno) {
        try {
            TurnoDTO turnoCancelado = turnoService.cancelarTurno(idturno);
            return ResponseEntity.ok(turnoCancelado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
  

    
}