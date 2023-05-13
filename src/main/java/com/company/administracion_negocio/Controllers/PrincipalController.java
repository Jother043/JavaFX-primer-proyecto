package com.company.administracion_negocio.Controllers;

import com.company.administracion_negocio.Model.Customers;
import com.company.administracion_negocio.Principal;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PrincipalController {

    public Button BtnRemove;
    public Button btnRemoveClient;
    public TableColumn idColumnFirstName;
    public TableColumn idColumnNumber;
    public TableColumn idColumnLastName;
    public TableColumn idColumnPhone;
    public TableColumn idColumnAddressLine1;
    public TableColumn idColumnAddressLine2;
    public TableColumn idColumnCity;
    public TableColumn idColumnState;
    public TableColumn idColumnPostalCode;
    public TableColumn idColumnCountry;
    public TableColumn idColumnSalesRepEmployeeNumber;
    public TableColumn idColumnCreditLimit;
    public TextField nombreCliente;

    public void viewProduct() throws IOException {
        Principal principal = new Principal();
        principal.addProduct(new Stage());
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

    public void RemoveCliente(ActionEvent actionEvent) {
        Customers customers = new Customers();
        customers.seleccionarClienteBd(nombreCliente.getText());
    }
}