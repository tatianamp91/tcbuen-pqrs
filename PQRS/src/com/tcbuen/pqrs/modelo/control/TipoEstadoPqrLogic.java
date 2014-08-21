package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.dataaccess.dao.*;
import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.TipoEstadoPqrDTO;
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
@Service("TipoEstadoPqrLogic")
public class TipoEstadoPqrLogic implements ITipoEstadoPqrLogic {
    /**
     * DAO injected by Spring that manages TipoEstadoPqr entities
     *
     */
    @Autowired
    private ITipoEstadoPqrDAO tipoEstadoPqrDAO;

    /**
    * DAO injected by Spring that manages SolicitudPqr entities
    *
    */
    @Autowired
    private ISolicitudPqrDAO solicitudPqrDAO;

    @Transactional(readOnly = true)
    public List<TipoEstadoPqr> getTipoEstadoPqr() throws Exception {
        List<TipoEstadoPqr> list = new ArrayList<TipoEstadoPqr>();

        try {
            list = tipoEstadoPqrDAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "TipoEstadoPqr");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveTipoEstadoPqr(TipoEstadoPqr entity)
        throws Exception {
        try {
            if (entity.getDescripcionEstado() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "descripcionEstado");
            }

            if ((entity.getDescripcionEstado() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcionEstado(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcionEstado");
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

            if (entity.getIdTpEstPqr() == null) {
                throw new ZMessManager().new EmptyFieldException("idTpEstPqr");
            }

            if ((entity.getIdTpEstPqr() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdTpEstPqr(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idTpEstPqr");
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

            if (getTipoEstadoPqr(entity.getIdTpEstPqr()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            tipoEstadoPqrDAO.save(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteTipoEstadoPqr(TipoEstadoPqr entity)
        throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("TipoEstadoPqr");
        }

        if (entity.getIdTpEstPqr() == null) {
            throw new ZMessManager().new EmptyFieldException("idTpEstPqr");
        }

        List<SolicitudPqr> solicitudPqrs = null;

        try {
            solicitudPqrs = solicitudPqrDAO.findByProperty("tipoEstadoPqr.idTpEstPqr",
                    entity.getIdTpEstPqr());

            if (Utilities.validationsList(solicitudPqrs) == true) {
                throw new ZMessManager().new DeletingException("solicitudPqrs");
            }

            tipoEstadoPqrDAO.delete(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateTipoEstadoPqr(TipoEstadoPqr entity)
        throws Exception {
        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "TipoEstadoPqr");
            }

            if (entity.getDescripcionEstado() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "descripcionEstado");
            }

            if ((entity.getDescripcionEstado() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcionEstado(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcionEstado");
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

            if (entity.getIdTpEstPqr() == null) {
                throw new ZMessManager().new EmptyFieldException("idTpEstPqr");
            }

            if ((entity.getIdTpEstPqr() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdTpEstPqr(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idTpEstPqr");
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

            tipoEstadoPqrDAO.update(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<TipoEstadoPqrDTO> getDataTipoEstadoPqr()
        throws Exception {
        try {
            List<TipoEstadoPqr> tipoEstadoPqr = tipoEstadoPqrDAO.findAll();

            List<TipoEstadoPqrDTO> tipoEstadoPqrDTO = new ArrayList<TipoEstadoPqrDTO>();

            for (TipoEstadoPqr tipoEstadoPqrTmp : tipoEstadoPqr) {
                TipoEstadoPqrDTO tipoEstadoPqrDTO2 = new TipoEstadoPqrDTO();

                tipoEstadoPqrDTO2.setIdTpEstPqr(tipoEstadoPqrTmp.getIdTpEstPqr());
                tipoEstadoPqrDTO2.setDescripcionEstado((tipoEstadoPqrTmp.getDescripcionEstado() != null)
                    ? tipoEstadoPqrTmp.getDescripcionEstado() : null);
                tipoEstadoPqrDTO2.setEstadoRegistro((tipoEstadoPqrTmp.getEstadoRegistro() != null)
                    ? tipoEstadoPqrTmp.getEstadoRegistro() : null);
                tipoEstadoPqrDTO2.setFechaCreacion(tipoEstadoPqrTmp.getFechaCreacion());
                tipoEstadoPqrDTO2.setFechaUltimaModificacion(tipoEstadoPqrTmp.getFechaUltimaModificacion());
                tipoEstadoPqrDTO2.setUsuarioCreador((tipoEstadoPqrTmp.getUsuarioCreador() != null)
                    ? tipoEstadoPqrTmp.getUsuarioCreador() : null);
                tipoEstadoPqrDTO2.setUsuarioUltimaModificacion((tipoEstadoPqrTmp.getUsuarioUltimaModificacion() != null)
                    ? tipoEstadoPqrTmp.getUsuarioUltimaModificacion() : null);
                tipoEstadoPqrDTO.add(tipoEstadoPqrDTO2);
            }

            return tipoEstadoPqrDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public TipoEstadoPqr getTipoEstadoPqr(Long idTpEstPqr)
        throws Exception {
        TipoEstadoPqr entity = null;

        try {
            entity = tipoEstadoPqrDAO.findById(idTpEstPqr);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("TipoEstadoPqr");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<TipoEstadoPqr> findPageTipoEstadoPqr(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<TipoEstadoPqr> entity = null;

        try {
            entity = tipoEstadoPqrDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("TipoEstadoPqr Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberTipoEstadoPqr() throws Exception {
        Long entity = null;

        try {
            entity = tipoEstadoPqrDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("TipoEstadoPqr Count");
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
    public List<TipoEstadoPqr> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<TipoEstadoPqr> list = new ArrayList<TipoEstadoPqr>();
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
            list = tipoEstadoPqrDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
