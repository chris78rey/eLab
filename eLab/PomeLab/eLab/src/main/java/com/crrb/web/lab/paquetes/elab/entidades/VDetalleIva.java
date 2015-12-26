/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crrb.web.lab.paquetes.elab.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "V_DETALLE_IVA")
@NamedQueries({
    @NamedQuery(name = "VDetalleIva.findAll", query = "SELECT v FROM VDetalleIva v")})
public class VDetalleIva implements Serializable {

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
    @Size(max = 12)
    @Column(name = "VALORPU")
    private String valorpu;
    @Size(max = 12)
    @Column(name = "IVA_VALOR")
    private String ivaValor;
    @Size(max = 12)
    @Column(name = "VALOR_CON_IVA")
    private String valorConIva;

    public VDetalleIva() {
    }

    public VDetalleIva(BigDecimal id) {
        this.id = id;
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
        this.numeroFactura = numeroFactura;
    }

    public String getValorpu() {
        return valorpu;
    }

    public void setValorpu(String valorpu) {
        this.valorpu = valorpu;
    }

    public String getIvaValor() {
        return ivaValor;
    }

    public void setIvaValor(String ivaValor) {
        this.ivaValor = ivaValor;
    }

    public String getValorConIva() {
        return valorConIva;
    }

    public void setValorConIva(String valorConIva) {
        this.valorConIva = valorConIva;
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
        if (!(object instanceof VDetalleIva)) {
            return false;
        }
        VDetalleIva other = (VDetalleIva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.crrb.web.lab.paquetes.elab.entidades.VDetalleIva[ id=" + id + " ]";
    }
    
}
