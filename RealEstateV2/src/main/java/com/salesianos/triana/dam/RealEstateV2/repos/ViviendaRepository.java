package com.salesianos.triana.dam.RealEstateV2.repos;

import com.salesianos.triana.dam.RealEstateV2.model.Vivienda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ViviendaRepository extends JpaRepository<Vivienda, Long> {

    Page<Vivienda> findAll(Specification<Vivienda> todos, Pageable pageable);

}
