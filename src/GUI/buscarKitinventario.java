/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ObjetosDB.Kitproductos;
import ObjetosDB.Productos;
import ObjetosDB.VentaProducto;
import ObjetosDB.metodosDB;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author tars
 */
public class buscarKitinventario extends javax.swing.JFrame {
    DefaultTableModel modelo3 = new DefaultTableModel();//Kits
    ArrayList<Productos> aux2a;
    DefaultTableModel modelo0;
    javax.swing.JTable jTable1;
    ArrayList<Productos> carroProductos = new  ArrayList<Productos>();
    javax.swing.JLabel j19,j20,j21,j22,j24;
    int montoDescuento , descuentoKit = 0 , sumakit = 0;
    /**
     * Creates new form buscarKitInventario
     */
    public buscarKitinventario(ArrayList<Productos> carroProductos,DefaultTableModel modelo0,javax.swing.JTable jTable1, javax.swing.JLabel j19, javax.swing.JLabel j20, javax.swing.JLabel j21, javax.swing.JLabel j22, int montoDescuento,ArrayList<Productos> aux2a,javax.swing.JLabel j24) {
        this.carroProductos = carroProductos;
        this.aux2a = aux2a;
        this.modelo0=modelo0;
        this.jTable1 = jTable1;
        this.j19=j19;
        this.j20=j20;
        this.j21 = j21;
        this.j24 = j24;
        this.j22 = j22;
        this.montoDescuento = montoDescuento;
        try {
            initComponents();
            iniciar();
        } catch (SQLException ex) {
            Logger.getLogger(buscarKitinventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void arreglarPrecios(ArrayList<Productos> carroProductos)
    {
           int preciokit = Integer.parseInt(jLabel7.getText());
         
    }
    public void calculaTotales(ArrayList<Productos> carroProductos)
        {
            int pkit = Integer.parseInt(jLabel7.getText());
            descuentoKit = sumakit - pkit;
            int montoNeto=0;
            int iva_pesos = 0;
            int iva_porcentaje = 19;
            int montoTotal =0;
            montoDescuento = Integer.parseInt(j20.getText()) + descuentoKit;
            for(int i = 0 ; i< carroProductos.size();i++)
            {
                Productos producto = carroProductos.get(i);
                montoNeto+= producto.getPrecioVenta();
                System.out.println(montoNeto+"\n");
            }
           //j24.setText(Integer.toString(pkit));
           j19.setText(Integer.toString(montoNeto));
           j20.setText(Integer.toString(montoDescuento));
           int sumaPrevia = montoNeto-montoDescuento;
           iva_pesos = (sumaPrevia * iva_porcentaje)/100;
           j21.setText(Integer.toString(iva_pesos));
           montoTotal = montoNeto  - montoDescuento ;
           j22.setText(Integer.toString(montoTotal));
           
        }
    
    public void iniciar() throws SQLException
    {
        /*Cargamos arbol de Kits*/
         final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Información del Producto");
         
                deleteItem.addActionListener(new ActionListener() 
                {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                                int id = -1;
                                boolean error = false;
                                try{
                                    int s = jTable3.getSelectedRow();
                                     id = (int)jTable3.getValueAt(s, 0);
                                     
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
        
        String t3[] = {"ID","KIT","PCompra","PVenta","Descripcion"};
        modelo3.setColumnIdentifiers(t3);
        jTable3.setModel(modelo3);
        ArrayList<Kitproductos> aux2a;
        metodosDB f = new metodosDB();
        aux2a = f.getKitproductos();
        int i=0;
        Object  object = new Object[5];
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
        
    }
    public void traverse(JTree tree) { 
        TreeModel model = jTree1.getModel(); 
        if (model != null) { 
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
    }
  }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTree1);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos incluidos en el Kit", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("DejaVu Sans", 1, 12))); // NOI18N

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable3);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resumen del kit", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("DejaVu Sans", 1, 14))); // NOI18N

        jLabel1.setText("Descripción:");

        jLabel2.setText("Costo Total:");

        jLabel3.setText("Precio:");

        jLabel4.setText("Nombre kit:");

        jLabel9.setText("ID;");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Add.png"))); // NOI18N
        jButton1.setText("Agregar Kit");
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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(178, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
        final int en =1;
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
        jTree1.getLastSelectedPathComponent();
        /* if nothing is selected */
        if (node == null) return;

        Object nodeInfo = node.getUserObject();
        System.out.println(nodeInfo);
        modelo3 = new DefaultTableModel();
        String t3[] = {"Nombre","Color","Marca","Proveedor","Talla","Tipo","idProducto"};
        modelo3.setColumnIdentifiers(t3);
        jTable3.setModel(modelo3);
        int i = 0;
        Object[] object = new Object[7];
        Kitproductos kit = new Kitproductos();

        try {
            kit = new metodosDB().getKitProductoByName((String) nodeInfo);
            /*Actualizar datos de jlabels*/
            jLabel5.setText(kit.getDescripcionKit());
            jLabel6.setText(kit.getPrecioCompraProductos().toString());
            jLabel7.setText(kit.getPrecioVentaKit().toString());
            jLabel8.setText(kit.getNombreKit());
            jLabel10.setText(kit.getIdKitProductos().toString());
            /*Actualizamos datos de la tabla.*/
            ArrayList<Productos> productosDelkit = new metodosDB().getrelacionKitproductos(kit.getIdKitProductos());
            while(productosDelkit.size()>i)
            {
                //agregar los datos a object
                object[0]= productosDelkit.get(i).getNombre();
                object[1] = productosDelkit.get(i).getColor();
                object[2] = productosDelkit.get(i).getMarca();
                object[3] = productosDelkit.get(i).getProveedor();
                object[4] = productosDelkit.get(i).getTalla();
                object[5] = productosDelkit.get(i).getTipo();
                object[6] = productosDelkit.get(i).getId_producto();
                modelo3.addRow(object);
                i++;
            }
            jTable3.setModel(modelo3);

        } catch (SQLException ex) { }
    }//GEN-LAST:event_jTree1MouseClicked
private void imprime(ArrayList<Productos> aux2a)
{
        for(int k=0; k < aux2a.size(); k++)
        {
        System.out.println("Nombre : "+ aux2a.get(k).getNombre());
        System.out.println("Cantidadp : "+ aux2a.get(k).getCantidadp());
        System.out.println("ID : "+ aux2a.get(k).getId_producto());
        }

}
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       /* System.out.println("AUX");
        imprime(aux2a);
        System.out.println("CARROPRODUCTOS");
        imprime(carroProductos);
        */
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
        jTree1.getLastSelectedPathComponent();
        if (node == null) return;

        Object nodeInfo = node.getUserObject();
        Kitproductos kit;
       
        int idkit = Integer.parseInt(jLabel10.getText());
        jTree1.getLastSelectedPathComponent();
        if (node == null) return;
/*
        for(int i=0;i<carroProductosLocal.size();i++)
            {
                System.out.println("Producto a Agregar : "+ carroProductosLocal.get(i).getNombre()+"\n");
                Productos producto = carroProductosLocal.get(i);
                object[0]  = producto.getId_producto();
                object[1] = producto.getNombre();
                object[2] = producto.getTalla();
                object[3] = producto.getMarca();
                object[4] = producto.getTipo();
                object[5] = producto.getProveedor();
                object[6] = producto.getPrecioCompra();
                object[7] = producto.getPrecioVenta();
                object[8] = producto.getCodigo_barra();
                modelo0.addRow(object);
                jTable0.setModel(modelo0);
            }
        */
        try { boolean error=false;
            kit = new metodosDB().getKitProductoByName((String) nodeInfo);
            ArrayList<Productos> productosDelkit = new metodosDB().getrelacionKitproductos(kit.getIdKitProductos());
             for (int k=0;k<productosDelkit.size();k++)
                 
                { 
                System.out.println(productosDelkit.get(k).getNombre());    
               if(!checkArray(productosDelkit,productosDelkit.get(k)))error=true;
                    
                }
            for (Productos pkit : productosDelkit)
            {
                
                int indice = -1;
               
                for(int i = 0 ;i< aux2a.size();i++)
                {   
                    if(aux2a.get(i).getId_producto() == pkit.getId_producto())
                        indice = i;
                }
                if(error==false )
                {
                    int cantidad = pkit.getCantidadp()-1;
                    pkit.setCantidadp(cantidad);
                    aux2a.set(indice, pkit);
                    
                    System.out.println("setead");
                    carroProductos.add(pkit);
                    carroProductos.get(carroProductos.size()-1).setKit(1);
                    Object[] object = new Object[10];
                    object[0]  = pkit.getId_producto();
                    object[1] = pkit.getNombre();
                    object[2] = pkit.getTalla();
                    object[3] = pkit.getMarca();
                    object[4] = pkit.getTipo();
                    object[5] = pkit.getProveedor();
                    object[6] = pkit.getPrecioCompra();
                    object[7] = pkit.getPrecioVenta();
                    object[8] = pkit.getCodigo_barra();
                    sumakit = sumakit +pkit.getPrecioVenta();
                    modelo0.addRow(object);
                    jTable1.setModel(modelo0);
                    
                }else
                {
                    JOptionPane.showMessageDialog(rootPane, "NO HAY INVENTARIO PARA KIT: "+ jLabel8.getText());
                    jframeUsuario a = new jframeUsuario(); 
                    this.dispose();
                    return; 
                       
                }   

            }
            calculaTotales(carroProductos);
            JOptionPane.showMessageDialog(rootPane, "KIT AGREGADO EXITOSAMENTE");
            dispose();
            

        } catch (SQLException ex) {
            Logger.getLogger(buscarKitinventario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed
public boolean checkArray(ArrayList<Productos> array, Productos p)
    {
        for(int a = 0 ; a<array.size();a++)
            if(array.get(a).getId_producto() == p.getId_producto())
            {
                if(array.get(a).getCantidadp()<apariciones(array,p)) return false;
                  
                 
            } 
        return true;
     }
    
    public int apariciones(ArrayList<Productos> array, Productos p)
{
    int contador=0;
    int cantidad = 0;
    for(int i = 0 ; i<array.size();i++){  System.out.println(p.getId_producto());
     System.out.println(array.get(i).getId_producto());
      if(array.get(i).getId_producto() == p.getId_producto()){ 
             System.out.println("TAMAÑO: "+array.size());
            contador=contador+1;
        
       
           
           
        }
  }
       System.out.println("CONTADOR :"+contador);
    return contador;
    
}

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable3;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
