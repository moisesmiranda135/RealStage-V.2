package com.salesianostriana.dam.RealEstateG1.controller;


import com.salesianostriana.dam.RealEstateG1.dto.vivienda.*;
import com.salesianostriana.dam.RealEstateG1.model.Interesa;
import com.salesianostriana.dam.RealEstateG1.model.Interesado;
import com.salesianostriana.dam.RealEstateG1.dto.vivienda.ViviendaDtoConverter;
import com.salesianostriana.dam.RealEstateG1.model.Vivienda;
import com.salesianostriana.dam.RealEstateG1.pagination.PaginationUtilsLinks;
import com.salesianostriana.dam.RealEstateG1.repos.InteresaRepository;
import com.salesianostriana.dam.RealEstateG1.repos.InteresadoRepository;
import com.salesianostriana.dam.RealEstateG1.repos.ViviendaRepository;
import com.salesianostriana.dam.RealEstateG1.service.InteresaService;
import com.salesianostriana.dam.RealEstateG1.service.InteresadoService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vivienda")
@RequiredArgsConstructor
@Tag(name = "Vivienda",description = "Controlador para Viviendas")
public class ViviendaController {

    private final ViviendaService viviendaService;
    private final PaginationUtilsLinks paginationUtilsLinks;
    private final ListViviendaDtoConverter dtoConverter;
    private final DetailDtoConverter detaildtoConverter;

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

    @Operation(summary = "Optiene los detalles de la vivienda elegida por el usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la vivienda correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vivienda.class))})})
    @GetMapping("/{id}")
    public ResponseEntity<GetDetailViviendaDto> findOne(@PathVariable Long id) {
        return ResponseEntity
                .ok(detaildtoConverter.viviendaToGetViviendaDto(viviendaService.findById(id).get()));
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
                            schema = @Schema(implementation = Vivienda.class))})
    })
    @PutMapping("/{id}")
    public ResponseEntity<Vivienda> edit(@RequestBody Vivienda vivienda,
                                         @Parameter(description = "ID de la vivienda a buscar")
                                         @PathVariable Long id) {
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
    }

    @Operation(summary = "Borra una vivienda")
    @ApiResponse(responseCode = "204",
            description = "La vivienda se ha borrado correctamente",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Vivienda.class))})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Parameter(description = "ID de la vivienda a borrar")
                                    @PathVariable Long id) {
        viviendaService.deleteById(id);
        return ResponseEntity.noContent().build();
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
                            schema = @Schema(implementation = Vivienda.class))})})
    @DeleteMapping("/{id}/inmobiliaria")
    public ResponseEntity<?> deleteInmboiliariaAsociada(
            @Parameter(description = "ID de la vivienda a buscar")
            @PathVariable Long id) {

        Optional<Vivienda> vivienda = viviendaService.findById(id);

        if (vivienda.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            vivienda.map(v -> {
                v.setInmobiliaria(null);
                viviendaService.save(v);
                return ResponseEntity.noContent().build();

            });

            return ResponseEntity.noContent().build();

        }

    }
    @Operation(summary = "Obtiene una lista de todas las viviendas y muestra las que más interesados tenga")
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

    @GetMapping("/top")

    public ResponseEntity<List<GetViviendaDto>> findAll(@RequestParam Long n) {

        List<Vivienda> data = viviendaService.diezPorInteres();

        if (data.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();

        } else {
            List<GetViviendaDto> result =
                    data.stream()
                            .map(ViviendaDtoConverter::viviendaToGetViviendaDto)
                            .collect(Collectors.toList());


            return ResponseEntity
                    .ok()
                    .body(result);
            

        }
    }

}


