/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject2;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import models.User;
import mappers.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author egarm
 */
public class UserFormController implements Initializable {

	@FXML
	private CustomTextField nombreTextField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private MFXComboBox<String> rolBox;
	@FXML
	private MFXButton aceptarButton;

	private User user;
	private ValidationSupport vl ; 
	@FXML
	private Label passwordLa;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		vl = new ValidationSupport();
		vl.registerValidator(nombreTextField, Validator.createEmptyValidator("Ingresa el nombre del usuario"));

		passwordLa.setVisible(false);

		ObservableList<String> opciones = FXCollections.observableArrayList(
			"Administrador",
			"Empleado"
		);
		rolBox.setItems(opciones);

		// Opcional: Establecer un valor por defecto
		rolBox.selectFirst();

		aceptarButton.disableProperty().bind(vl.invalidProperty());
		System.out.println("Configuracion terminada");
	}

	@FXML
	public void saveUser(ActionEvent event){
		int rol = rolBox.getSelectedIndex() + 1;
		if (passwordField.getText().isEmpty()){
			passwordLa.setVisible(true);
			return ;
		}
		user = new User(nombreTextField.getText(), BCrypt.hashpw(passwordField.getText(), BCrypt.gensalt()), rol);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.close(); // Cerrar la ventana
	}
	public void setUser(User user){
		
		System.out.println(user);
		this.user = user;
		nombreTextField.setText(user.getNombre());
		rolBox.selectIndex(user.getRol_id() - 1);
		aceptarButton.setOnAction(event -> {
			user.setNombre(nombreTextField.getText());
			if (!passwordField.getText().isEmpty()){
				String password = BCrypt.hashpw(passwordField.getText(), BCrypt.gensalt());
				user.setPassword(password);
			}
			int rol = rolBox.getSelectedIndex() + 1;
			user.setRol_id(rol);
			Session.getSQLSession().getMapper(UserMapper.class).updateUser(user);
			Session.getSQLSession().commit();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.close();
			
		
		
		});
	}

	public User getUser(){
		return user;
	}

}
