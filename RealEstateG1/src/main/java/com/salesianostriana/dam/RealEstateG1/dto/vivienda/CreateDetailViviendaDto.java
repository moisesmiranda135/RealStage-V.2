package com.salesianostriana.dam.RealEstateG1.dto.vivienda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDetailViviendaDto {

    private Long id;

    private String titulo;
    private String descripcion;
    private String avatar;
    private String latlng;
    private String direccion;
    private String codigoPostal;
    private String poblacion;
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
