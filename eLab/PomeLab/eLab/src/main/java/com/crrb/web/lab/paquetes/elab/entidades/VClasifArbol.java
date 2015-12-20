/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crrb.web.lab.paquetes.elab.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "V_CLASIF_ARBOL")
@NamedQueries({
    @NamedQuery(name = "VClasifArbol.findAll", query = "SELECT v FROM VClasifArbol v")})
public class VClasifArbol implements Serializable {

    @Column(name = "ID_PADRE")
    @Basic(optional = true)
    private BigDecimal idPadre;

    public void setIdPadre(BigDecimal idPadre) {
        this.idPadre = idPadre;
    }

    public BigDecimal getIdPadre() {
        return this.idPadre;
    }

    @Column(name = "IVA")
    private Double iva;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "NIVEL")
    private BigInteger nivel;
    @Size(max = 100)
    @Column(name = "S")
    private String s;
    @Column(name = "CON_DESCUENTO")
    private Double conDescuento;
    @Column(name = "SIN_DESCUENTO")
    private Double sinDescuento;

    public VClasifArbol() {
    }

    public VClasifArbol(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getNivel() {
        return nivel;
    }

    public void setNivel(BigInteger nivel) {
        this.nivel = nivel;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Double getConDescuento() {
        return conDescuento;
    }

    public void setConDescuento(Double conDescuento) {
        this.conDescuento = conDescuento;
    }

    public Double getSinDescuento() {
        return sinDescuento;
    }

    public void setSinDescuento(Double sinDescuento) {
        this.sinDescuento = sinDescuento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VClasifArbol)) {
            return false;
        }
        VClasifArbol other = (VClasifArbol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crrb.web.lab.paquetes.elab.entidades.VClasifArbol[ id=" + id + " ]";
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

}
