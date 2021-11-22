package com.salesianostriana.dam.RealEstateG1.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IBaseService <T, ID>{

    List<T> findAll();

    Page<T> findAll(Pageable pageable);

    Optional<T> findById(ID id);

    T save (T t);

    T edit (T t);

    void delete(T t);

    void deleteById(ID id);

    List<T> saveAll (Iterable<T> iterable);

}





