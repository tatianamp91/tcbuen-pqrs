package com.tcbuen.pqrs.modelo;
// Generated 20-ago-2014 23:05:43 by Hibernate Tools 4.0.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AreasInvolucradas generated by hbm2java
 */
public class AreasInvolucradas  implements java.io.Serializable {


     private Long idAreaInvolucrada;
     private String nombreArea;
     private String estadoRegistro;
     private Date fechaCreacion;
     private String usuarioCreador;
     private Date fechaUltimaModificacion;
     private String usuarioUltimaModificacion;
     private Set<SolicitudAsignadaArea> solicitudAsignadaAreas = new HashSet<SolicitudAsignadaArea>(0);
     private Set<UsuariosInternos> usuariosInternoses = new HashSet<UsuariosInternos>(0);
     private Set<AnxsXArea> anxsXAreas = new HashSet<AnxsXArea>(0);

    public AreasInvolucradas() {
    }

	
    public AreasInvolucradas(Long idAreaInvolucrada, String nombreArea, String estadoRegistro) {
        this.idAreaInvolucrada = idAreaInvolucrada;
        this.nombreArea = nombreArea;
        this.estadoRegistro = estadoRegistro;
    }
    public AreasInvolucradas(Long idAreaInvolucrada, String nombreArea, String estadoRegistro, Date fechaCreacion, String usuarioCreador, Date fechaUltimaModificacion, String usuarioUltimaModificacion, Set<SolicitudAsignadaArea> solicitudAsignadaAreas, Set<UsuariosInternos> usuariosInternoses, Set<AnxsXArea> anxsXAreas) {
       this.idAreaInvolucrada = idAreaInvolucrada;
       this.nombreArea = nombreArea;
       this.estadoRegistro = estadoRegistro;
       this.fechaCreacion = fechaCreacion;
       this.usuarioCreador = usuarioCreador;
       this.fechaUltimaModificacion = fechaUltimaModificacion;
       this.usuarioUltimaModificacion = usuarioUltimaModificacion;
       this.solicitudAsignadaAreas = solicitudAsignadaAreas;
       this.usuariosInternoses = usuariosInternoses;
       this.anxsXAreas = anxsXAreas;
    }
   
    public Long getIdAreaInvolucrada() {
        return this.idAreaInvolucrada;
    }
    
    public void setIdAreaInvolucrada(Long idAreaInvolucrada) {
        this.idAreaInvolucrada = idAreaInvolucrada;
    }
    public String getNombreArea() {
        return this.nombreArea;
    }
    
    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
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
    public Set<SolicitudAsignadaArea> getSolicitudAsignadaAreas() {
        return this.solicitudAsignadaAreas;
    }
    
    public void setSolicitudAsignadaAreas(Set<SolicitudAsignadaArea> solicitudAsignadaAreas) {
        this.solicitudAsignadaAreas = solicitudAsignadaAreas;
    }
    public Set<UsuariosInternos> getUsuariosInternoses() {
        return this.usuariosInternoses;
    }
    
    public void setUsuariosInternoses(Set<UsuariosInternos> usuariosInternoses) {
        this.usuariosInternoses = usuariosInternoses;
    }
    public Set<AnxsXArea> getAnxsXAreas() {
        return this.anxsXAreas;
    }
    
    public void setAnxsXAreas(Set<AnxsXArea> anxsXAreas) {
        this.anxsXAreas = anxsXAreas;
    }




}


