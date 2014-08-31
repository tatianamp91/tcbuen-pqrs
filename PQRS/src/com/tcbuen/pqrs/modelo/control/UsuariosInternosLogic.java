package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.dataaccess.dao.*;
import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.UsuariosInternosDTO;
import com.tcbuen.pqrs.utilities.Utilities;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
* @author Zathura Code Generator http://code.google.com/p/zathura
*
*/
@Scope("singleton")
@Service("UsuariosInternosLogic")
public class UsuariosInternosLogic implements IUsuariosInternosLogic {
    /**
     * DAO injected by Spring that manages UsuariosInternos entities
     *
     */
    @Autowired
    private IUsuariosInternosDAO usuariosInternosDAO;

    /**
    * Logic injected by Spring that manages AreasInvolucradas entities
    *
    */
    @Autowired
    IAreasInvolucradasLogic logicAreasInvolucradas1;

    /**
    * Logic injected by Spring that manages Roles entities
    *
    */
    @Autowired
    IRolesLogic logicRoles2;

    @Transactional(readOnly = true)
    public List<UsuariosInternos> getUsuariosInternos()
        throws Exception {
        List<UsuariosInternos> list = new ArrayList<UsuariosInternos>();

        try {
            list = usuariosInternosDAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "UsuariosInternos");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveUsuariosInternos(UsuariosInternos entity)
        throws Exception {
        try {
            if (entity.getAreasInvolucradas() == null) {
                throw new ZMessManager().new ForeignException(
                    "areasInvolucradas");
            }

            if (entity.getRoles() == null) {
                throw new ZMessManager().new ForeignException("roles");
            }

            if (entity.getApellidos() == null) {
                throw new ZMessManager().new EmptyFieldException("apellidos");
            }

            if ((entity.getApellidos() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getApellidos(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "apellidos");
            }

            if (entity.getContrasena() == null) {
                throw new ZMessManager().new EmptyFieldException("contrasena");
            }

            if ((entity.getContrasena() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getContrasena(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "contrasena");
            }

            if ((entity.getCorreoElectronico() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getCorreoElectronico(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "correoElectronico");
            }

            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "estadoRegistro");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "estadoRegistro");
            }

           /*if (entity.getIdUsuInterno() == null) {
                throw new ZMessManager().new EmptyFieldException("idUsuInterno");
            }

            if ((entity.getIdUsuInterno() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdUsuInterno(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idUsuInterno");
            }*/

            if (entity.getLogin() == null) {
                throw new ZMessManager().new EmptyFieldException("login");
            }

            if ((entity.getLogin() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getLogin(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException("login");
            }

            if (entity.getNombres() == null) {
                throw new ZMessManager().new EmptyFieldException("nombres");
            }

            if ((entity.getNombres() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombres(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombres");
            }

            if (entity.getNumeroIdentificacion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "numeroIdentificacion");
            }

            if ((entity.getNumeroIdentificacion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNumeroIdentificacion(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "numeroIdentificacion");
            }

            if (entity.getAreasInvolucradas().getIdAreaInvolucrada() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idAreaInvolucrada_AreasInvolucradas");
            }

            if ((entity.getAreasInvolucradas().getIdAreaInvolucrada() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getAreasInvolucradas().getIdAreaInvolucrada(),
                        10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idAreaInvolucrada_AreasInvolucradas");
            }

            if (entity.getRoles().getIdRol() == null) {
                throw new ZMessManager().new EmptyFieldException("idRol_Roles");
            }

            if ((entity.getRoles().getIdRol() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getRoles().getIdRol(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idRol_Roles");
            }

           /* if (getUsuariosInternos(entity.getIdUsuInterno()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }*/

            usuariosInternosDAO.save(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteUsuariosInternos(UsuariosInternos entity)
        throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("UsuariosInternos");
        }

        if (entity.getIdUsuInterno() == null) {
            throw new ZMessManager().new EmptyFieldException("idUsuInterno");
        }

        try {
            usuariosInternosDAO.delete(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateUsuariosInternos(UsuariosInternos entity)
        throws Exception {
        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "UsuariosInternos");
            }

            if (entity.getAreasInvolucradas() == null) {
                throw new ZMessManager().new ForeignException(
                    "areasInvolucradas");
            }

            if (entity.getRoles() == null) {
                throw new ZMessManager().new ForeignException("roles");
            }

            if (entity.getApellidos() == null) {
                throw new ZMessManager().new EmptyFieldException("apellidos");
            }

            if ((entity.getApellidos() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getApellidos(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "apellidos");
            }

            if (entity.getContrasena() == null) {
                throw new ZMessManager().new EmptyFieldException("contrasena");
            }

            if ((entity.getContrasena() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getContrasena(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "contrasena");
            }

            if ((entity.getCorreoElectronico() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getCorreoElectronico(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "correoElectronico");
            }

            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "estadoRegistro");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "estadoRegistro");
            }

            if (entity.getIdUsuInterno() == null) {
                throw new ZMessManager().new EmptyFieldException("idUsuInterno");
            }

            if ((entity.getIdUsuInterno() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdUsuInterno(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idUsuInterno");
            }

            if (entity.getLogin() == null) {
                throw new ZMessManager().new EmptyFieldException("login");
            }

            if ((entity.getLogin() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getLogin(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException("login");
            }

            if (entity.getNombres() == null) {
                throw new ZMessManager().new EmptyFieldException("nombres");
            }

            if ((entity.getNombres() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombres(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException("nombres");
            }

            if (entity.getNumeroIdentificacion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "numeroIdentificacion");
            }

            if ((entity.getNumeroIdentificacion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNumeroIdentificacion(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "numeroIdentificacion");
            }

            if (entity.getAreasInvolucradas().getIdAreaInvolucrada() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idAreaInvolucrada_AreasInvolucradas");
            }

            if ((entity.getAreasInvolucradas().getIdAreaInvolucrada() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getAreasInvolucradas().getIdAreaInvolucrada(),
                        10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idAreaInvolucrada_AreasInvolucradas");
            }

            if (entity.getRoles().getIdRol() == null) {
                throw new ZMessManager().new EmptyFieldException("idRol_Roles");
            }

            if ((entity.getRoles().getIdRol() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getRoles().getIdRol(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idRol_Roles");
            }

            usuariosInternosDAO.update(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<UsuariosInternosDTO> getDataUsuariosInternos()
        throws Exception {
        try {
            List<UsuariosInternos> usuariosInternos = usuariosInternosDAO.findAll();

            List<UsuariosInternosDTO> usuariosInternosDTO = new ArrayList<UsuariosInternosDTO>();

            for (UsuariosInternos usuariosInternosTmp : usuariosInternos) {
                UsuariosInternosDTO usuariosInternosDTO2 = new UsuariosInternosDTO();

                usuariosInternosDTO2.setIdUsuInterno(usuariosInternosTmp.getIdUsuInterno());
                usuariosInternosDTO2.setApellidos((usuariosInternosTmp.getApellidos() != null)
                    ? usuariosInternosTmp.getApellidos() : null);
                usuariosInternosDTO2.setContrasena((usuariosInternosTmp.getContrasena() != null)
                    ? usuariosInternosTmp.getContrasena() : null);
                usuariosInternosDTO2.setCorreoElectronico((usuariosInternosTmp.getCorreoElectronico() != null)
                    ? usuariosInternosTmp.getCorreoElectronico() : null);
                usuariosInternosDTO2.setEstadoRegistro((usuariosInternosTmp.getEstadoRegistro() != null)
                    ? usuariosInternosTmp.getEstadoRegistro() : null);
                usuariosInternosDTO2.setFechaCreacion(usuariosInternosTmp.getFechaCreacion());
                usuariosInternosDTO2.setLogin((usuariosInternosTmp.getLogin() != null)
                    ? usuariosInternosTmp.getLogin() : null);
                usuariosInternosDTO2.setNombres((usuariosInternosTmp.getNombres() != null)
                    ? usuariosInternosTmp.getNombres() : null);
                usuariosInternosDTO2.setNumeroIdentificacion((usuariosInternosTmp.getNumeroIdentificacion() != null)
                    ? usuariosInternosTmp.getNumeroIdentificacion() : null);
                usuariosInternosDTO2.setIdAreaInvolucrada_AreasInvolucradas((usuariosInternosTmp.getAreasInvolucradas()
                                                                                                .getIdAreaInvolucrada() != null)
                    ? usuariosInternosTmp.getAreasInvolucradas()
                                         .getIdAreaInvolucrada() : null);

                if (usuariosInternosTmp.getRoles() != null) {
                    usuariosInternosDTO2.setIdRol_Roles(usuariosInternosTmp.getRoles()
                                                                           .getIdRol());
                } else {
                    usuariosInternosDTO2.setIdRol_Roles(null);
                }

                usuariosInternosDTO.add(usuariosInternosDTO2);
            }

            return usuariosInternosDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public UsuariosInternos getUsuariosInternos(Long idUsuInterno)
        throws Exception {
        UsuariosInternos entity = null;

        try {
            entity = usuariosInternosDAO.findById(idUsuInterno);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("UsuariosInternos");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<UsuariosInternos> findPageUsuariosInternos(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<UsuariosInternos> entity = null;

        try {
            entity = usuariosInternosDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "UsuariosInternos Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberUsuariosInternos() throws Exception {
        Long entity = null;

        try {
            entity = usuariosInternosDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "UsuariosInternos Count");
        } finally {
        }

        return entity;
    }

    /**
    *
    * @param varibles
    *            este arreglo debera tener:
    *
    * [0] = String variable = (String) varibles[i]; representa como se llama la
    * variable en el pojo
    *
    * [1] = Boolean booVariable = (Boolean) varibles[i + 1]; representa si el
    * valor necesita o no ''(comillas simples)usado para campos de tipo string
    *
    * [2] = Object value = varibles[i + 2]; representa el valor que se va a
    * buscar en la BD
    *
    * [3] = String comparator = (String) varibles[i + 3]; representa que tipo
    * de busqueda voy a hacer.., ejemplo: where nombre=william o where nombre<>william,
        * en este campo iria el tipo de comparador que quiero si es = o <>
            *
            * Se itera de 4 en 4..., entonces 4 registros del arreglo representan 1
            * busqueda en un campo, si se ponen mas pues el continuara buscando en lo
            * que se le ingresen en los otros 4
            *
            *
            * @param variablesBetween
            *
            * la diferencia son estas dos posiciones
            *
            * [0] = String variable = (String) varibles[j]; la variable ne la BD que va
            * a ser buscada en un rango
            *
            * [1] = Object value = varibles[j + 1]; valor 1 para buscar en un rango
            *
            * [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en un rango
            * ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria value2
                *
                * [3] = String comparator1 = (String) varibles[j + 3]; comparador 1
                * ejemplo: a comparator1 1 and a < 5
                    *
                    * [4] = String comparator2 = (String) varibles[j + 4]; comparador 2
                    * ejemplo: a comparador1>1  and a comparador2<5  (el original: a > 1 and a <
                            * 5) *
                            * @param variablesBetweenDates(en
                            *            este caso solo para mysql)
                            *  [0] = String variable = (String) varibles[k]; el nombre de la variable que hace referencia a
                            *            una fecha
                            *
                            * [1] = Object object1 = varibles[k + 2]; fecha 1 a comparar(deben ser
                            * dates)
                            *
                            * [2] = Object object2 = varibles[k + 3]; fecha 2 a comparar(deben ser
                            * dates)
                            *
                            * esto hace un between entre las dos fechas.
                            *
                            * @return lista con los objetos que se necesiten
                            * @throws Exception
                            */
    @Transactional(readOnly = true)
    public List<UsuariosInternos> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<UsuariosInternos> list = new ArrayList<UsuariosInternos>();
        String where = new String();
        String tempWhere = new String();

        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                if ((variables[i] != null) && (variables[i + 1] != null) &&
                        (variables[i + 2] != null) &&
                        (variables[i + 3] != null)) {
                    String variable = (String) variables[i];
                    Boolean booVariable = (Boolean) variables[i + 1];
                    Object value = variables[i + 2];
                    String comparator = (String) variables[i + 3];

                    if (booVariable.booleanValue()) {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " \'" +
                            value + "\' )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " \'" + value + "\' )");
                    } else {
                        tempWhere = (tempWhere.length() == 0)
                            ? ("(model." + variable + " " + comparator + " " +
                            value + " )")
                            : (tempWhere + " AND (model." + variable + " " +
                            comparator + " " + value + " )");
                    }
                }

                i = i + 3;
            }
        }

        if (variablesBetween != null) {
            for (int j = 0; j < variablesBetween.length; j++) {
                if ((variablesBetween[j] != null) &&
                        (variablesBetween[j + 1] != null) &&
                        (variablesBetween[j + 2] != null) &&
                        (variablesBetween[j + 3] != null) &&
                        (variablesBetween[j + 4] != null)) {
                    String variable = (String) variablesBetween[j];
                    Object value = variablesBetween[j + 1];
                    Object value2 = variablesBetween[j + 2];
                    String comparator1 = (String) variablesBetween[j + 3];
                    String comparator2 = (String) variablesBetween[j + 4];
                    tempWhere = (tempWhere.length() == 0)
                        ? ("(" + value + " " + comparator1 + " " + variable +
                        " and " + variable + " " + comparator2 + " " + value2 +
                        " )")
                        : (tempWhere + " AND (" + value + " " + comparator1 +
                        " " + variable + " and " + variable + " " +
                        comparator2 + " " + value2 + " )");
                }

                j = j + 4;
            }
        }

        if (variablesBetweenDates != null) {
            for (int k = 0; k < variablesBetweenDates.length; k++) {
                if ((variablesBetweenDates[k] != null) &&
                        (variablesBetweenDates[k + 1] != null) &&
                        (variablesBetweenDates[k + 2] != null)) {
                    String variable = (String) variablesBetweenDates[k];
                    Object object1 = variablesBetweenDates[k + 1];
                    Object object2 = variablesBetweenDates[k + 2];
                    String value = null;
                    String value2 = null;

                    try {
                        Date date1 = (Date) object1;
                        Date date2 = (Date) object2;
                        value = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date1);
                        value2 = Utilities.formatDateWithoutTimeInAStringForBetweenWhere(date2);
                    } catch (Exception e) {
                        list = null;
                        throw e;
                    }

                    tempWhere = (tempWhere.length() == 0)
                        ? ("(model." + variable + " between \'" + value +
                        "\' and \'" + value2 + "\')")
                        : (tempWhere + " AND (model." + variable +
                        " between \'" + value + "\' and \'" + value2 + "\')");
                }

                k = k + 2;
            }
        }

        if (tempWhere.length() == 0) {
            where = null;
        } else {
            where = "(" + tempWhere + ")";
        }

        try {
            list = usuariosInternosDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
