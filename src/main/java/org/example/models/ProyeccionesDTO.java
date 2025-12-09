package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name="proyecciones")
public class ProyeccionesDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="id_proyeccion")
    private int id_proyeccion;

    @Column(name="id_pelicula")
    private int id_pelicula;

    @Column(name="id_sala")
    private int id_sala;

    @Column(name="fecha_hora_inicio")
    private String fecha_hora_inicio;

    @Column(name="fecha_hora_fin")
    private String fecha_hora_fin;

    @Column(name="precio_entrada")
    private double precio_entrada;

    @Column(name="asientos_disponibles")
    private int asientos_disponibles;

    public int getId_proyeccion() {
        return id_proyeccion;
    }

    public void setId_proyeccion(int id_proyeccion) {
        this.id_proyeccion = id_proyeccion;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public String getFecha_hora_inicio() {
        return fecha_hora_inicio;
    }

    public void setFecha_hora_inicio(String fecha_hora_inicio) {
        this.fecha_hora_inicio = fecha_hora_inicio;
    }

    public int getId_pelicula() {
        return id_pelicula;
    }

    public void setId_pelicula(int id_pelicula) {
        this.id_pelicula = id_pelicula;
    }

    public int getAsientos_disponibles() {
        return asientos_disponibles;
    }

    public void setAsientos_disponibles(int asientos_disponibles) {
        this.asientos_disponibles = asientos_disponibles;
    }

    public String getFecha_hora_fin() {
        return fecha_hora_fin;
    }

    public void setFecha_hora_fin(String fecha_hora_fin) {
        this.fecha_hora_fin = fecha_hora_fin;
    }

    public double getPrecio_entrada() {
        return precio_entrada;
    }

    public void setPrecio_entrada(double precio_entrada) {
        this.precio_entrada = precio_entrada;
    }
}
