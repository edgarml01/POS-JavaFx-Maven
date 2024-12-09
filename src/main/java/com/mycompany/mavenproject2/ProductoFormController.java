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
import mappers.ProductoMapper;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author egarm
 */
public class ProductoFormController implements Initializable {

	private Producto producto = null;
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
		System.out.println(producto);

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
			"Papeleria",
			"Abarrotera"
		);
		segmentoBox.setItems(opciones);

		// Opcional: Establecer un valor por defecto
		segmentoBox.selectFirst();

	}

	@FXML
	public void saveProducto(ActionEvent event){
		producto = new Producto( 
			nombreTextField.getText(), 
			Double.parseDouble(costoTextField.getText()), 
			Double.parseDouble(precioTextField.getText()),
			Integer.parseInt(stockTextField.getText()),
			segmentoBox.getSelectedIndex() + 1
		);
		Session.getSQLSession().getMapper(ProductoMapper.class).insertProducto(producto);
		Session.getSQLSession().commit();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.close(); // Cerrar la ventana
	}
	@FXML
	public void cerrarForm(ActionEvent event){
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	stage.close(); // Cerrar la ventana
	}

	public void setProducto (Producto producto) {
		this.producto = producto;
		if (producto != null){
			nombreTextField.setText(producto.getNombre());
			precioTextField.setText(producto.getPrecio() + "");
			costoTextField.setText(producto.getCosto()+ "");
			stockTextField.setText(producto.getStock() + "");
			segmentoBox.selectIndex(producto.getSegmento_id() - 1 );
			aceptarButton.setOnAction(event -> {
				double costo = Double.parseDouble(costoTextField.getText()) ;
				double precio = Double.parseDouble(precioTextField.getText());
				int stock = Integer.parseInt(stockTextField.getText());
				int segmento = segmentoBox.getSelectedIndex() + 1;
				this.producto.setCosto(costo);
				this.producto.setPrecio(precio);
				this.producto.setStock(stock);
				this.producto.setSegmento_id(segmento);
				this.producto.setNombre(nombreTextField.getText());
				Session.getSQLSession().getMapper(ProductoMapper.class).updateProducto(this.producto);
				Session.getSQLSession().commit();
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				stage.close();
			});
		}
	}

}
