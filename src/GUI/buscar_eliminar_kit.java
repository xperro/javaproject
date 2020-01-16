/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Clases.funciones;
import ObjetosDB.Kitproductos;
import ObjetosDB.Productos;
import ObjetosDB.metodosDB;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alejandro
 */
public class buscar_eliminar_kit extends javax.swing.JDialog implements KeyListener, ActionListener, MouseListener {

    int usuario, a = 1, mnsj, num_celdas = 44;
    String vacio = "NO SE OBTUVO RESULTADO CON LOS PARAMETROS INGRESADOS.";
    String correcto = "OPERACIÓN REALIZADA CON ÉXITO.", error = "HA OCURRIDO EL SIGUIENTE ERROR:\n";
    String no_empleado = "NO SE REGISTRA EMPLEADO CON EL RUT INGRESADO.";
    String no_opcion = "DEBE SELECCIONAR UNA DE LAS OPCIONES DEL SISTEMA.";
    String no_numero = "DEBE INGRESAR UN DATO NUMÉRICO EN EL CAMPO ";
    String num_no_valido = "DEBE INGRESAR UN NÚMERO VÁLIDO EN EL CAMPO ";
    String campo_vacio = "DEBE COMPLETAR EL SIGUIENTE CAMPO:\n";
    DefaultTableModel modelo = new DefaultTableModel() {
    
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public buscar_eliminar_kit(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        iniciar();
    }

    buscar_eliminar_kit(int usuario) throws SQLException {
        initComponents();
        this.usuario = usuario;
        iniciar();
    }

    buscar_eliminar_kit(jframeUsuario aThis, boolean b, int u) throws SQLException {
        super(aThis, b);
        initComponents();
        this.usuario = u;
        iniciar();
    }

    private void iniciar() throws SQLException {
String t[] = {"ID", "NOMBRE KIT", "COSTO KIT", "PRECIO KIT", "DESCRIPCION KIT", "CANTIDAD KIT DISPONIBLES"};
        modelo.setColumnIdentifiers(t);
        jTable1.setModel(modelo);
        modelo.setNumRows(0);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr2.setHorizontalAlignment(SwingConstants.CENTER);
      
          jTable1.getColumn("ID").setMaxWidth(5);
        jTable1.getColumn("NOMBRE KIT").setPreferredWidth(100);
        jTable1.getColumn("COSTO KIT").setPreferredWidth(20);
        jTable1.getColumn("PRECIO KIT").setPreferredWidth(20);
        jTable1.getColumn("DESCRIPCION KIT").setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(tcr);
        ((DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        jButton2.addKeyListener(this);
        jButton5.addKeyListener(this);
        jTable1.addKeyListener(this);
        jTable1.setRowHeight(22);
        jTable1.setCellSelectionEnabled(false);
        jTable1.setRowSelectionAllowed(true);
 ArrayList<Kitproductos> aux2 = new ArrayList<Kitproductos>();
            metodosDB f = new metodosDB();
        modelo.setNumRows(0);
        int aux3;
        
          
		         aux2 = f.getKitproductos();//Cargar resultados , debe ser por nombres
                    Object[] object = new Object[11];
                    int i=0;
                    while(aux2.size()>i){
       
        object[0] = aux2.get(i).getIdKitProductos();
        object[1] = aux2.get(i).getNombreKit();
        object[2] = aux2.get(i).getPrecioCompraProductos();
        object[3] = aux2.get(i).getPrecioVentaKit();
        object[4] = aux2.get(i).getDescripcionKit();
        object[5] = 10; //HAY QUE CALCULAR LA CANTIDAD DISPONIBLE
        
         modelo.addRow(object);i++;}
		// Accion a realizar cuando el JComboBox cambia de item seleccionado.
        
        
    }

   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BUSCAR ELIMINAR KIT");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(41, 91, 134));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/zoom.png"))); // NOI18N
        jButton2.setText("BUSCAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jButton5.setForeground(new java.awt.Color(206, 12, 12));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/delete.png"))); // NOI18N
        jButton5.setText("CERRAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kit Disponibles", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        jTable1.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jTable1.setToolTipText("");
        jTable1.setColumnSelectionAllowed(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel3.setText("BUSCAR KIT ");

        jComboBox1.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NOMBRE", "ID PRODUCTO", "MARCA" }));

        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        metodosDB f = new metodosDB();
        modelo.setNumRows(0);
        int aux3;
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Productos aux2 = new Productos();
        if (jComboBox1.getSelectedIndex()==0) { // si quiere buscar por solicitud
            if (jTextField1.getText().isEmpty() | jTextField1.getText().equals("")) {  // si el campo esta vacio                
                this.setCursor(Cursor.getDefaultCursor());
                mnsj = JOptionPane.showConfirmDialog(null, "DEBE INGRESAR UN NOMBRE", "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
            }else{
                try {
                    aux2 = f.getProductoById(Integer.parseInt(jTextField1.getText()));//Cargar resultados , debe ser por nombres
                    Object[] object = new Object[10];
        object[0]  = aux2.getId_producto();
        object[1] = aux2.getNombre();
        object[2] = aux2.getTalla();
        object[3] = aux2.getMarca();
        object[4] = aux2.getCantidadActual();
        object[5] = aux2.getTipo();
        object[6] = aux2.getProveedor();
        object[7] = aux2.getPrecioCompra();
        object[8] = aux2.getPrecioVenta();
        object[9] = aux2.getCodigo_barra();
         modelo.addRow(object);
                } catch (SQLException ex) {
                    Logger.getLogger(buscar_eliminar_kit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
         
            
        }

        if (jComboBox1.getSelectedIndex()==1) { // si quiere buscar por rut
            if (jTextField1.getText().isEmpty() | jTextField1.getText().equals("")) {  // si el campo esta vacio
                this.setCursor(Cursor.getDefaultCursor());
                mnsj = JOptionPane.showConfirmDialog(null, "DEBE INGRESAR UN ID DE PRODUCTO", "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
            }else{
                try {
                      aux2 = f.getProductoById(Integer.parseInt(jTextField1.getText()));//Cargar resultados , debe ser por nombres
                    Object[] object = new Object[10];
        object[0]  = aux2.getId_producto();
        object[1] = aux2.getNombre();
        object[2] = aux2.getTalla();
        object[3] = aux2.getMarca();
        object[4] = aux2.getCantidadActual();
        object[5] = aux2.getTipo();
        object[6] = aux2.getProveedor();
        object[7] = aux2.getPrecioCompra();
        object[8] = aux2.getPrecioVenta();
        object[9] = aux2.getCodigo_barra();
         modelo.addRow(object);
                } catch (SQLException ex) {
                    Logger.getLogger(buscar_eliminar_kit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          
          
        }
          if (jComboBox1.getSelectedIndex()==2) { // si quiere buscar por rut
            if (jTextField1.getText().isEmpty() | jTextField1.getText().equals("")) {  // si el campo esta vacio
                this.setCursor(Cursor.getDefaultCursor());
                mnsj = JOptionPane.showConfirmDialog(null, "DEBE INGRESAR UNA MARCA", "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
            }else{
                try {
                      aux2 = f.getProductoById(Integer.parseInt(jTextField1.getText()));//Cargar resultados , debe ser por nombres
                    Object[] object = new Object[10];
        object[0]  = aux2.getId_producto();
        object[1] = aux2.getNombre();
        object[2] = aux2.getTalla();
        object[3] = aux2.getMarca();
        object[4] = aux2.getCantidadActual();
        object[5] = aux2.getTipo();
        object[6] = aux2.getProveedor();
        object[7] = aux2.getPrecioCompra();
        object[8] = aux2.getPrecioVenta();
        object[9] = aux2.getCodigo_barra();
         modelo.addRow(object);
                } catch (SQLException ex) {
                    Logger.getLogger(buscar_eliminar_kit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          
           
        }
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    }//GEN-LAST:event_formWindowOpened

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
         int i = jTable1.getSelectedRow();
        Kitproductos op = new Kitproductos(0,null,0,0,null);
        if (evt.getClickCount() == 2 && i != -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            op.setIdKitProductos(Integer.parseInt(modelo.getValueAt(i, 0).toString()));
            op.setNombreKit(modelo.getValueAt(i, 1).toString());
            op.setPrecioCompraProductos(Integer.parseInt(modelo.getValueAt(i, 2).toString()));
        op.setPrecioVentaKit(Integer.parseInt(modelo.getValueAt(i, 3).toString()));
         op.setDescripcionKit(modelo.getValueAt(i, 4).toString());
     
     
            
            
            eliminar_kit a = null;
            try {
                a = new eliminar_kit(op);
            } catch (SQLException ex) {
                Logger.getLogger(buscar_eliminar_producto.class.getName()).log(Level.SEVERE, null, ex);
            }
            a.setLocationRelativeTo(null);
            this.setVisible(false);
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            a.setVisible(true);
        }
            }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

        jButton2ActionPerformed(evt);
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

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
            java.util.logging.Logger.getLogger(buscar_eliminar_kit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(buscar_eliminar_kit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(buscar_eliminar_kit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(buscar_eliminar_kit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>    
        //</editor-fold>    
        //</editor-fold>    
        //</editor-fold>    
        //</editor-fold>    
        //</editor-fold>    
        //</editor-fold>    
        //</editor-fold>    
        //</editor-fold>    
        //</editor-fold>    
        //</editor-fold>    
        //</editor-fold>    
        //</editor-fold>    
        //</editor-fold>    
        //</editor-fold>    
        //</editor-fold>    

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                buscar_eliminar_kit dialog = null;
                try {
                    dialog = new buscar_eliminar_kit(new javax.swing.JFrame(), true);
                } catch (SQLException ex) {
                    Logger.getLogger(buscar_eliminar_kit.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

   

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

  
}
