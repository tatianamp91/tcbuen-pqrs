package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.dataaccess.dao.*;
import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.AnexosSolicitanteDTO;
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
@Service("AnexosSolicitanteLogic")
public class AnexosSolicitanteLogic implements IAnexosSolicitanteLogic {
    /**
     * DAO injected by Spring that manages AnexosSolicitante entities
     *
     */
    @Autowired
    private IAnexosSolicitanteDAO anexosSolicitanteDAO;

    /**
    * Logic injected by Spring that manages AnexosPqr entities
    *
    */
    @Autowired
    IAnexosPqrLogic logicAnexosPqr1;

    /**
    * Logic injected by Spring that manages SolicitudPqr entities
    *
    */
    @Autowired
    ISolicitudPqrLogic logicSolicitudPqr2;

    @Transactional(readOnly = true)
    public List<AnexosSolicitante> getAnexosSolicitante()
        throws Exception {
        List<AnexosSolicitante> list = new ArrayList<AnexosSolicitante>();

        try {
            list = anexosSolicitanteDAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "AnexosSolicitante");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveAnexosSolicitante(AnexosSolicitante entity)
        throws Exception {
        try {
            if (entity.getAnexosPqr() == null) {
                throw new ZMessManager().new ForeignException("anexosPqr");
            }

            if (entity.getSolicitudPqr() == null) {
                throw new ZMessManager().new ForeignException("solicitudPqr");
            }

            if ((entity.getAdjuntoDocumento() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getAdjuntoDocumento(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "adjuntoDocumento");
            }

            if ((entity.getApruebaAnexo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getApruebaAnexo(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "apruebaAnexo");
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

            if (entity.getIdAnxSolicitante() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idAnxSolicitante");
            }

            if ((entity.getIdAnxSolicitante() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdAnxSolicitante(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idAnxSolicitante");
            }

            if (entity.getNombreAnexo() == null) {
                throw new ZMessManager().new EmptyFieldException("nombreAnexo");
            }

            if ((entity.getNombreAnexo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreAnexo(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nombreAnexo");
            }

            if (entity.getNombreBusqueda() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "nombreBusqueda");
            }

            if ((entity.getNombreBusqueda() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreBusqueda(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nombreBusqueda");
            }

            if ((entity.getRazonRechazo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getRazonRechazo(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "razonRechazo");
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

            if (entity.getSolicitudPqr().getIdSolPqr() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idSolPqr_SolicitudPqr");
            }

            if ((entity.getSolicitudPqr().getIdSolPqr() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getSolicitudPqr().getIdSolPqr(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idSolPqr_SolicitudPqr");
            }

            if (getAnexosSolicitante(entity.getIdAnxSolicitante()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }

            anexosSolicitanteDAO.save(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteAnexosSolicitante(AnexosSolicitante entity)
        throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "AnexosSolicitante");
        }

        if (entity.getIdAnxSolicitante() == null) {
            throw new ZMessManager().new EmptyFieldException("idAnxSolicitante");
        }

        try {
            anexosSolicitanteDAO.delete(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateAnexosSolicitante(AnexosSolicitante entity)
        throws Exception {
        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "AnexosSolicitante");
            }

            if (entity.getAnexosPqr() == null) {
                throw new ZMessManager().new ForeignException("anexosPqr");
            }

            if (entity.getSolicitudPqr() == null) {
                throw new ZMessManager().new ForeignException("solicitudPqr");
            }

            if ((entity.getAdjuntoDocumento() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getAdjuntoDocumento(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "adjuntoDocumento");
            }

            if ((entity.getApruebaAnexo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getApruebaAnexo(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "apruebaAnexo");
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

            if (entity.getIdAnxSolicitante() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idAnxSolicitante");
            }

            if ((entity.getIdAnxSolicitante() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdAnxSolicitante(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idAnxSolicitante");
            }

            if (entity.getNombreAnexo() == null) {
                throw new ZMessManager().new EmptyFieldException("nombreAnexo");
            }

            if ((entity.getNombreAnexo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreAnexo(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nombreAnexo");
            }

            if (entity.getNombreBusqueda() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "nombreBusqueda");
            }

            if ((entity.getNombreBusqueda() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreBusqueda(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nombreBusqueda");
            }

            if ((entity.getRazonRechazo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getRazonRechazo(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "razonRechazo");
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

            if (entity.getSolicitudPqr().getIdSolPqr() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idSolPqr_SolicitudPqr");
            }

            if ((entity.getSolicitudPqr().getIdSolPqr() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getSolicitudPqr().getIdSolPqr(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idSolPqr_SolicitudPqr");
            }

            anexosSolicitanteDAO.update(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<AnexosSolicitanteDTO> getDataAnexosSolicitante()
        throws Exception {
        try {
            List<AnexosSolicitante> anexosSolicitante = anexosSolicitanteDAO.findAll();

            List<AnexosSolicitanteDTO> anexosSolicitanteDTO = new ArrayList<AnexosSolicitanteDTO>();

            for (AnexosSolicitante anexosSolicitanteTmp : anexosSolicitante) {
                AnexosSolicitanteDTO anexosSolicitanteDTO2 = new AnexosSolicitanteDTO();

                anexosSolicitanteDTO2.setIdAnxSolicitante(anexosSolicitanteTmp.getIdAnxSolicitante());
                anexosSolicitanteDTO2.setAdjuntoDocumento((anexosSolicitanteTmp.getAdjuntoDocumento() != null)
                    ? anexosSolicitanteTmp.getAdjuntoDocumento() : null);
                anexosSolicitanteDTO2.setApruebaAnexo((anexosSolicitanteTmp.getApruebaAnexo() != null)
                    ? anexosSolicitanteTmp.getApruebaAnexo() : null);
                anexosSolicitanteDTO2.setDocumentoReal((anexosSolicitanteTmp.getDocumentoReal() != null)
                    ? anexosSolicitanteTmp.getDocumentoReal() : null);
                anexosSolicitanteDTO2.setEstadoRegistro((anexosSolicitanteTmp.getEstadoRegistro() != null)
                    ? anexosSolicitanteTmp.getEstadoRegistro() : null);
                anexosSolicitanteDTO2.setFechaCreacion(anexosSolicitanteTmp.getFechaCreacion());
                anexosSolicitanteDTO2.setFechaUltimaModificacion(anexosSolicitanteTmp.getFechaUltimaModificacion());
                anexosSolicitanteDTO2.setNombreAnexo((anexosSolicitanteTmp.getNombreAnexo() != null)
                    ? anexosSolicitanteTmp.getNombreAnexo() : null);
                anexosSolicitanteDTO2.setNombreBusqueda((anexosSolicitanteTmp.getNombreBusqueda() != null)
                    ? anexosSolicitanteTmp.getNombreBusqueda() : null);
                anexosSolicitanteDTO2.setRazonRechazo((anexosSolicitanteTmp.getRazonRechazo() != null)
                    ? anexosSolicitanteTmp.getRazonRechazo() : null);
                anexosSolicitanteDTO2.setUsuarioCreador((anexosSolicitanteTmp.getUsuarioCreador() != null)
                    ? anexosSolicitanteTmp.getUsuarioCreador() : null);
                anexosSolicitanteDTO2.setUsuarioUltimaModificacion((anexosSolicitanteTmp.getUsuarioUltimaModificacion() != null)
                    ? anexosSolicitanteTmp.getUsuarioUltimaModificacion() : null);
                anexosSolicitanteDTO2.setIdAnexoPqr_AnexosPqr((anexosSolicitanteTmp.getAnexosPqr()
                                                                                   .getIdAnexoPqr() != null)
                    ? anexosSolicitanteTmp.getAnexosPqr().getIdAnexoPqr() : null);
                anexosSolicitanteDTO2.setIdSolPqr_SolicitudPqr((anexosSolicitanteTmp.getSolicitudPqr()
                                                                                    .getIdSolPqr() != null)
                    ? anexosSolicitanteTmp.getSolicitudPqr().getIdSolPqr() : null);
                anexosSolicitanteDTO.add(anexosSolicitanteDTO2);
            }

            return anexosSolicitanteDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public AnexosSolicitante getAnexosSolicitante(Long idAnxSolicitante)
        throws Exception {
        AnexosSolicitante entity = null;

        try {
            entity = anexosSolicitanteDAO.findById(idAnxSolicitante);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("AnexosSolicitante");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<AnexosSolicitante> findPageAnexosSolicitante(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<AnexosSolicitante> entity = null;

        try {
            entity = anexosSolicitanteDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "AnexosSolicitante Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberAnexosSolicitante() throws Exception {
        Long entity = null;

        try {
            entity = anexosSolicitanteDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "AnexosSolicitante Count");
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
    public List<AnexosSolicitante> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<AnexosSolicitante> list = new ArrayList<AnexosSolicitante>();
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
            list = anexosSolicitanteDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
