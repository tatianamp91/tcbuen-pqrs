package com.tcbuen.pqrs.dataaccess.dao;

import java.util.List;

import com.tcbuen.pqrs.dataaccess.api.Dao;
import com.tcbuen.pqrs.modelo.AnexosPqr;
import com.tcbuen.pqrs.modelo.MotReclXTpSol;
import com.tcbuen.pqrs.modelo.MotivoReclamacion;
import com.tcbuen.pqrs.modelo.TipoSolicitudPqr;


/**
* Interface for   MotivoReclamacionDAO.
*
*/
public interface IMotivoReclamacionDAO extends Dao<MotivoReclamacion, Long> {
	
	public List<MotivoReclamacion> consultarMotivosReclamacion() throws Exception;
	
	public List<MotivoReclamacion> consultarMotReclXTipoPqr(TipoSolicitudPqr tipoSolicitudPqr) throws Exception;
	
	public List<MotivoReclamacion> consultarMotReclNoTipoPqr(TipoSolicitudPqr tipoSolicitudPqr) throws Exception;
}
