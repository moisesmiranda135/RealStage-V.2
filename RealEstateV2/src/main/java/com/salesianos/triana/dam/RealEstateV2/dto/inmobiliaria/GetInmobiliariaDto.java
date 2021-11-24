package com.salesianos.triana.dam.RealEstateV2.dto.inmobiliaria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetInmobiliariaDto {

    private String nombre;
    private String email;
    private String titulo_vivienda;
    private String avatar_vivienda;
    private double precio_vivienda;



}
