package GUI;

import Clases.funciones;
import ObjetosDB.OrdenDeCompra;
import ObjetosDB.OrdenDeVenta;
import ObjetosDB.metodosDB;
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
import javax.swing.*;
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

public class libro_diario_compras extends javax.swing.JDialog implements MouseListener {

    int usuario, mnsj, num_celdas = 45;
    String vacio = "NO SE OBTUVO RESULTADO CON LOS PARAMETROS INGRESADOS.";
    String correcto = "OPERACIÓN REALIZADA CON ÉXITO.", error = "HA OCURRIDO EL SIGUIENTE ERROR:\n";
    String no_empleado = "NO SE REGISTRA EMPLEADO CON EL RUT INGRESADO.";
    String no_opcion = "DEBE SELECCIONAR UNA DE LAS OPCIONES DEL SISTEMA.";
    String no_numero = "DEBE INGRESAR UN DATO NUMÉRICO EN EL CAMPO ";
    String num_no_valido = "DEBE INGRESAR UN NÚMERO VÁLIDO EN EL CAMPO ";
    String campo_vacio = "DEBE COMPLETAR EL SIGUIENTE CAMPO:\n";
    DefaultTableModel modelo = new DefaultTableModel() {

        public boolean isCellEditable(int row, int column) { // sobreescribe el metodo para convertir la celda ingresada en NO editable
            return false;
        }
    };

    public libro_diario_compras(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        
        iniciar();
    }

    libro_diario_compras(java.awt.Frame parent, int rut) throws SQLException {
        super(parent, true);
        initComponents();
        this.usuario = rut;
        iniciar();
    }

