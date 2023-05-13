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

    private DataBaseConnection() throws SQLException {

        conexion = DriverManager.getConnection(url, usuario, password);

    }

    public static DataBaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataBaseConnection();
        }
        return instance;
    }

    public void close() {
        try {
            if(conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConexion() {
        return conexion;
    }
}
