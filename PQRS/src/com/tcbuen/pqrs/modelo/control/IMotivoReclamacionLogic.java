package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.MotivoReclamacion;
import com.tcbuen.pqrs.modelo.dto.MotivoReclamacionDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface IMotivoReclamacionLogic {
    public List<MotivoReclamacion> getMotivoReclamacion()
        throws Exception;

    /**
         * Save an new MotivoReclamacion entity
         */
    public void saveMotivoReclamacion(MotivoReclamacion entity)
        throws Exception;

    /**
         * Delete an existing MotivoReclamacion entity
         *
         */
    public void deleteMotivoReclamacion(MotivoReclamacion entity)
        throws Exception;

    /**
        * Update an existing MotivoReclamacion entity
        *
        */
    public void updateMotivoReclamacion(MotivoReclamacion entity)
        throws Exception;

    /**
         * Load an existing MotivoReclamacion entity
         *
         */
    public MotivoReclamacion getMotivoReclamacion(Long idMotRecl)
        throws Exception;

    public List<MotivoReclamacion> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<MotivoReclamacion> findPageMotivoReclamacion(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberMotivoReclamacion() throws Exception;

    public List<MotivoReclamacionDTO> getDataMotivoReclamacion()
        throws Exception;
}
