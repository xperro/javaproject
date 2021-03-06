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
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class buscar_producto extends javax.swing.JDialog implements KeyListener, ActionListener, MouseListener {

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

    public buscar_producto(java.awt.Frame parent, boolean modal) throws SQLException {
        initComponents();
        iniciar();
    }

    buscar_producto(int usuario) throws SQLException {
        initComponents();
        this.usuario = usuario;
        iniciar();
    }

    buscar_producto(jframeUsuario aThis, boolean b, int u) throws SQLException {
        //super(aThis, b);
        initComponents();
        this.usuario = u;
        iniciar();
    }

    private void iniciar() throws SQLException {
String t[] = {"ID", "NOMBRE", "PESO", "MARCA", "CANTIDAD ACTUAL", "TIPO", "PROVEEDOR","PRECIO COMPRA", "PRECIO VENTA","CODIGO"};
        modelo.setColumnIdentifiers(t);
        jTable1.setModel(modelo);
        modelo.setNumRows(0);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr2.setHorizontalAlignment(SwingConstants.CENTER);
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getColumn("ID").setMinWidth(5);
        jTable1.getColumn("NOMBRE").setMinWidth(50);
        jTable1.getColumn("MARCA").setMinWidth(30);
        jTable1.getColumn("PESO").setMinWidth(5);
        jTable1.getColumn("CANTIDAD ACTUAL").setMinWidth(5);
        jTable1.getColumn("TIPO").setMinWidth(10);
        jTable1.getColumn("PROVEEDOR").setMinWidth(10);
        jTable1.getColumn("PRECIO COMPRA").setMinWidth(20);
        jTable1.getColumn("PRECIO VENTA").setMinWidth(20);
        jTable1.getColumn("CODIGO").setMinWidth(5);
     
        ((DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        jButton2.addKeyListener(this);
        jButton5.addKeyListener(this);
        jTable1.addKeyListener(this);
        jTable1.setRowHeight(22);
        jTable1.setCellSelectionEnabled(false);
        jTable1.setRowSelectionAllowed(true);
ArrayList<Productos> aux2 = new ArrayList<Productos>();
            metodosDB f = new metodosDB();
        modelo.setNumRows(0);
        int aux3;
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
          
		         aux2 = f.getProductoByNombre1();//Cargar resultados , debe ser por nombres
                    Object[] object = new Object[10];
                    int i=0;
                    Collections.sort(aux2, new Comparator<Productos>(){

			@Override
			public int compare(Productos o1, Productos o2) {
				return o1.getNombre().compareTo(o2.getNombre());
			}
			
			
		});
                    while(aux2.size()>i){
       
        object[0]  = aux2.get(i).getId_producto();
        object[1] = aux2.get(i).getNombre();
        object[2] = aux2.get(i).getTalla();
        object[3] = aux2.get(i).getMarca();
        object[4] = aux2.get(i).getCantidadActual();
        object[5] = aux2.get(i).getTipo();
        object[6] = aux2.get(i).getProveedor();
        object[7] = aux2.get(i).getPrecioCompra();
        object[8] = aux2.get(i).getPrecioVenta();
        object[9] = aux2.get(i).getCodigo_barra();
        
         modelo.addRow(object);i++;} this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		// Accion a realizar cuando el JComboBox cambia de item seleccionado.
         final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Información del Producto");
         
                deleteItem.addActionListener(new ActionListener() 
                {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                                int id = -1;
                                boolean error = false;
                                try{
                                    int s = jTable1.getSelectedRow();
                                     id = (int)jTable1.getValueAt(s, 0);
                                     
                                }catch(ArrayIndexOutOfBoundsException a)
                                {
                                    JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un item de la tabla");
                                    error = true;
                                }
                            if(error == false)
                             {
                                try {
                                    Productos p = new metodosDB().getProductoById(id);
                                    frameDescripcionProducto freim = new frameDescripcionProducto(p);
                                   freim.setVisible(true);
                                } catch (SQLException ex) {
                                    JOptionPane.showMessageDialog(rootPane, "Error en el producto / producto no existe en base de datos");
                                }
                             }
                        }

                });
        popupMenu.add(deleteItem);
        jTable1.setComponentPopupMenu(popupMenu);
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BUSCAR PRODUCTO");
        setResizable(false);
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

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar Producto", 2, 0));

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
        jLabel3.setText("BUSCAR POR NOMBRE O TIPO");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
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
        int i=0;
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ArrayList<Productos> aux2 = new ArrayList<Productos>();
            if (jTextField1.getText().isEmpty() | jTextField1.getText().equals("")) {  // si el campo esta vacio                
             
        modelo.setNumRows(0);
       
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
          
            try {
                aux2 = f.getProductoByNombre1();//Cargar resultados , debe ser por nombres
            } catch (SQLException ex) {
                Logger.getLogger(buscar_producto.class.getName()).log(Level.SEVERE, null, ex);
            }
                    Object[] object = new Object[10];
                    int k=0;
                    while(aux2.size()>k){
       
        object[0]  = aux2.get(k).getId_producto();
        object[1] = aux2.get(k).getNombre();
        object[2] = aux2.get(k).getTalla();
        object[3] = aux2.get(k).getMarca();
        object[4] = aux2.get(k).getCantidadActual();
        object[5] = aux2.get(k).getTipo();
        object[6] = aux2.get(k).getProveedor();
        object[7] = aux2.get(k).getPrecioCompra();
        object[8] = aux2.get(k).getPrecioVenta();
        object[9] = aux2.get(k).getCodigo_barra();
        
         modelo.addRow(object);k++;}
		// Accion a realizar cuando el JComboBox cambia de item seleccionado.
            }else{
                String nombre="";
                String var="";
                nombre=jTextField1.getText().toUpperCase();
                
                try {
                    aux2 = f.getProductoByNombre1();//Cargar resultados , debe ser por nombres
                   if(aux2.equals(null)){
                        this.setCursor(Cursor.getDefaultCursor());
                mnsj = JOptionPane.showConfirmDialog(null, "NO SE ENCONTRO NINGUN PRODUCTO", "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                   }else{
                    while(aux2.size()>i){   
                       var=aux2.get(i).getNombre().toUpperCase();
                       var= var+aux2.get(i).getTipo().toUpperCase();
                       var=var.replace(" ","");
                       nombre = nombre.replace(" ","");
                       System.out.println(var);
                        System.out.println(nombre);
                            if(var.indexOf(nombre)!=-1){
                    Object[] object = new Object[10];
          object[0]  = aux2.get(i).getId_producto();
        object[1] = aux2.get(i).getNombre();
        object[2] = aux2.get(i).getTalla();
        object[3] = aux2.get(i).getMarca();
        object[4] = aux2.get(i).getCantidadActual();
        object[5] = aux2.get(i).getTipo();
        object[6] = aux2.get(i).getProveedor();
        object[7] = aux2.get(i).getPrecioCompra();
        object[8] = aux2.get(i).getPrecioVenta();
        object[9] = aux2.get(i).getCodigo_barra();
         modelo.addRow(object);}i++;}
                } 
            } catch (SQLException ex) {
                Logger.getLogger(buscar_producto.class.getName()).log(Level.SEVERE, null, ex);
            }
         
              this.setCursor(Cursor.getDefaultCursor());
        
  }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    }//GEN-LAST:event_formWindowOpened

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int i = jTable1.getSelectedRow();
        Productos op = new Productos();
        if (evt.getClickCount() == 2 && i != -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            op.setId_producto(Integer.parseInt(modelo.getValueAt(i, 0).toString()));
            op.setNombre(modelo.getValueAt(i, 1).toString());
            op.setTalla(modelo.getValueAt(i, 2).toString());
        op.setMarca(modelo.getValueAt(i, 3).toString());
         op.setCantidadActual(Integer.parseInt(modelo.getValueAt(i, 4).toString()));
         op.setTipo(modelo.getValueAt(i, 5).toString());
         op.setProveedor(modelo.getValueAt(i, 6).toString());
         op.setPrecioCompra(Integer.parseInt(modelo.getValueAt(i, 7).toString()));
         op.setPrecioVenta(Integer.parseInt(modelo.getValueAt(i, 8).toString()));
         op.setCodigo_barra(modelo.getValueAt(i, 9).toString());
     
            
            
            modificar_producto a = null;
            try {
                a = new modificar_producto(op.getId_producto());
            } catch (SQLException ex) {
                Logger.getLogger(buscar_producto.class.getName()).log(Level.SEVERE, null, ex);
            }
            a.setLocationRelativeTo(null);
            this.setVisible(false);
            this.setCursor(Cursor.getDefaultCursor());
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
            java.util.logging.Logger.getLogger(buscar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(buscar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(buscar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(buscar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>    
        //</editor-fold>    

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                buscar_producto dialog = null;
                try {
                    dialog = new buscar_producto(new javax.swing.JFrame(), true);
                } catch (SQLException ex) {
                    Logger.getLogger(buscar_producto.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
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
