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
import mappers.VentaMapper;
import models.User;
import models.Venta;

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
	       System.out.println("Something went wrong with validations support " + e.getMessage());
	  }
     }

     @FXML
     private void switchToSecondary() throws IOException {
	  try {
	       User u = usersMapper.validateUser(textlbl.getText());
	       if (u != null && BCrypt.checkpw(pswLbl.getText(), u.getPassword())) {
		    Session.setUser(u);
		    App.setRoot("mainWindow",918,600);
	       System.out.println("Todo correcto");
	       } else {
		    invalidationLabel.setVisible(true);
	       }
	  } catch (Exception ex) {
		  System.out.println("Algo salio mal ");
	       System.out.println(ex.getMessage());
	       ex.printStackTrace();
	  }
     }
}
