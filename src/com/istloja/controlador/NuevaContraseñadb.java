package com.istloja.controlador;

import ConexionBase.ConexionBaseDatos;
import com.istloja.modelo.NuevaContraseña;
import com.istloja.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class NuevaContraseñadb {

    PreparedStatement ps = null;
    //Interfaz de acceso a la base de datos
    Statement stm = null;
    //Conexion con la base de datos.
    Connection con = null;
    //Sentencia de JDBC para obtener valores de la base de datos.
    ResultSet rs = null;
    Utilidades utilidades;

    public NuevaContraseñadb() {
        utilidades = new Utilidades();
    }

    //Método para insertar, crear o guardar una contraseña**************************************************************************
    public boolean crearContraseña(NuevaContraseña nueva) {
        boolean registrar = false;
        String sql = "INSERT INTO `basegestorcontraseñas`.`nueva_contrasena` (`contraseña`, `tipo_contrasena`, `nombre_sitio_web_o_app`, `fecha_registro`, `propietario`) VALUES ('"
                + nueva.getContraseña() + "', '"
                + nueva.getTipoContraseña() + "', '"
                + nueva.getNombreSitioApp() + "', '"
                + utilidades.formatoDateTime(nueva.getFechaRegistro())+ "', '"
                + nueva.getPropietario()+"');";
        try {
            ConexionBaseDatos conexion = new ConexionBaseDatos();
            con = conexion.conexionBase();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al crear una contraseña " + e.getMessage());
        }
        return registrar;
    }
    //Método para quitar o eliminar una contraseña

    public boolean eliminarPersona(NuevaContraseña contraseña) {
        boolean registrar = false;

        String sql = "DELETE FROM `basegestorcontraseñas`.`nueva_contrasena` WHERE id_contrasena =" + String.valueOf(contraseña.getIdContraseña());
        try {
            ConexionBaseDatos conexion = new ConexionBaseDatos();
            con = conexion.conexionBase();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar una contraseña" + e.getMessage());
        }
        return registrar;
    }
    //Método para actualizar, modificar o editar una contraseña***************************************************************

    public boolean actualizarContraseña(NuevaContraseña nc) {
        boolean registrar = false;

        String sql = "UPDATE `basegestorcontraseñas`.`nueva_contrasena` SET `contraseña` = '" + nc.getContraseña()
                + "', tipo_contrasena = '" + nc.getTipoContraseña()
                + "', nombre_sitio_web_o_app = '" + nc.getNombreSitioApp()
                + "', fecha_registro = '" + utilidades.formatoDateTime(nc.getFechaRegistro())
                + "', fecha_actualizacion = '" + utilidades.formatoDateTime(nc.getFechaActualizacion())
                + "', propietario = '" + nc.getPropietario()
                + "'WHERE id_contrasena =" + String.valueOf(nc.getIdContraseña());
        try {
            ConexionBaseDatos conexion = new ConexionBaseDatos();
            con = conexion.conexionBase();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar una contraseña" + e.getMessage());
            System.out.println(e);
        }
        return registrar;
    }

    //Método traer a todas las contraseña******************************************************************************************
    public List<NuevaContraseña> obtenerContraseñasGuardadas() {

        String sql = "SELECT * FROM basegestorcontraseñas.nueva_contrasena;";

        List<NuevaContraseña> listaContraseñas = new ArrayList<>();

        try {
            con = new ConexionBaseDatos().conexionBase();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                NuevaContraseña nc = new NuevaContraseña();
                nc.setIdContraseña(rs.getInt(1));
                nc.setContraseña(rs.getString(2));
                nc.setTipoContraseña(rs.getString(3));
                nc.setNombreSitioApp(rs.getString(4));
                nc.setFechaRegistro(rs.getDate(5));
                nc.setFechaActualizacion(rs.getDate(6));
                nc.setPropietario(rs.getString(7));

                listaContraseñas.add(nc);
            }
            stm.close();
            rs.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error al mostrar las contraseñas " + e.getMessage());

        }
        return listaContraseñas;
    }

    public List<NuevaContraseña> getContraseñaNombreSitioApp(String sitioApp) {

        List<NuevaContraseña> contraseñaEncontrada = new ArrayList<>();
        String sql = "SELECT * FROM basegestorcontraseñas.nueva_contrasena WHERE nombre_sitio_web_o_app LIKE \"%" + sitioApp + "%\"";
        try {
            con = new ConexionBaseDatos().conexionBase();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                NuevaContraseña nc = new NuevaContraseña();
                nc.setIdContraseña(rs.getInt(1));
                nc.setContraseña(rs.getString(2));
                nc.setTipoContraseña(rs.getString(3));
                nc.setNombreSitioApp(rs.getString(4));
                nc.setFechaRegistro(rs.getDate(5));
                nc.setFechaActualizacion(rs.getDate(6));
                nc.setPropietario(rs.getString(7));
                contraseñaEncontrada.add(nc);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar por nombre:" + e.getMessage());
        }
        return contraseñaEncontrada;
    }

    public List<NuevaContraseña> mostrarSitioWeb(String tipo) {

        List<NuevaContraseña> contraseñaEncontrada = new ArrayList<>();
        String sql = "SELECT * FROM basegestorcontraseñas.nueva_contrasena WHERE tipo_contrasena LIKE \"%" + tipo + "%\"";
        try {
            con = new ConexionBaseDatos().conexionBase();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                NuevaContraseña nc = new NuevaContraseña();
                nc.setIdContraseña(rs.getInt(1));
                nc.setContraseña(rs.getString(2));
                nc.setTipoContraseña(rs.getString(3));
                nc.setNombreSitioApp(rs.getString(4));
                nc.setFechaRegistro(rs.getDate(5));
                nc.setFechaActualizacion(rs.getDate(6));
                nc.setPropietario(rs.getString(7));
                contraseñaEncontrada.add(nc);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar por tipo:" + e.getMessage());
        }
        return contraseñaEncontrada;
    }

    public List<NuevaContraseña> mostrarFecha_Reciente() {

        List<NuevaContraseña> contraseñaEncontrada = new ArrayList<>();
        String sql = "SELECT * FROM basegestorcontraseñas.nueva_contrasena ORDER BY fecha_registro DESC;";
        try {
            con = new ConexionBaseDatos().conexionBase();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                NuevaContraseña nc = new NuevaContraseña();
                nc.setIdContraseña(rs.getInt(1));
                nc.setContraseña(rs.getString(2));
                nc.setTipoContraseña(rs.getString(3));
                nc.setNombreSitioApp(rs.getString(4));
                nc.setFechaRegistro(rs.getDate(5));
                nc.setFechaActualizacion(rs.getDate(6));
                nc.setPropietario(rs.getString(7));
                contraseñaEncontrada.add(nc);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar por fecha:" + e.getMessage());
        }
        return contraseñaEncontrada;
    }

    public List<NuevaContraseña> mostrarFecha_Pasada() {

        List<NuevaContraseña> contraseñaEncontrada = new ArrayList<>();
        String sql = "SELECT * FROM basegestorcontraseñas.nueva_contrasena ORDER BY fecha_registro ASC;";
        try {
            con = new ConexionBaseDatos().conexionBase();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                NuevaContraseña nc = new NuevaContraseña();
                nc.setIdContraseña(rs.getInt(1));
                nc.setContraseña(rs.getString(2));
                nc.setTipoContraseña(rs.getString(3));
                nc.setNombreSitioApp(rs.getString(4));
                nc.setFechaRegistro(rs.getDate(5));
                nc.setFechaActualizacion(rs.getDate(6));
                nc.setPropietario(rs.getString(7));
                contraseñaEncontrada.add(nc);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar por fecha:" + e.getMessage());
        }
        return contraseñaEncontrada;
    }
}
