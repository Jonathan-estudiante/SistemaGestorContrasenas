/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.modeloTablas;

import com.istloja.modelo.Borrador;
import com.istloja.vistas.Principal;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Personal
 */
public class ModeloTablaBorrador extends AbstractTableModel{
        //Arreglo con el nombre de las columnas
    public String[] m_colNames = {"CONTRASEÑA", "TIPO CONTRASEÑA", "SITIO WEB O APP" , "PROPIETARIO"};
    public List<Borrador> borrador;
    private final Principal principal;

    public ModeloTablaBorrador(List<Borrador> borrador, Principal principal) {
        this.borrador = borrador;
        this.principal = principal;
    }

    //Determina el número de filas que tengo en mi tabla
    @Override
    public int getRowCount() {
        return borrador.size();
    }

    //Determina el número de columnas que tengo en mi tabla
    @Override
    public int getColumnCount() {
        return m_colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Borrador borradores = borrador.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return borradores.getContraseña();
            case 1:
                return borradores.getTipoContraseña();
            case 2:
                return borradores.getNombreSitioApp();
            case 3:
                return borradores.getPropietario();
        }
        return new String();
    }

    //Este método sirve para definir los nombres de las columnas
    @Override
    public String getColumnName(int column) {
        return m_colNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        principal.clickListaEliminar(borrador.get(rowIndex));
        return super.isCellEditable(rowIndex, columnIndex);//To change body of generated methods, choose Tools | Templates.
    }

    public List<Borrador> getPersonas() {
        return borrador;
    }

    public void setPersonas(List<Borrador> borradores) {
        this.borrador = borradores;
    }

}
