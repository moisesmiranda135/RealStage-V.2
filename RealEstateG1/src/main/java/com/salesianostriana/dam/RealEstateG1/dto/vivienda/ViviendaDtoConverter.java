package com.salesianostriana.dam.RealEstateG1.dto.vivienda;

import com.salesianostriana.dam.RealEstateG1.model.Vivienda;
import org.springframework.stereotype.Component;

@Component
public class ViviendaDtoConverter {

    public Vivienda createViviendaDtoToInteresado(CreateViviendaDto v){

        return new Vivienda(
                v.getTitulo(),
                v.getDescripcion(),
                v.getAvatar(),
                v.getPoblacion(),
                v.getProvincia(),
                v.getTipo()
        );
    }

    public static GetViviendaDto viviendaToGetViviendaDto(Vivienda v){

        GetViviendaDto result = new GetViviendaDto();
        result.setTitulo(v.getTitulo());
        result.setDescripcion(v.getDescripcion());
        result.setAvatar(v.getAvatar());

        return result;
    }

}
