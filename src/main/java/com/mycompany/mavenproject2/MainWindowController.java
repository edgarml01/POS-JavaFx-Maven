package com.mycompany.mavenproject2;

import Services.StatService;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import org.apache.ibatis.session.SqlSession;
import models.Producto;
import mappers.ProductoMapper;

public class MainWindowController implements Initializable {

	@FXML
	private VBox leftPane;
	@FXML
	private VBox centerPane;
	@FXML
	private TextField searchField;
	@FXML
	private MFXButton searchButton;
	@FXML
	private Label totalLabel;
	@FXML
	private FlowPane productsFlowPane;

	private SqlSession session = MyBatisUtil.getSession();

	private List<Producto> productos;
	@FXML
	private MFXButton adminButton;

	@FXML
	private void switchToDashboard() throws IOException {
		App.setRoot("secondary");
		//jkStatService serv = new StatService();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if (Session.getUser().getRol_id() != 1){
			adminButton.setVisible(false);
		}
		productos = session.getMapper(ProductoMapper.class).getAllProductos();
		for (Producto producto : productos) {
			Label label = new Label(producto.toString());
			productsFlowPane.getChildren().add(label);
		}

	}
}
