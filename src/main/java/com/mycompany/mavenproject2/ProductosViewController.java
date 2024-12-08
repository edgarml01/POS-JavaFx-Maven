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
	private TableColumn<Producto, Integer> colSegmento;
	@FXML
	private TableColumn<Producto, Integer> colStock;
	@FXML
	private TableColumn<Producto, Void> colActions;
	private final ObservableList<Producto> userList = FXCollections.observableArrayList();
	@FXML
	private MFXButton addProductoButton;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		try {
			if (userList.isEmpty()) {
				userList.addAll(Session.getSQLSession().getMapper(ProductoMapper.class).getAllProductos());
			}
			colId.setCellValueFactory(new PropertyValueFactory<>("id"));
			colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
			colCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
			colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
		        colSegmento.setCellValueFactory(new PropertyValueFactory<>("segmento_id"));
			colActions.setCellFactory(colum -> new TableCell<Producto, Void>(){
				   private final Button btnEdit = new Button("Edit");
				    private final Button btnDelete = new Button("Delete");
				    private final HBox actionBox = new HBox(10, btnEdit, btnDelete);
				    {
				      btnEdit.setOnAction(event -> {
					    Producto producto = getTableView().getItems().get(getIndex());
					      showFormularioMod(producto);
					    System.out.println("Editando: " + producto.getNombre());
					});

					btnDelete.setOnAction(event -> {
					    Producto producto = getTableView().getItems().get(getIndex());
					    Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
					    confirmDialog.setTitle("Confirmación de eliminación");
					    confirmDialog.setHeaderText("¿Estás seguro de que deseas eliminar este recurso?");
					    confirmDialog.setContentText("Producto: " + producto.getNombre());

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
						    Session.getSQLSession().getMapper(ProductoMapper.class).deleteProducto(producto.getId());
						    Session.getSQLSession().commit();
						    System.out.println("Producto eliminado: " + producto.getNombre());
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


			colSegmento.setCellFactory(colum -> new TableCell<Producto, Integer>() {
				@Override
				protected void updateItem(Integer segmento_id, boolean empty) {
					super.updateItem(segmento_id, empty);
					 if (empty || segmento_id == null) {
					setText(null);
				    } else {
					setText(segmento_id == 1 ? "Papelería" : "Abarrotes");
				    }
				}

			});


			userTable.setItems(userList);

		} catch (Exception e) {
			System.out.println("Eror en la carga de productos y no se por que ");
			System.out.println(e.getMessage());
		}

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
	private void showFormularioMod(Producto producto) {
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
			ProductoFormController controller = loader.getController();
			controller.setProducto(producto);

			// Mostrar la ventana
			stage.showAndWait(); // Espera hasta que se cierre la ventana
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
