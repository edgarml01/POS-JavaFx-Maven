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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

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

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		ValidationSupport vl = new ValidationSupport();
		vl.registerValidator(nombreTextField, Validator.createEmptyValidator("Ingresa el nombre del usuario"));

		vl.registerValidator(passwordField, Validator.createEmptyValidator("Ingresa la contraseña del usuario"));

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

}
