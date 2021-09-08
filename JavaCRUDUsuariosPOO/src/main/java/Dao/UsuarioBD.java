
package Dao;

import config.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin_Paez
 */
public class UsuarioBD {
    private static final String Listado="SELECT * FROM users";
    private static final String Insertado="INSERT INTO users SET nombre=?, password=md5(?), edad=?";
    private static final String Buscar="SELECT * FROM users WHERE id_user=?";
    private static final String EditarUser="UPDATE users SET nombre=? WHERE id_user=? ";
    private static final String BuscarContra="SELECT * FROM users WHERE password=MD5(?) AND id_user=?";
    private static final String CambiarCon="UPDATE users SET password=md5(?) WHERE id_user=?";
    private static final String Eliminar="DELETE FROM users WHERE id_user=?";
    private Connection conexion = new Conexion().getConexion();
    private PreparedStatement stmt;
    private ResultSet rs;
    
    
    public List<Usuario> ListadoUsuario()throws SQLException {
        List<Usuario> usuarios=new ArrayList<>();
        this.stmt=this.conexion.prepareStatement(this.Listado); 
        this.rs=this.stmt.executeQuery();
        while (this.rs.next()){
            usuarios.add(new Usuario(this.rs.getInt("id_user"), this.rs.getString("nombre"), this.rs.getString("password"), this.rs.getInt("edad")));
            
        }   
        return usuarios;
    }
    
    public boolean InsertarUsuario(Usuario usuario) throws SQLException{
        this.stmt=this.conexion.prepareStatement(Insertado);
        this.stmt.setString(1, usuario.getNombre());
        this.stmt.setString(2, usuario.getPassword());
        this.stmt.setInt(3, usuario.getEdad());
        
        if(this.stmt.executeUpdate()==1){
            return true;
        }else{
            return false;
        }
    }
    
    public String BuscarUsuario(Usuario usuario) throws SQLException{
        String nom="";
        this.stmt=this.conexion.prepareStatement(Buscar);
        this.stmt.setInt(1, usuario.getId_user());
        this.rs=this.stmt.executeQuery();
        this.rs.next();
        nom=this.rs.getString("nombre");
        
        return nom;
    }
    
    public boolean EditarUsuario(Usuario usuario) throws SQLException{
        this.stmt=this.conexion.prepareStatement(EditarUser);
        this.stmt.setString(1, usuario.getNombre());
        this.stmt.setInt(2, usuario.getId_user());
        System.out.println(usuario.getNombre());
        if(this.stmt.executeUpdate()==1){
            return true;
        }else{
            return false;
        }
    }
    
    public String BuscarContra(Usuario usuario) throws SQLException{
        String nom="";
        try {
            this.stmt=this.conexion.prepareStatement(BuscarContra);
            this.stmt.setString(1, usuario.getPassword());
            this.stmt.setInt(2, usuario.getId_user());
            this.rs=this.stmt.executeQuery();
            this.rs.next();
            nom=this.rs.getString("nombre");
            return nom;
        } catch (Exception e) {
            nom="vacio";
            return nom;
        }
    }
    
    public boolean CambiarContra(Usuario usuario) throws SQLException{
        this.stmt=this.conexion.prepareStatement(CambiarCon);
        this.stmt.setString(1, usuario.getPassword());
        this.stmt.setInt(2, usuario.getId_user());
        if(this.stmt.executeUpdate()==1){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean EliminarUsuario(Usuario usuario) throws SQLException{
        this.stmt=this.conexion.prepareStatement(Eliminar);
        this.stmt.setInt(1, usuario.getId_user());
        if(this.stmt.executeUpdate()==1){
            return true;
        }else{
            return false;
        }
        
    }
    
    
}
