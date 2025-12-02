package org.example.repository;
import org.example.models.EmpleadoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repositorio_empleado extends JpaRepository<EmpleadoDTO, Long> {
    //Al tener Jpa los métodos curd ya vienen por defcto
}
