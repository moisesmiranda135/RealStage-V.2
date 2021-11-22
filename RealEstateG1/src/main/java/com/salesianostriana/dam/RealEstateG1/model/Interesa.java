package com.salesianostriana.dam.RealEstateG1.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Interesa implements Serializable {

    @Builder.Default
    @EmbeddedId
    private InteresaPK id = new InteresaPK();

    @ManyToOne
    @MapsId("vivienda_id")
    @JoinColumn(name = "vivienda_id")
    private Vivienda vivienda;

    @ManyToOne
    @MapsId("interesado_id")
    @JoinColumn(name = "interesado_id")
    private Interesado interesado;

    @CreatedDate
    private LocalDateTime createdDate;

    @Lob
    private String mensaje;

    //HELPERS
    //**************************************************

    public void addToVivienda(Vivienda v){
        this.vivienda = v;
        v.getListInteresa().add(this);
    }

    public void deleteFromVivienda(Vivienda v){
        v.getListInteresa().remove(this);
        this.vivienda = null;
    }

    public void addToInteresado(Interesado i){
        this.interesado = i;
        i.getListInteresa().add(this);
    }

    public void deleteFromInteresado(Interesado i){
        i.getListInteresa().remove(this);
        this.interesado = null;
    }

    public void addViviendaToInteresado(Vivienda v,Interesado i){
        addToVivienda(v);
        addToInteresado(i);
    }

    public void deleteViviendaFromInteresado(Vivienda v,Interesado i){
        deleteFromVivienda(v);
        deleteFromInteresado(i);
    }

    //******************************************************
}
