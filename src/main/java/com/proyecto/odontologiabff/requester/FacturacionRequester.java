package com.proyecto.odontologiabff.requester;
import com.proyecto.odontologiabff.dto.FacturacionDTO;

public interface FacturacionRequester {
    void enviarNuevoFactura(FacturacionDTO facturadto) throws Exception;

}
