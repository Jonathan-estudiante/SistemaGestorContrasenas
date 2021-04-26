package com.istloja.vistas;

import com.istloja.controlador.Logindb;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    private final Logindb controladorLogin;

    public Login() {
        super("SISTEMA DE GESTIÓN DE CONTRASEÑAS");
        controladorLogin = new Logindb();

        initComponents();
        jtxt_contraseña.setEchoChar('*');

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        PanelLogin = new javax.swing.JPanel();
        jtxt_usuario = new javax.swing.JTextField();
        jtxt_contraseña = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_mostrar = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        btn_entrar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelLogin.setBackground(new java.awt.Color(102, 204, 255));

        jtxt_usuario.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jtxt_usuario.setBorder(null);

        jtxt_contraseña.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jtxt_contraseña.setBorder(null);
        jtxt_contraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxt_contraseñaKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Iniciar Sesión");

        jLabel2.setBackground(new java.awt.Color(150, 232, 240));
        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText(" Usuario");
        jLabel2.setOpaque(true);

        jLabel3.setBackground(new java.awt.Color(150, 232, 240));
        jLabel3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText(" Contraseña");
        jLabel3.setOpaque(true);

        txt_mostrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txt_mostrar.setText("Mostrar contraseña");
        txt_mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_mostrarActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/usuario (1).png"))); // NOI18N

        btn_entrar.setBackground(new java.awt.Color(102, 204, 255));
        btn_entrar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btn_entrar.setForeground(new java.awt.Color(255, 255, 255));
        btn_entrar.setText("Entrar");
        btn_entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_entrarActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(102, 204, 255));
        jButton2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Registrarse");

        javax.swing.GroupLayout PanelLoginLayout = new javax.swing.GroupLayout(PanelLogin);
        PanelLogin.setLayout(PanelLoginLayout);
        PanelLoginLayout.setHorizontalGroup(
            PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLoginLayout.createSequentialGroup()
                .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLoginLayout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addComponent(jLabel4)
                        .addGap(102, 102, 102))
                    .addGroup(PanelLoginLayout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLoginLayout.createSequentialGroup()
                                .addComponent(btn_entrar)
                                .addGap(48, 48, 48)
                                .addComponent(jButton2)
                                .addGap(58, 58, 58))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLoginLayout.createSequentialGroup()
                                .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jtxt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxt_contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_mostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(180, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLoginLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(229, 229, 229))
        );
        PanelLoginLayout.setVerticalGroup(
            PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLoginLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxt_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(27, 27, 27)
                .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxt_contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_mostrar)
                .addGap(50, 50, 50)
                .addGroup(PanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_entrar)
                    .addComponent(jButton2))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_entrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_entrarActionPerformed
        // TODO add your handling code here:
        String user = jtxt_usuario.getText();
        String password = jtxt_contraseña.getText();
        if (user.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Por favor, ingrese su usuario");
        }
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Por favor, ingrese su contraseña");
        } else {
            controladorLogin.obtenerUsuario(user, password);
            Principal principal = new Principal();
            principal.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btn_entrarActionPerformed

    private void txt_mostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_mostrarActionPerformed
        // TODO add your handling code here:
        if (txt_mostrar.isSelected()) {
            jtxt_contraseña.setEchoChar((char) 0);
        } else {
            jtxt_contraseña.setEchoChar('*');
        }

    }//GEN-LAST:event_txt_mostrarActionPerformed

    private void jtxt_contraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_contraseñaKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_SPACE) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep(); //sonido de no aceptar más caracteres.
        }
    }//GEN-LAST:event_jtxt_contraseñaKeyTyped

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelLogin;
    private javax.swing.JButton btn_entrar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jtxt_contraseña;
    private javax.swing.JTextField jtxt_usuario;
    private javax.swing.JCheckBox txt_mostrar;
    // End of variables declaration//GEN-END:variables
}
