package com.salesianos.triana.dam.RealEstateV2.controller;

import com.salesianos.triana.dam.RealEstateV2.dto.inmobiliaria.GetInmobiliariaDto;
import com.salesianos.triana.dam.RealEstateV2.dto.inmobiliaria.InmobiliariaDtoConverter;
import com.salesianos.triana.dam.RealEstateV2.model.Inmobiliaria;
import com.salesianos.triana.dam.RealEstateV2.model.Vivienda;
import com.salesianos.triana.dam.RealEstateV2.pagination.PaginationUtilsLinks;
import com.salesianos.triana.dam.RealEstateV2.services.InmobiliariaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inmobiliaria")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Inmobiliaria",description = "Controlador para Inmobiliarias")
public class InmobiliariaController {

    private final InmobiliariaService inmobiliariaService;
    private final PaginationUtilsLinks paginationUtilsLinks;
    private final InmobiliariaDtoConverter inmobiliariaDtoConverter;

    @Operation(summary = "Obtiene una lista de todas las Inmobiliarias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la lista de Inmobiliarias",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado ninguna Inmobiliaria",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))})})
    @GetMapping("/")
    public ResponseEntity<?> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable,
                                     HttpServletRequest request) {

        Page<Inmobiliaria> result = inmobiliariaService.findAll(pageable);

        if (inmobiliariaService.findAll().isEmpty()) {
            return ResponseEntity
                    .noContent()
                    .build();
        } else {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder
                    .fromHttpUrl(request.getRequestURL().toString());
            return ResponseEntity
                    .ok()
                    .header("link", paginationUtilsLinks.createLinkHeader(result, uriBuilder))
                    .body(result.stream()
                            .map(inmobiliariaDtoConverter::inmobiliariaToGetInmobiliariaDto)
                            .collect(Collectors.toList()));
        }

    }


}