package com.proyecto.odontologiabff.dto;

import java.time.LocalDateTime;
import java.util.List; // Importa List
import lombok.Data;

@Data
public class HistorialClinicoDTO {

    private int idhistorial;
    private String dnipaciente;
    private String dniodontologo;
    private int idturno;
    private List<Integer> idtratamientos;  // Cambié a List<Integer> para manejar varios tratamientos
    private String motivodeconsulta;
    private LocalDateTime fechadeconsulta;
    private String diagnostico;
    private String observaciones;
    private String alergias;
    private String antecedentesmedicos;

    public HistorialClinicoDTO() {}

    public HistorialClinicoDTO(String dnipaciente, String dniodontologo, int idturno, List<Integer> idtratamientos,
                               String motivodeconsulta, LocalDateTime fechadeconsulta, String diagnostico,
                               String observaciones, String alergias, String antecedentesmedicos) {
        this.dnipaciente = dnipaciente;
        this.dniodontologo = dniodontologo;
        this.idturno = idturno;
        this.idtratamientos = idtratamientos;
        this.motivodeconsulta = motivodeconsulta;
        this.fechadeconsulta = fechadeconsulta;
        this.diagnostico = diagnostico;
        this.observaciones = observaciones;
        this.alergias = alergias;
        this.antecedentesmedicos = antecedentesmedicos;
    }
}
