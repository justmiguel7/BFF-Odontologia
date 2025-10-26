package com.proyecto.odontologiabff.service;

import com.proyecto.odontologiabff.dto.TratamientosDTO;

public interface TratamientosService {

	TratamientosDTO buscarPorId(int id) throws Exception;

	void crearTratamiento(TratamientosDTO tratamientosDTO) throws Exception;
}
