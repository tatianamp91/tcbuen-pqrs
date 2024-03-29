package com.tcbuen.pqrs.modelo;
// Generated 20-ago-2014 23:05:43 by Hibernate Tools 4.0.0


import java.sql.Blob;
import java.util.Date;

/**
 * AnexosSolicitante generated by hbm2java
 */
public class AnexosSolicitante  implements java.io.Serializable {


     private Long idAnxSolicitante;
     private SolicitudPqr solicitudPqr;
     private AnexosPqr anexosPqr;
     private Blob documentoReal;
     private String nombreAnexo;
     private String nombreBusqueda;
     private String razonRechazo;
     private String apruebaAnexo;
     private String adjuntoDocumento;
     private String estadoRegistro;
     private Date fechaCreacion;
     private String usuarioCreador;
     private Date fechaUltimaModificacion;
     private String usuarioUltimaModificacion;

    public AnexosSolicitante() {
    }

	
    public AnexosSolicitante(Long idAnxSolicitante, SolicitudPqr solicitudPqr, AnexosPqr anexosPqr, String nombreAnexo, String nombreBusqueda, String estadoRegistro) {
        this.idAnxSolicitante = idAnxSolicitante;
        this.solicitudPqr = solicitudPqr;
        this.anexosPqr = anexosPqr;
        this.nombreAnexo = nombreAnexo;
        this.nombreBusqueda = nombreBusqueda;
        this.estadoRegistro = estadoRegistro;
    }
    public AnexosSolicitante(Long idAnxSolicitante, SolicitudPqr solicitudPqr, AnexosPqr anexosPqr, Blob documentoReal, String nombreAnexo, String nombreBusqueda, String razonRechazo, String apruebaAnexo, String adjuntoDocumento, String estadoRegistro, Date fechaCreacion, String usuarioCreador, Date fechaUltimaModificacion, String usuarioUltimaModificacion) {
       this.idAnxSolicitante = idAnxSolicitante;
       this.solicitudPqr = solicitudPqr;
       this.anexosPqr = anexosPqr;
       this.documentoReal = documentoReal;
       this.nombreAnexo = nombreAnexo;
       this.nombreBusqueda = nombreBusqueda;
       this.razonRechazo = razonRechazo;
       this.apruebaAnexo = apruebaAnexo;
       this.adjuntoDocumento = adjuntoDocumento;
       this.estadoRegistro = estadoRegistro;
       this.fechaCreacion = fechaCreacion;
       this.usuarioCreador = usuarioCreador;
       this.fechaUltimaModificacion = fechaUltimaModificacion;
       this.usuarioUltimaModificacion = usuarioUltimaModificacion;
    }
   
    public Long getIdAnxSolicitante() {
        return this.idAnxSolicitante;
    }
    
    public void setIdAnxSolicitante(Long idAnxSolicitante) {
        this.idAnxSolicitante = idAnxSolicitante;
    }
    public SolicitudPqr getSolicitudPqr() {
        return this.solicitudPqr;
    }
    
    public void setSolicitudPqr(SolicitudPqr solicitudPqr) {
        this.solicitudPqr = solicitudPqr;
    }
    public AnexosPqr getAnexosPqr() {
        return this.anexosPqr;
    }
    
    public void setAnexosPqr(AnexosPqr anexosPqr) {
        this.anexosPqr = anexosPqr;
    }
    public Blob getDocumentoReal() {
        return this.documentoReal;
    }
    
    public void setDocumentoReal(Blob documentoReal) {
        this.documentoReal = documentoReal;
    }
    public String getNombreAnexo() {
        return this.nombreAnexo;
    }
    
    public void setNombreAnexo(String nombreAnexo) {
        this.nombreAnexo = nombreAnexo;
    }
    public String getNombreBusqueda() {
        return this.nombreBusqueda;
    }
    
    public void setNombreBusqueda(String nombreBusqueda) {
        this.nombreBusqueda = nombreBusqueda;
    }
    public String getRazonRechazo() {
        return this.razonRechazo;
    }
    
    public void setRazonRechazo(String razonRechazo) {
        this.razonRechazo = razonRechazo;
    }
    public String getApruebaAnexo() {
        return this.apruebaAnexo;
    }
    
    public void setApruebaAnexo(String apruebaAnexo) {
        this.apruebaAnexo = apruebaAnexo;
    }
    public String getAdjuntoDocumento() {
        return this.adjuntoDocumento;
    }
    
    public void setAdjuntoDocumento(String adjuntoDocumento) {
        this.adjuntoDocumento = adjuntoDocumento;
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


