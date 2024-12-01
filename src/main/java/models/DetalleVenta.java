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
    private int ventaId;
    private int productoId;
    private int cantidad;
    private double precio_venta;

    private Producto producto; // Producto se cargará de forma diferida
    private boolean productoCargado = false; // Para lazy loading del producto

    public DetalleVenta(int id, int ventaId, int productoId, int cantidad, double precioUnitario) {
        this.id = id;
        this.ventaId = ventaId;
        this.productoId = productoId;
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

    public int getVentaId() {
        return ventaId;
    }

    public void setVentaId(int ventaId) {
        this.ventaId = ventaId;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
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

	@Override
	public String toString() {
		return "DetalleVenta{" + "id=" + id + ", ventaId=" + ventaId + ", productoId=" + productoId + ", cantidad=" + cantidad + ", precio_venta=" + precio_venta + ", producto=" + producto + ", productoCargado=" + productoCargado + '}';
	}

    // Lazy loading del producto
    public Producto getProducto(SqliteConn connection) throws SQLException {
        if (!productoCargado) {
            ProductoRepository productoRepo = new ProductoRepository(connection);
            this.producto = productoRepo.findById(this.productoId);
            productoCargado = true; // Marcar que el producto ha sido cargado
        }
        return producto;
    }
}
