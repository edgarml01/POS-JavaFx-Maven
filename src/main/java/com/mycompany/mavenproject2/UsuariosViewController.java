/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject2;

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
import mappers.UserMapper;
import models.User;
/**
 * FXML Controller class
 *
 * @author egarm
 */
public class UsuariosViewController implements Initializable {


	@FXML
	private TableView<User> userTable;
	@FXML
	private TableColumn<?, ?> colId;
	@FXML
	private TableColumn<?, ?> colNombre;
	@FXML
	private TableColumn<?, ?> colRol;
	    private final ObservableList <User> userList = FXCollections.observableArrayList();
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
			colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colRol.setCellValueFactory(new PropertyValueFactory<>("rol_id"));

	System.out.println(userList);
	userList.addAll(MyBatisUtil.getSession().getMapper(UserMapper.class).getAllUsers());

	userTable.setItems(userList);

	}	
		@FXML
	private void showFormulario() {
		try {
			// Cargar el archivo FXML
			FXMLLoader loader = new FXMLLoader(App.class.getResource("userForm.fxml"));
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
