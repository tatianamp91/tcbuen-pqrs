package com.tcbuen.pqrs.dataaccess.dao;

import com.tcbuen.pqrs.dataaccess.api.HibernateDaoImpl;
import com.tcbuen.pqrs.modelo.AnexosPqr;
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
 * AnexosPqr entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.AnexosPqr
 */
@Scope("singleton")
@Repository("AnexosPqrDAO")
public class AnexosPqrDAO extends HibernateDaoImpl<AnexosPqr, Long>
    implements IAnexosPqrDAO {
    private static final Logger log = LoggerFactory.getLogger(AnexosPqrDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IAnexosPqrDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IAnexosPqrDAO) ctx.getBean("AnexosPqrDAO");
    }
    
    @Override
	public List<AnexosPqr> consultarAnexos() throws Exception {
			
		String hql = "Select anexosPqr from AnexosPqr anexosPqr "
					+ "where anexosPqr.estadoRegistro = 'A' ";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<AnexosPqr> anexosPqr = query.list();
		
		return anexosPqr;
	}
    
    @Override
	public List<AnexosPqr> consultarAnxsXTipoPqr(TipoSolicitudPqr tipoSolicitudPqr) throws Exception {
			
		String hql = "Select anexosPqr from AnxsXTpSol anxsXTpSol, AnexosPqr anexosPqr "
					+ "where anexosPqr.idAnexoPqr = anxsXTpSol.anexosPqr.idAnexoPqr "
					+ "and anxsXTpSol.tipoSolicitudPqr.idTpSolPqr ="+ tipoSolicitudPqr.getIdTpSolPqr();
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<AnexosPqr> anexosPqr = query.list();
		
		return anexosPqr;
	}
	
	@Override
	public List<AnexosPqr> consultarAnxsNoTipoPqr(TipoSolicitudPqr tipoSolicitudPqr) throws Exception {
		
		String hql = "Select anexosPqr from AnexosPqr anexosPqr "
					+ "where anexosPqr.idAnexoPqr not in (Select anexosPqr from AnxsXTpSol anxsXTpSol, AnexosPqr anexosPqr "
					+ "where anexosPqr.idAnexoPqr = anxsXTpSol.anexosPqr.idAnexoPqr "
					+ "and anxsXTpSol.tipoSolicitudPqr.idTpSolPqr =" + tipoSolicitudPqr.getIdTpSolPqr() + ")"
					+ "and estadoRegistro = 'A'";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<AnexosPqr> anexosPqr = query.list();	
		return anexosPqr;
	}
}
