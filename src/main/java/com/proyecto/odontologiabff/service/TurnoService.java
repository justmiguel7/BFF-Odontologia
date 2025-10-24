package com.proyecto.odontologiabff.service;


import org.springframework.stereotype.Service;

import com.proyecto.odontologiabff.dto.TurnoDTO;

@Service
public interface TurnoService {

	void crearTurno(TurnoDTO turnoDTO) throws Exception;
    void crearTurnoPaciente(TurnoDTO turnoDTO) throws Exception;
    void crearTurnoOdontologo(TurnoDTO turnoDTO) throws Exception;
    boolean existePaciente(String dnipaciente);
    boolean existeOdontologo(String dniodontologo);
}
