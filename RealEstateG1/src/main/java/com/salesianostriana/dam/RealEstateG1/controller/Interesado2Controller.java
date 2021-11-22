package com.salesianostriana.dam.RealEstateG1.controller;

import com.salesianostriana.dam.RealEstateG1.model.Interesa;
import com.salesianostriana.dam.RealEstateG1.model.InteresaPK;
import com.salesianostriana.dam.RealEstateG1.model.Vivienda;
import com.salesianostriana.dam.RealEstateG1.pagination.PaginationUtilsLinks;
import com.salesianostriana.dam.RealEstateG1.service.InteresaService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/interesado")
@RequiredArgsConstructor
@Tag(name = "Interesado",description = "Controlador para interesados")
public class Interesado2Controller {

    private final InteresaService interesaService;
    private final PaginationUtilsLinks paginationUtilsLinks;


   /* @Operation(summary = "Obtiene un interesado por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el interesado indicado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Interesa.class))}),
            @ApiResponse(responseCode = "400",
                    description = "No se ha encontrado al interesado indicado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Interesa.class))})})
    @GetMapping("/interesado")
    public ResponseEntity<?> BuscarInteresado(
            @RequestParam("id") InteresaPK id,
            @RequestParam("nombre") Optional<String> nombre,
            @RequestParam("apellidos") Optional<String> apellidos,
            @RequestParam("email") Optional<String> email,
            @RequestParam("telefono") Optional<String> telefono,
            @RequestParam("avatar") Optional<Integer> avatar,
            @PageableDefault(size = 10,page = 0) Pageable pageable, HttpServletRequest request) {

        Page<Vivienda> result = interesaService.findByArgs(id,nombre, apellidos, email, telefono,
                avatar, pageable);

        return ResponseEntity
                .of(interesaService.findById(id));



    }*/



}
