package com.salesianos.triana.dam.RealEstateV2.controller;

import com.salesianos.triana.dam.RealEstateV2.dto.vivienda.DetailDtoConverter;
import com.salesianos.triana.dam.RealEstateV2.dto.vivienda.GetDetailViviendaDto;
import com.salesianos.triana.dam.RealEstateV2.dto.vivienda.ListViviendaDtoConverter;
import com.salesianos.triana.dam.RealEstateV2.model.Vivienda;
import com.salesianos.triana.dam.RealEstateV2.pagination.PaginationUtilsLinks;
import com.salesianos.triana.dam.RealEstateV2.services.ViviendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vivienda/")
@RequiredArgsConstructor
public class ViviendaController {

    private final ViviendaService viviendaService;
    private final DetailDtoConverter detailDtoConverter;
    private final PaginationUtilsLinks paginationUtilsLinks;
    private final ListViviendaDtoConverter dtoConverter;

    @Operation(summary = "Optiene los detalles de la vivienda elegida por el usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la vivienda correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))})})
    @GetMapping("/{id}")
    public ResponseEntity<GetDetailViviendaDto> findOne(@PathVariable Long id) {
        return ResponseEntity
                .ok(detailDtoConverter.viviendaToGetViviendaDto(viviendaService.findById(id).get()));
    }

    @Operation(summary = "Obtiene una lista de todas las viviendas y las filtra según varios criterios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la lista de viviendas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado la lista de viviendas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))})
    })
    @GetMapping("/")
    public ResponseEntity<?> findAllWithCriteria(
            @RequestParam("tipo") Optional<String> tipo,
            @RequestParam("ciudad") Optional<String> ciudad,
            @RequestParam("codigoPostal") Optional<String> codigoPostal,
            @RequestParam("provincia") Optional<String> provincia,
            @RequestParam("numHabitaciones") Optional<Integer> numHabitaciones,
            @RequestParam("metrosCuadradosMin") Optional<Double> metrosCuadradosMin,
            @RequestParam("metrosCuadradosMax") Optional<Double> metrosCuadradosMax,
            @RequestParam("precioMin") Optional<Double> precioMin,
            @RequestParam("precioMax") Optional<Double> precioMax,
            @PageableDefault(size = 10, page = 0) Pageable pageable, HttpServletRequest request) {

        Page<Vivienda> result = viviendaService.findByArgs(tipo, ciudad, codigoPostal, provincia,
                numHabitaciones, metrosCuadradosMin, metrosCuadradosMax, precioMin, precioMax, pageable);

        if (result.isEmpty()) {
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
                            .map(dtoConverter::viviendaToGetViviendaDto)
                            .collect(Collectors.toList()));
        }
    }
}
