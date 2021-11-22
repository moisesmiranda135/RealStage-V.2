package com.salesianostriana.dam.RealEstateG1.service.base;

import com.salesianostriana.dam.RealEstateG1.model.Persona;
import com.salesianostriana.dam.RealEstateG1.repos.BasePersonaRepository;

public abstract class BasePersonaService<T extends Persona,
                                        R extends BasePersonaRepository<T>> extends BaseService<T, Long, R>{

}
