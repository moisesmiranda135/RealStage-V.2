package com.salesianos.triana.dam.RealEstateV2.security;

import com.salesianos.triana.dam.RealEstateV2.security.dto.JwtUsuarioResponse;
import com.salesianos.triana.dam.RealEstateV2.security.dto.LoginDto;
import com.salesianos.triana.dam.RealEstateV2.security.jwt.JwtProvider;
import com.salesianos.triana.dam.RealEstateV2.users.models.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDto.getEmail(),
                                loginDto.getPassword()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);


        String jwt = jwtProvider.generateToken(authentication);


        Usuario u = (Usuario) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertUserToJwtUserResponse(u, jwt));

    }

    @GetMapping("/me")
    public ResponseEntity<?> sobreMi(@AuthenticationPrincipal Usuario u){
        return ResponseEntity.ok(convertUserToJwtUserResponse(u, null));
    }


    private JwtUsuarioResponse convertUserToJwtUserResponse(Usuario u, String jwt) {
        return JwtUsuarioResponse.builder()
                .nombre(u.getNombre())
                .apellidos(u.getApellidos())
                .email(u.getEmail())
                .avatar(u.getAvatar())
                .role(u.getRol().name())
                .token(jwt)
                .build();
    }


}
