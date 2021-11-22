package com.salesianostriana.dam.RealEstateG1.dto.inmobiliaria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInmobiliariaDto {

    private String nombre;
    private String email;
    private String telefono;
    private Long vivienda_id;
}
