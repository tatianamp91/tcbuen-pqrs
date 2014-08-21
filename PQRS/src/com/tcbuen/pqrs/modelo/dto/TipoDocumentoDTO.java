package com.tcbuen.pqrs.modelo.dto;

import com.tcbuen.pqrs.modelo.TipoDocumento;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class TipoDocumentoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String descripcionTpDoc;
    private String estadoRegistro;
    private Date fechaCreacion;
    private Date fechaUltimaModificacion;
    private Long idTpDoc;
    private String usuarioCreador;
    private String usuarioUltimaModificacion;

    public String getDescripcionTpDoc() {
        return descripcionTpDoc;
    }

    public void setDescripcionTpDoc(String descripcionTpDoc) {
        this.descripcionTpDoc = descripcionTpDoc;
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

    public Long getIdTpDoc() {
        return idTpDoc;
    }

    public void setIdTpDoc(Long idTpDoc) {
        this.idTpDoc = idTpDoc;
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
