package com.salesianos.triana.dam.RealEstateV2.model;

import com.salesianos.triana.dam.RealEstateV2.users.models.Usuario;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vivienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private String avatar;
    private String latlng;
    private String direccion;
    private String codigoPostal;
    private String poblacion;
    private String provincia;

    @Enumerated(EnumType.STRING)
    private TipoVivienda tipo;

    private Double precio;
    private int numHabitaciones;
    private double metrosCuadrados;
    private int numBanyos;
    private boolean tienePiscina;
    private boolean tieneAscensor;
    private boolean tieneGaraje;

    @ManyToOne
    private Inmobiliaria inmobiliaria;

    @ManyToOne
    private Usuario usuario;



    //HELPERS PROPIETARIO
    //**************************************************

    public void addToPropietario(Usuario p){
        this.usuario = p;
        p.getListaviviendas().add(this);
    }

    public void deleteFromPropietario(Usuario p){
        p.getListaviviendas().remove(this);
        this.usuario = null;
    }



    //Constructores

    public Vivienda(Long id, String titulo, String avatar, String codigoPostal, String provincia, TipoVivienda tipo, Double precio,
                    int numHabitaciones, double metrosCuadrados, int numBanyos, boolean tienePiscina,
                    boolean tieneAscensor, boolean tieneGaraje) {
        this.id = id;
        this.titulo = titulo;
        this.avatar = avatar;
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
        this.tipo = tipo;
        this.precio = precio;
        this.numHabitaciones = numHabitaciones;
        this.metrosCuadrados = metrosCuadrados;
        this.numBanyos = numBanyos;
        this.tienePiscina = tienePiscina;
        this.tieneAscensor = tieneAscensor;
        this.tieneGaraje = tieneGaraje;
    }

    public Vivienda(Long id, String titulo, String descripcion, String avatar, String latlng, String direccion, String codigoPostal,
                    String poblacion, String provincia, TipoVivienda tipo, Double precio, int numHabitaciones, double metrosCuadrados,
                    int numBanyos, boolean tienePiscina, boolean tieneAscensor, boolean tieneGaraje) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.avatar = avatar;
        this.latlng = latlng;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.tipo = tipo;
        this.precio = precio;
        this.numHabitaciones = numHabitaciones;
        this.metrosCuadrados = metrosCuadrados;
        this.numBanyos = numBanyos;
        this.tienePiscina = tienePiscina;
        this.tieneAscensor = tieneAscensor;
        this.tieneGaraje = tieneGaraje;
    }
}
