package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.UsuariosInternos;
import com.tcbuen.pqrs.modelo.dto.UsuariosInternosDTO;

import java.math.BigDecimal;
import java.util.*;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface IUsuariosInternosLogic {
    public List<UsuariosInternos> getUsuariosInternos()
        throws Exception;

    /**
         * Save an new UsuariosInternos entity
         */
    public void saveUsuariosInternos(UsuariosInternos entity)
        throws Exception;

    /**
         * Delete an existing UsuariosInternos entity
         *
         */
    public void deleteUsuariosInternos(UsuariosInternos entity)
        throws Exception;

    /**
        * Update an existing UsuariosInternos entity
        *
        */
    public void updateUsuariosInternos(UsuariosInternos entity)
        throws Exception;

    /**
         * Load an existing UsuariosInternos entity
         *
         */
    public UsuariosInternos getUsuariosInternos(Long idUsuInterno)
        throws Exception;

    public List<UsuariosInternos> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<UsuariosInternos> findPageUsuariosInternos(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberUsuariosInternos() throws Exception;

    public List<UsuariosInternosDTO> getDataUsuariosInternos()
        throws Exception;
    
    public UsuariosInternos consultarLoginContrasena(String login, String contrasena) 
    	throws Exception;
}
