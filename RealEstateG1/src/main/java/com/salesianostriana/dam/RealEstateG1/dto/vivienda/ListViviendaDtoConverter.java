package com.salesianostriana.dam.RealEstateG1.dto.vivienda;

import com.salesianostriana.dam.RealEstateG1.model.Vivienda;
import org.springframework.stereotype.Component;

@Component
public class ListViviendaDtoConverter {

    public Vivienda createViviendaDtoToVivienda(CreateListViviendaDto c){
        return new Vivienda(
                c.getId(),
                c.getTitulo(),
                c.getAvatar(),
                c.getCodigoPostal(),
                c.getProvincia(),
                c.getTipo(),
                c.getPrecio(),
                c.getNumHabitaciones(),
                c.getMetrosCuadrados(),
                c.getNumBanyos(),
                c.isTieneAscensor(),
                c.isTieneGaraje(),
                c.isTienePiscina()
        );
    }

    public GetListViviendaDto viviendaToGetViviendaDto(Vivienda v){
        return GetListViviendaDto
                .builder()
                .id(v.getId())
                .titulo(v.getTitulo())
                .avatar(v.getAvatar())
                .codigoPostal(v.getCodigoPostal())
                .provincia(v.getProvincia())
                .tipo(v.getTipo())
                .precio(v.getPrecio())
                .numHabitaciones(v.getNumHabitaciones())
                .metrosCuadrados(v.getMetrosCuadrados())
                .numBanyos(v.getNumBanyos())
                .tieneAscensor(v.isTieneAscensor())
                .tieneGaraje(v.isTieneGaraje())
                .tienePiscina(v.isTienePiscina())
                .nombre_propietario(v.getPropietario() == null ? "Sin propietario" : v.getPropietario().getNombre())
                .avatar_propietario(v.getPropietario() == null ? "Sin propietario" : v.getPropietario().getAvatar())
                .build();
    }
}
