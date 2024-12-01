/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mappers;
import java.util.List;
import models.DetalleVenta;

/**
 *
 * @author egarm
 */
public interface DetalleVentaMapper{
    DetalleVenta getDetalleVentaById(int id);
    List<DetalleVenta> getAllDetalleVentas();
    void insertDetalleVenta(DetalleVenta detalleVenta);
    void updateDetalleVenta(DetalleVenta detalleVenta);
    void deleteDetalleVenta(int id);
}
