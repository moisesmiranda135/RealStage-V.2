package com.salesianostriana.dam.RealEstateG1.dto.propietario;

import com.salesianostriana.dam.RealEstateG1.model.Propietario;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class PropietarioDtoConverter implements Serializable {

    public static GetPropietarioDto propietarioToGetPropietarioDto(Propietario p){

        GetPropietarioDto result = new GetPropietarioDto();
        result.setId(p.getId());
        result.setNombre(p.getNombre());
        result.setApellidos(p.getApellidos());
        result.setEmail(p.getEmail());
        result.setAvatar(p.getAvatar());
        result.setTelefono(p.getTelefono());

        return result;
    }
}
