package com.tcbuen.pqrs.modelo;
// Generated 20-ago-2014 23:05:43 by Hibernate Tools 4.0.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SolicitudPqr generated by hbm2java
 */
public class SolicitudPqr  implements java.io.Serializable {


     private Long idSolPqr;
     private TipoSolicitudPqr tipoSolicitudPqr;
     private InfoSolicitante infoSolicitante;
     private TipoEstadoPqr tipoEstadoPqr;
     private String numeroRadicacion;
     private String nombreCliente;
     private String nombreAgenciaAduana;
     private String descripcionCaso;
     private String solicitudARealizar;
     private Date fechaCreacion;
     private String usuarioCreador;
     private Date fechaUltimaModificacion;
     private String usuarioUltimaModificacion;
     private Set<MotSolSelect> motSolSelects = new HashSet<MotSolSelect>(0);
     private Set<MotReclSelect> motReclSelects = new HashSet<MotReclSelect>(0);
     private Set<SolicitudAsignadaArea> solicitudAsignadaAreas = new HashSet<SolicitudAsignadaArea>(0);
     private Set<AnexosSolicitante> anexosSolicitantes = new HashSet<AnexosSolicitante>(0);

    public SolicitudPqr() {
    }

	
    public SolicitudPqr(Long idSolPqr, TipoSolicitudPqr tipoSolicitudPqr, InfoSolicitante infoSolicitante, TipoEstadoPqr tipoEstadoPqr, String numeroRadicacion, String descripcionCaso, Date fechaCreacion) {
        this.idSolPqr = idSolPqr;
        this.tipoSolicitudPqr = tipoSolicitudPqr;
        this.infoSolicitante = infoSolicitante;
        this.tipoEstadoPqr = tipoEstadoPqr;
        this.numeroRadicacion = numeroRadicacion;
        this.descripcionCaso = descripcionCaso;
        this.fechaCreacion = fechaCreacion;
    }
    public SolicitudPqr(Long idSolPqr, TipoSolicitudPqr tipoSolicitudPqr, InfoSolicitante infoSolicitante, TipoEstadoPqr tipoEstadoPqr, String numeroRadicacion, String nombreCliente, String nombreAgenciaAduana, String descripcionCaso, String solicitudARealizar, Date fechaCreacion, String usuarioCreador, Date fechaUltimaModificacion, String usuarioUltimaModificacion, Set<MotSolSelect> motSolSelects, Set<MotReclSelect> motReclSelects, Set<SolicitudAsignadaArea> solicitudAsignadaAreas, Set<AnexosSolicitante> anexosSolicitantes) {
       this.idSolPqr = idSolPqr;
       this.tipoSolicitudPqr = tipoSolicitudPqr;
       this.infoSolicitante = infoSolicitante;
       this.tipoEstadoPqr = tipoEstadoPqr;
       this.numeroRadicacion = numeroRadicacion;
       this.nombreCliente = nombreCliente;
       this.nombreAgenciaAduana = nombreAgenciaAduana;
       this.descripcionCaso = descripcionCaso;
       this.solicitudARealizar = solicitudARealizar;
       this.fechaCreacion = fechaCreacion;
       this.usuarioCreador = usuarioCreador;
       this.fechaUltimaModificacion = fechaUltimaModificacion;
       this.usuarioUltimaModificacion = usuarioUltimaModificacion;
       this.motSolSelects = motSolSelects;
       this.motReclSelects = motReclSelects;
       this.solicitudAsignadaAreas = solicitudAsignadaAreas;
       this.anexosSolicitantes = anexosSolicitantes;
    }
   
    public Long getIdSolPqr() {
        return this.idSolPqr;
    }
    
    public void setIdSolPqr(Long idSolPqr) {
        this.idSolPqr = idSolPqr;
    }
    public TipoSolicitudPqr getTipoSolicitudPqr() {
        return this.tipoSolicitudPqr;
    }
    
    public void setTipoSolicitudPqr(TipoSolicitudPqr tipoSolicitudPqr) {
        this.tipoSolicitudPqr = tipoSolicitudPqr;
    }
    public InfoSolicitante getInfoSolicitante() {
        return this.infoSolicitante;
    }
    
    public void setInfoSolicitante(InfoSolicitante infoSolicitante) {
        this.infoSolicitante = infoSolicitante;
    }
    public TipoEstadoPqr getTipoEstadoPqr() {
        return this.tipoEstadoPqr;
    }
    
    public void setTipoEstadoPqr(TipoEstadoPqr tipoEstadoPqr) {
        this.tipoEstadoPqr = tipoEstadoPqr;
    }
    public String getNumeroRadicacion() {
        return this.numeroRadicacion;
    }
    
    public void setNumeroRadicacion(String numeroRadicacion) {
        this.numeroRadicacion = numeroRadicacion;
    }
    public String getNombreCliente() {
        return this.nombreCliente;
    }
    
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    public String getNombreAgenciaAduana() {
        return this.nombreAgenciaAduana;
    }
    
    public void setNombreAgenciaAduana(String nombreAgenciaAduana) {
        this.nombreAgenciaAduana = nombreAgenciaAduana;
    }
    public String getDescripcionCaso() {
        return this.descripcionCaso;
    }
    
    public void setDescripcionCaso(String descripcionCaso) {
        this.descripcionCaso = descripcionCaso;
    }
    public String getSolicitudARealizar() {
        return this.solicitudARealizar;
    }
    
    public void setSolicitudARealizar(String solicitudARealizar) {
        this.solicitudARealizar = solicitudARealizar;
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
    public Set<MotSolSelect> getMotSolSelects() {
        return this.motSolSelects;
    }
    
    public void setMotSolSelects(Set<MotSolSelect> motSolSelects) {
        this.motSolSelects = motSolSelects;
    }
    public Set<MotReclSelect> getMotReclSelects() {
        return this.motReclSelects;
    }
    
    public void setMotReclSelects(Set<MotReclSelect> motReclSelects) {
        this.motReclSelects = motReclSelects;
    }
    public Set<SolicitudAsignadaArea> getSolicitudAsignadaAreas() {
        return this.solicitudAsignadaAreas;
    }
    
    public void setSolicitudAsignadaAreas(Set<SolicitudAsignadaArea> solicitudAsignadaAreas) {
        this.solicitudAsignadaAreas = solicitudAsignadaAreas;
    }
    public Set<AnexosSolicitante> getAnexosSolicitantes() {
        return this.anexosSolicitantes;
    }
    
    public void setAnexosSolicitantes(Set<AnexosSolicitante> anexosSolicitantes) {
        this.anexosSolicitantes = anexosSolicitantes;
    }




}

