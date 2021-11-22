package com.salesianos.triana.dam.RealEstateV2.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;

    @Lob
    private String avatar;

    private String password;

    private Roles rol;


    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "usuario")
    private List<Vivienda> listaviviendas = new ArrayList<>();

    @ManyToOne
    private Inmobiliaria inmobiliaria;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
