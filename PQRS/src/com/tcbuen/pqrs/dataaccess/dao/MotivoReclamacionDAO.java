package com.tcbuen.pqrs.dataaccess.dao;

import com.tcbuen.pqrs.dataaccess.api.HibernateDaoImpl;
import com.tcbuen.pqrs.modelo.MotReclXTpSol;
import com.tcbuen.pqrs.modelo.MotivoReclamacion;
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
 * MotivoReclamacion entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.MotivoReclamacion
 */
@Scope("singleton")
@Repository("MotivoReclamacionDAO")
public class MotivoReclamacionDAO extends HibernateDaoImpl<MotivoReclamacion, Long>
    implements IMotivoReclamacionDAO {
    private static final Logger log = LoggerFactory.getLogger(MotivoReclamacionDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IMotivoReclamacionDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IMotivoReclamacionDAO) ctx.getBean("MotivoReclamacionDAO");
    }
    
	@Override
	public List<MotivoReclamacion> consultarMotReclXTipoPqr(TipoSolicitudPqr tipoSolicitudPqr) throws Exception {
		
		String hql = "Select MotivoReclamacion from MotReclXTpSol motReclXTpSol, MotivoReclamacion motivoReclamacion"
					+ " where motivoReclamacion.idMotRecl = motReclXTpSol.idMotRecl "
					+ "and tipoSolicitudPqr.idTpSolPqr ="+tipoSolicitudPqr.getIdTpSolPqr();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<MotivoReclamacion> motivoReclamacion = query.list();
		return motivoReclamacion;
	}
	
	@Override
	public List<MotivoReclamacion> consultarMotReclNoTipoPqr(TipoSolicitudPqr tipoSolicitudPqr) throws Exception {
		
		String hql = "Select motivoReclamacion from MotivoReclamacion motivoReclamacion "
					+ "where motivoReclamacion.idMotRecl not in (Select MotivoReclamacion from MotReclXTpSol motReclXTpSol, MotivoReclamacion motivoReclamacion"
					+ " where motivoReclamacion.idMotRecl = motReclXTpSol.idMotRecl "
					+ "and tipoSolicitudPqr.idTpSolPqr ="+tipoSolicitudPqr.getIdTpSolPqr()+")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<MotivoReclamacion> motivoReclamacion = query.list();
		return motivoReclamacion;
	}
}
