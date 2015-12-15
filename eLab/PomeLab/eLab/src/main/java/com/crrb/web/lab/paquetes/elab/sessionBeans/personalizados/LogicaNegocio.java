/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crrb.web.lab.paquetes.elab.sessionBeans.personalizados;

import com.crrb.web.lab.paquetes.elab.entidades.T00Persona;
import com.crrb.web.lab.paquetes.elab.entidades.T00Persona_;
import com.crrb.web.lab.paquetes.elab.entidades.T01Factura;
import com.crrb.web.lab.paquetes.elab.entidades.T01FacturaDetalle;
import com.crrb.web.lab.paquetes.elab.entidades.T01FacturaDetalle_;
import com.crrb.web.lab.paquetes.elab.entidades.T01Factura_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
        SQLNative.append("  FROM V_MAPPING   ");
        SQLNative.append(" WHERE V_MAPPING.NUMERO_FACTURA LIKE '%").append(numerofact.toUpperCase()).append("%'   ");

        Query query = em.createNativeQuery(SQLNative.toString());

        List<String> results = query.getResultList();
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
}
