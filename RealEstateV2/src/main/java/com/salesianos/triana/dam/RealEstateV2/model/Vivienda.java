package com.salesianos.triana.dam.RealEstateV2.model;

import com.salesianos.triana.dam.RealEstateV2.users.models.Usuario;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vivienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private String avatar;
    private String latlng;
    private String direccion;
    private String codigoPostal;
    private String poblacion;
    private String provincia;

    @Enumerated(EnumType.STRING)
    private tipoVivienda tipo;

    private Double precio;
    private int numHabitaciones;
    private double metrosCuadrados;
    private int numBanyos;
    private boolean tienePiscina;
    private boolean tieneAscensor;
    private boolean tieneGaraje;

    @ManyToOne
    private Inmobiliaria inmobiliaria;

    @ManyToOne
    private Usuario usuario;
}
