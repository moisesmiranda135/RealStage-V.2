package com.salesianostriana.dam.RealEstateG1.controller;

import com.salesianostriana.dam.RealEstateG1.dto.inmobiliaria.GetInmobiliariaDto;
import com.salesianostriana.dam.RealEstateG1.dto.inmobiliaria.InmobiliariaDtoConverter;
import com.salesianostriana.dam.RealEstateG1.model.Inmobiliaria;
import com.salesianostriana.dam.RealEstateG1.model.Vivienda;
import com.salesianostriana.dam.RealEstateG1.pagination.PaginationUtilsLinks;
import com.salesianostriana.dam.RealEstateG1.repos.InmobiliariaRepository;
import com.salesianostriana.dam.RealEstateG1.service.InmobiliariaService;
import com.salesianostriana.dam.RealEstateG1.service.ViviendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inmobiliaria")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Inmobiliaria",description = "Controlador para Inmobiliarias")
public class InmobiliariaController {

    private final InmobiliariaService  inmobiliariaService;
    private final PaginationUtilsLinks paginationUtilsLinks;
    private final InmobiliariaDtoConverter inmobiliariaDtoConverter;

    @Operation(summary = "Obtiene una lista de todas las Inmobiliarias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la lista de Inmobiliarias",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado ninguna Inmobiliaria",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))})})
    @GetMapping("/")

    public ResponseEntity<?> findAll(@PageableDefault(size = 10,page = 0) Pageable pageable,
                                                      HttpServletRequest request) {

        Page<Inmobiliaria> result = inmobiliariaService.findAll(pageable);

        if (inmobiliariaService.findAll().isEmpty()) {
            return ResponseEntity
                    .noContent()
                    .build();
        }else {
            UriComponentsBuilder uriBuilder = UriComponentsBuilder
                    .fromHttpUrl(request.getRequestURL().toString());
            return ResponseEntity
                    .ok()
                    .header("link",paginationUtilsLinks.createLinkHeader(result,uriBuilder))
                    .body(result);
        }

    }

    @Operation(summary = "Crea una nueva inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado la inmobiliaria correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido crear la inmobiliaria",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))})
    })
    @PostMapping("/")
    public ResponseEntity<Inmobiliaria> add(@RequestBody Inmobiliaria nueva){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inmobiliariaService.save(nueva));
    }

    @Operation(summary = "Edita los atributos de una inmobiliaria existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la inmobiliaria y se ha modificado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado la inmobiliaria indicada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))})})
    @PutMapping("/{id}")
    public ResponseEntity<Inmobiliaria> edit(@RequestBody Inmobiliaria modificada,
                                             @Parameter(description = "ID de la inmobiliaria a editar")
                                             @PathVariable Long id){
        return ResponseEntity.of(
                inmobiliariaService.findById(id).map(inmobiliaria -> {
                    inmobiliaria.setNombre(modificada.getNombre());
                    inmobiliaria.setEmail(modificada.getEmail());
                    inmobiliaria.setTelefono(modificada.getTelefono());
                    inmobiliariaService.save(inmobiliaria);
                    return inmobiliaria;
                })
        );
    }

    @Operation(summary = "Borra una inmobiliaria previamente creada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "No content",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))}),


            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado la inmobiliaria indicada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))})})

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        inmobiliariaService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @Operation(summary = "Optiene los detalles de la inmobiliaria elegida por el usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la inmobiliaria correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inmobiliaria.class))})})
    @GetMapping("/{id}")
    public ResponseEntity<GetInmobiliariaDto> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(inmobiliariaDtoConverter.inmobiliariaToGetInmobiliariaDto(inmobiliariaService.findById(id).get()));
    }

}
