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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class modificarordenes extends javax.swing.JDialog implements KeyListener {
String FechaEmision;
    int usuario, a = 1, mnsj = 0, global_update, global_suma = 0, op = 0, id_liq = 0;
    Date fecha = new Date();
    String x, aux_1 = null;
    float pro;
    private JTextField tf;
    int i = -1, flag = 0, aux2, id_sol = 0, cuenta = 0, monto = 0;
    String aux = null, arancel = null, aux4;
    boolean nuevo = true;
    String[][] cuentas;
    int ordenn=0;
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

    public modificarordenes(java.awt.Frame parent, boolean modal) throws SQLException, ParseException {
        super(parent, modal);
        initComponents();
        iniciar(0);
     

    }

    modificarordenes() throws SQLException, ParseException {
        initComponents();
        iniciar(0);
    }

    modificarordenes(int ordendecompra) throws SQLException, ParseException { // constructor que viene de el listado de solicitudes de seguro
        initComponents();
       
   //    jTextField1.setEditable(false);
        //    jTextField18.setEditable(false);
        //   jTextField22.setEditable(false);

      //  jTextField18.setText(rut_benef + "");
        //  jTextField19.setText(f.validar_rut(rut_benef + ""));
        // jTextField8.setText(rut);
        //  jTextField11.setText(f.validar_rut(rut));
        //jTextField16.setText(p + " %");
        iniciar(ordendecompra);
        
        //jTextField1.setText("0");
    }

    public void iniciar(int orden) throws SQLException, ParseException {
ArrayList<CompraProducto> compras = new ArrayList<CompraProducto>();
      //  jTextField8.setEditable(false);
        //  jTextField10.setEditable(false);
        //  jTextField7.setEditable(false);
        //  jTextField2.setEditable(false);
        //   jTextField9.setEditable(false);
        //   jTextField6.setEditable(false);
        metodosDB f = new metodosDB();
        OrdenDeCompra order = f.getOrdenDeCompraById(orden);
        compras=f.getCompraProductoByIdOrdenCompra(orden);
        int var=0;
      
       productos= new metodosDB().getProductos();
       Collections.sort(productos, new Comparator<Productos>(){

			@Override
			public int compare(Productos o1, Productos o2) {
				return o1.getNombre().compareTo(o2.getNombre());
			}
			
			
		});
  
              for(int k=0;k<productos.size();i++){
                
                  
                      this.jComboBox4.addItem(productos.get(k).getNombre()+" "+productos.get(k).getTalla()+" "+productos.get(k).getMedida()+ " ID: "+productos.get(k).getId_producto());
                  
                   k++;
              }   
       
       
       

        Calendar c = Calendar.getInstance();
      //  jDateChooser1.setDate(c.getTime());
        //   jDateChooser1.addKeyListener(this);
        jTable1.addKeyListener(this);
        String t[] = {"NOMBRE PRODUCTO","PESO","MARCA", "TIPO", "PRECIO VENTA", "COSTO NETO","COSTO BRUTO","CANTIDAD","PROVEEDOR","COD.","ID","IMPUESTO HARINA","MEDIDA","TIPO"};
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
        jTable1.getColumnModel().getColumn(6).setCellRenderer(tcr2);
        
           jTable1.getColumnModel().getColumn(8).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(8).setMinWidth(0);
            jTable1.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
            jTable1.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
            
            
              
           jTable1.getColumnModel().getColumn(13).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(13).setMinWidth(0);
            jTable1.getTableHeader().getColumnModel().getColumn(13).setMaxWidth(0);
            jTable1.getTableHeader().getColumnModel().getColumn(13).setMinWidth(0);
//        jTable1.getColumn("NIVEL").setPreferredWidth(1);
        // jTable1.getColumn("# VECES").setPreferredWidth(8);
        // jTable1.getColumn("BENEFICIO").setPreferredWidth(15);
        // jTable1.getColumn("COD.").setPreferredWidth(50);
        // jTable1.getColumn("COPAGO").setPreferredWidth(39);
        //// String[] aux = this.getEstadoDetSol().split("=");    System.out.println("hola");
    
		
		// Accion a realizar cuando el JComboBox cambia de item seleccionado.
         //Iniciamos la Ward sin nada
        
       
         
         jTextField28.setText("0");
         jTextField28.setEditable(false);
         int suma=0;
         while(var<compras.size()){
            
           Productos product= f.getProductoById(compras.get(var).getIdProductoCompra());
              Object[] object = new Object[14];
        object[0] = product.getNombre();
        object[1] = product.getTalla();
        object[2] = product.getMarca();
        object[4] = product.getPrecioVenta();
        object[3] = product.getTipo();
        object[8] = product.getProveedor();
        object[5] = product.getPrecioCompra();
        object[7] = compras.get(var).getCantidad();
        object[9] = product.getCodigo_barra();
        object[10] = product.getId_producto();
            object[11] = product.getHarina();
                   object[12] = product.getMedida();
                   object[13] = order.getTipo();
      
                    if(product.getHarina()==1){
                        double imp2=  (double) ( compras.get(0).getMontoUnitario()*0.19);
                          double imp=  (double) ( compras.get(0).getMontoUnitario()*0.12);
                    System.out.println(imp2);
             int var3 = (int) Math.ceil(imp2); 
             int var4 = (int) Math.ceil(imp); 
                        object[6] = product.getPrecioCompra()+var3+var4;
                 }else{
                     
                     double imp2=  (double) (product.getPrecioCompra()*0.19);
                    System.out.println(imp2);
             int var3 = (int) Math.ceil(imp2); 
                      object[6] = var3+product.getPrecioCompra();
                 }
                   
                   
                   
                   
        modelo.addRow(object);
        suma=suma+product.getPrecioCompra();;
            //TERMINO DATOS TABLA
        limpiar(); var++;
        }jTextField27.setText(Integer.toString(order.getNumeroFacturaRecibida()));
        this.ordenn=orden;
        
        lista= new metodosDB().getClientes();int prove=0;
              for(int i=0;i<lista.size();i++){
                  if(lista.get(i).getTipo()==1){
                  
                      this.jComboBox2.addItem(lista.get(i).getNombre());
                      if(lista.get(i).getNombre().equals(order.getProveedor())){
                           prove=this.jComboBox2.getItemCount()-1;System.out.println("HOLA");
                      }
                  }
                   
              }
        
        this.jComboBox2.setSelectedIndex(prove);
        
        
        DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
String dateAsString = "25/12/2010";
Date date = sourceFormat.parse(order.getFechaEmision());
        jDateChooser2.setDate(date);
        
        
           jLabel17.setText(Integer.toString(this.getMonto()));
                jLabel21.setText(Integer.toString(this.getMontoNeto()));
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
public int getMonto(){
    int suma=0;
          for(int i=0;i<modelo.getRowCount();i++){
              
            int a=Integer.parseInt(modelo.getValueAt(i, 5).toString());
             int b=Integer.parseInt(modelo.getValueAt(i, 7).toString());
             
             suma=suma+a*b;
          }return suma;
}
public int getMontoNeto(){
    int suma=0;
          for(int i=0;i<modelo.getRowCount();i++){
              
            int a=Integer.parseInt(modelo.getValueAt(i, 6).toString());
             int b=Integer.parseInt(modelo.getValueAt(i, 7).toString());
             
             suma=suma+a*b;
          }return suma;
}
public void eliminarguia(int order) throws SQLException{
    
   
      metodosDB metodos= new metodosDB();
       OrdenDeCompra orden = new OrdenDeCompra();
     orden=  metodos.getOrdenDeCompraById(order);
      
            try {
                ArrayList<CompraProducto> compra = metodos.getCompraProductoByIdOrdenCompra(order);
                int m=0;
                while(m<compra.size()){
                  
                    Productos produc = metodos.getProductoById(compra.get(m).getIdProductoCompra());
                    produc.setCantidadActual(produc.getCantidadActual()-compra.get(m).getCantidad());
                   System.out.println("regresado "+produc.getCantidadActual());
                   metodos.addrespaldoOrdenCompraProducto(compra.get(m));
                    metodos.updateProductos(produc);
                    m++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(buscar_eliminar_guia.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                metodos.deleteOrden(orden);                                    JOptionPane.showMessageDialog(rootPane, "Guia o Factura Eliminada");

             this.dispose();   
            } catch (SQLException ex) {
                Logger.getLogger(buscar_eliminar_guia.class.getName()).log(Level.SEVERE, null, ex);
            }
    
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
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INGRESAR NUEVO PRODUCTO");
        setModal(true);
        setResizable(false);

        jButton1.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(41, 91, 134));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/disk.png"))); // NOI18N
        jButton1.setText("MODIFICAR DESPACHO");
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

        jButton4.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/arrow_rotate_clockwise.png"))); // NOI18N
        jButton4.setText("ATRAS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/stock_id.png"))); // NOI18N
        jButton3.setText("Escanear Código de Barra");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("COSTO");

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel5.setText("TIPO");

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel6.setText("MARCA");

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
        jLabel18.setText("CANTIDAD A AGREGAR");

        jLabel11.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 255, 0));
        jLabel11.setText("PRECIO VENTA");

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 51));
        jLabel12.setText("PROVEEDOR");

        jTextField22.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField22.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField22ActionPerformed(evt);
            }
        });

        jTextField23.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField23.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField23ActionPerformed(evt);
            }
        });

        jTextField24.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField24.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField24ActionPerformed(evt);
            }
        });

        jTextField25.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField25.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField25ActionPerformed(evt);
            }
        });

        jTextField26.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField26.setForeground(new java.awt.Color(51, 204, 0));
        jTextField26.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField26ActionPerformed(evt);
            }
        });

        jTextField27.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField27.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField27ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jButton9.setForeground(new java.awt.Color(61, 182, 70));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Add.png"))); // NOI18N
        jButton9.setText("Agregar Producto");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONAR ITEM", "GATOS", "PERROS", "AVES Y OTROS", "PANADERIA  PASTELERIA", "FRUTOS SECOS Y LEGUMBRES", "CONFITERIA", "OTROS" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField1.setBackground(new java.awt.Color(204, 204, 204));
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setToolTipText("CODIGO");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField1.setCaretColor(new java.awt.Color(204, 0, 0));
        jTextField1.setDisabledTextColor(new java.awt.Color(102, 255, 102));

        jLabel13.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 51));
        jLabel13.setText("FECHA EMISION");

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 0, 51));
        jCheckBox1.setText("Impuesto Harina");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Falta Medida", "Mg", "Gr", "Kg", "Ton", "Lata", "Unidad", "Sachet", "Lt", "Cc" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel3.setText("ELEGIR PRODUCTO ");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONAR ITEM" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jButton5.setText("Seleccionar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel4.setText("ID");

        jTextField28.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField28.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField28ActionPerformed(evt);
            }
        });

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "GUIA DESPACHO", "FACTURA" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 51));
        jLabel14.setText("N° FACTURA/GUIA");

        jDateChooser2.setDateFormatString("dd/MM/yyyy");
        jDateChooser2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel1.setText("+imp : =");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("0");

        jLabel8.setText("+imp : =");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("0");

        jButton8.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Remove.png"))); // NOI18N
        jButton8.setText("ELIMINAR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("STOCK CRITICO");

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("5");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("MONTO TOTAL NETO:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("0");

        jLabel19.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel19.setText("TIPO GUIA");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("MONTO TOTAL BRUTO:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel3)
                            .addComponent(jLabel19))
                        .addGap(109, 109, 109)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton5)
                                .addGap(31, 31, 31))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(136, 136, 136))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBox5, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField27, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField24))
                                .addGap(136, 136, 136))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel23)
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel10))
                                                .addGap(44, 44, 44)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jTextField23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel15)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jButton3))
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jTextField1))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1050, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(7, 7, 7)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox1)))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        
       
        
        boolean correcto=false;
            boolean correcto2=false;
        int montototal=0;   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
