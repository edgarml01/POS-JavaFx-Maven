/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.SQLException;
import repositories.ProductoRepository;
import repositories.SqliteConn;

/**
 *
 * @author egarm
 */
public class DetalleVenta {
    private int id;
    private int venta_id;
    private int producto_id;
    private int cantidad;
    private double precio_venta;
    private Producto producto;

    private boolean productoCargado = false; // Para lazy loading del producto

    public DetalleVenta(int id, int ventaId, int productoId, int cantidad, double precioUnitario) {
        this.id = id;
        this.venta_id = ventaId;
        this.producto_id = productoId;
        this.cantidad = cantidad;
        this.precio_venta = precioUnitario;
    }
    public DetalleVenta(){}
    public DetalleVenta( int ventaId, int productoId, int cantidad, double precioUnitario) {
        this.venta_id = ventaId;
        this.producto_id = productoId;
        this.cantidad = cantidad;
        this.precio_venta = precioUnitario;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVenta_id() {
        return venta_id;
    }

    public void setVenta_id(int venta_id) {
        this.venta_id = venta_id;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "DetalleVenta{" + "id=" + id + ", ventaId=" + venta_id + ", productoId=" + producto_id + ", cantidad=" + cantidad + ", precio_venta=" + precio_venta + ", =" + ", productoCargado=" + producto+ '}';
	}

    // Lazy loading del producto
}
