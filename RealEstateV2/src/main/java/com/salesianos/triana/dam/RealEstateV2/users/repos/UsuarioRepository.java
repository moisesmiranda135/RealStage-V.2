package com.salesianos.triana.dam.RealEstateV2.users.repos;

import com.salesianos.triana.dam.RealEstateV2.users.models.Roles;
import com.salesianos.triana.dam.RealEstateV2.users.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findFirstByEmail(String email);

    List<Usuario> findByRol (Roles rol);
}
