package com.salesianostriana.dam.RealEstateG1.service;

import com.salesianostriana.dam.RealEstateG1.model.Inmobiliaria;
import com.salesianostriana.dam.RealEstateG1.model.Propietario;
import com.salesianostriana.dam.RealEstateG1.repos.InmobiliariaRepository;
import com.salesianostriana.dam.RealEstateG1.service.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InmobiliariaService extends BaseService<Inmobiliaria,Long, InmobiliariaRepository> {

}
