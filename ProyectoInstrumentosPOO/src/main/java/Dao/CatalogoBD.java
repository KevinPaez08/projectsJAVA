
package Dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Config.Conexion;
/**
 *
 * @author Kevin_Paez
 */
public class CatalogoBD {
    private static final String Listado="SELECT * FROM cat_marca";
    private static final String Insertado="INSERT INTO cat_marca SET marca=?";
    private static final String Buscar="SELECT * FROM cat_marca WHERE idMarca=?";
    private static final String EditarMarca="UPDATE cat_marca SET marca=? WHERE idMarca=?";
    private static final String Eliminar="DELETE FROM cat_marca WHERE idMarca=?";
    private Connection conexion = new Conexion().getConexion();
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public List<Catalogo> ListadoCatalogo() throws SQLException{
        List<Catalogo> catalogo = new ArrayList<>();
        this.stmt=this.conexion.prepareStatement(this.Listado); 
        this.rs=this.stmt.executeQuery();
        while (this.rs.next()){
            catalogo.add(new Catalogo(this.rs.getInt("idMarca"), this.rs.getString("marca")));
        }
        
        return catalogo;
    }
    
    public boolean InsertarMarca(Catalogo catalogo) throws SQLException{
        this.stmt=this.conexion.prepareStatement(Insertado);
        this.stmt.setString(1, catalogo.getMarca());
        
        if(this.stmt.executeUpdate()==1){
            return true;
        }else{
            return false;
        }    
    }
    
    public String BuscarMarca(Catalogo catalogo) throws SQLException{
        String nom="";
        this.stmt=this.conexion.prepareStatement(Buscar);
        this.stmt.setInt(1, catalogo.getIdMarca());
        this.rs=this.stmt.executeQuery();
        this.rs.next();
        nom=this.rs.getString("marca");
        
        return nom;
    }
    
    public boolean EditarMarca(Catalogo catalogo) throws SQLException{
        this.stmt=this.conexion.prepareStatement(EditarMarca);
        this.stmt.setString(1, catalogo.getMarca());
        this.stmt.setInt(2, catalogo.getIdMarca());
        if(this.stmt.executeUpdate()==1){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean EliminarMarca(Catalogo catalogo) throws SQLException{
        this.stmt=this.conexion.prepareStatement(Eliminar);
        this.stmt.setInt(1, catalogo.getIdMarca());
        if(this.stmt.executeUpdate()==1){
            return true;
        }else{
            return false;
        }
        
    }
    
}
