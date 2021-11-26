package com.salesianos.triana.dam.RealEstateV2.controller;

import com.salesianos.triana.dam.RealEstateV2.dto.propietario.GetPropietarioViviendaDto;
import com.salesianos.triana.dam.RealEstateV2.dto.vivienda.DetailDtoConverter;
import com.salesianos.triana.dam.RealEstateV2.dto.vivienda.GetDetailViviendaDto;
import com.salesianos.triana.dam.RealEstateV2.dto.vivienda.ListViviendaDtoConverter;
import com.salesianos.triana.dam.RealEstateV2.model.Inmobiliaria;
import com.salesianos.triana.dam.RealEstateV2.model.Vivienda;
import com.salesianos.triana.dam.RealEstateV2.pagination.PaginationUtilsLinks;
import com.salesianos.triana.dam.RealEstateV2.services.InmobiliariaService;
import com.salesianos.triana.dam.RealEstateV2.services.ViviendaService;
import com.salesianos.triana.dam.RealEstateV2.users.models.Roles;
import com.salesianos.triana.dam.RealEstateV2.users.models.Usuario;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vivienda/")
@RequiredArgsConstructor
@Tag(name = "vivienda",description = "Controlador para viviendas")
public class ViviendaController {

    private final ViviendaService viviendaService;
    private final DetailDtoConverter detailDtoConverter;
    private final PaginationUtilsLinks paginationUtilsLinks;
    private final ListViviendaDtoConverter dtoConverter;
    private final InmobiliariaService inmobiliariaService;

    @Operation(summary = "Optiene los detalles de la vivienda elegida por el usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la vivienda correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "401",
                    description = "El usuario no está autorizado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))})
    })
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
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "401",
                    description = "El usuario no está autorizado",
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


    @Operation(summary = "Borra una vivienda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "La vivienda se ha borrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "401",
                    description = "El usuario no está autorizado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "403",
                    description = "No tienes permisos para esta petición",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Parameter(description = "ID de la vivienda a borrar")
                                    @PathVariable Long id,
                                    @AuthenticationPrincipal Usuario user) {

        Optional<Vivienda> vivienda = viviendaService.findById(id);

        if (viviendaService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }else if (user.getRol().equals(Roles.ADMIN) ||
                    (user.getRol().equals(Roles.PROPIETARIO) && vivienda.get().getUsuario().getId().equals(user.getId()))) {
            viviendaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.status(403).build();
        }
    }


    @Operation(summary = "Borrar inmobiliaria asociada a una vivienda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se elimina la inmbiliaria asociada a la vivienda pero no se borra la inmobiliaria",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "404",
                    description = "No se a podido emcontrar la vivienda",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "401",
                    description = "El usuario no está autorizado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "403",
                    description = "No tienes permisos para esta petición",
                    content = @Content)
    })
    @DeleteMapping("/{id}/inmobiliaria")
    public ResponseEntity<?> deleteInmboiliariaAsociada(
            @Parameter(description = "ID de la vivienda a buscar")
            @PathVariable Long id,
            @AuthenticationPrincipal Usuario user) {

        Optional<Vivienda> vivienda = viviendaService.findById(id);

        if (vivienda.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else if (user.getRol().equals(Roles.ADMIN)  ||
                (user.getRol().equals(Roles.PROPIETARIO) && vivienda.get().getUsuario().getId().equals(user.getId())) ||
                (user.getRol().equals(Roles.GESTOR)&& vivienda.get().getInmobiliaria().getId().equals(user.getInmobiliaria().getId()))) {
            vivienda.map(v -> {
                v.setInmobiliaria(null);
                viviendaService.save(v);
                return ResponseEntity.noContent().build();

            });
        }else {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(403).build();
    }




    @Operation(summary = "Asignar vivienda a una inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado la inmobiliaria correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha podido crear la inmobiliaria",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "401",
                    description = "El usuario no está autorizado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "403",
                    description = "No tienes permisos para esta petición",
                    content = @Content),
    })
    @PostMapping("/{id}/inmobiliaria/{id2}")
    public ResponseEntity<Vivienda> addViviendaToInmobiliaria(@PathVariable Long id,
                                                              @PathVariable Long id2,
                                                              @AuthenticationPrincipal Usuario user){

        Optional<Vivienda> vivienda = viviendaService.findById(id);
        Optional<Inmobiliaria> inmobiliaria = inmobiliariaService.findById(id2);


        if (vivienda.isEmpty()){
            return ResponseEntity.notFound().build();
        }else if (user.getRol().equals(Roles.ADMIN) ||
                (user.getRol().equals(Roles.PROPIETARIO) && vivienda.get().getUsuario().getId().equals(user.getId()))) {
            vivienda.get().setInmobiliaria(inmobiliaria.get());
            viviendaService.save(vivienda.get());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .build();
        }else {
            return ResponseEntity.status(403).build();
        }

    }


    @Operation(summary = "Puede editar una vivienda concreta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha editado y guardado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se a podido editar o no se pudo encontrar",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "401",
                    description = "El usuario no está autorizado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))}),
            @ApiResponse(responseCode = "403",
                    description = "No tienes permisos para esta petición",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<Vivienda> edit(@RequestBody Vivienda vivienda,
                                         @Parameter(description = "ID de la vivienda a buscar")
                                         @PathVariable Long id,
                                         @AuthenticationPrincipal Usuario user) {

        Optional<Vivienda> vivienda1 = viviendaService.findById(id);

        if (vivienda1.isEmpty()){
            return ResponseEntity.notFound().build();
        }else if (user.getRol().equals(Roles.ADMIN) ||
                (user.getRol().equals(Roles.PROPIETARIO) && vivienda1.get().getUsuario().getId().equals(user.getId()))) {
            return ResponseEntity.of(
                    viviendaService.findById(id).map(v -> {
                        v.setTitulo(vivienda.getTitulo());
                        v.setDescripcion(vivienda.getDescripcion());
                        v.setAvatar(vivienda.getAvatar());
                        v.setLatlng(vivienda.getLatlng());
                        v.setCodigoPostal(vivienda.getCodigoPostal());
                        v.setDireccion(vivienda.getDireccion());
                        v.setPoblacion(vivienda.getPoblacion());
                        v.setProvincia(vivienda.getProvincia());
                        v.setTipo(vivienda.getTipo());
                        v.setPrecio(vivienda.getPrecio());
                        v.setNumHabitaciones(vivienda.getNumHabitaciones());
                        v.setMetrosCuadrados(vivienda.getMetrosCuadrados());
                        v.setNumBanyos(vivienda.getNumBanyos());
                        v.setTienePiscina(vivienda.isTienePiscina());
                        v.setTieneAscensor(vivienda.isTieneAscensor());
                        v.setTieneGaraje(vivienda.isTieneGaraje());
                        viviendaService.save(v);
                        return v;
                    })
            );
        }else {
            return ResponseEntity.status(403).build();
        }

    }
}
