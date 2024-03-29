package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.dataaccess.dao.*;
import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.SolicitudDTO;
import com.tcbuen.pqrs.modelo.dto.SolicitudPqrDTO;
import com.tcbuen.pqrs.utilities.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Scope("singleton")
@Service("SolicitudLogic")
public class SolicitudLogic implements ISolicitudLogic {

    @Autowired
    private ISolicitudPqrDAO solicitudPqrDAO;
    @Autowired
    private IAnexosSolicitanteDAO anexosSolicitanteDAO;
    @Autowired
    private IMotReclSelectDAO motReclSelectDAO;
    @Autowired
    private IMotSolSelectDAO motSolSelectDAO;
    @Autowired
    private ISolicitudAsignadaAreaDAO solicitudAsignadaAreaDAO;
    @Autowired
    IInfoSolicitanteLogic logicInfoSolicitante;
    @Autowired
    ITipoEstadoPqrLogic logicTipoEstadoPqr;
    @Autowired
    ITipoSolicitudPqrLogic logicTipoSolicitudPqr;
    @Autowired
    ISolicitudPqrLogic logicSolicitudPqr;
    @Autowired
    IMotSolSelectLogic logicMotSolSelect;
    @Autowired
    IMotReclSelectLogic logicMotReclSelect;
    @Autowired
    ISolicitudAsignadaAreaLogic logicSolicitudAsignadaArea;
    @Autowired
    IAnexosSolicitanteLogic logicAnexosSolicitante;
    
    @Autowired
    IRespuestaSolLogic respuestaSolLogic;
    @Autowired
    ISolicitudAsignadaAreaLogic solicitudAsignadaAreaLogic;
    @Autowired
    IAnexosRespuestaLogic anexosRespuestaLogic;
    

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveSolicitudPqr(InfoSolicitante infoSol, SolicitudPqr solicitudPqr, 
    		MotSolSelect motSolSelect, MotReclSelect motReclSelect, 
    		SolicitudAsignadaArea solicitudAsignadaArea, 
    		List<AnexosSolicitante> anexosSolicitantes) throws Exception {
        try {
        	
            logicInfoSolicitante.saveInfoSolicitante(infoSol);
            
            solicitudPqr.setInfoSolicitante(infoSol);
            logicSolicitudPqr.saveSolicitudPqr(solicitudPqr);
            
            if(motSolSelect != null){
            	motSolSelect.setSolicitudPqr(solicitudPqr);
            	logicMotSolSelect.saveMotSolSelect(motSolSelect);
            }
            
        	motReclSelect.setSolicitudPqr(solicitudPqr);
        	logicMotReclSelect.saveMotReclSelect(motReclSelect);
        	
        	if(solicitudAsignadaArea != null){
	        	solicitudAsignadaArea.setSolicitudPqr(solicitudPqr);
	        	logicSolicitudAsignadaArea.saveSolicitudAsignadaArea(solicitudAsignadaArea);
        	}
        	
        	if(anexosSolicitantes != null){
	        	for (AnexosSolicitante anexoSolicitante : anexosSolicitantes) {
					anexoSolicitante.setSolicitudPqr(solicitudPqr);
					logicAnexosSolicitante.saveAnexosSolicitante(anexoSolicitante);
				}
        	}
        	
        	solicitudPqr.setNumeroRadicacion(solicitudPqr.getIdSolPqr().toString());
        	logicSolicitudPqr.updateSolicitudPqr(solicitudPqr);
        	
        } catch (Exception e) {
            throw new Exception (e);
        } 
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveRespuestaSolicitud(SolicitudAsignadaArea solicitudAsignadaArea, 
    		RespuestaSol respuestaSol, List<AnexosRespuesta> anexosRespuestas) throws Exception {
    	try{
    		solicitudAsignadaAreaLogic.updateSolicitudAsignadaArea(solicitudAsignadaArea);
    		respuestaSol.setSolicitudAsignadaArea(solicitudAsignadaArea);
    		
    		respuestaSolLogic.saveRespuestaSol(respuestaSol);
    		
    		if(anexosRespuestas != null){
    			if(anexosRespuestas.size()>0){
		    		for (AnexosRespuesta anexoRespuesta : anexosRespuestas) {
						anexoRespuesta.setRespuestaSol(respuestaSol);
						anexosRespuestaLogic.saveAnexosRespuesta(anexoRespuesta);
					}
    			}
    		}
    	}catch (Exception e) {
            throw new Exception (e);
        }
    }
    
    @Transactional(readOnly = true)
    public List<SolicitudPqr> consultarSolicitudes(Long idAreaInvolucrada) throws Exception {
    	List<SolicitudPqr> solicitudes = null;
    	try{
    		solicitudes = solicitudPqrDAO.consultarSolicitudes(idAreaInvolucrada);
    	}catch (Exception e) {
            throw new Exception (e);
        }
    	return solicitudes;
    }
    
    @Transactional(readOnly = true)
    public List<SolicitudDTO> consultarAsignacion(AreasInvolucradas area) throws Exception {
    	List<SolicitudDTO> solicitudes = null;
    	try{
    		solicitudes = solicitudPqrDAO.consultarAsignacion(area);
    	}catch (Exception e) {
            throw new Exception (e);
        }
    	return solicitudes;
    }
}
