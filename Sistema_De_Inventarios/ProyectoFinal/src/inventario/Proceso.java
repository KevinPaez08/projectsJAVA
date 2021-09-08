/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario;
import java.util.ArrayList;
/**
 *
 * @author Kevin_Paez
 */
public class Proceso {
    private ArrayList<Object> a = new ArrayList<Object>();
    
    public Proceso(){}
    
    public Proceso(ArrayList<Object> a){
        this.a = a;
    }
    
    public void agregarRegistro(Producto p){
        this.a.add(p);
    }

    public void modificarRegistro(int i, Producto p){
        this.a.set(i, p);
    }
    
    public void eliminarRegistro(int i){
        this.a.remove(i);
    }
    
    public Producto obtenerRegistro(int i){
        return (Producto)a.get(i);
    }
    
    public int cantidadRegistro(){
        return this.a.size();

    }
    
    public int buscaCodigo(int codigo){
        for(int i = 0; i < cantidadRegistro(); i++){
            if(codigo == obtenerRegistro(i).getId_produc())return i;
        }
        return -1;
    }    
    
    
}
