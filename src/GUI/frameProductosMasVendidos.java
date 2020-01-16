/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ObjetosDB.Productos;
import ObjetosDB.VentaProducto;
import ObjetosDB.metodosDB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tars
 */
public class frameProductosMasVendidos extends javax.swing.JFrame {
        DefaultTableModel modelo= new DefaultTableModel(); // Tabla Productos
        String t3[] = {"ID","NOMBRE","CANTIDAD VENDIDA"};
        
    public frameProductosMasVendidos() 
    {
        initComponents();
        iniciar();
    }
    public void iniciar()
    {
        modelo.setColumnIdentifiers(t3);
        jTable1.setModel(modelo);
        carga();
        
    }
    public String getSelectedMes()
    {
        int s = jComboBox1.getSelectedIndex()+1;
        if(s<10)
            return "0"+Integer.toString(s);
        else
            return Integer.toString(jComboBox1.getSelectedIndex()+1);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PRODUCTOS MAS VENDIDOS");

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
        jScrollPane1.setViewportView(jTable1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/arrow_undo.png"))); // NOI18N
        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        jLabel5.setText("Seleccione mes:");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/zoom.png"))); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2015", "2016", "2017", "2018", "2019", "2020" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
       carga();
    }//GEN-LAST:event_jButton2ActionPerformed
public void carga()
{
    String year=jComboBox2.getSelectedItem().toString();
        try {
            
            ArrayList<VentaProducto> masVendidos = new metodosDB().getMasVendidos(getSelectedMes(),year);
            empaqueta(masVendidos);
            if(masVendidos.size()>0)
            {
                for(VentaProducto v : masVendidos)
                    {
                        Object[] object = new Object[6];
                        object[0] = v.getIdProducto();
                        object[1] = v.getProducto().getNombre();
                        object[2] = v.getCantidadProducto();
                        modelo.addRow(object);

                    }
            }
            if(masVendidos.size()==0)
            {
                for(int k=0;k<modelo.getRowCount();k++)
               modelo.removeRow(k);
                
            }
            } catch (SQLException ex) {JOptionPane.showMessageDialog(rootPane,  "ERROR EN LA BASE DE DATOS".toUpperCase());}
}
public void empaqueta(ArrayList<VentaProducto> ventas)
{
    int cantidad = 0;
    for(int i = 0 ; i<ventas.size();i++)
    {
        VentaProducto v = ventas.get(i);
        cantidad = v.getCantidadProducto();
        for(int j = i+1 ; j<ventas.size();j++)
        {
            if(ventas.get(j).getProducto().getId_producto() == v.getProducto().getId_producto())
            {
                cantidad = cantidad + ventas.get(j).getCantidadProducto();
                v.setCantidadProducto(cantidad);
                ventas.remove(j);
                
            }
        }
    }
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
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
