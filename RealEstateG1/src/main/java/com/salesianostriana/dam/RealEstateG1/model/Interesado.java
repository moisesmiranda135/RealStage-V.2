package com.salesianostriana.dam.RealEstateG1.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@SuperBuilder
@DiscriminatorValue("I")
@NoArgsConstructor
public class Interesado extends Persona implements Serializable {

    @Builder.Default
    @OneToMany(mappedBy = "vivienda")
    private List<Interesa> listInteresa = new ArrayList<>();
}
