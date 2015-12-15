/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crrb.web.lab.paquetes.elab.controllers;

import com.crrb.web.lab.paquetes.elab.entidades.T00Cliente;
import com.crrb.web.lab.paquetes.elab.entidades.T00Persona;
import com.crrb.web.lab.paquetes.elab.entidades.T01Factura;
import com.crrb.web.lab.paquetes.elab.entidades.T01FacturaDetalle;
import com.crrb.web.lab.paquetes.elab.sessionBeans.T00ClienteFacade;
import com.crrb.web.lab.paquetes.elab.sessionBeans.T00PersonaFacade;
import com.crrb.web.lab.paquetes.elab.sessionBeans.T01FacturaFacade;
import com.crrb.web.lab.paquetes.elab.sessionBeans.personalizados.LogicaNegocio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    private T00PersonaFacade t00PersonaFacade;
    
    @EJB
    private T00ClienteFacade t00ClienteFacade;
    
    @EJB
    private LogicaNegocio logicaNegocio;
    
    @EJB
    private T01FacturaFacade t01FacturaFacade;
    private List<T01Factura> facturas = new ArrayList<>();
    private T01Factura facturaObj = new T01Factura();
    
    private List<T01FacturaDetalle> findDetalleFactura = new ArrayList<>();
    
    private T00Cliente cliente = new T00Cliente();
    private T00Persona persona = new T00Persona();

    /**
     * Creates a new instance of Factura
     */
    public Factura() {
    }
    
    public List<T01Factura> findByNumeroFactura(String par) {
        return logicaNegocio.findByNumeroFactura(par);
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
        System.out.println("actionEvent = " + actionEvent);
        facturas = new ArrayList<>();
        facturaObj = new T01Factura();
        findDetalleFactura = new ArrayList<>();
        
        cliente = new T00Cliente();
        persona = new T00Persona();
        
    }
    
    private void limpiaPantalla() {
        setFacturaObj(new T01Factura());
        setPersona(new T00Persona());
        setCliente(new T00Cliente());
        findDetalleFactura = new ArrayList<>();
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
        
        cliente.setId(persona.getId());
        t00ClienteFacade.edit(cliente);
        
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
    
    public List<String> completeText1(String query) {
        List<String> lista = logicaNegocio.findDNI(query);
        return lista;
    }
    
    public void listen1(AjaxBehaviorEvent event) {
        
        String ccRucPasaporte = persona.getCcRucPasaporte();
        List<T00Persona> findPersonaByCC = logicaNegocio.findPersonaByCC(ccRucPasaporte);
        if (findPersonaByCC.size() != 0) {
            for (T00Persona t00Persona : findPersonaByCC) {
                persona = t00Persona;
            }
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
    
}
