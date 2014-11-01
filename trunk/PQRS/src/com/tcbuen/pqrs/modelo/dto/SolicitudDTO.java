package com.tcbuen.pqrs.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import com.tcbuen.pqrs.modelo.TipoEstadoPqr;
import com.tcbuen.pqrs.modelo.TipoSolicitudPqr;

public class SolicitudDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long idSolPqr;
    private String numeroRadicacion;
    private Date fechaRadicacion;
    private Date fechaAsignacion;
    private TipoSolicitudPqr tipoSolicitudPqr;
    
	public Long getIdSolPqr() {
		return idSolPqr;
	}
	public void setIdSolPqr(Long idSolPqr) {
		this.idSolPqr = idSolPqr;
	}
	public String getNumeroRadicacion() {
		return numeroRadicacion;
	}
	public void setNumeroRadicacion(String numeroRadicacion) {
		this.numeroRadicacion = numeroRadicacion;
	}
	public Date getFechaRadicacion() {
		return fechaRadicacion;
	}
	public void setFechaRadicacion(Date fechaRadicacion) {
		this.fechaRadicacion = fechaRadicacion;
	}
	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}
	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}
	public TipoSolicitudPqr getTipoSolicitudPqr() {
		return tipoSolicitudPqr;
	}
	public void setTipoSolicitudPqr(TipoSolicitudPqr tipoSolicitudPqr) {
		this.tipoSolicitudPqr = tipoSolicitudPqr;
	}
}
