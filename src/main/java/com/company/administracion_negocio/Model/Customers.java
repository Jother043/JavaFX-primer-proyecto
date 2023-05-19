package com.company.administracion_negocio.Model;

import com.company.administracion_negocio.Conection.DataBaseConnection;
import com.company.administracion_negocio.Principal;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Customers {

    private int customerNumber;
    private String customerName;
    private String contactLastName;
    private String contactFirstName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private int salesRepEmployeeNumber;
    private double creditLimit;

    //Constructor
    public Customers(int customerNumber, String customerName, String contactLastName, String contactFirstName, String phone, String addressLine1, String addressLine2, String city, String state, String postalCode, String country, int salesRepEmployeeNumber, double creditLimit) {

        this.customerNumber = new SimpleIntegerProperty(customerNumber).get();
        this.customerName = new SimpleStringProperty(customerName).get();
        this.contactLastName = new SimpleStringProperty(contactLastName).get();
        this.contactFirstName = new SimpleStringProperty(contactFirstName).get();
        this.phone = new SimpleStringProperty(phone).get();
        this.addressLine1 = new SimpleStringProperty(addressLine1).get();
        this.addressLine2 = new SimpleStringProperty(addressLine2).get();
        this.city = new SimpleStringProperty(city).get();
        this.state = new SimpleStringProperty(state).get();
        this.postalCode = new SimpleStringProperty(postalCode).get();
        this.country = new SimpleStringProperty(country).get();
        this.salesRepEmployeeNumber = new SimpleIntegerProperty(salesRepEmployeeNumber).get();
        this.creditLimit = new SimpleIntegerProperty((int) creditLimit).get();

    }

    //Constructor sin parámetros para poder instanciar la clase y poder usar sus métodos.
    public Customers() {

    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSalesRepEmployeeNumber() {
        return salesRepEmployeeNumber;
    }

    public void setSalesRepEmployeeNumber(int salesRepEmployeeNumber) {
        this.salesRepEmployeeNumber = salesRepEmployeeNumber;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    Principal principal = new Principal();

    /**
     * Método encargado de obtener los datos de todos los clientes para mostrarlos en la tabla de clientes
     * de la vista de clientes.
     *
     * @return ArrayList<Customers>
     */
    public ArrayList<Customers> seleccionarClienteBd() {

        //Crear lista de clientes
        ArrayList<Customers> listaClientes = new ArrayList<>();
        //Conectar a base de datos
        DataBaseConnection conexion;
        try {
            conexion = DataBaseConnection.getInstance();
            //Preparar consulta
            String clienteSelect = "SELECT * FROM customers";
            //Ejecutar consulta y guardar resultado en variable resultado de tipo ResultSet.
            Statement sentencia = conexion.getConexion().createStatement();
            ResultSet resultado = sentencia.executeQuery(clienteSelect);

            //Si no hay resultado mostrar mensaje de error
            if (!resultado.next()) {
                //Mostrar mensaje de error
                VBox vbox = new VBox();
                //Creamos un label para mostrar el mensaje de error.
                vbox.getChildren().add(new javafx.scene.control.Label("No existe el cliente seleccionado"));

                return null;
            }

            //Si hay resultado, recorrer resultado y guardar cada cliente en la lista de clientes.
            while (resultado.next()) {

                customerNumber = resultado.getInt("customerNumber");
                customerName = resultado.getString("customerName");
                contactLastName = resultado.getString("contactLastName");
                contactFirstName = resultado.getString("contactFirstName");
                phone = resultado.getString("phone");
                addressLine1 = resultado.getString("addressLine1");
                addressLine2 = resultado.getString("addressLine2");
                city = resultado.getString("city");
                state = resultado.getString("state");
                postalCode = resultado.getString("postalCode");
                country = resultado.getString("country");
                salesRepEmployeeNumber = resultado.getInt("salesRepEmployeeNumber");
                creditLimit = resultado.getDouble("creditLimit");
                //Crear objeto de tipo Customers con los datos obtenidos de la base de datos.
                Customers customers = new Customers(customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit);
                //Añadir objeto a la lista de clientes.
                listaClientes.add(customers);

            }

        } catch (SQLException e) {
            principal.errorObtenerClientes("Error al obtener los clientes", "No se han podido obtener los clientes de la base de datos");
        }

        return listaClientes;
    }

    /**
     * Método encargado de obtener los datos de un cliente en específico para mostrarlos en la vista de clientes.
     *
     * @param customerName
     * @return int
     */
    public int getNumber(String customerName) {

        System.out.println("CustomerName: " + customerName);

        if (customerName.trim().isEmpty()) {
            //Llamar al método errorObtenerClientes de la clase principal para mostrar el mensaje de error.
            principal.errorObtenerClientes("Error al obtener el número de cliente", "No se ha seleccionado ningún cliente");
        }

        DataBaseConnection conexion;
        try {

            conexion = DataBaseConnection.getInstance();
            String query = "SELECT customerNumber FROM customers where customerName = ?";
            PreparedStatement statement = conexion.getConexion().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery(query);

            //Validar que exista el cliente
            if (!resultSet.next()) {
                principal.errorObtenerClientes("Error al obtener el número de cliente", "No existe el cliente seleccionado");
            }

            int customerNumber = 0;

            //Obtener el número de cliente si existe el cliente seleccionado en la base de datos. ç
            if (resultSet.next()) {
                customerNumber = resultSet.getInt(1);
            }


        } catch (SQLException e) {
            //Llamar al método errorObtenerClientes de la clase principal para mostrar el mensaje de error.
            principal.errorObtenerClientes("Error al obtener el número de cliente", "Error al conectar con la base de datos: " + e.getMessage());
        }
        return customerNumber;
    }

    /**
     * Método para eliminar un cliente de la base de datos.
     *
     * @throws SQLException
     */
    public void removeCustomer(String customerNumber) {

        try (DataBaseConnection conexion = DataBaseConnection.getInstance();
             Statement statement = conexion.getConexion().createStatement()) {

            conexion.getConexion().setAutoCommit(false);
            String query = "DELETE FROM payments WHERE customerNumber = " + customerNumber;
            statement.executeUpdate(query);
            ResultSet orderResult = statement.executeQuery("SELECT orderNumber FROM orders WHERE customerNumber = " + customerNumber);

            if (!orderResult.next()) {
                System.out.println("No hay pedidos para este cliente");
            }

            while (orderResult.next()) {
                String orderNumber = orderResult.getString("orderNumber");
                statement.executeUpdate("DELETE FROM orderdetails WHERE orderNumber = " + orderNumber);
            }

            //Borramos los pedidos del cliente:
            statement.executeUpdate("DELETE FROM orders WHERE customerNumber = " + customerNumber);

            //Botramos el cliente:
            statement.executeUpdate("DELETE FROM customers WHERE customerNumber = " + customerNumber);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
