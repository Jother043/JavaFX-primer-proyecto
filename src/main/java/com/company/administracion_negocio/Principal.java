package com.company.administracion_negocio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Principal extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println("El archivo es " + Principal.class.getResource("Principal-view.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Principal.class.getResource("Principal-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
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
        System.out.println("El archivo es " + Principal.class.getResource("remove-cliente.fxml"));
        FXMLLoader fxmlLoader2 = new FXMLLoader(Principal.class.getResource("remove-cliente.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 600, 500);
        stage.setTitle("Add Product!");
        stage.setScene(scene2);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}