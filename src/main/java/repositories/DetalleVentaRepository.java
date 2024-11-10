package repositories;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.DetalleVenta;

public class DetalleVentaRepository {
    private final Connection connection;

    public DetalleVentaRepository(Connection connection) {
        this.connection = connection;
    }

    // Obtener todos los detalles de una venta específica
    public List<DetalleVenta> findByVentaId(int ventaId) throws SQLException {
        String query = "SELECT * FROM detallesVenta WHERE venta_id = ?";
        List<DetalleVenta> detallesVentas = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ventaId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DetalleVenta detalleVenta = new DetalleVenta(
                    resultSet.getInt("id"),
                    resultSet.getInt("venta_id"),
                    resultSet.getInt("producto_id"),
                    resultSet.getInt("cantidad"),
                    resultSet.getDouble("precio_unitario")
                );
                detallesVentas.add(detalleVenta);
            }
        }
        return detallesVentas;
    }
}
