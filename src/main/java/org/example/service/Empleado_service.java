package org.example.service;
import org.example.models.EmpleadoDTO;
import org.springframework.stereotype.Service;
import org.example.repository.Repositorio_empleado;
import java.util.Optional; // Necesitamos esto para verificar si existe
import java.util.List;
import org.bson.Document;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.example.ConnectionMongo;
@Service

public class Empleado_service {

    private final Repositorio_empleado repositorio_empleado;

    public Empleado_service(Repositorio_empleado repositorio_empleado) {
        this.repositorio_empleado = repositorio_empleado;
    }
    // Añadir
    public EmpleadoDTO crearEmpleado(EmpleadoDTO empleado) {

        EmpleadoDTO empleadoGuardado = repositorio_empleado.save(empleado);

        try {
            // Conexión
            MongoDatabase db = ConnectionMongo.getConnectionMongo();
            MongoCollection<Document> collection = db.getCollection("empleados");

            Document doc = new Document()
                    .append("id_empleado", empleadoGuardado.getId_empleado()) // ID generado por SQL
                    .append("nombre", empleadoGuardado.getNombre())
                    .append("email", empleadoGuardado.getEmail())
                    .append("puesto", empleadoGuardado.getPuesto())
                    .append("tipoJornada", empleadoGuardado.getTipo_jornada())
                    .append("telefono", empleadoGuardado.getTelefono())
                    .append("fechaContratacion", empleadoGuardado.getFecha_contratacion())
                    .append("salarioHora", empleadoGuardado.getSalario_hora())
                    .append("activo", empleadoGuardado.getEstado());

            // Insertar
            collection.insertOne(doc);
            System.out.println(" [MONGO] Copia de seguridad guardada para: " + empleadoGuardado.getNombre());

        } catch (Exception e) {
            // Si falla Mongo, solo imprimimos el error
            System.err.println("⚠️ Error guardando en Mongo: " + e.getMessage());
        }

        return empleadoGuardado;
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
        // Buscar el empleado existente
        Optional<EmpleadoDTO> empleadoExistente = repositorio_empleado.findById(id);

        if (empleadoExistente.isPresent()) {
            EmpleadoDTO empleado = empleadoExistente.get();

            // Actualizar los campos
            // Aquí se actualizan solo los campos modificados
            empleado.setNombre(detallesEmpleado.getNombre());
            empleado.setPuesto(detallesEmpleado.getPuesto());
            empleado.setTipo_jornada(detallesEmpleado.getTipo_jornada());
            empleado.setEmail(detallesEmpleado.getEmail());
            empleado.setTelefono(detallesEmpleado.getTelefono());
            empleado.setFecha_contratacion(detallesEmpleado.getFecha_contratacion());
            empleado.setSalario_hora(detallesEmpleado.getSalario_hora());
            empleado.setEstado(detallesEmpleado.isEstado());

            // Guardar los cambios
            return Optional.of(repositorio_empleado.save(empleado));
        } else {
            return Optional.empty();
        }
    }
}
