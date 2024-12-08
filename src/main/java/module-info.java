module com.mycompany.mavenproject2 {
    requires java.sql; // Agrega esta línea
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires commons.validator;
    requires java.base;
    requires com.jfoenix;
    requires jbcrypt;
    requires org.controlsfx.controls;
    requires org.mybatis;
    requires org.apache.commons.csv;
    opens com.mycompany.mavenproject2 to javafx.fxml;
    exports com.mycompany.mavenproject2;
    exports models;
    //provides io.ebean.config.EntityClassRegister with EbeanEntityRegister;
}
