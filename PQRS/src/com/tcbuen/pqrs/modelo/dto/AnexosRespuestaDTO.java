package com.tcbuen.pqrs.modelo.dto;

import com.tcbuen.pqrs.modelo.AnexosRespuesta;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class AnexosRespuestaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String adjuntoDocumento;
    private Blob documentoReal;
    private String estadoRegistro;
    private Date fechaCreacion;
    private Long idAnxResp;
    private String nombreAnexo;
    private String nombreBusqueda;
    private String usuarioCreador;
    private Long idAnexoPqr_AnexosPqr;
    private Long idRespSol_RespuestaSol;

    public String getAdjuntoDocumento() {
        return adjuntoDocumento;
    }

    public void setAdjuntoDocumento(String adjuntoDocumento) {
        this.adjuntoDocumento = adjuntoDocumento;
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

    public Long getIdAnxResp() {
        return idAnxResp;
    }

    public void setIdAnxResp(Long idAnxResp) {
        this.idAnxResp = idAnxResp;
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

    public String getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public Long getIdAnexoPqr_AnexosPqr() {
        return idAnexoPqr_AnexosPqr;
    }

    public void setIdAnexoPqr_AnexosPqr(Long idAnexoPqr_AnexosPqr) {
        this.idAnexoPqr_AnexosPqr = idAnexoPqr_AnexosPqr;
    }

    public Long getIdRespSol_RespuestaSol() {
        return idRespSol_RespuestaSol;
    }

    public void setIdRespSol_RespuestaSol(Long idRespSol_RespuestaSol) {
        this.idRespSol_RespuestaSol = idRespSol_RespuestaSol;
    }
}
