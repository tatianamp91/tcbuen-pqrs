package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.AnexosSolicitante;
import com.tcbuen.pqrs.modelo.dto.AnexosSolicitanteDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface IAnexosSolicitanteLogic {
    public List<AnexosSolicitante> getAnexosSolicitante()
        throws Exception;

    /**
         * Save an new AnexosSolicitante entity
         */
    public void saveAnexosSolicitante(AnexosSolicitante entity)
        throws Exception;

    /**
         * Delete an existing AnexosSolicitante entity
         *
         */
    public void deleteAnexosSolicitante(AnexosSolicitante entity)
        throws Exception;

    /**
        * Update an existing AnexosSolicitante entity
        *
        */
    public void updateAnexosSolicitante(AnexosSolicitante entity)
        throws Exception;

    /**
         * Load an existing AnexosSolicitante entity
         *
         */
    public AnexosSolicitante getAnexosSolicitante(Long idAnxSolicitante)
        throws Exception;

    public List<AnexosSolicitante> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<AnexosSolicitante> findPageAnexosSolicitante(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberAnexosSolicitante() throws Exception;

    public List<AnexosSolicitanteDTO> getDataAnexosSolicitante()
        throws Exception;
}
