
package Dao;

/**
 *
 * @author Kevin_Paez
 */
public class Catalogo {
    private int idMarca;
    private String marca;
    
    public Catalogo(){
        
    }
    
    public Catalogo(int idMarca, String marca) {
        this.idMarca = idMarca;
        this.marca = marca;
    }
    
    public Catalogo(String marca){
        this.marca = marca;
    }

    public Catalogo(int idMarca) {
        this.idMarca = idMarca;
    }
    
    
    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Catalogo{" + "idMarca=" + idMarca + ", marca=" + marca + '}';
    }

}
