package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.InfoSolicitante;
import com.tcbuen.pqrs.modelo.dto.InfoSolicitanteDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface IInfoSolicitanteLogic {
    public List<InfoSolicitante> getInfoSolicitante() throws Exception;

    /**
         * Save an new InfoSolicitante entity
         */
    public void saveInfoSolicitante(InfoSolicitante entity)
        throws Exception;

    /**
         * Delete an existing InfoSolicitante entity
         *
         */
    public void deleteInfoSolicitante(InfoSolicitante entity)
        throws Exception;

    /**
        * Update an existing InfoSolicitante entity
        *
        */
    public void updateInfoSolicitante(InfoSolicitante entity)
        throws Exception;

    /**
         * Load an existing InfoSolicitante entity
         *
         */
    public InfoSolicitante getInfoSolicitante(Long idInfoSolicitante)
        throws Exception;

    public List<InfoSolicitante> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<InfoSolicitante> findPageInfoSolicitante(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberInfoSolicitante() throws Exception;

    public List<InfoSolicitanteDTO> getDataInfoSolicitante()
        throws Exception;
}
