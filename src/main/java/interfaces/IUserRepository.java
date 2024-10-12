/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.sql.SQLException;
import models.User;

/**
 *
 * @author egarm
 */
public interface IUserRepository {
    User getById(int id) throws SQLException;
    void add(User user) throws SQLException;
    void update(User user) throws SQLException;
    void delete(int id) throws SQLException;
}
