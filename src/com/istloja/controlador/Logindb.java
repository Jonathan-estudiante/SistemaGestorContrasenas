package com.istloja.controlador;

import ConexionBase.ConexionBaseDatos;
import com.istloja.modelo.Login;
import com.istloja.Utilidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Logindb {

    PreparedStatement ps = null;
    //Interfaz de acceso a la base de datos
    Statement stm = null;
    //Conexion con la base de datos.
    Connection con = null;
    //Sentencia de JDBC para obtener valores de la base de datos.
    ResultSet rs = null;
    Utilidades utilidades;

    public Logindb() {
        utilidades = new Utilidades();
    }

    public List<Login> obtenerUsuario(String usuario, String contraseña) {

        String sql = "SELECT * FROM basegestorcontraseñas.usuario WHERE nombre_usuario='" + usuario + "'AND contrasena='" + contraseña + "'";

        List<Login> listaLogin = new ArrayList<>();

        try {
            con = new ConexionBaseDatos().conexionBase();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Login login = new Login();
                login.setUsuario(rs.getString(1));
                login.setContraseña(rs.getString(2));
                login.setFechaRegistro(rs.getDate(3));
                login.setFechaActualizacion(rs.getDate(4));

                listaLogin.add(login);
            }
            stm.close();
            rs.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error al entrar" + e.getMessage());

        }
        return listaLogin;
    }
}
