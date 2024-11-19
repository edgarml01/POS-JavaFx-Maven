module com.mycompany.mavenproject2 {
    requires java.sql; // Agrega esta línea
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires commons.validator;
    requires java.base;
    requires com.jfoenix;
    requires org.controlsfx.controls;
    
    
    opens com.mycompany.mavenproject2 to javafx.fxml;
    exports com.mycompany.mavenproject2;
    //provides io.ebean.config.EntityClassRegister with EbeanEntityRegister;
}
