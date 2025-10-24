package com.proyecto.odontologiabff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.odontologiabff.dto.TurnoDTO;
import com.proyecto.odontologiabff.requester.TurnoRequester;

@Service
public class TurnoServiceImp implements TurnoService {

    @Autowired
    private TurnoRequester turnoRequester;

    @Override
    public void crearTurno(TurnoDTO turnoDTO) throws Exception {
        this.turnoRequester.enviarNuevoTurno(turnoDTO);
    }

    @Override
    public void crearTurnoPaciente(TurnoDTO turnoDTO) throws Exception {
        if (!turnoRequester.existePaciente(turnoDTO.getDnipaciente())) {
            throw new IllegalArgumentException("El paciente con DNI " + turnoDTO.getDnipaciente() + " no existe");
        }

        turnoRequester.enviarNuevoTurno(turnoDTO);
    }

    @Override
    public void crearTurnoOdontologo(TurnoDTO turnoDTO) throws Exception {
        if (!turnoRequester.existePaciente(turnoDTO.getDnipaciente())) {
            throw new IllegalArgumentException("El paciente con DNI " + turnoDTO.getDnipaciente() + " no existe");
        }
        if (!turnoRequester.existeOdontologo(turnoDTO.getDniodontologo())) {
            throw new IllegalArgumentException("El odontólogo con DNI " + turnoDTO.getDniodontologo() + " no existe");
        }

        turnoRequester.enviarNuevoTurno(turnoDTO);
    }

    @Override
    public boolean existePaciente(String dniPaciente) {
        return turnoRequester.existePaciente(dniPaciente);
    }

    @Override
    public boolean existeOdontologo(String dniOdontologo) {
        return turnoRequester.existeOdontologo(dniOdontologo);
    }
}
