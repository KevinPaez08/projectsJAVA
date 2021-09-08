package Dao;

/**
 *
 * @author Kevin_Paez
 */
public class Usuario {
    private int id_user;
    private String nombre;
    private String password;
    private int edad;
    
    public Usuario(){
        
    }

    public Usuario(int id_user, String nombre, String password, int edad) {
        this.id_user = id_user;
        this.nombre = nombre;
        this.password = password;
        this.edad = edad;
    }

    public Usuario(String nombre, String password, int edad) {
        this.nombre = nombre;
        this.password = password;
        this.edad = edad;
    }

    public Usuario(int id_user) {
        this.id_user = id_user;
    }
    
    public Usuario(int id_user, String nombre) {
        this.id_user = id_user;
        this.nombre = nombre;
    }
    
    public Usuario(String password, int id_user) {
        this.id_user = id_user;
        this.password = password;
    }
    
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_user=" + id_user + ", nombre=" + nombre + ", password=" + password + ", edad=" + edad + '}';
    }
 
}
