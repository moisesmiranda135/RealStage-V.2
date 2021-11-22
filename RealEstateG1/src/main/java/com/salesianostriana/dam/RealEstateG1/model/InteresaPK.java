package com.salesianostriana.dam.RealEstateG1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class InteresaPK implements Serializable {

    private Long vivenda_id;
    private Long interesado_id;
}
