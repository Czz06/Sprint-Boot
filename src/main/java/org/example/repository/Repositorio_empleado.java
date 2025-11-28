package org.example.repository;
import org.example.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repositorio_empleado extends JpaRepository<Empleado, Long> {
    //Al tener Jpa los métodos curd ya vienen por defcto
}
