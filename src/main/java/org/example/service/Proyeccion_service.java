package org.example.service;

import org.example.models.ProyeccionesDTO;
import org.example.repository.Repositorio_proyeccion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Proyeccion_service {

    private final Repositorio_proyeccion repositorioProyecciones;

    public Proyeccion_service(Repositorio_proyeccion repositorioProyecciones) {
        this.repositorioProyecciones = repositorioProyecciones;
    }

    // --- 1. AÑADIR (CREAR) ---
    public ProyeccionesDTO crearProyeccion(ProyeccionesDTO proyeccion) {
        return repositorioProyecciones.save(proyeccion);
    }

    // --- 2. SELECCIONAR TODO (LEER) ---
    public List<ProyeccionesDTO> findAll() {
        return repositorioProyecciones.findAll();
    }

    // --- 3. ELIMINAR ---
    public boolean eliminarProyeccion(Integer id) {
        // Verifica si la proyección existe antes de eliminar
        if (repositorioProyecciones.existsById(id)) {
            repositorioProyecciones.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // --- 4. ACTUALIZAR ---
    public Optional<ProyeccionesDTO> actualizarProyeccion(Integer id, ProyeccionesDTO detallesProyeccion) {
        // Buscar la proyección existente por su ID
        Optional<ProyeccionesDTO> proyeccionExistente = repositorioProyecciones.findById(id);

        if (proyeccionExistente.isPresent()) {
            ProyeccionesDTO proyeccion = proyeccionExistente.get();

            // Seteamos todos los campos (lógica de actualización PUT)
            proyeccion.setId_pelicula(detallesProyeccion.getId_pelicula());
            proyeccion.setId_sala(detallesProyeccion.getId_sala());
            proyeccion.setFecha_hora_inicio(detallesProyeccion.getFecha_hora_inicio());
            proyeccion.setFecha_hora_fin(detallesProyeccion.getFecha_hora_fin());
            proyeccion.setPrecio_entrada(detallesProyeccion.getPrecio_entrada());
            proyeccion.setAsientos_disponibles(detallesProyeccion.getAsientos_disponibles());

            // Guardar y devolver la proyección actualizada
            return Optional.of(repositorioProyecciones.save(proyeccion));
        } else {
            // No fue encontrada
            return Optional.empty();
        }
    }
}
