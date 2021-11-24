package com.salesianos.triana.dam.RealEstateV2.controller;

import com.salesianos.triana.dam.RealEstateV2.dto.propietario.GetPropietarioDto;
import com.salesianos.triana.dam.RealEstateV2.dto.propietario.PropietarioDtoConverter;
import com.salesianos.triana.dam.RealEstateV2.users.models.Roles;
import com.salesianos.triana.dam.RealEstateV2.users.models.Usuario;
import com.salesianos.triana.dam.RealEstateV2.users.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/propietario/")
@RequiredArgsConstructor
public class PropietarioController {

    private final UsuarioService usuarioEntityService;

    @Operation(summary = "Obtiene todos los propietarios creados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado los propietarios",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han encontrado los propietarios",
                    content = @Content),
    })
    @GetMapping("")
    public ResponseEntity<List<GetPropietarioDto>> findAll(){
        List<Usuario> data = usuarioEntityService.loadUserByRole(Roles.PROPIETARIO);

        if (data.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            List<GetPropietarioDto> result =
                    data.stream()
                            .map(PropietarioDtoConverter::propietarioToGetPropietarioDto)
                            .collect(Collectors.toList());

            return ResponseEntity.ok().body(result);
        }
    }


}
