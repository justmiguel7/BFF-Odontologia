package com.proyecto.odontologiabff.dto;

import lombok.Data;
import java.util.List;

@Data
public class DienteDTO {
    private int id;
    private String numero;
    private String nombre;
    private String cuadrante;
    private String dnipaciente;
    private List<DetalleDentalDTO> detalles; // opcional si querés cargar los detalles
}
