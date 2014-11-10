package com.tcbuen.pqrs.modelo.dto;

import java.io.Serializable;
import com.tcbuen.pqrs.modelo.SolicitudPqr;

public class SolicitudAreaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long idSolAsigArea;
    private SolicitudPqr solicitudPqr;     
    
	public SolicitudAreaDTO() {
		super();
	}
	
	public SolicitudAreaDTO(Long idSolAsigArea, SolicitudPqr solicitudPqr) {
		super();
		this.idSolAsigArea = idSolAsigArea;
		this.solicitudPqr = solicitudPqr;
	}

	public Long getIdSolAsigArea() {
		return idSolAsigArea;
	}

	public void setIdSolAsigArea(Long idSolAsigArea) {
		this.idSolAsigArea = idSolAsigArea;
	}

	public SolicitudPqr getSolicitudPqr() {
		return solicitudPqr;
	}

	public void setSolicitudPqr(SolicitudPqr solicitudPqr) {
		this.solicitudPqr = solicitudPqr;
	}
}
