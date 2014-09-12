package com.tcbuen.pqrs.dataaccess.dao;

import java.util.List;

import com.tcbuen.pqrs.dataaccess.api.Dao;
import com.tcbuen.pqrs.modelo.AnexosPqr;
import com.tcbuen.pqrs.modelo.TipoSolicitudPqr;


/**
* Interface for   AnexosPqrDAO.
*
*/
public interface IAnexosPqrDAO extends Dao<AnexosPqr, Long> {
	
	public List<AnexosPqr> consultarAnxsXTipoPqr(TipoSolicitudPqr tipoSolicitudPqr) 
			throws Exception;
	
	public List<AnexosPqr> consultarAnxsNoTipoPqr(TipoSolicitudPqr tipoSolicitudPqr) 
			throws Exception;
}
