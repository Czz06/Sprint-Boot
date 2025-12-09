package org.example.controller;

import org.example.models.PeliculaDTO;
import org.example.service.Pelicula_service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/peliculas") // Definimos la ruta base para este recurso
public class ControladorPelicula {

    private final Pelicula_service peliculaService;

    // Inyección de dependencias por constructor
    public ControladorPelicula(Pelicula_service peliculaService) {
        this.peliculaService = peliculaService;
    }

    // --- 1. AÑADIR (POST) ---
    @PostMapping
    public ResponseEntity<PeliculaDTO> save(@RequestBody PeliculaDTO pelicula) {
        PeliculaDTO peliculaCreada = peliculaService.crearPelicula(pelicula);
        // Respuesta 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaCreada);
    }

    // --- 2. SELECCIONAR TODO (GET) ---
    @GetMapping
    public List<PeliculaDTO> findAll() {
        return peliculaService.findAll();
    }

    // --- 3. ELIMINAR (DELETE) ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean eliminado = peliculaService.eliminarPelicula(id);

        if (eliminado) {
            // Respuesta 204 No Content (Éxito sin cuerpo de respuesta)
            return ResponseEntity.noContent().build();
        } else {
            // Respuesta 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    // --- 4. ACTUALIZAR (PUT) ---
    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> updatePelicula(@PathVariable Long id, @RequestBody PeliculaDTO peliculaDetails) {
        Optional<PeliculaDTO> peliculaActualizada = peliculaService.actualizarPelicula(id, peliculaDetails);

        if (peliculaActualizada.isPresent()) {
            // Respuesta 200 OK
            return ResponseEntity.ok(peliculaActualizada.get());
        } else {
            // Respuesta 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}