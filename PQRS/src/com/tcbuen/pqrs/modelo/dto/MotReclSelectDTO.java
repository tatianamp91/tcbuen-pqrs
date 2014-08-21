package com.tcbuen.pqrs.modelo.dto;

import com.tcbuen.pqrs.modelo.MotReclSelect;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class MotReclSelectDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long idMotReclSelect;
    private Long idMotRecl_MotivoReclamacion;
    private Long idSolPqr_SolicitudPqr;

    public Long getIdMotReclSelect() {
        return idMotReclSelect;
    }

    public void setIdMotReclSelect(Long idMotReclSelect) {
        this.idMotReclSelect = idMotReclSelect;
    }

    public Long getIdMotRecl_MotivoReclamacion() {
        return idMotRecl_MotivoReclamacion;
    }

    public void setIdMotRecl_MotivoReclamacion(Long idMotRecl_MotivoReclamacion) {
        this.idMotRecl_MotivoReclamacion = idMotRecl_MotivoReclamacion;
    }

    public Long getIdSolPqr_SolicitudPqr() {
        return idSolPqr_SolicitudPqr;
    }

    public void setIdSolPqr_SolicitudPqr(Long idSolPqr_SolicitudPqr) {
        this.idSolPqr_SolicitudPqr = idSolPqr_SolicitudPqr;
    }
}
