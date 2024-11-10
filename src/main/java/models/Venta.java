/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import repositories.DetalleVentaRepository;

/**
 *
 * @author egarm
 */
public class Venta {
    private int id;
    private double total; 
    private LocalDateTime fecha;
    private List<DetalleVenta> detallesVentas  = null;

    public Venta(int id, double total, LocalDateTime fecha) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }
    
    public List<DetalleVenta> getDetalles() {
        return this.detallesVentas;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
     public List<DetalleVenta> cargarDetalles(Connection connection) throws SQLException {
        if (detallesVentas == null) {
            DetalleVentaRepository detalleVentaRepo = new DetalleVentaRepository(connection);
            this.detallesVentas = detalleVentaRepo.findByVentaId(this.id);
        }
        return detallesVentas;
    }

}
