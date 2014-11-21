package com.tcbuen.pqrs.dataaccess.dao;

import com.tcbuen.pqrs.dataaccess.api.HibernateDaoImpl;
import com.tcbuen.pqrs.modelo.AreasInvolucradas;
import com.tcbuen.pqrs.modelo.MotivoReclamacion;
import com.tcbuen.pqrs.modelo.SolicitudPqr;
import com.tcbuen.pqrs.modelo.dto.EstadisticasDTO;
import com.tcbuen.pqrs.modelo.dto.SolicitudAreaDTO;
import com.tcbuen.pqrs.modelo.dto.SolicitudDTO;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.management.Query;


/**
 * A data access object (DAO) providing persistence and search support for
 * SolicitudPqr entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.SolicitudPqr
 */
@Scope("singleton")
@Repository("SolicitudPqrDAO")
public class SolicitudPqrDAO extends HibernateDaoImpl<SolicitudPqr, Long>
    implements ISolicitudPqrDAO {
    private static final Logger log = LoggerFactory.getLogger(SolicitudPqrDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static ISolicitudPqrDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (ISolicitudPqrDAO) ctx.getBean("SolicitudPqrDAO");
    }
    
    @Override
	public List<SolicitudPqr> consultarSolicitudes(Long idAreaInvolucrada) throws Exception {
    	String hql = "select new com.tcbuen.pqrs.modelo.dto.SolicitudAreaDTO(max(saa.idSolAsigArea), sol) from SolicitudPqr sol, SolicitudAsignadaArea saa "
    			+ "where sol.idSolPqr = saa.solicitudPqr.idSolPqr and sol.tipoEstadoPqr.descripcionEstado = 'activo' "
    			+ "or sol.tipoEstadoPqr.descripcionEstado = 'en proceso' "
    			+ "and saa.areasInvolucradas.idAreaInvolucrada = "+idAreaInvolucrada+" "
    			+ "group by (sol.idSolPqr, sol.tipoSolicitudPqr.idTpSolPqr, sol.infoSolicitante.idInfoSolicitante, "
    			+ "sol.tipoEstadoPqr.idTpEstPqr, sol.numeroRadicacion, sol.nombreCliente, sol.nombreAgenciaAduana, "
    			+ "sol.descripcionCaso, sol.solicitudARealizar, sol.fechaCreacion, sol.usuarioCreador, "
    			+ "sol.fechaUltimaModificacion, sol.usuarioUltimaModificacion) "
    			+ "order by sol.fechaCreacion desc";
    	
    	List<SolicitudAreaDTO> sol = sessionFactory.getCurrentSession().createQuery(hql).list();
    	List<SolicitudPqr> solicitudes = new ArrayList<SolicitudPqr>();
    	for (SolicitudAreaDTO solicitud : sol) {
			solicitudes.add(solicitud.getSolicitudPqr());
		}    	
    	return solicitudes;
    }

	@Override
	public List<SolicitudDTO> consultarAsignacion(AreasInvolucradas area) throws Exception {
		String hql = "select new com.tcbuen.pqrs.modelo.dto.SolicitudDTO(max(solA.idSolAsigArea), sol.idSolPqr, "
				+ "sol.numeroRadicacion, max(sol.fechaCreacion), max(solA.fechaAsignacion), tps.descTpSol) "
				+ "from SolicitudPqr sol, SolicitudAsignadaArea solA, TipoEstadoPqr tpe, TipoSolicitudPqr tps "
				+ "where sol.idSolPqr = solA.solicitudPqr.idSolPqr and tpe.idTpEstPqr = sol.tipoEstadoPqr.idTpEstPqr "
				+ "and tps.idTpSolPqr = sol.tipoSolicitudPqr.idTpSolPqr "
				+ "and solA.areasInvolucradas.idAreaInvolucrada = "+area.getIdAreaInvolucrada()+" "
				+ "and tpe.descripcionEstado = 'en proceso'"
				+ "group by (sol.idSolPqr, sol.numeroRadicacion, tps.descTpSol)";
		List<SolicitudDTO> sol = sessionFactory.getCurrentSession().createQuery(hql).list();

		return sol;
	}

	@Override
	public List<EstadisticasDTO> consultarSolicitudPorEstado(String estado) throws Exception {
		String hql = "SELECT new com.tcbuen.pqrs.modelo.dto.EstadisticasDTO(max(SPQR.numeroRadicacion), MOTR.descripcionMotRecl, SPQR.fechaCreacion, TEPQR.descripcionEstado, AI.nombreArea, SAA.fechaRespuesta) " +
				 "FROM SolicitudPqr SPQR, MotivoReclamacion MOTR, TipoEstadoPqr TEPQR, MotReclSelect MRS, SolicitudAsignadaArea SAA, AreasInvolucradas AI " +
				 "WHERE MOTR.idMotRecl = MRS.motivoReclamacion.idMotRecl and MRS.solicitudPqr.idSolPqr = SPQR.idSolPqr and SPQR.tipoEstadoPqr.idTpEstPqr = TEPQR.idTpEstPqr " +
				 "and SPQR.idSolPqr = SAA.solicitudPqr.idSolPqr and SAA.areasInvolucradas.idAreaInvolucrada = AI.idAreaInvolucrada " +
				 "and TEPQR.descripcionEstado = " + "'" + estado + "'" + 
				 "group by (MOTR.descripcionMotRecl, SPQR.fechaCreacion, TEPQR.descripcionEstado, AI.nombreArea, SAA.fechaRespuesta) "+
				 " order by SPQR.fechaCreacion asc";
		
		return (List<EstadisticasDTO>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	public List<EstadisticasDTO> consultarSolicitudMotivoReclamacion(Long idMotivoReclamacion) throws Exception {
		String hql= "SELECT new com.tcbuen.pqrs.modelo.dto.EstadisticasDTO(max(SPQR.numeroRadicacion), MOTR.descripcionMotRecl, SPQR.fechaCreacion, TEPQR.descripcionEstado, AI.nombreArea, SAA.fechaRespuesta) " +
				 "FROM SolicitudPqr SPQR, MotivoReclamacion MOTR, TipoEstadoPqr TEPQR, MotReclSelect MRS, SolicitudAsignadaArea SAA, AreasInvolucradas AI " +
				 "WHERE MOTR.idMotRecl = MRS.motivoReclamacion.idMotRecl and MRS.solicitudPqr.idSolPqr = SPQR.idSolPqr and SPQR.tipoEstadoPqr.idTpEstPqr = TEPQR.idTpEstPqr " +
				 "and SPQR.idSolPqr = SAA.solicitudPqr.idSolPqr and SAA.areasInvolucradas.idAreaInvolucrada = AI.idAreaInvolucrada " +
				 "and MOTR.idMotRecl = " + idMotivoReclamacion + 
				 "group by (MOTR.descripcionMotRecl, SPQR.fechaCreacion, TEPQR.descripcionEstado, AI.nombreArea, SAA.fechaRespuesta) "+
				 " order by SPQR.fechaCreacion asc";
		
		
		return (List<EstadisticasDTO>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}

    @Override
    public List<EstadisticasDTO> consultarSolicitudNumeroRadicacion(String numeroRadicacion) throws Exception{
    	String hql = "SELECT new com.tcbuen.pqrs.modelo.dto.EstadisticasDTO(max(SPQR.numeroRadicacion), MOTR.descripcionMotRecl, SPQR.fechaCreacion, TEPQR.descripcionEstado, AI.nombreArea, SAA.fechaRespuesta) "
    			+ "FROM SolicitudPqr SPQR, MotivoReclamacion MOTR, TipoEstadoPqr TEPQR, MotReclSelect MRS, SolicitudAsignadaArea SAA, AreasInvolucradas AI " 
    			+ "WHERE MOTR.idMotRecl = MRS.motivoReclamacion.idMotRecl and MRS.solicitudPqr.idSolPqr = SPQR.idSolPqr and SPQR.tipoEstadoPqr.idTpEstPqr = TEPQR.idTpEstPqr "
    			+ "and SPQR.idSolPqr = SAA.solicitudPqr.idSolPqr and SAA.areasInvolucradas.idAreaInvolucrada = AI.idAreaInvolucrada " 
    			+ "and SPQR.numeroRadicacion = " + "'" + numeroRadicacion + "'" 
    			+ " group by (MOTR.descripcionMotRecl, SPQR.fechaCreacion, TEPQR.descripcionEstado, AI.nombreArea, SAA.fechaRespuesta) " 
    			+ "order by SPQR.fechaCreacion asc ";
    	return (List<EstadisticasDTO>) sessionFactory.getCurrentSession().createQuery(hql).list();
    }
	
    @Override
    public List<EstadisticasDTO> consultarSolicitudPorFecha(String fechaInicio, String fechaFin) throws Exception{
    	String hql = "SELECT new com.tcbuen.pqrs.modelo.dto.EstadisticasDTO(max(SPQR.numeroRadicacion), MOTR.descripcionMotRecl, SPQR.fechaCreacion, TEPQR.descripcionEstado, AI.nombreArea, SAA.fechaRespuesta) "
    			+ "FROM SolicitudPqr SPQR, MotivoReclamacion MOTR, TipoEstadoPqr TEPQR, MotReclSelect MRS, SolicitudAsignadaArea SAA, AreasInvolucradas AI " 
    			+ "WHERE MOTR.idMotRecl = MRS.motivoReclamacion.idMotRecl and MRS.solicitudPqr.idSolPqr = SPQR.idSolPqr and SPQR.tipoEstadoPqr.idTpEstPqr = TEPQR.idTpEstPqr "
    			+ "and SPQR.idSolPqr = SAA.solicitudPqr.idSolPqr and SAA.areasInvolucradas.idAreaInvolucrada = AI.idAreaInvolucrada " 
    			+ "and SPQR.fechaCreacion BETWEEN " + "to_date(" + "'" + fechaInicio + "'" +", 'dd/MM/yyyy') " + " AND " + "to_date(" + "'" + fechaFin + "'" +", 'dd/MM/yyyy') "
    			+ "group by (MOTR.descripcionMotRecl, SPQR.fechaCreacion, TEPQR.descripcionEstado, AI.nombreArea, SAA.fechaRespuesta) "
    			+ " order by SPQR.fechaCreacion asc";
    	return (List<EstadisticasDTO>) sessionFactory.getCurrentSession().createQuery(hql).list();
    }
    
    @Override
    public List<EstadisticasDTO> consultarSolicitudMotivoReclamacionEstado(Long idMotivoReclamacion, String estado) throws Exception{
    	String hql = "SELECT new com.tcbuen.pqrs.modelo.dto.EstadisticasDTO(max(SPQR.numeroRadicacion), MOTR.descripcionMotRecl, SPQR.fechaCreacion, TEPQR.descripcionEstado, AI.nombreArea, SAA.fechaRespuesta) "
    			+ "FROM SolicitudPqr SPQR, MotivoReclamacion MOTR, TipoEstadoPqr TEPQR, MotReclSelect MRS, SolicitudAsignadaArea SAA, AreasInvolucradas AI " 
    			+ "WHERE MOTR.idMotRecl = MRS.motivoReclamacion.idMotRecl and MRS.solicitudPqr.idSolPqr = SPQR.idSolPqr and SPQR.tipoEstadoPqr.idTpEstPqr = TEPQR.idTpEstPqr "
    			+ "and SPQR.idSolPqr = SAA.solicitudPqr.idSolPqr and SAA.areasInvolucradas.idAreaInvolucrada = AI.idAreaInvolucrada " 
    			+ "and MOTR.idMotRecl = " + idMotivoReclamacion + " and TEPQR.descripcionEstado = " + "'" + estado + "'"    
    			+ "group by (MOTR.descripcionMotRecl, SPQR.fechaCreacion, TEPQR.descripcionEstado, AI.nombreArea, SAA.fechaRespuesta) "
    			+ " order by SPQR.fechaCreacion asc";
    	return (List<EstadisticasDTO>) sessionFactory.getCurrentSession().createQuery(hql).list();
    }
    
    @Override
    public List<EstadisticasDTO> consultarSolicitudPorEstadoYFechas(String estado, String fechaInicio, String fechaFin) throws Exception{
    	String hql = "SELECT new com.tcbuen.pqrs.modelo.dto.EstadisticasDTO(max(SPQR.numeroRadicacion), MOTR.descripcionMotRecl, SPQR.fechaCreacion, TEPQR.descripcionEstado, AI.nombreArea, SAA.fechaRespuesta) "
    			+ "FROM SolicitudPqr SPQR, MotivoReclamacion MOTR, TipoEstadoPqr TEPQR, MotReclSelect MRS, SolicitudAsignadaArea SAA, AreasInvolucradas AI " 
    			+ "WHERE MOTR.idMotRecl = MRS.motivoReclamacion.idMotRecl and MRS.solicitudPqr.idSolPqr = SPQR.idSolPqr and SPQR.tipoEstadoPqr.idTpEstPqr = TEPQR.idTpEstPqr "
    			+ "and SPQR.idSolPqr = SAA.solicitudPqr.idSolPqr and SAA.areasInvolucradas.idAreaInvolucrada = AI.idAreaInvolucrada " 
    			+ "and TEPQR.descripcionEstado = " + "'" + estado + "'" 
    			+ " and SPQR.fechaCreacion BETWEEN " + "to_date(" + "'" + fechaInicio + "'" +", 'dd/MM/yyyy') " + " AND " + "to_date(" + "'" + fechaFin + "'" +", 'dd/MM/yyyy') "   
    			+ "group by (MOTR.descripcionMotRecl, SPQR.fechaCreacion, TEPQR.descripcionEstado, AI.nombreArea, SAA.fechaRespuesta) "
    			+ " order by SPQR.fechaCreacion asc";
    	return (List<EstadisticasDTO>) sessionFactory.getCurrentSession().createQuery(hql).list();
    }
    
    @Override
    public List<EstadisticasDTO> consultarSolicitudPorReclamacionYFechas(Long idMotivoReclamacion, String fechaInicio, String fechaFin) throws Exception{
    	String hql = "SELECT new com.tcbuen.pqrs.modelo.dto.EstadisticasDTO(max(SPQR.numeroRadicacion), MOTR.descripcionMotRecl, SPQR.fechaCreacion, TEPQR.descripcionEstado, AI.nombreArea, SAA.fechaRespuesta) "
    			+ "FROM SolicitudPqr SPQR, MotivoReclamacion MOTR, TipoEstadoPqr TEPQR, MotReclSelect MRS, SolicitudAsignadaArea SAA, AreasInvolucradas AI " 
    			+ "WHERE MOTR.idMotRecl = MRS.motivoReclamacion.idMotRecl and MRS.solicitudPqr.idSolPqr = SPQR.idSolPqr and SPQR.tipoEstadoPqr.idTpEstPqr = TEPQR.idTpEstPqr "
    			+ "and SPQR.idSolPqr = SAA.solicitudPqr.idSolPqr and SAA.areasInvolucradas.idAreaInvolucrada = AI.idAreaInvolucrada " 
    			+ "and MOTR.idMotRecl = " + idMotivoReclamacion 
    			+ " and SPQR.fechaCreacion BETWEEN " + "to_date(" + "'" + fechaInicio + "'" +", 'dd/MM/yyyy') " + " AND " + "to_date(" + "'" + fechaFin + "'" +", 'dd/MM/yyyy') "    
    			+ " group by (MOTR.descripcionMotRecl, SPQR.fechaCreacion, TEPQR.descripcionEstado, AI.nombreArea, SAA.fechaRespuesta) "
    			+ " order by SPQR.fechaCreacion asc";
    	return (List<EstadisticasDTO>) sessionFactory.getCurrentSession().createQuery(hql).list();
    }
    
    @Override
    public List<EstadisticasDTO> consultarSolicitudPorReclamacionEstadoYFechas(Long idMotivoReclamacion, String estado, String fechaInicio, String fechaFin) throws Exception{
    	String hql = "SELECT new com.tcbuen.pqrs.modelo.dto.EstadisticasDTO(max(SPQR.numeroRadicacion), MOTR.descripcionMotRecl, SPQR.fechaCreacion, TEPQR.descripcionEstado, AI.nombreArea, SAA.fechaRespuesta) "
    			+ "FROM SolicitudPqr SPQR, MotivoReclamacion MOTR, TipoEstadoPqr TEPQR, MotReclSelect MRS, SolicitudAsignadaArea SAA, AreasInvolucradas AI " 
    			+ "WHERE MOTR.idMotRecl = MRS.motivoReclamacion.idMotRecl and MRS.solicitudPqr.idSolPqr = SPQR.idSolPqr and SPQR.tipoEstadoPqr.idTpEstPqr = TEPQR.idTpEstPqr "
    			+ "and SPQR.idSolPqr = SAA.solicitudPqr.idSolPqr and SAA.areasInvolucradas.idAreaInvolucrada = AI.idAreaInvolucrada " 
    			+ "and MOTR.idMotRecl = " + idMotivoReclamacion + " and TEPQR.descripcionEstado = " + "'" + estado + "'" 
    			+ " and SPQR.fechaCreacion BETWEEN " + "to_date(" + "'" + fechaInicio + "'" +", 'dd/MM/yyyy') " + " AND " + "to_date(" + "'" + fechaFin + "'" +", 'dd/MM/yyyy') "    
    			+ "group by (MOTR.descripcionMotRecl, SPQR.fechaCreacion, TEPQR.descripcionEstado, AI.nombreArea, SAA.fechaRespuesta) "
    			+ " order by SPQR.fechaCreacion asc";
    	return (List<EstadisticasDTO>) sessionFactory.getCurrentSession().createQuery(hql).list();
    }
    
}
