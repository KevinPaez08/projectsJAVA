
package inventario;

import java.io.IOException;
import javax.swing.JOptionPane;


public class Seguridad {
    Login login = new Login();
    String res;
    public void validarUser(String usuarios[], String user, String pwd,int intentos) throws IOException{
        boolean encontrado = false;
        
        for(int i=0; i<usuarios.length; i++){
            if((usuarios[i].equals(user)&& usuarios[i+1].equals(pwd))){//equalsIgnoreCase no importa si son mayúsculas o minusculas
                res ="Bienvenido "+ user;
                encontrado=true;
                JOptionPane.showMessageDialog(null, res, " Inicio de Sesión ", JOptionPane.INFORMATION_MESSAGE);
                
                intentos=0;
                login.setIntentos(intentos);
                Productos r1 = new Productos();
                r1.setVisible(true);
                break;
            }
            
        }
        if(encontrado == false){
            res="Constraseña o Usuario incorrectos, te queda: "+ intentos +" intento" ;
            JOptionPane.showMessageDialog(null, res, " Inicio de Sesión ", JOptionPane.ERROR_MESSAGE);
        }
        if(intentos > 2){
            JOptionPane.showMessageDialog(null, " 3 Intentos fallidos ", " Inicio de Sesión ", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }   
    }
}
