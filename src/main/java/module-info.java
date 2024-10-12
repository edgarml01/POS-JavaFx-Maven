module com.mycompany.mavenproject2 {
    requires java.sql; // Agrega esta línea
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    opens com.mycompany.mavenproject2 to javafx.fxml;
    exports com.mycompany.mavenproject2;
}
