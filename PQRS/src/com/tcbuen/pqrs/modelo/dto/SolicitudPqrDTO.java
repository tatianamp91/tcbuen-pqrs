package com.tcbuen.pqrs.modelo.dto;

import com.tcbuen.pqrs.modelo.SolicitudPqr;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class SolicitudPqrDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String descripcionCaso;
    private Date fechaCreacion;
    private Date fechaUltimaModificacion;
    private Long idSolPqr;
    private String nombreAgenciaAduana;
    private String nombreCliente;
    private String numeroRadicacion;
    private String solicitudARealizar;
    private String usuarioCreador;
    private String usuarioUltimaModificacion;
    private Long idInfoSolicitante_InfoSolicitante;
    private Long idTpEstPqr_TipoEstadoPqr;
    private Long idTpSolPqr_TipoSolicitudPqr;

    public String getDescripcionCaso() {
        return descripcionCaso;
    }

    public void setDescripcionCaso(String descripcionCaso) {
        this.descripcionCaso = descripcionCaso;
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

    public Long getIdSolPqr() {
        return idSolPqr;
    }

    public void setIdSolPqr(Long idSolPqr) {
        this.idSolPqr = idSolPqr;
    }

    public String getNombreAgenciaAduana() {
        return nombreAgenciaAduana;
    }

    public void setNombreAgenciaAduana(String nombreAgenciaAduana) {
        this.nombreAgenciaAduana = nombreAgenciaAduana;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNumeroRadicacion() {
        return numeroRadicacion;
    }

    public void setNumeroRadicacion(String numeroRadicacion) {
        this.numeroRadicacion = numeroRadicacion;
    }

    public String getSolicitudARealizar() {
        return solicitudARealizar;
    }

    public void setSolicitudARealizar(String solicitudARealizar) {
        this.solicitudARealizar = solicitudARealizar;
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

    public Long getIdInfoSolicitante_InfoSolicitante() {
        return idInfoSolicitante_InfoSolicitante;
    }

    public void setIdInfoSolicitante_InfoSolicitante(
        Long idInfoSolicitante_InfoSolicitante) {
        this.idInfoSolicitante_InfoSolicitante = idInfoSolicitante_InfoSolicitante;
    }

    public Long getIdTpEstPqr_TipoEstadoPqr() {
        return idTpEstPqr_TipoEstadoPqr;
    }

    public void setIdTpEstPqr_TipoEstadoPqr(Long idTpEstPqr_TipoEstadoPqr) {
        this.idTpEstPqr_TipoEstadoPqr = idTpEstPqr_TipoEstadoPqr;
    }

    public Long getIdTpSolPqr_TipoSolicitudPqr() {
        return idTpSolPqr_TipoSolicitudPqr;
    }

    public void setIdTpSolPqr_TipoSolicitudPqr(Long idTpSolPqr_TipoSolicitudPqr) {
        this.idTpSolPqr_TipoSolicitudPqr = idTpSolPqr_TipoSolicitudPqr;
    }
}
