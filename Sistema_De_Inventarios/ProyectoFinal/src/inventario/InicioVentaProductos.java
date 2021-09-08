
package inventario;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kevin_Paez
 */
public class InicioVentaProductos extends javax.swing.JFrame {

    /**
     * Creates new form InicioVentaProductos
     */
    Limpiar_txt lt = new Limpiar_txt();

    private ArrayList<Producto2> Lis;
    
    
    Producto p;
    Proceso rp;
    Producto2 pro;
    int clic_tabla;
    int clic_tabla2;
    public InicioVentaProductos() {
        initComponents();
        this.setTitle("Venta de Productos");
        this.setLocationRelativeTo(null);
        rp = new Proceso();
        Lis = new ArrayList<Producto2>();
        
        
        try{
            cargar_txt();
            listarRegistro();
            

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
                //p.setPrecio_uni(Double.parseDouble(st.nextToken()));
                //p.setId_proveedor(st.nextToken());
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
            fw = new FileWriter("compra.txt");
            pw = new PrintWriter(fw);
            
            for(int i = 0; i < rp.cantidadRegistro(); i++){
                p = rp.obtenerRegistro(i);
                pw.println(String.valueOf(p.getId_produc()+","+p.getNombre()+","+p.getStock()+","+
                        ","+p.getPrecio_ven()));
            }
            pw.close();
            fw.close();
        }catch(Exception ex){
            mensaje("Error al grabar archivo: "+ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

    
    public void modificarRegistro(){
        
        try{
            if(leerId_produc() == -666)mensaje("Ingresar ID del Producto");
            else if(leerNombre() == null)mensaje("Ingresar Nombre");
            else if(leerStock2() == -666) mensaje("Ingresar Stock");
            else if(leerPrecio_ven() == -666) mensaje("Ingresar Precio Venta");
            else if(leerPrecio_uni() == -666) mensaje("Ingresar Precio Unidad");
            else if(leerProveedor() == null)mensaje("Ingresar Nombre");
            else{
                
                int codigo = rp.buscaCodigo(leerId_produc());
                p = new Producto(leerId_produc(), leerNombre(), leerStock2(), leerPrecio_ven(), leerPrecio_uni(), leerProveedor());
                if(codigo == -1)rp.agregarRegistro(p);
                else rp.modificarRegistro(codigo, p);
                //grabar_txt();
                listarRegistro();
                lt.limpiar_texto(jPanel1);
            }
        }catch(Exception ex){
            mensaje(ex.getMessage());
        }
    }
    
    public void modificarRegistro2(){
        
        try{
            if(leerId_produc() == -666)mensaje("Ingresar ID del Producto");
            else if(leerNombre() == null)mensaje("Ingresar Nombre");
            else if(leerStock3() == -666) mensaje("Ingresar Stock");
            else if(leerPrecio_ven() == -666) mensaje("Ingresar Precio Venta");
            else if(leerPrecio_uni() == -666) mensaje("Ingresar Precio Unidad");
            else if(leerProveedor() == null)mensaje("Ingresar Nombre");
            else{
                
                int codigo = rp.buscaCodigo(leerId_produc());
                p = new Producto(leerId_produc(), leerNombre(), leerStock3(), leerPrecio_ven(), leerPrecio_uni(), leerProveedor());
                if(codigo == -1)rp.agregarRegistro(p);
                else rp.modificarRegistro(codigo, p);
                //grabar_txt();
                listarRegistro();
                lt.limpiar_texto(jPanel1);
            }
        }catch(Exception ex){
            mensaje(ex.getMessage());
        }  
        
    }
    
    public int leerStock2(){
        Producto2 aux;
        int i,c;
        try{
            c = Integer.parseInt(txt_cantidad.getText().trim());
            int stoc = Integer.parseInt(txt_stock.getText().trim());
            
            stoc = stoc - c;
            
            return stoc;
        }catch(Exception ex){
            return -666;
        }
    }
    
    public int leerStock3(){
        Producto2 aux;
        int i,c;
        try{
            c = Integer.parseInt(txt_cantidad.getText().trim());
            int stoc = Integer.parseInt(txt_stock.getText().trim());
            
            stoc = stoc + c;
            
            return stoc;
        }catch(Exception ex){
            return -666;
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
        dt.addColumn("$ Precio");
        
        jTable_productos.setDefaultRenderer(Object.class, new FormTable());
        
        Object fila[] = new Object[dt.getColumnCount()];
        for(int i = 0; i < rp.cantidadRegistro(); i++){
            p = rp.obtenerRegistro(i);
            fila[0] = p.getId_produc();
            fila[1] = p.getNombre();
            fila[2] = p.getStock();
            fila[3] = p.getPrecio_ven();            
            dt.addRow(fila);
        }
        jTable_productos.setModel(dt);
        jTable_productos.setRowHeight(20);
        
        
    }

    
    public void ingresar(){
        String nom;
        double preven;
        int stoc, id_pro,c;
        Producto2 aux;
        
        try {
            if(leerId_produc() == -666)mensaje("Ingresar ID del Producto");
            else if(leerNombre() == null)mensaje("Ingresar Nombre");
            else if(leerStock() == -666) mensaje("Ingresar Stock");
            else if(leerPrecio_ven() == -666) mensaje("Ingresar Precio");            
            else{
                id_pro = Integer.parseInt(txt_id_producto.getText());
                nom = txt_nombre.getText();
                stoc = Integer.parseInt(txt_stock.getText());
                preven = Double.parseDouble(txt_precio_vent.getText());
 
                c = Integer.parseInt(txt_cantidad.getText());
                
                if(c <= stoc ){
                    Lis.add(new Producto2(id_pro, nom, c, preven));
                    modificarRegistro();
                    pedidos(c);
                    
                    
                }else{
                    mensaje("No hay suficentes productos");
                }

            }
        } catch (Exception e) {
            mensaje("Escriba la cantidad que desea comprar ");
        }
        verDatos();
        
    }
    
    
    private void verDatos(){
        String Mat[][]=new String[Lis.size()][4];
        Producto2 aux;
        for(int i=0; i< Lis.size(); i++){
            aux = Lis.get(i);
            Mat[i][0] = Integer.toString(aux.getId_produc());
            Mat[i][1] = aux.getNombre();
            Mat[i][2] = Integer.toString(aux.getStock());
            Mat[i][3] = Double.toString(aux.getPrecio_ven()); 
        }
        
        jTable_produc_adqui.setModel(new javax.swing.table.DefaultTableModel(
            Mat,
            new String [] {
                "ID", "Nombre", "Cantidad", "$ Precio"
            }
        ));
    }
    
    private void guardar(){
        File file = new File("compra.txt");
        PrintWriter escribe;
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (Exception e) {
            }
        }
        try {
            Producto2 aux;
            escribe = new PrintWriter(file,"utf-8");
            for(int i=0; i<Lis.size(); i++){
                aux = Lis.get(i);
                
                escribe.println(String.valueOf(aux.getId_produc()+","+aux.getNombre()+","+aux.getStock()+","+aux.getPrecio_ven()));
            }
            escribe.close();
        } catch (Exception e) {
        }
    }

    
    void eliminar(){
        int c;
        try {
            c = jTable_produc_adqui.getSelectedRow();
            Lis.remove(c);
            
            modificarRegistro2();
            
        } catch (Exception e) {
            mensaje("Seleccione un producto");
        }
        
        verDatos();
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
    
    public double leerPrecio_ven(){
        try{
            double precio_uni = Double.parseDouble(txt_precio_vent.getText().trim());
            return precio_uni;
        }catch(Exception ex){
            return -666;
        }
    }
    
    public double leerPrecio_uni(){
        try{
            double precio_uni=0; //= Double.parseDouble(txt_precio_uni.getText().trim());
            return precio_uni;
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
            String prove ="";// txt_proveedor.getText().trim().replace(" ", "_");
            return prove;
        }catch(Exception ex){
            return null;
        }
    }

    
    public void pedidos(int cant){
        int pedido;
        pedido= cant/20;
        
        String p = Integer.toString(pedido);
        try {
            if(pedido==1){
                jLabel1.setText(p);
            }else{
                jLabel1.setText("0");
            }
            
            if(pedido >= 2){
                jLabel12.setText(p);
            }else{
                jLabel12.setText("0");
            }
            
        } catch (Exception e) {
            mensaje("no se puede");
        }
        
    }
    
    public void limpiar(){
        jLabel1.setText("0");
        jLabel12.setText("0");
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
        jLabel5 = new javax.swing.JLabel();
        txt_id_producto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton_salir = new javax.swing.JButton();
        jButton_next = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton_sesion = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_productos = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jButton_agregar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_produc_adqui = new javax.swing.JTable();
        txt_stock = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_precio_vent = new javax.swing.JTextField();
        jButton_eliminar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txt_cantidad = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton_instru = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("ID:");

        txt_id_producto.setBackground(new java.awt.Color(255, 255, 255));
        txt_id_producto.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txt_id_producto.setForeground(new java.awt.Color(0, 0, 0));
        txt_id_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_id_productoKeyTyped(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Cantidad:");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("No. de Pedido Simple:");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No. de Pedido compuesto:");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Productos Disponibles");

        jButton_salir.setBackground(new java.awt.Color(255, 0, 0));
        jButton_salir.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton_salir.setForeground(new java.awt.Color(255, 255, 255));
        jButton_salir.setText("Salir");
        jButton_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_salirActionPerformed(evt);
            }
        });

        jButton_next.setBackground(new java.awt.Color(0, 0, 0));
        jButton_next.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton_next.setForeground(new java.awt.Color(255, 255, 255));
        jButton_next.setText("Pagar");
        jButton_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_nextActionPerformed(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Eres Proveedor inicia sesión dando ");

        jButton_sesion.setBackground(new java.awt.Color(255, 255, 255));
        jButton_sesion.setForeground(new java.awt.Color(0, 0, 0));
        jButton_sesion.setText("clic aquí");
        jButton_sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_sesionActionPerformed(evt);
            }
        });

