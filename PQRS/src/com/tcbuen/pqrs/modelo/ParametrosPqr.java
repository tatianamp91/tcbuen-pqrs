package com.tcbuen.pqrs.modelo;
// Generated 20-ago-2014 23:05:43 by Hibernate Tools 4.0.0


import java.util.Date;

/**
 * ParametrosPqr generated by hbm2java
 */
public class ParametrosPqr  implements java.io.Serializable {


     private Long idParam;
     private String descripcionParam;
     private String valorParam;
     private String estadoRegistro;
     private Date fechaCreacion;
     private String usuarioCreador;
     private Date fechaUltimaModificacion;
     private String usuarioUltimaModificacion;

    public ParametrosPqr() {
    }

	
    public ParametrosPqr(Long idParam, String descripcionParam, String valorParam, String estadoRegistro) {
        this.idParam = idParam;
        this.descripcionParam = descripcionParam;
        this.valorParam = valorParam;
        this.estadoRegistro = estadoRegistro;
    }
    public ParametrosPqr(Long idParam, String descripcionParam, String valorParam, String estadoRegistro, Date fechaCreacion, String usuarioCreador, Date fechaUltimaModificacion, String usuarioUltimaModificacion) {
       this.idParam = idParam;
       this.descripcionParam = descripcionParam;
       this.valorParam = valorParam;
       this.estadoRegistro = estadoRegistro;
       this.fechaCreacion = fechaCreacion;
       this.usuarioCreador = usuarioCreador;
       this.fechaUltimaModificacion = fechaUltimaModificacion;
       this.usuarioUltimaModificacion = usuarioUltimaModificacion;
    }
   
    public Long getIdParam() {
        return this.idParam;
    }
    
    public void setIdParam(Long idParam) {
        this.idParam = idParam;
    }
    public String getDescripcionParam() {
        return this.descripcionParam;
    }
    
    public void setDescripcionParam(String descripcionParam) {
        this.descripcionParam = descripcionParam;
    }
    public String getValorParam() {
        return this.valorParam;
    }
    
    public void setValorParam(String valorParam) {
        this.valorParam = valorParam;
    }
    public String getEstadoRegistro() {
        return this.estadoRegistro;
    }
    
    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public String getUsuarioCreador() {
        return this.usuarioCreador;
    }
    
    public void setUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }
    public Date getFechaUltimaModificacion() {
        return this.fechaUltimaModificacion;
    }
    
    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }
    public String getUsuarioUltimaModificacion() {
        return this.usuarioUltimaModificacion;
    }
    
    public void setUsuarioUltimaModificacion(String usuarioUltimaModificacion) {
        this.usuarioUltimaModificacion = usuarioUltimaModificacion;
    }




}

