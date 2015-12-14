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
@Table(name = "T00_MEDICO")
@NamedQueries({
    @NamedQuery(name = "T00Medico.findAll", query = "SELECT t FROM T00Medico t")})
public class T00Medico implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "PORCENTAJE")
    private Double porcentaje;
    @JoinColumn(name = "ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private T00Persona t00Persona;
    @OneToMany(mappedBy = "t00Medico", fetch = FetchType.LAZY)
    private List<T01FacturaDetalle> t01FacturaDetalleList;

    public T00Medico() {
    }

    public T00Medico(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public T00Persona getT00Persona() {
        return t00Persona;
    }

    public void setT00Persona(T00Persona t00Persona) {
        this.t00Persona = t00Persona;
    }

    public List<T01FacturaDetalle> getT01FacturaDetalleList() {
        return t01FacturaDetalleList;
    }

    public void setT01FacturaDetalleList(List<T01FacturaDetalle> t01FacturaDetalleList) {
        this.t01FacturaDetalleList = t01FacturaDetalleList;
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
        if (!(object instanceof T00Medico)) {
            return false;
        }
        T00Medico other = (T00Medico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crrb.web.lab.paquetes.elab.entidades.T00Medico[ id=" + id + " ]";
    }
    
}
