
package Dao;

import Config.Conexion;
import java.sql.*;


/**
 *
 * @author Kevin_Paez
 */
public class OrdenCompraBD {
    private static final String Insertado="INSERT INTO orden_compra VALUES()";
    private static final String Buscar="SELECT * FROM orden_compra WHERE fecha=?";
    private Connection conexion = new Conexion().getConexion();
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public boolean pruebafecha() throws SQLException{
        this.stmt=this.conexion.prepareStatement(this.Insertado);
        if(this.stmt.executeUpdate()==1){
            return true;
        }else{
            return false;
        }
    }
    
    public int BuscarIdOrden(String fecha) throws SQLException{
        int idOrden;
        this.stmt=this.conexion.prepareStatement(Buscar);
        this.stmt.setString(1, fecha);
        this.rs=this.stmt.executeQuery();
        this.rs.next();
        idOrden=this.rs.getInt("idOrden");
        
        return idOrden;
    }
    
   
    
}
