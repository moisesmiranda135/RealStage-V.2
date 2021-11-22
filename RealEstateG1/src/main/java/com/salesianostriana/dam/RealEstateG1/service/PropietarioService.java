package com.salesianostriana.dam.RealEstateG1.service;

import com.salesianostriana.dam.RealEstateG1.model.Propietario;
import com.salesianostriana.dam.RealEstateG1.repos.PropietarioRepository;
import com.salesianostriana.dam.RealEstateG1.service.base.BasePersonaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PropietarioService extends BasePersonaService<Propietario, PropietarioRepository> {

    public Page<Propietario> findAll(Pageable pageable){

        return this.repositorio.findAll(pageable);
    }

}
