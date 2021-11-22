package com.salesianostriana.dam.RealEstateG1.dto.propietario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPropietarioDto {

    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;

    @Lob
    private String avatar;
}
