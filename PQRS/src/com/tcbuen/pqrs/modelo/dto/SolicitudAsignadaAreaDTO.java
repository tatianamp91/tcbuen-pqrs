package com.tcbuen.pqrs.modelo.dto;

import com.tcbuen.pqrs.modelo.SolicitudAsignadaArea;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class SolicitudAsignadaAreaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date fechaAsignacion;
    private Date fechaRespuesta;
    private Long idSolAsigArea;
    private Long idAreaInvolucrada_AreasInvolucradas;
    private Long idSolPqr_SolicitudPqr;

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public Long getIdSolAsigArea() {
        return idSolAsigArea;
    }

    public void setIdSolAsigArea(Long idSolAsigArea) {
        this.idSolAsigArea = idSolAsigArea;
    }

    public Long getIdAreaInvolucrada_AreasInvolucradas() {
        return idAreaInvolucrada_AreasInvolucradas;
    }

    public void setIdAreaInvolucrada_AreasInvolucradas(
        Long idAreaInvolucrada_AreasInvolucradas) {
        this.idAreaInvolucrada_AreasInvolucradas = idAreaInvolucrada_AreasInvolucradas;
    }

    public Long getIdSolPqr_SolicitudPqr() {
        return idSolPqr_SolicitudPqr;
    }

    public void setIdSolPqr_SolicitudPqr(Long idSolPqr_SolicitudPqr) {
        this.idSolPqr_SolicitudPqr = idSolPqr_SolicitudPqr;
    }
}
