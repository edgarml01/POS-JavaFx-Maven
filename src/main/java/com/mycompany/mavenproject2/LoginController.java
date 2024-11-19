package com.mycompany.mavenproject2;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import repositories.SqliteConn;
import javafx.scene.control.Label;
import org.apache.commons.validator.routines.IntegerValidator;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import repositories.UserRepository;
import repositories.VentaRepository;

public class LoginController implements Initializable {

    private SqliteConn slql;
    private UserRepository ur;
    private VentaRepository venta;

    @FXML
    private MFXPasswordField pswLbl;

    @FXML
    private MFXTextField textlbl;

    @FXML
    private Label name;
    
    @FXML
    private Button primaryButton;
    
    @FXML
    private Label invalidationLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        slql = new SqliteConn();
        ur = new UserRepository(slql);
        venta = new VentaRepository(slql);
        ValidationSupport vl = new ValidationSupport();
        
        vl.registerValidator(textlbl, Validator.createEmptyValidator("Ingresa tu usuario"));
        vl.registerValidator(pswLbl, Validator.createEmptyValidator("Ingresa la contraseña"));
        primaryButton.disableProperty().bind(vl.invalidProperty());
        
    }

    @FXML
    private void switchToSecondary() throws IOException {
        boolean userflag = true;
        boolean pwdflag = true;
        
        IntegerValidator integerValidator = IntegerValidator.getInstance();
        if (!integerValidator.isValid(textlbl.getText())) {
            System.out.println("Es un entero");
       
        }
         if (textlbl.getText().trim().isEmpty()) {
            // Aplica un estilo para marcar el campo como inválido
             textlbl.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
             userflag = false;
        } else {
            textlbl.setStyle(null);
        }
         if (pswLbl.getText().trim().isEmpty()) {
            // Aplica un estilo para marcar el campo como inválido
             pswLbl.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
             pwdflag = false;
        } else {
            pswLbl.setStyle(null);
        }
         
        if (userflag && pwdflag )try {
            if (ur.loginWithCredentials(textlbl.getText(), pswLbl.getText()) != null) {
                App.setRoot("mainWindow" );
            }else {
                invalidationLabel.setVisible(true);
                System.out.println("credenciales equivocadas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

    

}
