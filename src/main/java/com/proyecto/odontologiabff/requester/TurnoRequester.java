package com.proyecto.odontologiabff.requester;
import java.util.List;

import com.proyecto.odontologiabff.dto.TurnoDTO;


public interface TurnoRequester {
    void enviarNuevoTurno(TurnoDTO dto) throws Exception;
    boolean existePaciente(String dnipaciente);
    boolean existeOdontologo(String dniodontologo);
    String obtenerEmailPaciente(String dnipaciente);
    List<TurnoDTO> obtenerTurnos();
}