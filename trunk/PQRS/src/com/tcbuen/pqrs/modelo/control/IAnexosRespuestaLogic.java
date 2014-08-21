package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.AnexosRespuesta;
import com.tcbuen.pqrs.modelo.dto.AnexosRespuestaDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface IAnexosRespuestaLogic {
    public List<AnexosRespuesta> getAnexosRespuesta() throws Exception;

    /**
         * Save an new AnexosRespuesta entity
         */
    public void saveAnexosRespuesta(AnexosRespuesta entity)
        throws Exception;

    /**
         * Delete an existing AnexosRespuesta entity
         *
         */
    public void deleteAnexosRespuesta(AnexosRespuesta entity)
        throws Exception;

    /**
        * Update an existing AnexosRespuesta entity
        *
        */
    public void updateAnexosRespuesta(AnexosRespuesta entity)
        throws Exception;

    /**
         * Load an existing AnexosRespuesta entity
         *
         */
    public AnexosRespuesta getAnexosRespuesta(Long idAnxResp)
        throws Exception;

    public List<AnexosRespuesta> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<AnexosRespuesta> findPageAnexosRespuesta(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberAnexosRespuesta() throws Exception;

    public List<AnexosRespuestaDTO> getDataAnexosRespuesta()
        throws Exception;
}
