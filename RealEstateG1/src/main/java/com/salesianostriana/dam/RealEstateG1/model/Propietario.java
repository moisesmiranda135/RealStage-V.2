package com.salesianostriana.dam.RealEstateG1.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("P")
public class Propietario extends Persona implements Serializable {

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "propietario")
    private List<Vivienda> vivienda = new ArrayList<>();

}
