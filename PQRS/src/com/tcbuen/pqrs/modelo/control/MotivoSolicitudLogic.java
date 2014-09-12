package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.dataaccess.dao.*;
import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.exceptions.ZMessManager.GettingException;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.MotivoSolicitudDTO;
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
@Service("MotivoSolicitudLogic")
public class MotivoSolicitudLogic implements IMotivoSolicitudLogic {
    /**
     * DAO injected by Spring that manages MotivoSolicitud entities
     *
     */
    @Autowired
    private IMotivoSolicitudDAO motivoSolicitudDAO;

    /**
    * DAO injected by Spring that manages MotSolSelect entities
    *
    */
    @Autowired
    private IMotSolSelectDAO motSolSelectDAO;

    /**
    * DAO injected by Spring that manages MotSolXTpSol entities
    *
    */
    @Autowired
    private IMotSolXTpSolDAO motSolXTpSolDAO;

    @Transactional(readOnly = true)
    public List<MotivoSolicitud> getMotivoSolicitud() throws Exception {
        List<MotivoSolicitud> list = new ArrayList<MotivoSolicitud>();

        try {
            list = motivoSolicitudDAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "MotivoSolicitud");
        } finally {
        }

        return list;
    }
    
    @Transactional(readOnly = true)
    public List<MotivoSolicitud> consultarMotSolXTipoPqr(TipoSolicitudPqr tipoSolicitudPqr)
        	throws Exception{
    	List<MotivoSolicitud> motivoSolicitud = new ArrayList<MotivoSolicitud>();
    	try{
    		motivoSolicitud = motivoSolicitudDAO.consultarMotSolXTipoPqr(tipoSolicitudPqr);
    	}catch(Exception e){
    		throw new ZMessManager().new GettingException(ZMessManager.ALL + 
    				"Motivos de Solcitud por Tipo de Solicitud");
    	}
    	return motivoSolicitud;
    }
    
    @Transactional(readOnly = true)
    public List<MotivoSolicitud> consultarMotSolNoTipoPqr (TipoSolicitudPqr tipoSolicitudPqr)
        	throws Exception{
    	List<MotivoSolicitud> motivoSolicitud = new ArrayList<MotivoSolicitud>();
    	try{
    		motivoSolicitud = motivoSolicitudDAO.consultarMotSolNoTipoPqr(tipoSolicitudPqr);
    	}catch(Exception e){
    		throw new ZMessManager().new GettingException(ZMessManager.ALL + 
    				"Motivos de Solcitud por Tipo de Solicitud");
    	}
    	return motivoSolicitud;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveMotivoSolicitud(MotivoSolicitud entity)
        throws Exception {
        try {
        	/*
        	if (getMotivoSolicitud(entity.getIdMotSol()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }
            if (entity.getIdMotSol() == null) {
                throw new ZMessManager().new EmptyFieldException("idMotSol");
            }

            if ((entity.getIdMotSol() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdMotSol(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("idMotSol");
            }
            */
            if (entity.getDescripcionMotSol() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "descripcionMotSol");
            }

            if ((entity.getDescripcionMotSol() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcionMotSol(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcionMotSol");
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

            motivoSolicitudDAO.save(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteMotivoSolicitud(MotivoSolicitud entity)
        throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("MotivoSolicitud");
        }

        if (entity.getIdMotSol() == null) {
            throw new ZMessManager().new EmptyFieldException("idMotSol");
        }

        List<MotSolSelect> motSolSelects = null;
        List<MotSolXTpSol> motSolXTpSols = null;

        try {
            motSolSelects = motSolSelectDAO.findByProperty("motivoSolicitud.idMotSol",
                    entity.getIdMotSol());

            if (Utilities.validationsList(motSolSelects) == true) {
                throw new ZMessManager().new DeletingException("motSolSelects");
            }

            motSolXTpSols = motSolXTpSolDAO.findByProperty("motivoSolicitud.idMotSol",
                    entity.getIdMotSol());

            if (Utilities.validationsList(motSolXTpSols) == true) {
                throw new ZMessManager().new DeletingException("motSolXTpSols");
            }

            motivoSolicitudDAO.delete(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateMotivoSolicitud(MotivoSolicitud entity)
        throws Exception {
        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "MotivoSolicitud");
            }

            if (entity.getDescripcionMotSol() == null) {
                throw new ZMessManager().new EmptyFieldException(
                    "descripcionMotSol");
            }

            if ((entity.getDescripcionMotSol() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescripcionMotSol(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descripcionMotSol");
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

            if (entity.getIdMotSol() == null) {
                throw new ZMessManager().new EmptyFieldException("idMotSol");
            }

            if ((entity.getIdMotSol() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdMotSol(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("idMotSol");
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

            motivoSolicitudDAO.update(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<MotivoSolicitudDTO> getDataMotivoSolicitud()
        throws Exception {
        try {
            List<MotivoSolicitud> motivoSolicitud = motivoSolicitudDAO.findAll();

            List<MotivoSolicitudDTO> motivoSolicitudDTO = new ArrayList<MotivoSolicitudDTO>();

            for (MotivoSolicitud motivoSolicitudTmp : motivoSolicitud) {
                MotivoSolicitudDTO motivoSolicitudDTO2 = new MotivoSolicitudDTO();

                motivoSolicitudDTO2.setIdMotSol(motivoSolicitudTmp.getIdMotSol());
                motivoSolicitudDTO2.setDescripcionMotSol((motivoSolicitudTmp.getDescripcionMotSol() != null)
                    ? motivoSolicitudTmp.getDescripcionMotSol() : null);
                motivoSolicitudDTO2.setEstadoRegistro((motivoSolicitudTmp.getEstadoRegistro() != null)
                    ? motivoSolicitudTmp.getEstadoRegistro() : null);
                motivoSolicitudDTO2.setFechaCreacion(motivoSolicitudTmp.getFechaCreacion());
                motivoSolicitudDTO2.setFechaUltimaModificacion(motivoSolicitudTmp.getFechaUltimaModificacion());
                motivoSolicitudDTO2.setUsuarioCreador((motivoSolicitudTmp.getUsuarioCreador() != null)
                    ? motivoSolicitudTmp.getUsuarioCreador() : null);
                motivoSolicitudDTO2.setUsuarioUltimaModificacion((motivoSolicitudTmp.getUsuarioUltimaModificacion() != null)
                    ? motivoSolicitudTmp.getUsuarioUltimaModificacion() : null);
                motivoSolicitudDTO.add(motivoSolicitudDTO2);
            }

            return motivoSolicitudDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public MotivoSolicitud getMotivoSolicitud(Long idMotSol)
        throws Exception {
        MotivoSolicitud entity = null;

        try {
            entity = motivoSolicitudDAO.findById(idMotSol);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("MotivoSolicitud");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<MotivoSolicitud> findPageMotivoSolicitud(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<MotivoSolicitud> entity = null;

        try {
            entity = motivoSolicitudDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "MotivoSolicitud Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberMotivoSolicitud() throws Exception {
        Long entity = null;

        try {
            entity = motivoSolicitudDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "MotivoSolicitud Count");
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
    public List<MotivoSolicitud> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<MotivoSolicitud> list = new ArrayList<MotivoSolicitud>();
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
            list = motivoSolicitudDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
