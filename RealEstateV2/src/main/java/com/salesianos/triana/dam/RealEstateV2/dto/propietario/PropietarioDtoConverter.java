package com.salesianos.triana.dam.RealEstateV2.dto.propietario;

import com.salesianos.triana.dam.RealEstateV2.users.models.Usuario;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class PropietarioDtoConverter implements Serializable {

    public static GetPropietarioDto propietarioToGetPropietarioDto(Usuario p){

        GetPropietarioDto result = new GetPropietarioDto();
        result.setId(p.getId());
        result.setNombre(p.getNombre());
        result.setApellidos(p.getApellidos());
        result.setEmail(p.getEmail());
        result.setPassword(p.getPassword());
        result.setAvatar(p.getAvatar());
        result.setTelefono(p.getTelefono());
        result.setRol(p.getRol());

        return result;
    }
}
