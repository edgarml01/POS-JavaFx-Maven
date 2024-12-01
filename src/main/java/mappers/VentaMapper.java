/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mappers;
import java.util.List;
import models.Venta;

/**
 *
 * @author egarm
 */
public interface VentaMapper {
    Venta getVentaById(int id);
    List<Venta> getAllVentas();
    void insertVenta(Venta venta);
    void updateVenta(Venta venta);
    void deleteVenta(int id);
}
