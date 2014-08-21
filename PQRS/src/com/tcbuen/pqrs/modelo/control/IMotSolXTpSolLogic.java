package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.MotSolXTpSol;
import com.tcbuen.pqrs.modelo.dto.MotSolXTpSolDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface IMotSolXTpSolLogic {
    public List<MotSolXTpSol> getMotSolXTpSol() throws Exception;

    /**
         * Save an new MotSolXTpSol entity
         */
    public void saveMotSolXTpSol(MotSolXTpSol entity) throws Exception;

    /**
         * Delete an existing MotSolXTpSol entity
         *
         */
    public void deleteMotSolXTpSol(MotSolXTpSol entity)
        throws Exception;

    /**
        * Update an existing MotSolXTpSol entity
        *
        */
    public void updateMotSolXTpSol(MotSolXTpSol entity)
        throws Exception;

    /**
         * Load an existing MotSolXTpSol entity
         *
         */
    public MotSolXTpSol getMotSolXTpSol(Long idMotSolXTpSol)
        throws Exception;

    public List<MotSolXTpSol> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<MotSolXTpSol> findPageMotSolXTpSol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberMotSolXTpSol() throws Exception;

    public List<MotSolXTpSolDTO> getDataMotSolXTpSol()
        throws Exception;
}
