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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "T00_CLASIFICADOR")
@NamedQueries({
    @NamedQuery(name = "T00Clasificador.findAll", query = "SELECT t FROM T00Clasificador t")})
public class T00Clasificador implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "CON_DESCUENTO")
    private Double conDescuento;
    @Column(name = "SIN_DESCUENTO")
    private Double sinDescuento;
    @Column(name = "IVA")
    private Double iva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "t00Clasificador", fetch = FetchType.LAZY)
    private List<T01FacturaDetalle> t01FacturaDetalleList;
    @OneToMany(mappedBy = "t00Clasificador", fetch = FetchType.LAZY)
    private List<T00Clasificador> t00ClasificadorList;
    @JoinColumn(name = "ID_PADRE", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private T00Clasificador t00Clasificador;

    public T00Clasificador() {
    }

    public T00Clasificador(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public List<T01FacturaDetalle> getT01FacturaDetalleList() {
        return t01FacturaDetalleList;
    }

    public void setT01FacturaDetalleList(List<T01FacturaDetalle> t01FacturaDetalleList) {
        this.t01FacturaDetalleList = t01FacturaDetalleList;
    }

    public List<T00Clasificador> getT00ClasificadorList() {
        return t00ClasificadorList;
    }

    public void setT00ClasificadorList(List<T00Clasificador> t00ClasificadorList) {
        this.t00ClasificadorList = t00ClasificadorList;
    }

    public T00Clasificador getT00Clasificador() {
        return t00Clasificador;
    }

    public void setT00Clasificador(T00Clasificador t00Clasificador) {
        this.t00Clasificador = t00Clasificador;
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
        if (!(object instanceof T00Clasificador)) {
            return false;
        }
        T00Clasificador other = (T00Clasificador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crrb.web.lab.paquetes.elab.entidades.T00Clasificador[ id=" + id + " ]";
    }
    
}
