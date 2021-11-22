package com.salesianos.triana.dam.RealEstateV2.users.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUsuarioDto {

    private String nombre;
    private String avatar;
    private String apeliidos;
    private String email;
    private String password;
    private String password2;

}
