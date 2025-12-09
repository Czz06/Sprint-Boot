package org.example.repository;

import org.example.models.PeliculaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// El repositorio debe ser una interfaz que extiende JpaRepository
@Repository
public interface Repositorio_pelicula extends JpaRepository<PeliculaDTO, Long> {
    // Al extender JpaRepository, ya tienes métodos como save(), findAll(), findById(), deleteById()
}