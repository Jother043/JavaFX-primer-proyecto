package com.company.administracion_negocio;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {


    protected void onHelloButtonClick2() {

    }


    public void viewProduct(ActionEvent actionEvent) throws IOException {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.addProduct(new Stage());
    }

    public void onByeButtonClick(ActionEvent actionEvent) {
        /*
        *Node source pertenece a la clase javafx.scene.Node y es el nodo que ha generado el evento.
        * Utilizamos el método getSource() para obtener el nodo que ha generado el evento.
        * Stage stage pertenece a la clase javafx.stage.Stage y es la ventana que contiene el nodo que ha generado el evento.
        * Utilizamos el método getScene() para obtener la escena que contiene el nodo que ha generado el evento.
        * (es decir me devuelve la ventana donde se encuentra el nodo que ha generado el evento).
         */
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void addProduct(ActionEvent actionEvent) throws IOException {

    }
}