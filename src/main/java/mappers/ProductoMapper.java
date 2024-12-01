/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mappers;
import java.util.List;
import models.Producto;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author egarm
 */
public interface ProductoMapper {
    List<Producto> getAllProductos();
    Producto getProductoById();
    Producto insertProducto(Producto producto);
    void updateProducto(Producto producto);
    void deleteProducto(int id);
    void updateStock(@Param("id") int id, @Param("stock") int stock);
}
