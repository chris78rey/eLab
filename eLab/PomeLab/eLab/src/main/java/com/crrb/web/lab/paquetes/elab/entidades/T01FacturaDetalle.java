/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crrb.web.lab.paquetes.elab.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "T01_FACTURA_DETALLE")
@NamedQueries({
    @NamedQuery(name = "T01FacturaDetalle.findAll", query = "SELECT t FROM T01FacturaDetalle t")})
public class T01FacturaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private BigInteger cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private double valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CLASIF_DESCRI")
    private String clasifDescri;
    @Size(max = 100)
    @Column(name = "TIENE_DESCUENTO")
    private String tieneDescuento;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @JoinColumn(name = "ID_CLASIF", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private T00Clasificador t00Clasificador;
    @JoinColumn(name = "ID_MEDICO", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private T00Medico t00Medico;
    @JoinColumn(name = "ID_FACTURA", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private T01Factura t01Factura;

    @Column(name = "ESTADO")
    @Basic(optional = true)
    private String estado;

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }

    @Column(name = "IVA_VALOR")
    @Basic(optional = true)
    private BigDecimal ivaValor;

    public void setIvaValor(BigDecimal ivaValor) {
        this.ivaValor = ivaValor;
    }

    public BigDecimal getIvaValor() {
        return this.ivaValor;
    }
    @Column(name = "VALOR_CON_IVA")
    @Basic(optional = true)
    private BigDecimal valorConIva;

    public void setValorConIva(BigDecimal valorConIva) {
        this.valorConIva = valorConIva;
    }

    public BigDecimal getValorConIva() {
        return this.valorConIva;
    }

    public T01FacturaDetalle() {
    }

    public T01FacturaDetalle(BigDecimal id) {
        this.id = id;
    }

    public T01FacturaDetalle(BigDecimal id, BigInteger cantidad, double valor, String clasifDescri) {
        this.id = id;
        this.cantidad = cantidad;
        this.valor = valor;
        this.clasifDescri = clasifDescri;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigInteger cantidad) {
        this.cantidad = cantidad;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getClasifDescri() {
        return clasifDescri;
    }

    public void setClasifDescri(String clasifDescri) {
        this.clasifDescri = clasifDescri;
    }

    public String getTieneDescuento() {
        return tieneDescuento;
    }

    public void setTieneDescuento(String tieneDescuento) {
        this.tieneDescuento = tieneDescuento;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public T00Clasificador getT00Clasificador() {
        return t00Clasificador;
    }

    public void setT00Clasificador(T00Clasificador t00Clasificador) {
        this.t00Clasificador = t00Clasificador;
    }

    public T00Medico getT00Medico() {
        return t00Medico;
    }

    public void setT00Medico(T00Medico t00Medico) {
        this.t00Medico = t00Medico;
    }

    public T01Factura getT01Factura() {
        return t01Factura;
    }

    public void setT01Factura(T01Factura t01Factura) {
        this.t01Factura = t01Factura;
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
        if (!(object instanceof T01FacturaDetalle)) {
            return false;
        }
        T01FacturaDetalle other = (T01FacturaDetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crrb.web.lab.paquetes.elab.entidades.T01FacturaDetalle[ id=" + id + " ]";
    }

}
