package com.company.administracion_negocio.Controllers;

import com.company.administracion_negocio.Conection.DataBaseConnection;
import com.company.administracion_negocio.Model.Customers;
import com.company.administracion_negocio.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddClienteController implements Initializable {

    @FXML
    public ChoiceBox boxEmpleyes;
    @FXML
    private TextField idNombreCliente;
    @FXML
    private TextField idPrimerApellido;
    @FXML
    private TextField idSegundoApellido;
    @FXML
    private TextField idNumeroTelefono;
    @FXML
    private TextField idDirection;
    @FXML
    private TextField idDireccion2;
    @FXML
    private TextField idCiudad;
    @FXML
    private TextField idCodigoPostal;
    @FXML
    private TextField idPais;
    @FXML
    private TextField idEstado;
    @FXML
    private TextField idCredito;


    /**
     * Este método se encarga de llamar al método selectChoiceBox para rellenar el choiceBox con los datos de los
     * empleados.
     */
    private void selectChoiceBox() {
        //Creamo un try with resources para establecer la conexión con la base de datos y rellenar el choiceBox.
        try {
            Connection connection = DataBaseConnection.getInstance().getConexion();
            System.out.println("Conexión establecida selectChoiceBox");
            //Creamos un objeto de la clase Statement para poder ejecutar la consulta.
            Statement statement = connection.createStatement();
            //Ejecutamos la consulta y guardamos el resultado en un objeto de la clase ResultSet.
            ResultSet resultSet = statement.executeQuery("SELECT employeeNumber FROM employees");
            //Creamos un ArrayList para guardar los datos de los empleados.
            List<String> opcionesEmpleados = new ArrayList<>();
            //Recorremos el resultado de la consulta y guardamos los datos en el ArrayList.
            while (resultSet.next()) {
                opcionesEmpleados.add(resultSet.getString("employeeNumber"));
            }
            //Rellenamos el choiceBox con los datos del ArrayList.
            boxEmpleyes.getItems().addAll(opcionesEmpleados);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Este método se encarga de llamar al método insertarCliente de la clase Customers para insertar un nuevo cliente
     * en la base de datos con los datos que se han introducido en los campos de texto.
     *
     * @param actionEvent
     */
    @FXML
    private void insertarCleinte(ActionEvent actionEvent) {
        //Creamos un objeto de la clase Customers.
        Customers customer = new Customers();
        Principal principal = new Principal();
        //Comprobamos que no haya campos vacíos.
         if(validarCamposVacios()) {
             //Llamamos al método insertarCliente de la clase Customers.
             customer.insertarCliente(customer, idNombreCliente.getText(), idPrimerApellido.getText(), idSegundoApellido.getText(),
                     idNumeroTelefono.getText(), idDirection.getText(), idDireccion2.getText(), idCiudad.getText(), idEstado.getText(),
                     idCodigoPostal.getText(), idPais.getText(), selectEmployees(), Double.parseDouble(idCredito.getText()));
             //Limpiamos los campos de texto.
             onClickClearField();
         }
    }

    @FXML
    private void returnToMainView(ActionEvent actionEvent) {
        Principal principal = new Principal();
        try {
            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
            principal.start(new Stage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Este método se encarga de llamar al método initialize de la interfaz Initializable.
     * Este método se utiliza para inicializar el choiceBox con los datos de los empleados.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectChoiceBox();
    }

    /**
     * Este método se encarga de devolver el valor seleccionado del choiceBox.
     *
     * @return Integer
     */
    public Integer selectEmployees() {
        //Devolvemos el valor seleccionado del choiceBox.
        return Integer.parseInt(boxEmpleyes.getValue().toString());
    }

    /**
     * Este método se encarga de limpiar los campos de texto del formulario una vez se ha insertado un nuevo cliente.
     */
    public void onClickClearField() {
        idNombreCliente.clear();
        idPrimerApellido.clear();
        idSegundoApellido.clear();
        idNumeroTelefono.clear();
        idDirection.clear();
        idDireccion2.clear();
        idCiudad.clear();
        idEstado.clear();
        idCodigoPostal.clear();
        idPais.clear();
        idCredito.clear();
    }

    public boolean validarCamposVacios() {
        //Creamos una variable booleana para comprobar si hay campos vacíos o los datos introducidos son correctos.
        boolean validacion = false;
        //Creamos un objeto de la clase Principal para poder llamar a los métodos de dicha clase.
        Principal principal = new Principal();
        if (idNombreCliente.getText().isEmpty() || idPrimerApellido.getText().isEmpty() ||
                idNumeroTelefono.getText().isEmpty() || idDirection.getText().isEmpty() ||
                idCiudad.getText().isEmpty() || idEstado.getText().isEmpty() || idCodigoPostal.getText().isEmpty() ||
                idPais.getText().isEmpty() || selectEmployees().toString().isEmpty() || idCredito.getText().isEmpty()) {
            principal.errorObtenerClientes("Error", "No puede haber campos vacíos");

        }
        if (idCredito.getText().matches("[a-zA-Z]+")) {
            principal.errorObtenerClientes("Error", "El campo crédito no puede contener letras");
        } else if (idNumeroTelefono.getText().matches("[a-zA-Z]+")) {
            principal.errorObtenerClientes("Error", "El campo número de teléfono no puede contener letras");

        } else if (idCodigoPostal.getText().matches("[a-zA-Z]+")) {
            principal.errorObtenerClientes("Error", "El campo código postal no puede contener letras");

        } else if (idNombreCliente.getText().matches("[0-9]+")) {
            principal.errorObtenerClientes("Error", "El campo nombre no puede contener números");

        } else if (idPrimerApellido.getText().matches("[0-9]+")) {
            principal.errorObtenerClientes("Error", "El campo primer apellido no puede contener números");

        } else if (idSegundoApellido.getText().matches("[0-9]+")) {
            principal.errorObtenerClientes("Error", "El campo segundo apellido no puede contener números");

        } else if (idDirection.getText().matches("[0-9]+")) {
            principal.errorObtenerClientes("Error", "El campo dirección no puede contener números");

        } else if (idDireccion2.getText().matches("[0-9]+")) {
            principal.errorObtenerClientes("Error", "El campo dirección 2 no puede contener números");

        } else if (idCiudad.getText().matches("[0-9]+")) {
            principal.errorObtenerClientes("Error", "El campo ciudad no puede contener números");

        } else if (idEstado.getText().matches("[0-9]+")) {
            principal.errorObtenerClientes("Error", "El campo estado no puede contener números");

        } else if (idPais.getText().matches("[0-9]+")) {
            principal.errorObtenerClientes("Error", "El campo país no puede contener números");

        }else {
            validacion = true;
        }

        return validacion;
    }
}
