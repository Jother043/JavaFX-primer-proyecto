package com.company.administracion_negocio.Model;

import com.company.administracion_negocio.Conection.DataBaseConnection;
import com.company.administracion_negocio.Principal;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
     * Método encargado de conectar a la base de datos.
     *
     * @return Connection
     * @throws SQLException
     */
    public Connection conecctionDataBase() throws SQLException {
        DataBaseConnection conexion;
        conexion = DataBaseConnection.getInstance();
        return conexion.getConexion();
    }

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
        Connection conexion;
        try {
            conexion = conecctionDataBase();

            DataBaseConnection.getInstance();
            //Preparar consulta
            String clienteSelect = "SELECT * FROM customers";
            //Ejecutar consulta y guardar resultado en variable resultado de tipo ResultSet.
            Statement sentencia = conexion.createStatement();
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
            throw new RuntimeException(e);
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
        int customerNumber = 0;
        System.out.println("CustomerName: " + customerName);

        try {
            Connection conexion = conecctionDataBase();
        } catch (SQLException e) {
            //Llamar al método errorObtenerClientes de la clase principal para mostrar el mensaje de error.
            principal.errorObtenerClientes("Error al obtener el número de cliente", "Error al conectar con la base de datos: " + e.getMessage());
        }

        try (PreparedStatement consultaNumeroCliente = conecctionDataBase().prepareStatement("SELECT customerNumber FROM customers where customerName = ?")) {

            consultaNumeroCliente.setString(1, customerName);
            ResultSet resultSet = consultaNumeroCliente.executeQuery();

            if (!resultSet.next()) {
                principal.errorObtenerClientes("Error al obtener el número de cliente", "No existe el cliente seleccionado");
            }

            customerNumber = resultSet.getInt(1);


        } catch (SQLException e) {
            //Si el mensaje de error contiene "empty result set" mostrar mensaje de error de cliente no encontrado.
            if (e.getMessage().contains("empty result set")) {
                System.err.println("No existe el cliente seleccionado");
            } else if (e.getMessage().contains("No operations allowed after connection closed.")) {
                principal.errorObtenerClientes("Error al obtener el número de cliente", "Error al conectar con la base de datos por cierre de conexión");
            } else {
                principal.errorObtenerClientes("Error al obtener el número de cliente", "Error al conectar con la base de datos: " + e.getMessage());
                throw new RuntimeException(e);
            }

        }
        return customerNumber;
    }

    /**
     * Método para eliminar un cliente de la base de datos.
     * Elimina los pagos relacionados con el cliente, las órdenes relacionadas con el cliente y finalmente el cliente.
     * Lo hacemos asi para evitar problemas de integridad referencial.
     *
     * @param customerNumber
     */
    public void removeCustomer(String customerNumber) {
        if (customerNumber.trim().isEmpty()) {
            principal.errorObtenerClientes("No se ha podido eliminar el cliente", "El cliente no existe");
        }
        Principal principal = new Principal();
        try {

            conecctionDataBase().setAutoCommit(false);

            // Eliminar pagos relacionados con el cliente
            String deletePaymentsQuery = "DELETE FROM payments WHERE customerNumber = ?";
            PreparedStatement deletePaymentsStatement = conecctionDataBase().prepareStatement(deletePaymentsQuery);
            deletePaymentsStatement.setString(1, customerNumber);
            deletePaymentsStatement.executeUpdate();


            // Obtener los números de orden relacionados con el cliente
            String selectOrdersQuery = "SELECT orderNumber FROM orders WHERE customerNumber = ?";
            PreparedStatement selectOrdersStatement = conecctionDataBase().prepareStatement(selectOrdersQuery);
            selectOrdersStatement.setString(1, customerNumber);
            ResultSet orderResult = selectOrdersStatement.executeQuery();

            List<Integer> orderNumbers = new ArrayList<>();

            while (orderResult.next()) {
                int orderNumber = orderResult.getInt("orderNumber");
                orderNumbers.add(orderNumber);
            }


            // Eliminar los detalles de orden relacionados con los números de orden obtenidos
            String deleteOrderDetailsQuery = "DELETE FROM orderdetails WHERE orderNumber = ?";
            PreparedStatement deleteOrderDetailsStatement = conecctionDataBase().prepareStatement(deleteOrderDetailsQuery);
            for (Integer orderNumber : orderNumbers) {
                deleteOrderDetailsStatement.setInt(1, orderNumber);
                deleteOrderDetailsStatement.executeUpdate();
            }


            // Eliminar las órdenes relacionadas con el cliente
            String deleteOrdersQuery = "DELETE FROM orders WHERE customerNumber = ?";
            PreparedStatement deleteOrdersStatement = conecctionDataBase().prepareStatement(deleteOrdersQuery);
            deleteOrdersStatement.setString(1, customerNumber);
            deleteOrdersStatement.executeUpdate();


            // Eliminar el cliente
            String deleteCustomersQuery = "DELETE FROM customers WHERE customerNumber = ?";
            PreparedStatement deleteCustomersStatement = conecctionDataBase().prepareStatement(deleteCustomersQuery);
            deleteCustomersStatement.setString(1, customerNumber);
            deleteCustomersStatement.executeUpdate();
            // Confirmar cambios
            conecctionDataBase().commit();
            if (conecctionDataBase() != null) {
                System.out.println("Conexión cerrada");

                deleteCustomersStatement.close();
                deletePaymentsStatement.close();
                selectOrdersStatement.close();
                selectOrdersStatement.close();
                deleteOrderDetailsStatement.close();
                deleteOrdersStatement.close();
            }
        } catch (SQLException e) {
            try {
                conecctionDataBase().rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            principal.errorObtenerClientes("Error al eliminar el cliente", "Error al conectar con la base de datos: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    /**
     * Método para obtener el último número de cliente de la base de datos.
     * Utiliza un PreparedStatement para evitar inyección SQL.
     * Llama al método errorObtenerClientes de la clase principal para mostrar el mensaje de error.
     * @return int
     */
    public int lastCustomerNumer() {
        int lastCustomerNumber = 0;
    
        try (PreparedStatement consultaNumeroCliente = conecctionDataBase().prepareStatement("SELECT MAX(customerNumber) FROM customers")) {

            ResultSet resultSet = consultaNumeroCliente.executeQuery();

            if (!resultSet.next()) {
                principal.errorObtenerClientes("Error al obtener el último número de cliente", "No existe el cliente seleccionado");
            }

            lastCustomerNumber = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lastCustomerNumber;
    }

    /**
     * Método para insertar un cliente en la base de datos a partir de los datos introducidos en el formulario.
     * Utiliza un PreparedStatement para evitar inyección SQL.
     * Llama al método errorObtenerClientes de la clase principal para mostrar el mensaje de error.
     * @param customers
     * @param customerName
     * @param contactLastName
     * @param contactFirstName
     * @param phone
     * @param addressLine1
     * @param addressLine2
     * @param city
     * @param state
     * @param postalCode
     * @param country
     * @param salesRepEmployeeNumber
     * @param creditLimit
     * @throws SQLException
     */
    public void insertarCliente(Customers customers, String customerName, String contactLastName,
                                String contactFirstName, String phone, String addressLine1, String addressLine2,
                                String city, String state, String postalCode, String country, int salesRepEmployeeNumber,
                                double creditLimit) {
        try {
            Connection conexion = conecctionDataBase();
        } catch (SQLException e) {
            principal.errorObtenerClientes("Error al insertar el cliente", "Error al conectar con la base de datos: " + e.getMessage());
        }
        //Hacemos un prepareStatement para evitar la inyección SQL
        try (PreparedStatement insertarCliente = conecctionDataBase().prepareStatement("INSERT INTO customers VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)")) {
                //Insertamos los datos en la base de datos
                insertarCliente.setInt(1, lastCustomerNumer() + 1);
                insertarCliente.setString(2, customerName);
                insertarCliente.setString(3, contactLastName);
                insertarCliente.setString(4, contactFirstName);
                insertarCliente.setString(5, phone);
                insertarCliente.setString(6, addressLine1);
                insertarCliente.setString(7, addressLine2);
                insertarCliente.setString(8, city);
                insertarCliente.setString(9, state);
                insertarCliente.setString(10, postalCode);
                insertarCliente.setString(11, country);
                insertarCliente.setInt(12, salesRepEmployeeNumber);
                insertarCliente.setDouble(13, creditLimit);
                //Ejecutamos la consulta
                insertarCliente.executeUpdate();
                //Creamos un objeto de la clase principal para poder llamar al método alertaBorrarCliente
                Principal principal = new Principal();
                //Llamamos al método alertaBorrarCliente para mostrar un mensaje de confirmación
                principal.alertaBorrarCliente("Cliente insertado correctamente", "El cliente se ha insertado correctamente");


        } catch (SQLException e) {
            principal.errorObtenerClientes("Error al insertar el cliente", "Error al conectar con la base de datos: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
