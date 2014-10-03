package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.AnexosPqr;
import com.tcbuen.pqrs.modelo.AnxsXArea;
import com.tcbuen.pqrs.modelo.AreasInvolucradas;
import com.tcbuen.pqrs.modelo.dto.AnxsXAreaDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface IAnxsXAreaLogic {
    public List<AnxsXArea> getAnxsXArea() throws Exception;

    /**
         * Save an new AnxsXArea entity
         */
    public void saveAnxsXArea(AnxsXArea entity) throws Exception;

    /**
         * Delete an existing AnxsXArea entity
         *
         */
    public void deleteAnxsXArea(AnxsXArea entity) throws Exception;

    /**
        * Update an existing AnxsXArea entity
        *
        */
    public void updateAnxsXArea(AnxsXArea entity) throws Exception;

    public void save_anxs_x_area(AreasInvolucradas areasInvolucradas,
			List<AnexosPqr> anexosPqrTargetCopia, List<AnexosPqr> anexosPqrTarget, 
			String esObligatorioSeleccionado)  throws Exception;
    
    /**
         * Load an existing AnxsXArea entity
         *
         */
    public AnxsXArea getAnxsXArea(Long idAnxXArea) throws Exception;

    public List<AnxsXArea> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<AnxsXArea> findPageAnxsXArea(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberAnxsXArea() throws Exception;

    public List<AnxsXAreaDTO> getDataAnxsXArea() throws Exception;
}
