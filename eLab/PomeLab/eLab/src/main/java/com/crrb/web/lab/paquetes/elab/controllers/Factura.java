/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crrb.web.lab.paquetes.elab.controllers;

import com.crrb.web.lab.paquetes.elab.entidades.T00Clasificador;
import com.crrb.web.lab.paquetes.elab.entidades.T00Cliente;
import com.crrb.web.lab.paquetes.elab.entidades.T00Medico;
import com.crrb.web.lab.paquetes.elab.entidades.T00Persona;
import com.crrb.web.lab.paquetes.elab.entidades.T01Factura;
import com.crrb.web.lab.paquetes.elab.entidades.T01FacturaDetalle;
import com.crrb.web.lab.paquetes.elab.entidades.VClasifArbol;
import com.crrb.web.lab.paquetes.elab.sessionBeans.T00ClienteFacade;
import com.crrb.web.lab.paquetes.elab.sessionBeans.T00MedicoFacade;
import com.crrb.web.lab.paquetes.elab.sessionBeans.T00PersonaFacade;
import com.crrb.web.lab.paquetes.elab.sessionBeans.T01FacturaDetalleFacade;
import com.crrb.web.lab.paquetes.elab.sessionBeans.T01FacturaFacade;
import com.crrb.web.lab.paquetes.elab.sessionBeans.personalizados.LogicaNegocio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Administrador
 */
@Named(value = "factura")
@SessionScoped
public class Factura implements Serializable {
    
    @EJB
    private T00MedicoFacade t00MedicoFacade1;
    
    @EJB
    private T01FacturaDetalleFacade t01FacturaDetalleFacade;
    
    @EJB
    private T00MedicoFacade t00MedicoFacade;
    
    @EJB
    private T00PersonaFacade t00PersonaFacade;
    
    @EJB
    private T00ClienteFacade t00ClienteFacade;
    
    @EJB
    private LogicaNegocio logicaNegocio;
    
    @EJB
    private T01FacturaFacade t01FacturaFacade;
    private T01FacturaDetalle selected = new T01FacturaDetalle();
    
    private T00Medico medico;
    
    private List<VClasifArbol> clasifArbols = new ArrayList<>();
    
    public List<VClasifArbol> findArbolClasif() {
        return logicaNegocio.findArbolClasif();
    }
    
    private List<T01Factura> facturas = new ArrayList<>();
    private T01Factura facturaObj = new T01Factura();
    
    private List<T00Persona> personas = new ArrayList<>();
    
    public List<T00Persona> findMedicosQueRecomiendan() {
        return logicaNegocio.findMedicosQueRecomiendan();
    }
    
    private String aplicaDescuento = "No";
    
    private List<T01FacturaDetalle> findDetalleFactura = new ArrayList<>();
    
    private T00Cliente cliente = new T00Cliente();
    private T00Persona persona = new T00Persona();

    /**
     * Creates a new instance of Factura
     */
    public Factura() {
        this.medico = new T00Medico();
    }
    private VClasifArbol clasifconvalores = new VClasifArbol();
    
    @PostConstruct
    private void init() {
        personas = findMedicosQueRecomiendan();
        clasifArbols = findArbolClasif();
        
    }
    
    public void destroy() {
        selected.setEstado("I");
        t01FacturaDetalleFacade.edit(selected);
        findDetalleFactura = logicaNegocio.findDetalleFactura(facturaObj);
        
    }
    
    List<T00Clasificador> listClasif = new ArrayList<>();
    
    public List<T00Clasificador> getListClasif() {
        return listClasif;
    }
    
    public List<T00Clasificador> findClasificadorPorDescripcion(String par) {
        return logicaNegocio.findClasificadorPorDescripcion(par);
    }
    
    public List<String> listadeItems(String parametro) {
        return logicaNegocio.listadeItems(parametro);
    }
    
    public List<String> completeTextItem(String query) {
        List<String> listadeItems = listadeItems(query);
        return listadeItems;
    }
    
    public List<T01Factura> findByNumeroFactura(String par) {
        return logicaNegocio.findByNumeroFactura(par);
    }
    
