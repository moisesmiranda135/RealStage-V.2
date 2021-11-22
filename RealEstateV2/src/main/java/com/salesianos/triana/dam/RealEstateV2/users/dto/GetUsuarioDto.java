package com.salesianos.triana.dam.RealEstateV2.users.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUsuarioDto {

    private String avatar;
    private String nombre;
    private String apellidos;
    private String email;
    private String role;

}
