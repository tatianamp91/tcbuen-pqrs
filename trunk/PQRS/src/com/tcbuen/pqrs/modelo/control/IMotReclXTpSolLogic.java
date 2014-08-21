package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.MotReclXTpSol;
import com.tcbuen.pqrs.modelo.dto.MotReclXTpSolDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface IMotReclXTpSolLogic {
    public List<MotReclXTpSol> getMotReclXTpSol() throws Exception;

    /**
         * Save an new MotReclXTpSol entity
         */
    public void saveMotReclXTpSol(MotReclXTpSol entity)
        throws Exception;

    /**
         * Delete an existing MotReclXTpSol entity
         *
         */
    public void deleteMotReclXTpSol(MotReclXTpSol entity)
        throws Exception;

    /**
        * Update an existing MotReclXTpSol entity
        *
        */
    public void updateMotReclXTpSol(MotReclXTpSol entity)
        throws Exception;

    /**
         * Load an existing MotReclXTpSol entity
         *
         */
    public MotReclXTpSol getMotReclXTpSol(Long idMotReclXTpSol)
        throws Exception;

    public List<MotReclXTpSol> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<MotReclXTpSol> findPageMotReclXTpSol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberMotReclXTpSol() throws Exception;

    public List<MotReclXTpSolDTO> getDataMotReclXTpSol()
        throws Exception;
}
