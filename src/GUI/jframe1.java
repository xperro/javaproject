package GUI;

//import Clases.funciones;
import Clases.DB_connection;
import ObjetosDB.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

public class jframe1 extends javax.swing.JFrame implements ActionListener {
    ArrayList<Usuarios> usuariosCargados = new ArrayList<Usuarios>();
    public jframe1() {
        metodosDB cargaUsuarios = new metodosDB();
        try {
            usuariosCargados = cargaUsuarios.cargaUsuariosDB();
            } catch (SQLException ex) 
            {            
                JOptionPane.showMessageDialog(rootPane,"ERROR AL CARGAR USUARIOS DESDE LA BASE DE DATOS");
            }
        initComponents();
        centrarVentana();
        jpanel1 panel = new jpanel1();
        this.add(panel, BorderLayout.CENTER);
        this.pack();
        jTextField1.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e){

                    if(verificar_usuario())
                            {
                                jframeUsuario as = null;
                        try {
                            as = new jframeUsuario(jTextField1.getText());
                        } catch (SQLException ex) {
                            Logger.getLogger(jframe1.class.getName()).log(Level.SEVERE, null, ex);
                        }
                                as.setVisible(true);
                                dispose();
                            }
                            else
                            {
                              JOptionPane.showMessageDialog(rootPane,  "login incorrecto".toUpperCase());

                            }
                }});
        jPasswordField1.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent e){

                            if(verificar_usuario())
                            {  
                                jframeUsuario as = null;
                                try {
                                    as = new jframeUsuario(jTextField1.getText());
                                } catch (SQLException ex) {
                                    Logger.getLogger(jframe1.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                as.setVisible(true);
                                dispose();
                            }
                            else
                            {
                              JOptionPane.showMessageDialog(rootPane,  "login incorrecto".toUpperCase());

                            }
                }});
   
    }

    


    
    public void centrarVentana() {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        // Se obtienen las dimensiones en pixels de la ventana.
        Dimension ventana = getSize();
        // Una cuenta para situar la ventana en el centro de la pantalla.
        setLocation((pantalla.width - ventana.width) / 2,
                (pantalla.height - ventana.height) / 2);
    }

    private boolean verificar_usuario() {
        boolean login = false;
        if(verificaCampos())
        {
            String user = jTextField1.getText();
            String password = new String(jPasswordField1.getPassword());
            for(Usuarios u : usuariosCargados)
            {
                if(Integer.toString(u.getIdUsuario()).equals(user))
                    if(u.getPassword().equals(password))
                        login = true;
            }
        }
        return login;
    }
    public boolean verificaCampos()
    {
        try {
            // Check whether priceField.getText()'s length equals 0
            if(jTextField1.getText().trim().length() == 0) 
                {
                throw new Exception();
                }
            } catch(Exception e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "Debe ingresar nombre de usuario".toUpperCase());
                return false;
                }
        try {
            // Check whether priceField.getText()'s length equals 0
            if((new String(jPasswordField1.getPassword())).trim().length() == 0) 
                {
                throw new Exception();
                }
            } catch(Exception e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "Debe ingresar Contraseña".toUpperCase());
                return false;
                }
        return true;
    }
    public void siguiente() {
        
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ALVAREZ SOFTWARE");
        setPreferredSize(new java.awt.Dimension(550, 470));
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Usuario");
        jLabel2.setAlignmentX(0.5F);

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Contraseña");
        jLabel3.setAlignmentX(0.5F);

        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jPasswordField1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("DejaVu Sans", 1, 10)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/stock_keyring.png"))); // NOI18N
        jButton1.setText("ENTRAR");
        jButton1.setAlignmentX(0.5F);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("DejaVu Sans", 1, 20)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 102));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/gnome-logout.png"))); // NOI18N
        jButton2.setText("SALIR");
        jButton2.setAlignmentX(0.5F);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("IDP SOFTWARE 2016");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 0, 51));
        jLabel6.setAlignmentX(0.5F);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(jTextField1)
                            .addComponent(jPasswordField1)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(149, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(44, 44, 44))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel4)
                .addGap(137, 137, 137)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(46, 46, 46))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if(verificar_usuario())
        {
            jframeUsuario as = null;
            try {
                as = new jframeUsuario(jTextField1.getText());
            } catch (SQLException ex) {
                Logger.getLogger(jframe1.class.getName()).log(Level.SEVERE, null, ex);
            }
              as.setLocationRelativeTo(null);
            as.setVisible(true);
            
            this.dispose();
        }
        else
        {
          JOptionPane.showMessageDialog(rootPane,  "usuario y/o contraseña incorrectos".toUpperCase());

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        System.exit(0);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    public static void main(String args[]) {



        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            // for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            //   if ("Nimbus".equals(info.getName())) {
            //     javax.swing.UIManager.setLookAndFeel(info.getClassName());
            //   break;
            //}
            //}
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jframe1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new jframe1().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    private String validarUsuario(java.lang.String login, java.lang.String pass) 
    {
      
        /*Retornos de esta funcion :
        "0" -> OK
        "-1" -> contraseña incorrecta
        "-2" -> login incorrecto */
        for (Usuarios usuariosCargado : usuariosCargados) 
        {
            if (usuariosCargado.getLogin().equals(login)) 
            {
                System.out.println("Login Encontrado!"+login); //Delete
                System.out.println("Buscando Password: "+pass);//Delete
                if (usuariosCargado.getPassword().equals(pass)) {
                    System.out.println("Password Encontrado"+pass); //Delete
                   return "0";
                } else {
                    return "0";
                }
            }   
        }
        return "0";
    }
    
    
}
