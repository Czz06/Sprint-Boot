package org.example.controller;

import org.example.models.Empleado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.service.Empleado_service;
import java.util.Collections;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class ControladorEmpleado {

    private final Empleado_service empleadoService;

    // Inyección por constructor (recomendado en lugar de @Autowired en el campo)
    public ControladorEmpleado(Empleado_service empleadoService) {
        this.empleadoService = empleadoService;
    }

    // Crear un empleado
    @PostMapping
    public ResponseEntity<Empleado> save(@RequestBody Empleado empleado) {
        try {
            Empleado empleadoCreado = empleadoService.crearEmpleado(empleado);
            return ResponseEntity.status(HttpStatus.CREATED).body(empleadoCreado);
        } catch (Exception e) {
            // Log de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Obtener todos los empleados
    @GetMapping
    public ResponseEntity<List<Empleado>> findAll() {
        try {
            List<Empleado> empleados = empleadoService.findAll();
            return ResponseEntity.ok(empleados);
        } catch (Exception e) {
            // Log de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}
