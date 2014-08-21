package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.MotReclSelect;
import com.tcbuen.pqrs.modelo.dto.MotReclSelectDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface IMotReclSelectLogic {
    public List<MotReclSelect> getMotReclSelect() throws Exception;

    /**
         * Save an new MotReclSelect entity
         */
    public void saveMotReclSelect(MotReclSelect entity)
        throws Exception;

    /**
         * Delete an existing MotReclSelect entity
         *
         */
    public void deleteMotReclSelect(MotReclSelect entity)
        throws Exception;

    /**
        * Update an existing MotReclSelect entity
        *
        */
    public void updateMotReclSelect(MotReclSelect entity)
        throws Exception;

    /**
         * Load an existing MotReclSelect entity
         *
         */
    public MotReclSelect getMotReclSelect(Long idMotReclSelect)
        throws Exception;

    public List<MotReclSelect> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<MotReclSelect> findPageMotReclSelect(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberMotReclSelect() throws Exception;

    public List<MotReclSelectDTO> getDataMotReclSelect()
        throws Exception;
}
