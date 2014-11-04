package com.tcbuen.pqrs.dataaccess.dao;

import com.tcbuen.pqrs.dataaccess.api.HibernateDaoImpl;
import com.tcbuen.pqrs.modelo.AreasInvolucradas;
import com.tcbuen.pqrs.modelo.SolicitudPqr;
import com.tcbuen.pqrs.modelo.dto.SolicitudDTO;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


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
    	String hql = "select sol from SolicitudPqr sol, SolicitudAsignadaArea saa "
    			+ "where sol.idSolPqr = saa.solicitudPqr.idSolPqr and sol.tipoEstadoPqr.descripcionEstado = 'activo' "
    			+ "and saa.areasInvolucradas.idAreaInvolucrada = "+idAreaInvolucrada+" order by saa.fechaAsignacion";
    	return (List<SolicitudPqr>) sessionFactory.getCurrentSession().createQuery(hql).list();
    }

	@Override
	public List<SolicitudDTO> consultarAsignacion(AreasInvolucradas area) throws Exception {
		String hql = "select sol.idSolPqr, sol.numeroRadicacion, sol.fechaCreacion, solA.fechaAsignacion, tps "
				+ "from SolicitudPqr sol, SolicitudAsignadaArea solA, TipoEstadoPqr tpe, TipoSolicitudPqr tps "
				+ "where sol.idSolPqr = solA.solicitudPqr.idSolPqr and tpe.idTpEstPqr = sol.tipoEstadoPqr.idTpEstPqr "
				+ "and tps.idTpSolPqr = sol.tipoSolicitudPqr.idTpSolPqr "
				+ "and solA.areasInvolucradas.idAreaInvolucrada = "+area.getIdAreaInvolucrada()+" and tpe.descripcionEstado = 'activo' "
				+ "order by solA.fechaAsignacion";
		List<SolicitudDTO> solicitudDTO = sessionFactory.getCurrentSession().createQuery(hql).list();
		List<SolicitudDTO> sol = new ArrayList<SolicitudDTO>();
		
		for(int i = 0; i < solicitudDTO.size(); i++){
			SolicitudDTO solicitud = new SolicitudDTO();
			solicitud.setIdSolPqr(solicitudDTO.get(i).getIdSolPqr());
			solicitud.setNumeroRadicacion(solicitudDTO.get(i).getNumeroRadicacion());
			solicitud.setFechaRadicacion(solicitudDTO.get(i).getFechaRadicacion());
			solicitud.setFechaAsignacion(solicitudDTO.get(i).getFechaAsignacion());
			solicitud.setTipoSolicitudPqr(solicitudDTO.get(i).getTipoSolicitudPqr());
			sol.add(solicitud);
		}
		return sol;
	}
	
	@Override
	public List<SolicitudPqr> consultarSolicitudPorEstado(String estado) throws Exception{
		String hql = "SELECT SPQR.idSolPqr, MOTR.descripcionMotRecl, SPQR.fechaCreacion, TEPQR.descripcionEstado, AI.nombreArea, SAA.fechaRespuesta " +
					 "FROM SolicitudPqr SPQR, MotivoReclamacion MOTR, TipoEstadoPqr TEPQR, MotReclSelect MRS, SolicitudAsignadaArea SAA, AreasInvolucradas AI " +
					 "WHERE MOTR.idMotRecl = MRS.motivoReclamacion.idMotRecl and MRS.solicitudPqr.idSolPqr = SPQR.idSolPqr and SPQR.tipoEstadoPqr.idTpEstPqr = TEPQR.idTpEstPqr " +
					 "and SPQR.idSolPqr = SAA.solicitudPqr.idSolPqr and SAA.areasInvolucradas.idAreaInvolucrada = AI.idAreaInvolucrada " +
					 "and TEPQR.descripcionEstado = " + "'" + estado + "'" + 
					 " order by SPQR.fechaCreacion asc";
		return (List<SolicitudPqr>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}
    
    
}
