package com.mycompany.mavenproject2;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.apache.ibatis.session.SqlSession;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.mindrot.jbcrypt.BCrypt;
import mappers.UserMapper;
import models.User;

public class LoginController implements Initializable {
	private UserMapper usersMapper;


    @FXML
    protected MFXPasswordField pswLbl;

    @FXML
    protected MFXTextField textlbl;

    @FXML
    private Label name;
    
    @FXML
    private Button primaryButton;
    
    @FXML
    private Label invalidationLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ValidationSupport vl = new ValidationSupport();

	    try {
	     SqlSession session = MyBatisUtil.getSession();
	     usersMapper = session.getMapper(UserMapper.class);

	    } catch (Exception e) {
		    System.out.println(e.getMessage());
	    }

        try {
            vl.registerValidator(textlbl, Validator.createEmptyValidator("Ingresa tu usuario"));
            vl.registerValidator(pswLbl, Validator.createEmptyValidator("Ingresa la contraseña"));
            primaryButton.disableProperty().bind(vl.invalidProperty());
        } catch (Exception e) {
            System.out.println("Algo salio mal " + e.getMessage());
        }

        
    }

    @FXML
    private void switchToSecondary() throws IOException {
        boolean userflag = true;
        boolean pwdflag = true;
        
        if (userflag && pwdflag )try {
	    User u = usersMapper.validateUser(textlbl.getText());
            if ( u != null && BCrypt.checkpw(pswLbl.getText(), u.getPassword())) {
                App.setRoot("mainWindow" );
		Session.setUser(u);
            }else {
                invalidationLabel.setVisible(true);
                System.out.println("credenciales equivocadas");
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

    

}
