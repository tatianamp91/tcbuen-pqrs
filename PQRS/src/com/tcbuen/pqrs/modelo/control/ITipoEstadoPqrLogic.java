package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.TipoEstadoPqr;
import com.tcbuen.pqrs.modelo.dto.TipoEstadoPqrDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface ITipoEstadoPqrLogic {
    public List<TipoEstadoPqr> getTipoEstadoPqr() throws Exception;

    /**
         * Save an new TipoEstadoPqr entity
         */
    public void saveTipoEstadoPqr(TipoEstadoPqr entity)
        throws Exception;

    /**
         * Delete an existing TipoEstadoPqr entity
         *
         */
    public void deleteTipoEstadoPqr(TipoEstadoPqr entity)
        throws Exception;

    /**
        * Update an existing TipoEstadoPqr entity
        *
        */
    public void updateTipoEstadoPqr(TipoEstadoPqr entity)
        throws Exception;

    /**
         * Load an existing TipoEstadoPqr entity
         *
         */
    public TipoEstadoPqr getTipoEstadoPqr(Long idTpEstPqr)
        throws Exception;

    public List<TipoEstadoPqr> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<TipoEstadoPqr> findPageTipoEstadoPqr(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberTipoEstadoPqr() throws Exception;

    public List<TipoEstadoPqrDTO> getDataTipoEstadoPqr()
        throws Exception;
}
