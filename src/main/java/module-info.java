module com.company.administracion_negocio {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.company.administracion_negocio to javafx.fxml;
    exports com.company.administracion_negocio;
    exports com.company.administracion_negocio.Controllers;
    opens com.company.administracion_negocio.Controllers to javafx.fxml;
    opens com.company.administracion_negocio.Model to javafx.base;
}