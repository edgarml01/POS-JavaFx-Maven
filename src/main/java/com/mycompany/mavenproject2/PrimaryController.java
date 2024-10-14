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
import models.User;
import repositories.SqliteConn;
import javafx.scene.control.Label;
import models.Producto;
import repositories.UserRepository;

public class PrimaryController implements Initializable {

    private SqliteConn slql;
    private UserRepository ur;

    @FXML
    private MFXPasswordField pswLbl;

    @FXML
    private MFXTextField textlbl;

    @FXML
    private Label name;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        slql = new SqliteConn();
        ur = new UserRepository(slql);
    }

    @FXML
    private void switchToSecondary() throws IOException {
        
     

        try {
            if (ur.loginWithCredentials(textlbl.getText(), pswLbl.getText()) != null) {
                App.setRoot("mainW");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void switchToSecondary2() throws IOException {
        // App.setRoot("secondary");
    }

}