        jTable_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Stock", "$ Precio"
            }
        ));
        jTable_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_productosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_productos);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Productos Adquiridos");

        jButton_agregar.setBackground(new java.awt.Color(0, 102, 255));
        jButton_agregar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_agregar.setText("Agregar");
        jButton_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_agregarActionPerformed(evt);
            }
        });

        jTable_produc_adqui.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Stock", "$ Precio"
            }
        ));
        jTable_produc_adqui.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_produc_adquiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_produc_adqui);

        txt_stock.setBackground(new java.awt.Color(255, 255, 255));
        txt_stock.setForeground(new java.awt.Color(0, 0, 0));
        txt_stock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_stockKeyTyped(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nombre:");

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

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Precio Venta $:");

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

        jButton_eliminar.setBackground(new java.awt.Color(0, 102, 255));
        jButton_eliminar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_eliminar.setText("Eliminar Producto");
        jButton_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_eliminarActionPerformed(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Stock:");

        txt_cantidad.setBackground(new java.awt.Color(255, 255, 255));
        txt_cantidad.setForeground(new java.awt.Color(0, 0, 0));
        txt_cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cantidadKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("0");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("0");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(275, 275, 275))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton_instru)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_eliminar)
                                .addGap(122, 122, 122)))
                        .addComponent(jButton_salir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_next)
                        .addGap(29, 29, 29))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 18, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(37, 37, 37)
                                        .addComponent(txt_id_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(54, 54, 54)
                                        .addComponent(jLabel10))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_precio_vent, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_agregar))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(35, 35, 35)
                                .addComponent(txt_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_id_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txt_precio_vent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButton_agregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel9)
                            .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_salir)
                            .addComponent(jButton_next))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_eliminar)
                            .addComponent(jButton_instru))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton_salirActionPerformed

    private void jButton_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_nextActionPerformed
        this.guardar();
        this.hide();
        Pago next = new Pago();
        next.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton_nextActionPerformed

    private void jButton_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_sesionActionPerformed
        this.hide();
        Login l1 = new Login();
        l1.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton_sesionActionPerformed

    private void jButton_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_agregarActionPerformed
        this.ingresar();
        
    }//GEN-LAST:event_jButton_agregarActionPerformed

    private void txt_id_productoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_id_productoKeyTyped
        char c=evt.getKeyChar();
        if((c<' '|| c>' ') )evt.consume();
    }//GEN-LAST:event_txt_id_productoKeyTyped

    private void txt_stockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_stockKeyTyped
        char c=evt.getKeyChar();
        if((c<' '|| c>' ') )evt.consume();
    }//GEN-LAST:event_txt_stockKeyTyped

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void txt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyTyped
        char c=evt.getKeyChar();
        if((c<' '|| c>' ') )evt.consume();
    }//GEN-LAST:event_txt_nombreKeyTyped

    private void txt_precio_ventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_precio_ventActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_precio_ventActionPerformed

    private void txt_precio_ventKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precio_ventKeyTyped
        char c=evt.getKeyChar();
        if((c<' '|| c>' ') )evt.consume();
    }//GEN-LAST:event_txt_precio_ventKeyTyped

    private void jTable_productosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_productosMouseClicked
        clic_tabla = jTable_productos.rowAtPoint(evt.getPoint());
        
        
        int id = (int)jTable_productos.getValueAt(clic_tabla, 0);
        String nombre = " "+jTable_productos.getValueAt(clic_tabla, 1);
        int stoc = (int)jTable_productos.getValueAt(clic_tabla, 2);
        double precio_vent = (double)jTable_productos.getValueAt(clic_tabla, 3);

        txt_id_producto.setText(String.valueOf(id));
        txt_nombre.setText(nombre);
        txt_stock.setText(String.valueOf(stoc));
        txt_precio_vent.setText(String.valueOf(precio_vent));
        limpiar();
    }//GEN-LAST:event_jTable_productosMouseClicked

    private void jButton_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_eliminarActionPerformed
        this.eliminar();
        
    }//GEN-LAST:event_jButton_eliminarActionPerformed

    private void txt_cantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantidadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cantidadKeyTyped

    private void jTable_produc_adquiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_produc_adquiMouseClicked
        clic_tabla2 = jTable_produc_adqui.rowAtPoint(evt.getPoint());
        clic_tabla = jTable_productos.rowAtPoint(evt.getPoint());

        int stoc = (int)jTable_productos.getValueAt(clic_tabla, 2);
        txt_stock.setText(String.valueOf(stoc));
        txt_id_producto.setText(String.valueOf(jTable_produc_adqui.getValueAt(clic_tabla2, 0)));
        txt_nombre.setText(String.valueOf(jTable_produc_adqui.getValueAt(clic_tabla2, 1)));
        txt_cantidad.setText(String.valueOf(jTable_produc_adqui.getValueAt(clic_tabla2, 2)));
        txt_precio_vent.setText(String.valueOf(jTable_produc_adqui.getValueAt(clic_tabla2, 3)));
        
        
    }//GEN-LAST:event_jTable_produc_adquiMouseClicked

    private void jButton_instruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_instruActionPerformed
        mensaje("Da clic en la tabla para agregar un producto");
        mensaje("Si quieres eliminar un producto \n"
                + "selecciona ese mismo producto\n "
                + "tanto de la tabla"+" '" +"productos disponibles"+"'," +"\n "+ "como de la tabla"+" '" +"productos adquiridos"+"'" +" \n "
                + "después, de clic en el botón eliminar");
        
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
            java.util.logging.Logger.getLogger(InicioVentaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioVentaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioVentaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioVentaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioVentaProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_agregar;
    private javax.swing.JButton jButton_eliminar;
    private javax.swing.JButton jButton_instru;
    private javax.swing.JButton jButton_next;
    private javax.swing.JButton jButton_salir;
    private javax.swing.JButton jButton_sesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_produc_adqui;
    private javax.swing.JTable jTable_productos;
    private javax.swing.JTextField txt_cantidad;
    private javax.swing.JTextField txt_id_producto;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_precio_vent;
    private javax.swing.JTextField txt_stock;
    // End of variables declaration//GEN-END:variables

    
}
