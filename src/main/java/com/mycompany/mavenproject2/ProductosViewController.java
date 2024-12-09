/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject2;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import models.Producto;
import mappers.ProductoMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * FXML Controller class
 *
 * @author egarm
 */
public class ProductosViewController implements Initializable {

	@FXML
	private TableView<Producto> userTable;
	@FXML
	private TableColumn<Producto, Integer> colId;
	@FXML
	private TableColumn<Producto, String> colNombre;
	@FXML
	private TableColumn<Producto, Double> colPrecio;
	@FXML
	private TableColumn<Producto, Double> colCosto;
	@FXML
	private TableColumn<Producto, Integer> colSegmento;
	@FXML
	private TableColumn<Producto, Integer> colStock;
	@FXML
	private TableColumn<Producto, Void> colActions;
	private final ObservableList<Producto> userList = FXCollections.observableArrayList();
	@FXML
	private MFXButton addProductoButton;
	@FXML
	private MFXTextField buscarField;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		try {
			System.out.println("trono1");
			if (userList.isEmpty()) {
				userList.addAll(Session.getSQLSession().getMapper(ProductoMapper.class).getAllProductos());
			}
			System.out.println("trono2");
			colId.setCellValueFactory(new PropertyValueFactory<>("id"));
			colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
			colCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
			colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
			colSegmento.setCellValueFactory(new PropertyValueFactory<>("segmento_id"));
			System.out.println("trono3");
			colActions.setCellFactory(colum -> new TableCell<Producto, Void>() {
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

			System.out.println("trono4");

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
			System.out.println("trono5");

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
			userList.clear();
			userList.addAll(Session.getSQLSession().getMapper(ProductoMapper.class).getAllProductos());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@FXML
	private void buscarProductos() {
		List<Producto>productos = Session.getSQLSession().getMapper(ProductoMapper.class).getProductosByNombre(buscarField.getText());
		userList.clear();
		userList.addAll(productos);
	}


	@FXML
	public void exportData() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Guardar archivo CSV");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo CSV", "*.csv"));
		File selectedFile = fileChooser.showSaveDialog(App.getMainStage());
		if (selectedFile != null) {
			// Escribir los datos en el archivo seleccionado
			try (FileWriter writer = new FileWriter(selectedFile); CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(
				userTable.getColumns().stream()
					.map(column -> column.getText())
					.toArray(String[]::new)))) {

				// Escribir las filas de datos
				for (var item : userTable.getItems()) {
					for (var column : userTable.getColumns()) {
						Object cellValue = column.getCellData(item);
						csvPrinter.print(cellValue != null ? cellValue : "");
					}
					csvPrinter.println(); // Nueva línea después de cada fila
				}

				System.out.println("CSV exportado exitosamente a: " + selectedFile.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Error al escribir el archivo CSV.");
			}
		} else {
			System.out.println("No se seleccionó ningún archivo.");
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
			userList.clear();
			userList.addAll(Session.getSQLSession().getMapper(ProductoMapper.class).getAllProductos());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
