package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.MotSolSelect;
import com.tcbuen.pqrs.modelo.dto.MotSolSelectDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface IMotSolSelectLogic {
    public List<MotSolSelect> getMotSolSelect() throws Exception;

    /**
         * Save an new MotSolSelect entity
         */
    public void saveMotSolSelect(MotSolSelect entity) throws Exception;

    /**
         * Delete an existing MotSolSelect entity
         *
         */
    public void deleteMotSolSelect(MotSolSelect entity)
        throws Exception;

    /**
        * Update an existing MotSolSelect entity
        *
        */
    public void updateMotSolSelect(MotSolSelect entity)
        throws Exception;

    /**
         * Load an existing MotSolSelect entity
         *
         */
    public MotSolSelect getMotSolSelect(Long idMotSolSelected)
        throws Exception;

    public List<MotSolSelect> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<MotSolSelect> findPageMotSolSelect(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberMotSolSelect() throws Exception;

    public List<MotSolSelectDTO> getDataMotSolSelect()
        throws Exception;
}
