package com.tcbuen.pqrs.dataaccess.dao;

import com.tcbuen.pqrs.dataaccess.api.HibernateDaoImpl;
import com.tcbuen.pqrs.modelo.MotivoReclamacion;
import com.tcbuen.pqrs.modelo.MotivoSolicitud;
import com.tcbuen.pqrs.modelo.TipoSolicitudPqr;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;


/**
 * A data access object (DAO) providing persistence and search support for
 * MotivoSolicitud entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.MotivoSolicitud
 */
@Scope("singleton")
@Repository("MotivoSolicitudDAO")
public class MotivoSolicitudDAO extends HibernateDaoImpl<MotivoSolicitud, Long>
    implements IMotivoSolicitudDAO {
    private static final Logger log = LoggerFactory.getLogger(MotivoSolicitudDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IMotivoSolicitudDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IMotivoSolicitudDAO) ctx.getBean("MotivoSolicitudDAO");
    }
    
	@Override
	public List<MotivoSolicitud> consultarMotSolXTipoPqr(TipoSolicitudPqr tipoSolicitudPqr) throws Exception {
		
		String hql = "Select motivoSolicitud from MotivoSolicitud motivoSolicitud, MotSolXTpSol motSolXTpSol "
					+ "where motivoSolicitud.idMotSol = motSolXTpSol.motivoSolicitud.idMotSol "
					+ "and motSolXTpSol.tipoSolicitudPqr.idTpSolPqr =" + tipoSolicitudPqr.getIdTpSolPqr();
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<MotivoSolicitud> motivoSolicitud = query.list();
		return motivoSolicitud;
	}
	
	@Override
	public List<MotivoSolicitud> consultarMotSolNoTipoPqr(TipoSolicitudPqr tipoSolicitudPqr) throws Exception {
			
		String hql = "Select motivoSolicitud from MotivoSolicitud motivoSolicitud "
					+ "where motivoSolicitud.idMotSol not in (Select motivoSolicitud from MotivoSolicitud motivoSolicitud, MotSolXTpSol motSolXTpSol "
					+ "where motivoSolicitud.idMotSol = motSolXTpSol.motivoSolicitud.idMotSol "
					+ "and motSolXTpSol.tipoSolicitudPqr.idTpSolPqr =" + tipoSolicitudPqr.getIdTpSolPqr() + ")";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<MotivoSolicitud> motivoSolicitud = query.list();
		return motivoSolicitud;
	}
}
