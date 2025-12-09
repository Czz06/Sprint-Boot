package org.example.controller;

import org.example.models.EmpleadoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.service.Empleado_service;
import java.util.Optional;
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
    public ResponseEntity<EmpleadoDTO> save(@RequestBody EmpleadoDTO empleado) {
        try {
            EmpleadoDTO empleadoCreado = empleadoService.crearEmpleado(empleado);
            return ResponseEntity.status(HttpStatus.CREATED).body(empleadoCreado);
        } catch (Exception e) {
            // Log de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Obtener todos los empleados
    @GetMapping
    public List<EmpleadoDTO> findAll() {
        System.out.println(">>> Entrando a /api/empleados <<<");
        try {
            List<EmpleadoDTO> empleados = empleadoService.findAll();
            System.out.println(">>> Empleados recuperados: " + empleados);
            return empleados;
        } catch (Exception e) {
            e.printStackTrace(); // <<<< Esto imprimirá la causa real del error
            throw e; // Volvemos a lanzar la excepción para que Spring la maneje
        }
    }

    //Eliminar un empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        // Llama al servicio para intentar eliminar
        boolean eliminado = empleadoService.eliminarEmpleado(id);

        if (eliminado) {
            // Código 204: No Content. Éxito, pero no hay cuerpo de respuesta.
            return ResponseEntity.noContent().build();
        } else {
            // Código 404: Not Found. El empleado con ese ID no existe.
            return ResponseEntity.notFound().build();
        }
    }

    //Actualizar un empleado
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> updateEmpleado(@PathVariable Long id, @RequestBody EmpleadoDTO empleadoDetails) {

        Optional<EmpleadoDTO> empleadoActualizado = empleadoService.actualizarEmpleado(id, empleadoDetails);

        if (empleadoActualizado.isPresent()) {
            // Código 200: OK. La actualización fue exitosa.
            return ResponseEntity.ok(empleadoActualizado.get());
        } else {
            // Código 404: Not Found. El empleado a actualizar no existe.
            return ResponseEntity.notFound().build();
        }
    }
}

