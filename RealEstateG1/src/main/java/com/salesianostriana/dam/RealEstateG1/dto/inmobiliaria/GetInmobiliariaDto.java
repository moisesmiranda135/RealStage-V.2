package com.salesianostriana.dam.RealEstateG1.dto.inmobiliaria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetInmobiliariaDto {

    private String nombre;
    private String email;
    private String titulo_vivienda;
    private String avatar_vivienda;
    private String precio_vivienda;



}
