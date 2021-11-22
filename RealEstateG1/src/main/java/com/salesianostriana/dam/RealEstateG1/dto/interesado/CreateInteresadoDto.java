package com.salesianostriana.dam.RealEstateG1.dto.interesado;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInteresadoDto {



    private String nombre;

    private String email;

    private String telefono;
}
