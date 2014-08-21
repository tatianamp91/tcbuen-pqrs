package com.tcbuen.pqrs.modelo.dto;

import com.tcbuen.pqrs.modelo.RespuestaSol;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class RespuestaSolDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String descObservacion;
    private String estadoRegistro;
    private Date fechaCreacion;
    private Date fechaUltimaModificacion;
    private Long idRespSol;
    private String usuarioCreador;
    private String usuarioUltimaModificacion;
    private Double valorReclamacion;
    private Long idSolAsigArea_SolicitudAsignadaArea;

    public String getDescObservacion() {
        return descObservacion;
    }

    public void setDescObservacion(String descObservacion) {
        this.descObservacion = descObservacion;
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

    public Long getIdRespSol() {
        return idRespSol;
    }

    public void setIdRespSol(Long idRespSol) {
        this.idRespSol = idRespSol;
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

    public Double getValorReclamacion() {
        return valorReclamacion;
    }

    public void setValorReclamacion(Double valorReclamacion) {
        this.valorReclamacion = valorReclamacion;
    }

    public Long getIdSolAsigArea_SolicitudAsignadaArea() {
        return idSolAsigArea_SolicitudAsignadaArea;
    }

    public void setIdSolAsigArea_SolicitudAsignadaArea(
        Long idSolAsigArea_SolicitudAsignadaArea) {
        this.idSolAsigArea_SolicitudAsignadaArea = idSolAsigArea_SolicitudAsignadaArea;
    }
}
