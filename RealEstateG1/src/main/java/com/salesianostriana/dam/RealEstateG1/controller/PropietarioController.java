package com.salesianostriana.dam.RealEstateG1.controller;

import com.salesianostriana.dam.RealEstateG1.model.Inmobiliaria;
import com.salesianostriana.dam.RealEstateG1.dto.propietario.GetPropietarioDto;
import com.salesianostriana.dam.RealEstateG1.dto.propietario.PropietarioDtoConverter;
import com.salesianostriana.dam.RealEstateG1.model.Propietario;
import com.salesianostriana.dam.RealEstateG1.pagination.PaginationUtilsLinks;
import com.salesianostriana.dam.RealEstateG1.service.PropietarioService;
import com.salesianostriana.dam.RealEstateG1.service.ViviendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/propietario")
@RequiredArgsConstructor
@Tag(name = "Propietario",description = "Controlador para Propietarios")
public class PropietarioController {

    private final PropietarioService propietarioService;
    private final ViviendaService viviendaService;

    @Operation(summary = "Obtiene una lista de todos los propietarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la lista de los propietarios",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Propietario.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado ningún propietario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Propietario.class))})})
    @GetMapping("/")
    public ResponseEntity<List<GetPropietarioDto>> findAll() {

        List<Propietario> data = propietarioService.findAll();


        if (data.isEmpty()) {
            return ResponseEntity
                    .noContent()
                    .build();
        }else{
            List<GetPropietarioDto> result =
                    data.stream()
                            .map(PropietarioDtoConverter::propietarioToGetPropietarioDto)
                            .collect(Collectors.toList());

            return ResponseEntity.ok().body(result);
        }
    }

    @Operation(summary = "Borra un propietario previamente creada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "No content",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))}),


            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el propietario indicado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Propietario propietario = propietarioService.findById(id).get();
        viviendaService
                .findAll()
                .stream()
                .map(vivienda -> {
                    vivienda.deleteFromPropietario(propietario);
                    return vivienda;
                })
                .forEach(viviendaService::save);
        propietarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
