package com.proyecto.odontologiabff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.proyecto.odontologiabff.dto.TurnoDTO;
import com.proyecto.odontologiabff.requester.TurnoRequester;

@Service
public class TurnoServiceImp implements TurnoService{
	
	@Autowired
	private TurnoRequester turnoRequester;
	
	@Override
	public void crearTurno(TurnoDTO turnoDTO) throws Exception{
		this.turnoRequester.enviarNuevoTurno(turnoDTO);
	}
	

    @Override
    public void crearTurnoPaciente(TurnoDTO turnoDTO) throws Exception {
        // validar que exista el paciente
        if (!turnoRequester.existePaciente(turnoDTO.getIdpaciente())) {
            throw new IllegalArgumentException("El paciente con ID " + turnoDTO.getIdpaciente() + " no existe");
        }

        turnoRequester.enviarNuevoTurno(turnoDTO);
    }

    @Override
    public void crearTurnoOdontologo(TurnoDTO turnoDTO) throws Exception {
        // validar que existan ambos
        if (!turnoRequester.existePaciente(turnoDTO.getIdpaciente())) {
            throw new IllegalArgumentException("El paciente con ID " + turnoDTO.getIdpaciente() + " no existe");
        }
        if (!turnoRequester.existeOdontologo(turnoDTO.getIdodontologo())) {
            throw new IllegalArgumentException("El odontólogo con ID " + turnoDTO.getIdodontologo() + " no existe");
        }

        turnoRequester.enviarNuevoTurno(turnoDTO);
    }
    
    @Override
    public boolean existePaciente(int idPaciente) {
        return turnoRequester.existePaciente(idPaciente);
    }

    @Override
    public boolean existeOdontologo(int idOdontologo) {
        return turnoRequester.existeOdontologo(idOdontologo);
    }

}
