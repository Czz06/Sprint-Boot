package org.example.service;
import org.example.models.EmpleadoDTO;
import org.springframework.stereotype.Service;
import org.example.repository.Repositorio_empleado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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


}


//import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class Empleado_service {
//    private static final String URL = "jdbc:postgresql://localhost:5432/cine";
//    private static final String USER = "postgres";
//    private static final String PASSWORD = "postgres";
//    Connection connection = null;
//        public void insertarEmpleado(Empleado e) throws SQLException {
//            try {
//                connection = DriverManager.getConnection(URL, USER, PASSWORD);
//                 PreparedStatement ps = connection.prepareStatement(
//                         "INSERT INTO empleados(nombre, puesto, tipo_jornada, email, telefono, fecha_contratacion, salario_hora, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"); {
//                ps.setString(1, e.getNombre());
//                ps.setString(2, e.getPuesto());
//                ps.setString(3, e.getTipo_jornada());
//                ps.setString(4, e.getEmail());
//                ps.setString(5, e.getTelefono());
//                ps.setDate(6, e.getFecha_contratacion());
//                ps.setDouble(7, e.getSalario_hora());
//                ps.setBoolean(8, e.isEstado());
//                ps.executeUpdate();
//            }
//            } catch (Exception ex) {
//
//                throw new RuntimeException(ex);
//            }
//        }
//        public void eliminarEmpleadoByID(Empleado e) throws SQLException {
//        String sql = "DELETE FROM empleados WHERE id_empleado = ?";
//
//        try {
//            connection = DriverManager.getConnection(URL, USER, PASSWORD);
//
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, e.getId_empleado());
//
//            int filasAfectadas = ps.executeUpdate();
//
//            if (filasAfectadas == 0) {
//                System.out.println("No se encontró el empleado con con el mismo id");
//            } else {
//                System.out.println("Empleado eliminado correctamente.");
//            }
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//    public void actualizarEmpleado(int id_empleado, Empleado e) throws SQLException {
//        String sql = "UPDATE empleados SET nombre = ?, puesto = ?, tipo_jornada = ?, email = ?, telefono = ?, fecha_contratacion = ?, salario_hora = ?, activo = ? WHERE id_empleado = ?";
//
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//
//            ps.setString(1, e.getNombre());  // nombre
//            ps.setString(2, e.getPuesto());  // puesto
//            ps.setString(3, e.getTipo_jornada());  // tipo_jornada
//            ps.setString(4, e.getEmail());  // email
//            ps.setString(5, e.getTelefono());  // telefono
//            ps.setDate(6, e.getFecha_contratacion());  // fecha_contratacion
//            ps.setDouble(7, e.getSalario_hora());  // salario_hora
//            ps.setBoolean(8, e.isEstado());  // activo
//            ps.setInt(9, id_empleado);  // id_empleado para el WHERE
//
//            // Ejecutar la actualización
//            int filas = ps.executeUpdate();
//
//            if (filas > 0) {
//                System.out.println("Empleado actualizado correctamente.");
//            } else {
//                System.out.println("No existe un empleado con el mismo Id.");
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            throw new RuntimeException("Error al actualizar el empleado", ex);
//        }
//    }
//
//}


