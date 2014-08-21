package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.AnxsXTpSol;
import com.tcbuen.pqrs.modelo.dto.AnxsXTpSolDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface IAnxsXTpSolLogic {
    public List<AnxsXTpSol> getAnxsXTpSol() throws Exception;

    /**
         * Save an new AnxsXTpSol entity
         */
    public void saveAnxsXTpSol(AnxsXTpSol entity) throws Exception;

    /**
         * Delete an existing AnxsXTpSol entity
         *
         */
    public void deleteAnxsXTpSol(AnxsXTpSol entity) throws Exception;

    /**
        * Update an existing AnxsXTpSol entity
        *
        */
    public void updateAnxsXTpSol(AnxsXTpSol entity) throws Exception;

    /**
         * Load an existing AnxsXTpSol entity
         *
         */
    public AnxsXTpSol getAnxsXTpSol(Long idAnexoXTpSol)
        throws Exception;

    public List<AnxsXTpSol> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<AnxsXTpSol> findPageAnxsXTpSol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberAnxsXTpSol() throws Exception;

    public List<AnxsXTpSolDTO> getDataAnxsXTpSol() throws Exception;
}
