package com.company.administracion_negocio.Controllers;

import com.company.administracion_negocio.Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClienteController implements javafx.fxml.Initializable {

    @FXML
    public Button BtnUpdate;
    @FXML
    private TableView<Customers> table = new TableView<>();

    @FXML
    private TableColumn<Customers, Integer> customerNumber;

    @FXML
    private TableColumn<Customers, String> customerName;

    @FXML
    private TableColumn<Customers, String> contactLastName;

    @FXML
    private TableColumn<Customers, String> contactFirstName;

    @FXML
    private TableColumn<Customers, String> phone;

    @FXML
    private TableColumn<Customers, String> addressLine1;

    @FXML
    private TableColumn<Customers, String> addressLine2;

    @FXML
    private TableColumn<Customers, String> city;

    @FXML
    private TableColumn<Customers, String> state;

    @FXML
    private TableColumn<Customers, String> postalCode;

    @FXML
    private TableColumn<Customers, String> country;

    @FXML
    private TableColumn<Customers, Integer> salesRepEmployeeNumber;

    @FXML
    private TableColumn<Customers, Double> creditLimit;

    @FXML
    public Button BtnRemove;
    @FXML
    public TextField nombreCliente;


    /**
     * Método para obtener los clientes de la base de datos
     * @return ArrayList<Customers>
     */
    public ArrayList<Customers> obtenerClientes() {
        // Crear un objeto de tipo Customers
        Customers c = new Customers();
        // Obtener los clientes de la base de datos y guardarlos en un ArrayList.
        ArrayList<Customers> customersList = c.seleccionarClienteBd();
        return customersList;
    }

    /**
     * Método para cargar los datos de los clientes en la tabla
     * @param customersList
     */
    public void cargarDatos(ArrayList<Customers> customersList) {
        /* Crear un objeto de tipo ObservableList para guardar los datos de los clientes en la tabla de clientes.
         * Se utiliza el método FXCollections.observableArrayList() para crear un objeto de tipo ObservableList.
         * Se utiliza el método setItems() para establecer los datos de los clientes en la tabla de clientes.
         */
        ObservableList<Customers> observableList = FXCollections.observableArrayList(customersList);
        table.setItems(observableList);
    }

    /**
     * Método para inicializar la tabla de clientes
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Mostrar tabla de clientes
        mostrarTabla();
    }

    /**
     * Método para mostrar la tabla de clientes
     */
    public void mostrarTabla(){

        assert BtnRemove != null : "fx:id=\"BtnRemove\" was not injected: check your FXML file 'remove-cliente.fxml'.";
        customerNumber.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("customerNumber"));
        customerName.setCellValueFactory(new PropertyValueFactory<Customers, String>("customerName"));
        contactLastName.setCellValueFactory(new PropertyValueFactory<Customers, String>("contactLastName"));
        contactFirstName.setCellValueFactory(new PropertyValueFactory<Customers, String>("contactFirstName"));
        phone.setCellValueFactory(new PropertyValueFactory<Customers, String>("phone"));
        addressLine1.setCellValueFactory(new PropertyValueFactory<Customers, String>("addressLine1"));
        addressLine2.setCellValueFactory(new PropertyValueFactory<Customers, String>("addressLine2"));
        city.setCellValueFactory(new PropertyValueFactory<Customers, String>("city"));
        state.setCellValueFactory(new PropertyValueFactory<Customers, String>("state"));
        postalCode.setCellValueFactory(new PropertyValueFactory<Customers, String>("postalCode"));
        country.setCellValueFactory(new PropertyValueFactory<Customers, String>("country"));
        salesRepEmployeeNumber.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("salesRepEmployeeNumber"));
        creditLimit.setCellValueFactory(new PropertyValueFactory<Customers, Double>("creditLimit"));
        /*
         *Cargar datos en la tabla de clientes con el método cargarDatos() y el método obtenerClientes()
         * para obtener los clientes de la base de datos.
         */
        cargarDatos(obtenerClientes());

    }

    /**
     * Método para eliminar un cliente y todos sus pedidos, pagos y detalles de pedido de la base
     * de datos de la tabla customers y de las tablas orders, payments y orderdetails.
     * Se utiliza el método removeCustomer() de la clase Customers.
     * Se utiliza el método getNumber() de la clase Customers para obtener el número de cliente.
     * Se utiliza el método String.valueOf() para convertir el número de cliente a String.
     * Se utiliza el método getText() para obtener el texto del campo de texto nombreCliente.
     * Se utiliza el método removeCustomer() de la clase Customers para eliminar el cliente.
     * Se utiliza el método mostrarTabla() para actualizar la tabla de clientes.
     */
    public void RemoveCliente() {
        Customers c = new Customers();
        // Obtener el número de cliente
        c.getNumber(nombreCliente.getText());
        // Eliminar cliente
        c.removeCustomer(String.valueOf(c.getNumber(nombreCliente.getText())));
        // Actualizar tabla cliente automáticamente al eliminar cliente.
        mostrarTabla();
    }

    @FXML
    public void updateCliente() {
        //Actualizar tabla cliente.
        mostrarTabla();
    }
}
