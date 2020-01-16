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
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
import javax.swing.table.TableColumnModel;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class libro_estadistica_productos extends javax.swing.JDialog implements MouseListener {

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

          @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }  @Override
            public Class getColumnClass(int column) {
                if (column == 2 || column == 5 || column == 0 || column == 4) {
                    return Integer.class;
                } else {
                    return String.class;
                }
            }
    };
   DefaultTableModel modelo2 = new DefaultTableModel() {

        public boolean isCellEditable(int row, int column) { // sobreescribe el metodo para convertir la celda ingresada en NO editable
            return false;
        }
        @Override
            public Class getColumnClass(int column) {
                if (column == 2 || column == 3) {
                    return Integer.class;
                } else {
                    return String.class;
                }
            }
        
        
    };
    public libro_estadistica_productos(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        iniciar();
    }

    libro_estadistica_productos(java.awt.Frame parent, int rut) throws SQLException {
        super(parent, true);
        initComponents();
        this.usuario = rut;
        iniciar();
    }

    private void iniciar() throws SQLException { 
        String titulos[] = {"ID PRODUCTO", "NOMBRE PRODUCTO","PESO","MEDIDA", "CANTIDAD VENDIDA", "MONTO TOTAL NETO","MONTO TOTAL BRUTO","COSTO TOTAL NETO","COSTO TOTAL BRUTO","MARGEN NETO","MARGEN BRUTO"};

        
        modelo.setColumnIdentifiers(titulos);
        modelo.setNumRows(0);
   
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
        jTable1.getColumnModel().getColumn(3).setCellRenderer(tcr);
      jTable1.getColumnModel().getColumn(4).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(tcr);
          jTable1.getColumnModel().getColumn(6).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(7).setCellRenderer(tcr);
                jTable1.getColumnModel().getColumn(8).setCellRenderer(tcr);
                        jTable1.getColumnModel().getColumn(9).setCellRenderer(tcr);
                            jTable1.getColumnModel().getColumn(10).setCellRenderer(tcr);
        ((DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
   jTable1.setAutoCreateRowSorter(true);
        jButton2.addMouseListener(this);
          Calendar c2 = new GregorianCalendar();
          c2.add(Calendar.MONTH, 0);
  
   double margenb=0;
            metodosDB metodos = new metodosDB();
         
             //  DateFormat dateFormat =jDateChooser2.toString()
           // Calendar cal = Calendar.getInstance();
       // String FechaHoy = dateFormat.format(cal.getTime());
             modelo.setNumRows(0);

                 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
       // String FechaHoy = dateFormat.format(jDateChooser2.getCalendar().getTime());
              ArrayList<Productos> ordenesDia = new metodosDB().getcantidadventasproductos();
          int i=0;System.out.println("orden"+ordenesDia.size());
           Object[] object = new Object[11];
              while(i<ordenesDia.size()){
                   object[1]  = ordenesDia.get(i).getNombre();
        object[4] = ordenesDia.get(i).getCantidadp();
             object[2] = ordenesDia.get(i).getTalla();
             
        object[3] = ordenesDia.get(i).getMedida();
        object[0] = ordenesDia.get(i).getId_producto();
         object[5] = ordenesDia.get(i).getCantidadp()*ordenesDia.get(i).getPrecioVenta(); // PRECIO NETO VENTA
            object[8] = ordenesDia.get(i).getCantidadp()*ordenesDia.get(i).getPrecioCompra();// PRECIO NETO COMPRA
        
             if(ordenesDia.get(i).getHarina()==1){
                        double imp2=  (double) ( ordenesDia.get(i).getPrecioCompra()*0.19);
                          double imp=  (double) ( ordenesDia.get(i).getPrecioCompra()*0.12);
                              double imphar=  (double) (ordenesDia.get(i).getPrecioCompra()*1.31);
                    System.out.println(imp2);
             int var3 = (int) Math.ceil(imp2); 
             int var4 = (int) Math.ceil(imp); 
              int var5 = (int) Math.ceil(imphar); 
                        object[7] = var5*ordenesDia.get(i).getCantidadp();
                 }else{
                     
                     double imp2=  (double) (ordenesDia.get(i).getPrecioCompra()*0.19);
                    System.out.println(imp2);
             int var3 = (int) Math.ceil(imp2); 
                      object[7] = (var3+ordenesDia.get(i).getPrecioCompra())*ordenesDia.get(i).getCantidadp();
                 }
             
             
                if(ordenesDia.get(i).getHarina()==1){
                        double imp2=  (double) ( ordenesDia.get(i).getPrecioVenta()*0.19);
                          double imp=  (double) ( ordenesDia.get(i).getPrecioVenta()*0.12);
                              double imphar=  (double) (ordenesDia.get(i).getPrecioVenta()*1.31);
                    System.out.println(imp2);
             int var3 = (int) Math.ceil(imp2); 
             int var4 = (int) Math.ceil(imp); 
              int var5 = (int) Math.ceil(imphar); 
                        object[6] = var5*ordenesDia.get(i).getCantidadp();
                           
                 }else{
                     
                     double imp2=  (double) (ordenesDia.get(i).getPrecioVenta()*0.19);
                    System.out.println(imp2);
             int var3 = (int) Math.ceil(imp2); 
                      object[6] = (var3+ordenesDia.get(i).getPrecioVenta())*ordenesDia.get(i).getCantidadp();
                 }
            //  object[7]=Integer.parseInt(object[6].toString())-Integer.parseInt(object[5].toString());
                  double preciov=ordenesDia.get(i).getPrecioVenta();
                          double precioc=ordenesDia.get(i).getPrecioCompra();
                          System.out.println("PRECIOS DEL PRODUCTO");
                          System.out.println("PRECIO COMPRA: "+precioc);
                          System.out.println("PRECIO VENTA: " +preciov);
                          
                          
                          
                          
                          margenb=0;
                          
                          if(precioc>0){
                      if(ordenesDia.get(i).getHarina()==1){
                             double imphar2=  (double) (ordenesDia.get(i).getPrecioCompra()*1.31);
                             double imphar=  (double) (ordenesDia.get(i).getPrecioVenta()*1.31);
                           margenb=imphar/imphar2-1;
                      }    else{
                          
                                 double imphar2=  (double) (ordenesDia.get(i).getPrecioCompra()*1.19);
                             double imphar=  (double) (ordenesDia.get(i).getPrecioVenta()*1.19);
                           margenb=imphar/imphar2-1;
                          
                      }      
                         
                double margen=(preciov/precioc)-1;
             
                margen=margen*100;
  margenb=margenb*100;

                 object[9]=truncateDecimal(margen,2);
                            object[10]=truncateDecimal(margenb,2);
                          
                          }else{
                            object[9]="SIN INFO";  
                              object[10]="SIN INFO";
                          }
               // object[9]=Integer.parseInt(object[9].toString())-Integer.parseInt(object[8].toString());

      modelo.addRow(object);i++;
              } 
           
        
          
          
          

        //Funcion que debe cargar todos los prestamos desde la BD y agregarlos a la tabla
    }

   private static BigDecimal truncateDecimal(double x,int numberofDecimals)
{
    if ( x > 0) {
        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
    } else {
        return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ESTADISTICAS");
        setModal(true);
        setResizable(false);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PRODUCTOS", 2, 0, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("BUSQUEDA POR PERIODO");

        jDateChooser2.setDateFormatString("yyyy-MM-dd 00:00:00");
        jDateChooser2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jDateChooser3.setDateFormatString("yyyy-MM-dd 23:59:59");
        jDateChooser3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("-");

        jButton1.setText("FILTRAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1213, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(55, 55, 55)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                .addGap(48, 48, 48)
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
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    

    }//GEN-LAST:event_jTable1MouseClicked
public void exportVentas() throws IOException, WriteException{
   
                   DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          Calendar cal2 = Calendar.getInstance();
    String date1;
  
       
       date1=""+cal2.getTimeInMillis();
       
  
    
  
    ArrayList<OrdenDeVenta> ordenesDia = null;

            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            
            funciones f = new funciones();
            File proveedores = new File(f.getRutaExcel() + "EstadisticaDiarioVentas "+date1+".xls");
            WritableWorkbook workbook = Workbook.createWorkbook(new File(f.getRutaExcel() + "EstadisticaDiarioVentas "+date1+".xls"));
            //Se crea una nueva hoja dentro del libro
            WritableSheet sheet = workbook.createSheet("LIBRO ESTIDISTICO", 1);
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
                    sheet.addCell(new jxl.write.Label(1, k, "ID PRODUCTO"));
                    sheet.addCell(new jxl.write.Label(2, k, "NOMBRE PRODUCTO"));
                     sheet.addCell(new jxl.write.Label(3, k, "PESO"));
                      sheet.addCell(new jxl.write.Label(4, k, "MEDIDA"));
                  
                      sheet.addCell(new jxl.write.Label(5, k, "CANTIDAD VENDIDA"));
                      sheet.addCell(new jxl.write.Label(6, k, "MONTO TOTAL NETO"));
                    sheet.addCell(new jxl.write.Label(7, k, "MONTO TOTAL BRUTO"));
                    sheet.addCell(new jxl.write.Label(8, k, "MARGEN VENTA"));
                 sheet.addCell(new jxl.write.Label(9, k, "COSTO TOTAL NETO"));   
                 sheet.addCell(new jxl.write.Label(10, k, "COSTO TOTAL BRUTO"));
                 sheet.addCell(new jxl.write.Label(11, k, "MARGEN COMPRA"));
                }
            
                sheet.addCell(new jxl.write.Label(1, k + 1, jTable1.getValueAt(i, 0).toString()));
        sheet.addCell(new jxl.write.Label(2, k + 1, jTable1.getValueAt(i, 1).toString()));         
                sheet.addCell(new jxl.write.Label(3, k + 1, jTable1.getValueAt(i, 2).toString()));
                sheet.addCell(new jxl.write.Label(4, k + 1, jTable1.getValueAt(i, 3).toString()));
                      sheet.addCell(new jxl.write.Label(5, k + 1, jTable1.getValueAt(i, 4).toString()));
                            sheet.addCell(new jxl.write.Label(6, k + 1, jTable1.getValueAt(i, 5).toString()));
      sheet.addCell(new jxl.write.Label(7 ,k + 1, jTable1.getValueAt(i, 6).toString()));
            sheet.addCell(new jxl.write.Label(8, k + 1, jTable1.getValueAt(i, 7).toString()));
                  sheet.addCell(new jxl.write.Label(9, k + 1, jTable1.getValueAt(i, 8).toString()));
                        sheet.addCell(new jxl.write.Label(10, k + 1, jTable1.getValueAt(i, 9).toString()));
                              sheet.addCell(new jxl.write.Label(11, k + 1, jTable1.getValueAt(i, 10).toString()));
 k++;                 
} k++;
           
                
                
                
             
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

       
}


public void exportCompras(){/*try{
  ArrayList<OrdenDeVenta> ordenesDia = null;
 SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
              String date2=formatter.format(jDateChooser2.getDate());
           SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              String date1=formatter2.format(jDateChooser2.getDate());
            ordenesDia = new metodosDB().getVentasDia(date2.trim());
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
        }*/
}




    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            exportVentas();
        } catch (IOException ex) {
            Logger.getLogger(libro_estadistica_productos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(libro_estadistica_productos.class.getName()).log(Level.SEVERE, null, ex);
        }
 
  
      
       
       
       
 
       
    }//GEN-LAST:event_jButton5ActionPerformed

    
    public static void reiniciarJTable(javax.swing.JTable Tabla){
        DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
        while(modelo.getRowCount()>0)modelo.removeRow(0);
 
        TableColumnModel modCol = Tabla.getColumnModel();
        while(modCol.getColumnCount()>0)modCol.removeColumn(modCol.getColumn(0));
    }
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            
            if (modelo.getRowCount() > 0) {
    for (int i = modelo.getRowCount() - 1; i > -1; i--) {
        modelo.removeRow(i);
    }
}
             
            
            
            Date date = jDateChooser2.getDate();
                   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          Calendar cal = Calendar.getInstance();
          String Fecha1 = dateFormat.format(jDateChooser2.getCalendar().getTime());
          
        
          
            Date date2 = jDateChooser3.getDate();
                   DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
          Calendar cal2 = Calendar.getInstance();
          String Fecha2 = dateFormat2.format(jDateChooser3.getCalendar().getTime());
         
          
          
                    ArrayList<Productos> ordenesDia = new metodosDB().getcantidadventasproductosFECHAS(Fecha1, Fecha2);
            int i=0;System.out.println("orden"+ordenesDia.size());
             Object[] object = new Object[6];
                while(i<ordenesDia.size()){
                         object[1]  = ordenesDia.get(i).getNombre();
        object[4] = ordenesDia.get(i).getCantidadp();
             object[2] = ordenesDia.get(i).getTalla();
             
        object[3] = ordenesDia.get(i).getMedida();
        object[0] = ordenesDia.get(i).getId_producto();
         object[5] = ordenesDia.get(i).getCantidadp()*ordenesDia.get(i).getPrecioVenta();
      modelo.addRow(object);i++;
                } 
             
          
            
          // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(libro_estadistica_productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(libro_estadistica_productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(libro_estadistica_productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(libro_estadistica_productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(libro_estadistica_productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                libro_estadistica_productos dialog = null;
                try {
                    dialog = new libro_estadistica_productos(new javax.swing.JFrame(), true);
                } catch (SQLException ex) {
                    Logger.getLogger(libro_estadistica_productos.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
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
