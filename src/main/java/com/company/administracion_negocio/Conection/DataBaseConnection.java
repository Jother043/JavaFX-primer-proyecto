package com.company.administracion_negocio.Conection;
import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection implements Closeable {

    private static DataBaseConnection instance;
    private String url = "jdbc:mysql://localhost:3306/classicmodels?serverTimezone=UTC&useSSL=true&characterEncoding=UTF-8";
    private String usuario = "root";
    private String password = "admin.123";
    private Connection conexion;

    //Constructor privado para que no se pueda instanciar desde fuera
    private DataBaseConnection() throws SQLException {

        conexion = DriverManager.getConnection(url, usuario, password);

    }

    //Método público que devuelve la instancia única
    public static DataBaseConnection getInstance() throws SQLException {
        //Si no existe la instancia única la creamos
        if (instance == null) {
            instance = new DataBaseConnection();
        }
        return instance;
    }

    //Método para cerrar la conexión.
    public void close() {
        try {
            if(conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Método para obtener la conexión
    public Connection getConexion() {
        return conexion;
    }
}
