package org.example.service;

import org.example.models.PeliculaDTO;
import org.example.repository.Repositorio_pelicula;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
public class Pelicula_service {

    private final Repositorio_pelicula repositorioPelicula;

    public Pelicula_service(Repositorio_pelicula repositorioPelicula) {
        this.repositorioPelicula = repositorioPelicula;
    }

    // --- 1. AÑADIR (CREAR) ---
    public PeliculaDTO crearPelicula(PeliculaDTO pelicula) {
        // Al guardar, JPA usará el ID para determinar si es un INSERT o un UPDATE (si el ID ya existe)
        return repositorioPelicula.save(pelicula);
    }

    // --- 2. SELECCIONAR TODO (LEER) ---
    public List<PeliculaDTO> findAll() {
        return repositorioPelicula.findAll();
    }

    // --- 3. ELIMINAR ---
    public boolean eliminarPelicula(Long id) {
        // Verificar si la película existe antes de intentar eliminar
        if (repositorioPelicula.existsById(id)) {
            repositorioPelicula.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // --- 4. ACTUALIZAR ---
    public Optional<PeliculaDTO> actualizarPelicula(Long id, PeliculaDTO detallesPelicula) {
        // Buscar la película existente por su ID
        Optional<PeliculaDTO> peliculaExistente = repositorioPelicula.findById(id);

        if (peliculaExistente.isPresent()) {
            PeliculaDTO pelicula = peliculaExistente.get();

            // Seteamos todos los campos con los nuevos detalles
            pelicula.setTitulo(detallesPelicula.getTitulo());
            pelicula.setDuracionMinuto(detallesPelicula.getDuracionMinuto());
            pelicula.setGenero(detallesPelicula.getGenero());
            pelicula.setClasificacion(detallesPelicula.getClasificacion());
            pelicula.setDirector(detallesPelicula.getDirector());
            pelicula.setSinopsis(detallesPelicula.getSinopsis());
            pelicula.setFecha_estreno(detallesPelicula.getFecha_estreno());
            pelicula.setPopularidad(detallesPelicula.getPopularidad());
            pelicula.setActiva(detallesPelicula.getActiva());
            // Nota: Podrías añadir lógica aquí para manejar fecha_baja si activa=false

            // Guardar y devolver la película actualizada
            return Optional.of(repositorioPelicula.save(pelicula));
        } else {
            // La película no fue encontrada
            return Optional.empty();
        }
    }
}