Date date = new Date();
 metodosDB metodos3= new metodosDB();
   int idordendecompra=0;
   jComboBox2.setEditable(true);int ordenumero=0;
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String date2 = sdf.format(jDateChooser2.getDate());
;
                     OrdenDeCompra orden = new OrdenDeCompra(0,dateFormat.format(date),Integer.parseInt(jLabel17.getText()),Integer.parseInt(jTextField27.getText()), modelo.getValueAt(0, 8).toString(),modelo.getValueAt(0, 13).toString(),date2);
          
             ordenumero=this.ordenn;
        try {
            idordendecompra=metodos3.getLastId("compraproducto");
        } catch (SQLException ex) {
            Logger.getLogger(modificarordenes.class.getName()).log(Level.SEVERE, null, ex);
        }
           try {  metodos3.addOrdenCompra(orden);
                    this.eliminarguia(ordenumero);} catch (SQLException ex) {
                    Logger.getLogger(modificarordenes.class.getName()).log(Level.SEVERE, null, ex);
                }
        for (int j = 0; j < modelo.getRowCount(); j++) {
         int mont=0;  
            if(modelo.getRowCount()!=0){
            
                    
                    

                try {
                    ordenumero=   metodos3.getLastId("ordendecompra");
                } catch (SQLException ex) {
                    Logger.getLogger(modificarordenes.class.getName()).log(Level.SEVERE, null, ex);
                }
            ArrayList<Productos> productos = new ArrayList();
 //Productos(String nombre, String marca, String talla, String color, int precioCompra, int precioVenta, String proveedor, int cantidadActual) { //Constructor
            String color = ""; //deberia ser modelo.getColor
            String tipo = "";
            String codigo_barra = "";
   Object[] object = new Object[11];
            Productos producto = new Productos(Integer.parseInt(modelo.getValueAt(j, 10).toString()),modelo.getValueAt(j, 0).toString(),modelo.getValueAt(j, 2).toString(),modelo.getValueAt(j, 1).toString()
            ,"EMPTY",Integer.parseInt(modelo.getValueAt(j, 5).toString()),Integer.parseInt(modelo.getValueAt(j, 4).toString()),modelo.getValueAt(j, 8).toString(),Integer.parseInt(modelo.getValueAt(j, 7).toString())
 ,modelo.getValueAt(j, 3).toString(),modelo.getValueAt(j, 9).toString(),Integer.parseInt(modelo.getValueAt(j, 11).toString()),modelo.getValueAt(j, 12).toString(),Integer.parseInt(jTextField2.getText()));
           //falta agregar en tabla color
            
               producto.setImagen("sinfoto");
            //falta para TIPO y para CODIGO
             int selected=jComboBox2.getSelectedIndex();
    
            
            metodosDB metodos= new metodosDB();
            try {
              //agregacion de productos, de ordenes de compra  y detalle de orden compra. 
                if(Integer.parseInt(modelo.getValueAt(j, 10).toString())==0){
                    
                    
                    int id=0;
            correcto2=true;
                   id= metodos.addProductos2(producto);
            producto= metodos.getProductoById(id);
            montototal=montototal+producto.getPrecioCompra()*producto.getCantidadActual();
            producto.setId_producto(producto.getId_producto() );
            mont=producto.getPrecioCompra()*producto.getCantidadActual();
 DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String FechaHoy = dateFormat2.format(date);
        System.out.println("FechaHoy:"+FechaHoy);
     CompraProducto compra = new CompraProducto(idordendecompra,FechaHoy,Integer.parseInt(modelo.getValueAt(j, 7).toString()),producto.getPrecioCompra(),mont,modelo.getValueAt(0, 8).toString(),ordenumero,metodos.getLastId("productos"));
 metodos.addOrdenCompraProducto(compra);
 correcto2=false;
        idordendecompra++; 
                }else{
                   producto= metodos.getProductoById(Integer.parseInt(modelo.getValueAt(j, 10).toString()));
                   
                   producto.setPrecioCompra(Integer.parseInt(modelo.getValueAt(j, 5).toString()));
                   producto.setPrecioVenta(Integer.parseInt(modelo.getValueAt(j, 4).toString()));
                   producto.setProveedor(jComboBox2.getSelectedItem().toString());
                   producto.setCantidadActual(Integer.parseInt(modelo.getValueAt(j, 7).toString())+producto.getCantidadActual());
               /*    Productos producto = new Productos(Integer.parseInt(modelo.getValueAt(j, 9).toString()),modelo.getValueAt(j, 0).toString(),modelo.getValueAt(j, 2).toString(),modelo.getValueAt(j, 1).toString()
            ,"EMPTY",Integer.parseInt(modelo.getValueAt(j, 5).toString()),Integer.parseInt(modelo.getValueAt(j, 4).toString()),modelo.getValueAt(j, 7).toString(),Integer.parseInt(modelo.getValueAt(j, 6).toString())
 ,modelo.getValueAt(j, 3).toString(),modelo.getValueAt(j, 8).toString(),Integer.parseInt(modelo.getValueAt(j, 10).toString()),modelo.getValueAt(j, 11).toString());
           //falta agregar en tabla color
                   */
                         montototal=montototal+producto.getPrecioCompra()*Integer.parseInt(modelo.getValueAt(j, 7).toString());
                   metodos.updateProductos(producto);
                     DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String FechaHoy = dateFormat2.format(date);
        System.out.println("FechaHoy:"+FechaHoy);
   mont=producto.getPrecioCompra()*Integer.parseInt(modelo.getValueAt(j, 7).toString());
     CompraProducto compra = new CompraProducto(idordendecompra,FechaHoy,Integer.parseInt(modelo.getValueAt(j, 7).toString()),producto.getPrecioCompra(),mont,modelo.getValueAt(0, 8).toString(),ordenumero,producto.getId_producto());
 metodos.addOrdenCompraProducto(compra);  
                    
                    
                    
                    
                 idordendecompra++;  correcto2=false;   
                }
              
            } catch (SQLException ex) {
                Logger.getLogger(modificarordenes.class.getName()).log(Level.SEVERE, null, ex);
            }
           
         
                      
                      //validar campos
                      //guardar en la base de datos si es consistente
                         
                      this.dispose(); //dispose para salir de la ventana
        }else{
               
              JOptionPane.showMessageDialog(rootPane,  "NO HA AGREGADO NINGUN PRODUCTO".toUpperCase());
                
                }}     if(correcto2==false)
   { metodosDB metodos2= new metodosDB();
       


        jframe1 a = new jframe1();
            JOptionPane.showMessageDialog(a, "PRODUCTO(S) AGREGADOS CON EXITO");
    }
                
               
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            new Metodos_objetos().testProductos(new metodosDB().getProductos());
        } catch (SQLException ex) {
            Logger.getLogger(modificarordenes.class.getName()).log(Level.SEVERE, null, ex);
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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
            
           jTextField1.requestFocus();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20ActionPerformed

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
            jComboBox1.setEnabled(true);
           // jTextField7.setEnabled(true);
            // jTextField9.setEnabled(true);
            int porciento = 0;// Integer.valueOf(jTextField16.getText().replace("%", "").trim());
            int monto = Integer.valueOf(modelo.getValueAt(linea, 4).toString());
          //  jTextField7.setText((monto * 100 / porciento) + "");
            //  jTextField22.setText(modelo.getValueAt(linea, 4).toString());
            //  jTextField9.setText(modelo.getValueAt(linea, 5).toString());
            //  jTextField10.setText(modelo.getValueAt(linea, 6).toString());
            jComboBox1.setSelectedItem(modelo.getValueAt(linea, 7).toString());
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField22ActionPerformed
        // TODO add your handling code here:safsd
       
    }//GEN-LAST:event_jTextField22ActionPerformed

    private void jTextField24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField24ActionPerformed
