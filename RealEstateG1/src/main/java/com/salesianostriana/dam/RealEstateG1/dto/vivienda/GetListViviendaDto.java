package com.salesianostriana.dam.RealEstateG1.dto.vivienda;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListViviendaDto {

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
    private String nombre_propietario;
    private String avatar_propietario;
}
