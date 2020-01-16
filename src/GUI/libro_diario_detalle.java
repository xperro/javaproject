package GUI;

import Clases.funciones;
import ObjetosDB.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class libro_diario_detalle extends javax.swing.JDialog implements MouseListener {

    int usuario, mnsj, num_celdas = 45;
    String vacio = "NO SE OBTUVO RESULTADO CON LOS PARAMETROS INGRESADOS.";
    String correcto = "OPERACIÓN REALIZADA CON ÉXITO.", error = "HA OCURRIDO EL SIGUIENTE ERROR:\n";
    String no_empleado = "NO SE REGISTRA EMPLEADO CON EL RUT INGRESADO.";
    String no_opcion = "DEBE SELECCIONAR UNA DE LAS OPCIONES DEL SISTEMA.";
    String no_numero = "DEBE INGRESAR UN DATO NUMÉRICO EN EL CAMPO ";
    String num_no_valido = "DEBE INGRESAR UN NÚMERO VÁLIDO EN EL CAMPO ";
    String campo_vacio = "DEBE COMPLETAR EL SIGUIENTE CAMPO:\n";
      ArrayList<CompraProducto> ComprasDia=null;
       ArrayList<VentaProducto> ventasDia=null;
    DefaultTableModel modelo = new DefaultTableModel() {

        public boolean isCellEditable(int row, int column) { // sobreescribe el metodo para convertir la celda ingresada en NO editable
            return false;
        }
    };
   DefaultTableModel modelo2 = new DefaultTableModel() {

        public boolean isCellEditable(int row, int column) { // sobreescribe el metodo para convertir la celda ingresada en NO editable
            return false;
        }
    };
    public libro_diario_detalle(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        iniciar();
    }

    libro_diario_detalle(java.awt.Frame parent, int rut) throws SQLException {
        super(parent, true);
        initComponents();
        
        this.usuario = rut;
        iniciar();
    }

    private void iniciar() throws SQLException { 
        String titulos[] = {"ID VENTA", "N° BOLETA", "FECHA", "RUT","PROVEEDOR","PRODUCTO", "MEDIDA","UNIDAD","CANTIDAD","PRECIO UNITARIO","IVA","HARINA","PRECIO BRUTO","TOTAL","MEDIO PAGO"};
 String titulos2[] = {"ID COMPRA", "N°GUIA/FACTURA", "FECHA", "RUT","PROVEEDOR","PRODUCTO", "MEDIDA","UNIDAD","CANTIDAD","PRECIO UNITARIO","IVA","HARINA","PRECIO BRUTO","TOTAL"};

        
        modelo.setColumnIdentifiers(titulos);
        modelo.setNumRows(0);
         modelo2.setColumnIdentifiers(titulos2);
        modelo2.setNumRows(0);
        jTable1.setModel(modelo);
        int suma=0;
         int suma2=0;

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr2.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(tcr2);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(tcr);
    
        ((DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jButton1.addMouseListener(this);
        jButton2.addMouseListener(this);
          Calendar c2 = new GregorianCalendar();
          c2.add(Calendar.MONTH, 0);
   jDateChooser2.setCalendar(c2); 
        try {
            metodosDB metodos = new metodosDB();
         
             //  DateFormat dateFormat =jDateChooser2.toString()
           // Calendar cal = Calendar.getInstance();
       // String FechaHoy = dateFormat.format(cal.getTime());
             modelo.setNumRows(0);
                   Calendar c = new GregorianCalendar();
             Date date = jDateChooser2.getDate();
                jDateChooser2.setCalendar(c);
                 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 10:00:00");
        Calendar cal = Calendar.getInstance();
        String FechaHoy = dateFormat.format(jDateChooser2.getCalendar().getTime());
        
        
        Date date2 = jDateChooser3.getDate();
          
                 DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd 03:59:59");
                 
        Calendar cal2 = Calendar.getInstance();
          cal2.add( Calendar.DATE, 1 ); jDateChooser3.setCalendar(cal2);
        String FechaHoy2 = dateFormat2.format(jDateChooser3.getCalendar().getTime());
              ArrayList<OrdenDeVenta> ordenesDia = new metodosDB().getVentasDia(FechaHoy,FechaHoy2);
          int i=0;System.out.println("orden"+ordenesDia.size());
                 for(OrdenDeVenta orden: ordenesDia)
                 {  System.out.println("hola");
                               ArrayList<VentaProducto> ventasDia = new metodosDB().getVentaProductoByIdOrdenVenta(orden.getIdOrdenVenta());   

                  for(VentaProducto ordenn: ventasDia)
            {
          Productos producto= new Productos();
producto=new metodosDB().getProductoById(ordenn.getIdProducto());
           ArrayList<Cliente> proveedor =new metodosDB().getClientesById(ordenesDia.get(i).getCliente().getIdCliente());
           if(proveedor.size()>1){
                          jframe1 a = new jframe1();
            JOptionPane.showMessageDialog(a, "EXISTE UN PROBLEMA CON LOS PROVEEDORES (ALCANCE DE NOMBRES)");
             ;
           }
             Object[] object = new Object[15];
            object[0] = orden.getIdOrdenVenta();
            object[1] = orden.getNumeroBoleta();
            if(metodos.getProductoById(ordenn.getIdProducto()).getHarina()==1       ){
                int harina=0;
               harina= (int) (metodos.getProductoById(ordenn.getIdProducto()).getPrecioVenta()*0.12);
                  object[2] = ordenn.getPrecioTotalFinal()+harina;
            }else{
                object[2] = ordenn.getPrecioTotalFinal();  
            }
            
            object[2] = orden.getFecha();
               object[3] =proveedor.get(0).getRut().toString();
            object[4] = proveedor.get(0).getNombre();
              object[5] = ordenn.getProducto().getNombre();
               object[6] = ordenn.getProducto().getTalla();
                object[7] = ordenn.getProducto().getMedida();
                
            object[8] = ordenn.getCantidadProducto();
                 object[9] = ordenn.getPrecioUnitarioNeto();
                           if(ordenn.getProducto().getHarina()==1){
                        double imp2=  (double) (ordenn.getPrecioUnitarioNeto()*0.19);
                          double imp=  (double) (ordenn.getPrecioUnitarioNeto()*0.12);
                    System.out.println(imp2);
             int var3 = (int) Math.floor(imp2); 
             int var4 = (int) Math.floor(imp); 
                      object[10] = var3;
                      object[11] = var4;
                 }else{
                     
                     double imp2=  (double) (ordenn.getPrecioUnitarioNeto()*0.19);
                    System.out.println(imp2);
             int var3 = (int) Math.floor(imp2); 
                      object[10] = var3;
                      object[11] = 0;
                 }
                           System.out.println(ordenn.getPrecioUnitarioFinal());
                           object[12] = ordenn.getPrecioUnitarioFinal();
                           object[13] = ordenn.getPrecioUnitarioFinal()*ordenn.getCantidadProducto();
                      suma=suma+ordenn.getPrecioUnitarioFinal()*ordenn.getCantidadProducto();;
                           object[14] = orden.getMedioPago();
            modelo.addRow(object);
            
       
        
            } 
                i++;  } 
               
                 
                modelo2.setNumRows(0);
                ComprasDia=null;
       ComprasDia = new metodosDB().getCompraProductoFecha(FechaHoy,FechaHoy2);
       
       if(ComprasDia.size()<=0)
       {
           
       }else{
       
     int k=0; 
     System.out.println("compras: "+ ComprasDia.size());
            for(CompraProducto orden: ComprasDia)
            {
     Productos producto=new metodosDB().getProductoByIdCompras(ComprasDia.get(k).getIdProductoCompra());
     int ordenid=ComprasDia.get(k).getIdOrdenDeCompra();
     int factura=0;System.out.println("LA ID ORDEN DE COMPRA ES: "+ordenid);
            factura= new metodosDB().getOrdenDeCompraById(ordenid).getNumeroFacturaRecibida();

          System.out.println("PROVEEDOR: "+ComprasDia.get(k).getProveedor());
           ArrayList<Cliente> proveedor =new metodosDB().getClientesByName(ComprasDia.get(k).getProveedor());
        Object[] object = new Object[14];
            object[0] = ComprasDia.get(k).getIdCompraProducto();
            object[1] = factura;
            object[2] = ComprasDia.get(k).getFecha();
            object[3] = proveedor.get(0).getRut();
            object[4] = proveedor.get(0).getNombre(); //producto.getNombre()+" "+producto.getTalla()+" "+producto.getMedida();
             object[5] = producto.getNombre();
              object[6] = producto.getTalla();
            object[7] = producto.getMedida();
             object[8] = ComprasDia.get(k).getCantidad();
           
              
              
                 if(producto.getHarina()==1){
                        double imp2=  (double) ( producto.getPrecioCompra()*0.19);
                          double imp=  (double) ( producto.getPrecioCompra()*0.12);
                    System.out.println(imp2);
             int var3 = (int) Math.floor(imp2); 
             int var4 = (int) Math.floor(imp); 
                      object[10] = var3;
                      object[11] = var4;
                        object[9] = producto.getPrecioCompra()-var3-var4;
                 }else{
                     
                     double imp2=  (double) (producto.getPrecioCompra()*0.19);
                    System.out.println(imp2);
             int var3 = (int) Math.floor(imp2); 
                      object[10] = var3;
                      object[11] = 0;
                       object[9] = producto.getPrecioCompra()-var3;
                 }
                      object[12] = ComprasDia.get(k).getMontoUnitario();
               object[13] = ComprasDia.get(k).getMontoTotal();
            modelo2.addRow(object);
            suma2 = suma2 +ComprasDia.get(k).getMontoTotal();
        k++;
        
             
            } 
            }
            
            
            
            
                jTextField1.setText(Integer.toString(suma));
                 
                 
                 
        } catch (SQLException ex) {
            Logger.getLogger(libro_diario_detalle.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Funcion que debe cargar todos los prestamos desde la BD y agregarlos a la tabla
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LIBRO DIARIO");
        setModal(true);
        setResizable(false);

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/zoom.png"))); // NOI18N
        jButton1.setText("BUSCAR");
        jButton1.setPreferredSize(new java.awt.Dimension(137, 35));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "VENTAS AL DETALLE (DEBITO)", 2, 0, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/delete.png"))); // NOI18N
        jButton2.setText("CERRAR");
        jButton2.setPreferredSize(new java.awt.Dimension(135, 35));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Export To Document.png"))); // NOI18N
        jButton5.setText("ARCHIVO");
        jButton5.setToolTipText("");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel3.setText("BUSCAR POR FECHA");
        jLabel3.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel1.setText("MONTO VENTAS : ");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField1.setText("0");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jDateChooser3.setDateFormatString("yyyy-MM-dd 03:59:59");
        jDateChooser3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("-");

        jDateChooser2.setDateFormatString("yyyy-MM-dd 10:00:00");
        jDateChooser2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1168, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(71, 71, 71)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    private String getFecha()
 {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String FechaHoy = dateFormat.format(cal.getTime());
        return FechaHoy;
 }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 try {int suma=0;
 int suma2=0;
            metodosDB metodos = new metodosDB();
         
             //  DateFormat dateFormat =jDateChooser2.toString()
           // Calendar cal = Calendar.getInstance();
       // String FechaHoy = dateFormat.format(cal.getTime());
             modelo.setNumRows(0);
             
           Date date = jDateChooser2.getDate();
                 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 13:00:00");
        Calendar cal = Calendar.getInstance();
        String FechaHoy = dateFormat.format(cal.getTime());
        
                 
                    
         Date date2 = jDateChooser3.getDate();
                    DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd 03:59:59");
        Calendar cal2 = Calendar.getInstance();
        cal2.add( Calendar.DATE, 1 );
        String FechaHoy2 = dateFormat2.format(cal2.getTime());
                 
              ArrayList<OrdenDeVenta> ordenesDia = new metodosDB().getVentasDia(FechaHoy,FechaHoy2);
          int i=0;System.out.println("orden"+ordenesDia.size());
                 for(OrdenDeVenta orden: ordenesDia)
                 {  System.out.println("hola");
                               ArrayList<VentaProducto> ventasDia = new metodosDB().getVentaProductoByIdOrdenVenta(orden.getIdOrdenVenta());   

                  for(VentaProducto ordenn: ventasDia)
            {
          Productos producto= new Productos();
producto=new metodosDB().getProductoByIdCompras(ordenn.getIdProducto());
      ArrayList<Cliente> proveedor =new metodosDB().getClientesByName(producto.getProveedor());
           if(proveedor.size()>1){
                          jframe1 a = new jframe1();
            JOptionPane.showMessageDialog(a, "EXISTE UN PROBLEMA CON LOS PROVEEDORES (ALCANCE DE NOMBRES)");
             ;
           }
              Object[] object = new Object[15];
            object[0] = orden.getIdOrdenVenta();
            object[1] = orden.getNumeroBoleta();
            if(metodos.getProductoById(ordenn.getIdProducto()).getHarina()==1       ){
                int harina=0;
               harina= (int) (metodos.getProductoById(ordenn.getIdProducto()).getPrecioVenta()*0.12);
                  object[2] = ordenn.getPrecioTotalFinal()+harina;
            }else{
                object[2] = ordenn.getPrecioTotalFinal();  
            }
            
            object[2] = orden.getFecha();
               object[3] = proveedor.get(0).getRut().toString();
            object[4] = proveedor.get(0).getNombre();
              object[5] = ordenn.getProducto().getNombre();
               object[6] = ordenn.getProducto().getTalla();
                object[7] = ordenn.getProducto().getMedida();
                
            object[8] = ordenn.getCantidadProducto();
                 object[9] = ordenn.getPrecioUnitarioNeto();
                           if(ordenn.getProducto().getHarina()==1){
                        double imp2=  (double) (ordenn.getPrecioUnitarioNeto()*0.19);
                          double imp=  (double) (ordenn.getPrecioUnitarioNeto()*0.12);
                    System.out.println(imp2);
             int var3 = (int) Math.floor(imp2); 
             int var4 = (int) Math.floor(imp); 
                      object[10] = var3;
                      object[11] = var4;
                 }else{
                     
                     double imp2=  (double) (ordenn.getPrecioUnitarioNeto()*0.19);
                    System.out.println(imp2);
             int var3 = (int) Math.floor(imp2); 
                      object[10] = var3;
                      object[11] = 0;
                 }
                           System.out.println(ordenn.getPrecioUnitarioFinal());
                           object[12] = ordenn.getPrecioUnitarioFinal();
                           object[13] = ordenn.getPrecioUnitarioFinal()*ordenn.getCantidadProducto();
                      suma=suma+ordenn.getPrecioUnitarioFinal()*ordenn.getCantidadProducto();;
                           object[14] = orden.getMedioPago();
            modelo.addRow(object);
            
        i++;
        
            } 
                 } 
               
                 
                modelo2.setNumRows(0);
                ComprasDia=null;
       ComprasDia = new metodosDB().getCompraProductoFecha(FechaHoy,FechaHoy2);
       
       if(ComprasDia.size()<=0)
       {
           
       }else{
            
     int k=0; 
     System.out.println("compras: "+ ComprasDia.size());
            for(CompraProducto orden: ComprasDia)
            {
                Productos producto=new metodosDB().getProductoById(ComprasDia.get(k).getIdProductoCompra());
int factura=new metodosDB().getOrdenDeCompraById(ComprasDia.get(k).getIdOrdenDeCompra()).getNumeroFacturaRecibida();
          System.out.println("PROVEEDOR: "+ComprasDia.get(k).getProveedor());
           ArrayList<Cliente> proveedor =new metodosDB().getClientesByName(ComprasDia.get(k).getProveedor());
        Object[] object = new Object[14];
            object[0] = ComprasDia.get(k).getIdCompraProducto();
            object[1] = factura;
            object[2] = ComprasDia.get(k).getFecha();
            object[3] = proveedor.get(0).getRut();System.out.println(proveedor.get(0).getRut());
            object[4] = proveedor.get(0).getNombre();System.out.println(proveedor.get(0).getNombre()); //producto.getNombre()+" "+producto.getTalla()+" "+producto.getMedida();
             object[5] = producto.getNombre();
              object[6] = producto.getTalla();
            object[7] = producto.getMedida();
             object[8] = ComprasDia.get(k).getCantidad();
            
              
                 if(producto.getHarina()==1){
                        double imp2=  (double) ( producto.getPrecioCompra()*0.19);
                          double imp=  (double) ( producto.getPrecioCompra()*0.12);
                    System.out.println(imp2);
             int var3 = (int) Math.floor(imp2); 
             int var4 = (int) Math.floor(imp); 
                      object[10] = var3;
                      object[11] = var4;
                        object[9] = producto.getPrecioCompra()-var3-var4;
                 }else{
                     
                     double imp2=  (double) ( producto.getPrecioCompra()*0.19);
                    System.out.println(imp2);
             int var3 = (int) Math.floor(imp2); 
                      object[10] = var3;
                      object[11] = 0;
                        object[9] = producto.getPrecioCompra()-var3;
                 }
                 
              object[12] = ComprasDia.get(k).getMontoUnitario();
               object[13] = ComprasDia.get(k).getMontoTotal();
            modelo2.addRow(object);
            suma2 = suma2 +ComprasDia.get(k).getMontoTotal();
        k++;
        
             
            } 
            }
            
            
            
            System.out.println("las sumas 1 son:"+suma);
                jTextField1.setText(Integer.toString(suma));
                System.out.println("las sumas 2 son:"+suma2);   
                 
                 
        } catch (SQLException ex) {
            Logger.getLogger(libro_diario_detalle.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    

    }//GEN-LAST:event_jTable1MouseClicked
public void exportVentas(){try{
    ArrayList<OrdenDeVenta> ordenesDia = null;
 SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
              String date2=formatter.format(jDateChooser2.getDate());
           SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMddHHmmss");
              String date1=formatter2.format(jDateChooser2.getDate());
               Date date3 = jDateChooser3.getDate();
                 DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Calendar cal2 = Calendar.getInstance();
        String FechaHoy2 = dateFormat2.format(jDateChooser3.getCalendar().getTime());
            ordenesDia = new metodosDB().getVentasDia(date2.trim(),FechaHoy2);
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            funciones f = new funciones();
            File proveedores = new File(f.getRutaExcel() + "libroDiarioVentas "+date1+".xls");
            WritableWorkbook workbook = Workbook.createWorkbook(new File(f.getRutaExcel() + "libroDiarioVentas "+date1+".xls"));
            //Se crea una nueva hoja dentro del libro
            WritableSheet sheet = workbook.createSheet("LIBRO DIARIO", 1);
            sheet.setColumnView(1, 15);
            sheet.setColumnView(2, 17);
            sheet.setColumnView(3, 17);
            sheet.setColumnView(4, 17);
            sheet.setColumnView(5, 18);
            sheet.setColumnView(6, 17);
             int i=0;int k=0;
             int suma=0;     int suma2=0;
            
             for ( i = 0; i < modelo.getRowCount(); i++) {
           
                if (i == 0) {
                    sheet.addCell(new jxl.write.Label(1, k, "ID VENTA"));
                    sheet.addCell(new jxl.write.Label(2, k, "NUMERO VALE"));
                      sheet.addCell(new jxl.write.Label(3, k, "FECHA"));
                    sheet.addCell(new jxl.write.Label(4, k, "RUT"));
                    sheet.addCell(new jxl.write.Label(5, k, "CLIENTE"));
                    sheet.addCell(new jxl.write.Label(6, k, "PRODUCTO"));
                    sheet.addCell(new jxl.write.Label(7, k, "MEDIDA"));
                     sheet.addCell(new jxl.write.Label(8, k, "UNIDAD"));
                         sheet.addCell(new jxl.write.Label(9, k, "CANTIDAD"));
                           sheet.addCell(new jxl.write.Label(10, k, "PRECIO UNITARIO"));
                                   sheet.addCell(new jxl.write.Label(11, k, "IVA"));
                                           sheet.addCell(new jxl.write.Label(12, k, "HARINA"));
                                             sheet.addCell(new jxl.write.Label(13, k, "PRECIO BRUTO"));
                                    sheet.addCell(new jxl.write.Label(14, k, "TOTAL"));
                                     sheet.addCell(new jxl.write.Label(15, k, "MEDIO PAGO"));
                }
                  sheet.addCell(new jxl.write.Label(8, k + 1, (jTable1.getValueAt(i, 7).toString())));
              sheet.addCell(new jxl.write.Label(7, k + 1, jTable1.getValueAt(i, 6).toString()));
                  sheet.addCell(new jxl.write.Label(6, k + 1, jTable1.getValueAt(i, 5).toString()));
                sheet.addCell(new jxl.write.Label(1, k + 1, jTable1.getValueAt(i, 0).toString()));
                sheet.addCell(new jxl.write.Label(3, k + 1, jTable1.getValueAt(i, 2).toString()));
                    int harina=0;       metodosDB metodos = new metodosDB();
                    sheet.addCell(new jxl.write.Label(4, k + 1, jTable1.getValueAt(i, 3).toString()));
              suma2=suma2+Integer.parseInt(jTable1.getValueAt(i, 13).toString());
              System.out.println("lasuma es :"+suma2);
                sheet.addCell(new jxl.write.Number(11, k + 1, Integer.parseInt(jTable1.getValueAt(i, 10).toString())));
                sheet.addCell(new jxl.write.Label(2, k + 1, jTable1.getValueAt(i, 1).toString()));
                sheet.addCell(new jxl.write.Label(5, k + 1, jTable1.getValueAt(i, 4).toString()));
                    sheet.addCell(new jxl.write.Number(9, k + 1,Integer.parseInt(jTable1.getValueAt(i, 8).toString())));
                 sheet.addCell(new jxl.write.Number(10, k + 1,Integer.parseInt(jTable1.getValueAt(i, 9).toString())));
                            sheet.addCell(new jxl.write.Number(12, k + 1,Integer.parseInt(jTable1.getValueAt(i, 11).toString())));
                            sheet.addCell(new jxl.write.Number(13, k + 1,Integer.parseInt(jTable1.getValueAt(i, 12).toString())));
                                       sheet.addCell(new jxl.write.Number(14, k + 1,Integer.parseInt(jTable1.getValueAt(i, 13).toString())));
                                                  sheet.addCell(new jxl.write.Label(15, k + 1,jTable1.getValueAt(i, 14).toString()));
  k++;                 
} k++;
                sheet.addCell(new jxl.write.Label(14,  k + 3, "MONTO TOTAL VENTAS."));
                sheet.addCell(new jxl.write.Number(14, k + 4,suma2 ));    
                
                
                
                
                
            workbook.write();
            workbook.close();
            this.setCursor(Cursor.getDefaultCursor());
            if (Desktop.isDesktopSupported() == true) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.open(proveedores);
                    return;
                } catch (Exception ex) {
                    mnsj = JOptionPane.showConfirmDialog(null, error + ex.getMessage(), "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

        } catch (Exception ex) {
            this.setCursor(Cursor.getDefaultCursor());
            mnsj = JOptionPane.showConfirmDialog(null, error + ex.getMessage(), "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
}


public void exportCompras(){try{
    ArrayList<OrdenDeVenta> ordenesDia = null;
 SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
              String date2=formatter.format(jDateChooser2.getDate());
           SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              String date1=formatter2.format(jDateChooser2.getDate());
              
               Date date3= jDateChooser3.getDate();
                 DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Calendar cal2 = Calendar.getInstance();
        String FechaHoy2 = dateFormat2.format(jDateChooser3.getCalendar().getTime());
            ordenesDia = new metodosDB().getVentasDia(date2.trim(),FechaHoy2);
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            funciones f = new funciones();
            File proveedores = new File(f.getRutaExcel() + "libroDiarioCompras "+date1+".xls");
            WritableWorkbook workbook = Workbook.createWorkbook(new File(f.getRutaExcel() + "libroDiarioCompras "+date1+".xls"));
            //Se crea una nueva hoja dentro del libro
            WritableSheet sheet = workbook.createSheet("LIBRO DIARIO COMPRAS", 1);
            sheet.setColumnView(1, 15);
            sheet.setColumnView(2, 17);
            sheet.setColumnView(3, 17);
            sheet.setColumnView(4, 17);
            sheet.setColumnView(5, 18);
            sheet.setColumnView(6, 17);
             int i=0;int k=0;
             int suma=0;     int suma2=0;
            for ( i = 0; i < modelo2.getRowCount(); i++) {
           
                if (i == 0) {
                 sheet.addCell(new jxl.write.Label(1, k, "ID VENTA"));
                    sheet.addCell(new jxl.write.Label(2, k, "N°GUIA/FACTURA"));
                      sheet.addCell(new jxl.write.Label(3, k, "FECHA"));
                    sheet.addCell(new jxl.write.Label(4, k, "RUT"));
                    sheet.addCell(new jxl.write.Label(5, k, "PROVEEDOR"));
                    sheet.addCell(new jxl.write.Label(6, k, "PRODUCTO"));
                    sheet.addCell(new jxl.write.Label(7, k, "MEDIDA"));
                     sheet.addCell(new jxl.write.Label(8, k, "UNIDAD"));
                         sheet.addCell(new jxl.write.Label(9, k, "CANTIDAD"));
                           sheet.addCell(new jxl.write.Label(10, k, "PRECIO UNITARIO"));
                              sheet.addCell(new jxl.write.Label(11, k, "IVA"));
                                 sheet.addCell(new jxl.write.Label(12, k, "HARINA"));
                                   sheet.addCell(new jxl.write.Label(13, k, "PRECIO BRUTO"));
                                    sheet.addCell(new jxl.write.Label(14, k, "TOTAL"));
                }
               
               suma=suma+ComprasDia.get(i).getMontoTotal();
               
               metodosDB  metodos= new metodosDB();
                ArrayList<Cliente> proveedor =new metodosDB().getClientesByName(ComprasDia.get(k).getProveedor());
           if(proveedor.size()>1){
                          jframe1 a = new jframe1();
            JOptionPane.showMessageDialog(a, "EXISTE UN PROBLEMA CON LOS PROVEEDORES (ALCANCE DE NOMBRES)");
             ;
           }
           Productos product=metodos.getProductoById(ComprasDia.get(i).getIdProductoCompra());
           int factura=new metodosDB().getOrdenDeCompraById(ComprasDia.get(i).getIdOrdenDeCompra()).getNumeroFacturaRecibida();

                       sheet.addCell(new jxl.write.Number(1, i + 5, ComprasDia.get(i).getIdCompraProducto()));
                sheet.addCell(new jxl.write.Number(2, i + 5, factura));
                sheet.addCell(new jxl.write.Label(3, i + 5, ComprasDia.get(i).getFecha()));
                sheet.addCell(new jxl.write.Label(4, i + 5, proveedor.get(0).getRut()));
                  sheet.addCell(new jxl.write.Label(5, i + 5, proveedor.get(0).getNombre()));
                sheet.addCell(new jxl.write.Label(6, i + 5, product.getNombre()));
                 sheet.addCell(new jxl.write.Label(7, i + 5,product.getTalla()));
                    sheet.addCell(new jxl.write.Label(8, i + 5, product.getMedida()));
                        sheet.addCell(new jxl.write.Number(9, i + 5, ComprasDia.get(i).getCantidad()));
                        int var4=0;
                        int var3=0;
                       if(product.getHarina()==1){
                        double imp2=  (double) (product.getPrecioCompra()*0.19);
                          double imp=  (double) (product.getPrecioCompra()*0.12);
                    System.out.println(imp2);
              var3 = (int) Math.round(imp2); 
              var4 = (int) Math.round(imp); 
                    
                 }else{
                     
                     double imp2=  (double) (product.getPrecioCompra()*0.19);
                    System.out.println(imp2);
              var3 = (int) Math.round(imp2); 
                
                 }
                        sheet.addCell(new jxl.write.Number(11, i + 5, var3));
                         sheet.addCell(new jxl.write.Number(13, i + 5, product.getPrecioCompra()));
                         
                        sheet.addCell(new jxl.write.Number(10, i + 5, product.getPrecioCompra()-var3-var4));
                         sheet.addCell(new jxl.write.Number(12, i + 5, var4));
                                 sheet.addCell(new jxl.write.Number(14, i + 5, ComprasDia.get(i).getMontoTotal()));
}
                sheet.addCell(new jxl.write.Label(14,  i + 6, "MONTO TOTAL COMPRAS."));
                sheet.addCell(new jxl.write.Number(14, i + 7,suma ));
               k=modelo.getRowCount()+8; 
                System.out.println("MAX;:"+modelo.getRowCount());
             
                
                
            workbook.write();
            workbook.close();
            this.setCursor(Cursor.getDefaultCursor());
            if (Desktop.isDesktopSupported() == true) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.open(proveedores);
                    return;
                } catch (Exception ex) {
                    mnsj = JOptionPane.showConfirmDialog(null, error + ex.getMessage(), "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

        } catch (Exception ex) {
            this.setCursor(Cursor.getDefaultCursor());
            mnsj = JOptionPane.showConfirmDialog(null, error + ex.getMessage(), "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }
}




    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

     if(modelo.getRowCount()==0){
                      jframe1 a = new jframe1();
            JOptionPane.showMessageDialog(a, "NO EXISTEN VENTAS ASOCIADAS AL DIA SELECCIONADO");
             ;
               }else{
         exportVentas();
     }
  
      
       
       
       
 
       
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(libro_diario_detalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(libro_diario_detalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(libro_diario_detalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(libro_diario_detalle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                libro_diario_detalle dialog = null;
                try {
                    dialog = new libro_diario_detalle(new javax.swing.JFrame(), true);
                } catch (SQLException ex) {
                    Logger.getLogger(libro_diario_detalle.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    
}
