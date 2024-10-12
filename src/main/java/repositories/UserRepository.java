/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import interfaces.IUserRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.User;

/**
 * Clase que se comunica el modelo de usuarios con la base de datos 
 * @author egarm
 */
public class UserRepository implements IUserRepository{
    private Connection conn ;
    
    public UserRepository(SqliteConn conn){
        this.conn = conn.connect();
    }
    /**
     * Metodo que devuelve true si las credenciales coniciden y devuelve false si las 
     * credenciales no coinciden 
     * @param String username nombre de usuario
     * @param String password contrasenia del usuario 
     * @retun Boolean
     */
    public User loginWithCredentials(String username, String password)throws SQLException{
        String query = "SELECT * FROM users WHERE name = ? and password = ?";
        PreparedStatement statement = this.conn.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

       if (resultSet.next()) {
            return new User(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("password")
                //resultSet.getString("role")
            );
        }
        return null;
    }

    @Override
    public User getById(int id) throws SQLException {
     String query = "SELECT * FROM users WHERE id = ?";
        PreparedStatement statement = this.conn.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return new User(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("password")
                //resultSet.getString("role")
            );
        }
        return null;}

    @Override
    public void add(User user) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User user) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
