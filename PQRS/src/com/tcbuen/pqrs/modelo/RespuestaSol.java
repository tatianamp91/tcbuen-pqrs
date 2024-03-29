package com.tcbuen.pqrs.modelo;
// Generated 20-ago-2014 23:05:43 by Hibernate Tools 4.0.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * RespuestaSol generated by hbm2java
 */
public class RespuestaSol  implements java.io.Serializable {


     private Long idRespSol;
     private SolicitudAsignadaArea solicitudAsignadaArea;
     private String descObservacion;
     private Double valorReclamacion;
     private String estadoRegistro;
     private Date fechaCreacion;
     private String usuarioCreador;
     private Date fechaUltimaModificacion;
     private String usuarioUltimaModificacion;
     private Set<AnexosRespuesta> anexosRespuestas = new HashSet<AnexosRespuesta>(0);

    public RespuestaSol() {
    }

	
    public RespuestaSol(Long idRespSol, SolicitudAsignadaArea solicitudAsignadaArea, String estadoRegistro) {
        this.idRespSol = idRespSol;
        this.solicitudAsignadaArea = solicitudAsignadaArea;
        this.estadoRegistro = estadoRegistro;
    }
    public RespuestaSol(Long idRespSol, SolicitudAsignadaArea solicitudAsignadaArea, String descObservacion, Double valorReclamacion, String estadoRegistro, Date fechaCreacion, String usuarioCreador, Date fechaUltimaModificacion, String usuarioUltimaModificacion, Set<AnexosRespuesta> anexosRespuestas) {
       this.idRespSol = idRespSol;
       this.solicitudAsignadaArea = solicitudAsignadaArea;
       this.descObservacion = descObservacion;
       this.valorReclamacion = valorReclamacion;
       this.estadoRegistro = estadoRegistro;
       this.fechaCreacion = fechaCreacion;
       this.usuarioCreador = usuarioCreador;
       this.fechaUltimaModificacion = fechaUltimaModificacion;
       this.usuarioUltimaModificacion = usuarioUltimaModificacion;
       this.anexosRespuestas = anexosRespuestas;
    }
   
    public Long getIdRespSol() {
        return this.idRespSol;
    }
    
    public void setIdRespSol(Long idRespSol) {
        this.idRespSol = idRespSol;
    }
    public SolicitudAsignadaArea getSolicitudAsignadaArea() {
        return this.solicitudAsignadaArea;
    }
    
    public void setSolicitudAsignadaArea(SolicitudAsignadaArea solicitudAsignadaArea) {
        this.solicitudAsignadaArea = solicitudAsignadaArea;
    }
    public String getDescObservacion() {
        return this.descObservacion;
    }
    
    public void setDescObservacion(String descObservacion) {
        this.descObservacion = descObservacion;
    }
    public Double getValorReclamacion() {
        return this.valorReclamacion;
    }
    
    public void setValorReclamacion(Double valorReclamacion) {
        this.valorReclamacion = valorReclamacion;
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
    public Set<AnexosRespuesta> getAnexosRespuestas() {
        return this.anexosRespuestas;
    }
    
    public void setAnexosRespuestas(Set<AnexosRespuesta> anexosRespuestas) {
        this.anexosRespuestas = anexosRespuestas;
    }




}


