package com.salesianostriana.dam.RealEstateG1.dto.vivienda;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetViviendaDto {

    private String titulo;
    private String descripcion;
    private String avatar;
}