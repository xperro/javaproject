/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import ObjetosDB.metodosDB;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URI;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class jframeUsuario extends javax.swing.JFrame implements KeyListener {

    int usuario = 0, mnsj;
    String vacio = "NO SE OBTUVO RESULTADO CON LOS PARAMETROS INGRESADOS.";
    String correcto = "OPERACIÓN REALIZADA CON ÉXITO.", error = "HA OCURRIDO EL SIGUIENTE ERROR:\n";
    String no_empleado = "NO SE REGISTRA EMPLEADO CON EL RUT INGRESADO.";
    String no_opcion = "DEBE SELECCIONAR UNA DE LAS OPCIONES DEL SISTEMA.";
    String no_numero = "DEBE INGRESAR UN DATO NUMÉRICO EN EL CAMPO ";
    String num_no_valido = "DEBE INGRESAR UN NÚMERO VÁLIDO EN EL CAMPO ";
    String campo_vacio = "DEBE COMPLETAR EL SIGUIENTE CAMPO:\n";
    String sin_conexion = "NO SE HA PODIDO ESTABLECER LA CONEXIÓN PARA OBTENER LA INFORMACIÓN.";
    String fecha_no_valida = "DEBE INGRESAR EN EL CAMPO FECHA UN DATO VALIDO";
String rut;
    public jframeUsuario() {
        initComponents();
               this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
this.setUndecorated(true);
this.setVisible(true);
        iniciar();
    }

    private void modulosPrivilegios(int privilegio) {
           
       System.out.println("q Ward");
        System.out.println(privilegio);
            if (privilegio==1) {
        
                //    modulo1.setEnabled(true);
                 
                    modulo2.setEnabled(true);
                    
                    modulo3.setEnabled(true);
                    
      //              modulo4.setEnabled(true);
                   
         //           modulo5.setEnabled(true);
                   
                   // modulo6.setEnabled(true);
                   
                    modulo7.setEnabled(true);
                    
                  //  modulo8.setEnabled(true);
                    
                    modulo14.setEnabled(true);
                   
                    modulo15.setEnabled(true);
                   
              //     jMenuItem3.setEnabled(true);
                    
                
                   
                    modulo18.setEnabled(true);
                    
                    modulo19.setEnabled(true);
                       solicitud.setEnabled(true);
     //   empleado.setEnabled(true);
        comprobante.setEnabled(true);
        contabilidad.setEnabled(true);
        gestion.setEnabled(true);
         jMenu1.setEnabled(true);
                  administrador.setEnabled(true);
            }
 }
    private void modulosDefault() {
     //modulo1.setEnabled(false);
     modulo2.setEnabled(false);
   modulo3.setEnabled(false);
      //  modulo4.setEnabled(false);
       // modulo5.setEnabled(false);
      ;
        modulo7.setEnabled(false);
       
        modulo14.setEnabled(false);
        modulo15.setEnabled(true);
        
//                   jMenuItem3.setEnabled(false);
        modulo18.setEnabled(false);
        modulo19.setEnabled(false);
      //  modulo20.setEnabled(false);
        administrador.setEnabled(false);
        solicitud.setEnabled(false);
       // empleado.setEnabled(false);
        comprobante.setEnabled(false);
        contabilidad.setEnabled(true);
        gestion.setEnabled(false);
         jMenu1.setEnabled(true);
    }

    public jframeUsuario(String rut_usuario) throws SQLException {
        initComponents();iniciar();
        usuario = Integer.parseInt(rut_usuario);
        metodosDB funciones = new metodosDB();
        int privilegio = funciones.cargaUsuarioByRut(usuario);
        modulosDefault();
          this.rut=rut_usuario;
        modulosPrivilegios(privilegio);
      //  modulosPrivilegios(a);
        

    }

    public void iniciar() {
        jpanel2 pane = new jpanel2();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 

        this.add(pane, BorderLayout.CENTER);
        this.pack();
        this.addKeyListener(this);
        jMenuBar1.addKeyListener(this);


    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        solicitud = new javax.swing.JMenu();
        modulo2 = new javax.swing.JMenuItem();
        modulo3 = new javax.swing.JMenuItem();
        modulo5 = new javax.swing.JMenuItem();
        modulo4 = new javax.swing.JMenuItem();
        comprobante = new javax.swing.JMenu();
        modulo7 = new javax.swing.JMenuItem();
        contabilidad = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        modulo15 = new javax.swing.JMenuItem();
        modulo6 = new javax.swing.JMenuItem();
        modulo8 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        modulo9 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        gestion = new javax.swing.JMenu();
        modulo14 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        gestion1 = new javax.swing.JMenu();
        modulo16 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        administrador = new javax.swing.JMenu();
        modulo18 = new javax.swing.JMenuItem();
        modulo19 = new javax.swing.JMenuItem();
        modulo10 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bienvenido");
        setMaximumSize(new java.awt.Dimension(1280, 768));
        setPreferredSize(new java.awt.Dimension(1280, 768));
        setResizable(false);

        jButton2.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 102));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/stock_lock.png"))); // NOI18N
        jButton2.setText("Cerrar Sesión");
        jButton2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton2.setSelected(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Microsoft YaHei", 1, 36)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 153, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/newventa.png"))); // NOI18N
        jButton4.setText("Vender Productos");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        solicitud.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/emblem-sales.png"))); // NOI18N
        solicitud.setText("Productos");
        solicitud.setIconTextGap(1);
        solicitud.setMaximumSize(new java.awt.Dimension(100, 32767));
        solicitud.setPreferredSize(new java.awt.Dimension(100, 29));

        modulo2.setText("Modificar Producto");
        modulo2.setToolTipText("");
        modulo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo2ActionPerformed(evt);
            }
        });
        solicitud.add(modulo2);

        modulo3.setText("Eliminar Producto");
        modulo3.setToolTipText("");
        modulo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo3ActionPerformed(evt);
            }
        });
        solicitud.add(modulo3);

        modulo5.setText("Actualizar Datos Masivo");
        modulo5.setToolTipText("");
        modulo5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo5ActionPerformed(evt);
            }
        });
        solicitud.add(modulo5);

        modulo4.setText("Eliminar Guia/Factura");
        modulo4.setToolTipText("");
        modulo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo4ActionPerformed(evt);
            }
        });
        solicitud.add(modulo4);

        jMenuBar1.add(solicitud);

        comprobante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/chart_curve.png"))); // NOI18N
        comprobante.setText("Inventario");
        comprobante.setMaximumSize(new java.awt.Dimension(110, 32767));
        comprobante.setPreferredSize(new java.awt.Dimension(100, 21));

        modulo7.setText("Inventario General");
        modulo7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modulo7MouseClicked(evt);
            }
        });
        modulo7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo7ActionPerformed(evt);
            }
        });
        comprobante.add(modulo7);

        jMenuBar1.add(comprobante);

        contabilidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/coins.png"))); // NOI18N
        contabilidad.setText("Ventas/Compras");
        contabilidad.setMaximumSize(new java.awt.Dimension(120, 40767));

        jMenuItem1.setText("Cuadrar Caja");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        contabilidad.add(jMenuItem1);

        modulo15.setText("Vender Productos");
        modulo15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo15ActionPerformed(evt);
            }
        });
        contabilidad.add(modulo15);

        modulo6.setText("Recibir Factura/Guía");
        modulo6.setToolTipText("");
        modulo6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo6ActionPerformed(evt);
            }
        });
        contabilidad.add(modulo6);

        modulo8.setText("Transformar Producto");
        modulo8.setToolTipText("");
        modulo8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo8ActionPerformed(evt);
            }
        });
        contabilidad.add(modulo8);

        jMenuBar1.add(contabilidad);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/chart.png"))); // NOI18N
        jMenu1.setText("Estadísticas");
        jMenu1.setMaximumSize(new java.awt.Dimension(105, 32767));

        jMenuItem5.setText("Ver Boletas Diarias");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem9.setText("Ver Compras");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        modulo9.setText("Modificar Guias");
        modulo9.setToolTipText("");
        modulo9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo9ActionPerformed(evt);
            }
        });
        jMenu1.add(modulo9);

        jMenuItem6.setText("Libro Diario Compras");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem10.setText("Libro Diario Ventas");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);

        jMenuItem11.setText("Estadísticas Productos");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenuBar1.add(jMenu1);

        gestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/basket.png"))); // NOI18N
        gestion.setText("Promociones");
        gestion.setMaximumSize(new java.awt.Dimension(125, 32767));

        modulo14.setText("Nueva Promocion");
        modulo14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo14ActionPerformed(evt);
            }
        });
        gestion.add(modulo14);

        jMenuItem7.setText("Eliminar Promocion");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        gestion.add(jMenuItem7);

        jMenuBar1.add(gestion);

        gestion1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/basket.png"))); // NOI18N
        gestion1.setText("Cliente/Proveedor");
        gestion1.setMaximumSize(new java.awt.Dimension(125, 32767));

        modulo16.setText("Nuevo Clientes/Proovedor");
        modulo16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo16ActionPerformed(evt);
            }
        });
        gestion1.add(modulo16);

        jMenuItem8.setText("Modificar Cliente/Proveedor");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        gestion1.add(jMenuItem8);

        jMenuBar1.add(gestion1);

        administrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/user_gray.png"))); // NOI18N
        administrador.setText("Administrador");
        administrador.setActionCommand("Administrador     ");

        modulo18.setText("Crear usuario");
        modulo18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo18ActionPerformed(evt);
            }
        });
        administrador.add(modulo18);

        modulo19.setText("Modificar Usuario");
        modulo19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo19ActionPerformed(evt);
            }
        });
        administrador.add(modulo19);

        modulo10.setText("Eliminar Vale");
        modulo10.setToolTipText("");
        modulo10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo10ActionPerformed(evt);
            }
        });
        administrador.add(modulo10);

        jMenuBar1.add(administrador);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(318, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modulo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo2ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
       buscar_producto p = null;
        try {
            p = new buscar_producto(this, true, usuario);
        } catch (SQLException ex) {
            Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
       p.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
      p.setVisible(true);

    }//GEN-LAST:event_modulo2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

            Command comando = new Command();
    comando.executeLocal();
    comando.executeExterno();
        SyncExt sincronizar = new SyncExt();
      sincronizar.cargar();
        if(java.awt.Desktop.isDesktopSupported()){
 try{
      Desktop dk = Desktop.getDesktop();
      dk.browse(new URI("http://200.73.116.212/~fullerto/bdsync.php"));
 }catch(Exception e){
     System.out.println("Error al abrir URL: "+e.getMessage());
 }
}      
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        jframe1 login = new jframe1();
        dispose();
        this.setCursor(Cursor.getDefaultCursor());
        
        login.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void modulo7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo7ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            frameInventarioActual frameInventario = new frameInventarioActual();
      frameInventario.setLocationRelativeTo(null);
            frameInventario.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_modulo7ActionPerformed

    private void modulo18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo18ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
      frameNuevoVendedor a = new frameNuevoVendedor();
     a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
        a.setVisible(true);

    }//GEN-LAST:event_modulo18ActionPerformed

    private void modulo19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo19ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        frameModificarVendedores a = new frameModificarVendedores();
        a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
        a.setVisible(true);
        this.dispose();
       
      

    }//GEN-LAST:event_modulo19ActionPerformed

    private void modulo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo3ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     buscar_eliminar_producto a = null;
        try {
            a = new buscar_eliminar_producto(usuario);
        } catch (SQLException ex) {
            Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
      a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
     a.setVisible(true);
    }//GEN-LAST:event_modulo3ActionPerformed

    private void modulo15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo15ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     //   dialog_buscar_arancel a = new dialog_buscar_arancel(this, usuario);
     //   a.setLocationRelativeTo(null);
                   this.setCursor(Cursor.getDefaultCursor());
        Framenewventa frame;
        try {
            frame = new Framenewventa(this.rut);
            frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
     //   a.setVisible(true);

    }//GEN-LAST:event_modulo15ActionPerformed

    private void modulo14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo14ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        frameNuevaPromo frame = null;
        try {
            frame = new frameNuevaPromo();
        } catch (SQLException ex) {
            Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        frame.setVisible(true);
       // dialog_reportes2 a = new dialog_reportes2(usuario);
      frame.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
      //  a.setVisible(true);

    }//GEN-LAST:event_modulo14ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        libro_diario frame = null;
        try {
                frame = new libro_diario(this,false);
              
            } catch (SQLException ex) 
            {
                Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
          frame.setLocationRelativeTo(null);
            frame.setVisible(true);
       this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        eliminar_promo prom = null;
        try {
            prom = new eliminar_promo();
        } catch (SQLException ex) {
            Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        prom.setLocationRelativeTo(null);
        prom.setVisible(true);
        setCursor(Cursor.getDefaultCursor());
       // a.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void modulo7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modulo7MouseClicked
      
    }//GEN-LAST:event_modulo7MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        frameCuadrarCaja frame = new frameCuadrarCaja();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
   // TODO add your handling code here:
        Framenewventa frame;
        this.dispose();
        try {
            frame = new Framenewventa(this.rut);   frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Cliente registrado con éxito");
            Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void modulo16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo16ActionPerformed
 frameNewClienteB frame;
      
            frame = new frameNewClienteB();
                    frame.setVisible(true);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
                //         // TODO add your handling code here:
    }//GEN-LAST:event_modulo16ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
     buscar_clientes frame = null;
        try {
            frame = new buscar_clientes(this.usuario);
        } catch (SQLException ex) {
            Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
                    frame.setVisible(true);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);   // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        libro_diario_detalle_compras frame = null;
        try {
                frame = new libro_diario_detalle_compras(this,false);
              
            } catch (SQLException ex) 
            {
                Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
          frame.setLocationRelativeTo(null);
            frame.setVisible(true);
       this.setCursor(Cursor.getDefaultCursor());        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void modulo5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo5ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     modificar_productos_all a = null;
        try {
            a = new modificar_productos_all(usuario);
        } catch (SQLException ex) {
            Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
      a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
     a.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_modulo5ActionPerformed

    private void modulo6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo6ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     ordenesmasivo a = null;
        try {
            a = new ordenesmasivo(this,true);
        } catch (SQLException ex) {
            Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
      a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
     a.setVisible(true);       // TODO add your handling code here:
    }//GEN-LAST:event_modulo6ActionPerformed

    private void modulo8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo8ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     Transformar a = null;
        try {
            a = new Transformar(this,true);
        } catch (SQLException ex) {
            Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
      a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
     a.setVisible(true);   // TODO add your handling code here:
    }//GEN-LAST:event_modulo8ActionPerformed

    private void modulo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo4ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     buscar_eliminar_guia a = null;
        try {
            a = new buscar_eliminar_guia(usuario);
        } catch (SQLException ex) {
            Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
      a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
     a.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_modulo4ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
       this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
       libro_diario_compras a = null;
        try {
            a = new libro_diario_compras(this,this.usuario);
        } catch (SQLException ex) {
            Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
      a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
     a.setVisible(true);   // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void modulo9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo9ActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
       modificar_ordencompra_all a = null;
        try {
            a = new modificar_ordencompra_all(this.usuario);
        } catch (SQLException ex) {
            Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
      a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
     a.setVisible(true);     // TODO add your handling code here:
    }//GEN-LAST:event_modulo9ActionPerformed

    private void modulo10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo10ActionPerformed
      this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
       eliminar_boleta a = null;
        try {
            a = new eliminar_boleta(this,this.usuario);
        } catch (SQLException ex) {
            Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
      a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
     a.setVisible(true);    // TODO add your handling code here:
    }//GEN-LAST:event_modulo10ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
       this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
       libro_diario_detalle a = null;
        try {
            a = new libro_diario_detalle(this,this.usuario);
        } catch (SQLException ex) {
            Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
      a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
     a.setVisible(true);     // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
       libro_estadistica_productos a = null;
        try {
            a = new libro_estadistica_productos(this,this.usuario);
        } catch (SQLException ex) {
            Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
      a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
     a.setVisible(true);   // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem11ActionPerformed

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
            java.util.logging.Logger.getLogger(jframeUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jframeUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jframeUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jframeUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new jframeUsuario().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu administrador;
    private javax.swing.JMenu comprobante;
    private javax.swing.JMenu contabilidad;
    private javax.swing.JMenu gestion;
    private javax.swing.JMenu gestion1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem modulo10;
    private javax.swing.JMenuItem modulo14;
    private javax.swing.JMenuItem modulo15;
    private javax.swing.JMenuItem modulo16;
    private javax.swing.JMenuItem modulo18;
    private javax.swing.JMenuItem modulo19;
    private javax.swing.JMenuItem modulo2;
    private javax.swing.JMenuItem modulo3;
    private javax.swing.JMenuItem modulo4;
    private javax.swing.JMenuItem modulo5;
    private javax.swing.JMenuItem modulo6;
    private javax.swing.JMenuItem modulo7;
    private javax.swing.JMenuItem modulo8;
    private javax.swing.JMenuItem modulo9;
    private javax.swing.JMenu solicitud;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 18) {
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private static String getModulos(java.lang.String rut) {
     //   metodos.Servicio_Service service = new metodos.Servicio_Service();
       // metodos.Servicio port = service.getServicioPort();
     //   return port.getModulos(rut);
        return null;
    }
}
