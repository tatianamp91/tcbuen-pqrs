package com.tcbuen.pqrs.modelo.dto;

import com.tcbuen.pqrs.modelo.MotReclXTpSol;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class MotReclXTpSolDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long idMotReclXTpSol;
    private Long idMotRecl_MotivoReclamacion;
    private Long idTpSolPqr_TipoSolicitudPqr;

    public Long getIdMotReclXTpSol() {
        return idMotReclXTpSol;
    }

    public void setIdMotReclXTpSol(Long idMotReclXTpSol) {
        this.idMotReclXTpSol = idMotReclXTpSol;
    }

    public Long getIdMotRecl_MotivoReclamacion() {
        return idMotRecl_MotivoReclamacion;
    }

    public void setIdMotRecl_MotivoReclamacion(Long idMotRecl_MotivoReclamacion) {
        this.idMotRecl_MotivoReclamacion = idMotRecl_MotivoReclamacion;
    }

    public Long getIdTpSolPqr_TipoSolicitudPqr() {
        return idTpSolPqr_TipoSolicitudPqr;
    }

    public void setIdTpSolPqr_TipoSolicitudPqr(Long idTpSolPqr_TipoSolicitudPqr) {
        this.idTpSolPqr_TipoSolicitudPqr = idTpSolPqr_TipoSolicitudPqr;
    }
}
