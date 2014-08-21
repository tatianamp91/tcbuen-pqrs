package com.tcbuen.pqrs.modelo.dto;

import com.tcbuen.pqrs.modelo.InfoSolicitante;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class InfoSolicitanteDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String correoElectronico;
    private Long idInfoSolicitante;
    private String nombreContacto;
    private String nombreEmpresa;
    private String numeroCelular;
    private String numeroIdentificacion;
    private String telefonoFijo;
    private Long idTpDoc_TipoDocumento;

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Long getIdInfoSolicitante() {
        return idInfoSolicitante;
    }

    public void setIdInfoSolicitante(Long idInfoSolicitante) {
        this.idInfoSolicitante = idInfoSolicitante;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public Long getIdTpDoc_TipoDocumento() {
        return idTpDoc_TipoDocumento;
    }

    public void setIdTpDoc_TipoDocumento(Long idTpDoc_TipoDocumento) {
        this.idTpDoc_TipoDocumento = idTpDoc_TipoDocumento;
    }
}
