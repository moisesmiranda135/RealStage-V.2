package com.salesianostriana.dam.RealEstateG1.controller;

import com.salesianostriana.dam.RealEstateG1.model.Interesa;
import com.salesianostriana.dam.RealEstateG1.model.InteresaPK;
import com.salesianostriana.dam.RealEstateG1.model.Interesado;
import com.salesianostriana.dam.RealEstateG1.model.Vivienda;
import com.salesianostriana.dam.RealEstateG1.pagination.PaginationUtilsLinks;
import com.salesianostriana.dam.RealEstateG1.service.InteresaService;
import com.salesianostriana.dam.RealEstateG1.service.InteresadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/interesado")
@RequiredArgsConstructor
@Tag(name = "Interesado",description = "Controlador para interesados")
public class InteresadoController {

    private final InteresadoService interesadoService;
    private final InteresaService interesaService;
    private final PaginationUtilsLinks paginationUtilsLinks;

}