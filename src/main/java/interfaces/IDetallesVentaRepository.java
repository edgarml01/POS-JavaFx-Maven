/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import models.DetalleVenta;

/**
 *
 * @author egarm
 */
public interface IDetallesVentaRepository {
    void addDetalleVenta(DetalleVenta detalleVenta);
    List<DetalleVenta> getDetallesByVentaId(int ventaId);
}
