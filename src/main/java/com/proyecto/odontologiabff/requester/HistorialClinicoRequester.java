package com.proyecto.odontologiabff.requester;

import com.proyecto.odontologiabff.dto.HistorialClinicoDTO;

public interface HistorialClinicoRequester {
    void enviarNuevoHistorialClinico(HistorialClinicoDTO historialClinicoDTO) throws Exception;
}
