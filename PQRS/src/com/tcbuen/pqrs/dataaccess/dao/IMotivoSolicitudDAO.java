package com.tcbuen.pqrs.dataaccess.dao;

import java.util.List;

import com.tcbuen.pqrs.dataaccess.api.Dao;
import com.tcbuen.pqrs.modelo.MotivoSolicitud;
import com.tcbuen.pqrs.modelo.TipoSolicitudPqr;


/**
* Interface for   MotivoSolicitudDAO.
*
*/
public interface IMotivoSolicitudDAO extends Dao<MotivoSolicitud, Long> {
	
	public List<MotivoSolicitud> consultarMotSolXTipoPqr(TipoSolicitudPqr tipoSolicitudPqr) throws Exception;
	
	public List<MotivoSolicitud> consultarMotSolNoTipoPqr(TipoSolicitudPqr tipoSolicitudPqr) throws Exception;
}