    private void iniciar() throws SQLException { 
        String titulos[] = {"ID COMPRA", "FECHA INGRESO", "FECHA EMISION", "NUMERO", "TIPO","NOMBRE PROVEEDOR","MONTO"};
        modelo.setColumnIdentifiers(titulos);
        modelo.setNumRows(0);
        jTable1.setModel(modelo);
        int suma=0;
        jTextField3.setText("0");
        jTextField3.setEditable(false);
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
        jProgressBar1.setVisible(false);
     Calendar c = new GregorianCalendar();
             
             
             Date date = jDateChooser2.getDate();
             jDateChooser2.setCalendar(c);
                 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar cal = Calendar.getInstance();
        String FechaHoy = dateFormat.format(jDateChooser2.getCalendar().getTime());
        
        
         Date date2 = jDateChooser3.getDate();
         jDateChooser3.setCalendar(c);
                 DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Calendar cal2 = Calendar.getInstance();
        String FechaHoy2 = dateFormat2.format(jDateChooser3.getCalendar().getTime());
        metodosDB metodos = new metodosDB();
       
       
        modelo.setNumRows(0);
         ArrayList<OrdenDeCompra> ordenesDia = new metodosDB().getComprasDia(FechaHoy,FechaHoy2);
     int i=0;
            for(OrdenDeCompra orden: ordenesDia)
            {
             Object[] object = new Object[7];
            object[0] = orden.getIdOrdenCompra();
            object[1] = orden.getFecha();
            object[2] = orden.getFechaEmision();
            object[3] = orden.getNumeroFacturaRecibida();
            object[4] = orden.getTipo();
            
            object[5] = orden.getProveedor();
             object[6] = orden.getMontoTotal();
            modelo.addRow(object);
            suma = suma + orden.getMontoTotal();
        i++;
        
            
            } 
            jTextField1.setText(Integer.toString(suma));
            jTextField3.setText(Integer.toString(i));
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
        jProgressBar1 = new javax.swing.JProgressBar();
        jTextField3 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("LIBRO DIARIO COMPRAS");
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

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Compras", 2, 0, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

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

        jProgressBar1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jTextField3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("0");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel2.setText("REGISTRO(S)");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel3.setText("INICIAL");
        jLabel3.setToolTipText("");

        jDateChooser2.setDateFormatString("yyyy-MM-dd 23:59:59");
        jDateChooser2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel1.setText("MONTO NETO: ");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField1.setText("0");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel4.setText("FINAL");
        jLabel4.setToolTipText("");

        jDateChooser3.setDateFormatString("yyyy-MM-dd 00:00:00");
        jDateChooser3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    private String getFecha()
 {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String FechaHoy = dateFormat.format(cal.getTime());
        return FechaHoy;
 }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String r = null;
        String[] aux2, aux3;
        int aux = 0;
        modelo.setNumRows(0);
        if (jDateChooser2.getDate().toString().equals("")) {
            mnsj = JOptionPane.showConfirmDialog(null, "DEBE SELECCIONAR UNA FECHA.", "INFORMACIÓN", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int suma=0;
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //r funcion que debe buscar prestamos con las variables correspondientes
          metodosDB metodos = new metodosDB();
       
         
        modelo.setNumRows(0);
         ArrayList<OrdenDeCompra> ordenesDia = null;
         
         
      
         Calendar c = new GregorianCalendar();
             
             
             Date date = jDateChooser2.getDate();
                 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar cal = Calendar.getInstance();
        String FechaHoy = dateFormat.format(jDateChooser2.getCalendar().getTime());
        
        
         Date date2 = jDateChooser3.getDate();
                 DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Calendar cal2 = Calendar.getInstance();
        String FechaHoy2 = dateFormat2.format(jDateChooser3.getCalendar().getTime());
        try {
            ordenesDia = new metodosDB().getComprasDia(FechaHoy2,FechaHoy);
        } catch (SQLException ex) {
            Logger.getLogger(libro_diario_compras.class.getName()).log(Level.SEVERE, null, ex);
        }
     int i=0;
            for(OrdenDeCompra orden: ordenesDia)
            {
             Object[] object = new Object[7];
            object[0] = orden.getIdOrdenCompra();
            object[1] = orden.getFecha();
            object[2] = orden.getFechaEmision();
            object[3] = orden.getNumeroFacturaRecibida();
            object[4] = orden.getTipo();
            
            object[5] = orden.getProveedor();
             object[6] = orden.getMontoTotal();
            modelo.addRow(object);
            suma = suma + orden.getMontoTotal();
        i++;
        
            
            } 
            jTextField1.setText(Integer.toString(suma));
            jTextField3.setText(Integer.toString(i));
                      setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        //Funcion que debe cargar todos los prestamos desde la BD y agregarlos a la tabla


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
try {
     if(modelo.getRowCount()==0){
                      jframe1 a = new jframe1();
            JOptionPane.showMessageDialog(a, "NO EXISTEN VENTAS ASOCIADAS AL DIA SELECCIONADO");
            return ;
               }
   
 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              String date2=formatter.format(jDateChooser2.getDate());
           SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              String date1=formatter2.format(jDateChooser3.getDate());
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            String datee=date1;
            funciones f = new funciones();
            File proveedores = new File(f.getRutaExcel() + "libroDiarioCompras"+datee.replaceAll("/", "-") +".xls");
            WritableWorkbook workbook = Workbook.createWorkbook(new File(f.getRutaExcel() + "libroDiarioCompras"+datee.replaceAll("/", "-")+".xls"));
            //Se crea una nueva hoja dentro del libro
            WritableSheet sheet = workbook.createSheet("LIBRO DIARIO COMPRAS", 1);
            sheet.setColumnView(1, 15);
            sheet.setColumnView(2, 17);
            sheet.setColumnView(3, 17);
            sheet.setColumnView(4, 17);
            sheet.setColumnView(5, 18);
            sheet.setColumnView(6, 17);
             int i=0; 
             int suma=0;int k=0;
            for ( i = 0; i < modelo.getRowCount(); i++) {
              
                if (i == 0) {
                    sheet.addCell(new jxl.write.Label(1, 4, "ID COMPRA"));
                      sheet.addCell(new jxl.write.Label(2, 4, "FECHA INGRESO"));
                    sheet.addCell(new jxl.write.Label(3, 4, "FECHA EMISION"));
                    sheet.addCell(new jxl.write.Label(4, 4, "NUMERO"));
                    sheet.addCell(new jxl.write.Label(5, 4, "TIPO"));
                    sheet.addCell(new jxl.write.Label(6, 4, "NOMBRE PROVEEDOR."));
                       sheet.addCell(new jxl.write.Label(7, 4, "MONTO TOTAL."));
                }
                   suma=suma+Integer.parseInt(modelo.getValueAt(k, 6).toString());
                sheet.addCell(new jxl.write.Number(1, i + 5,  Integer.parseInt(modelo.getValueAt(k, 0).toString())));
                sheet.addCell(new jxl.write.Label(2, i + 5, modelo.getValueAt(k, 1).toString()));
                sheet.addCell(new jxl.write.Label(3, i + 5, modelo.getValueAt(k, 2).toString()));
                sheet.addCell(new jxl.write.Number(4, i + 5, Integer.parseInt(modelo.getValueAt(k, 3).toString())));
                sheet.addCell(new jxl.write.Label(5, i + 5, modelo.getValueAt(k, 4).toString()));
                    sheet.addCell(new jxl.write.Label(6, i + 5, modelo.getValueAt(k, 5).toString()));
                             sheet.addCell(new jxl.write.Number(7, i + 5, Integer.parseInt(modelo.getValueAt(k, 6).toString())));
            
                
                     
                k++;}
                sheet.addCell(new jxl.write.Label(7,  i + 6, "MONTO NETO TOTAL."));
                sheet.addCell(new jxl.write.Number(7, i + 7,suma ));
                
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
       
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyPressed

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
            java.util.logging.Logger.getLogger(libro_diario_compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(libro_diario_compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(libro_diario_compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(libro_diario_compras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                libro_diario_compras dialog = null;
                try {
                    dialog = new libro_diario_compras(new javax.swing.JFrame(), true);
                } catch (SQLException ex) {
                    Logger.getLogger(libro_diario_compras.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
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
