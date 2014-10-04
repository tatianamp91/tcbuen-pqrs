package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.dataaccess.dao.*;
import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.exceptions.ZMessManager.GettingException;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.AreasInvolucradasDTO;
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
@Service("AreasInvolucradasLogic")
public class AreasInvolucradasLogic implements IAreasInvolucradasLogic {
    /**
     * DAO injected by Spring that manages AreasInvolucradas entities
     *
     */
    @Autowired
    private IAreasInvolucradasDAO areasInvolucradasDAO;

    /**
    * DAO injected by Spring that manages AnxsXArea entities
    *
    */
    @Autowired
    private IAnxsXAreaDAO anxsXAreaDAO;

    /**
    * DAO injected by Spring that manages SolicitudAsignadaArea entities
    *
    */
    @Autowired
    private ISolicitudAsignadaAreaDAO solicitudAsignadaAreaDAO;

    /**
    * DAO injected by Spring that manages UsuariosInternos entities
    *
    */
    @Autowired
    private IUsuariosInternosDAO usuariosInternosDAO;

    @Transactional(readOnly = true)
    public List<AreasInvolucradas> getAreasInvolucradas()
        throws Exception {
        List<AreasInvolucradas> list = new ArrayList<AreasInvolucradas>();

        try {
            list = areasInvolucradasDAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "AreasInvolucradas");
        } finally {
        }

