
package inventario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kevin_Paez
 */
public class Productos extends javax.swing.JFrame {

    /**
     * Creates new form Productos
     */
    
    Limpiar_txt lt = new Limpiar_txt();

    Producto p;
    Proceso rp;
    
    int clic_tabla;
    
    public Productos() {
        initComponents();
        this.setTitle("Productos");
        this.setLocationRelativeTo(null);
        
        rp = new Proceso();
        
        try{
            cargar_txt();
            listarRegistro();
            combo();
            
            
        }catch(Exception ex){
            mensaje("No existe el archivo txt");
        }
    }
    
    public void cargar_txt(){
        File ruta = null;
        FileReader fi = null;
        BufferedReader bu = null;

        try{
            ruta= new File("productos.txt");
            fi = new FileReader(ruta);
            bu = new BufferedReader(fi);

            String linea;
            while((linea = bu.readLine())!=null){
                StringTokenizer st = new StringTokenizer(linea, ",");
                
                p = new Producto();
                p.setId_produc(Integer.parseInt(st.nextToken()));
                p.setNombre(st.nextToken());
                p.setStock(Integer.parseInt(st.nextToken()));
                p.setPrecio_ven(Double.parseDouble(st.nextToken()));
                p.setPrecio_uni(Double.parseDouble(st.nextToken()));
                p.setId_proveedor(st.nextToken());
                
                rp.agregarRegistro(p);
            }
            bu.close();
            fi.close();
        }catch(Exception ex){
            mensaje("Error al cargar archivo: "+ex.getMessage());
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void grabar_txt(){
        FileWriter fw;
        PrintWriter pw;
        
        try{
            fw = new FileWriter("productos.txt");
            pw = new PrintWriter(fw);
            
            for(int i = 0; i < rp.cantidadRegistro(); i++){
                p = rp.obtenerRegistro(i);
                pw.println(String.valueOf(p.getId_produc()+","+p.getNombre()+","+p.getStock()+","+
                        p.getPrecio_ven()+","+p.getPrecio_uni()+","+p.getId_proveedor()));
            }
            pw.close();
            fw.close();
        }catch(Exception ex){
            mensaje("Error al grabar archivo: "+ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }
    
    public void ingresarRegistro(){
        try{
            if(leerId_produc() == -666)mensaje("Ingresar ID del Producto");
            else if(leerNombre() == null)mensaje("Ingresar Nombre");
            else if(leerStock() == -666) mensaje("Ingresar Stock");
            else if(leerPrecio_ven() == -666) mensaje("Ingresar Precio Venta");
            else if(leerProveedor() == null)mensaje("Ingresar Nombre");
            
            else{
                p = new Producto(leerId_produc(), leerNombre(), leerStock(), leerPrecio_ven(), leerPrecio_uni(),leerProveedor());
                if((rp.buscaCodigo(p.getId_produc())!= -1 ) )mensaje("Este codigo ya existe");
                else rp.agregarRegistro(p);
                
                grabar_txt();
                listarRegistro();
                lt.limpiar_texto(jPanel1); 
            }
        }catch(Exception ex){
            mensaje(ex.getMessage());
        }
    }
    
    public void modificarRegistro(){
        try{
            if(leerId_produc() == -666)mensaje("Ingresar ID del Producto");
            else if(leerNombre() == null)mensaje("Ingresar Nombre");
            else if(leerStock() == -666) mensaje("Ingresar Stock");
            else if(leerPrecio_ven() == -666) mensaje("Ingresar Precio Venta");
            else if(leerPrecio_uni() == -666) mensaje("Ingresar Precio Unidad");
            else if(leerProveedor() == null)mensaje("Ingresar Nombre");
            
            else{
                int codigo = rp.buscaCodigo(leerId_produc());
                p = new Producto(leerId_produc(), leerNombre(), leerStock(), leerPrecio_ven(), leerPrecio_uni(),leerProveedor());
                
                if(codigo == -1)rp.agregarRegistro(p);
                else rp.modificarRegistro(codigo, p);
                
                
                grabar_txt();
                listarRegistro();
                lt.limpiar_texto(jPanel1);
            }
        }catch(Exception ex){
            mensaje(ex.getMessage());
        }
    }
    
    public void eliminarRegistro(){
        try{
            if(leerId_produc() == -666) mensaje("Ingresar ID del Producto");
            
            else{
                int codigo = rp.buscaCodigo(leerId_produc());
                if(codigo == -1) mensaje("codigo no existe");
                
                else{
                    int s = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar este producto","Si/No",0);
                    if(s == 0){
                        rp.eliminarRegistro(codigo);
                        
                        grabar_txt();
                        listarRegistro();
                        lt.limpiar_texto(jPanel1);
                    }
                }   
            }
        }catch(Exception ex){
            mensaje(ex.getMessage());
        }
    }
    
    public void listarRegistro(){
        DefaultTableModel dt = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        dt.addColumn("ID");
        dt.addColumn("Nombre");
        dt.addColumn("Stock");
        dt.addColumn("$ Venta");
        dt.addColumn("$ Unitario");
        dt.addColumn("Usuario-Proveedor");
        jTable_productos.setDefaultRenderer(Object.class, new FormTable());
        
        Object fila[] = new Object[dt.getColumnCount()];
        for(int i = 0; i < rp.cantidadRegistro(); i++){
            p = rp.obtenerRegistro(i);
            
            fila[0] = p.getId_produc();
            fila[1] = p.getNombre();
            fila[2] = p.getStock();
            fila[3] = p.getPrecio_ven();
            fila[4] = p.getPrecio_uni();
            fila[5] = p.getId_proveedor();
            
            //combo();
            dt.addRow(fila);
        }
        jTable_productos.setModel(dt);
        jTable_productos.setRowHeight(20);
    }
    
    
    public void combo(){
        
        //jComboBox1.addItem(p.getId_proveedor());
        try {
            BufferedReader br=new BufferedReader(new FileReader("user.txt"));
            String linea;
            while((linea = br.readLine()) != null) {
            StringTokenizer tokens = new StringTokenizer(linea,",");
            jComboBox1.addItem((String) tokens.nextToken());
            }
            br.close();
        } catch(Exception x) {
        x.printStackTrace();
        }
        
    }
    
    public int leerId_produc(){
        try{
            int id = Integer.parseInt(txt_id_producto.getText().trim());
            return id;
        }catch(Exception ex){
            return -666;
        }
    }
    
    public String leerNombre(){
        try{
            String nombre = txt_nombre.getText().trim().replace(" ", "_");
            return nombre;
        }catch(Exception ex){
            return null;
        }
    }
    
    public double leerPrecio_uni(){
        try{
            double precio_uni = Double.parseDouble(txt_precio_uni.getText().trim());
            return precio_uni;
        }catch(Exception ex){
            return -666;
        }
    }
    
    public double leerPrecio_ven(){
        try{
            double precio_ven = Double.parseDouble(txt_precio_vent.getText().trim());
            return precio_ven;
        }catch(Exception ex){
            return -666;
        }
    }
    
   
    public int leerStock(){
        try{
            int stoc = Integer.parseInt(txt_stock.getText().trim());
            return stoc;
        }catch(Exception ex){
            return -666;
        }
    }
    
    
    public String leerProveedor(){
        try{
            String prove = txt_proveedor.getText().trim().replace(" ", "_");
            return prove;
        }catch(Exception ex){
            return null;
        }
    }

    public void mensaje(String texto){
        JOptionPane.showMessageDialog(null, texto);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_id_producto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_precio_uni = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_precio_vent = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_stock = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_proveedor = new javax.swing.JTextField();
        jButton_Back = new javax.swing.JButton();
        jButton_agregar = new javax.swing.JButton();
        jButton_eliminar = new javax.swing.JButton();
        jButton_modificar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_productos = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton_instru = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ID:");

        txt_id_producto.setBackground(new java.awt.Color(255, 255, 255));
        txt_id_producto.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txt_id_producto.setForeground(new java.awt.Color(0, 0, 0));
        txt_id_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id_productoActionPerformed(evt);
            }
        });
        txt_id_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_id_productoKeyTyped(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Nombre:");

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

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Precio Unitario $:");

        txt_precio_uni.setBackground(new java.awt.Color(255, 255, 255));
        txt_precio_uni.setForeground(new java.awt.Color(0, 0, 0));
        txt_precio_uni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_precio_uniActionPerformed(evt);
            }
        });
        txt_precio_uni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_precio_uniKeyTyped(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Precio Venta $:");

        txt_precio_vent.setBackground(new java.awt.Color(255, 255, 255));
        txt_precio_vent.setForeground(new java.awt.Color(0, 0, 0));
        txt_precio_vent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_precio_ventActionPerformed(evt);
            }
        });
        txt_precio_vent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_precio_ventKeyTyped(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Stock:");

        txt_stock.setBackground(new java.awt.Color(255, 255, 255));
        txt_stock.setForeground(new java.awt.Color(0, 0, 0));
        txt_stock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_stockKeyTyped(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Proveedor:");

        txt_proveedor.setBackground(new java.awt.Color(255, 255, 255));
        txt_proveedor.setForeground(new java.awt.Color(0, 0, 0));
        txt_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_proveedorActionPerformed(evt);
            }
        });
        txt_proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_proveedorKeyTyped(evt);
            }
        });

        jButton_Back.setBackground(new java.awt.Color(0, 0, 0));
        jButton_Back.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Back.setText("Salir");
        jButton_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BackActionPerformed(evt);
            }
        });

        jButton_agregar.setBackground(new java.awt.Color(0, 102, 255));
        jButton_agregar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton_agregar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_agregar.setText("Agregar");
        jButton_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_agregarActionPerformed(evt);
            }
        });

        jButton_eliminar.setBackground(new java.awt.Color(0, 102, 255));
        jButton_eliminar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton_eliminar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_eliminar.setText("Eliminar");
        jButton_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_eliminarActionPerformed(evt);
            }
        });

        jButton_modificar.setBackground(new java.awt.Color(0, 102, 255));
        jButton_modificar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton_modificar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_modificar.setText("Modificar");
        jButton_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificarActionPerformed(evt);
            }
        });

        jTable_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Stock", "$ Venta", "$ Unitario", "Usuario-Proveedor"
            }
        ));
        jTable_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_productosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_productos);

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton_instru.setBackground(new java.awt.Color(0, 0, 0));
        jButton_instru.setForeground(new java.awt.Color(255, 255, 255));
        jButton_instru.setText("Instrucciones");
        jButton_instru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_instruActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_id_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_precio_uni, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txt_precio_vent, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(jButton_agregar)
                        .addGap(33, 33, 33)
                        .addComponent(jButton_eliminar)
                        .addGap(35, 35, 35)
                        .addComponent(jButton_modificar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jButton_instru)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Back)
                        .addGap(28, 28, 28)))
                .addGap(59, 59, 59))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_id_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txt_precio_uni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_precio_vent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_modificar)
                    .addComponent(jButton_agregar)
                    .addComponent(jButton_eliminar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Back)
                    .addComponent(jButton_instru))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_precio_ventKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precio_ventKeyTyped
        
    }//GEN-LAST:event_txt_precio_ventKeyTyped

    private void txt_id_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id_productoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_productoActionPerformed

    private void txt_precio_ventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_precio_ventActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_precio_ventActionPerformed

    private void jButton_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_agregarActionPerformed
        this.ingresarRegistro();
    }//GEN-LAST:event_jButton_agregarActionPerformed

    private void jButton_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_eliminarActionPerformed
        this.eliminarRegistro();
    }//GEN-LAST:event_jButton_eliminarActionPerformed

    private void jButton_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modificarActionPerformed
        this.modificarRegistro();
    }//GEN-LAST:event_jButton_modificarActionPerformed

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void txt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyTyped

    }//GEN-LAST:event_txt_nombreKeyTyped

    private void txt_precio_uniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_precio_uniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_precio_uniActionPerformed

    private void txt_precio_uniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precio_uniKeyTyped
        
    }//GEN-LAST:event_txt_precio_uniKeyTyped

    private void txt_stockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_stockKeyTyped
        char c=evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_txt_stockKeyTyped

    private void txt_proveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_proveedorKeyTyped
        char c=evt.getKeyChar();
        if((c<' '|| c>' ') )evt.consume();
    }//GEN-LAST:event_txt_proveedorKeyTyped

    private void jButton_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BackActionPerformed
        this.hide();
        Login back = new Login();
        back.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton_BackActionPerformed

    private void jTable_productosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_productosMouseClicked
        clic_tabla = jTable_productos.rowAtPoint(evt.getPoint());
        
        
        int id = (int)jTable_productos.getValueAt(clic_tabla, 0);
        String nombre = " "+jTable_productos.getValueAt(clic_tabla, 1);
        int stoc = (int)jTable_productos.getValueAt(clic_tabla, 2);
        double precio_vent = (double)jTable_productos.getValueAt(clic_tabla, 3);
        double precio_uni = (double)jTable_productos.getValueAt(clic_tabla, 4);
        String provee = " "+jTable_productos.getValueAt(clic_tabla, 5);
        

        txt_id_producto.setText(String.valueOf(id));
        txt_nombre.setText(nombre);
        txt_precio_uni.setText(String.valueOf(precio_uni));
        txt_precio_vent.setText(String.valueOf(precio_vent));
        txt_stock.setText(String.valueOf(stoc));
        txt_proveedor.setText(provee);

        
    }//GEN-LAST:event_jTable_productosMouseClicked

    private void txt_id_productoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_id_productoKeyTyped
        char c=evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_txt_id_productoKeyTyped

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        
        txt_proveedor.setText((String) jComboBox1.getSelectedItem());
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void txt_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_proveedorActionPerformed
        
    }//GEN-LAST:event_txt_proveedorActionPerformed

    private void jButton_instruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_instruActionPerformed
        mensaje("Da clic en la tabla para seleccionar un producto");
        mensaje("Para modificar un registro seleccione el producto \n y cambie los datos en la caja de texto");
        mensaje("Para eliminar un registro seleccione el producto \n y de clic en el botón borrar");
    }//GEN-LAST:event_jButton_instruActionPerformed

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
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Productos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Back;
    private javax.swing.JButton jButton_agregar;
    private javax.swing.JButton jButton_eliminar;
    private javax.swing.JButton jButton_instru;
    private javax.swing.JButton jButton_modificar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_productos;
    private javax.swing.JTextField txt_id_producto;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_precio_uni;
    private javax.swing.JTextField txt_precio_vent;
    private javax.swing.JTextField txt_proveedor;
    private javax.swing.JTextField txt_stock;
    // End of variables declaration//GEN-END:variables
}
