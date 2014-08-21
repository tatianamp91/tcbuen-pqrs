package com.tcbuen.pqrs.modelo.dto;

import com.tcbuen.pqrs.modelo.AnxsXTpSol;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class AnxsXTpSolDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String esObligatorio;
    private Long idAnexoXTpSol;
    private Long idAnexoPqr_AnexosPqr;
    private Long idTpSolPqr_TipoSolicitudPqr;

    public String getEsObligatorio() {
        return esObligatorio;
    }

    public void setEsObligatorio(String esObligatorio) {
        this.esObligatorio = esObligatorio;
    }

    public Long getIdAnexoXTpSol() {
        return idAnexoXTpSol;
    }

    public void setIdAnexoXTpSol(Long idAnexoXTpSol) {
        this.idAnexoXTpSol = idAnexoXTpSol;
    }

    public Long getIdAnexoPqr_AnexosPqr() {
        return idAnexoPqr_AnexosPqr;
    }

    public void setIdAnexoPqr_AnexosPqr(Long idAnexoPqr_AnexosPqr) {
        this.idAnexoPqr_AnexosPqr = idAnexoPqr_AnexosPqr;
    }

    public Long getIdTpSolPqr_TipoSolicitudPqr() {
        return idTpSolPqr_TipoSolicitudPqr;
    }

    public void setIdTpSolPqr_TipoSolicitudPqr(Long idTpSolPqr_TipoSolicitudPqr) {
        this.idTpSolPqr_TipoSolicitudPqr = idTpSolPqr_TipoSolicitudPqr;
    }
}
