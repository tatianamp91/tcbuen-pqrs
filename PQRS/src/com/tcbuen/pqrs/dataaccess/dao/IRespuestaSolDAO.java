package com.tcbuen.pqrs.dataaccess.dao;

import java.util.List;

import com.tcbuen.pqrs.dataaccess.api.Dao;
import com.tcbuen.pqrs.modelo.RespuestaSol;


/**
* Interface for   RespuestaSolDAO.
*
*/
public interface IRespuestaSolDAO extends Dao<RespuestaSol, Long> {
	
	public List<RespuestaSol> consultarRespuestasSolicitud(Long idSolPqr) throws Exception;
}
