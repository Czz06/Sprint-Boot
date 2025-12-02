package org.example.models;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "empleados")
public class EmpleadoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_empleado")
    private Long id_empleado;

    @Column(name="nombre")
    private String nombre;

    @Column(name="puesto")
    private String puesto;

    @Column(name="tipo_jornada")
    private String tipo_jornada;

    @Column(name="email")
    private String email;

    @Column(name="telefono")
    private String telefono;

    @Column(name="fecha_contratacion")
    private LocalDate fecha_contratacion;

    @Column(name="salario_hora")
    private BigDecimal salario_hora;

    @Column(name="estado")
    private Boolean estado; //

    public Long getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(Long id_empleado) {
        this.id_empleado = id_empleado;
    }

    public boolean isEstado() {
        return estado;
    }
    public Boolean getEstado() {
        return estado != null ? estado : true; // nunca devuelve null
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public BigDecimal getSalario_hora() {
        return salario_hora;
    }

    public void setSalario_hora(BigDecimal salario_hora) {
        this.salario_hora = salario_hora;
    }

    public LocalDate getFecha_contratacion() {
        return fecha_contratacion;
    }

    public void setFecha_contratacion(LocalDate fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getTipo_jornada() {
        return tipo_jornada;
    }

    public void setTipo_jornada(String tipo_jornada) {
        this.tipo_jornada = tipo_jornada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
