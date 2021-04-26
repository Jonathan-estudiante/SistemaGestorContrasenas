package com.istloja.modelo;

import java.util.Date;

public class NuevaContraseña {
    private int idContraseña;
    private String contraseña;
    private String tipoContraseña;
    private String nombreSitioApp;
    private Date fechaRegistro;
    private Date fechaActualizacion;
    private String propietario;

    public NuevaContraseña() {
    }

    public int getIdContraseña() {
        return idContraseña;
    }

    public void setIdContraseña(int idContraseña) {
        this.idContraseña = idContraseña;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipoContraseña() {
        return tipoContraseña;
    }

    public void setTipoContraseña(String tipoContraseña) {
        this.tipoContraseña = tipoContraseña;
    }

    public String getNombreSitioApp() {
        return nombreSitioApp;
    }

    public void setNombreSitioApp(String nombreSitioApp) {
        this.nombreSitioApp = nombreSitioApp;
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

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propiedtario) {
        this.propietario = propiedtario;
    }


    
}
