/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crrb.web.lab.paquetes.elab.sessionBeans.personalizados;

import com.crrb.web.lab.paquetes.elab.entidades.T00Cliente;
import com.crrb.web.lab.paquetes.elab.entidades.T00Cliente_;
import com.crrb.web.lab.paquetes.elab.entidades.T00Medico;
import com.crrb.web.lab.paquetes.elab.entidades.T00Persona;
import com.crrb.web.lab.paquetes.elab.entidades.T00Persona_;
import com.crrb.web.lab.paquetes.elab.entidades.T01Factura;
import com.crrb.web.lab.paquetes.elab.entidades.T01FacturaDetalle;
import com.crrb.web.lab.paquetes.elab.entidades.T01FacturaDetalle_;
import com.crrb.web.lab.paquetes.elab.entidades.T01Factura_;
import com.crrb.web.lab.paquetes.elab.entidades.VClasifArbol;
import com.crrb.web.lab.paquetes.elab.entidades.VClasifArbol_;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sun.nio.cs.ext.Big5;

/**
 *
 * @author Administrador
 */
@Stateless
public class LogicaNegocio {

    @PersistenceContext(unitName = "com.crrb.web.lab.paquetes_eLab_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public List<T01Factura> findFacturaByNumeroFactura(String numeroFactura) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T01Factura> cq = cb.createQuery(T01Factura.class);
        Root<T01Factura> root = cq.from(T01Factura.class);
        cq.where(cb.like(root.get(T01Factura_.numeroFactura), "%" + numeroFactura.toUpperCase() + "%"));
        List resultList = em.createQuery(cq).setHint("eclipselink.refresh", "true").getResultList();
        return resultList;
    }

    public List<String> listByNumeroFactura(String numerofact) {
        StringBuilder SQLNative = new StringBuilder();
        SQLNative.append("SELECT  distinct  ");
        SQLNative.append("       V_MAPPING.NUMERO_FACTURA   ");
        SQLNative.append("  FROM T01_FACTURA V_MAPPING  ");
        SQLNative.append(" WHERE V_MAPPING.NUMERO_FACTURA LIKE '%").append(numerofact.toUpperCase()).append("%'   ");

        Query query = em.createNativeQuery(SQLNative.toString());

        List<String> results = query.setMaxResults(3).getResultList();
        return results;
    }

    public List<T01Factura> findByNumeroFactura(String par) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T01Factura> cq = cb.createQuery(T01Factura.class);
        Root<T01Factura> root = cq.from(T01Factura.class);
        cq.where(cb.equal(root.get(T01Factura_.numeroFactura), par.toUpperCase()));
        List resultList = em.createQuery(cq).setHint("eclipselink.refresh", "true").getResultList();
        return resultList;
    }

    public List<String> findDNI(String parametro) {
        StringBuilder SQLNative = new StringBuilder();
        SQLNative.append("SELECT CC_RUC_PASAPORTE "
                + "FROM IMALAB.T00_PERSONA "
                + "where "
                + " CC_RUC_PASAPORTE like '%").append(parametro.toUpperCase()).append("%' ");
        Query query = em.createNativeQuery(SQLNative.toString());
        List<String> results = query.getResultList();
        return results;

    }

    public List<T00Persona> findPersonaByCC(String par) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T00Persona> cq = cb.createQuery(T00Persona.class);
        Root<T00Persona> root = cq.from(T00Persona.class);
        cq.where(cb.equal(root.get(T00Persona_.ccRucPasaporte), par.toUpperCase()));
        List resultList = em.createQuery(cq).setHint("eclipselink.refresh", "true").getResultList();
        return resultList;
    }

    public List<T01FacturaDetalle> findDetalleFactura(T01Factura par) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T01FacturaDetalle> cq = cb.createQuery(T01FacturaDetalle.class);
        Root<T01FacturaDetalle> root = cq.from(T01FacturaDetalle.class);
        cq.where(cb.equal(root.get(T01FacturaDetalle_.t01Factura), par));
        List resultList = em.createQuery(cq).setHint("eclipselink.refresh", "true").getResultList();
        return resultList;
    }

    public List<T00Cliente> findEsCliente(T00Persona par) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T00Cliente> cq = cb.createQuery(T00Cliente.class);
        Root<T00Cliente> root = cq.from(T00Cliente.class);
        cq.where(cb.equal(root.get(T00Cliente_.id), par.getId()));
        List resultList = em.createQuery(cq).setHint("eclipselink.refresh", "true").getResultList();
        return resultList;
    }

    public List<T00Persona> findMedicosQueRecomiendan() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T00Medico> cq = cb.createQuery(T00Medico.class);
        Root<T00Medico> root = cq.from(T00Medico.class);

        List<T00Medico> resultList = em.createQuery(cq).setHint("eclipselink.refresh", "true").getResultList();
        List<T00Persona> personas = new ArrayList<T00Persona>();

        T00Persona persona = new T00Persona();

        for (T00Medico t00Medico : resultList) {
            persona = t00Medico.getT00Persona();
            personas.add(persona);
        }

        return personas;
    }

    public List<VClasifArbol> findArbolClasif() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<VClasifArbol> cq = cb.createQuery(VClasifArbol.class);
        Root<VClasifArbol> root = cq.from(VClasifArbol.class);
      
        List resultList;
        resultList = em.createQuery(cq).setHint("eclipselink.refresh", "true").getResultList();
        return resultList;
    }

}
