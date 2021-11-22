package com.salesianos.triana.dam.RealEstateV2.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Inmobiliaria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String telefono;

    @Builder.Default
    @OneToMany(mappedBy ="inmobiliaria")
    private List<Vivienda> viviendas = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy ="inmobiliaria")
    private List<Usuario> usuarios = new ArrayList<>();

}
