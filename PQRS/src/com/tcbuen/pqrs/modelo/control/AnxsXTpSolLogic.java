package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.dataaccess.dao.*;
import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.AnxsXTpSolDTO;
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
@Service("AnxsXTpSolLogic")
public class AnxsXTpSolLogic implements IAnxsXTpSolLogic {
    /**
     * DAO injected by Spring that manages AnxsXTpSol entities
     *
     */
    @Autowired
    private IAnxsXTpSolDAO anxsXTpSolDAO;

    /**
    * Logic injected by Spring that manages AnexosPqr entities
    *
    */
    @Autowired
    IAnexosPqrLogic logicAnexosPqr1;

    /**
    * Logic injected by Spring that manages TipoSolicitudPqr entities
    *
    */
    @Autowired
    ITipoSolicitudPqrLogic logicTipoSolicitudPqr2;

    @Transactional(readOnly = true)
    public List<AnxsXTpSol> getAnxsXTpSol() throws Exception {
        List<AnxsXTpSol> list = new ArrayList<AnxsXTpSol>();

        try {
            list = anxsXTpSolDAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "AnxsXTpSol");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveAnxsXTpSol(AnxsXTpSol entity) throws Exception {
        try {
            if (entity.getAnexosPqr() == null) {
                throw new ZMessManager().new ForeignException("anexosPqr");
            }

            if (entity.getTipoSolicitudPqr() == null) {
                throw new ZMessManager().new ForeignException(
                    "tipoSolicitudPqr");
            }

            if (entity.getEsObligatorio() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "esObligatorio");
            }

            if ((entity.getEsObligatorio() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEsObligatorio(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "esObligatorio");
            }

            if (entity.getIdAnexoXTpSol() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idAnexoXTpSol");
            }

            if ((entity.getIdAnexoXTpSol() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdAnexoXTpSol(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idAnexoXTpSol");
            }

            if (entity.getAnexosPqr().getIdAnexoPqr() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idAnexoPqr_AnexosPqr");
            }

            if ((entity.getAnexosPqr().getIdAnexoPqr() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getAnexosPqr().getIdAnexoPqr(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idAnexoPqr_AnexosPqr");
            }

            if (entity.getTipoSolicitudPqr().getIdTpSolPqr() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idTpSolPqr_TipoSolicitudPqr");
            }

            if ((entity.getTipoSolicitudPqr().getIdTpSolPqr() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getTipoSolicitudPqr().getIdTpSolPqr(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idTpSolPqr_TipoSolicitudPqr");
            }

            if (getAnxsXTpSol(entity.getIdAnexoXTpSol()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            anxsXTpSolDAO.save(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteAnxsXTpSol(AnxsXTpSol entity) throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("AnxsXTpSol");
        }

        if (entity.getIdAnexoXTpSol() == null) {
            throw new ZMessManager().new EmptyFieldException("idAnexoXTpSol");
        }

        try {
            anxsXTpSolDAO.delete(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateAnxsXTpSol(AnxsXTpSol entity) throws Exception {
        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("AnxsXTpSol");
            }

            if (entity.getAnexosPqr() == null) {
                throw new ZMessManager().new ForeignException("anexosPqr");
            }

            if (entity.getTipoSolicitudPqr() == null) {
                throw new ZMessManager().new ForeignException(
                    "tipoSolicitudPqr");
            }

            if (entity.getEsObligatorio() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "esObligatorio");
            }

            if ((entity.getEsObligatorio() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getEsObligatorio(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "esObligatorio");
            }

            if (entity.getIdAnexoXTpSol() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idAnexoXTpSol");
            }

            if ((entity.getIdAnexoXTpSol() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdAnexoXTpSol(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idAnexoXTpSol");
            }

            if (entity.getAnexosPqr().getIdAnexoPqr() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idAnexoPqr_AnexosPqr");
            }

            if ((entity.getAnexosPqr().getIdAnexoPqr() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getAnexosPqr().getIdAnexoPqr(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idAnexoPqr_AnexosPqr");
            }

            if (entity.getTipoSolicitudPqr().getIdTpSolPqr() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idTpSolPqr_TipoSolicitudPqr");
            }

            if ((entity.getTipoSolicitudPqr().getIdTpSolPqr() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getTipoSolicitudPqr().getIdTpSolPqr(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idTpSolPqr_TipoSolicitudPqr");
            }

            anxsXTpSolDAO.update(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<AnxsXTpSolDTO> getDataAnxsXTpSol() throws Exception {
        try {
            List<AnxsXTpSol> anxsXTpSol = anxsXTpSolDAO.findAll();

            List<AnxsXTpSolDTO> anxsXTpSolDTO = new ArrayList<AnxsXTpSolDTO>();

            for (AnxsXTpSol anxsXTpSolTmp : anxsXTpSol) {
                AnxsXTpSolDTO anxsXTpSolDTO2 = new AnxsXTpSolDTO();

                anxsXTpSolDTO2.setIdAnexoXTpSol(anxsXTpSolTmp.getIdAnexoXTpSol());
                anxsXTpSolDTO2.setEsObligatorio((anxsXTpSolTmp.getEsObligatorio() != null)
                    ? anxsXTpSolTmp.getEsObligatorio() : null);
                anxsXTpSolDTO2.setIdAnexoPqr_AnexosPqr((anxsXTpSolTmp.getAnexosPqr()
                                                                     .getIdAnexoPqr() != null)
                    ? anxsXTpSolTmp.getAnexosPqr().getIdAnexoPqr() : null);
                anxsXTpSolDTO2.setIdTpSolPqr_TipoSolicitudPqr((anxsXTpSolTmp.getTipoSolicitudPqr()
                                                                            .getIdTpSolPqr() != null)
                    ? anxsXTpSolTmp.getTipoSolicitudPqr().getIdTpSolPqr() : null);
                anxsXTpSolDTO.add(anxsXTpSolDTO2);
            }

            return anxsXTpSolDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public AnxsXTpSol getAnxsXTpSol(Long idAnexoXTpSol)
        throws Exception {
        AnxsXTpSol entity = null;

        try {
            entity = anxsXTpSolDAO.findById(idAnexoXTpSol);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("AnxsXTpSol");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<AnxsXTpSol> findPageAnxsXTpSol(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<AnxsXTpSol> entity = null;

        try {
            entity = anxsXTpSolDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("AnxsXTpSol Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberAnxsXTpSol() throws Exception {
        Long entity = null;

        try {
            entity = anxsXTpSolDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("AnxsXTpSol Count");
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
    public List<AnxsXTpSol> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<AnxsXTpSol> list = new ArrayList<AnxsXTpSol>();
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
            list = anxsXTpSolDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
