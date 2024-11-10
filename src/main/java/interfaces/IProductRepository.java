/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.sql.SQLException;
import java.util.List;
import models.Producto;

/**
 *
 * @author egarm
 */
public interface IProductRepository {
  void addProduct(Producto product);
    Producto findById(int id);
    List<Producto> getAllProducts();
    void updateProductStock(int id, int newStock);
    void deleteProduct(int id);
}
