/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author egarm
 */
public class Venta {
    private int id;
    private double total; 
    private LocalDateTime fecha;
    private List<DetalleVenta> detalles;
    
    public Venta(){}

    public Venta(int id, double total, LocalDateTime fecha) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
    }

    public Venta( double total) {
        this.total = total;
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
        return this.detalles;
    }

	public void setDetalles(List<DetalleVenta> detalles) {
		this.detalles = detalles;
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

	@Override
	public String toString() {
		return "Venta{" + "id=" + id + ", total=" + total + ", fecha=" + fecha + ", detallesVentas=" + detalles+ '}';
	}
    

}
