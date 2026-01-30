package org.example.repository;

import org.example.models.EntradaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Usamos Long como tipo de la clave primaria
@Repository
public interface Repositorio_entrada extends JpaRepository<EntradaDTO, Long> {
    // Métodos CRUD básicos listos para usar (save, findAll, findById, deleteById)
}