package com.salesianostriana.dam.RealEstateG1.dto.vivienda;

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
    private String tipo;
    private Double precio;
    private int numHabitaciones;
    private double metrosCuadrados;
    private int numBanyos;
    private boolean tienePiscina;
    private boolean tieneAscensor;
    private boolean tieneGaraje;
    private Long propietario_id;
}
