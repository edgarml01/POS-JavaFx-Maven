/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject2;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import models.Producto;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author egarm
 */
public class ProductoFormController implements Initializable {

	private Producto producto;
	@FXML
	private MFXTextField nombreTextField;
	@FXML
	private MFXTextField precioTextField;
	@FXML
	private MFXTextField costoTextField;
	@FXML
	private MFXComboBox<String> segmentoBox;
	@FXML
	private MFXTextField stockTextField;
	@FXML
	private MFXButton aceptarButton;

	private String s1 = "Abarrotera";

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		ValidationSupport vl = new ValidationSupport();
		vl.registerValidator(nombreTextField, Validator.createEmptyValidator("Ingresa el nombre del producto"));

		vl.registerValidator(stockTextField,
			Validator.createRegexValidator("Debe ser un numero entero", "\\d+", Severity.ERROR));

		vl.registerValidator(costoTextField,
			Validator.createRegexValidator("Debe ser un número decimal", "\\d*\\.?\\d+", Severity.ERROR));

		vl.registerValidator(precioTextField,
			Validator.createRegexValidator("Debe ser un número decimal", "\\d*\\.?\\d+", Severity.ERROR));
		aceptarButton.disableProperty().bind(vl.invalidProperty());
		ObservableList<String> opciones = FXCollections.observableArrayList(
			"Abarrotera",
			"Papeleria"
		);
		segmentoBox.setItems(opciones);

		// Opcional: Establecer un valor por defecto
		segmentoBox.selectFirst();

	}

}
