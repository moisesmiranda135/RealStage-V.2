package com.salesianos.triana.dam.RealEstateV2.services;

import com.salesianos.triana.dam.RealEstateV2.model.Inmobiliaria;
import com.salesianos.triana.dam.RealEstateV2.model.Vivienda;
import com.salesianos.triana.dam.RealEstateV2.repos.InmobiliariaRepository;
import com.salesianos.triana.dam.RealEstateV2.services.base.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InmobiliariaService extends BaseService<Inmobiliaria, Long, InmobiliariaRepository> {

    public Page<Inmobiliaria> findAll(Pageable pageable){
        return this.repositorio.findAll(pageable);
    }
}
