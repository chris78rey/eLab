/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crrb.web.lab.paquetes.elab.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "T00_PERSONA")
@NamedQueries({
    @NamedQuery(name = "T00Persona.findAll", query = "SELECT t FROM T00Persona t")})
public class T00Persona implements Serializable {

    @SequenceGenerator(name = "modulos_T00_SOCIOS_SEQ", sequenceName = "IMALAB.T00_SOCIOS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "modulos_T00_SOCIOS_SEQ")
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 100)
    @Column(name = "CC_RUC_PASAPORTE")
    private String ccRucPasaporte;
    @Size(max = 100)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Size(max = 100)
    @Column(name = "NOMBRES")
    private String nombres;
    @Size(max = 20)
    @Column(name = "TELEF")
    private String telef;
    @Size(max = 20)
    @Column(name = "CELULAR")
    private String celular;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 30)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 100)
    @Column(name = "DIRECCION")
    private String direccion;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "t00Persona", fetch = FetchType.LAZY)
    private T00Medico t00Medico;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "t00Persona", fetch = FetchType.LAZY)
    private T00Cliente t00Cliente;

    public T00Persona() {
    }

    public T00Persona(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCcRucPasaporte() {
        return ccRucPasaporte;
    }

    public void setCcRucPasaporte(String ccRucPasaporte) {
        this.ccRucPasaporte = ccRucPasaporte;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelef() {
        return telef;
    }

    public void setTelef(String telef) {
        this.telef = telef;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public T00Medico getT00Medico() {
        return t00Medico;
    }

    public void setT00Medico(T00Medico t00Medico) {
        this.t00Medico = t00Medico;
    }

    public T00Cliente getT00Cliente() {
        return t00Cliente;
    }

    public void setT00Cliente(T00Cliente t00Cliente) {
        this.t00Cliente = t00Cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    @SuppressWarnings("null")
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof T00Persona)) {
            return false;
        }
        T00Persona other = (T00Persona) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.crrb.web.lab.paquetes.elab.entidades.T00Persona[ id=" + id + " ]";
    }

}
