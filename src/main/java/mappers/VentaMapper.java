/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mappers;
import java.util.List;
import models.Venta;
import models.DetalleVenta;

/**
 *
 * @author egarm
 */
public interface VentaMapper {
    Venta getVentaById(int id);
    List<Venta> getAllVentas();
    Venta getVentaConDetalles(int id);
    List<DetalleVenta> cargarDetalles();
    void insertVenta(Venta venta);
    void updateVenta(Venta venta);
    void deleteVenta(int id);
}
