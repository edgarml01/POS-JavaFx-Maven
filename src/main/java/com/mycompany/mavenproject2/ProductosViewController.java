/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject2;

import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Producto;
import mappers.ProductoMapper;

/**
 * FXML Controller class
 *
 * @author egarm
 */
public class ProductosViewController implements Initializable {
	
	@FXML
	private TableView<Producto> userTable;
	@FXML
	private TableColumn<?, ?> colId;
	@FXML
	private TableColumn<?, ?> colNombre;
	@FXML
	private TableColumn<?, ?> colPrecio;
	@FXML
	private TableColumn<?, ?> colCosto;
	@FXML
	private TableColumn<?, ?> colSegmento;
	@FXML
	private TableColumn<?, ?> colActions;
	private final ObservableList<Producto> userList = FXCollections.observableArrayList();
	@FXML
	private MFXButton addProductoButton;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		colCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
		colSegmento.setCellValueFactory(new PropertyValueFactory<>("segmento_id"));
		
		System.out.println(userList);
		if (userList.isEmpty()) {
			userList.addAll(MyBatisUtil.getSession().getMapper(ProductoMapper.class).getAllProductos());
		}
		
		userTable.setItems(userList);
		
	}	
	
	@FXML
	private void showFormulario() {
		try {
			// Cargar el archivo FXML
			FXMLLoader loader = new FXMLLoader(App.class.getResource("productoForm.fxml"));
			Parent root = loader.load();

			// Crear una nueva ventana (Stage)
			Stage stage = new Stage();
			stage.setTitle("Formulario de Producto");
			stage.setScene(new Scene(root));
			stage.setResizable(false);

			// Configurar la modalidad (opcional)
			stage.initModality(Modality.APPLICATION_MODAL); // Bloquea la ventana principal mientras está abierta
			stage.setResizable(false);

			// Mostrar la ventana
			stage.showAndWait(); // Espera hasta que se cierre la ventana
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
