/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ObjetosDB.Kitproductos;
import ObjetosDB.Productos;
import ObjetosDB.Promociones;
import ObjetosDB.metodosDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

/**
 *
 * @author ttars
 */
public class frameInventarioActual extends javax.swing.JFrame {

    DefaultTableModel modelo1 = new DefaultTableModel(); // Tabla Productos
    DefaultTableModel modelo2 = new DefaultTableModel();//StockCritico
   // DefaultTableModel modelo3 = new DefaultTableModel();//Kits
    DefaultTableModel modelo4 = new DefaultTableModel();//Promociones

    public frameInventarioActual()  {
               initComponents();
        try {
            iniciar();
        } catch (SQLException ex) {System.out.println("Error al cargar los datos!");}
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INVENTARIO");
        setResizable(false);

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
        jScrollPane5.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Productos", jPanel4);

        jPanel1.setForeground(new java.awt.Color(206, 12, 12));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Stock Critico", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 945, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 422, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("", jPanel2);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Promociones", jPanel3);

        jButton1.setFont(new java.awt.Font("DejaVu Sans", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/arrow_rotate_clockwise.png"))); // NOI18N
        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Productos\n\n\n");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        metodosDB asd = new metodosDB();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frameInventarioActual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frameInventarioActual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frameInventarioActual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frameInventarioActual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frameInventarioActual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable4;
    // End of variables declaration//GEN-END:variables

    private void iniciar() throws SQLException {
       
        /*Cargamos Datos de Inventario -> Productos*/
        String t[] = {"ID","PRODUCTO","PESO","MARCA","CANTIDAD","TIPO", "PROVEEDOR", "COSTO","PRECIO VENTA","COD."};
        modelo1.setColumnIdentifiers(t);
        jTable1.setModel(modelo1);
        
        //Configuramos Ancho de las columnas
        jTable1.getColumnModel().getColumn(0).setMaxWidth(50); //id
        jTable1.getColumnModel().getColumn(1).setMinWidth(200); //nombre
        jTable1.getColumnModel().getColumn(2).setMinWidth(15); //talla
        jTable1.getColumnModel().getColumn(3).setMinWidth(25); //marca
        jTable1.getColumnModel().getColumn(4).setMinWidth(20); //costo
        jTable1.getColumnModel().getColumn(5).setMinWidth(15); //tipo
        jTable1.getColumnModel().getColumn(6).setMinWidth(25); //proveedor
        jTable1.getColumnModel().getColumn(7).setMinWidth(25); //cantidad
        jTable1.getColumnModel().getColumn(8).setMinWidth(20); //precioventa
        jTable1.getColumnModel().getColumn(9).setMinWidth(25); //codigo

        metodosDB f = new metodosDB();
        
        int aux3;
        ArrayList<Productos> aux2 = new ArrayList<Productos>();
	aux2 = f.getProductos();//Cargar resultados , debe ser por nombres
        Object[] object = new Object[10];
        int i=0;
        
        while(aux2.size()>i)
        {
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
            modelo1.addRow(object);
            i++;
        }
         final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Información del Producto");
         
        
        popupMenu.add(deleteItem);
        jTable1.setComponentPopupMenu(popupMenu);
        
        /*Cargamos datos de Productos Stock Critico*/
        String t2[] = {"ID","PRODUCTO","PESO","MARCA","CANTIDAD","TIPO", "PROVEEDOR", "COSTO","PRECIO VENTA","COD."};
        modelo2.setColumnIdentifiers(t2);
        jTable2.setModel(modelo2);
        aux2 = f.getProductosStockCritico();
        object = new Object[10];
        i=0;
        while(aux2.size()>i)
        {
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
            modelo2.addRow(object);
            i++;
        }
         
        jTable2.setComponentPopupMenu(popupMenu);
        
        /*Cargamos arbol de Kits*/
    //  String t3[] = {"ID","KIT","PCompra","PVenta","Descripcion"};
     //   modelo3.setColumnIdentifiers(t3);
     //   jTable3.setModel(modelo3);
      /*  ArrayList<Kitproductos> aux2a;
        aux2a = f.getKitproductos();
        i=0;
        object = new Object[5];
        System.out.println("aux2a = "+aux2a.size()+"asd i"+i);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode( "Kits Disponibles" );

        while(aux2a.size()>i)
        {
            DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(aux2a.get(i).getNombreKit());
            root.add(nodo);
            i++;
        }
        
       
        DefaultTreeModel modeloarbol = new DefaultTreeModel(root);
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) jTree1.getCellRenderer();
        ImageIcon leafIcon = createImageIcon("/Iconos/cart.png");
        Icon closedIcon = createImageIcon("/Iconos/boxing_gloves_red.png");
        Icon openIcon = createImageIcon("/Iconos/boxing_gloves_red.png");
        renderer.setClosedIcon(closedIcon);
        renderer.setOpenIcon(openIcon);
        renderer.setLeafIcon(leafIcon);
        
        jTree1.setModel(modeloarbol);
        traverse(jTree1);
        jTable3.setComponentPopupMenu(popupMenu);
        /*Cargamos datos de promociones*/
        String t4[]={"Nombre","Monto Descuento"};
        modelo4.setColumnIdentifiers(t4);
        jTable4.setModel(modelo4);
        ArrayList<Promociones> promos = new metodosDB().getPromocionesDisponibles();
        i = 0;
        object = new Object[2];
        while(promos.size()>i)
        {
            object[0] = promos.get(i).getCantidad();
            object[1] = promos.get(i).getMontoDcto();
            modelo4.addRow(object);
            i++;
        }
          
        
    deleteItem.addActionListener(new ActionListener() 
                {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                                int id = -1;
                                boolean error = false;
                                int selected = jTabbedPane1.getSelectedIndex();
                                if (selected == 0)
                                try{
                                    int s = jTable1.getSelectedRow();
                                    id = (int)jTable1.getValueAt(s, 0);
                                    
                                }catch(ArrayIndexOutOfBoundsException a)
                                {
                                JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un item de la tabla");

                                    error = true;
                                }
                                if(selected == 1 )
                                try{
                                    int s = jTable2.getSelectedRow();
                                    id = (int)jTable2.getValueAt(s, 0);
                                     
                                }catch(ArrayIndexOutOfBoundsException a)
                                {
                                JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un item de la tabla");

                                    error = true;
                                }
                                if(selected == 2)
                                try{
                               //     int s = jTable3.getSelectedRow();
                                //    id = (int)jTable3.getValueAt(s, 6);
                                     
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
        
	System.out.println("Datos Cargados Correctamente!")	;
    }
    public void traverse(JTree tree) { 
     //   TreeModel model = jTree1.getModel(); 
     /*  if (model != null) { 
            Object root = model.getRoot(); 
            System.out.println(root.toString()); 
            walk(model,root); 
        } 
        else
            System.out.println("Tree is empty."); 
    } 
 
    protected void walk(TreeModel model, Object o){ 
        int cc; 
        cc = model.getChildCount(o); 
        for( int i=0; i < cc; i++) { 
            Object child = model.getChild(o, i ); 
            if (model.isLeaf(child)) 
                System.out.println("Leaf: "+i+" "+child.toString()); 
            else { 
                System.out.print("\n\rParent: "+i+" "+child.toString()+"\n\r"); 
                walk(model,child ); 
            } 
        } 
    }
    protected static ImageIcon createImageIcon(String path) {
    java.net.URL imgURL = frameInventarioActual.class.getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }*/
  }
    
}
   

