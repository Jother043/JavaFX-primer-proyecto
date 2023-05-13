package com.company.administracion_negocio.Model;

import com.company.administracion_negocio.Conection.DataBaseConnection;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Customers {

    private String customerNumber;
    private String customerName;
    private String contactLastName;
    private String contactFirstName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private int postalCode;
    private String country;
    private int salesRepEmployeeNumber;
    private double creditLimit;

    public Customers() {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.contactLastName = contactLastName;
        this.contactFirstName = contactFirstName;
        this.phone = phone;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.salesRepEmployeeNumber = salesRepEmployeeNumber;
        this.creditLimit = creditLimit;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
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

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
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

    /**
     * Metodo para insertar un cliente en la base de datos
     */
    public TableView<Customers> seleccionarClienteBd(String customerNumber) {
        //Validar que se haya seleccionado un cliente
        if (customerNumber == null || customerNumber.isEmpty()) {
            VBox vbox = new VBox();
            vbox.getChildren().add(new javafx.scene.control.Label("No se ha seleccionado un cliente"));
            return null;
        }
        //Crear lista de clientes
        ArrayList<Customers> listaClientes = new ArrayList<>();
        //Conectar a base de datos
        try (DataBaseConnection conexion = DataBaseConnection.getInstance()) {

            //Preparar consulta
            String clienteSelect = "SELECT customerNumber FROM customers WHERE customerName = ?";
            PreparedStatement sentencia = conexion.getConexion().prepareStatement(clienteSelect);
            sentencia.setString(1, customerNumber);
            ResultSet resultado = sentencia.executeQuery();
            //Validar que exista el cliente
            if (!resultado.next()) {
                //Mostrar mensaje de error
                VBox vbox = new VBox();
                //Creamos un label para mostrar el mensaje de error.
                vbox.getChildren().add(new javafx.scene.control.Label("No existe el cliente seleccionado"));
                conexion.getConexion().rollback();
                return null;
            }
            //Recorrer resultado
            while (resultado.next()) {
                Customers cliente = new Customers();
                cliente.setCustomerName(resultado.getString("customerNumber"));
                listaClientes.add(cliente);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //TODO: Mostrar clientes en la tabla
        listaClientes.forEach(cliente -> System.out.println(cliente.getCustomerName()));
        return null;
    }

    /**
     * Método para eliminar un cliente de la base de datos
     * @param listaClientes
     * @throws SQLException
     */
    public void removeCustomer(ArrayList<Customers> listaClientes) {
        try (DataBaseConnection conexion = DataBaseConnection.getInstance()) {
            for (Customers cliente : listaClientes) {
                int customerNumber = Integer.parseInt(cliente.getCustomerNumber());
                String clienteDelete = "DELETE FROM customers WHERE customerNumber =" + customerNumber;
                //TODO: Mostrar mensaje de confirmación

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
