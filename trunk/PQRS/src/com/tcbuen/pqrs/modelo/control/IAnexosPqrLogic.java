package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.AnexosPqr;
import com.tcbuen.pqrs.modelo.dto.AnexosPqrDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface IAnexosPqrLogic {
    public List<AnexosPqr> getAnexosPqr() throws Exception;

    /**
         * Save an new AnexosPqr entity
         */
    public void saveAnexosPqr(AnexosPqr entity) throws Exception;

    /**
         * Delete an existing AnexosPqr entity
         *
         */
    public void deleteAnexosPqr(AnexosPqr entity) throws Exception;

    /**
        * Update an existing AnexosPqr entity
        *
        */
    public void updateAnexosPqr(AnexosPqr entity) throws Exception;

    /**
         * Load an existing AnexosPqr entity
         *
         */
    public AnexosPqr getAnexosPqr(Long idAnexoPqr) throws Exception;

    public List<AnexosPqr> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<AnexosPqr> findPageAnexosPqr(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberAnexosPqr() throws Exception;

    public List<AnexosPqrDTO> getDataAnexosPqr() throws Exception;
}
