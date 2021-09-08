
package inventario;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Kevin_Paez
 */
public class UserRegister extends javax.swing.JFrame {
    
    String barra=File.separator;
    String CrearUbicacion=System.getProperty("user.dir")+barra;
    /**
     * Creates new form UserRegister
     */
    public UserRegister() {
        initComponents();
        this.setTitle("Registro de Proveedores");
        this.setLocationRelativeTo(null);
    }
    
    private void crear(){
  
        File CrearArchi = new File("usuarios.txt");
        
        if(txt_user.getText().equals(" ")|| txt_nombre.getText().equals("") || (txt_apellidos.getText().equals("")) 
                || (txt_direc.getText().equals("")) || (txt_cel.getText().equals("")) 
                || (txt_email.getText().equals("")) || (txt_pass.getText().equals("")) ){
            javax.swing.JOptionPane.showMessageDialog(rootPane,"Debe llenar todos los campos \n");
           
        }else{
            if(!CrearArchi.exists()){
                try {
                    CrearArchi.createNewFile();
                    
                    FileWriter CrearForma = new FileWriter(CrearArchi,true);
                    CrearForma.write(txt_user.getText()+"\n"+txt_pass.getText()+
                            "\n"+txt_nombre.getText()+"\n"+txt_apellidos.getText()+"\n"+txt_direc.getText()+
                            "\n"+txt_cel.getText()+"\n"+txt_email.getText());
                    CrearForma.close();
                } catch (IOException ex) {
                    Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    FileWriter CrearForma = new FileWriter(CrearArchi,true);
                    CrearForma.write("\n*******\n"+txt_user.getText()+"\n"+txt_pass.getText()+
                            "\n"+txt_nombre.getText()+"\n"+txt_apellidos.getText()+"\n"+txt_direc.getText()+
                            "\n"+txt_cel.getText()+"\n"+txt_email.getText());
                    CrearForma.close();
                   
                    
                } catch (IOException ex) {
                    Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            createUser();
        }
    }
    
    private void createUser(){
  
        File CrearArchi = new File("user.txt");
 
            if(!CrearArchi.exists()){
                try {
                    CrearArchi.createNewFile();
                    
                    FileWriter CrearForma = new FileWriter(CrearArchi,true);
                    CrearForma.write(txt_user.getText());
                    CrearForma.close();
                    JOptionPane.showMessageDialog(rootPane,"Registro Exitoso");
                    
                } catch (IOException ex) {
                    Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    FileWriter CrearForma = new FileWriter(CrearArchi,true);
                    CrearForma.write("\n"+txt_user.getText());
                    CrearForma.close();
                    JOptionPane.showMessageDialog(rootPane,"Registro Exitoso");
                    
                } catch (IOException ex) {
                    Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_apellidos = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_user = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_direc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_cel = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_pass = new javax.swing.JPasswordField();
        jButton_Login = new javax.swing.JButton();
        jButton_Clean = new javax.swing.JButton();
        jButton_Back = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nombre:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txt_nombre.setBackground(new java.awt.Color(255, 255, 255));
        txt_nombre.setForeground(new java.awt.Color(0, 0, 0));
        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });
        txt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombreKeyTyped(evt);
            }
        });
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 220, -1));

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Apellidos:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 70, -1));

        txt_apellidos.setBackground(new java.awt.Color(255, 255, 255));
        txt_apellidos.setForeground(new java.awt.Color(0, 0, 0));
        txt_apellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_apellidosActionPerformed(evt);
            }
        });
        txt_apellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_apellidosKeyTyped(evt);
            }
        });
        getContentPane().add(txt_apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 240, -1));

        jLabel8.setBackground(new java.awt.Color(204, 204, 204));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Nombre de Usuario:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        txt_user.setBackground(new java.awt.Color(255, 255, 255));
        txt_user.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(txt_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 200, -1));

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Dirección:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        txt_direc.setBackground(new java.awt.Color(255, 255, 255));
        txt_direc.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(txt_direc, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 210, -1));

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Teléfono:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        txt_cel.setBackground(new java.awt.Color(255, 255, 255));
        txt_cel.setForeground(new java.awt.Color(0, 0, 0));
        txt_cel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_celActionPerformed(evt);
            }
        });
        txt_cel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_celKeyTyped(evt);
            }
        });
        getContentPane().add(txt_cel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 160, -1));

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("E-mail:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, -1));

        txt_email.setBackground(new java.awt.Color(255, 255, 255));
        txt_email.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 270, -1));

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Contraseña:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        txt_pass.setBackground(new java.awt.Color(255, 255, 255));
        txt_pass.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(txt_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 270, -1));

        jButton_Login.setBackground(new java.awt.Color(0, 102, 255));
        jButton_Login.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton_Login.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Login.setText("Registrar");
        jButton_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LoginActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, -1, -1));

        jButton_Clean.setBackground(new java.awt.Color(255, 0, 0));
        jButton_Clean.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton_Clean.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Clean.setText("Limpiar");
        jButton_Clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CleanActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Clean, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, -1, -1));

        jButton_Back.setBackground(new java.awt.Color(0, 0, 0));
        jButton_Back.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton_Back.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Back.setText("Regresar");
        jButton_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BackActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/inventario2.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void txt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyTyped

        char c=evt.getKeyChar();
        if((c<'a' || c>'z') && (c<'A' || c>'z') && (c<' '|| c>' ') )evt.consume();
    }//GEN-LAST:event_txt_nombreKeyTyped

    private void txt_celActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_celActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_celActionPerformed

    private void txt_celKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_celKeyTyped
        char c=evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_txt_celKeyTyped

    private void jButton_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LoginActionPerformed

        crear();
        txt_user.setText(" ");
        txt_nombre.setText(" ");
        txt_apellidos.setText(" ");
        txt_direc.setText(" ");
        txt_cel.setText(" ");
        txt_email.setText(" ");
        txt_pass.setText(" ");

    }//GEN-LAST:event_jButton_LoginActionPerformed

    private void jButton_CleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CleanActionPerformed
        txt_user.setText("");
        txt_nombre.setText("");
        txt_apellidos.setText("");
        txt_direc.setText("");
        txt_cel.setText("");
        txt_email.setText("");
        txt_pass.setText("");

    }//GEN-LAST:event_jButton_CleanActionPerformed

    private void jButton_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BackActionPerformed
        this.hide();
        Login back = new Login();
        back.setVisible(true);
        dispose();

    }//GEN-LAST:event_jButton_BackActionPerformed

    private void txt_apellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellidosActionPerformed

    private void txt_apellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellidosKeyTyped
        char c=evt.getKeyChar();
        if((c<'a' || c>'z') && (c<'A' || c>'z') && (c<' '|| c>' ') )evt.consume();
    }//GEN-LAST:event_txt_apellidosKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserRegister().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Back;
    private javax.swing.JButton jButton_Clean;
    private javax.swing.JButton jButton_Login;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txt_apellidos;
    private javax.swing.JTextField txt_cel;
    private javax.swing.JTextField txt_direc;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JPasswordField txt_pass;
    private javax.swing.JTextField txt_user;
    // End of variables declaration//GEN-END:variables
}
