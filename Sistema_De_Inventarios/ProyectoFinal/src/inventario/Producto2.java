
package inventario;


/**
 *
 * @author Kevin_Paez
 */
public class Producto2 {
    private int id_produc;
    private String nombre;
    private int stock;
    private double precio_ven;

    public Producto2(int id_produc, String nombre, int stock, double precio_ven) {
        this.id_produc = id_produc;
        this.nombre = nombre;
        this.stock = stock;        
        this.precio_ven = precio_ven;
    
    }

    public int getId_produc() {
        return id_produc;
    }

    public void setId_produc(int id_produc) {
        this.id_produc = id_produc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public double getPrecio_ven(){
        return precio_ven;
    }
    
    public void setPrecio_ven(double precio_ven){
        this.precio_ven = precio_ven;
    }
  
}
