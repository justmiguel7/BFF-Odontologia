package com.proyecto.odontologiabff.requester;
import com.proyecto.odontologiabff.dto.HistorialClinicoDTO;

public interface HistorialClinicoRequester {
	
    void enviarNuevoHistorialClinico(HistorialClinicoDTO historialClinicodto) throws Exception;
    
    HistorialClinicoDTO obtenerHistorialPorDniPaciente(String dniPaciente);
    
    HistorialClinicoDTO obtenerHistorialPorDniOdontologo(String dniOdontologo);
    
}
