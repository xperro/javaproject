/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Clases.Metodos_objetos;
import Clases.funciones;
import ObjetosDB.*;
import com.mxrck.autocompleter.TextAutoCompleter;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class nuevo_kit extends javax.swing.JDialog implements KeyListener {
 DefaultTableModel modelo1 =new DefaultTableModel();
    int usuario, a = 1, mnsj = 0, global_update, global_suma = 0, op = 0, id_liq = 0;
    int precio_kit=0,precio_TOTAL=0;
    Date fecha = new Date();
    String x, aux_1 = null;
    float pro;
    private JTextField tf;
    int i = -1, flag = 0,  id_sol = 0, cuenta = 0, monto = 0;
    String aux = null, arancel = null, aux4;
    boolean nuevo = true;
    String[][] cuentas;
      ArrayList<Productos> aux2 = new ArrayList<Productos>();
    String[] aux3 = null, aux5;
    String vacio = "NO SE OBTUVO RESULTADO CON LOS PARAMETROS INGRESADOS.";
    String correcto = "OPERACIÓN REALIZADA CON ÉXITO.", error = "HA OCURRIDO EL SIGUIENTE ERROR:\n";
    String no_empleado = "NO SE REGISTRA EMPLEADO CON EL RUT INGRESADO.";
    String no_opcion = "DEBE SELECCIONAR UNA DE LAS OPCIONES DEL SISTEMA.";
    String no_numero = "DEBE INGRESAR UN DATO NUMÉRICO EN EL CAMPO ";
    String num_no_valido = "DEBE INGRESAR UN NÚMERO VÁLIDO EN EL CAMPO ";
    String campo_vacio = "DEBE COMPLETAR EL SIGUIENTE CAMPO:\n";
    TextAutoCompleter autocompleter;
    DefaultTableModel modelo = new DefaultTableModel() {
private JComboBox combo1;
        @Override
        public boolean isCellEditable(int row, int column) { // sobreescribe el metodo para convertir la celda ingresada en NO editable
            return false;
        }
    };

    public nuevo_kit(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        iniciar();

    }

    nuevo_kit() throws SQLException {
        initComponents();
        iniciar();
    }

    nuevo_kit(int u, int idliq, int rut_emp, int p, String nom_emp, String nom_benef, int rut_benef) throws SQLException { // constructor que viene de el listado de solicitudes de seguro
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
        Calendar c = Calendar.getInstance();
      //  jDateChooser1.setDate(c.getTime());
        //   jDateChooser1.addKeyListener(this);
        jTable1.addKeyListener(this);
        String t[] = {"NOMBRE PRODUCTO","TALLA","MARCA", "TIPO", "PRECIO VENTA", "COSTO", "CANTIDAD","PROVEEDOR","COD.","ID"};
        modelo.setColumnIdentifiers(t);
        jTable1.setRowHeight(22);
        jTable1.setModel(modelo);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr2.setHorizontalAlignment(SwingConstants.RIGHT);
        ((DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(tcr2);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(tcr2);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(tcr2);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(6).setCellRenderer(tcr2);
//        jTable1.getColumn("NIVEL").setPreferredWidth(1);
        // jTable1.getColumn("# VECES").setPreferredWidth(8);
        // jTable1.getColumn("BENEFICIO").setPreferredWidth(15);
        // jTable1.getColumn("COD.").setPreferredWidth(50);
        // jTable1.getColumn("COPAGO").setPreferredWidth(39);
        //// String[] aux = this.getEstadoDetSol().split("=");
       metodosDB f = new metodosDB();
        modelo.setNumRows(0);
        int aux3;
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
          
		         aux2 = f.getProductos();//Cargar resultados , debe ser por nombres
                    Object[] object = new Object[11];
                    int i=0;
                    while(aux2.size()>i){
        object[9]  = aux2.get(i).getId_producto();
        object[0] = aux2.get(i).getNombre();
        object[1] = aux2.get(i).getTalla();
        object[2] = aux2.get(i).getMarca();
        object[6] = aux2.get(i).getCantidadActual();
        object[3] = aux2.get(i).getTipo();
        object[7] = aux2.get(i).getProveedor();
        object[5] = aux2.get(i).getPrecioCompra();
        object[4] = aux2.get(i).getPrecioVenta();
        object[8] = aux2.get(i).getCodigo_barra();
         modelo.addRow(object);i++;}
		// Accion a realizar cuando el JComboBox cambia de item seleccionado.
		
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("NUEVO KIT PRODUCTOS");
        setModal(true);
        setResizable(false);

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/accept.png"))); // NOI18N
        jButton1.setText("GUARDAR NUEVO KIT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/delete.png"))); // NOI18N
        jButton2.setText("CERRAR");
        jButton2.setFocusCycleRoot(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos Disponibles", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jTable1.setAutoCreateRowSorter(true);
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
        jTable1.setSurrendersFocusOnKeystroke(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jButton8.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Remove.png"))); // NOI18N
        jButton8.setText("ELIMINAR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Add.png"))); // NOI18N
        jButton9.setText("AGREGAR");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/zoom.png"))); // NOI18N
        jButton5.setText("BUSCAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel3.setText("BUSCAR PRODUCTOS DISPONIBLES POR NOMBRE:");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Productos Agregados Al Kit", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jTable2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jTable2.setToolTipText("");
        jTable2.setColumnSelectionAllowed(true);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);

        jTextField2.setEditable(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel1.setText("COSTO TOTAL");

        jLabel2.setText("PRECIO KIT");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel4.setText("NOMBRE KIT");

        jLabel5.setText("DESCRIPCION");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jLabel6.setText("PRECIO TOTAL");

        jTextField5.setEditable(false);
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(18, 18, 18))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(15, 15, 15)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
int id_producto_asociado=0;
        int id_kit_producto=0;
          int row=0;   Kitproductos kit2=new Kitproductos(0,null,0,0,null);
          Kitproductos kit = new Kitproductos(0,null,0,0,null);
           if (jTextField4.getText().isEmpty() || jTextField4.getText().equals("")||
                   precio_kit==0 
                   
                        ||   Integer.parseInt(jTextField3.getText())==0 
                   ||   jTextArea1.getText().isEmpty() || jTextArea1.getText().equals("")) {
       
                   try {
            // Check whether priceField.getText()'s length equals 0
            Integer.parseInt(jTextField3.getText().trim());
               
            } catch(NumberFormatException e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "costo debe ser un número".toUpperCase());
                return;
                }        // TODO add your handling code here:
          this.setCursor(Cursor.getDefaultCursor());
                mnsj = JOptionPane.showConfirmDialog(null, "DEBE INGRESAR LOS DATOS DEL KIT", "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
        }else{   kit.setNombreKit(jTextField4.getText());
          kit.setPrecioCompraProductos(precio_kit);
          kit.setPrecioVentaKit(Integer.parseInt(jTextField3.getText()));
          kit.setDescripcionKit(jTextArea1.getText());
              
           } metodosDB f=new metodosDB();
     try {
          id_kit_producto=f.addKitProductos(kit);
     } catch (SQLException ex) {
         Logger.getLogger(nuevo_kit.class.getName()).log(Level.SEVERE, null, ex);
     }
          while(row<modelo1.getRowCount()){
           id_producto_asociado = Integer.parseInt(modelo1.getValueAt(row, 9).toString());
    try {
        f.addKit_relacion_productos(id_kit_producto, id_producto_asociado);
        row++;
    } catch (SQLException ex) {
        Logger.getLogger(nuevo_kit.class.getName()).log(Level.SEVERE, null, ex);
    }
           
           }  
       
                      
                      
                      //validar campos
                      //guardar en la base de datos si es consistente
                      this.setCursor(Cursor.getDefaultCursor());this.dispose(); //dispose para salir de la ventana
                JOptionPane.showMessageDialog(this, "CREADO DE FORMA EXITOSA");
                return;
                      
        
    
       

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            new Metodos_objetos().testProductos(new metodosDB().getProductos());
        } catch (SQLException ex) {
            Logger.getLogger(nuevo_kit.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        funciones f = new funciones();
        int linea = jTable2.getSelectedRow();
        
        if (linea != -1) {
            
          precio_kit=precio_kit-(Integer.parseInt(modelo1.getValueAt(linea, 5).toString()));
                 jTextField2.setText(Integer.toString(precio_kit));
                   precio_TOTAL=precio_TOTAL-(Integer.parseInt(modelo1.getValueAt(linea, 4).toString()));
                 jTextField5.setText(Integer.toString(precio_TOTAL));
          //  jTextField13.setText(f.getNumcPto(p));
            //    jTextField14.setText(f.getNumcPto(b2));
            //    jTextField18.requestFocus();
            i = -1;modelo1.removeRow(linea);
        }else{
             
              this.setCursor(Cursor.getDefaultCursor());
                mnsj = JOptionPane.showConfirmDialog(null, "DEBE SELECCIONAR UN PRODUCTO", "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
         } 
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String aux3[];
        int linea = jTable1.getSelectedRow();
        if (evt.getClickCount() == 2 & linea != -1) {

            if (!modelo.getValueAt(linea, 3).toString().equals("-")) { // cuenta con arancel
                //      jTextField2.setEnabled(true);
                //      jTextField6.setEnabled(true);
                //      jTextField2.setText(modelo.getValueAt(linea, 2).toString());
                //      jTextField6.setText(modelo.getValueAt(linea, 3).toString().replace("-", ""));
            } else {
              //  jTextField2.setEnabled(false);
                //  jTextField6.setEnabled(false);
            }
            i = linea;
        
           // jTextField7.setEnabled(true);
            // jTextField9.setEnabled(true);
            int porciento = 0;// Integer.valueOf(jTextField16.getText().replace("%", "").trim());
            int monto = Integer.valueOf(modelo.getValueAt(linea, 4).toString());
          //  jTextField7.setText((monto * 100 / porciento) + "");
            //  jTextField22.setText(modelo.getValueAt(linea, 4).toString());
            //  jTextField9.setText(modelo.getValueAt(linea, 5).toString());
            //  jTextField10.setText(modelo.getValueAt(linea, 6).toString());
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
          int j=0;   jTable1.addKeyListener(this);
                  j=jTable1.getSelectedRow();
     
         if(j==-1){
              this.setCursor(Cursor.getDefaultCursor());
                mnsj = JOptionPane.showConfirmDialog(null, "DEBE SELECCIONAR UN PRODUCTO", "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
         } else{
        
        
        String t[] = {"NOMBRE PRODUCTO","TALLA","MARCA", "TIPO", "PRECIO VENTA", "COSTO", "CANTIDAD","PROVEEDOR","COD.","ID"};
        modelo1.setColumnIdentifiers(t);
        jTable2.setRowHeight(22);
        jTable2.setModel(modelo1);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr2.setHorizontalAlignment(SwingConstants.RIGHT);
        ((DefaultTableCellRenderer) jTable2.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jTable2.getColumnModel().getColumn(0).setCellRenderer(tcr2);
        jTable2.getColumnModel().getColumn(1).setCellRenderer(tcr);
        jTable2.getColumnModel().getColumn(2).setCellRenderer(tcr);
        jTable2.getColumnModel().getColumn(3).setCellRenderer(tcr2);
        jTable2.getColumnModel().getColumn(4).setCellRenderer(tcr2);
        jTable2.getColumnModel().getColumn(5).setCellRenderer(tcr);
        jTable2.getColumnModel().getColumn(6).setCellRenderer(tcr2);
         jTable2.getColumnModel().getColumn(7).setCellRenderer(tcr);
        jTable2.getColumnModel().getColumn(8).setCellRenderer(tcr2);
//        jTable1.getColumn("NIVEL").setPreferredWidth(1);
        // jTable1.getColumn("# VECES").setPreferredWidth(8);
        // jTable1.getColumn("BENEFICIO").setPreferredWidth(15);
        // jTable1.getColumn("COD.").setPreferredWidth(50);
        // jTable1.getColumn("COPAGO").setPreferredWidth(39);
        //// String[] aux = this.getEstadoDetSol().split("=");
           Object[] object = new Object[10];
        object[0]  =  modelo.getValueAt(j, 0);
        object[1] =modelo.getValueAt(j,1);
        object[2] =modelo.getValueAt(j,2);
        object[3] = modelo.getValueAt(j,3);
        object[4] = modelo.getValueAt(j,4);
        object[5] = modelo.getValueAt(j,5);
        object[6] =modelo.getValueAt(j,6);
        object[7] = modelo.getValueAt(j,7);
        object[8] = modelo.getValueAt(j,8);
        object[9] = modelo.getValueAt(j,9);
        modelo1.addRow(object);
    precio_kit=Integer.parseInt(modelo.getValueAt(j,5).toString())+precio_kit;
    
     jTextField2.setText(Integer.toString(precio_kit));
       precio_TOTAL=Integer.parseInt(modelo.getValueAt(j,4).toString())+precio_TOTAL;
    
     jTextField5.setText(Integer.toString(precio_TOTAL));
  limpiar();
}
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        metodosDB f = new metodosDB();
        modelo.setNumRows(0);
        int aux3;
         
        int i=0;
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ArrayList<Productos> aux4 = new ArrayList<Productos>();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Productos aux2 = new Productos();
        // si quiere buscar por solicitud
           String nombre="";
                String var="";
                nombre=jTextField1.getText().toUpperCase();
                
                try {
                    aux4 = f.getProductoByNombre1();//Cargar resultados , debe ser por nombres
                   if(aux4.equals(null)){
                        this.setCursor(Cursor.getDefaultCursor());
                        iniciar();
                mnsj = JOptionPane.showConfirmDialog(null, "NO SE ENCONTRO NINGUN PRODUCTO", "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                   }else{
                    while(aux4.size()>i){   
                       var=aux4.get(i).getNombre().toUpperCase();
                       var=var.replace(" ","");
                       nombre = nombre.replace(" ","");
                       System.out.println(var);
                        System.out.println(nombre);
                            if(var.indexOf(nombre)!=-1){
                    Object[] object = new Object[10];
          object[0]  = aux4.get(i).getId_producto();
        object[1] = aux4.get(i).getNombre();
        object[2] = aux4.get(i).getTalla();
        object[3] = aux4.get(i).getMarca();
        object[4] = aux4.get(i).getCantidadActual();
        object[5] = aux4.get(i).getTipo();
        object[6] = aux4.get(i).getProveedor();
        object[7] = aux4.get(i).getPrecioCompra();
        object[8] = aux4.get(i).getPrecioVenta();
        object[9] = aux4.get(i).getCodigo_barra();
         modelo.addRow(object);}i++;}
                } 
            } catch (SQLException ex) {
                Logger.getLogger(buscar_producto.class.getName()).log(Level.SEVERE, null, ex);
            }

        

       

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

        jButton2ActionPerformed(evt);

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
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
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
    try {
            // Check whether priceField.getText()'s length equals 0
            Integer.parseInt(jTextField3.getText().trim());
               
            } catch(NumberFormatException e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "costo debe ser un número".toUpperCase());
                return ;
                }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed
 private void limpiar(){

     
  
      
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
            java.util.logging.Logger.getLogger(nuevo_kit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nuevo_kit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nuevo_kit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nuevo_kit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                nuevo_kit dialog=null;
                try {
                    dialog = new nuevo_kit(new javax.swing.JFrame(), true);
                } catch (SQLException ex) {
                    Logger.getLogger(nuevo_kit.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
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

}
