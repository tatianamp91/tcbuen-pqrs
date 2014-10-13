package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.dataaccess.dao.*;
import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.SolicitudPqrDTO;
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
@Service("SolicitudPqrLogic")
public class SolicitudPqrLogic implements ISolicitudPqrLogic {
    /**
     * DAO injected by Spring that manages SolicitudPqr entities
     *
     */
    @Autowired
    private ISolicitudPqrDAO solicitudPqrDAO;

    /**
    * DAO injected by Spring that manages AnexosSolicitante entities
    *
    */
    @Autowired
    private IAnexosSolicitanteDAO anexosSolicitanteDAO;

    /**
    * DAO injected by Spring that manages MotReclSelect entities
    *
    */
    @Autowired
    private IMotReclSelectDAO motReclSelectDAO;

    /**
    * DAO injected by Spring that manages MotSolSelect entities
    *
    */
    @Autowired
    private IMotSolSelectDAO motSolSelectDAO;

    /**
    * DAO injected by Spring that manages SolicitudAsignadaArea entities
    *
    */
    @Autowired
    private ISolicitudAsignadaAreaDAO solicitudAsignadaAreaDAO;

    /**
    * Logic injected by Spring that manages InfoSolicitante entities
    *
    */
    @Autowired
    IInfoSolicitanteLogic logicInfoSolicitante1;

    /**
    * Logic injected by Spring that manages TipoEstadoPqr entities
    *
    */
    @Autowired
    ITipoEstadoPqrLogic logicTipoEstadoPqr2;

    /**
    * Logic injected by Spring that manages TipoSolicitudPqr entities
    *
    */
    @Autowired
    ITipoSolicitudPqrLogic logicTipoSolicitudPqr3;

