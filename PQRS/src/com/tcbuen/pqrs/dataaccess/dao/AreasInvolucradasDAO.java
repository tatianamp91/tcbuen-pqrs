package com.tcbuen.pqrs.dataaccess.dao;

import com.tcbuen.pqrs.dataaccess.api.HibernateDaoImpl;
import com.tcbuen.pqrs.modelo.AreasInvolucradas;

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
 * AreasInvolucradas entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see lidis.AreasInvolucradas
 */
@Scope("singleton")
@Repository("AreasInvolucradasDAO")
public class AreasInvolucradasDAO extends HibernateDaoImpl<AreasInvolucradas, Long>
    implements IAreasInvolucradasDAO {
    private static final Logger log = LoggerFactory.getLogger(AreasInvolucradasDAO.class);
    @Resource
    private SessionFactory sessionFactory;

    public static IAreasInvolucradasDAO getFromApplicationContext(
        ApplicationContext ctx) {
        return (IAreasInvolucradasDAO) ctx.getBean("AreasInvolucradasDAO");
    }
    
	@Override
	public List<AreasInvolucradas> consultarTodasAreaXAnxs() throws Exception {
		   
			String hql = "Select distinct(areasInvolucradas) from AnxsXArea anxsXArea, AreasInvolucradas areasInvolucradas "
						+ "where areasInvolucradas.idAreaInvolucrada = anxsXArea.areasInvolucradas.idAreaInvolucrada ";
			
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			List<AreasInvolucradas> areasInvolucradas = query.list();
			
			return areasInvolucradas;
	}
	
	@Override
	public List<AreasInvolucradas> consultarNoAreaXAnxs() throws Exception {
		   
			String hql = "Select areasInvolucrada from AreasInvolucradas areasInvolucrada "
						+ "where areasInvolucrada.idAreaInvolucrada not in (Select distinct(areasInvolucradas) "
						+ "from AnxsXArea anxsXArea, AreasInvolucradas areasInvolucradas "
						+ "where areasInvolucradas.idAreaInvolucrada = anxsXArea.areasInvolucradas.idAreaInvolucrada) "
						+ "and estadoRegistro = 'A'";
			
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			List<AreasInvolucradas> areasInvolucradas = query.list();
			
			return areasInvolucradas;
	}
}
