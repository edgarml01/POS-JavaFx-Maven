/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package models;

/**
 *
 * @author egarm
 */

public class Producto  {
    
    private int id;
    private String nombre;
    private double costo;
    private double precio;
    private int stock;
    private int segmento_id;  // Relación con Segmento
    //private Segmento segmento;

    // Constructor, getters y setters
    public Producto() {}

    public Producto(int id, String nombre, double costo, double precio, int stock, int segmento) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
        this.precio = precio;
        this.stock = stock;
        this.segmento_id = segmento;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

	public int getSegmento_id() {
		return segmento_id;
	}

	public void setSegmento_id(int segmento_id) {
		this.segmento_id = segmento_id;
	}

	@Override
	public String toString() {
		return "Producto{" + "id=" + id + ", nombre=" + nombre + ", costo=" + costo + ", precio=" + precio + ", stock=" + stock + ", segmento_id=" + segmento_id + '}';
	}
}
