/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;
import models.Venta;

/**
 *
 * @author egarm
 */
public interface IVentaRepostitory {
    void addVenta(Venta venta);
    Venta getVentaById(int id);
    List<Venta> getAllVentas();
}
