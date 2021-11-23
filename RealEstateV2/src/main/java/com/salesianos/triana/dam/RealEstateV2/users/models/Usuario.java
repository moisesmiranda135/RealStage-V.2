package com.salesianos.triana.dam.RealEstateV2.users.models;

import com.salesianos.triana.dam.RealEstateV2.model.Inmobiliaria;
import com.salesianos.triana.dam.RealEstateV2.model.Vivienda;
import com.salesianos.triana.dam.RealEstateV2.users.models.Roles;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
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

    @Enumerated(EnumType.STRING)
    private Roles rol;


    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "usuario")
    private List<Vivienda> listaviviendas = new ArrayList<>();

    @ManyToOne
    private Inmobiliaria inmobiliaria;


    //HELPERS

    public void addInmobiliaria(Inmobiliaria i) {
        this.inmobiliaria = i;
        i.getUsuarios().add(this);
    }

    public void removeInmobiliaria(Inmobiliaria i) {
        i.getUsuarios().remove(this);
        this.inmobiliaria = null;
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
