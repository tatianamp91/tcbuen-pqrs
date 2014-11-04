package com.tcbuen.pqrs.dataaccess.dao;

import java.util.List;

import com.tcbuen.pqrs.dataaccess.api.Dao;
import com.tcbuen.pqrs.modelo.AnexosRespuesta;


/**
* Interface for   AnexosRespuestaDAO.
*
*/
public interface IAnexosRespuestaDAO extends Dao<AnexosRespuesta, Long> {
	
	public List<AnexosRespuesta> consultarAnexosRespuesta(Long idRespSol) throws Exception;
}
