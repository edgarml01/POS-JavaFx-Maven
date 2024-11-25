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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
/**
 * FXML Controller class
 *
 * @author egarm
 */
public class SecondaryController implements Initializable {

    @FXML
    private JFXButton btnProducts;
    
    @FXML
    private Button secondaryButton;
    
    @FXML
    private JFXDrawer drawler;
    
    private Boolean b = true;
    
    @FXML
    private JFXHamburger hamburger;
    
    @FXML
    private BorderPane mainPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initDrawler();
    }    
    
    @FXML
    private void switchToPrimary(ActionEvent event) {
        HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
        task.setRate(1);
        task.play();
    }
    
    
    @FXML
    private void cargarUsuariosView(ActionEvent event) {
        try {
            loadFXMLIntoCenter("usuariosView");
            System.out.println("Cambiando a la vista corespondiente");
        } catch (Exception e) {
            System.out.println("Error al cargar el archivo FXML: " + e.getMessage());
        }
        
    }
    
    @FXML
    private void cargarProdcutosView(ActionEvent event) {
        try {
            loadFXMLIntoCenter("productosView");
            System.out.println("Cambiando a la vista corespondiente");
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

}