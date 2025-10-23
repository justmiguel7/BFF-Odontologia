package com.proyecto.odontologiabff.requester;
import com.proyecto.odontologiabff.dto.TurnoDTO;

public interface TurnoRequester {
    void enviarNuevoTurno(TurnoDTO dto) throws Exception;
    boolean existePaciente(int idPaciente);
    boolean existeOdontologo(int idOdontologo);
}
