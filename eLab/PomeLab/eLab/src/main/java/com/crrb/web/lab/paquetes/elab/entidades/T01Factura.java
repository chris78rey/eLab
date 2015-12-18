/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crrb.web.lab.paquetes.elab.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "T01_FACTURA")
@NamedQueries({
    @NamedQuery(name = "T01Factura.findAll", query = "SELECT t FROM T01Factura t")})
public class T01Factura implements Serializable {

    @SequenceGenerator(name = "modulos_T01_FACTURA_SEQ", sequenceName = "IMALAB.T01_FACTURA_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "modulos_T01_FACTURA_SEQ")

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 100)
    @Column(name = "NUMERO_FACTURA")
    private String numeroFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private T00Cliente t00Cliente;
    @OneToMany(mappedBy = "t01Factura", fetch = FetchType.LAZY)
    private List<T01FacturaDetalle> t01FacturaDetalleList;

    public T01Factura() {
    }

    public T01Factura(BigDecimal id) {
        this.id = id;
    }

    public T01Factura(BigDecimal id, Date fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {

        this.numeroFactura = numeroFactura.toUpperCase();
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public T00Cliente getT00Cliente() {
        return t00Cliente;
    }

    public void setT00Cliente(T00Cliente t00Cliente) {
        this.t00Cliente = t00Cliente;
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
        if (!(object instanceof T01Factura)) {
            return false;
        }
        T01Factura other = (T01Factura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crrb.web.lab.paquetes.elab.entidades.T01Factura[ id=" + id + " ]";
    }

}
