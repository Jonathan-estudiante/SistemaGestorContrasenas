package com.istloja.modelo;

import java.util.Date;

public class Login {
    private String usuario;
    private String contraseña;
    private Date fechaRegistro;
    private Date fechaActualizacion;

    public Login() {
    }

    public Login(String usuario, String contraseña, Date fechaRegistro, Date fechaActualizacion) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.fechaRegistro = fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
          


    
}
