/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.controlador;

import ConexionBase.ConexionBaseDatos;
import com.istloja.modelo.Borrador;
import com.istloja.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Personal
 */
public class Borradordb {

    PreparedStatement ps = null;
    //Interfaz de acceso a la base de datos
    Statement stm = null;
    //Conexion con la base de datos.
    Connection con = null;
    //Sentencia de JDBC para obtener valores de la base de datos.
    ResultSet rs = null;
    Utilidades utilidades;

    public Borradordb() {
        utilidades = new Utilidades();
    }

    //Método para insertar, crear o guardar una contraseña**************************************************************************
    public boolean crearBorrador(Borrador borrador) {
        boolean registrar = false;
        String sql = "INSERT INTO `basegestorcontraseñas`.`borradores` (`contrasena`, `tipo_contrasena`, `nombre_sitioWeb_app`, `fecha_registro`,`propietario`) VALUES ('"
                + borrador.getContraseña() + "', '"
                + borrador.getTipoContraseña() + "', '"
                + borrador.getNombreSitioApp() + "', '"
                + utilidades.formatoDateTime(borrador.getFechaRegistro())+ "', '"
                + borrador.getPropietario()+"');";
        try {
            ConexionBaseDatos conexion = new ConexionBaseDatos();
            con = conexion.conexionBase();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al crear un borrador " + e.getMessage());
        }
        return registrar;
    }
        public boolean elminarBorrador(Borrador borrador) {
        boolean registrar = false;

        String sql = "DELETE FROM `basegestorcontraseñas`.`borradores` WHERE id_borrador =" + String.valueOf(borrador.getIdBorrador());
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
        
    //Método traer a todas las contraseñas******************************************************************************************
    public List<Borrador> obtenerContraseñasNoGuardadas() {

        String sql = "SELECT * FROM basegestorcontraseñas.borradores;";

        List<Borrador> listaContraseñas = new ArrayList<>();

        try {
            con = new ConexionBaseDatos().conexionBase();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Borrador nc = new Borrador();
                nc.setIdBorrador(rs.getInt(1));
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

    public List<Borrador> getContraseñaNombreSitioApp(String sitioApp) {

        List<Borrador> contraseñaEncontrada = new ArrayList<>();
        String sql = "SELECT * FROM basegestorcontraseñas.borradores WHERE nombre_sitioWeb_app LIKE \"%" + sitioApp + "%\"";
        try {
            con = new ConexionBaseDatos().conexionBase();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Borrador nc = new Borrador();
                nc.setIdBorrador(rs.getInt(1));
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
            System.out.println("Error al buscar por nombre en borradores:" + e.getMessage());
        }
        return contraseñaEncontrada;
    }

    public List<Borrador> mostrarSitioWebBorrador(String tipo) {

        List<Borrador> contraseñaEncontrada = new ArrayList<>();
        String sql = "SELECT * FROM basegestorcontraseñas.borradores WHERE tipo_contrasena LIKE \"%" + tipo + "%\"";
        try {
            con = new ConexionBaseDatos().conexionBase();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Borrador nc = new Borrador();
                nc.setIdBorrador(rs.getInt(1));
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

    public List<Borrador> mostrarFecha_RecienteBorrador() {

        List<Borrador> contraseñaEncontrada = new ArrayList<>();
        String sql = "SELECT * FROM basegestorcontraseñas.borradores ORDER BY fecha_registro DESC;";
        try {
            con = new ConexionBaseDatos().conexionBase();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Borrador nc = new Borrador();
                nc.setIdBorrador(rs.getInt(1));
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

    public List<Borrador> mostrarFecha_PasadaBorrador() {

        List<Borrador> contraseñaEncontrada = new ArrayList<>();
        String sql = "SELECT * FROM basegestorcontraseñas.borradores ORDER BY fecha_registro ASC;";
        try {
            con = new ConexionBaseDatos().conexionBase();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Borrador nc = new Borrador();
                nc.setIdBorrador(rs.getInt(1));
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
