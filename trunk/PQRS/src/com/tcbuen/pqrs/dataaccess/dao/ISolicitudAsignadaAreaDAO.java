package com.tcbuen.pqrs.dataaccess.dao;

import com.tcbuen.pqrs.dataaccess.api.Dao;
import com.tcbuen.pqrs.modelo.SolicitudAsignadaArea;
import com.tcbuen.pqrs.modelo.SolicitudPqr;


/**
* Interface for   SolicitudAsignadaAreaDAO.
*
*/
public interface ISolicitudAsignadaAreaDAO extends Dao<SolicitudAsignadaArea, Long> {
	
	public SolicitudAsignadaArea consultarSolicitudAsiganada(SolicitudPqr solicitud) throws Exception;
}