    public void buttonActionAgregar(ActionEvent actionEvent) {
        listClasif = findClasificadorPorDescripcion(clasifconvalores.getS());
        T00Clasificador listCla = new T00Clasificador();
        for (Iterator<T00Clasificador> it = listClasif.iterator(); it.hasNext();) {
            listCla = it.next();
        }
        T01FacturaDetalle facturaDetalle = new T01FacturaDetalle();
        facturaDetalle.setCantidad(BigInteger.ONE);
        facturaDetalle.setClasifDescri(clasifconvalores.getS());
        facturaDetalle.setEstado("A");
        facturaDetalle.setT00Clasificador(listCla);
        facturaDetalle.setFechaCreacion(new Date());
        
        BigDecimal id = medico.getId();
        id = (id == null) ? new BigDecimal("-1") : id;
        medico = t00MedicoFacade1.find(id);
        facturaDetalle.setT00Medico(medico);
        
        facturaDetalle.setT01Factura(facturaObj);
        facturaDetalle.setTieneDescuento(aplicaDescuento);
        
        Double descuento = new Double("0");
        if (aplicaDescuento.equalsIgnoreCase("No")) {
            descuento = listCla.getSinDescuento();
        } else {
            descuento = listCla.getConDescuento();
        }
        
        facturaDetalle.setValor(descuento);
        
        t01FacturaDetalleFacade.create(facturaDetalle);
        clasifconvalores = new VClasifArbol();
        findDetalleFactura = logicaNegocio.findDetalleFactura(facturaObj);
        medico = new T00Medico();
        aplicaDescuento = "No";
    }
    
    public void buttonAction(ActionEvent actionEvent) {
        //buscar
        String numeroFactura = facturaObj.getNumeroFactura();
        facturas = findByNumeroFactura(facturaObj.getNumeroFactura());
        
        if (!facturas.isEmpty()) {
            facturas.stream().forEach((factura1) -> {
                setFacturaObj(factura1);
            });
            findDetalleFactura = logicaNegocio.findDetalleFactura(facturaObj);
            setCliente(getFacturaObj().getT00Cliente());
            setPersona(getFacturaObj().getT00Cliente().getT00Persona());
        } else {
            limpiaPantalla();
            facturaObj = new T01Factura();
            facturaObj.setNumeroFactura(numeroFactura);
        }
        
    }
    
    public void buttonAction2(ActionEvent actionEvent) {
//        System.out.println("actionEvent = " + actionEvent);
        facturas = new ArrayList<>();
        facturaObj = new T01Factura();
        findDetalleFactura = new ArrayList<>();
        
        cliente = new T00Cliente();
        persona = new T00Persona();
        
    }
    
    public void buttonActionRefreCC(ActionEvent actionEvent) {
        String ccRucPasaporte = persona.getCcRucPasaporte();
        List<T00Persona> findPersonaByCC = logicaNegocio.findPersonaByCC(ccRucPasaporte);
        if (!findPersonaByCC.isEmpty()) {
            findPersonaByCC.stream().forEach((t00Persona) -> {
                persona = t00Persona;
            });
        } else {
            persona = new T00Persona();
            persona.setCcRucPasaporte(ccRucPasaporte);
        }
    }
    
    private void limpiaPantalla() {
        clasifconvalores = new VClasifArbol();
        
        setFacturaObj(new T01Factura());
        setPersona(new T00Persona());
        setCliente(new T00Cliente());
        findDetalleFactura = new ArrayList<>();
        medico = new T00Medico();
        clasifconvalores = new VClasifArbol();
        facturaObj = new T01Factura();
        selected = new T01FacturaDetalle();
        
    }
    
    public void buttonActionFin(ActionEvent actionEvent) {
        facturaObj.setCerrada(new BigDecimal(BigInteger.ONE));
        t01FacturaFacade.edit(facturaObj);
    }
    
    public List<T01Factura> findAll() {
        return t01FacturaFacade.findAll();
    }
    
    public List<String> completeText(String query) {
        List<String> lista = logicaNegocio.listByNumeroFactura(query);
        return lista;
    }
    
    public void buttonAction1(ActionEvent actionEvent) {
        //buscar
        t00PersonaFacade.edit(persona);
        
        List<T00Persona> findPersonaByCC = logicaNegocio.findPersonaByCC(persona.getCcRucPasaporte());
        for (T00Persona t00Persona : findPersonaByCC) {
            persona = t00Persona;
        }
        //si la persona ya es cliente no se crea el id de cliente
        T00Cliente find = t00ClienteFacade.find(persona.getId());
        
        if (find == null) {
            cliente.setId(persona.getId());
            t00ClienteFacade.edit(cliente);
        }
        
    }
    
    private List<VClasifArbol> findByTextoItem = new ArrayList<>();
    
    public List<VClasifArbol> findByTextoIte(String par) {
        return logicaNegocio.findByTextoItem(par);
    }
    
    public void buttonAction3(ActionEvent actionEvent) {
        buttonAction1(actionEvent);
        
        List<T00Cliente> findEsCliente = logicaNegocio.findEsCliente(persona);
//     
//        if (facturaObj.getId() == null) {
//            facturaObj.setId(BigDecimal.ONE);
//        }

        facturaObj.setEstado("A");
        facturaObj.setCerrada(BigDecimal.ZERO);
        
        if (findEsCliente.isEmpty()) {
            cliente = new T00Cliente();
            cliente.setId(persona.getId());
            t00ClienteFacade.create(cliente);
        } else {
            findEsCliente.stream().forEach((t00Cliente) -> {
                cliente = t00Cliente;
            });
        }
        
        facturaObj.setT00Cliente(cliente);
        facturaObj.setNumeroFactura(facturaObj.getNumeroFactura().toUpperCase());
        
        t01FacturaFacade.edit(facturaObj);
        facturas = findByNumeroFactura(facturaObj.getNumeroFactura().toUpperCase());
        
        facturas.stream().forEach((fac) -> {
            facturaObj = fac;
        });
//        System.out.println("facturaObj = " + facturaObj.getId());

    }

