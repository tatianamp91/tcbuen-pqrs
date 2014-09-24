package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.dataaccess.dao.*;
import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.InfoSolicitanteDTO;
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
@Service("InfoSolicitanteLogic")
public class InfoSolicitanteLogic implements IInfoSolicitanteLogic {
    /**
     * DAO injected by Spring that manages InfoSolicitante entities
     *
     */
    @Autowired
    private IInfoSolicitanteDAO infoSolicitanteDAO;

    /**
    * DAO injected by Spring that manages SolicitudPqr entities
    *
    */
    @Autowired
    private ISolicitudPqrDAO solicitudPqrDAO;

    /**
    * Logic injected by Spring that manages TipoDocumento entities
    *
    */
    @Autowired
    ITipoDocumentoLogic logicTipoDocumento1;

    @Transactional(readOnly = true)
    public List<InfoSolicitante> getInfoSolicitante() throws Exception {
        List<InfoSolicitante> list = new ArrayList<InfoSolicitante>();

        try {
            list = infoSolicitanteDAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "InfoSolicitante");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveInfoSolicitante(InfoSolicitante entity)
        throws Exception {
        try {
            if (entity.getTipoDocumento() == null) {
                throw new ZMessManager().new ForeignException("tipoDocumento");
            }

            if (entity.getCorreoElectronico() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "correoElectronico");
            }

            if ((entity.getCorreoElectronico() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getCorreoElectronico(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "correoElectronico");
            }
            
            /*
            if (entity.getIdInfoSolicitante() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idInfoSolicitante");
            }

            
            if ((entity.getIdInfoSolicitante() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdInfoSolicitante(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idInfoSolicitante");
            }*/

            if (entity.getNombreContacto() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "nombreContacto");
            }

            if ((entity.getNombreContacto() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreContacto(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nombreContacto");
            }

            if ((entity.getNombreEmpresa() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreEmpresa(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nombreEmpresa");
            }

            if ((entity.getNumeroCelular() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNumeroCelular(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "numeroCelular");
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

            if ((entity.getTelefonoFijo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getTelefonoFijo(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "telefonoFijo");
            }

            if (entity.getTipoDocumento().getIdTpDoc() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idTpDoc_TipoDocumento");
            }

            if ((entity.getTipoDocumento().getIdTpDoc() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getTipoDocumento().getIdTpDoc(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idTpDoc_TipoDocumento");
            }
            
            /*
            if (getInfoSolicitante(entity.getIdInfoSolicitante()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }*/

            infoSolicitanteDAO.save(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteInfoSolicitante(InfoSolicitante entity)
        throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("InfoSolicitante");
        }

        if (entity.getIdInfoSolicitante() == null) {
            throw new ZMessManager().new EmptyFieldException(
                "idInfoSolicitante");
        }

        List<SolicitudPqr> solicitudPqrs = null;

        try {
            solicitudPqrs = solicitudPqrDAO.findByProperty("infoSolicitante.idInfoSolicitante",
                    entity.getIdInfoSolicitante());

            if (Utilities.validationsList(solicitudPqrs) == true) {
                throw new ZMessManager().new DeletingException("solicitudPqrs");
            }

            infoSolicitanteDAO.delete(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateInfoSolicitante(InfoSolicitante entity)
        throws Exception {
        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "InfoSolicitante");
            }

            if (entity.getTipoDocumento() == null) {
                throw new ZMessManager().new ForeignException("tipoDocumento");
            }

            if (entity.getCorreoElectronico() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "correoElectronico");
            }

            if ((entity.getCorreoElectronico() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getCorreoElectronico(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "correoElectronico");
            }

            if (entity.getIdInfoSolicitante() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idInfoSolicitante");
            }

            if ((entity.getIdInfoSolicitante() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdInfoSolicitante(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idInfoSolicitante");
            }

            if (entity.getNombreContacto() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "nombreContacto");
            }

            if ((entity.getNombreContacto() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreContacto(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nombreContacto");
            }

            if ((entity.getNombreEmpresa() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNombreEmpresa(), 100) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "nombreEmpresa");
            }

            if ((entity.getNumeroCelular() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getNumeroCelular(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "numeroCelular");
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

            if ((entity.getTelefonoFijo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getTelefonoFijo(), 20) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "telefonoFijo");
            }

            if (entity.getTipoDocumento().getIdTpDoc() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "idTpDoc_TipoDocumento");
            }

            if ((entity.getTipoDocumento().getIdTpDoc() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getTipoDocumento().getIdTpDoc(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idTpDoc_TipoDocumento");
            }

            infoSolicitanteDAO.update(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<InfoSolicitanteDTO> getDataInfoSolicitante()
        throws Exception {
        try {
            List<InfoSolicitante> infoSolicitante = infoSolicitanteDAO.findAll();

            List<InfoSolicitanteDTO> infoSolicitanteDTO = new ArrayList<InfoSolicitanteDTO>();

            for (InfoSolicitante infoSolicitanteTmp : infoSolicitante) {
                InfoSolicitanteDTO infoSolicitanteDTO2 = new InfoSolicitanteDTO();

                infoSolicitanteDTO2.setIdInfoSolicitante(infoSolicitanteTmp.getIdInfoSolicitante());
                infoSolicitanteDTO2.setCorreoElectronico((infoSolicitanteTmp.getCorreoElectronico() != null)
                    ? infoSolicitanteTmp.getCorreoElectronico() : null);
                infoSolicitanteDTO2.setNombreContacto((infoSolicitanteTmp.getNombreContacto() != null)
                    ? infoSolicitanteTmp.getNombreContacto() : null);
                infoSolicitanteDTO2.setNombreEmpresa((infoSolicitanteTmp.getNombreEmpresa() != null)
                    ? infoSolicitanteTmp.getNombreEmpresa() : null);
                infoSolicitanteDTO2.setNumeroCelular((infoSolicitanteTmp.getNumeroCelular() != null)
                    ? infoSolicitanteTmp.getNumeroCelular() : null);
                infoSolicitanteDTO2.setNumeroIdentificacion((infoSolicitanteTmp.getNumeroIdentificacion() != null)
                    ? infoSolicitanteTmp.getNumeroIdentificacion() : null);
                infoSolicitanteDTO2.setTelefonoFijo((infoSolicitanteTmp.getTelefonoFijo() != null)
                    ? infoSolicitanteTmp.getTelefonoFijo() : null);
                infoSolicitanteDTO2.setIdTpDoc_TipoDocumento((infoSolicitanteTmp.getTipoDocumento()
                                                                                .getIdTpDoc() != null)
                    ? infoSolicitanteTmp.getTipoDocumento().getIdTpDoc() : null);
                infoSolicitanteDTO.add(infoSolicitanteDTO2);
            }

            return infoSolicitanteDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public InfoSolicitante getInfoSolicitante(Long idInfoSolicitante)
        throws Exception {
        InfoSolicitante entity = null;

        try {
            entity = infoSolicitanteDAO.findById(idInfoSolicitante);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("InfoSolicitante");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<InfoSolicitante> findPageInfoSolicitante(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<InfoSolicitante> entity = null;

        try {
            entity = infoSolicitanteDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "InfoSolicitante Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberInfoSolicitante() throws Exception {
        Long entity = null;

        try {
            entity = infoSolicitanteDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "InfoSolicitante Count");
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
    public List<InfoSolicitante> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<InfoSolicitante> list = new ArrayList<InfoSolicitante>();
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
            list = infoSolicitanteDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
