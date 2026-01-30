package org.example.service;

import org.example.models.EntradaDTO;
import org.example.models.ProyeccionesDTO;
import org.example.repository.Repositorio_entrada;
import org.example.repository.Repositorio_proyeccion; // Asegúrate de tener esta dependencia
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importante para la lógica de stock

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class Entrada_service {

    private final Repositorio_entrada repositorioEntrada;
    private final Repositorio_proyeccion repositorioProyecciones; // Dependencia de Proyecciones

    // Constructor para inyección de dependencias
    public Entrada_service(Repositorio_entrada repositorioEntrada, Repositorio_proyeccion repositorioProyecciones) {
        this.repositorioEntrada = repositorioEntrada;
        this.repositorioProyecciones = repositorioProyecciones;
    }

    //COMPRAR ENTRADA
    @Transactional // Garantiza que si falla la actualización de stock, la compra no se registra
    public Optional<EntradaDTO> comprarEntrada(EntradaDTO entrada) {

        // Obtener la Proyección
        Optional<ProyeccionesDTO> optProyeccion = repositorioProyecciones.findById(entrada.getId_proyeccion());

        if (optProyeccion.isEmpty()) {
            return Optional.empty();
        }

        ProyeccionesDTO proyeccion = optProyeccion.get();

        // Verificar Stock
        if (proyeccion.getAsientos_disponibles() <= 0) {
            // Lanza una excepción de negocio que será capturada por el Controlador (para devolver 400)
            throw new IllegalStateException("Lo sentimos, no quedan asientos disponibles para esta proyección.");
        }

        // Asignar el precio de la Proyección a la Entrada (para asegurar la coherencia)
        entrada.setPrecio_pagado(proyeccion.getPrecio_entrada());


        // Actualizar Stock en Proyección
        proyeccion.setAsientos_disponibles(proyeccion.getAsientos_disponibles() - 1);
        repositorioProyecciones.save(proyeccion); // Guarda el nuevo stock

        // Guardar la nueva Entrada

        EntradaDTO entradaComprada = repositorioEntrada.save(entrada);

        return Optional.of(entradaComprada);
    }

    // SELECCIONAR TODO
    public List<EntradaDTO> findAll() {
        return repositorioEntrada.findAll();
    }

    //SELECCIONAR POR ID
    public Optional<EntradaDTO> findById(Long id) {
        return repositorioEntrada.findById(id);
    }

    //ACTUALIZAR
    public Optional<EntradaDTO> actualizarEntrada(Long id, EntradaDTO detallesEntrada) {
        Optional<EntradaDTO> entradaExistente = repositorioEntrada.findById(id);

        if (entradaExistente.isPresent()) {
            EntradaDTO entrada = entradaExistente.get();

            // Actualizar campos
            entrada.setId_proyeccion(detallesEntrada.getId_proyeccion());
            entrada.setFecha_compra(detallesEntrada.getFecha_compra());
            entrada.setAsiento(detallesEntrada.getAsiento());
            entrada.setPrecio_pagado(detallesEntrada.getPrecio_pagado());
            entrada.setCliente_email(detallesEntrada.getCliente_email());

            return Optional.of(repositorioEntrada.save(entrada));
        } else {
            return Optional.empty();
        }
    }

    //ELIMINAR
    public boolean eliminarEntrada(Long id) {
        Optional<EntradaDTO> optEntrada = repositorioEntrada.findById(id);
        if (optEntrada.isPresent()) {
            EntradaDTO entrada = optEntrada.get();

            repositorioProyecciones.findById(entrada.getId_proyeccion()).ifPresent(p -> {
                p.setAsientos_disponibles(p.getAsientos_disponibles() + 1);
                repositorioProyecciones.save(p);
            });

            repositorioEntrada.deleteById(id);
            return true;
        }
        return false;
    }
}