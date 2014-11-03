package com.tcbuen.pqrs.modelo.control;

import java.util.List;

import com.tcbuen.pqrs.modelo.AnexosSolicitante;
import com.tcbuen.pqrs.modelo.AreasInvolucradas;
import com.tcbuen.pqrs.modelo.InfoSolicitante;
import com.tcbuen.pqrs.modelo.MotReclSelect;
import com.tcbuen.pqrs.modelo.MotSolSelect;
import com.tcbuen.pqrs.modelo.SolicitudAsignadaArea;
import com.tcbuen.pqrs.modelo.SolicitudPqr;
import com.tcbuen.pqrs.modelo.dto.SolicitudDTO;


public interface ISolicitudLogic {

    public void saveSolicitudPqr(InfoSolicitante infoSol, SolicitudPqr solicitudPqr, 
    		MotSolSelect motSolSelect, MotReclSelect motReclSelect, 
    		SolicitudAsignadaArea solicitudAsignadaArea, 
    		List<AnexosSolicitante> anexosSolicitantes) throws Exception;
    
    public List<SolicitudPqr> consultarSolicitudes(Long idAreaInvolucrada) throws Exception;
    
    public List<SolicitudDTO> consultarAsignacion(AreasInvolucradas area) throws Exception;
}
