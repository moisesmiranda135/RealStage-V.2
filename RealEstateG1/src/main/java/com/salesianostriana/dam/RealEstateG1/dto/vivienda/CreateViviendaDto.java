package com.salesianostriana.dam.RealEstateG1.dto.vivienda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateViviendaDto {

    private String titulo;
    private String descripcion;
    private String avatar;
    private String poblacion;
    private String provincia;
    private String tipo;
}
