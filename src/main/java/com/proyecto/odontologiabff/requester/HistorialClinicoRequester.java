package com.proyecto.odontologiabff.requester;

import com.proyecto.odontologiabff.dto.HistorialClinicoDTO;

public interface HistorialClinicoRequester {
    HistorialClinicoDTO enviarNuevoHistorialClinico(HistorialClinicoDTO historialClinicoDTO) throws Exception;
}
