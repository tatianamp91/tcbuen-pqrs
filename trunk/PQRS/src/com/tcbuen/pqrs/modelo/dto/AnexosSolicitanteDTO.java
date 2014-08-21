package com.tcbuen.pqrs.modelo.dto;

import com.tcbuen.pqrs.modelo.AnexosSolicitante;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class AnexosSolicitanteDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String adjuntoDocumento;
    private String apruebaAnexo;
    private Blob documentoReal;
    private String estadoRegistro;
    private Date fechaCreacion;
    private Date fechaUltimaModificacion;
    private Long idAnxSolicitante;
    private String nombreAnexo;
    private String nombreBusqueda;
    private String razonRechazo;
    private String usuarioCreador;
    private String usuarioUltimaModificacion;
    private Long idAnexoPqr_AnexosPqr;
    private Long idSolPqr_SolicitudPqr;

    public String getAdjuntoDocumento() {
        return adjuntoDocumento;
    }

    public void setAdjuntoDocumento(String adjuntoDocumento) {
        this.adjuntoDocumento = adjuntoDocumento;
    }

    public String getApruebaAnexo() {
        return apruebaAnexo;
    }

    public void setApruebaAnexo(String apruebaAnexo) {
        this.apruebaAnexo = apruebaAnexo;
    }

    public Blob getDocumentoReal() {
        return documentoReal;
    }

    public void setDocumentoReal(Blob documentoReal) {
        this.documentoReal = documentoReal;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public Long getIdAnxSolicitante() {
        return idAnxSolicitante;
    }

    public void setIdAnxSolicitante(Long idAnxSolicitante) {
        this.idAnxSolicitante = idAnxSolicitante;
    }

    public String getNombreAnexo() {
        return nombreAnexo;
    }

    public void setNombreAnexo(String nombreAnexo) {
        this.nombreAnexo = nombreAnexo;
    }

    public String getNombreBusqueda() {
        return nombreBusqueda;
    }

    public void setNombreBusqueda(String nombreBusqueda) {
        this.nombreBusqueda = nombreBusqueda;
    }

    public String getRazonRechazo() {
        return razonRechazo;
    }

    public void setRazonRechazo(String razonRechazo) {
        this.razonRechazo = razonRechazo;
    }

    public String getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public String getUsuarioUltimaModificacion() {
        return usuarioUltimaModificacion;
    }

    public void setUsuarioUltimaModificacion(String usuarioUltimaModificacion) {
        this.usuarioUltimaModificacion = usuarioUltimaModificacion;
    }

    public Long getIdAnexoPqr_AnexosPqr() {
        return idAnexoPqr_AnexosPqr;
    }

    public void setIdAnexoPqr_AnexosPqr(Long idAnexoPqr_AnexosPqr) {
        this.idAnexoPqr_AnexosPqr = idAnexoPqr_AnexosPqr;
    }

    public Long getIdSolPqr_SolicitudPqr() {
        return idSolPqr_SolicitudPqr;
    }

    public void setIdSolPqr_SolicitudPqr(Long idSolPqr_SolicitudPqr) {
        this.idSolPqr_SolicitudPqr = idSolPqr_SolicitudPqr;
    }
}
