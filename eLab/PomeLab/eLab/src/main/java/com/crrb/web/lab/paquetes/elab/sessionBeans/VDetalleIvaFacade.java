/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crrb.web.lab.paquetes.elab.sessionBeans;

import com.crrb.web.lab.paquetes.elab.entidades.VDetalleIva;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
@Stateless
public class VDetalleIvaFacade extends AbstractFacade<VDetalleIva> {

    @PersistenceContext(unitName = "com.crrb.web.lab.paquetes_eLab_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VDetalleIvaFacade() {
        super(VDetalleIva.class);
    }
    
}
