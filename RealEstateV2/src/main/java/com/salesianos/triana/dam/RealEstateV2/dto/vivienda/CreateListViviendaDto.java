package com.salesianos.triana.dam.RealEstateV2.dto.vivienda;

import com.salesianos.triana.dam.RealEstateV2.model.TipoVivienda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateListViviendaDto {

    private Long id;
    private String titulo;
    private String avatar;
    private String codigoPostal;
    private String provincia;
    private TipoVivienda tipo;
    private Double precio;
    private int numHabitaciones;
    private double metrosCuadrados;
    private int numBanyos;
    private boolean tienePiscina;
    private boolean tieneAscensor;
    private boolean tieneGaraje;
    private Long propietario_id;
}
