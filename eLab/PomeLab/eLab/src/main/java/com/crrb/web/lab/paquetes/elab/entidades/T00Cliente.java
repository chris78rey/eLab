/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crrb.web.lab.paquetes.elab.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "T00_CLIENTE")
@NamedQueries({
    @NamedQuery(name = "T00Cliente.findAll", query = "SELECT t FROM T00Cliente t")})
public class T00Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "t00Cliente", fetch = FetchType.LAZY)
    private List<T01Factura> t01FacturaList;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private T00Persona t00Persona;

    public T00Cliente() {
    }

    public T00Cliente(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public List<T01Factura> getT01FacturaList() {
        return t01FacturaList;
    }

    public void setT01FacturaList(List<T01Factura> t01FacturaList) {
        this.t01FacturaList = t01FacturaList;
    }

    public T00Persona getT00Persona() {
        return t00Persona;
    }

    public void setT00Persona(T00Persona t00Persona) {
        this.t00Persona = t00Persona;
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
        if (!(object instanceof T00Cliente)) {
            return false;
        }
        T00Cliente other = (T00Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crrb.web.lab.paquetes.elab.entidades.T00Cliente[ id=" + id + " ]";
    }
    
}
