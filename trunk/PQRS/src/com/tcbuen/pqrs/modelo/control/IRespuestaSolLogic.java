package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.RespuestaSol;
import com.tcbuen.pqrs.modelo.dto.RespuestaSolDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface IRespuestaSolLogic {
    public List<RespuestaSol> getRespuestaSol() throws Exception;

    /**
         * Save an new RespuestaSol entity
         */
    public void saveRespuestaSol(RespuestaSol entity) throws Exception;

    /**
         * Delete an existing RespuestaSol entity
         *
         */
    public void deleteRespuestaSol(RespuestaSol entity)
        throws Exception;

    /**
        * Update an existing RespuestaSol entity
        *
        */
    public void updateRespuestaSol(RespuestaSol entity)
        throws Exception;

    /**
         * Load an existing RespuestaSol entity
         *
         */
    public RespuestaSol getRespuestaSol(Long idRespSol)
        throws Exception;

    public List<RespuestaSol> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<RespuestaSol> findPageRespuestaSol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberRespuestaSol() throws Exception;

    public List<RespuestaSolDTO> getDataRespuestaSol()
        throws Exception;
    
    public List<RespuestaSol> consultarRespuestasSolicitud(Long idSolPqr) throws Exception;
}
