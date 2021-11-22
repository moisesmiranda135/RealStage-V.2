package com.salesianos.triana.dam.RealEstateV2.security.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class JwtUsuarioResponse {

    private String email;
    private String nombre;
    private String apellidos;
    private String avatar;
    private String role;
    private String token;

}
