package com.salesianos.triana.dam.RealEstateV2.services;


import com.salesianos.triana.dam.RealEstateV2.model.Vivienda;
import com.salesianos.triana.dam.RealEstateV2.repos.ViviendaRepository;
import com.salesianos.triana.dam.RealEstateV2.services.base.BaseService;
import com.salesianos.triana.dam.RealEstateV2.users.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;


@Service
public class ViviendaService extends BaseService<Vivienda, Long, ViviendaRepository> {

    public List<Vivienda> buscarPorPropietario(Usuario u) {
        return repositorio.findByUsuario(u);
    }

    public Page<Vivienda> findByArgs(final Optional<String> tipo,
                                     final Optional<String> ciudad,
                                     final Optional<String> codigoPostal,
                                     final Optional<String> provincia,
                                     final Optional<Integer> numHabitaciones,
                                     final Optional<Double> metrosCuadradosMin,
                                     final Optional<Double> metrosCuadradosMax,
                                     final Optional<Double> precioMin,
                                     final Optional<Double> precioMax,
                                     Pageable pageable) {

        Specification<Vivienda> specTipoVivienda = new Specification<Vivienda>() {

            @Override
            public Predicate toPredicate(Root<Vivienda> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (tipo.isPresent()) {
                    return criteriaBuilder.like(criteriaBuilder.lower(root.get("tipo")), "%" + tipo.get() + "%");
                } else {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }
            }
        };

        Specification<Vivienda> specCiudadVivienda = new Specification<Vivienda>() {

            @Override
            public Predicate toPredicate(Root<Vivienda> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (ciudad.isPresent()) {
                    return criteriaBuilder.like(criteriaBuilder.lower(root.get("ciudad")), "%" + ciudad.get() + "%");
                } else {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }
            }
        };

        Specification<Vivienda> specCodigoPostalVivienda = new Specification<Vivienda>() {

            @Override
            public Predicate toPredicate(Root<Vivienda> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (codigoPostal.isPresent()) {
                    return criteriaBuilder.like(criteriaBuilder.lower(root.get("codigoPostal")), "%" + codigoPostal.get() + "%");
                } else {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }
            }
        };

        Specification<Vivienda> specProvinciaVivienda = new Specification<Vivienda>() {

            @Override
            public Predicate toPredicate(Root<Vivienda> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (provincia.isPresent()) {
                    return criteriaBuilder.like(criteriaBuilder.lower(root.get("provincia")), "%" + provincia.get() + "%");
                } else {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }
            }
        };

        Specification<Vivienda> specNumHabitacionesMenorQue = new Specification<Vivienda>() {

            @Override
            public Predicate toPredicate(Root<Vivienda> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (numHabitaciones.isPresent()) {
                    return criteriaBuilder.lessThanOrEqualTo(root.get("numHabitaciones"), numHabitaciones.get());
                } else {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }
            }
        };

        Specification<Vivienda> specNumHabitacionesMayorQue = new Specification<Vivienda>() {

            @Override
            public Predicate toPredicate(Root<Vivienda> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (numHabitaciones.isPresent()) {
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("numHabitaciones"), numHabitaciones.get());
                } else {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }
            }
        };

        Specification<Vivienda> specMetrosCuadradosMenorQue = new Specification<Vivienda>() {

            @Override
            public Predicate toPredicate(Root<Vivienda> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (metrosCuadradosMax.isPresent()) {
                    return criteriaBuilder.lessThanOrEqualTo(root.get("metrosCuadrados"), metrosCuadradosMax.get());
                } else {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }
            }
        };

        Specification<Vivienda> specMetrosCuadradosMayorQue = new Specification<Vivienda>() {

            @Override
            public Predicate toPredicate(Root<Vivienda> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (metrosCuadradosMin.isPresent()) {
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("metrosCuadrados"), metrosCuadradosMin.get());
                } else {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }
            }
        };

        Specification<Vivienda> specPrecioMenorQue = new Specification<Vivienda>() {

            @Override
            public Predicate toPredicate(Root<Vivienda> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if (precioMax.isPresent()) {
                    return criteriaBuilder.lessThanOrEqualTo(root.get("precio"), precioMax.get());
                } else {
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }
            }
        };


        Specification<Vivienda> specPrecioMayorQue = new Specification<Vivienda>() {


            @Override
            public Predicate toPredicate(Root<Vivienda> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if(precioMin.isPresent()){
                    return criteriaBuilder.greaterThanOrEqualTo(root.get("precio"),precioMin.get());
                }
                else{
                    return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
                }
            }
        };

        Specification<Vivienda> todos = specCiudadVivienda
                .and(specCodigoPostalVivienda)
                .and(specMetrosCuadradosMayorQue)
                .and(specMetrosCuadradosMenorQue)
                .and(specNumHabitacionesMayorQue)
                .and(specNumHabitacionesMenorQue)
                .and(specPrecioMayorQue)
                .and(specPrecioMenorQue)
                .and(specProvinciaVivienda)
                .and(specTipoVivienda);

        return this.repositorio.findAll(todos,pageable);

    }

}
