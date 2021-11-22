package com.salesianostriana.dam.RealEstateG1.repos;

import com.salesianostriana.dam.RealEstateG1.model.Vivienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface ViviendaRepository extends JpaRepository<Vivienda, Long>, JpaSpecificationExecutor<Vivienda> {

   /**Devuelve el numero de interesados que hay en una vivienda
     * @return*/

   public List<Vivienda> findTop10ViviendaByOrderByListInteresa();


}