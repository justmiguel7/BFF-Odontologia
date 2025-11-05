package com.proyecto.odontologiabff.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.odontologiabff.dto.TurnoDTO;

@Service
public interface TurnoService {

    void crearTurno(TurnoDTO turnoDTO) throws Exception;
    void crearTurnoPaciente(TurnoDTO turnoDTO) throws Exception;
    void crearTurnoOdontologo(TurnoDTO turnoDTO) throws Exception;
    boolean existePaciente(String dnipaciente);
    boolean existeOdontologo(String dniodontologo);

    List<TurnoDTO> obtenerListadoTurnos();
	TurnoDTO confirmarTurnoPorId(int idTurno, String dniOdontologo) throws Exception;

	TurnoDTO cancelarTurno(int idturno) throws Exception;


}