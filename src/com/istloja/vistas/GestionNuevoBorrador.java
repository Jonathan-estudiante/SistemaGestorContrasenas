package com.istloja.vistas;

import com.istloja.modelo.Borrador;
import com.istloja.Utilidades;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GestionNuevoBorrador {

    private JPasswordField jtxt_contraseña;
    private JPasswordField jtxt_repetirContraseña;
    private JComboBox jcb_tipoContraseña;
    private JTextField nombre_SitioApp;
    private JTextField jtxt_propietario;
    private Utilidades utilidades;
    private JFrame frameGestionContable;

    public GestionNuevoBorrador(JPasswordField jtxt_contraseña, JPasswordField jtxt_repetirContraseña, JComboBox jcb_tipoContraseña, JTextField nombre_SitioApp, JTextField jtxt_propietario, Utilidades utilidades, JFrame frameGestionContable) {
        this.jtxt_contraseña = jtxt_contraseña;
        this.jtxt_repetirContraseña = jtxt_repetirContraseña;
        this.jcb_tipoContraseña = jcb_tipoContraseña;
        this.nombre_SitioApp = nombre_SitioApp;
        this.jtxt_propietario = jtxt_propietario;
        this.utilidades = utilidades;
        this.frameGestionContable = frameGestionContable;
    }

    public JPasswordField getJtxt_contraseña() {
        return jtxt_contraseña;
    }

    public void setJtxt_contraseña(JPasswordField jtxt_contraseña) {
        this.jtxt_contraseña = jtxt_contraseña;
    }

    public JPasswordField getJtxt_repetirContraseña() {
        return jtxt_repetirContraseña;
    }

    public void setJtxt_repetirContraseña(JPasswordField jtxt_repetirContraseña) {
        this.jtxt_repetirContraseña = jtxt_repetirContraseña;
    }

    public JComboBox getJcb_tipoContraseña() {
        return jcb_tipoContraseña;
    }

    public void setJcb_tipoContraseña(JComboBox jcb_tipoContraseña) {
        this.jcb_tipoContraseña = jcb_tipoContraseña;
    }

    public JTextField getNombre_SitioApp() {
        return nombre_SitioApp;
    }

    public void setNombre_SitioApp(JTextField nombre_SitioApp) {
        this.nombre_SitioApp = nombre_SitioApp;
    }

    public JTextField getJtxt_propietario() {
        return jtxt_propietario;
    }

    public void setJtxt_propietario(JTextField jtxt_propietario) {
        this.jtxt_propietario = jtxt_propietario;
    }

    public Utilidades getUtilidades() {
        return utilidades;
    }

    public void setUtilidades(Utilidades utilidades) {
        this.utilidades = utilidades;
    }

    public JFrame getFrameGestionContable() {
        return frameGestionContable;
    }

    public void setFrameGestionContable(JFrame frameGestionContable) {
        this.frameGestionContable = frameGestionContable;
    }

    public void limpiar() {
        jtxt_contraseña.setText("");
        jtxt_repetirContraseña.setText("");
        jcb_tipoContraseña.setSelectedIndex(0);
        nombre_SitioApp.setText("");
        jtxt_propietario.setText("");
        jtxt_contraseña.requestFocus();

    }

    public Borrador guardarEditarContraseña(boolean isEditar) {
        if (jtxt_contraseña.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "Coloque una contraseña para poder continuar.", "ERROR", JOptionPane.ERROR_MESSAGE);
            jtxt_contraseña.requestFocus();
            return null;
        }
        if (jtxt_repetirContraseña.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "Por favor para continuar repita su contraseña.", "ERROR", JOptionPane.ERROR_MESSAGE);
            jtxt_repetirContraseña.requestFocus();
            return null;
        }
        if (nombre_SitioApp.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "Colocar el nombre del sitio web o aplicación a la que pertenece su contraseña.", "ERROR", JOptionPane.ERROR_MESSAGE);
            nombre_SitioApp.requestFocus();
            return null;
        }
        if (jtxt_propietario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frameGestionContable, "Colocar el nombre del propietario de la contraseña.", "ERROR", JOptionPane.ERROR_MESSAGE);
            nombre_SitioApp.requestFocus();
            return null;
        }

        Borrador borrador = new Borrador();
        borrador.setContraseña(utilidades.encriptar(jtxt_contraseña.getText()));
        borrador.setTipoContraseña(String.valueOf(jcb_tipoContraseña.getSelectedItem().toString()));
        borrador.setNombreSitioApp(nombre_SitioApp.getText());
        borrador.setPropietario(jtxt_propietario.getText());

        if (isEditar) {
            borrador.setFechaActualizacion(new Date());
        } else {
            borrador.setFechaRegistro(new Date());
        }
        return borrador;
    }
}
