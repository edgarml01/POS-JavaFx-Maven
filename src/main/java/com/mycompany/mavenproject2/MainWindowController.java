package com.mycompany.mavenproject2;

import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

	private HashMap<Integer, Integer> carrito;

	@FXML
	private MFXButton adminButton;
	@FXML
	private ScrollPane productosScrollPane;
	@FXML
	private Label userLabel;

	@FXML
	private void switchToDashboard() throws IOException {
		App.setRoot("secondary", 1006,662);
		//jkStatService serv = new StatService();
	}

	@FXML
	private void buscarProductos() {
		productos = session.getMapper(ProductoMapper.class).getProductosByNombre(searchField.getText());
		updatePanelProductos();
		System.out.println(productos);
	}

	private void addProductoCarrito(int producto_id) {
		carrito.put(producto_id, carrito.getOrDefault(producto_id, 0) + 1);
	}

	private void updatePanelProductos() {
		try {
			productsFlowPane.getChildren().clear();
			for (Producto producto : productos) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("productoCard.fxml"));
				Parent itemNode = loader.load();
				ProductoCardController prodController = loader.getController();
				prodController.setModel(producto, this);
				productsFlowPane.getChildren().add(itemNode);
			}

		} catch (IOException ex) {
			System.out.println("Salio mal algo jaja" + ex.getMessage());
			Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
    @FXML
    private void logout() {
		try {
			App.setRoot("login",337,524);
			Session.setUser(null);
		} catch (IOException ex) {
			System.out.println("Algo sali mal con el logout");
			
		}
    }

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if (Session.getUser().getRol_id() != 1){
			adminButton.setVisible(false);
		}
		userLabel.setText(Session.getUser().getNombre());

		productsFlowPane.maxWidthProperty().bind(productosScrollPane.widthProperty());

		carrito = new HashMap<>();

		productos = session.getMapper(ProductoMapper.class).getAllProductos();
		updatePanelProductos();

	}

	public void hadleClick(Producto producto) {
		addProductoCarrito(producto.getId());
	}
}
