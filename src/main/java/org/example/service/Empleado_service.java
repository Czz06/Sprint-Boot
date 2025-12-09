package org.example.service;
import org.example.models.EmpleadoDTO;
import org.springframework.stereotype.Service;
import org.example.repository.Repositorio_empleado;
import java.util.Optional; // Necesitamos esto para verificar si existe
import java.util.List;

@Service

public class Empleado_service {

    private final Repositorio_empleado repositorio_empleado;

    public Empleado_service(Repositorio_empleado repositorio_empleado) {
        this.repositorio_empleado = repositorio_empleado;
    }

    public EmpleadoDTO crearEmpleado(EmpleadoDTO empleado) {
        return repositorio_empleado.save(empleado);
    }

    public List<EmpleadoDTO> findAll() {
        return repositorio_empleado.findAll();
    }
    public boolean eliminarEmpleado(Long id) {

        Optional<EmpleadoDTO> empleado = repositorio_empleado.findById(id);

        if (empleado.isPresent()) {
            repositorio_empleado.deleteById(id);
            return true;
        } else {
            return false; // El empleado no fue encontrado
        }
    }
    public Optional<EmpleadoDTO> actualizarEmpleado(Long id, EmpleadoDTO detallesEmpleado) {
        // 1. Buscar el empleado existente
        Optional<EmpleadoDTO> empleadoExistente = repositorio_empleado.findById(id);

        if (empleadoExistente.isPresent()) {
            EmpleadoDTO empleado = empleadoExistente.get();

            // 2. Actualizar los campos
            // Nota: Aquí se actualizan solo los campos que se desean modificar
            empleado.setNombre(detallesEmpleado.getNombre());
            empleado.setPuesto(detallesEmpleado.getPuesto());
            empleado.setTipo_jornada(detallesEmpleado.getTipo_jornada());
            empleado.setEmail(detallesEmpleado.getEmail());
            empleado.setTelefono(detallesEmpleado.getTelefono());
            empleado.setFecha_contratacion(detallesEmpleado.getFecha_contratacion());
            empleado.setSalario_hora(detallesEmpleado.getSalario_hora());
            empleado.setEstado(detallesEmpleado.isEstado());

            // 3. Guardar (Update) el empleado modificado
            return Optional.of(repositorio_empleado.save(empleado));
        } else {
            // El empleado con ese ID no fue encontrado
            return Optional.empty();
        }
    }
}
