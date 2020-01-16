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

public class modificar_productos_all extends javax.swing.JDialog implements KeyListener, ActionListener, MouseListener {

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
            return true;
        }
    };

    public modificar_productos_all(java.awt.Frame parent, boolean modal) throws SQLException {
        initComponents();
        iniciar();
    }

    modificar_productos_all(int usuario) throws SQLException {
        initComponents();
        this.usuario = usuario;
        iniciar();
    }

    modificar_productos_all(jframeUsuario aThis, boolean b, int u) throws SQLException {
        //super(aThis, b);
        initComponents();
        this.usuario = u;
        iniciar();
    }

    private void iniciar() throws SQLException {
String t[] = {"ID", "NOMBRE", "PESO","MEDIDA", "MARCA", "CANTIDAD ACTUAL", "TIPO", "PROVEEDOR","PRECIO COMPRA", "PRECIO VENTA","CRITICO"};
        modelo.setColumnIdentifiers(t);
        jTable1.setModel(modelo);
        modelo.setNumRows(0);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr2.setHorizontalAlignment(SwingConstants.CENTER);
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumn("ID").setMinWidth(5);
        jTable1.getColumn("NOMBRE").setMinWidth(50);
        jTable1.getColumn("MARCA").setMinWidth(30);
        jTable1.getColumn("PESO").setMinWidth(5);
          jTable1.getColumn("MEDIDA").setMinWidth(10);
        jTable1.getColumn("CANTIDAD ACTUAL").setMinWidth(5);
        jTable1.getColumn("TIPO").setMinWidth(10);
        jTable1.getColumn("PROVEEDOR").setMinWidth(10);
        jTable1.getColumn("PRECIO COMPRA").setMinWidth(20);
        jTable1.getColumn("PRECIO VENTA").setMinWidth(20);
        jTable1.getColumn("CRITICO").setMinWidth(5);
     
        ((DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    
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
                                     Collections.sort(aux2, new Comparator<Productos>(){

			@Override
			public int compare(Productos o1, Productos o2) {
				return o1.getNombre().compareTo(o2.getNombre());
			}
			
			
		});
                    Object[] object = new Object[11];
                    int i=0;
                    while(aux2.size()>i){
       
        object[0]  = aux2.get(i).getId_producto();
        object[1] = aux2.get(i).getNombre();
        object[2] = aux2.get(i).getTalla();
         object[3] = aux2.get(i).getMedida();
        object[4] = aux2.get(i).getMarca();
        object[5] = aux2.get(i).getCantidadActual();
        object[6] = aux2.get(i).getTipo();
        object[7] = aux2.get(i).getProveedor();
        object[8] = aux2.get(i).getPrecioCompra();
        object[9] = aux2.get(i).getPrecioVenta();
        object[10] = aux2.get(i).getCritico();
        
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
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BUSCAR PRODUCTO");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jButton5.setForeground(new java.awt.Color(206, 12, 12));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Backup Green Button.png"))); // NOI18N
        jButton5.setText("ACTUALIZAR CAMBIOS");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos", 2, 0));

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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("SOLO ACEPTA MODIFICAR: NOMBRE , MARCA, CANTIDAD ACTUAL , P.COMPRA, P.VENTA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(516, 516, 516)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(174, 174, 174))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    }//GEN-LAST:event_formWindowOpened

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
System.out.println("hola");
        int i=0; ArrayList<Productos> productos=new ArrayList<Productos>();
        while(i<jTable1.getRowCount()){
    Productos product= new Productos();
    
    String nombre=jTable1.getValueAt(i, 1).toString();
    int cantidadactual=Integer.parseInt(jTable1.getValueAt(i,5).toString());
    String marca=jTable1.getValueAt(i, 4).toString();
    int compra=Integer.parseInt(jTable1.getValueAt(i, 8).toString());
    int venta=Integer.parseInt(jTable1.getValueAt(i, 9).toString());
    int id=Integer.parseInt(jTable1.getValueAt(i, 0).toString());
     int critico=Integer.parseInt(jTable1.getValueAt(i, 10).toString());
   product.setNombre(nombre);
    product.setCantidadActual(cantidadactual);
            product.setId_producto(id);
            product.setMarca(marca);
            product.setPrecioCompra(compra);
            product.setPrecioVenta(venta);
            product.setCritico(critico);
            productos.add(product);i++;
}       try {
            new metodosDB().updateProductos2(productos);
        } catch (SQLException ex) {
                                                JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un error");

            Logger.getLogger(modificar_productos_all.class.getName()).log(Level.SEVERE, null, ex);
        }

                                    JOptionPane.showMessageDialog(rootPane, "Actualizados correctamente");


this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
      
            }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(modificar_productos_all.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(modificar_productos_all.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(modificar_productos_all.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(modificar_productos_all.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>    
        //</editor-fold>    

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                modificar_productos_all dialog = null;
                try {
                    dialog = new modificar_productos_all(new javax.swing.JFrame(), true);
                } catch (SQLException ex) {
                    Logger.getLogger(modificar_productos_all.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
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
