package com.salesianostriana.dam.RealEstateG1.dto.vivienda;

import com.salesianostriana.dam.RealEstateG1.model.Vivienda;
import org.springframework.stereotype.Component;

@Component
public class DetailDtoConverter {

    public Vivienda createViviendaDtoToVivienda(CreateDetailViviendaDto c){
        return new Vivienda(
            c.getId(),
            c.getTitulo(),
            c.getDescripcion(),
            c.getAvatar(),
            c.getLatlng(),
            c.getDireccion(),
            c.getCodigoPostal(),
            c.getPoblacion(),
            c.getProvincia(),
            c.getTipo(),
            c.getPrecio(),
            c.getNumHabitaciones(),
            c.getMetrosCuadrados(),
            c.getNumBanyos(),
            c.isTienePiscina(),
            c.isTieneAscensor(),
            c.isTieneGaraje()
        );
    }

    public GetDetailViviendaDto viviendaToGetViviendaDto(Vivienda v){
        return GetDetailViviendaDto
                .builder()
                .id(v.getId())
                .titulo(v.getTitulo())
                .descripcion(v.getDescripcion())
                .avatar(v.getAvatar())
                .latlng(v.getLatlng())
                .direccion(v.getDireccion())
                .codigoPostal(v.getCodigoPostal())
                .poblacion(v.getPoblacion())
                .provincia(v.getProvincia())
                .tipo(v.getTipo())
                .precio(v.getPrecio())
                .numHabitaciones(v.getNumHabitaciones())
                .metrosCuadrados(v.getMetrosCuadrados())
                .numBanyos(v.getNumBanyos())
                .tieneAscensor(v.isTieneAscensor())
                .tieneGaraje(v.isTieneGaraje())
                .tienePiscina(v.isTienePiscina())
                .nombre_propietario(v.getPropietario().getNombre())
                .avatar_propietario(v.getPropietario().getAvatar())
                .build();
    }
}
