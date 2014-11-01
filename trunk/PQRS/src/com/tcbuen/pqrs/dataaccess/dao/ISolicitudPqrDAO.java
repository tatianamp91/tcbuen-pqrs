package com.tcbuen.pqrs.dataaccess.dao;

import java.util.List;

import com.tcbuen.pqrs.dataaccess.api.Dao;
import com.tcbuen.pqrs.modelo.AreasInvolucradas;
import com.tcbuen.pqrs.modelo.SolicitudPqr;
import com.tcbuen.pqrs.modelo.dto.SolicitudDTO;


/**
* Interface for   SolicitudPqrDAO.
*
*/
public interface ISolicitudPqrDAO extends Dao<SolicitudPqr, Long> {
	
	
	public List<SolicitudDTO> consultarAsignacion(AreasInvolucradas area) throws Exception;
}
