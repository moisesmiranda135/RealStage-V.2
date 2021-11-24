package com.salesianos.triana.dam.RealEstateV2.dto.propietario;

import com.salesianos.triana.dam.RealEstateV2.model.Inmobiliaria;
import com.salesianos.triana.dam.RealEstateV2.model.Vivienda;
import com.salesianos.triana.dam.RealEstateV2.users.models.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

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
    private String password;
    private Roles rol;

    @Lob
    private String avatar;

}
