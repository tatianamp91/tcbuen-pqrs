package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.MotivoSolicitud;
import com.tcbuen.pqrs.modelo.dto.MotivoSolicitudDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface IMotivoSolicitudLogic {
    public List<MotivoSolicitud> getMotivoSolicitud() throws Exception;

    /**
         * Save an new MotivoSolicitud entity
         */
    public void saveMotivoSolicitud(MotivoSolicitud entity)
        throws Exception;

    /**
         * Delete an existing MotivoSolicitud entity
         *
         */
    public void deleteMotivoSolicitud(MotivoSolicitud entity)
        throws Exception;

    /**
        * Update an existing MotivoSolicitud entity
        *
        */
    public void updateMotivoSolicitud(MotivoSolicitud entity)
        throws Exception;

    /**
         * Load an existing MotivoSolicitud entity
         *
         */
    public MotivoSolicitud getMotivoSolicitud(Long idMotSol)
        throws Exception;

    public List<MotivoSolicitud> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<MotivoSolicitud> findPageMotivoSolicitud(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberMotivoSolicitud() throws Exception;

    public List<MotivoSolicitudDTO> getDataMotivoSolicitud()
        throws Exception;
}
