package org.example.controller;

import org.example.models.ProyeccionesDTO;
import org.example.service.Proyeccion_service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proyecciones") // Ruta base para las proyecciones
public class ControladorProyeccion {

    private final Proyeccion_service proyeccionesService;

    public ControladorProyeccion(Proyeccion_service proyeccionesService) {
        this.proyeccionesService = proyeccionesService;
    }

    // AÑADIR
    @PostMapping
    public ResponseEntity<ProyeccionesDTO> save(@RequestBody ProyeccionesDTO proyeccion) {
        ProyeccionesDTO proyeccionCreada = proyeccionesService.crearProyeccion(proyeccion);
        // Respuesta Created
        return ResponseEntity.status(HttpStatus.CREATED).body(proyeccionCreada);
    }

    // SELECCIONAR TODO
    @GetMapping
    public List<ProyeccionesDTO> findAll() {
        return proyeccionesService.findAll();
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        boolean eliminado = proyeccionesService.eliminarProyeccion(id);

        if (eliminado) {
            // Respuesta No Content
            return ResponseEntity.noContent().build();
        } else {
            // Respuesta Not Found
            return ResponseEntity.notFound().build();
        }
    }

    //ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<ProyeccionesDTO> updateProyeccion(@PathVariable Integer id, @RequestBody ProyeccionesDTO proyeccionDetails) {
        Optional<ProyeccionesDTO> proyeccionActualizada = proyeccionesService.actualizarProyeccion(id, proyeccionDetails);

        if (proyeccionActualizada.isPresent()) {
            // Respuesta OK
            return ResponseEntity.ok(proyeccionActualizada.get());
        } else {
            // Respuesta Not Found
            return ResponseEntity.notFound().build();
        }
    }
}