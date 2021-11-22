package com.salesianostriana.dam.RealEstateG1.dto.interesado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetInteresadoDto {



    private String nombre;

    private String apellidos;

    private String email;

    private String telefono;


}
