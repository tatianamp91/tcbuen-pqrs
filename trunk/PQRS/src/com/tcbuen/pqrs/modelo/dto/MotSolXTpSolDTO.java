package com.tcbuen.pqrs.modelo.dto;

import com.tcbuen.pqrs.modelo.MotSolXTpSol;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class MotSolXTpSolDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long idMotSolXTpSol;
    private Long idMotSol_MotivoSolicitud;
    private Long idTpSolPqr_TipoSolicitudPqr;

    public Long getIdMotSolXTpSol() {
        return idMotSolXTpSol;
    }

    public void setIdMotSolXTpSol(Long idMotSolXTpSol) {
        this.idMotSolXTpSol = idMotSolXTpSol;
    }

    public Long getIdMotSol_MotivoSolicitud() {
        return idMotSol_MotivoSolicitud;
    }

    public void setIdMotSol_MotivoSolicitud(Long idMotSol_MotivoSolicitud) {
        this.idMotSol_MotivoSolicitud = idMotSol_MotivoSolicitud;
    }

    public Long getIdTpSolPqr_TipoSolicitudPqr() {
        return idTpSolPqr_TipoSolicitudPqr;
    }

    public void setIdTpSolPqr_TipoSolicitudPqr(Long idTpSolPqr_TipoSolicitudPqr) {
        this.idTpSolPqr_TipoSolicitudPqr = idTpSolPqr_TipoSolicitudPqr;
    }
}
