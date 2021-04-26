package com.istloja.modeloTablas;

import com.istloja.modelo.NuevaContraseña;
import com.istloja.vistas.Principal;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModeloTablaContraseñas extends AbstractTableModel {

    //Arreglo con el nombre de las columnas
    public String[] m_colNames = {"CONTRASEÑA", "TIPO CONTRASEÑA", "SITIO WEB O APP", "PROPIETARIO"};
    public List<NuevaContraseña> contraseñas;
    private final Principal principal;

    public ModeloTablaContraseñas(List<NuevaContraseña> contraseñas, Principal principal) {
        this.contraseñas = contraseñas;
        this.principal = principal;
    }

    //Determina el número de filas que tengo en mi tabla
    @Override
    public int getRowCount() {
        return contraseñas.size();
    }

    //Determina el número de columnas que tengo en mi tabla
    @Override
    public int getColumnCount() {
        return m_colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NuevaContraseña contraseña = contraseñas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return contraseña.getContraseña();
            case 1:
                return contraseña.getTipoContraseña();
            case 2:
                return contraseña.getNombreSitioApp();
            case 3:
                return contraseña.getPropietario();
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
        principal.clickListaContraseña(contraseñas.get(rowIndex));
        return super.isCellEditable(rowIndex, columnIndex);//To change body of generated methods, choose Tools | Templates.
    }

    public List<NuevaContraseña> getPersonas() {
        return contraseñas;
    }

    public void setPersonas(List<NuevaContraseña> contraseñas) {
        this.contraseñas = contraseñas;
    }

}
