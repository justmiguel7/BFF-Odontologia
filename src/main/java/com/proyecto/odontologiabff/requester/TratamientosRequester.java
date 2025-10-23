package com.proyecto.odontologiabff.requester;

import com.proyecto.odontologiabff.dto.PacienteDTO;
import com.proyecto.odontologiabff.dto.TratamientosDTO;

public interface TratamientosRequester {

	 void enviarNuevoTratamiento(TratamientosDTO tratamientosDTO) throws Exception;

}
