
package Dao;


public class VentaTotal {
    private int idCompra;
    private int idInstru;
    private String idOrden;
    private String nombre;
    private Double precio;
    private int cantidad;
    private Double total;
    
    public VentaTotal(){
        
    }

    public VentaTotal(int idCompra, int idInstru, String idOrden, String nombre, Double precio, int cantidad, Double total) {
        this.idCompra = idCompra;
        this.idInstru = idInstru;
        this.idOrden = idOrden;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
    }

    public VentaTotal(int idInstru, String idOrden, String nombre, Double precio, int cantidad, Double total) {
        this.idInstru = idInstru;
        this.idOrden = idOrden;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
    }

    public VentaTotal(int idInstru) {
        this.idInstru = idInstru;
    }
    
    
    
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdInstru() {
        return idInstru;
    }

    public void setIdInstru(int idInstru) {
        this.idInstru = idInstru;
    }
    
    public String getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(String idOrden) {
        this.idOrden = idOrden;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "VentaTotal{" + "idCompra=" + idCompra + ", idInstru=" + idInstru + ", nombre=" + nombre + ", precio=" + precio + ", cantidad=" + cantidad + ", total=" + total + '}';
    }


    
    
}
