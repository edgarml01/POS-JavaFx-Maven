/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import interfaces.IProductRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Producto;

/**
 *
 * @author egarm
 */
public class ProductoRepository implements IProductRepository {
    
    private Connection connection;
    
    public ProductoRepository(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void addProduct(Producto product) {
         String query = "INSERT INTO productos (name, stock, segmento_id) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getStock());
            statement.setInt(3, product.getSegmentoId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Producto findById(int id) {
         String query = "SELECT * FROM productos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Producto(rs.getInt("id"), 
                        rs.getString("name"), 
                        rs.getInt("stock"), 
                        rs.getInt("segmento_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Producto> getAllProducts() {
        List<Producto> products = new ArrayList<>();
        String query = "SELECT * FROM productos";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                products.add(new Producto(rs.getInt("id"), rs.getString("name"), rs.getInt("stock"), rs.getInt("segmento_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void updateProductStock(int id, int newStock) {
        String query = "UPDATE productos SET stock = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, newStock);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int id) {
         String query = "DELETE FROM productos WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}
