package com.salesianostriana.dam.RealEstateG1.repos;

import com.salesianostriana.dam.RealEstateG1.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BasePersonaRepository <T extends Persona> extends JpaRepository<T, Long> {


}