        return list;
    }
    
    @Transactional(readOnly = true)
    public List<AreasInvolucradas> consultarTodasAreaXAnxs() throws Exception{
    	List<AreasInvolucradas> areasInvolucradas = new ArrayList<AreasInvolucradas>();
    	try{
    		areasInvolucradas = areasInvolucradasDAO.consultarTodasAreaXAnxs();
    	}catch(Exception e){
    		throw new ZMessManager().new GettingException(ZMessManager.ALL + 
    				"Areas por Anexos");
    	}
    	return areasInvolucradas;
    }
    
    @Transactional(readOnly = true)
    public List<AreasInvolucradas> consultarNoAreaXAnxs() throws Exception {
    	List<AreasInvolucradas> areasInvolucradas = new ArrayList<AreasInvolucradas>();
    	try{
    		areasInvolucradas = areasInvolucradasDAO.consultarNoAreaXAnxs();
    	}catch(Exception e){
    		throw new ZMessManager().new GettingException(ZMessManager.ALL + 
    				"Areas sin Anexos");
    	}
    	return areasInvolucradas;
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveAreasInvolucradas(AreasInvolucradas entity)
        throws Exception {
        try {
        	/*
        	if (getAreasInvolucradas(entity.getIdAreaInvolucrada()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }
            
            if (entity.getIdAreaInvolucrada() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idAreaInvolucrada");
            }

            if ((entity.getIdAreaInvolucrada() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdAreaInvolucrada(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idAreaInvolucrada");
            }
            */
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


            if (entity.getNombreArea() == null) {
                throw new ZMessManager().new EmptyFieldException("nombreArea");
            }

            if ((entity.getNombreArea() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreArea(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nombreArea");
            }

            if ((entity.getUsuarioCreador() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUsuarioCreador(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "usuarioCreador");
            }

            if ((entity.getUsuarioUltimaModificacion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUsuarioUltimaModificacion(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "usuarioUltimaModificacion");
            }

            areasInvolucradasDAO.save(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteAreasInvolucradas(AreasInvolucradas entity)
        throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "AreasInvolucradas");
        }

        if (entity.getIdAreaInvolucrada() == null) {
            throw new ZMessManager().new EmptyFieldException(
                "idAreaInvolucrada");
        }

        List<AnxsXArea> anxsXAreas = null;
        List<SolicitudAsignadaArea> solicitudAsignadaAreas = null;
        List<UsuariosInternos> usuariosInternoses = null;

        try {
            anxsXAreas = anxsXAreaDAO.findByProperty("areasInvolucradas.idAreaInvolucrada",
                    entity.getIdAreaInvolucrada());

            if (Utilities.validationsList(anxsXAreas) == true) {
                throw new ZMessManager().new DeletingException("anxsXAreas");
            }

            solicitudAsignadaAreas = solicitudAsignadaAreaDAO.findByProperty("areasInvolucradas.idAreaInvolucrada",
                    entity.getIdAreaInvolucrada());

            if (Utilities.validationsList(solicitudAsignadaAreas) == true) {
                throw new ZMessManager().new DeletingException(
                    "solicitudAsignadaAreas");
            }

            usuariosInternoses = usuariosInternosDAO.findByProperty("areasInvolucradas.idAreaInvolucrada",
                    entity.getIdAreaInvolucrada());

            if (Utilities.validationsList(usuariosInternoses) == true) {
                throw new ZMessManager().new DeletingException(
                    "usuariosInternoses");
            }

            areasInvolucradasDAO.delete(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateAreasInvolucradas(AreasInvolucradas entity)
        throws Exception {
        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "AreasInvolucradas");
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

            if (entity.getIdAreaInvolucrada() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idAreaInvolucrada");
            }

            if ((entity.getIdAreaInvolucrada() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdAreaInvolucrada(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idAreaInvolucrada");
            }

            if (entity.getNombreArea() == null) {
                throw new ZMessManager().new EmptyFieldException("nombreArea");
            }

            if ((entity.getNombreArea() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreArea(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nombreArea");
            }

            if ((entity.getUsuarioCreador() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUsuarioCreador(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "usuarioCreador");
            }

            if ((entity.getUsuarioUltimaModificacion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUsuarioUltimaModificacion(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "usuarioUltimaModificacion");
            }

            areasInvolucradasDAO.update(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<AreasInvolucradasDTO> getDataAreasInvolucradas()
        throws Exception {
        try {
            List<AreasInvolucradas> areasInvolucradas = areasInvolucradasDAO.findAll();

            List<AreasInvolucradasDTO> areasInvolucradasDTO = new ArrayList<AreasInvolucradasDTO>();

            for (AreasInvolucradas areasInvolucradasTmp : areasInvolucradas) {
                AreasInvolucradasDTO areasInvolucradasDTO2 = new AreasInvolucradasDTO();

                areasInvolucradasDTO2.setIdAreaInvolucrada(areasInvolucradasTmp.getIdAreaInvolucrada());
                areasInvolucradasDTO2.setEstadoRegistro((areasInvolucradasTmp.getEstadoRegistro() != null)
                    ? areasInvolucradasTmp.getEstadoRegistro() : null);
                areasInvolucradasDTO2.setFechaCreacion(areasInvolucradasTmp.getFechaCreacion());
                areasInvolucradasDTO2.setFechaUltimaModificacion(areasInvolucradasTmp.getFechaUltimaModificacion());
                areasInvolucradasDTO2.setNombreArea((areasInvolucradasTmp.getNombreArea() != null)
                    ? areasInvolucradasTmp.getNombreArea() : null);
                areasInvolucradasDTO2.setUsuarioCreador((areasInvolucradasTmp.getUsuarioCreador() != null)
                    ? areasInvolucradasTmp.getUsuarioCreador() : null);
                areasInvolucradasDTO2.setUsuarioUltimaModificacion((areasInvolucradasTmp.getUsuarioUltimaModificacion() != null)
                    ? areasInvolucradasTmp.getUsuarioUltimaModificacion() : null);
                areasInvolucradasDTO.add(areasInvolucradasDTO2);
            }

            return areasInvolucradasDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public AreasInvolucradas getAreasInvolucradas(Long idAreaInvolucrada)
        throws Exception {
        AreasInvolucradas entity = null;

        try {
            entity = areasInvolucradasDAO.findById(idAreaInvolucrada);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("AreasInvolucradas");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<AreasInvolucradas> findPageAreasInvolucradas(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<AreasInvolucradas> entity = null;

        try {
            entity = areasInvolucradasDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "AreasInvolucradas Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberAreasInvolucradas() throws Exception {
        Long entity = null;

        try {
            entity = areasInvolucradasDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "AreasInvolucradas Count");
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
    public List<AreasInvolucradas> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<AreasInvolucradas> list = new ArrayList<AreasInvolucradas>();
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
            list = areasInvolucradasDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}