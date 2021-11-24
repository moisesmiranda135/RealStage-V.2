package com.salesianos.triana.dam.RealEstateV2.dto.vivienda;

import com.salesianos.triana.dam.RealEstateV2.model.TipoVivienda;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetDetailViviendaDto {

    private Long id;
    private String titulo;
    private String descripcion;
    private String avatar;
    private String latlng;
    private String direccion;
    private String codigoPostal;
    private String poblacion;
    private String provincia;
    private TipoVivienda tipo;
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
