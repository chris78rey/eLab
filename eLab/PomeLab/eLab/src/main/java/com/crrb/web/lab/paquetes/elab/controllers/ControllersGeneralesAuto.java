/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crrb.web.lab.paquetes.elab.controllers;

import com.crrb.web.lab.paquetes.elab.entidades.VClasifArbol;
import com.crrb.web.lab.paquetes.elab.sessionBeans.personalizados.LogicaNegocio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Administrador
 */
@Named(value = "controllersGeneralesAuto")
@SessionScoped
public class ControllersGeneralesAuto implements Serializable {

    @EJB
    private LogicaNegocio logicaNegocio;

    public List<VClasifArbol> getFindArbolClasif() {
        return logicaNegocio.findArbolClasif2();
    }

    /**
     * Creates a new instance of ControllersGeneralesAuto
     */
    public ControllersGeneralesAuto() {
    }

}
