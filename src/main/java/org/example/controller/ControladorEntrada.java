package org.example.controller;

import org.example.models.EntradaDTO;
import org.example.service.Entrada_service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entradas")
public class ControladorEntrada {

    private final Entrada_service entradaService;

    public ControladorEntrada(Entrada_service entradaService) {
        this.entradaService = entradaService;
    }

    //Comprar y añadir
    @PostMapping
    public ResponseEntity<EntradaDTO> save(@RequestBody EntradaDTO entrada) {
        try {
            Optional<EntradaDTO> optEntradaCreada = entradaService.comprarEntrada(entrada);

            if (optEntradaCreada.isPresent()) {
                // Éxito en la compra, se creó la entrada y se actualizó el stock.
                return ResponseEntity.status(HttpStatus.CREATED).body(optEntradaCreada.get()); // 201 Created
            } else {
                // El servicio devuelve vacío si la Proyección no existe (integridad referencial).
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
            }
        } catch (IllegalStateException e) {
            // Captura la excepción de stock lanzado desde el servicio.
            // 400 Bad Request es apropiado si el cliente envía una petición válida pero no es ejecutable.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            // Cualquier otro error, como fallo de la BD.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }

    //Seleccionar todo
    @GetMapping
    public List<EntradaDTO> findAll() {
        return entradaService.findAll();
    }

    // SELECCIONAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<EntradaDTO> findById(@PathVariable Long id) {
        Optional<EntradaDTO> entrada = entradaService.findById(id);
        return entrada.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    //Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<EntradaDTO> updateEntrada(@PathVariable Long id, @RequestBody EntradaDTO entradaDetails) {
        Optional<EntradaDTO> entradaActualizada = entradaService.actualizarEntrada(id, entradaDetails);

        if (entradaActualizada.isPresent()) {
            return ResponseEntity.ok(entradaActualizada.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean eliminado = entradaService.eliminarEntrada(id);

        if (eliminado) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}