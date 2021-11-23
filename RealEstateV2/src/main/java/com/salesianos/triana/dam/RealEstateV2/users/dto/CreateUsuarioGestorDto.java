package com.salesianos.triana.dam.RealEstateV2.users.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUsuarioGestorDto {

    private String nombre;
    private String avatar;
    private String apellidos;
    private String direccion;
    private String email;
    private String password;
    private String password2;
    private String telefono;
    private Long inmobiliaria;
}
