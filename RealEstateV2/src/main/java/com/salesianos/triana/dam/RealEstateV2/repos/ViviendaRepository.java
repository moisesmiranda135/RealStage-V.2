package com.salesianos.triana.dam.RealEstateV2.repos;

import com.salesianos.triana.dam.RealEstateV2.dto.vivienda.GetViviendasPropietarioDto;
import com.salesianos.triana.dam.RealEstateV2.model.Vivienda;
import com.salesianos.triana.dam.RealEstateV2.users.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ViviendaRepository extends JpaRepository<Vivienda, Long> {

    Page<Vivienda> findAll(Specification<Vivienda> todos, Pageable pageable);


    @Query("""
            select new com.salesianos.triana.dam.RealEstateV2.dto.vivienda.GetViviendasPropietarioDto(
                v.id, v.titulo, v.avatar, v.codigoPostal, v.provincia, v.tipo, v.precio, v.numHabitaciones, v.metrosCuadrados,
                v.numBanyos, v.tienePiscina, v.tieneAscensor, v.tieneGaraje
            )
            from Vivienda v
            where v.usuario.id = :id
            """)
    List<GetViviendasPropietarioDto> findByUsuario(Long id);

}
