package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.AnexosPqr;
import com.tcbuen.pqrs.modelo.MotivoReclamacion;
import com.tcbuen.pqrs.modelo.MotivoSolicitud;
import com.tcbuen.pqrs.modelo.TipoSolicitudPqr;
import com.tcbuen.pqrs.modelo.dto.TipoSolicitudPqrDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface ITipoSolicitudPqrLogic {
    public List<TipoSolicitudPqr> getTipoSolicitudPqr()
        throws Exception;

    /**
         * Save an new TipoSolicitudPqr entity
         */
    public void saveTipoSolicitudPqr(TipoSolicitudPqr entity)
        throws Exception;

    /**
         * Delete an existing TipoSolicitudPqr entity
         *
         */
    public void deleteTipoSolicitudPqr(TipoSolicitudPqr entity)
        throws Exception;

    /**
        * Update an existing TipoSolicitudPqr entity
        *
        */
    public void updateTipoSolicitudPqr(TipoSolicitudPqr entity)
        throws Exception;

    /**
         * Load an existing TipoSolicitudPqr entity
         *
         */
    
    public void save_mot_recl_mot_sol_anxs_x_tipo(TipoSolicitudPqr tipoSol, List<MotivoReclamacion> motivosReclamacionTargetCopia,
    		List<MotivoReclamacion> motivosReclamacionTarget, List<MotivoSolicitud> motivosSolicitudTargetCopia,
			List<MotivoSolicitud> motivosSolicitudTarget, List<AnexosPqr> anexosPqrTargetCopia,
			List<AnexosPqr> anexosPqrTarget) throws Exception;
			
    public TipoSolicitudPqr getTipoSolicitudPqr(Long idTpSolPqr)
        throws Exception;

    public List<TipoSolicitudPqr> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<TipoSolicitudPqr> findPageTipoSolicitudPqr(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberTipoSolicitudPqr() throws Exception;

    public List<TipoSolicitudPqrDTO> getDataTipoSolicitudPqr()
        throws Exception;
}
