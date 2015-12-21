/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crrb.web.lab.paquetes.elab.entidades;

import java.io.Serializable;
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
@Table(name = "V_PERSONA_MEDICO")
@NamedQueries({
    @NamedQuery(name = "VPersonaMedico.findAll", query = "SELECT v FROM VPersonaMedico v")})
public class VPersonaMedico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @Id
    private BigInteger id;
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
    @Column(name = "ID_MEDICO")
    private BigInteger idMedico;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PORCENTAJE")
    private Double porcentaje;

    public VPersonaMedico() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
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

    public BigInteger getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(BigInteger idMedico) {
        this.idMedico = idMedico;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }
    
}
