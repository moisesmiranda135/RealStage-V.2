package com.salesianos.triana.dam.RealEstateV2.users.controller;


import com.salesianos.triana.dam.RealEstateV2.users.dto.CreateUsuarioDto;
import com.salesianos.triana.dam.RealEstateV2.users.dto.GetUsuarioDto;
import com.salesianos.triana.dam.RealEstateV2.users.dto.UsuarioDtoConverter;
import com.salesianos.triana.dam.RealEstateV2.users.models.Usuario;
import com.salesianos.triana.dam.RealEstateV2.users.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioEntityService;
    private final UsuarioDtoConverter usuarioDtoConverter;

    @PostMapping("/auth/register")
    public ResponseEntity<GetUsuarioDto> nuevoUsuario(@RequestBody CreateUsuarioDto newUser) {
        Usuario saved = usuarioEntityService.save(newUser);

        if (saved == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(usuarioDtoConverter.convertUsuarioEntityToGetUsuarioDto(saved));

    }


}
