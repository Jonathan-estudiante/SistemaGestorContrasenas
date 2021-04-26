/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.istloja.modeloTablas;

import com.istloja.modelo.Borrador;
import com.istloja.modelo.NuevaContraseña;

public interface Comunicacion {
    void clickListaContraseña (NuevaContraseña nc);
    void clickListaEliminar(Borrador b);
}
