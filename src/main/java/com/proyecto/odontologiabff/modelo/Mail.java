package com.proyecto.odontologiabff.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Mail {
    private String to;   
    private String subjet;   
    private String text;     
}