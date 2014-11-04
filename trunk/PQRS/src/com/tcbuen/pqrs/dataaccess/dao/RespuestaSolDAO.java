package com.tcbuen.pqrs.dataaccess.dao;

import com.tcbuen.pqrs.dataaccess.api.HibernateDaoImpl;
import com.tcbuen.pqrs.modelo.RespuestaSol;

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
 * RespuestaSol entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.RespuestaSol
 */
@Scope("singleton")
@Repository("RespuestaSolDAO")
public class RespuestaSolDAO extends HibernateDaoImpl<RespuestaSol, Long>
    implements IRespuestaSolDAO {
    private static final Logger log = LoggerFactory.getLogger(RespuestaSolDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IRespuestaSolDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IRespuestaSolDAO) ctx.getBean("RespuestaSolDAO");
    }
    
	public List<RespuestaSol> consultarRespuestasSolicitud(Long idSolPqr) throws Exception {
		String hql = "select res from RespuestaSol res where res.solicitudAsignadaArea.solicitudPqr.idSolPqr = "+idSolPqr;
		return (List<RespuestaSol>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}
}
