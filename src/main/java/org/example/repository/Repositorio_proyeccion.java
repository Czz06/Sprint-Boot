package org.example.repository;

import org.example.models.ProyeccionesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  Repositorio_proyeccion extends JpaRepository<ProyeccionesDTO, Integer> {

}
