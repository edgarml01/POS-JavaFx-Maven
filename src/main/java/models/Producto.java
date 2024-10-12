/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author egarm
 */
public class Producto {
    private int id;
    private String name;
    private int stock;
    private int segmentoId; // Relación con Segmento

    public Producto(int id, String name, int stock, int segmentoId) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.segmentoId = segmentoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
