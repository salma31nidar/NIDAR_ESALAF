module com.example.esalaf1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.esalaf1 to javafx.fxml;
    exports com.example.esalaf1;
    exports models;

    opens  models to java.base;
    requires java.sql;


}