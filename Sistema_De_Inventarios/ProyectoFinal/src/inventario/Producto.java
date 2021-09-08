
package inventario;

/**
 *
 * @author Kevin_Paez
 */
public class Producto {
    private int id_produc;
    private String nombre;
    private int stock;
    private double precio_ven;
    private double precio_uni;
    private String id_proveedor;
    
    
    public Producto( int id_produc, String nombre, int stock, double precio_ven, double precio_uni,String id_proveedor ) {
        this.id_produc = id_produc;
        this.nombre = nombre;
        this.stock = stock;
        this.precio_ven = precio_ven;
        this.precio_uni = precio_uni;
        this.id_proveedor = id_proveedor;
        
        
    }

    public Producto(){}

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

    public double getPrecio_uni() {
        return precio_uni;
    }

    public void setPrecio_uni(double precio_uni) {
        this.precio_uni = precio_uni;
    }

    public double getPrecio_ven() {
        return precio_ven;
    }

    public void setPrecio_ven(double precio_ven) {
        this.precio_ven = precio_ven;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public String getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    String setId_proveedor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
