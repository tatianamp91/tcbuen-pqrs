package com.tcbuen.pqrs.modelo;
// Generated 20-ago-2014 23:05:43 by Hibernate Tools 4.0.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AnexosPqr generated by hbm2java
 */
public class AnexosPqr  implements java.io.Serializable {


     private Long idAnexoPqr;
     private String descripcionAnexo;
     private String estadoRegistro;
     private Date fechaCreacion;
     private String usuarioCreador;
     private Date fechaUltimaModificacion;
     private String usuarioUltimaModificacion;
     private Set<AnxsXTpSol> anxsXTpSols = new HashSet<AnxsXTpSol>(0);
     private Set<AnxsXArea> anxsXAreas = new HashSet<AnxsXArea>(0);
     private Set<AnexosRespuesta> anexosRespuestas = new HashSet<AnexosRespuesta>(0);
     private Set<AnexosSolicitante> anexosSolicitantes = new HashSet<AnexosSolicitante>(0);

    public AnexosPqr() {
    }

	
    public AnexosPqr(Long idAnexoPqr, String descripcionAnexo, String estadoRegistro) {
        this.idAnexoPqr = idAnexoPqr;
        this.descripcionAnexo = descripcionAnexo;
        this.estadoRegistro = estadoRegistro;
    }
    public AnexosPqr(Long idAnexoPqr, String descripcionAnexo, String estadoRegistro, Date fechaCreacion, String usuarioCreador, Date fechaUltimaModificacion, String usuarioUltimaModificacion, Set<AnxsXTpSol> anxsXTpSols, Set<AnxsXArea> anxsXAreas, Set<AnexosRespuesta> anexosRespuestas, Set<AnexosSolicitante> anexosSolicitantes) {
       this.idAnexoPqr = idAnexoPqr;
       this.descripcionAnexo = descripcionAnexo;
       this.estadoRegistro = estadoRegistro;
       this.fechaCreacion = fechaCreacion;
       this.usuarioCreador = usuarioCreador;
       this.fechaUltimaModificacion = fechaUltimaModificacion;
       this.usuarioUltimaModificacion = usuarioUltimaModificacion;
       this.anxsXTpSols = anxsXTpSols;
       this.anxsXAreas = anxsXAreas;
       this.anexosRespuestas = anexosRespuestas;
       this.anexosSolicitantes = anexosSolicitantes;
    }
   
    public Long getIdAnexoPqr() {
        return this.idAnexoPqr;
    }
    
    public void setIdAnexoPqr(Long idAnexoPqr) {
        this.idAnexoPqr = idAnexoPqr;
    }
    public String getDescripcionAnexo() {
        return this.descripcionAnexo;
    }
    
    public void setDescripcionAnexo(String descripcionAnexo) {
        this.descripcionAnexo = descripcionAnexo;
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
    public Set<AnxsXTpSol> getAnxsXTpSols() {
        return this.anxsXTpSols;
    }
    
    public void setAnxsXTpSols(Set<AnxsXTpSol> anxsXTpSols) {
        this.anxsXTpSols = anxsXTpSols;
    }
    public Set<AnxsXArea> getAnxsXAreas() {
        return this.anxsXAreas;
    }
    
    public void setAnxsXAreas(Set<AnxsXArea> anxsXAreas) {
        this.anxsXAreas = anxsXAreas;
    }
    public Set<AnexosRespuesta> getAnexosRespuestas() {
        return this.anexosRespuestas;
    }
    
    public void setAnexosRespuestas(Set<AnexosRespuesta> anexosRespuestas) {
        this.anexosRespuestas = anexosRespuestas;
    }
    public Set<AnexosSolicitante> getAnexosSolicitantes() {
        return this.anexosSolicitantes;
    }
    
    public void setAnexosSolicitantes(Set<AnexosSolicitante> anexosSolicitantes) {
        this.anexosSolicitantes = anexosSolicitantes;
    }




}


