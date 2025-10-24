package com.proyecto.odontologiabff.requester;
import com.proyecto.odontologiabff.dto.TurnoDTO;

public interface TurnoRequester {
    void enviarNuevoTurno(TurnoDTO dto) throws Exception;
    boolean existePaciente(String dnipaciente);
    boolean existeOdontologo(String dniodontologo);
}
