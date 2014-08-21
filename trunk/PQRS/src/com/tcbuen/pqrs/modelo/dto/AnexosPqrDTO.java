package com.tcbuen.pqrs.modelo.dto;

import com.tcbuen.pqrs.modelo.AnexosPqr;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class AnexosPqrDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String descripcionAnexo;
    private String estadoRegistro;
    private Date fechaCreacion;
    private Date fechaUltimaModificacion;
    private Long idAnexoPqr;
    private String usuarioCreador;
    private String usuarioUltimaModificacion;

    public String getDescripcionAnexo() {
        return descripcionAnexo;
    }

    public void setDescripcionAnexo(String descripcionAnexo) {
        this.descripcionAnexo = descripcionAnexo;
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

    public Long getIdAnexoPqr() {
        return idAnexoPqr;
    }

    public void setIdAnexoPqr(Long idAnexoPqr) {
        this.idAnexoPqr = idAnexoPqr;
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
