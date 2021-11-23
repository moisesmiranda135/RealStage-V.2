package com.salesianos.triana.dam.RealEstateV2.users.services;


import com.salesianos.triana.dam.RealEstateV2.services.base.BaseService;
import com.salesianos.triana.dam.RealEstateV2.users.dto.CreateUsuarioDto;
import com.salesianos.triana.dam.RealEstateV2.users.models.Roles;
import com.salesianos.triana.dam.RealEstateV2.users.models.Usuario;
import com.salesianos.triana.dam.RealEstateV2.users.repos.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("usuarioDetailsService")
@RequiredArgsConstructor
public class UsuarioService extends BaseService<Usuario, Long, UsuarioRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

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

}
