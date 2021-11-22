package com.salesianostriana.dam.RealEstateG1.repos;

import com.salesianostriana.dam.RealEstateG1.model.Inmobiliaria;
import com.salesianostriana.dam.RealEstateG1.model.Propietario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InmobiliariaRepository extends JpaRepository<Inmobiliaria,Long> {


}