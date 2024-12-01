package mappers;

import java.util.List;
import models.User;
import org.apache.ibatis.annotations.Param;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author egarm
 */
public interface UserMapper {
    User getUserById(int id);
    List<User> getAllUsers();
    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    User validateUser(@Param("nombre") String nombre );
}
