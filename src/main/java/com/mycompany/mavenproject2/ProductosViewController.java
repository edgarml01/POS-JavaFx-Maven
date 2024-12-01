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
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	if (userList.isEmpty())userList.addAll(MyBatisUtil.getSession().getMapper(ProductoMapper.class).getAllProductos());

	userTable.setItems(userList);

	}	
	
}
