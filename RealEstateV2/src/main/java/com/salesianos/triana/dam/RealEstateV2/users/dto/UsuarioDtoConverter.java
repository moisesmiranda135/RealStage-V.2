package com.salesianos.triana.dam.RealEstateV2.users.dto;


import com.salesianos.triana.dam.RealEstateV2.users.models.Usuario;
import org.springframework.stereotype.Component;


@Component
public class UsuarioDtoConverter {

    public GetUsuarioDto convertUsuarioEntityToGetUsuarioDto(Usuario u) {
        return GetUsuarioDto.builder()
                .avatar(u.getAvatar())
                .nombre(u.getNombre())
                .apellidos(u.getApellidos())
                .email(u.getEmail())
                .role(u.getRol().name())
                .build();

    }

}
