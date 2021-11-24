package com.salesianos.triana.dam.RealEstateV2.dto.vivienda;

import com.salesianos.triana.dam.RealEstateV2.model.TipoVivienda;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPropertiesViviendaDto {

    private Long id;
    private String titulo;
    private String avatar;
    private String codigoPostal;
    private String provincia;
    private TipoVivienda tipo;
    private Double precio;
}
