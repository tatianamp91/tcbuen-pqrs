package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.AreasInvolucradas;
import com.tcbuen.pqrs.modelo.dto.AreasInvolucradasDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface IAreasInvolucradasLogic {
    public List<AreasInvolucradas> getAreasInvolucradas()
        throws Exception;

    /**
         * Save an new AreasInvolucradas entity
         */
    public void saveAreasInvolucradas(AreasInvolucradas entity)
        throws Exception;

    /**
         * Delete an existing AreasInvolucradas entity
         *
         */
    public void deleteAreasInvolucradas(AreasInvolucradas entity)
        throws Exception;

    /**
        * Update an existing AreasInvolucradas entity
        *
        */
    public void updateAreasInvolucradas(AreasInvolucradas entity)
        throws Exception;

    /**
         * Load an existing AreasInvolucradas entity
         *
         */
    public AreasInvolucradas getAreasInvolucradas(Long idAreaInvolucrada)
        throws Exception;

    public List<AreasInvolucradas> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<AreasInvolucradas> findPageAreasInvolucradas(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberAreasInvolucradas() throws Exception;

    public List<AreasInvolucradasDTO> getDataAreasInvolucradas()
        throws Exception;
}
