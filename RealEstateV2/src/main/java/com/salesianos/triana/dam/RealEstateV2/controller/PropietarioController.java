package com.salesianos.triana.dam.RealEstateV2.controller;

import com.salesianos.triana.dam.RealEstateV2.dto.propietario.GetPropietarioDto;
import com.salesianos.triana.dam.RealEstateV2.dto.propietario.GetPropietarioViviendaDto;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/propietario")
@RequiredArgsConstructor
public class PropietarioController {

    private final UsuarioService usuarioEntityService;
    private final PropietarioDtoConverter propietarioDtoConverter;

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
    @GetMapping("/")
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


    @Operation(summary = "Obtiene los detalles del propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado los propietarios",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))}),
            @ApiResponse(responseCode = "403",
                    description = "No tienes permisos para esta petición",
                    content = @Content),
    })
    @GetMapping("/{id}/")
    public ResponseEntity<List<GetPropietarioViviendaDto>> findOnePropietario(@PathVariable Long id, HttpServletRequest request, @AuthenticationPrincipal Usuario user) {
        Optional<Usuario> propietario = usuarioEntityService.loadUserById(id);
        if (propietario.isEmpty()){
            return ResponseEntity.notFound().build();
        }else if (user.getRol().equals(Roles.ADMIN) || propietario.get().getRol().equals(user.getRol()) && propietario.get().getId().equals(user.getId())) {
            List<GetPropietarioViviendaDto> propietarioDto = propietario.stream()
                    .map(propietarioDtoConverter::propietarioToGetPropietarioViviendaDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok().body(propietarioDto);
        }else {
            return ResponseEntity.status(403).build();
        }
    }


}
