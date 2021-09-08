/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;
import java.sql.*;

/**
 *
 * @author Kevin_Paez
 */
public class Conexion {
    
    private Connection conexion = null;
    private static final String localhost="localhost";
    private static final String usuario="root";
    private static final String password="";
    private static final String bd="users";
    
    public Connection getConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/bdinstrumentos ", "root", "");
        } catch (Exception e) {
            System.out.println("Error " + e);
        } finally {
            return conexion;
        }
    }
   
}
