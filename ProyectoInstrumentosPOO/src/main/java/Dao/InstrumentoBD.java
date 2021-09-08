
package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Config.Conexion;
/**
 *
 * @author Kevin_Paez
 */
public class InstrumentoBD {
    private static final String Listado="SELECT instrumentos.idInstru, instrumentos.nombre, cat_marca.marca,"
            +"instrumentos.precio, instrumentos.cantidad, instrumentos.descripcion FROM instrumentos JOIN cat_marca ON instrumentos.id_marca=cat_marca.idMarca ";
    private static final String Insertado="INSERT INTO instrumentos SET nombre=?, id_marca=?, precio=?, cantidad=?, descripcion=?";
    private static final String Buscar="SELECT * FROM instrumentos WHERE idInstru=?";
    private static final String EditarInstru="UPDATE instrumentos SET nombre=?, precio=?, cantidad=? WHERE idInstru=? ";
    private static final String Eliminar="DELETE FROM instrumentos WHERE idInstru=?";
    private Connection conexion = new Conexion().getConexion();
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public List<Instrumento> ListadoInstrumento()throws SQLException {
        List<Instrumento> instrumentos=new ArrayList<>();
        this.stmt=this.conexion.prepareStatement(this.Listado); 
        this.rs=this.stmt.executeQuery();
        while (this.rs.next()){
            instrumentos.add(new Instrumento(this.rs.getInt("idInstru"), this.rs.getString("nombre"), this.rs.getString("marca"), this.rs.getDouble("precio"), this.rs.getInt("cantidad"), this.rs.getString("descripcion"))); 
        }   
        return instrumentos;
    }  
    
    
    public boolean InsertarInstrumento(Instrumento instrumento) throws SQLException{
        this.stmt=this.conexion.prepareStatement(Insertado);
        this.stmt.setString(1, instrumento.getNombre());
        this.stmt.setString(2, instrumento.getMarca());
        this.stmt.setDouble(3, instrumento.getPrecio());
        this.stmt.setInt(4, instrumento.getCantidad());
        this.stmt.setString(5, instrumento.getDescripcion());
        
        if(this.stmt.executeUpdate()==1){
            return true;
        }else{
            return false;
        }
    }
    
    public String BuscarInstrumento(Instrumento instrumento) throws SQLException{
        String nom="";
        this.stmt=this.conexion.prepareStatement(Buscar);
        this.stmt.setInt(1, instrumento.getIdInstru());
        this.rs=this.stmt.executeQuery();
        this.rs.next();
        nom=this.rs.getString("nombre");
        
        return nom;
    }
    
    public Double BuscarPrecio(Instrumento instrumento) throws SQLException{
        Double precio;
        this.stmt=this.conexion.prepareStatement(Buscar);
        this.stmt.setInt(1, instrumento.getIdInstru());
        this.rs=this.stmt.executeQuery();
        this.rs.next();
        precio=this.rs.getDouble("precio");
        
        return precio;
    }
    
    public int BuscarCantidad(Instrumento instrumento) throws SQLException{
        int cantidad;
        this.stmt=this.conexion.prepareStatement(Buscar);
        this.stmt.setInt(1, instrumento.getIdInstru());
        this.rs=this.stmt.executeQuery();
        this.rs.next();
        cantidad=this.rs.getInt("cantidad");
        
        return cantidad;
    }
    
    public boolean EditarInstrumento(Instrumento instrumento) throws SQLException{
        this.stmt=this.conexion.prepareStatement(EditarInstru);
        this.stmt.setString(1, instrumento.getNombre());
        this.stmt.setDouble(2, instrumento.getPrecio());
        this.stmt.setInt(3, instrumento.getCantidad());
        this.stmt.setInt(4, instrumento.getIdInstru());
        
        if(this.stmt.executeUpdate()==1){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean EliminarInstrumento(Instrumento instrumento) throws SQLException{
        this.stmt=this.conexion.prepareStatement(Eliminar);
        this.stmt.setInt(1, instrumento.getIdInstru());
        if(this.stmt.executeUpdate()==1){
            return true;
        }else{
            return false;
        }
        
    }
    
}
