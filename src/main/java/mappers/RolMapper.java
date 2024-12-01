/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mappers;
import java.util.List;
import models.Rol;

/**
 *
 * @author egarm
 */
public interface RolMapper {
    Rol getRolById(int id);
    List<Rol> getAllRoles();
    void deleteRol(int id);
}
