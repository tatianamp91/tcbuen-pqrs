package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.modelo.Roles;
import com.tcbuen.pqrs.modelo.dto.RolesDTO;

import java.math.BigDecimal;

import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
public interface IRolesLogic {
    public List<Roles> getRoles() throws Exception;

    /**
         * Save an new Roles entity
         */
    public void saveRoles(Roles entity) throws Exception;

    /**
         * Delete an existing Roles entity
         *
         */
    public void deleteRoles(Roles entity) throws Exception;

    /**
        * Update an existing Roles entity
        *
        */
    public void updateRoles(Roles entity) throws Exception;

    /**
         * Load an existing Roles entity
         *
         */
    public Roles getRoles(Long idRol) throws Exception;

    public List<Roles> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Roles> findPageRoles(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberRoles() throws Exception;

    public List<RolesDTO> getDataRoles() throws Exception;
}
