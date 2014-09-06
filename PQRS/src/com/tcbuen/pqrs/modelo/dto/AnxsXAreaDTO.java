package com.tcbuen.pqrs.modelo.dto;

import com.tcbuen.pqrs.modelo.AnxsXArea;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
*
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public class AnxsXAreaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String esObligatorio;
    private Long idAnxXArea;
    private Long idAnexoPqr_AnexosPqr;
    private Long idAreaInvolucrada_AreasInvolucradas;

    public String getEsObligatorio() {
        if(esObligatorio.equals("S")){
        	esObligatorio = "Si";
        }else{
        	esObligatorio ="No";
        }
        return esObligatorio;
    }

    public void setEsObligatorio(String esObligatorio) {
        this.esObligatorio = esObligatorio;
    }

    public Long getIdAnxXArea() {
        return idAnxXArea;
    }

    public void setIdAnxXArea(Long idAnxXArea) {
        this.idAnxXArea = idAnxXArea;
    }

    public Long getIdAnexoPqr_AnexosPqr() {
        return idAnexoPqr_AnexosPqr;
    }

    public void setIdAnexoPqr_AnexosPqr(Long idAnexoPqr_AnexosPqr) {
        this.idAnexoPqr_AnexosPqr = idAnexoPqr_AnexosPqr;
    }

    public Long getIdAreaInvolucrada_AreasInvolucradas() {
        return idAreaInvolucrada_AreasInvolucradas;
    }

    public void setIdAreaInvolucrada_AreasInvolucradas(
        Long idAreaInvolucrada_AreasInvolucradas) {
        this.idAreaInvolucrada_AreasInvolucradas = idAreaInvolucrada_AreasInvolucradas;
    }
}
