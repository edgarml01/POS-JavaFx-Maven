/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author egarm
 */
public class User {

    private int id;
    private String nombre;
    private String password;
    private int rol_id;

    // Constructor, getters y setters
    public User (){};
    public User(int id, String username, String password) {
        this.id = id;
        this.nombre = username;
        this.password = password;
        
    }

    public User( String username, String password, int role) {
        this.nombre = username;
        this.password = password;
        this.rol_id = role;
    }
    
    public User(int id, String username,  int role) {
        this.id = id;
        this.nombre = username;
        this.rol_id = role;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getRol_id() { return rol_id; }
    public void setRol_id(int rol_id) { this.rol_id = rol_id; }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + nombre + ", password=" + password + ", role=" + rol_id + '}';
    }
    
}
