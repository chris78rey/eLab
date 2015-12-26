/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crrb.web.lab.paquetes.elab.sessionBeans.personalizados;

import com.crrb.web.lab.paquetes.elab.entidades.T00Clasificador;
import com.crrb.web.lab.paquetes.elab.entidades.T00Clasificador_;
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
import com.crrb.web.lab.paquetes.elab.entidades.VDetalleIva;
import com.crrb.web.lab.paquetes.elab.entidades.VDetalleIva_;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
        numeroFactura = (numeroFactura == null) ? "" : numeroFactura;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T01Factura> cq = cb.createQuery(T01Factura.class);
        Root<T01Factura> root = cq.from(T01Factura.class);
        cq.where(cb.like(root.get(T01Factura_.numeroFactura), "%" + numeroFactura.toUpperCase() + "%"));
        List resultList = em.createQuery(cq).setHint("eclipselink.refresh", "true").getResultList();
        return resultList;
    }

    public List<String> listByNumeroFactura(String numerofact) {
        numerofact = (numerofact == null) ? "" : numerofact;
        StringBuilder SQLNative = new StringBuilder();
        SQLNative.append("SELECT  distinct  ");
        SQLNative.append("       V_MAPPING.NUMERO_FACTURA   ");
        SQLNative.append("  FROM T01_FACTURA V_MAPPING  ");
        SQLNative.append(" WHERE V_MAPPING.NUMERO_FACTURA LIKE '%").append(numerofact.toUpperCase()).append("%'   ");

        Query query = em.createNativeQuery(SQLNative.toString());

        List<String> results = query.setMaxResults(150).getResultList();
        return results;
    }

    public List<T01Factura> findByNumeroFactura(String par) {
        par = (par == null) ? "" : par;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T01Factura> cq = cb.createQuery(T01Factura.class);
        Root<T01Factura> root = cq.from(T01Factura.class);
        cq.where(cb.equal(root.get(T01Factura_.numeroFactura), par.toUpperCase()));
        List resultList = em.createQuery(cq).setHint("eclipselink.refresh", "true").getResultList();
        return resultList;
    }

    public List<String> findDNI(String parametro) {
        parametro = (parametro == null) ? "" : parametro;
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
        par = (par == null) ? "" : par;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T00Persona> cq = cb.createQuery(T00Persona.class);
        Root<T00Persona> root = cq.from(T00Persona.class);
        cq.where(cb.equal(root.get(T00Persona_.ccRucPasaporte), par.toUpperCase()));
        List resultList = em.createQuery(cq).setHint("eclipselink.refresh", "true").getResultList();
        return resultList;
    }

    public List<T01FacturaDetalle> findDetalleFactura(T01Factura par) {
        par = (par == null) ? new T01Factura() : par;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T01FacturaDetalle> cq = cb.createQuery(T01FacturaDetalle.class);
        Root<T01FacturaDetalle> root = cq.from(T01FacturaDetalle.class);

        List<Predicate> predicatesOR = new ArrayList<>();
        predicatesOR.add(cb.equal(root.get(T01FacturaDetalle_.t01Factura), par));
        predicatesOR.add(cb.equal(root.get(T01FacturaDetalle_.estado), "A"));
        cq.where(cb.and(predicatesOR.toArray(new Predicate[predicatesOR.size()])));

        cq.orderBy(cb.asc(root.get(T01FacturaDetalle_.id)));
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

    public List<VClasifArbol> findArbolClasif2() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<VClasifArbol> cq = cb.createQuery(VClasifArbol.class);
        Root<VClasifArbol> root = cq.from(VClasifArbol.class);
        List<Predicate> predicatesOR = new ArrayList<Predicate>();
        predicatesOR.add(cb.isNull(root.get(VClasifArbol_.conDescuento)));
        predicatesOR.add(cb.isNull(root.get(VClasifArbol_.sinDescuento)));
        cq.where(cb.and(predicatesOR.toArray(new Predicate[predicatesOR.size()])));
        List resultList;
        resultList = em.createQuery(cq).setHint("eclipselink.refresh", "true").getResultList();
        return resultList;
    }

    public List<VClasifArbol> findByTextoItem(String par) {
        par = (par == null) ? "" : par;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<VClasifArbol> cq = cb.createQuery(VClasifArbol.class);
        Root<VClasifArbol> root = cq.from(VClasifArbol.class);

        List<Predicate> predicatesOR = new ArrayList<Predicate>();

        predicatesOR.add(cb.like(root.get(VClasifArbol_.s), "%" + par.toUpperCase() + "%"));
        predicatesOR.add(cb.isNotNull(root.get(VClasifArbol_.sinDescuento)));

        cq.where(cb.and(predicatesOR.toArray(new Predicate[predicatesOR.size()])));
        List resultList = em.createQuery(cq).setHint("eclipselink.refresh", "true").getResultList();
        return resultList;

    }

    public List<String> listadeItems(String parametro) {
        parametro = (parametro == null) ? "%" : parametro;
        StringBuilder SQLNative = new StringBuilder();
        SQLNative.append("  SELECT UPPER(T00_CLASIFICADOR.DESCRIPCION)   ");
        SQLNative.append("    FROM IMALAB.T00_CLASIFICADOR   ");
        SQLNative.append("   WHERE T00_CLASIFICADOR.CON_DESCUENTO IS NOT NULL   ");
        SQLNative.append("   AND UPPER(T00_CLASIFICADOR.DESCRIPCION) LIKE '%").append(parametro.toUpperCase()).append("%'  ");
        SQLNative.append("ORDER BY T00_CLASIFICADOR.DESCRIPCION   ");

        Query query = em.createNativeQuery(SQLNative.toString());

        List<String> results = query.setMaxResults(200).getResultList();
        return results;

    }

    public List<T00Clasificador> findClasificadorPorDescripcion(String par) {
        par = (par == null) ? "" : par;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T00Clasificador> cq = cb.createQuery(T00Clasificador.class);
        Root<T00Clasificador> root = cq.from(T00Clasificador.class);
        cq.where(cb.equal(root.get(T00Clasificador_.descripcion), par.toUpperCase()));
        List resultList = em.createQuery(cq).setHint("eclipselink.refresh", "true").getResultList();
        return resultList;
    }

    public List<VDetalleIva> findIVAByNumeroFactura(String pnumeroFactura) {
        pnumeroFactura = (pnumeroFactura == null) ? "-1" : pnumeroFactura;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<VDetalleIva> cq = cb.createQuery(VDetalleIva.class);
        Root<VDetalleIva> root = cq.from(VDetalleIva.class);
        cq.where(cb.equal(root.get(VDetalleIva_.numeroFactura), pnumeroFactura));
        List resultList = em.createQuery(cq).setHint("eclipselink.refresh", "true").getResultList();
        return resultList;
    }

}
