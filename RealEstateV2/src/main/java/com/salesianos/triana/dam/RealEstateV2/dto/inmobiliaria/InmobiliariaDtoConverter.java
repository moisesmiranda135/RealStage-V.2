package com.salesianos.triana.dam.RealEstateV2.dto.inmobiliaria;

import com.salesianos.triana.dam.RealEstateV2.model.Inmobiliaria;
import org.springframework.stereotype.Component;

@Component
public class InmobiliariaDtoConverter {


    public Inmobiliaria createInmobiliariaDtoToInmobiliaria(CreateInmobiliariaDto i) {

        return new Inmobiliaria(
                i.getNombre(),
                i.getEmail(),
                i.getTelefono()
        );
    }

    public  GetInmobiliariaDto inmobiliariaToGetInmobiliariaDto(Inmobiliaria i) {

        int id = 0;

        return GetInmobiliariaDto
                .builder()
                .nombre(i.getNombre())
                .email(i.getEmail())
                .titulo_vivienda(i.getViviendas().get(id).getTitulo())
                .avatar_vivienda((i.getViviendas().get(id).getAvatar()))
                .precio_vivienda(i.getViviendas().get(id).getPrecio())
                .build();

    }

}
