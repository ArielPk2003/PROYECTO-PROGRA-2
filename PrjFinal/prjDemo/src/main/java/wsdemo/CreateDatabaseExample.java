package wsdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreateDatabaseExample {

    private static final String nombreBaseDatos = "prueba1";
    private static final String url = "jdbc:mysql://localhost:3306/" + nombreBaseDatos ;
    private static final String usuario = "root";
    private static final String contrasena = "gatox3185";

    private static final String nombreTabla = "registroPlanta";

    public static void registroBaseDatos() {
        try (Connection connection = DriverManager.getConnection(url, usuario, contrasena)) {
            Statement statement = connection.createStatement();
            String sql = "CREATE DATABASE " + nombreBaseDatos;
            statement.executeUpdate(sql);
            System.out.println("Base de datos creada correctamente.");
        } catch (SQLException e) {
            System.err.println("Base de datos encontrada");
        }
    }

    public static void creaciontabla() {
        try (Connection connection = DriverManager.getConnection(url, usuario, contrasena)) {
            Statement statement = connection.createStatement();
            String accesoBaseDatos = "USE " + nombreBaseDatos;
            statement.executeUpdate(accesoBaseDatos);
            String creacionTabla = "CREATE TABLE " + nombreTabla + " (" +
                "Indice INT UNSIGNED primary key auto_increment, " + 
                "fecha_hora TIMESTAMP DEFAULT NOW(), " +
                "Humedad INT UNSIGNED)auto_increment = 1";
            statement.executeUpdate(creacionTabla);
        } catch (SQLException e) {
            System.err.println("Tabla ya creada");
        }
    }

    public static void ingresoDatos(Integer Humedad) {
        try (Connection connection = DriverManager.getConnection(url, usuario, contrasena)) {
            Statement statement = connection.createStatement();
            String accesoBaseDatos = "USE " + nombreBaseDatos;
            statement.executeUpdate(accesoBaseDatos);
            String insercionDatos = "INSERT INTO " + nombreTabla + " (Humedad) VALUE ("+ Humedad +")";
            statement.executeUpdate(insercionDatos);
        } catch (SQLException e) {
            System.out.println("Error al ingresar datos a la tabla: " + e.getMessage());
        }
    }

    public static void extractorDatos() {
        
        try (Connection connection = DriverManager.getConnection(url, usuario, contrasena)) {
            Statement statement = connection.createStatement();
            
            String accesoBaseDatos = "USE " + nombreBaseDatos;

            statement.executeUpdate(accesoBaseDatos);

            String selectSQL = "SELECT Indice, fecha_hora, Humedad FROM "+ nombreTabla + " ORDER BY fecha_hora DESC LIMIT 10";
            
            ResultSet resultSet = statement.executeQuery(selectSQL);
            
            List<List<Object>> matrizDatos = new ArrayList<>();
            
            while (resultSet.next()) {
                String id = resultSet.getString("Indice");
                java.sql.Timestamp fechaHora = resultSet.getTimestamp("fecha_hora");
                int humedad = resultSet.getInt("Humedad");                  
                                
                List<Object> fila = new ArrayList<>();
                fila.add(id);
                fila.add(fechaHora);
                fila.add(humedad);
                
                matrizDatos.add(fila);
            }
            
            for (List<Object> fila : matrizDatos) {
                System.out.println("ID: " + fila.get(0) + ", Tiempo: " + fila.get(1) + ", Niveles de Humedad: " + fila.get(2));
            }
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static List<List<Object>> preTabla() {
        List<List<Object>> matrizDatos = new ArrayList<>();
    
        try (Connection connection = DriverManager.getConnection(url, usuario, contrasena)) {
            Statement statement = connection.createStatement();
    
            String accesoBaseDatos = "USE " + nombreBaseDatos;
            statement.executeUpdate(accesoBaseDatos);
    
            String selectSQL = "SELECT Indice, fecha_hora, Humedad FROM "+ nombreTabla + " ORDER BY fecha_hora DESC LIMIT 10";
    
            ResultSet resultSet = statement.executeQuery(selectSQL);
    
            while (resultSet.next()) {
                String id = resultSet.getString("Indice");
                java.sql.Timestamp fechaHora = resultSet.getTimestamp("fecha_hora");
                int humedad = resultSet.getInt("Humedad");                  
    
                List<Object> fila = new ArrayList<>();
                fila.add(id);
                fila.add(fechaHora);
                fila.add(humedad);
    
                matrizDatos.add(fila);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    
        return matrizDatos;
    }

    public static int ultimoDato() {
        
        try (Connection connection = DriverManager.getConnection(url, usuario, contrasena)) {
            Statement statement = connection.createStatement();
            
            String accesoBaseDatos = "USE " + nombreBaseDatos;

            statement.executeUpdate(accesoBaseDatos);

            String selectSQL = "SELECT humedad FROM "+ nombreTabla + " ORDER BY fecha_hora DESC LIMIT 1";

            ResultSet resultSet = statement.executeQuery(selectSQL);
            
            while (resultSet.next()) {
                int humedad = resultSet.getInt("Humedad");                  
                                
                return humedad;
            }
           
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } 

        return -1;
    }

    

}

