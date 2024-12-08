/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject2;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.MasterDetailPane;
import models.Venta;
import models.DetalleVenta;
import models.Producto;
import mappers.VentaMapper;
import mappers.ProductoMapper;
import mappers.DetalleVentaMapper;

/**
 * FXML Controller class
 *
 * @author egarm
 */
public class VentasViewController implements Initializable {

	private MasterDetailPane masterPane;
	private ObservableList ventas = FXCollections.observableArrayList();
	private ObservableList detallesVentas = FXCollections.observableArrayList();
	@FXML
	private TableColumn<?, ?> colId;
	@FXML
	private TableColumn<?, ?> colTotal;
	@FXML
	private TableColumn<Venta, ?> colFecha;
	@FXML
	private TableColumn<DetalleVenta, Integer> colNombreProducto;
	@FXML
	private TableColumn<?, ?> colCantidad;
	@FXML
	private TableColumn<?, ?> colPrecioVenta;
	@FXML
	private TableView<Venta> ventasTable;
	@FXML
	private TableView<DetalleVenta> detallesTable;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODOrole 
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		ventas.addAll(Session.getSQLSession().getMapper(VentaMapper.class).getAllVentas());
		ventasTable.setItems(ventas);

		ventasTable.setOnMouseClicked(event -> {
			// Verifica que la fila no esté vacía
			Venta ventaSeleccionada = ventasTable.getSelectionModel().getSelectedItem();

			if (ventaSeleccionada != null) {
				detallesTable.getItems().clear();
				Venta ventaConDetalles = Session.getSQLSession().getMapper(VentaMapper.class).getVentaConDetalles(ventaSeleccionada.getId());
				detallesVentas.addAll(ventaConDetalles.getDetalles());
				colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
				colPrecioVenta.setCellValueFactory(new PropertyValueFactory<>("precio_venta"));
				colNombreProducto.setCellValueFactory(new PropertyValueFactory<>("producto_id"));
				colNombreProducto.setCellFactory(colum -> new TableCell<DetalleVenta, Integer>() {
					@Override
					protected void updateItem(Integer producto_id, boolean empty) {
						super.updateItem(producto_id, empty);
						if(empty || producto_id == null){
							setText(null);
						}else{
							Producto p = Session.getSQLSession().getMapper(ProductoMapper.class).getProductoById(producto_id);
							setText(p.getNombre());
						}

					}

				});
				detallesTable.setItems(detallesVentas);

				// Aquí puedes mostrar los detalles de la venta en otra parte de la interfaz
			}
		});
	}

}
