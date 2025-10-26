package com.proyecto.odontologiabff.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class HistorialClinicoDTO {

    private int idhistorial;
    private String dnipaciente;
    private String dniodontologo;
    private int idturno;
    private int idtratamiento;
    private String motivodeconsulta;
    private LocalDateTime fechadeconsulta;
    private String diagnostico;
    private String observaciones;
    private String alergias;
    private String antecedentesmedicos;

    public HistorialClinicoDTO() {}

    public HistorialClinicoDTO(String dnipaciente, String dniodontologo, int idturno, int idtratamiento,
                               String motivodeconsulta, LocalDateTime fechadeconsulta, String diagnostico,
                               String observaciones, String alergias, String antecedentesmedicos) {
        this.dnipaciente = dnipaciente;
        this.dniodontologo = dniodontologo;
        this.idturno = idturno;
        this.idtratamiento = idtratamiento;
        this.motivodeconsulta = motivodeconsulta;
        this.fechadeconsulta = fechadeconsulta;
        this.diagnostico = diagnostico;
        this.observaciones = observaciones;
        this.alergias = alergias;
        this.antecedentesmedicos = antecedentesmedicos;
    }
}
