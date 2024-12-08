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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mappers.ProductoMapper;
import mappers.UserMapper;
import models.User;
import org.apache.ibatis.session.SqlSession;

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
	private TableColumn<User, Integer> colRol;
	@FXML
	private TableColumn<User, Void> colActions;

	private final ObservableList<User> userList = FXCollections.observableArrayList();

	private SqlSession session;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colRol.setCellValueFactory(new PropertyValueFactory<>("rol_id"));
		colRol.setCellFactory(colum -> new TableCell<User, Integer>() {
				@Override
				protected void updateItem(Integer rol_id, boolean empty) {
					super.updateItem(rol_id, empty);
					 if (empty || rol_id == null) {
					setText(null);
				    } else {
					setText(rol_id == 1 ? "Administrador" : "Empleado");
				    }
				}

			});
		colActions.setCellFactory(colum -> new TableCell<User, Void>(){
				   private final Button btnEdit = new Button("Edit");
				    private final Button btnDelete = new Button("Delete");
				    private final HBox actionBox = new HBox(10, btnEdit, btnDelete);
				    {
				      btnEdit.setOnAction(event -> {
					    User user = getTableView().getItems().get(getIndex());
					      showFormularioMod(user);
					    System.out.println("Editando: " + user.getNombre());
					});

					btnDelete.setOnAction(event -> {
					    User producto = getTableView().getItems().get(getIndex());
					    System.out.println("Eliminando: " + producto.getNombre());
					     Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
					    confirmDialog.setTitle("Confirmación de eliminación");
					    confirmDialog.setHeaderText("¿Estás seguro de que deseas eliminar este recurso?");
					    confirmDialog.setContentText("Usuario: " + producto.getNombre());

					    // Agregar botones personalizados
					    ButtonType btnYes = new ButtonType("Sí", ButtonBar.ButtonData.YES);
					    ButtonType btnNo = new ButtonType("No", ButtonBar.ButtonData.NO);
					    confirmDialog.getButtonTypes().setAll(btnYes, btnNo);

					    // Esperar por la respuesta del usuario
					    confirmDialog.showAndWait().ifPresent(response -> {
						if (response == btnYes) {
						    // Aquí elimina el recurso de la lista y actualiza la tabla
						    getTableView().getItems().remove(producto);
						    userList.remove(producto);
						    Session.getSQLSession().getMapper(UserMapper.class).deleteUser(producto.getId());
						    Session.getSQLSession().commit();
						    System.out.println("Usuario eliminado: " + producto.getNombre());
						}
					    });
					});
			
				    }
				    @Override
				    protected void updateItem(Void item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
					    setGraphic(null);
					} else {
					    setGraphic(actionBox);
					}
				    }
			
			});
		session = Session.getSQLSession();

		userList.addAll(session.getMapper(UserMapper.class).getAllUsers());

		userTable.setItems(userList);

	}

	@FXML
	private void showFormulario() {
		try {
			// Cargar el archivo FXML
			FXMLLoader loader = new FXMLLoader(App.class.getResource("userForm.fxml"));
			Parent root = loader.load();

			UserFormController us = loader.getController();
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

			if (us.getUser() != null) {
				session.commit();
				session.getMapper(UserMapper.class).insertUser(us.getUser());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private void showFormularioMod(User user) {
		try {
			// Cargar el archivo FXML
			FXMLLoader loader = new FXMLLoader(App.class.getResource("userForm.fxml"));
			Parent root = loader.load();

			UserFormController us = loader.getController();
			// Crear una nueva ventana (Stage)
			Stage stage = new Stage();
			stage.setTitle("Formulario de Usuarios");
			stage.setScene(new Scene(root));
			stage.setResizable(false);

			// Configurar la modalidad (opcional)
			stage.initModality(Modality.APPLICATION_MODAL); // Bloquea la ventana principal mientras está abierta
			stage.setResizable(false);

			UserFormController controller = loader.getController();
			controller.setUser(user);
			// Mostrar la ventana
			stage.showAndWait(); // Espera hasta que se cierre la ventana

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
