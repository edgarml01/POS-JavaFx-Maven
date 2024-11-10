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
    private int stock;
    private double precio;
    private double costo;
    private Segmento segmento; 
    private int segmentoId; 

    public Producto(){}
    
    public Producto(int id, String name, int stock, int segmentoId, double precio, double costo) {
        this.id = id;
        this.nombre = name;
        this.stock = stock;
        this.precio = precio;
        this.costo = costo;
        this.segmentoId = segmentoId;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSegmentoId() {
        return segmentoId;
    }

    public void setSegmentoId(int segmentoId) {
        this.segmentoId = segmentoId;
    }

}
