package com.company.administracion_negocio;

import com.company.administracion_negocio.Conection.DataBaseConnection;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Principal extends Application {


    /**
     * Este método inicia la aplicación. En este caso abre la ventana principal.
     *
     * @param stage
     */
    public void start(Stage stage) throws IOException {
        //Hacemos que la ventana sea de un tamaño fijo y no se pueda redimensionar.
        stage.setResizable(false);
        //Creamos un objeto de la clase FXMLLoader.
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("principal-view.fxml")));
        root.setStyle("-fx-background-color: #B5B2B2");
        root.setPrefSize(300, 300);
        AnchorPane.setTopAnchor(root, AnchorPane.USE_COMPUTED_SIZE);
        AnchorPane.setBottomAnchor(root, AnchorPane.USE_COMPUTED_SIZE);
        AnchorPane.setLeftAnchor(root, AnchorPane.USE_COMPUTED_SIZE);
        AnchorPane.setRightAnchor(root, AnchorPane.USE_COMPUTED_SIZE);
        Scene scene = new Scene(root);
        stage.setTitle("Administración de Negocio");
        stage.setScene(scene);
        stage.show();
    }

    /*
     * Este método abre una nueva ventana para agregar productos a la base de datos. Tenemos que tener en cuenta que el
     * archivo remove-cliente.fxml debe tener referenciada la clase donde se encuentra el método del controlador que se
     * encarga abrir la ventana.
     * (fx:controller="com.company.administración_negocio. HelloController)
     * si no lo ponemos nos da error en el método Start de la clase HelloApplication.
     */
    public void addProduct(Stage stage) throws IOException {

        stage.setResizable(false);
        System.out.println("El archivo es " + Principal.class.getResource("remove-cliente.fxml"));
        FXMLLoader fxmlLoader2 = new FXMLLoader(Principal.class.getResource("remove-cliente.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 1320, 680);
        stage.setTitle("Borrar cliente");
        stage.setScene(scene2);
        stage.show();

    }

    /**
     * Este método abre una nueva ventana para agregar clientes a la base de datos. Tenemos que tener en cuenta que el
     * archivo add-cliente.fxml debe tener referenciada la clase donde se encuentra el método del controlador que se
     * encarga abrir la ventana.
     * (fx:controller="com.company.administración_negocio. HelloController)
     * si no lo ponemos nos da error en el método Start de la clase HelloApplication.
     */
    public void openViewAddCustomer(Stage stage) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader(Principal.class.getResource("Add_Cliente.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 700, 400);
            stage.setTitle("Insertar cliente");
            stage.setScene(scene);

            // Insertar la imagen en el formulario
            ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource("/com/company/administracion_negocio/Img/44562.png")).toExternalForm());
            imageView.setFitHeight(70);
            imageView.setFitWidth(70);
            StackPane imageContainer = new StackPane();
            imageContainer.getChildren().add(imageView);
            // Puedes ajustar la posición de la imagen en el formulario utilizando los métodos de posicionamiento de nodos en el StackPane
            imageContainer.setLayoutX(475);
            imageContainer.setLayoutY(264);
            // Obtener el contenedor del formulario desde el FXMLLoader
            AnchorPane formContainer = (AnchorPane) fxmlLoader.getNamespace().get("formContainer");
            // Agregar el contenedor de la imagen al contenedor del formulario
            formContainer.getChildren().add(imageContainer);

            stage.show();
    }

    /**
     * Este método abre un dialogo de error cuando no se puede obtener la lista de clientes de la base de datos.
     *
     * @param info1
     * @param info2
     */
    @FXML
    public void errorObtenerClientes(String info1, String info2) {
        // Creamos la alerta de error.
        Alert alert = new Alert(Alert.AlertType.ERROR);
        // Le damos un título
        alert.setTitle("Error");
        // Le damos un encabezado
        alert.setHeaderText(info1);
        // Le damos un contenido
        alert.setContentText(info2);

        // Mostrar la alerta
        alert.showAndWait();
    }

    /**
     * Este método abre un dialogo de error cuando no se puede borrar un cliente de la base de datos.
     *
     * @param info1
     * @param info2
     */
    @FXML
    public void alertaBorrarCliente(String info1, String info2) {

        // Creamos la alerta de información.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        // Le damos un título
        alert.setTitle("Información");
        // Le damos un encabezado
        alert.setHeaderText(info1);
        // Le damos un contenido
        alert.setContentText(info2);
        // Mostrar la alerta
        alert.showAndWait();

    }

    public static void main(String[] args) {
        launch();
    }
}