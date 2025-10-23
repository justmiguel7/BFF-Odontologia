package com.proyecto.odontologiabff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.proyecto.odontologiabff.dto.PacienteDTO;
import com.proyecto.odontologiabff.requester.PacienteRequester;

@Service
public class PacienteServiceImp implements PacienteService{
	
	@Autowired
	private PacienteRequester pacienteRequester;
    private PacienteService pacienteService;

	
	@Override
	public void crearPaciente(PacienteDTO pacienteDTO) throws Exception{
		this.pacienteRequester.enviarNuevoPaciente(pacienteDTO);
	}

    @GetMapping("/{id}")
    public PacienteDTO obtenerPacientePorId(@PathVariable int id) {
        return pacienteService.obtenerPacientePorId(id);
    }
}
