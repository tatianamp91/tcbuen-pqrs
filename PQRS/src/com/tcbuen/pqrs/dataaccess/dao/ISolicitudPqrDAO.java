package com.tcbuen.pqrs.dataaccess.dao;


import java.util.List;
import java.util.Date;
import com.tcbuen.pqrs.dataaccess.api.Dao;
import com.tcbuen.pqrs.modelo.AreasInvolucradas;
import com.tcbuen.pqrs.modelo.MotivoReclamacion;
import com.tcbuen.pqrs.modelo.SolicitudPqr;
import com.tcbuen.pqrs.modelo.dto.EstadisticasDTO;
import com.tcbuen.pqrs.modelo.dto.SolicitudDTO;


/**
* Interface for   SolicitudPqrDAO.
*
*/
public interface ISolicitudPqrDAO extends Dao<SolicitudPqr, Long> {
	
	public List<SolicitudPqr> consultarSolicitudes(Long idAreaInvolucrada) throws Exception;
	
	public List<SolicitudDTO> consultarAsignacion(AreasInvolucradas area) throws Exception;
	
	public List<EstadisticasDTO> consultarSolicitudPorEstado(String estado) throws Exception;
	
	public List<EstadisticasDTO> consultarSolicitudMotivoReclamacion(Long idMotivoReclamacion) throws Exception;
	
	public List<EstadisticasDTO> consultarSolicitudNumeroRadicacion(String numeroRadicacion) throws Exception;
	
	public List<EstadisticasDTO> consultarSolicitudPorFecha(String fechaInicio, String fechaFin) throws Exception;
	
	public List<EstadisticasDTO> consultarSolicitudMotivoReclamacionEstado(Long idMotivoReclamacion, String estado) throws Exception;
	
	public List<EstadisticasDTO> consultarSolicitudPorEstadoYFechas(String estado, String fechaInicio, String fechaFin) throws Exception;
	
	public List<EstadisticasDTO> consultarSolicitudPorReclamacionYFechas(Long idMotivoReclamacion, String fechaInicio, String fechaFin) throws Exception;
	
	public List<EstadisticasDTO> consultarSolicitudPorReclamacionEstadoYFechas(Long idMotivoReclamacion, String estado, String fechaInicio, String fechaFin) throws Exception;
	
}
