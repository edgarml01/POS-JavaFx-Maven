package com.mycompany.mavenproject2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import repositories.ProductoRepository;
import repositories.SqliteConn;
import repositories.VentaRepository;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        SqliteConn sql = new SqliteConn();
        ProductoRepository pr = new ProductoRepository(sql);
        VentaRepository vr = new VentaRepository(sql);
        System.out.println(vr.findAll());
        System.out.println(pr.getAllProducts());
        
        scene = new Scene(loadFXML("secondary"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
