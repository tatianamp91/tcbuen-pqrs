package com.tcbuen.pqrs.modelo.dto;

import java.io.Serializable;
import java.util.Date;


public class EstadisticasDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    private String numeroRadicacion;
    private String descripcionMotRecl;
    private String descripcionEstado;
    private String nombreArea;
    private Date fechaCreacion;
    private Date fechaRespuesta;
    
    
	public EstadisticasDTO() {
		super();
	}
    
	public EstadisticasDTO(String numeroRadicacion,
			String descripcionMotRecl,Date fechaCreacion, String descripcionEstado,
			String nombreArea, Date fechaRespuesta) {
		super();
		
		this.numeroRadicacion = numeroRadicacion;
		this.descripcionMotRecl = descripcionMotRecl;
		this.fechaCreacion = fechaCreacion;
		this.descripcionEstado = descripcionEstado;
		this.nombreArea = nombreArea;		
		this.fechaRespuesta = fechaRespuesta;
	}	


	public String getNumeroRadicacion() {
		return numeroRadicacion;
	}
	public void setNumeroRadicacion(String numeroRadicacion) {
		this.numeroRadicacion = numeroRadicacion;
	}
	public String getDescripcionMotRecl() {
		return descripcionMotRecl;
	}
	public void setDescripcionMotRecl(String descripcionMotRecl) {
		this.descripcionMotRecl = descripcionMotRecl;
	}
	public String getDescripcionEstado() {
		return descripcionEstado;
	}
	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}
	public String getNombreArea() {
		return nombreArea;
	}
	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaRespuesta() {
		return fechaRespuesta;
	}
	public void setFechaRespuesta(Date fechaRespuesta) {
		this.fechaRespuesta = fechaRespuesta;
	}	
}