    @Transactional(readOnly = true)
    public List<SolicitudPqr> getSolicitudPqr() throws Exception {
        List<SolicitudPqr> list = new ArrayList<SolicitudPqr>();

        try {
            list = solicitudPqrDAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "SolicitudPqr");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveSolicitudPqr(SolicitudPqr entity) throws Exception {
        try {
            if (entity.getInfoSolicitante() == null) {
                throw new ZMessManager().new ForeignException("infoSolicitante");
            }

            if (entity.getTipoEstadoPqr() == null) {
                throw new ZMessManager().new ForeignException("tipoEstadoPqr");
            }

            if (entity.getTipoSolicitudPqr() == null) {
                throw new ZMessManager().new ForeignException(
                    "tipoSolicitudPqr");
            }

            if (entity.getDescripcionCaso() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "descripcionCaso");
            }

            if ((entity.getDescripcionCaso() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcionCaso(), 2000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcionCaso");
            }

            if (entity.getFechaCreacion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "fechaCreacion");
            }

            if ((entity.getNombreAgenciaAduana() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreAgenciaAduana(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nombreAgenciaAduana");
            }

            if ((entity.getNombreCliente() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreCliente(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nombreCliente");
            }

            if (entity.getNumeroRadicacion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "numeroRadicacion");
            }

            if ((entity.getNumeroRadicacion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNumeroRadicacion(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "numeroRadicacion");
            }

            if ((entity.getSolicitudARealizar() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSolicitudARealizar(), 2000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "solicitudARealizar");
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

            if (entity.getInfoSolicitante().getIdInfoSolicitante() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idInfoSolicitante_InfoSolicitante");
            }

            if ((entity.getInfoSolicitante().getIdInfoSolicitante() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getInfoSolicitante().getIdInfoSolicitante(), 10,
                        0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idInfoSolicitante_InfoSolicitante");
            }

            if (entity.getTipoEstadoPqr().getIdTpEstPqr() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idTpEstPqr_TipoEstadoPqr");
            }

            if ((entity.getTipoEstadoPqr().getIdTpEstPqr() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getTipoEstadoPqr().getIdTpEstPqr(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idTpEstPqr_TipoEstadoPqr");
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

            solicitudPqrDAO.save(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteSolicitudPqr(SolicitudPqr entity)
        throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("SolicitudPqr");
        }

        if (entity.getIdSolPqr() == null) {
            throw new ZMessManager().new EmptyFieldException("idSolPqr");
        }

        List<AnexosSolicitante> anexosSolicitantes = null;
        List<MotReclSelect> motReclSelects = null;
        List<MotSolSelect> motSolSelects = null;
        List<SolicitudAsignadaArea> solicitudAsignadaAreas = null;

        try {
            anexosSolicitantes = anexosSolicitanteDAO.findByProperty("solicitudPqr.idSolPqr",
                    entity.getIdSolPqr());

            if (Utilities.validationsList(anexosSolicitantes) == true) {
                throw new ZMessManager().new DeletingException(
                    "anexosSolicitantes");
            }

            motReclSelects = motReclSelectDAO.findByProperty("solicitudPqr.idSolPqr",
                    entity.getIdSolPqr());

            if (Utilities.validationsList(motReclSelects) == true) {
                throw new ZMessManager().new DeletingException("motReclSelects");
            }

            motSolSelects = motSolSelectDAO.findByProperty("solicitudPqr.idSolPqr",
                    entity.getIdSolPqr());

            if (Utilities.validationsList(motSolSelects) == true) {
                throw new ZMessManager().new DeletingException("motSolSelects");
            }

            solicitudAsignadaAreas = solicitudAsignadaAreaDAO.findByProperty("solicitudPqr.idSolPqr",
                    entity.getIdSolPqr());

            if (Utilities.validationsList(solicitudAsignadaAreas) == true) {
                throw new ZMessManager().new DeletingException(
                    "solicitudAsignadaAreas");
            }

            solicitudPqrDAO.delete(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateSolicitudPqr(SolicitudPqr entity)
        throws Exception {
        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("SolicitudPqr");
            }

            if (entity.getInfoSolicitante() == null) {
                throw new ZMessManager().new ForeignException("infoSolicitante");
            }

            if (entity.getTipoEstadoPqr() == null) {
                throw new ZMessManager().new ForeignException("tipoEstadoPqr");
            }

            if (entity.getTipoSolicitudPqr() == null) {
                throw new ZMessManager().new ForeignException(
                    "tipoSolicitudPqr");
            }

            if (entity.getDescripcionCaso() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "descripcionCaso");
            }

            if ((entity.getDescripcionCaso() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcionCaso(), 2000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcionCaso");
            }

            if (entity.getFechaCreacion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "fechaCreacion");
            }

            if (entity.getIdSolPqr() == null) {
                throw new ZMessManager().new EmptyFieldException("idSolPqr");
            }

            if ((entity.getIdSolPqr() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdSolPqr(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("idSolPqr");
            }

            if ((entity.getNombreAgenciaAduana() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreAgenciaAduana(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nombreAgenciaAduana");
            }

            if ((entity.getNombreCliente() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreCliente(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nombreCliente");
            }

            if (entity.getNumeroRadicacion() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "numeroRadicacion");
            }

            if ((entity.getNumeroRadicacion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNumeroRadicacion(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "numeroRadicacion");
            }

            if ((entity.getSolicitudARealizar() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getSolicitudARealizar(), 2000) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "solicitudARealizar");
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

            if (entity.getInfoSolicitante().getIdInfoSolicitante() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idInfoSolicitante_InfoSolicitante");
            }

            if ((entity.getInfoSolicitante().getIdInfoSolicitante() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getInfoSolicitante().getIdInfoSolicitante(), 10,
                        0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idInfoSolicitante_InfoSolicitante");
            }

            if (entity.getTipoEstadoPqr().getIdTpEstPqr() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idTpEstPqr_TipoEstadoPqr");
            }

            if ((entity.getTipoEstadoPqr().getIdTpEstPqr() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getTipoEstadoPqr().getIdTpEstPqr(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idTpEstPqr_TipoEstadoPqr");
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

            solicitudPqrDAO.update(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<SolicitudPqrDTO> getDataSolicitudPqr()
        throws Exception {
        try {
            List<SolicitudPqr> solicitudPqr = solicitudPqrDAO.findAll();

            List<SolicitudPqrDTO> solicitudPqrDTO = new ArrayList<SolicitudPqrDTO>();

            for (SolicitudPqr solicitudPqrTmp : solicitudPqr) {
                SolicitudPqrDTO solicitudPqrDTO2 = new SolicitudPqrDTO();

                solicitudPqrDTO2.setIdSolPqr(solicitudPqrTmp.getIdSolPqr());
                solicitudPqrDTO2.setDescripcionCaso((solicitudPqrTmp.getDescripcionCaso() != null)
                    ? solicitudPqrTmp.getDescripcionCaso() : null);
                solicitudPqrDTO2.setFechaCreacion(solicitudPqrTmp.getFechaCreacion());
                solicitudPqrDTO2.setFechaUltimaModificacion(solicitudPqrTmp.getFechaUltimaModificacion());
                solicitudPqrDTO2.setNombreAgenciaAduana((solicitudPqrTmp.getNombreAgenciaAduana() != null)
                    ? solicitudPqrTmp.getNombreAgenciaAduana() : null);
                solicitudPqrDTO2.setNombreCliente((solicitudPqrTmp.getNombreCliente() != null)
                    ? solicitudPqrTmp.getNombreCliente() : null);
                solicitudPqrDTO2.setNumeroRadicacion((solicitudPqrTmp.getNumeroRadicacion() != null)
                    ? solicitudPqrTmp.getNumeroRadicacion() : null);
                solicitudPqrDTO2.setSolicitudARealizar((solicitudPqrTmp.getSolicitudARealizar() != null)
                    ? solicitudPqrTmp.getSolicitudARealizar() : null);
                solicitudPqrDTO2.setUsuarioCreador((solicitudPqrTmp.getUsuarioCreador() != null)
                    ? solicitudPqrTmp.getUsuarioCreador() : null);
                solicitudPqrDTO2.setUsuarioUltimaModificacion((solicitudPqrTmp.getUsuarioUltimaModificacion() != null)
                    ? solicitudPqrTmp.getUsuarioUltimaModificacion() : null);
                solicitudPqrDTO2.setIdInfoSolicitante_InfoSolicitante((solicitudPqrTmp.getInfoSolicitante()
                                                                                      .getIdInfoSolicitante() != null)
                    ? solicitudPqrTmp.getInfoSolicitante().getIdInfoSolicitante()
                    : null);
                solicitudPqrDTO2.setIdTpEstPqr_TipoEstadoPqr((solicitudPqrTmp.getTipoEstadoPqr()
                                                                             .getIdTpEstPqr() != null)
                    ? solicitudPqrTmp.getTipoEstadoPqr().getIdTpEstPqr() : null);
                solicitudPqrDTO2.setIdTpSolPqr_TipoSolicitudPqr((solicitudPqrTmp.getTipoSolicitudPqr()
                                                                                .getIdTpSolPqr() != null)
                    ? solicitudPqrTmp.getTipoSolicitudPqr().getIdTpSolPqr() : null);
                solicitudPqrDTO.add(solicitudPqrDTO2);
            }

            return solicitudPqrDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public SolicitudPqr getSolicitudPqr(Long idSolPqr)
        throws Exception {
        SolicitudPqr entity = null;

        try {
            entity = solicitudPqrDAO.findById(idSolPqr);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SolicitudPqr");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<SolicitudPqr> findPageSolicitudPqr(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<SolicitudPqr> entity = null;

        try {
            entity = solicitudPqrDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SolicitudPqr Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberSolicitudPqr() throws Exception {
        Long entity = null;

        try {
            entity = solicitudPqrDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("SolicitudPqr Count");
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
    public List<SolicitudPqr> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<SolicitudPqr> list = new ArrayList<SolicitudPqr>();
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
            list = solicitudPqrDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
