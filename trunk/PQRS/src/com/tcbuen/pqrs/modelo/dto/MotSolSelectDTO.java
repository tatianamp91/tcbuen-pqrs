package com.tcbuen.pqrs.modelo.dto;

import com.tcbuen.pqrs.modelo.MotSolSelect;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class MotSolSelectDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long idMotSolSelected;
    private Long idMotSol_MotivoSolicitud;
    private Long idSolPqr_SolicitudPqr;

    public Long getIdMotSolSelected() {
        return idMotSolSelected;
    }

    public void setIdMotSolSelected(Long idMotSolSelected) {
        this.idMotSolSelected = idMotSolSelected;
    }

    public Long getIdMotSol_MotivoSolicitud() {
        return idMotSol_MotivoSolicitud;
    }

    public void setIdMotSol_MotivoSolicitud(Long idMotSol_MotivoSolicitud) {
        this.idMotSol_MotivoSolicitud = idMotSol_MotivoSolicitud;
    }

    public Long getIdSolPqr_SolicitudPqr() {
        return idSolPqr_SolicitudPqr;
    }

    public void setIdSolPqr_SolicitudPqr(Long idSolPqr_SolicitudPqr) {
        this.idSolPqr_SolicitudPqr = idSolPqr_SolicitudPqr;
    }
}
