package com.tcbuen.pqrs.modelo.dto;

import com.tcbuen.pqrs.modelo.TipoSolicitudPqr;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class TipoSolicitudPqrDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String descTpSol;
    private String estadoRegistro;
    private Date fechaCreacion;
    private Date fechaUltimaModificacion;
    private Long idTpSolPqr;
    private String usuarioCreador;
    private String usuarioUltimaModificacion;

    public String getDescTpSol() {
        return descTpSol;
    }

    public void setDescTpSol(String descTpSol) {
        this.descTpSol = descTpSol;
    }

    public String getEstadoRegistro() {
        if(estadoRegistro.equals("A")){
        	estadoRegistro = "Activo";
        }else{
        	estadoRegistro ="Inactivo";
        }
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

    public Long getIdTpSolPqr() {
        return idTpSolPqr;
    }

    public void setIdTpSolPqr(Long idTpSolPqr) {
        this.idTpSolPqr = idTpSolPqr;
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
}
