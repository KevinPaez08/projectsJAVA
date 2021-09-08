/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 *
 * @author Kevin_Paez
 */
public class Limpiar_txt {
    public void limpiar_texto(JPanel jPanel1){
        for(int i = 0; jPanel1.getComponents().length > i; i++){
            if(jPanel1.getComponents()[i] instanceof JTextField){
                ((JTextField)jPanel1.getComponents()[i]).setText("");
            }
            else if(jPanel1.getComponents()[i] instanceof JPasswordField){
                ((JPasswordField)jPanel1.getComponents()[i]).setText("");
            }
        }
    }
}
