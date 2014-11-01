package com.tcbuen.pqrs.dataaccess.dao;

import com.tcbuen.pqrs.dataaccess.api.HibernateDaoImpl;
import com.tcbuen.pqrs.modelo.AreasInvolucradas;
import com.tcbuen.pqrs.modelo.SolicitudPqr;
import com.tcbuen.pqrs.modelo.dto.SolicitudDTO;

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
	public List<SolicitudDTO> consultarAsignacion(AreasInvolucradas area) throws Exception {
		String hql = "select sol.idSolPqr, sol.numeroRadicacion, sol.fechaCreacion, solA.fechaAsignacion, tps "
				+ "from SolicitudPqr sol, SolicitudAsignadaArea solA, TipoEstadoPqr tpe, TipoSolicitudPqr tps "
				+ "where sol.idSolPqr = solA.idSolPqr and tpe.idTpEstPqr = sol.idTpEstPqr and tps.idTpSolPqr = sol.idTpSolPqr "
				+ "and solA.idAreaInvolucrada = "+area.getIdAreaInvolucrada()+" and tpe.descripcionEstado = 'activo' "
				+ "order by solA.fechaAsignacion";
		
		return (List<SolicitudDTO>) sessionFactory.getCurrentSession().createQuery(hql);
	}
    
    
}
