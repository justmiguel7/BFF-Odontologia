package com.proyecto.odontologiabff.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.odontologiabff.dto.DienteDTO;
import com.proyecto.odontologiabff.requester.DienteRequester;

@Service
public class DienteServiceImp implements DienteService {

    @Autowired
    private DienteRequester requester;

    @Override
    public List<DienteDTO> crearDientesBase(String dniPaciente) {
        return requester.crearDientesBase(dniPaciente);
    }

    @Override
    public List<DienteDTO> listarDientesPorPaciente(String dniPaciente) {
        return requester.listarDientesPorPaciente(dniPaciente);
    }
}
