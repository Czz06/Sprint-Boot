package org.example.models;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "entradas")
public class EntradaDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY,
            description = "ID autogenerado por el sistema, no debe ser enviado en POST.")
    @Column(name = "id_entrada")
    private Long id_entrada;

    @Column(name = "id_proyeccion")
    private Integer id_proyeccion; // Referencia a la tabla proyecciones

    @Column(name = "fecha_compra")
    private LocalDateTime fecha_compra;

    @Column(name = "asiento")
    private String asiento;

    @Column(name = "precio_pagado")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY,
            description = "El precio se obtiene automáticamente de la Proyección en el servidor.")
    private BigDecimal precio_pagado;

    @Column(name = "cliente_email")
    private String cliente_email;


    public EntradaDTO() {

}

    public Long getId_entrada() {
        return id_entrada;
    }

    public Integer getId_proyeccion() {
        return id_proyeccion;
    }

    public void setId_proyeccion(Integer id_proyeccion) {
        this.id_proyeccion = id_proyeccion;
    }

    public LocalDateTime getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(LocalDateTime fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public BigDecimal getPrecio_pagado() {
        return precio_pagado;
    }

    public void setPrecio_pagado(BigDecimal precio_pagado) {
        this.precio_pagado = precio_pagado;
    }

    public String getCliente_email() {
        return cliente_email;
    }

    public void setCliente_email(String cliente_email) {
        this.cliente_email = cliente_email;
    }
}