package com.salesianostriana.dam.RealEstateG1.dto.inmobiliaria;

import com.salesianostriana.dam.RealEstateG1.dto.vivienda.GetViviendaDto;
import com.salesianostriana.dam.RealEstateG1.model.Inmobiliaria;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

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
                .precio_vivienda(i.getViviendas().get(id).getAvatar())
                .build();

    }

}