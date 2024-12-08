/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import repositories.SqliteConn;

/**
 *
 * @author egarm
 */
public class StatService {

	private Connection conn;

	public StatService() {
		this.conn = SqliteConn.connect();
	}

	public void closeConnectio() {
		try {
			this.conn.close();
		} catch (SQLException ex) {
			Logger.getLogger(StatService.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public StatService(Connection conn) {
		this.conn = conn;
	}

	public XYChart.Series<String, Number> obtenerDatosVentas() {
		String querry = "SELECT \n"
			+ "    p.nombre AS producto, \n"
			+ "    SUM(dv.cantidad) AS cantidad_vendida\n"
			+ "FROM \n"
			+ "    productos p\n"
			+ "JOIN \n"
			+ "    detallesVenta dv ON p.id = dv.producto_id\n"
			+ "GROUP BY \n"
			+ "    p.nombre\n"
			+ "ORDER BY \n"
			+ "    cantidad_vendida DESC;";
		XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
		dataSeries.setName("Productos más vendidos");

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(querry);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String producto = resultSet.getString("producto");
				int cantidadVendida = resultSet.getInt("cantidad_vendida");

				dataSeries.getData().add(new XYChart.Data<>(producto, cantidadVendida));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataSeries;
	}

	public XYChart.Series<String, Number> obtenerDatosVentasPorFecha() {
		String querry = "SELECT \n"
			+ "    DATE(v.fecha) AS fecha, \n"
			+ "    SUM(v.total) AS total_ventas \n"
			+ "FROM \n"
			+ "    ventas v \n"
			+ "GROUP BY \n"
			+ "    DATE(v.fecha) \n"
			+ "ORDER BY \n"
			+ "    fecha ASC;";  // Ordenado de forma ascendente por fecha

		XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
		dataSeries.setName("Ventas Totales por Fecha");

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(querry);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String fecha = resultSet.getString("fecha");
				double totalVentas = resultSet.getDouble("total_ventas");

				dataSeries.getData().add(new XYChart.Data<>(fecha, totalVentas));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataSeries;
	}

	public ObservableList<PieChart.Data> obtenerDatosParticipacionPorSegmento() {
		String querry = "SELECT \n"
			+ "    s.nombre AS segmento, \n"
			+ "    SUM(dv.cantidad) AS cantidad_vendida \n"
			+ "FROM \n"
			+ "    segmentos s \n"
			+ "JOIN \n"
			+ "    productos p ON s.id = p.segmento_id \n"
			+ "JOIN \n"
			+ "    detallesVenta dv ON p.id = dv.producto_id \n"
			+ "GROUP BY \n"
			+ "    s.nombre \n"
			+ "ORDER BY \n"
			+ "    cantidad_vendida DESC;";  // Ordena por la cantidad vendida

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(querry);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String segmento = resultSet.getString("segmento");
				int cantidadVendida = resultSet.getInt("cantidad_vendida");

				// Crear los datos para el gráfico de pastel
				pieChartData.add(new PieChart.Data(segmento, cantidadVendida));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pieChartData;
	}

}
