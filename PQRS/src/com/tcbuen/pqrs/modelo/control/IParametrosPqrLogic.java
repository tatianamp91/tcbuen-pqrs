package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.ParametrosPqr;
import com.tcbuen.pqrs.modelo.dto.ParametrosPqrDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface IParametrosPqrLogic {
    public List<ParametrosPqr> getParametrosPqr() throws Exception;

    /**
         * Save an new ParametrosPqr entity
         */
    public void saveParametrosPqr(ParametrosPqr entity)
        throws Exception;

    /**
         * Delete an existing ParametrosPqr entity
         *
         */
    public void deleteParametrosPqr(ParametrosPqr entity)
        throws Exception;

    /**
        * Update an existing ParametrosPqr entity
        *
        */
    public void updateParametrosPqr(ParametrosPqr entity)
        throws Exception;

    /**
         * Load an existing ParametrosPqr entity
         *
         */
    public ParametrosPqr getParametrosPqr(Long idParam)
        throws Exception;

    public List<ParametrosPqr> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<ParametrosPqr> findPageParametrosPqr(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberParametrosPqr() throws Exception;

    public List<ParametrosPqrDTO> getDataParametrosPqr()
        throws Exception;
}
