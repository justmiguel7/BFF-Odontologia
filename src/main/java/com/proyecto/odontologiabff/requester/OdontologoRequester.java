package com.proyecto.odontologiabff.requester;
import com.proyecto.odontologiabff.dto.OdontologoDTO;

public interface OdontologoRequester {
    void enviarNuevoOdontologo(OdontologoDTO odontologodto) throws Exception;

}
