/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject2;

import Services.StatService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
/**
 * FXML Controller class
 *
 * @author egarm
 */
public class StatsViewController implements Initializable {


	@FXML
	private BarChart<String, Number> graficaBarra;
	@FXML
	private HBox root;
	@FXML
	private LineChart<String, Number> graficaLinea;
	@FXML
	private PieChart graficaPay;
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		StatService sv  = new StatService();
		graficaBarra.getData().add(sv.obtenerDatosVentas());
		graficaLinea.getData().add(sv.obtenerDatosVentasPorFecha());
		graficaPay.setData(sv.obtenerDatosParticipacionPorSegmento());
		
		// TODO
	}	
	
}
