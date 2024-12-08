package com.mycompany.mavenproject2;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import models.Producto;
import mappers.ProductoMapper;
import mappers.VentaMapper;
import mappers.DetalleVentaMapper;
import models.Venta;
import models.DetalleVenta;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;

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
	private double res = 0;
	
	private SqlSession session = Session.getSQLSession();
	
	private List<Producto> productos;
	
	private ObservableList<Producto> carrito;
	
	private Producto selectedProducto;
	
	private String cantidadStr = "";
	
	private boolean fistCantidad = true;
	
	private final VBox notificationContainer = new VBox(10); // Contenedor para las notificaciones
	private final List<HBox> notifications = new ArrayList<>();	
	
	private HashMap<Integer, Integer> productosConCantidad = new HashMap<>();
	
	private final PopOver notificationPopOver = new PopOver(); // PopOver para mostrar las notificaciones

	@FXML
	private MFXButton adminButton;
	@FXML
	private ScrollPane productosScrollPane;
	@FXML
	private Label userLabel;
	@FXML
	private JFXListView<Producto> carritoListView;
	@FXML
	private Label instructionLabel;
	@FXML
	private MFXButton button6;
	@FXML
	private MFXButton button5;
	@FXML
	private MFXButton button2;
	@FXML
	private MFXButton button9;
	@FXML
	private MFXButton buttonAdd;
	@FXML
	private MFXButton button0;
	@FXML
	private MFXButton buttonDel;
	@FXML
	private MFXButton button1;
	@FXML
	private MFXButton logoutButton;
	@FXML
	private Label cantidadLabel;
	@FXML
	private MFXButton finalizarButton;
	@FXML
	private MFXButton eliminarButton;
	@FXML
	private MFXButton notificationButton;
	
	@FXML
	private void switchToDashboard() throws IOException {
		App.setRoot("secondary", 1006, 662);
		//jkStatService serv = new StatService();
	}
	
	@FXML
	private void buscarProductos() {
		productos = session.getMapper(ProductoMapper.class).getProductosByNombre(searchField.getText());
		updatePanelProductos();
		System.out.println(productos);
	}
	
	private void addProductoCarrito(int producto_id) {
		//carrito.put(producto_id, carrito.getOrDefault(producto_id, 0) + 1);
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
			System.out.println("Salio mal algo" + ex.getMessage());
			Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
	@FXML
	private void logout() {
		try {
			App.setRoot("login", 337, 524);
			Session.setUser(null);
		} catch (IOException ex) {
			System.out.println("Algo sali mal con el logout");
			
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if (Session.getUser().getRol_id() != 1) {
			adminButton.setVisible(false);
		}
		userLabel.setText(Session.getUser().getNombre());
		
		productsFlowPane.maxWidthProperty().bind(productosScrollPane.widthProperty());
		carrito = FXCollections.observableArrayList();
		carritoListView.setItems(carrito);
		
		carritoListView.setCellFactory(listView -> new JFXListCell<Producto>() {
			@Override
			protected void updateItem(Producto producto, boolean empty) {
				super.updateItem(producto, empty);
				if (producto != null) {
					int cantidad = productosConCantidad.get(producto.getId());
					// Define el texto o diseño que se mostrará
					setText(producto.getNombre() + " x " + cantidad);
					
				}
				
			}
		});
		productos = session.getMapper(ProductoMapper.class).getAllProductos();
		updatePanelProductos();
		
	}

	@FXML
	private void toggleNotificationPopOver() {
		if (notificationPopOver.isShowing()) {
			notificationPopOver.hide();
		} else {
			notificationPopOver.show(notificationButton); // Mostrar el PopOver anclado al botón
		}
	}

	private void addNotification(String message) {
		// Crear una notificación individual
		Label label = new Label(message);
		
		HBox notification = new HBox(10, label);
		notification.setStyle("-fx-background-color: #ffffff; -fx-padding: 5; -fx-border-color: #ccc;");
		notification.setAlignment(Pos.CENTER);
		
		notifications.add(notification); // Agregar a la lista
		notificationContainer.getChildren().add(notification); // Mostrar en el contenedor
	}
	
	@FXML
	private void finalizarVenta() {
		if (carrito.isEmpty()){
			return;
		}
		try {
			Venta v = new Venta(calcularTotal());
			session.getMapper(VentaMapper.class).insertVenta(v);
			
			double totalAux = calcularTotal();
			for (Producto producto : carrito) {
				int cantidad = productosConCantidad.get(producto.getId());
				int stockRestante = producto.getStock() - cantidad;
				DetalleVenta dv = new DetalleVenta(v.getId(), producto.getId(), cantidad, producto.getPrecio());
				session.getMapper(ProductoMapper.class).updateStock(producto.getId(), stockRestante);
				session.getMapper(DetalleVentaMapper.class).insertDetalleVenta(dv);
				if (stockRestante <= 10) {
					alertaStock(producto, stockRestante);
				}
			}
			session.commit();
			
			productos = session.getMapper(ProductoMapper.class).getAllProductos();
			updatePanelProductos();
			carrito.clear();
			productosConCantidad.clear();
			totalLabel.setText("Total $ 0 ");
			
		} catch (Exception e) {
			System.out.println("Algo sali mal con la finalizacion de la venta");
			System.out.println(e.getMessage());
		}
	}
	
	private void alertaStock(Producto producto, int stock) {
		Notifications.create()
			.title("Alerta de stock")
			.text("Queda muy poco stock de " + producto.getNombre() + " quedan " + stock + " unidades.")
			.showWarning();
	}
	
	@FXML
	private void deleteFromCarrito() {
		Producto p = carritoListView.getSelectionModel().getSelectedItem();
		for (int i = 0; i < carrito.size(); i++) {
			if (carrito.get(i).getId() == p.getId()) {
				carrito.remove(i);
				productosConCantidad.remove(p.getId());
			}
		}
		totalLabel.setText("Total $ " + calcularTotal());
	}
	
	private double calcularTotal() {
		res = 0;
		for (Producto producto : carrito) {
			int cantidad = productosConCantidad.get(producto.getId());
			res += cantidad * producto.getPrecio();
		}
		
		return res;
	}
	
	@FXML
	private void addProducto() {
		if (selectedProducto == null) {
			System.out.println("Producto nulo");
			return;
		}
		
		if ((!cantidadStr.isEmpty() && Integer.parseInt(cantidadStr) > selectedProducto.getStock()) || (cantidadStr.isEmpty() && selectedProducto.getStock() == 0)) {
			Notifications.create()
				.title("Stock Insuficiente")
				.text("No hay suficiente stock de " + selectedProducto.getNombre())
				.showInformation();
			cantidadStr = "";
			fistCantidad = true;
			instructionLabel.setText("Seleccione un producto");
			cantidadLabel.setText("");
			
			return;
		}
		
		if (productosConCantidad.containsKey(selectedProducto.getId())) {
			System.out.println("El producto ya existe");
			// Si el producto ya existe, actualizamos la cantidad
			int cantidadActual = productosConCantidad.get(selectedProducto.getId());
			productosConCantidad.put(selectedProducto.getId(), cantidadActual + Integer.parseInt(!fistCantidad ? cantidadStr : "1"));
			carritoListView.refresh();
		} else {
			System.out.println("El producto no existe en el carrito");
			// Si el producto no existe, lo agregamos al map con la cantidad
			productosConCantidad.put(selectedProducto.getId(), Integer.parseInt(!fistCantidad ? cantidadStr : "1"));
			productosConCantidad.put(selectedProducto.getId(), Integer.parseInt(!fistCantidad ? cantidadStr : "1"));
			carrito.add(selectedProducto);
		}
		totalLabel.setText("Total $" + calcularTotal());
		selectedProducto = null;
		fistCantidad = true;
		instructionLabel.setText("Seleccione un producto");
		cantidadLabel.setText(" ");
		cantidadStr = "";
	}
	
	public void hadleClick(Producto producto) {
		selectedProducto = producto;
		instructionLabel.setText("Ingrese la cantidad del producto");
		cantidadLabel.setText("Cantidad: " + 1);
	}
	
	@FXML
	private void button1() {
		fistCantidad = false;
		cantidadStr += 1;
		cantidadLabel.setText("Cantidad: " + cantidadStr);
	}
	
	@FXML
	private void button2() {
		fistCantidad = false;
		cantidadStr += 2;
		cantidadLabel.setText("Cantidad: " + cantidadStr);
	}
	
	@FXML
	private void button3() {
		fistCantidad = false;
		cantidadStr += 3;
		cantidadLabel.setText("Cantidad: " + cantidadStr);
	}
	
	@FXML
	private void button4() {
		fistCantidad = false;
		cantidadStr += 4;
		cantidadLabel.setText("Cantidad: " + cantidadStr);
	}
	
	@FXML
	private void button5() {
		fistCantidad = false;
		cantidadStr += 5;
		cantidadLabel.setText("Cantidad: " + cantidadStr);
	}
	
	@FXML
	private void button6() {
		fistCantidad = false;
		cantidadStr += 6;
		cantidadLabel.setText("Cantidad: " + cantidadStr);
	}
	
	@FXML
	private void button7() {
		fistCantidad = false;
		cantidadStr += 7;
		cantidadLabel.setText("Cantidad: " + cantidadStr);
	}
	
	@FXML
	private void button8() {
		fistCantidad = false;
		cantidadStr += 8;
		cantidadLabel.setText("Cantidad: " + cantidadStr);
	}
	
	@FXML
	private void button9() {
		fistCantidad = false;
		cantidadStr += 9;
		cantidadLabel.setText("Cantidad: " + cantidadStr);
	}
	
	@FXML
	private void button0() {
		cantidadStr += 0;
		fistCantidad = false;
		cantidadLabel.setText("Cantidad: " + cantidadStr);
	}
	
	@FXML
	private void buttonDel() {
		if (cantidadStr != null && cantidadStr.length() > 0) {
			// Eliminar el último carácter
			cantidadStr = cantidadStr.substring(0, cantidadStr.length() - 1);
		} else {
			System.out.println("La cadena está vacía o es nula.");
		}
		cantidadLabel.setText("Cantidad: " + cantidadStr);
	}
	
}
