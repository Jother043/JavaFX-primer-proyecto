package com.company.administracion_negocio.Controllers;

import com.company.administracion_negocio.Model.Customers;
import com.company.administracion_negocio.Principal;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrincipalController {

    public Button btnRemoveClient;

    @FXML
    public void viewProduct() {
        Principal principal = new Principal();
        try {
            principal.addProduct(new Stage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void onByeButtonClick(ActionEvent actionEvent) {
        /*
         *Node source pertenece a la clase javafx.scene.Node y es el nodo que ha generado el evento.
         * Utilizamos el método getSource() para obtener el nodo que ha generado el evento.
         * Stage pertenece a la clase javafx.stage.Stage y es la ventana que contiene el nodo que ha generado el evento.
         * Utilizamos el método getScene() para obtener la escena que contiene el nodo que ha generado el evento.
         * (es decir me devuelve la ventana donde se encuentra el nodo que ha generado el evento).
         */
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }





}
