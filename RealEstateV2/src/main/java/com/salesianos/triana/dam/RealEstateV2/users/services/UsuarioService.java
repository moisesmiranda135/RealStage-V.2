package com.salesianos.triana.dam.RealEstateV2.users.services;


import com.salesianos.triana.dam.RealEstateV2.model.Inmobiliaria;
import com.salesianos.triana.dam.RealEstateV2.services.InmobiliariaService;
import com.salesianos.triana.dam.RealEstateV2.services.base.BaseService;
import com.salesianos.triana.dam.RealEstateV2.users.dto.CreateUsuarioDto;
import com.salesianos.triana.dam.RealEstateV2.users.dto.CreateUsuarioGestorDto;
import com.salesianos.triana.dam.RealEstateV2.users.models.Roles;
import com.salesianos.triana.dam.RealEstateV2.users.models.Usuario;
import com.salesianos.triana.dam.RealEstateV2.users.repos.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("usuarioDetailsService")
@RequiredArgsConstructor
public class UsuarioService extends BaseService<Usuario, Long, UsuarioRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final InmobiliariaService inmobiliariaService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repositorio.findFirstByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email + " no encontrado"));
    }



    public Usuario save(CreateUsuarioDto nuevoUsuario) {
        if (nuevoUsuario.getPassword().contentEquals(nuevoUsuario.getPassword2())) {
            Usuario usuario = Usuario.builder()
                    .password(passwordEncoder.encode(nuevoUsuario.getPassword()))
                    .avatar(nuevoUsuario.getAvatar())
                    .nombre(nuevoUsuario.getNombre())
                    .apellidos(nuevoUsuario.getApeliidos())
                    .email(nuevoUsuario.getEmail())
                    .rol(Roles.PROPIETARIO)
                    .build();
            return save(usuario);
        } else {
            return null;
        }
    }


    public Usuario saveGestor(CreateUsuarioGestorDto nuevoGestor){
        if (nuevoGestor.getPassword().contentEquals(nuevoGestor.getPassword2())) {
            Usuario usuario = Usuario.builder()
                    .password(passwordEncoder.encode(nuevoGestor.getPassword()))
                    .avatar(nuevoGestor.getAvatar())
                    .nombre(nuevoGestor.getNombre())
                    .apellidos(nuevoGestor.getApellidos())
                    .email(nuevoGestor.getEmail())
                    .telefono(nuevoGestor.getTelefono())
                    .inmobiliaria(null)
                    .rol(Roles.GESTOR)
                    .build();

            Optional<Inmobiliaria> inmobiliaria= inmobiliariaService.findById(nuevoGestor.getInmobiliaria());
            usuario.addInmobiliaria(inmobiliaria.get());
            //Inmobiliaria inmo = inmobiliaria.get();
            return save(usuario);
        } else {
            return null;
        }
    }

}
