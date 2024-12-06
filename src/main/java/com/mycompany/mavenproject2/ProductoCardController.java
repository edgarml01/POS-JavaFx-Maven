/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject2;

import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import models.Producto;

/**
 * FXML Controller class
 *
 * @author egarm
 */
public class ProductoCardController implements Initializable {

	@FXML
	private Label nombreLabel;
	@FXML
	private Label precioLabel;

	private Producto producto;
	@FXML
	private VBox maiCard;
	@FXML
	private MFXButton prodcutoButton;
	@FXML
	private Label stockLabel;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	
	
	public void setModel(Producto model, MainWindowController controller) {
		this.producto = model;
		prodcutoButton.setOnMouseClicked(
			(event) -> {
				controller.hadleClick(model);
			}
		
		);
		nombreLabel.setText("Nombre:" + model.getNombre());
		precioLabel.setText("Precio:" + model.getPrecio()+"");
		stockLabel.setText("Stock:" + model.getStock()+"");
	    }
}
