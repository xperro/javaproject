/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Clases.Metodos_objetos;
import Clases.funciones;
import static GUI.buscaProductoInventario.createImageIcon2;
import ObjetosDB.*;
import com.mxrck.autocompleter.TextAutoCompleter;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFileChooser;
import java.io.File; 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Transformar extends javax.swing.JDialog implements KeyListener {
double peso1=0;
double peso2=0;
double sobra=0;
Productos pr1= null;
Productos pr2=null;
    int usuario, a = 1, mnsj = 0, global_update, global_suma = 0, op = 0, id_liq = 0;
    Date fecha = new Date();
    String x, aux_1 = null;
    float pro;
    private JTextField tf;
    int i = -1, flag = 0, aux2, id_sol = 0, cuenta = 0, monto = 0;
    String aux = null, arancel = null, aux4;
    boolean nuevo = true;
    String[][] cuentas;
    String[] aux3 = null, aux5;
    String vacio = "NO SE OBTUVO RESULTADO CON LOS PARAMETROS INGRESADOS.";
    String correcto = "OPERACIÓN REALIZADA CON ÉXITO.", error = "HA OCURRIDO EL SIGUIENTE ERROR:\n";
    String no_empleado = "NO SE REGISTRA EMPLEADO CON EL RUT INGRESADO.";
    String no_opcion = "DEBE SELECCIONAR UNA DE LAS OPCIONES DEL SISTEMA.";
    String no_numero = "DEBE INGRESAR UN DATO NUMÉRICO EN EL CAMPO ";
    String num_no_valido = "DEBE INGRESAR UN NÚMERO VÁLIDO EN EL CAMPO ";
    String campo_vacio = "DEBE COMPLETAR EL SIGUIENTE CAMPO:\n";
    String proveedor=null;
    TextAutoCompleter autocompleter;
            String nombreArchivo = "";
          ArrayList<Cliente> lista =new   ArrayList<Cliente>();
                ArrayList<Productos> productos =new   ArrayList<Productos>();
    DefaultTableModel modelo = new DefaultTableModel() {
private JComboBox combo1;
        @Override
        public boolean isCellEditable(int row, int column) { // sobreescribe el metodo para convertir la celda ingresada en NO editable
            return false;
        }
    };

    public Transformar(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        iniciar();
     

    }

    Transformar() throws SQLException {
        initComponents();
        iniciar();
    }

    Transformar(int u, int idliq, int rut_emp, int p, String nom_emp, String nom_benef, int rut_benef) throws SQLException { // constructor que viene de el listado de solicitudes de seguro
        initComponents();
        String rut = String.valueOf(rut_emp);
        usuario = u;
        funciones f = new funciones();
   //    jTextField1.setEditable(false);
        //    jTextField18.setEditable(false);
        //   jTextField22.setEditable(false);

      //  jTextField18.setText(rut_benef + "");
        //  jTextField19.setText(f.validar_rut(rut_benef + ""));
        // jTextField8.setText(rut);
        //  jTextField11.setText(f.validar_rut(rut));
        pro = Float.parseFloat(p + "") / 100;
        id_liq = idliq;
        //jTextField16.setText(p + " %");
        iniciar();
        //jTextField1.setText("0");
    }

    public void iniciar() throws SQLException {

      //  jTextField8.setEditable(false);
        //  jTextField10.setEditable(false);
        //  jTextField7.setEditable(false);
        //  jTextField2.setEditable(false);
        //   jTextField9.setEditable(false);
        //   jTextField6.setEditable(false);
       productos= new metodosDB().getProductos();
             Collections.sort(productos, new Comparator<Productos>(){

			@Override
			public int compare(Productos o1, Productos o2) {
				return o1.getNombre().compareTo(o2.getNombre());
			}
			
			
		});
  
              for(int k=0;k<productos.size();i++){
                
              if(productos.get(k).getCantidadActual()>=1){
                                                              this.jComboBox4.addItem(productos.get(k).getNombre()+" "+productos.get(k).getTalla()+" "+productos.get(k).getMedida()+ " ID: "+productos.get(k).getId_producto());

              }

                  
                   k++;
              }   

        Calendar c = Calendar.getInstance();
      //  jDateChooser1.setDate(c.getTime());
    
//        jTable1.getColumn("NIVEL").setPreferredWidth(1);
        // jTable1.getColumn("# VECES").setPreferredWidth(8);
        // jTable1.getColumn("BENEFICIO").setPreferredWidth(15);
        // jTable1.getColumn("COD.").setPreferredWidth(50);
        // jTable1.getColumn("COPAGO").setPreferredWidth(39);
        //// String[] aux = this.getEstadoDetSol().split("=");    System.out.println("hola");
    
		
		// Accion a realizar cuando el JComboBox cambia de item seleccionado.
         //Iniciamos la Ward sin nada
        
       
        cargar();
    }

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            evt.setKeyCode(java.awt.event.KeyEvent.VK_TAB);
            //este codigo lo que hace es convertir el enter en tab            
        }

    }

    public void cargar() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        final SwingWorker worker = new SwingWorker() {

            @Override
            protected Void doInBackground() throws Exception {
                try {/*
                     String[] ctas = getcuentas().split("=");
                     cuentas = new String[ctas[0].split(";").length][ctas.length];
                     for (int j = 0; j < ctas.length; j++) {
                     String[] aux = ctas[j].split(";");
                     cuentas[0][j] = aux[0];
                     cuentas[1][j] = aux[1];
                     cuentas[2][j] = aux[2];
                     cuentas[3][j] = aux[3];
                     cuentas[4][j] = aux[4];
                     cuentas[5][j] = aux[5];
                     }*/

                } catch (Exception ex) {
                }
                setCursor(Cursor.getDefaultCursor());
                return null;
            }
        };
        worker.execute();
    }

    int buscar_cta(String nro) {
        for (int j = 0; j < cuentas[0].length; j++) {
            if (cuentas[0][j].equals(nro)) {
                return j;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jButton5 = new javax.swing.JButton();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INGRESAR NUEVO PRODUCTO");
        setModal(true);
        setResizable(false);

        jButton1.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(41, 91, 134));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/disk.png"))); // NOI18N
        jButton1.setText("TRANSFORMAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(206, 12, 12));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/delete.png"))); // NOI18N
        jButton2.setText("CERRAR");
        jButton2.setFocusCycleRoot(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/arrow_rotate_clockwise.png"))); // NOI18N
        jButton4.setText("ATRAS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel2.setText("NOMBRE ");

        jLabel23.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel23.setText("PESO");

        jTextField20.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField20.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField20ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 51));
        jLabel18.setText("CANTIDAD A AGREGAR");

        jTextField22.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField22.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField22ActionPerformed(evt);
            }
        });

        jTextField24.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField24.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField24ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Falta Medida", "Mg", "Gr", "Kg", "Ton", "Lata", "Unidad", "Sachet", "Lt", "Cc" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel3.setText("ELEGIR PRODUCTO DESTINO ");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONAR ITEM" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jButton5.setText("Seleccionar Origen");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONAR ITEM" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel7.setText("ELEGIR PRODUCTO ORIGEN ");

        jLabel1.setText("Transformar a:");

        jButton6.setText("Seleccionar Destino");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel4.setText("DATOS TRANSFORMACION");

        jLabel24.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel24.setText("SOBRA");

        jLabel25.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("0");

        jLabel5.setText("Gr.");

        jLabel26.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel26.setText("CANTIDAD DISPONIBLE ANTERIOR:");

        jLabel27.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("0");

        jLabel6.setText("Gr.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(73, 73, 73)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addGap(29, 29, 29)
                                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jButton6))
                .addGap(9, 9, 9)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel5))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.out.println("SEGEMENTO 0");
        
           System.out.println("CANTIDAD ACTUAL "+this.pr1.getCantidadActual());
        int resta = this.pr1.getCantidadActual()-1;
               System.out.println("PRODUCTO "+pr1.getNombre()+ "! "+pr1.getId_producto());
          System.out.println("RESTA "+resta);
        pr1.setCantidadActual(resta);
        try {
            System.out.println("SEGEMENTO 1");
            /////RESTO 1 AL PRODUCTO ORIGEN
            metodosDB metodos = new metodosDB();
            metodos.updateProductos(this.pr1);
            ///
            System.out.println("SEGEMENTO 2");
            ///SUMO AL PRODUCTO DESTINO
            this.pr2.setCantidadActual(this.pr2.getCantidadActual()+Integer.parseInt(jTextField24.getText()));
            metodos.updateProductos(this.pr2);
            
            //SI EXISTE SOBRA CREO LA RELACION
            System.out.println("SEGEMENTO 3");
            Transformacion trans= new Transformacion();
            trans.setIdCliente(this.pr1.getId_producto());
            System.out.println("ID PRODUCTO SOBRANTE : "+pr1.getId_producto());
            System.out.println("SOBRA : "+jLabel25.getText());
            
            trans.setTipo(Integer.parseInt(jLabel25.getText()));
            metodos.updateTransformaciones(trans);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Transformar.class.getName()).log(Level.SEVERE, null, ex);
        }
                
               
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            new Metodos_objetos().testProductos(new metodosDB().getProductos());
        } catch (SQLException ex) {
            Logger.getLogger(Transformar.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        JFrame frame = null;
      //  dialog_sol_seg a = new dialog_sol_seg(frame, usuario);
        // a.setLocationRelativeTo(null);
        this.dispose();
        // a.setVisible(true);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20ActionPerformed

    private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField22ActionPerformed
        // TODO add your handling code here:safsd
       
    }//GEN-LAST:event_jTextField22ActionPerformed

    private void jTextField24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField24ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {boolean anterior=false;
            int selected=jComboBox4.getSelectedIndex();
                  productos= new metodosDB().getProductos();
               jComboBox5.removeAllItems();
              String nombre=jComboBox4.getSelectedItem().toString();
              nombre=nombre.substring(0,4);
              System.out.println(nombre);
              
             
                          for(int k=0;k<productos.size();i++){
                            
                              if(productos.get(k).getNombre().indexOf(nombre)!=-1){
                                               // System.out.println("hola");                      
                                  this.jComboBox5.addItem(productos.get(k).getNombre()+" "+productos.get(k).getTalla()+" "+productos.get(k).getMedida()+ " ID: "+productos.get(k).getId_producto());

                     
                              metodosDB metodos= new metodosDB();
                             Transformacion trans= metodos.gettransById(productos.get(k).getId_producto());
                             
                              if(trans==null){
                                  anterior=false;
                              }else{
                                  anterior=true; jLabel27.setText(Integer.toString(trans.getTipo()));
                              }
                              
                              
                              
                              }
                              
                              
                               k++;
                          }   
                    
                    
                    
                    
                  
        } catch (SQLException ex) {
            Logger.getLogger(Transformar.class.getName()).log(Level.SEVERE, null, ex);
        }
  
  
  
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try { int selected2=jComboBox4.getSelectedIndex();
            int selected=jComboBox5.getSelectedIndex();
                  productos= new metodosDB().getProductos();
              String nombre=jComboBox5.getSelectedItem().toString();
        String nombre2=     jComboBox4.getSelectedItem().toString();
              System.out.println(nombre);
                          for(int k=0;k<productos.size();i++){
                           String nombre3= productos.get(k).getNombre()+" "+productos.get(k).getTalla()+" "+productos.get(k).getMedida()+ " ID: "+productos.get(k).getId_producto();
                              if(nombre3.indexOf(nombre)!=-1){
                                               // System.out.println("hola");        System.out.println(nombre);                
                       System.out.println(nombre);            jTextField22.setText(productos.get(k).getNombre());
                                    jTextField20.setText(productos.get(k).getTalla());
                                    
                                      if(productos.get(k).getMedida().equals("Falta Medida")){
                 jComboBox3.setSelectedIndex(0);  
        }
                  peso2=Double.parseDouble(productos.get(k).getTalla());
                                
         if(productos.get(k).getMedida().equals("Mg")){
                 jComboBox3.setSelectedIndex(1);  
                  peso2=peso2*0.001;
        }
          if(productos.get(k).getMedida().equals("Gr")){
                 jComboBox3.setSelectedIndex(2);      peso2=peso2*1;
             
        }
           if(productos.get(k).getMedida().equals("Kg")){
                 jComboBox3.setSelectedIndex(3);      peso2=peso2*1000;
        }
            if(productos.get(k).getMedida().equals("Ton")){
                 jComboBox3.setSelectedIndex(4);      peso2=peso2*1000000;
        }
               if(productos.get(k).getMedida().equals("Lata")){
                 jComboBox3.setSelectedIndex(5);          peso1=0;
                 peso2=1;               JOptionPane.showMessageDialog(rootPane,  "NO ES TRANSFORMABLE".toUpperCase());  return; 

        }     
                     if(productos.get(k).getMedida().equals("Unidad")){
                 jComboBox3.setSelectedIndex(6);         peso1=0;
                 peso2=1;     JOptionPane.showMessageDialog(rootPane,  "NO ES TRANSFORMABLE".toUpperCase());  return; 

        }     if(productos.get(k).getMedida().equals("Sachet")){
                 jComboBox3.setSelectedIndex(7);    peso1=0;
                 peso2=1;             JOptionPane.showMessageDialog(rootPane,  "NO ES TRANSFORMABLE".toUpperCase());  return; 

        } if(productos.get(k).getMedida().equals("Lt")){
                 jComboBox3.setSelectedIndex(8);     peso1=0;
                 peso2=1;          JOptionPane.showMessageDialog(rootPane,  "NO ES TRANSFORMABLE".toUpperCase());  return; 

        } if(productos.get(k).getMedida().equals("Cc")){
                 jComboBox3.setSelectedIndex(9);     peso1=0;
                 peso2=1;    JOptionPane.showMessageDialog(rootPane,  "NO ES TRANSFORMABLE".toUpperCase());  return; 

        }   jTextField22.setEnabled(false);
pr2=productos.get(k);
            }
                              
                              
                               k++;
                          } 
          metodosDB metodos= new metodosDB();
        ArrayList<Productos> productos= new ArrayList<Productos>(); 
         productos=metodos.getProductos();
          System.out.println("EL PRODUCTO ES: "+nombre2);                      

        for(int r=0;r<productos.size();i++){
                     String product1=      productos.get(r).getNombre()+" "+productos.get(r).getTalla()+" "+productos.get(r).getMedida()+ " ID: "+productos.get(r).getId_producto() ;
                              if(product1.equals(nombre2)){
                                              System.out.println("hola");                      

                       
                              this.peso1= Double.parseDouble(productos.get(r).getTalla());
                                        System.out.println("EL PRODUCTO ES: "+productos.get(r).getNombre());  
                              
                               if(productos.get(r).getMedida().equals("Mg")){
                 peso1=peso1*0.001; 
        }
          if(productos.get(r).getMedida().equals("Gr")){
                   peso1=peso1*1; 
        }
           if(productos.get(r).getMedida().equals("Kg")){
               peso1=           (peso1*1000); 
        }
            if(productos.get(r).getMedida().equals("Ton")){
                peso1=           (peso1*1000000); 
        }
               if(productos.get(r).getMedida().equals("Lata")){
                  JOptionPane.showMessageDialog(rootPane,  "NO ES TRANSFORMABLE".toUpperCase());  return; 
        }     
                     if(productos.get(r).getMedida().equals("Unidad")){
                 jComboBox3.setSelectedIndex(6);    
                 peso2=1; 
        }     if(productos.get(r).getMedida().equals("Sachet")){
                     peso1=0;
                 peso2=1; JOptionPane.showMessageDialog(rootPane,  "NO ES TRANSFORMABLE".toUpperCase());  return;
        } if(productos.get(r).getMedida().equals("Lt")){
                   peso1=0;
                 peso2=1;   JOptionPane.showMessageDialog(rootPane,  "NO ES TRANSFORMABLE".toUpperCase());  return; 
        } if(productos.get(r).getMedida().equals("Cc")){
                  peso1=0;
                 peso2=1;   JOptionPane.showMessageDialog(rootPane,  "NO ES TRANSFORMABLE".toUpperCase());  return;
        }   jTextField22.setEnabled(false);
                       // CALCULO DE PESOS Y CANTIDADES DE TRANSFORMACION Y SETEO DE JLABEL       
                              int gr=Integer.parseInt(jLabel27.getText());
                              peso1=peso1+Integer.parseInt(jLabel27.getText());
                            System.out.println("MUESTRA EL GR:" + gr);
                     int cantidad= (int)(peso1/peso2);
                           System.out.println("MUESTRA EL peso2:" + peso2);
                           
                                   System.out.println("MUESTRA EL peso1:" + peso1);
                     int sobra = (int) (peso1%peso2);
        jTextField24.setText(Integer.toString(cantidad));
        jLabel25.setText(Integer.toString(sobra));
        
        
        ///TERMINO DE CALCULO///
        
        jTextField20.setEnabled(false);
jTextField24.setEnabled(false);
  pr1=productos.get(r);
                              }
                              
                              
                               r++;
                          }   
                    
                    
                    
                    
                  
        } catch (SQLException ex) {
            Logger.getLogger(Transformar.class.getName()).log(Level.SEVERE, null, ex);
        }
  
  
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed
  
    private void limpiar(){
         jTextField22.setText("");
        jTextField20.setText(""); 
        jComboBox3.setSelectedIndex(0); 
       // jTextField27.setText(""); 
         jTextField24.setText("");
     jTextField22.setEditable(true); 
     jTextField20.setEditable(true);  
    }
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
            java.util.logging.Logger.getLogger(Transformar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transformar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transformar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transformar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                Transformar dialog = null;
                try {
                    dialog = new Transformar(new javax.swing.JFrame(), true);
                } catch (SQLException ex) {
                    Logger.getLogger(Transformar.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField24;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyPressed(KeyEvent e) {
        int op = 0, mnsj;
        ActionEvent ex = null;
        //if (jButton2.hasFocus()) {
        if (e.getKeyCode() == 116) { // tecla F5
            //  jTextField8.setText("");
            this.jButton1ActionPerformed(ex);

        }

        if (e.getKeyCode() == 27) { // tecla ESC
            if (e.getKeyCode() == 27) {
                dispose();
            }
        }
        /*  if (e.getKeyCode() == 10 & jTextField11.hasFocus()) { // tecla enter
         // this.rutSocioActionPerformed(ex);
         }*/

    }

    @Override
    public void keyReleased(KeyEvent e) {
        ActionEvent ex = null;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        ActionEvent ex = null;

    }

    public boolean validarFecha(int dia, int mes, int anio) {
        if (0 < mes & mes > 13) {
            return false;
        } else {
            if (0 < dia & dia > 32) {
                return false;
            } else {
                if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && (dia > 30)) {
                    return false;
                } else {
                    if (mes == 2 && bisiesto(anio) && dia > 29) {
                        return false;
                    } else if (mes == 2 && !bisiesto(anio) && dia > 28) {
                        return false;
                    } else {
                        if (anio + 1 < Calendar.getInstance().getTime().getYear() + 1900) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
            }
        }
    }

    public boolean bisiesto(int anio) {
        if (anio % 400 == 0) {
            return true;
        } else {
            if (anio % 4 == 0 && anio % 100 != 0) {
                return true;
            } else {
                return false;
            }
        }
    }
    public boolean validaCampos()
    {   
        
        //jtextfield22 == nombre
        try {
            // Check whether priceField.getText()'s length equals 0
            if(jTextField22.getText().trim().length() == 0) 
                {
                throw new Exception();
                }
            } catch(Exception e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "nombre no puede estar vacío".toUpperCase());
                return false;
                }
        //jtexfield 20 = talla
        try {
            // Check whether priceField.getText()'s length equals 0
            if(jTextField20.getText().trim().length() == 0) 
                {
                throw new Exception();
                }
            } catch(Exception e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "talla no puede estar vacío".toUpperCase());
                return false;
                }
        //jtexfield 23 = marca
        
        
        //jtexfield 28 = color

         //jtexfield 24 = cantidad
        try {
            // Check whether priceField.getText()'s length equals 0
            if(jTextField24.getText().trim().length() == 0) 
                {
                throw new Exception();
                }
            } catch(Exception e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "cantidad no puede estar vacío".toUpperCase());
                return false;
                }
        
         try {
            // Check whether priceField.getText()'s length equals 0
            Integer.parseInt(jTextField24.getText().trim());
               
            } catch(NumberFormatException e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "cantidad debe ser un número".toUpperCase());
                return false;
                }
         
      
        
     
          //jtexfield 26 precio venta
            return true;
    }
     protected static ImageIcon createImageIcon2(String path) {
         System.out.println(path);
     

    if (path != null) {
        ImageIcon imageIcon =new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image nueva = image.getScaledInstance(250,200,java.awt.Image.SCALE_FAST);
        imageIcon = new ImageIcon(nueva);
        
      return imageIcon;
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
    
  }
   public static void copyFile(File sourceFile, File destFile) throws IOException {
    if(!destFile.exists()) {
        destFile.createNewFile();
    }

    FileChannel source = null;
    FileChannel destination = null;

    try {
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        destination.transferFrom(source, 0, source.size());
    }
    finally {
        if(source != null) {
            source.close();
        }
        if(destination != null) {
            destination.close();
        }

    }
    jframe1 a = new jframe1();
    JOptionPane.showMessageDialog(a, "IMAGEN ALMACENADA CORRECTAMENTE");
    

}
      
}
