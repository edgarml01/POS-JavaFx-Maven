/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.mavenproject2;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
/**
 * FXML Controller class
 *
 * @author egarm
 */
public class SecondaryController implements Initializable {

    @FXML
    private JFXButton btnProducts;
    
    
    private Boolean b = true;
    
    
    @FXML
    private BorderPane mainPane;
	@FXML
	private Label userLabel;
	@FXML
	private JFXButton logoutButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	    userLabel.setText(Session.getUser().getNombre());
            loadFXMLIntoCenter("productosView");
    }    
    
    @FXML
    private void switchToPrimary(ActionEvent event) {

	    try {
		    App.setRoot("mainWindow",918,600);
	    } catch (IOException ex) {
		    System.out.println("Algo sali mal con el logout");
	    }
    }
    
    @FXML
    private void cargarStatsView(ActionEvent event) {
        try {
            loadFXMLIntoCenter("statsView");
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo FXML: " + e.getMessage());
        }
        
    }
    
    @FXML
    private void cargarUsuariosView(ActionEvent event) {
        try {
            loadFXMLIntoCenter("usuariosView");
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo FXML: " + e.getMessage());
        }
        
    }
   @FXML
   private void productView(){
        try {
            loadFXMLIntoCenter("productosView");
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo FXML: " + e.getMessage());
        }
   
   }
    @FXML
    private void cargarProdcutosView() {
        
    }
    @FXML
    private void cargarVentasView(ActionEvent event) {
        try {
            loadFXMLIntoCenter("ventasView");
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo FXML: " + e.getMessage());
        }
        
    }
    private void loadFXMLIntoCenter(String fxmlFile) {
        try {
            // Carga el archivo FXML
            FXMLLoader loader = new FXMLLoader(App.class.getResource(fxmlFile+".fxml"));
            Parent newView = loader.load();

            // Reemplaza el contenido en el centro del BorderPane
            mainPane.setCenter(newView);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar el archivo FXML: " + fxmlFile);
        }
    }


    private void initDrawler() {
        
    }

	@FXML
	private void cargarProdcutosView(MouseEvent event) {
	}

}
