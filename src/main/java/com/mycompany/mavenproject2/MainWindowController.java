package com.mycompany.mavenproject2;

import Services.StatService;
import java.io.IOException;
import javafx.fxml.FXML;

public class MainWindowController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
        StatService serv = new StatService();
        
    }
}