    /**
     * @return the facturas
     */
    public List<T01Factura> getFacturas() {
        facturas = findAll();
        return facturas;
    }

    /**
     * @param facturas the facturas to set
     */
    public void setFacturas(List<T01Factura> facturas) {
        this.facturas = facturas;
    }

    /**
     * @return the facturaObj
     */
    public T01Factura getFacturaObj() {
        return facturaObj;
    }

    /**
     * @param facturaObj the facturaObj to set
     */
    public void setFacturaObj(T01Factura facturaObj) {
        this.facturaObj = facturaObj;
    }
    
    public List<T01Factura> findFacturaByNumeroFactura(String numeroFactura) {
        return logicaNegocio.findFacturaByNumeroFactura(numeroFactura);
    }
    
    public List<T01Factura> getCabeceraGenerada() {
        List<T01Factura> findFacturaByNumeroFactura = logicaNegocio.findFacturaByNumeroFactura(facturaObj.getNumeroFactura());
        return findFacturaByNumeroFactura;
    }
    
    public List<String> completeText1(String query) {
        List<String> lista = logicaNegocio.findDNI(query);
        return lista;
    }
    
    
    public void listen1(AjaxBehaviorEvent event) {
        
        String ccRucPasaporte = persona.getCcRucPasaporte();
        List<T00Persona> findPersonaByCC = logicaNegocio.findPersonaByCC(ccRucPasaporte);
        if (!findPersonaByCC.isEmpty()) {
            findPersonaByCC.stream().forEach((t00Persona) -> {
                persona = t00Persona;
            });
        } else {
            persona = new T00Persona();
            persona.setCcRucPasaporte(ccRucPasaporte);
        }
        
    }

    /**
     * @return the cliente
     */
    public T00Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(T00Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the persona
     */
    public T00Persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(T00Persona persona) {
        this.persona = persona;
    }

    /**
     * @return the findDetalleFactura
     */
    public List<T01FacturaDetalle> getFindDetalleFactura() {
        return findDetalleFactura;
    }

    /**
     * @param findDetalleFactura the findDetalleFactura to set
     */
    public void setFindDetalleFactura(List<T01FacturaDetalle> findDetalleFactura) {
        this.findDetalleFactura = findDetalleFactura;
    }

    /**
     * @return the aplicaDescuento
     */
    public String getAplicaDescuento() {
        return aplicaDescuento;
    }

    /**
     * @param aplicaDescuento the aplicaDescuento to set
     */
    public void setAplicaDescuento(String aplicaDescuento) {
        this.aplicaDescuento = aplicaDescuento;
    }

    /**
     * @return the personas
     */
    public List<T00Persona> getPersonas() {
        return personas;
    }

    /**
     * @param personas the personas to set
     */
    public void setPersonas(List<T00Persona> personas) {
        this.personas = personas;
    }

    /**
     * @return the clasifArbols
     */
    public List<VClasifArbol> getClasifArbols() {
        return clasifArbols;
    }

    /**
     * @param clasifArbols the clasifArbols to set
     */
    public void setClasifArbols(List<VClasifArbol> clasifArbols) {
        this.clasifArbols = clasifArbols;
    }

    /**
     * @return the findByTextoItem
     */
    public List<VClasifArbol> getFindByTextoItem() {
        return findByTextoItem;
    }

    /**
     * @param findByTextoItem the findByTextoItem to set
     */
    public void setFindByTextoItem(List<VClasifArbol> findByTextoItem) {
        this.findByTextoItem = findByTextoItem;
    }

    /**
     * @return the clasifconvalores
     */
    public VClasifArbol getClasifconvalores() {
        return clasifconvalores;
    }

    /**
     * @param clasifconvalores the clasifconvalores to set
     */
    public void setClasifconvalores(VClasifArbol clasifconvalores) {
        this.clasifconvalores = clasifconvalores;
    }

    /**
     * @return the medico
     */
    public T00Medico getMedico() {
        return medico;
    }

    /**
     * @param medico the medico to set
     */
    public void setMedico(T00Medico medico) {
        this.medico = medico;
    }

    /**
     * @return the selected
     */
    public T01FacturaDetalle getSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(T01FacturaDetalle selected) {
        this.selected = selected;
    }

    /**
     * @return the vClasifArbol
     */
}
