package org.example.models;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "peliculas")

public class PeliculaDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pelicula")
    private Long id_pelicula;

    @Column(name="titulo")
    private String titulo;

    @Column(name="duracion_minuto")
    private BigDecimal duracionMinuto;

    @Column(name="genero")
    private String genero;

    @Column(name="clasificacion")
    private String clasificacion;

    @Column(name="director")
    private String director;

    @Column(name="sinopsis")
    private String sinopsis;

    @Column(name="fecha_estreno")
    private LocalDate fecha_estreno;

    @Column(name="popularidad")
    private Integer popularidad;

    @Column(name="activa")
    private Boolean activa;

    @Column(name="fecha_baja")
    private LocalDate fecha_baja;

    public Long getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(Long id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public BigDecimal getDuracionMinuto() {
        return duracionMinuto;
    }

    public void setDuracionMinuto(BigDecimal duracionMinuto) {
        this.duracionMinuto = duracionMinuto;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public LocalDate getFecha_estreno() {
        return fecha_estreno;
    }

    public void setFecha_estreno(LocalDate fecha_estreno) {
        this.fecha_estreno = fecha_estreno;
    }

    public Integer getPopularidad() {
        return popularidad;
    }

    public void setPopularidad(Integer popularidad) {
        this.popularidad = popularidad;
    }

    public LocalDate getFecha_baja() {
        return fecha_baja;
    }

    public void setFecha_baja(LocalDate fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }
}
