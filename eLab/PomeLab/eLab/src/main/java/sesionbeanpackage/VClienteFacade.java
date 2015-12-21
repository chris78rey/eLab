/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesionbeanpackage;

import com.crrb.web.lab.paquetes.elab.entidades.VCliente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
@Stateless
public class VClienteFacade extends AbstractFacade<VCliente> {

    @PersistenceContext(unitName = "com.crrb.web.lab.paquetes_eLab_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VClienteFacade() {
        super(VCliente.class);
    }
    
}
