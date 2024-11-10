/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Venta;

/**
 *
 * @author egarm
 */
public class VentaRepository {
    private final Connection connection;

    public VentaRepository(SqliteConn connection) {
        this.connection = connection.connect();
    }

    // Obtener todas las ventas
    public List<Venta> findAll() throws SQLException {
        String query = "SELECT * FROM ventas";
        List<Venta> ventas = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Venta venta = new Venta(
                    resultSet.getInt("id"),
                    resultSet.getDouble("total"),
                    resultSet.getTimestamp("fecha").toLocalDateTime()
                );
                ventas.add(venta);
            }
        }
        return ventas;
    }

    // Guardar una nueva venta
    public void save(Venta venta) throws SQLException {
        String query = "INSERT INTO ventas (total) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, venta.getTotal());
            statement.executeUpdate();
        }
    }

    // Obtener una venta por ID
    public Venta findById(int id) throws SQLException {
        String query = "SELECT * FROM ventas WHERE id = ?";
        Venta venta = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                venta = new Venta(
                    resultSet.getInt("id"),
                    resultSet.getDouble("total"),
                    resultSet.getTimestamp("fecha").toLocalDateTime()
                );
            }
        }
        return venta;
    }
}
