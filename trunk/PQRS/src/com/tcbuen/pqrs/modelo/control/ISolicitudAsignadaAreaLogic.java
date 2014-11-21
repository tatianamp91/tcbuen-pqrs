package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.SolicitudAsignadaArea;
import com.tcbuen.pqrs.modelo.SolicitudPqr;
import com.tcbuen.pqrs.modelo.dto.SolicitudAsignadaAreaDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface ISolicitudAsignadaAreaLogic {
    public List<SolicitudAsignadaArea> getSolicitudAsignadaArea()
        throws Exception;

    /**
         * Save an new SolicitudAsignadaArea entity
         */
    public void saveSolicitudAsignadaArea(SolicitudAsignadaArea entity)
        throws Exception;

    /**
         * Delete an existing SolicitudAsignadaArea entity
         *
         */
    public void deleteSolicitudAsignadaArea(SolicitudAsignadaArea entity)
        throws Exception;

    /**
        * Update an existing SolicitudAsignadaArea entity
        *
        */
    public void updateSolicitudAsignadaArea(SolicitudAsignadaArea entity)
        throws Exception;

    /**
         * Load an existing SolicitudAsignadaArea entity
         *
         */
    public SolicitudAsignadaArea getSolicitudAsignadaArea(Long idSolAsigArea)
        throws Exception;

    public List<SolicitudAsignadaArea> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<SolicitudAsignadaArea> findPageSolicitudAsignadaArea(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberSolicitudAsignadaArea()
        throws Exception;

    public List<SolicitudAsignadaAreaDTO> getDataSolicitudAsignadaArea()
        throws Exception;
    
    public SolicitudAsignadaArea consultarSolicitudAsiganada(SolicitudPqr solicitud) throws Exception;
}
