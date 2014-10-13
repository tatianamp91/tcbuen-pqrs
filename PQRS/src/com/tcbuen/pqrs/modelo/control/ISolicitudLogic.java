package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.InfoSolicitante;
import com.tcbuen.pqrs.modelo.MotReclSelect;
import com.tcbuen.pqrs.modelo.MotSolSelect;
import com.tcbuen.pqrs.modelo.SolicitudAsignadaArea;
import com.tcbuen.pqrs.modelo.SolicitudPqr;


public interface ISolicitudLogic {

    public void saveSolicitudPqr(InfoSolicitante infoSol, SolicitudPqr solicitudPqr, 
    		MotSolSelect motSolSelect, MotReclSelect motReclSelect, 
    		SolicitudAsignadaArea solicitudAsignadaArea) throws Exception;
}
