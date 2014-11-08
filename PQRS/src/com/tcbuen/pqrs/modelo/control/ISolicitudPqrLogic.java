package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.MotivoReclamacion;
import com.tcbuen.pqrs.modelo.SolicitudPqr;
import com.tcbuen.pqrs.modelo.dto.EstadisticasDTO;
import com.tcbuen.pqrs.modelo.dto.SolicitudPqrDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface ISolicitudPqrLogic {
    public List<SolicitudPqr> getSolicitudPqr() throws Exception;

    /**
         * Save an new SolicitudPqr entity
         */
    public void saveSolicitudPqr(SolicitudPqr entity) throws Exception;

    /**
         * Delete an existing SolicitudPqr entity
         *
         */
    public void deleteSolicitudPqr(SolicitudPqr entity)
        throws Exception;

    /**
        * Update an existing SolicitudPqr entity
        *
        */
    public void updateSolicitudPqr(SolicitudPqr entity)
        throws Exception;

    /**
         * Load an existing SolicitudPqr entity
         *
         */
    public SolicitudPqr getSolicitudPqr(Long idSolPqr)
        throws Exception;

    public List<SolicitudPqr> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SolicitudPqr> findPageSolicitudPqr(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberSolicitudPqr() throws Exception;
    
    public List<EstadisticasDTO> consultarSolicitudPorEstado(String estado) throws Exception;

    public List<SolicitudPqrDTO> getDataSolicitudPqr() throws Exception;
    
    public List<EstadisticasDTO> consultarSolicitudMotivoReclamacion(MotivoReclamacion motReclamacion) throws Exception ;
}
