package com.proyecto.odontologiabff.requester;
import com.proyecto.odontologiabff.dto.PacienteDTO;

public interface PacienteRequester {
    void enviarNuevoPaciente(PacienteDTO dto) throws Exception;

}
