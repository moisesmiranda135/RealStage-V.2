package com.salesianos.triana.dam.RealEstateV2.dto.propietario;

import com.salesianos.triana.dam.RealEstateV2.dto.vivienda.GetListViviendaDto;
import com.salesianos.triana.dam.RealEstateV2.dto.vivienda.GetPropertiesViviendaDto;
import com.salesianos.triana.dam.RealEstateV2.users.models.Usuario;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.stream.Collectors;

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

    public GetPropietarioViviendaDto propietarioToGetPropietarioViviendaDto(Usuario p){

        return GetPropietarioViviendaDto.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .apellidos(p.getApellidos())
                .email(p.getEmail())
                .telefono(p.getTelefono())
                .avatar(p.getAvatar())
                .listviviendadto(p.getListaviviendas().stream().map(v -> new GetPropertiesViviendaDto(
                        v.getId(),
                        v.getTitulo(),
                        v.getAvatar(),
                        v.getCodigoPostal(),
                        v.getProvincia(),
                        v.getTipo(),
                        v.getPrecio()
                )).toList())
                .build();

    }
}
