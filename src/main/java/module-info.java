module com.company.administracion_negocio {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;

    opens com.company.administracion_negocio to javafx.fxml;
    exports com.company.administracion_negocio;

}