this.actualiza();        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField24ActionPerformed

    private void jTextField25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField25ActionPerformed
       double imp=0;
        if(jCheckBox1.isSelected()==true){
     jCheckBox1.setSelected(true);
     jCheckBox1.setEnabled(false); 
         imp=  (double) (Integer.parseInt(jTextField25.getText().toString())*1.31);
                    System.out.println(imp);
           int var2 = (int) Math.ceil(imp);
           jLabel7.setText(Integer.toString(var2));
}else{
                 imp=  (double) (Integer.parseInt(jTextField25.getText().toString())*1.0);
                    System.out.println(imp);
           int var2 = (int) Math.ceil(imp);
            jLabel7.setText(Integer.toString(var2));
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField25ActionPerformed
public void actualiza(){
    
      double imp=0;
        if(jCheckBox1.isSelected()==true){
     jCheckBox1.setSelected(true);
     jCheckBox1.setEnabled(false); 
         imp=  (double) (Integer.parseInt(jTextField25.getText().toString())*1.31);
                    System.out.println(imp);
           int var2 = (int) Math.ceil(imp);
           jLabel7.setText(Integer.toString(var2));
}else{
                 imp=  (double) (Integer.parseInt(jTextField25.getText().toString())*1.0);
                    System.out.println(imp);
           int var2 = (int) Math.ceil(imp);
            jLabel7.setText(Integer.toString(var2));
        }  
        
        
        
          double imp2=0;
        
        if(jCheckBox1.isSelected()==true){
     jCheckBox1.setSelected(true);
     jCheckBox1.setEnabled(false); 
   
               imp2=  (double) (Integer.parseInt(jTextField26.getText().toString())*1.31); 
                  System.out.println(imp2);
           int var3 = (int) Math.ceil(imp2);
           
           jLabel9.setText(Integer.toString(var3));
}else{
               
                imp2=  (double) (Integer.parseInt(jTextField26.getText().toString())*1.0); 
                  System.out.println(imp2);
           int var3 = (int) Math.ceil(imp2);
           
           jLabel9.setText(Integer.toString(var3));
        }     
        
}
    private void jTextField26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField26ActionPerformed

          double imp2=0;
        
             double imp=0;
        if(jCheckBox1.isSelected()==true){
     jCheckBox1.setSelected(true);
     jCheckBox1.setEnabled(false); 
   
               imp2=  (double) (Integer.parseInt(jTextField26.getText().toString())*1.31); 
                  System.out.println(imp2);
           int var3 = (int) Math.ceil(imp2);
           
           jLabel9.setText(Integer.toString(var3));
}else{
               
                imp2=  (double) (Integer.parseInt(jTextField26.getText().toString())*1.0); 
                  System.out.println(imp2);
           int var3 = (int) Math.ceil(imp2);
           
           jLabel9.setText(Integer.toString(var3));
        }     
        
        
        
        
        
        
        
      
        
        
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField26ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
int harina=0;
        if(jCheckBox1.isSelected()){
     harina =1;
}
        
         Date date = jDateChooser2.getDate();
                 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
         FechaEmision = dateFormat.format(jDateChooser2.getCalendar().getTime());  
         jDateChooser2.setEnabled(false);
    if(validaCampos())  
    {int selected=jComboBox2.getSelectedIndex();
    int selected2=jComboBox3.getSelectedIndex();
     int selected3=jComboBox5.getSelectedIndex();
        //DATOS DE LA TABLA
     if(FechaEmision.equals(null)){
                         JOptionPane.showMessageDialog(rootPane,  "Debe Seleccionar Fecha Emisión".toUpperCase());

        
     }else{
         String cod=jTextField1.getText();
              if(cod.equals("")){
                             JOptionPane.showMessageDialog(rootPane,  "Debe ingresar código (Si no posee ingrese 0)".toUpperCase());
              
                       }else{
            
           Object[] object = new Object[14];
        object[0] = jTextField22.getText();
        object[1] = jTextField20.getText();
        object[2] = jTextField23.getText();
        object[4] = Integer.parseInt(jTextField26.getText());
        object[3] = jComboBox1.getSelectedItem().toString();
        object[8] = jComboBox2.getItemAt(selected).toString();
        object[5] = Integer.parseInt(jTextField25.getText());
        object[7] = Integer.parseInt(jTextField24.getText());
        object[9] = jTextField1.getText();
        object[10] = jTextField28.getText();
            object[11] = harina;
                   object[12] = jComboBox3.getItemAt(selected2).toString();
                   object[13] = jComboBox5.getItemAt(selected3).toString();
        jComboBox2.setEditable(true);
        modelo.addRow(object);
            //TERMINO DATOS TABLA
        limpiar();  }
     }
       jLabel17.setText(Integer.toString(this.getMonto()));
    }
  

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField23ActionPerformed

    private void jTextField28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField28ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
  int selected=jComboBox4.getSelectedIndex();        // TODO add your handling code here:
  
  if(selected==0){
      
  }else{
   if(selected==1){
          jTextField22.setText(productos.get(0).getNombre());
        jTextField23.setText(productos.get(0).getMarca()); 
        jTextField20.setText(productos.get(0).getTalla()); 
        jTextField2.setText(Integer.toString(productos.get(0).getCritico()));
        jTextField25.setText(Integer.toString(productos.get(0).getPrecioCompra())); 
        jTextField28.setText(Integer.toString(productos.get(0).getId_producto())); 
        jComboBox1.setSelectedIndex(0); 
       // jTextField27.setText(""); 
        jTextField26.setText(Integer.toString(productos.get(0).getPrecioVenta())); 
        jTextField1.setText(productos.get(0).getCodigo_barra());
             double imp=0;
             double imp2=0;
     jTextField28.setEditable(false); 
     jTextField22.setEditable(false); 
     jTextField23.setEditable(false); 
     jTextField20.setEditable(false); 
     jTextField1.setEditable(false); 
        if(productos.get(0).getHarina()==1){
     jCheckBox1.setSelected(true);
     jCheckBox1.setEnabled(false); 
         imp=  (double) (productos.get(0).getPrecioVenta()*1.31);
                    System.out.println(imp);
           int var2 = (int) Math.ceil(imp);
           jLabel7.setText(Integer.toString(var2));
            imp2=  (double) (productos.get(0).getPrecioVenta()*1.31); 
                  System.out.println(imp2);
           int var3 = (int) Math.ceil(imp2);
           
           jLabel9.setText(Integer.toString(var3));
}else{
                 imp=  (double) (productos.get(0).getPrecioVenta()*1.0);
                    System.out.println(imp);
           int var2 = (int) Math.ceil(imp);
            jLabel7.setText(Integer.toString(var2));
              imp2=  (double) (productos.get(0).getPrecioVenta()*1.0); 
                  System.out.println(imp2);
           int var3 = (int) Math.ceil(imp2);
           
           jLabel9.setText(Integer.toString(var3));
        }
        if(productos.get(0).getTipo().equals("SELECCIONAR ITEM")){
                 jComboBox1.setSelectedIndex(0);  
        }
         if(productos.get(0).getTipo().equals("GATOS")){
                 jComboBox1.setSelectedIndex(1);  
        }
          if(productos.get(0).getTipo().equals("PERROS")){
                 jComboBox1.setSelectedIndex(2);  
        }
           if(productos.get(0).getTipo().equals("AVES Y OTROS")){
                 jComboBox1.setSelectedIndex(3);  
        }
            if(productos.get(0).getTipo().equals("PANADERIA PASTELERIA")){
                 jComboBox1.setSelectedIndex(4);  
        }
                 if(productos.get(0).getTipo().equals("FRUTOS SECOS Y LEGUMBRES")){
                 jComboBox1.setSelectedIndex(5);  
        }
           
            if(productos.get(0).getTipo().equals("CONFITERIA")){
                 jComboBox1.setSelectedIndex(6);  
        }
           if(productos.get(0).getTipo().equals("OTROS")){
                 jComboBox1.setSelectedIndex(7);  
        } 
        if(productos.get(0).getMedida().equals("Falta Medida")){
                 jComboBox3.setSelectedIndex(0);  
        }
         if(productos.get(0).getMedida().equals("Mg")){
                 jComboBox3.setSelectedIndex(1);  
        }
          if(productos.get(0).getMedida().equals("Gr")){
                 jComboBox3.setSelectedIndex(2);  
        }
           if(productos.get(0).getMedida().equals("Kg")){
                 jComboBox3.setSelectedIndex(3);  
        }
            if(productos.get(0).getMedida().equals("Ton")){
                 jComboBox3.setSelectedIndex(4);  
        }
               if(productos.get(0).getMedida().equals("Lata")){
                 jComboBox3.setSelectedIndex(5);  
        }     
                     if(productos.get(0).getMedida().equals("Unidad")){
                 jComboBox3.setSelectedIndex(6);  
        }     if(productos.get(0).getMedida().equals("Sachet")){
                 jComboBox3.setSelectedIndex(7);  
        } if(productos.get(0).getMedida().equals("Lt")){
                 jComboBox3.setSelectedIndex(8);  
        } if(productos.get(0).getMedida().equals("Cc")){
                 jComboBox3.setSelectedIndex(9);  
        } 
       
   }else{
          jTextField22.setText(productos.get(selected-1).getNombre());
        jTextField23.setText(productos.get(selected-1).getMarca()); 
        jTextField20.setText(productos.get(selected-1).getTalla()); 
        jTextField25.setText(Integer.toString(productos.get(selected-1).getPrecioCompra())); 
        jTextField28.setText(Integer.toString(productos.get(selected-1).getId_producto())); 
        jComboBox1.setSelectedIndex(0); 
       // jTextField27.setText(""); 
        jTextField26.setText(Integer.toString(productos.get(selected-1).getPrecioVenta())); 
        jTextField1.setText(productos.get(selected-1).getCodigo_barra());
        
     jTextField28.setEditable(false); 
     jTextField22.setEditable(false); 
     jTextField23.setEditable(false); 
     jTextField20.setEditable(false); 
     jTextField1.setEditable(false); 
        if(productos.get(selected-1).getHarina()==1){
     jCheckBox1.setSelected(true);
     jCheckBox1.setEnabled(false); 
}
        if(productos.get(selected-1).getTipo().equals("SELECCIONAR ITEM")){
                 jComboBox1.setSelectedIndex(0);  
        }
         if(productos.get(selected-1).getTipo().equals("GATOS")){
                 jComboBox1.setSelectedIndex(1);  
        }
          if(productos.get(selected-1).getTipo().equals("PERROS")){
                 jComboBox1.setSelectedIndex(2);  
        }
           if(productos.get(selected-1).getTipo().equals("AVES Y OTROS")){
                 jComboBox1.setSelectedIndex(3);  
        }
            if(productos.get(selected-1).getTipo().equals("PANADERIA PASTELERIA")){
                 jComboBox1.setSelectedIndex(4);  
        }
                 if(productos.get(selected-1).getTipo().equals("FRUTOS SECOS Y LEGUMBRES")){
                 jComboBox1.setSelectedIndex(5);  
        }
           
            if(productos.get(selected-1).getTipo().equals("CONFITERIA")){
                 jComboBox1.setSelectedIndex(6);  
        }
             if(productos.get(0).getTipo().equals("OTROS")){
                 jComboBox1.setSelectedIndex(7);  
        }  
        if(productos.get(selected-1).getMedida().equals("Falta Medida")){
                 jComboBox3.setSelectedIndex(0);  
        }
         if(productos.get(selected-1).getMedida().equals("Mg")){
                 jComboBox3.setSelectedIndex(1);  
        }
          if(productos.get(selected-1).getMedida().equals("Gr")){
                 jComboBox3.setSelectedIndex(2);  
        }
           if(productos.get(selected-1).getMedida().equals("Kg")){
                 jComboBox3.setSelectedIndex(3);  
        }
            if(productos.get(selected-1).getMedida().equals("Ton")){
                 jComboBox3.setSelectedIndex(4);  
        }
               if(productos.get(selected-1).getMedida().equals("Lata")){
                 jComboBox3.setSelectedIndex(5);  
        }     
                     if(productos.get(selected-1).getMedida().equals("Unidad")){
                 jComboBox3.setSelectedIndex(6);  
        }     if(productos.get(i).getMedida().equals("Sachet")){
                 jComboBox3.setSelectedIndex(7);  
        } if(productos.get(selected-1).getMedida().equals("Lt")){
                 jComboBox3.setSelectedIndex(8);  
        } if(productos.get(selected-1).getMedida().equals("Cc")){
                 jComboBox3.setSelectedIndex(9);  
        } 
       
   }
  }
  
  jTextField24.requestFocus();
  
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jTextField27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField27ActionPerformed
jDateChooser2.requestFocus();       // TODO add your handling code here:
    }//GEN-LAST:event_jTextField27ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed


        int linea = jTable1.getSelectedRow();
        System.out.println(linea);
        if (linea != -1) {


            modelo.removeRow(linea);
        } jLabel17.setText(Integer.toString(this.getMonto()));jLabel21.setText(Integer.toString(this.getMontoNeto()));
    }//GEN-LAST:event_jButton8ActionPerformed
  
    private void limpiar(){
         jTextField22.setText("");
        jTextField23.setText(""); 
        jTextField20.setText(""); 
        jTextField25.setText(""); 
        jTextField28.setText("0"); 
        jComboBox1.setSelectedIndex(0); 
        jComboBox3.setSelectedIndex(0); 
       // jTextField27.setText(""); 
        jTextField26.setText(""); 
        jTextField1.setText("");
        jCheckBox1.setSelected(false);
         jTextField24.setText("");
     jTextField28.setEditable(false); 
     jTextField22.setEditable(true); 
     jTextField23.setEditable(true); 
     jTextField20.setEditable(true); 
     jTextField1.setEditable(true);  
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
            java.util.logging.Logger.getLogger(modificarordenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(modificarordenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(modificarordenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(modificarordenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                modificarordenes dialog = null;
                try {
                    try {
                        dialog = new modificarordenes(new javax.swing.JFrame(), true);
                    } catch (ParseException ex) {
                        Logger.getLogger(modificarordenes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(modificarordenes.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
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
        try {
            // Check whether priceField.getText()'s length equals 0
            if(jTextField23.getText().trim().length() == 0) 
                {
                throw new Exception();
                }
            } catch(Exception e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "marca no puede estar vacío".toUpperCase());
                return false;
                }
        
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
         
         if (jComboBox1.getSelectedIndex()==0)
         {
                JOptionPane.showMessageDialog(rootPane,  "Debe seleccionar un tipo".toUpperCase());
                return false;
         }
          //jtexfield 25 = costo
        try {
            // Check whether priceField.getText()'s length equals 0
            if(jTextField25.getText().trim().length() == 0) 
                {
                throw new Exception();
                }
            } catch(Exception e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "costo no puede estar vacío".toUpperCase());
                return false;
                }
        
         try {
            // Check whether priceField.getText()'s length equals 0
            Integer.parseInt(jTextField25.getText().trim());
               
            } catch(NumberFormatException e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "costo debe ser un número".toUpperCase());
                return false;
                }
         
          //jtexfield 26 precio venta
        try {
            // Check whether priceField.getText()'s length equals 0
            if(jTextField26.getText().trim().length() == 0) 
                {
                throw new Exception();
                }
            } catch(Exception e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "precio venta no puede estar vacío".toUpperCase());
                return false;
                }
        
         try {
            // Check whether priceField.getText()'s length equals 0
            Integer.parseInt(jTextField26.getText().trim());
               
            } catch(NumberFormatException e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "precio venta debe ser un número".toUpperCase());
                return false;
                }
         
           //jtexfield 27 = proveedor
        try {
            // Check whether priceField.getText()'s length equals 0
            if(jTextField27.getText().trim().length() == 0) 
                {
                throw new Exception();
                }
            } catch(Exception e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "NUMERO DE DESPACHO VACIO".toUpperCase());
                return false;
                }
        
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
