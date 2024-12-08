package com.mycompany.mavenproject2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        scene = new Scene(loadFXML("login"));
        stage.setScene(scene);
	Image icon = new Image(App.class.getResourceAsStream("/icon.png"));
	stage.setTitle("POS CONALEP");
	mainStage = stage;

	stage.getIcons().add(icon);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));

    }

    static Stage getMainStage(){
	    return  mainStage;
    }

    static void setRoot(String fxml, double width, double height) throws IOException {
    // Cargar el nuevo nodo raíz
    Parent root = loadFXML(fxml);

    // Establecer el nuevo nodo raíz en la escena
    scene.setRoot(root);

    if (width > 0 && height > 0) {
        scene.getWindow().setWidth(width);
        scene.getWindow().setHeight(height);
    }
}

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();

	return root;
	
    }
    

    public static void main(String[] args) {
        launch();
    }

}
