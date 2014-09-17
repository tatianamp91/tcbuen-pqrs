package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.dataaccess.dao.*;
import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.exceptions.ZMessManager.GettingException;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.AnexosPqrDTO;
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
@Service("AnexosPqrLogic")
public class AnexosPqrLogic implements IAnexosPqrLogic {
    /**
     * DAO injected by Spring that manages AnexosPqr entities
     *
     */
    @Autowired
    private IAnexosPqrDAO anexosPqrDAO;

    /**
    * DAO injected by Spring that manages AnexosRespuesta entities
    *
    */
    @Autowired
    private IAnexosRespuestaDAO anexosRespuestaDAO;

    /**
    * DAO injected by Spring that manages AnexosSolicitante entities
    *
    */
    @Autowired
    private IAnexosSolicitanteDAO anexosSolicitanteDAO;

    /**
    * DAO injected by Spring that manages AnxsXArea entities
    *
    */
    @Autowired
    private IAnxsXAreaDAO anxsXAreaDAO;

    /**
    * DAO injected by Spring that manages AnxsXTpSol entities
    *
    */
    @Autowired
    private IAnxsXTpSolDAO anxsXTpSolDAO;

    @Transactional(readOnly = true)
    public List<AnexosPqr> getAnexosPqr() throws Exception {
        List<AnexosPqr> list = new ArrayList<AnexosPqr>();

        try {
            list = anexosPqrDAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "AnexosPqr");
        } finally {
        }

        return list;
    }
    
    @Transactional(readOnly = true)
    public List<AnexosPqr> consultarAnexos() throws Exception{
    	List<AnexosPqr> anexosPqrs = new ArrayList<AnexosPqr>();
    	try{
    		anexosPqrs = anexosPqrDAO.consultarAnexos();
    	}catch(Exception e){
    		throw new ZMessManager().new GettingException(ZMessManager.ALL + 
    				"Anexos por estado");
    	}
    	return anexosPqrs;
    }
    
    @Transactional(readOnly = true)
    public List<AnexosPqr> consultarAnxsXTipoPqr(TipoSolicitudPqr tipoSolicitudPqr)
        	throws Exception{
    	List<AnexosPqr> anexosPqrs = new ArrayList<AnexosPqr>();
    	try{
    		anexosPqrs = anexosPqrDAO.consultarAnxsXTipoPqr(tipoSolicitudPqr);
    	}catch(Exception e){
    		throw new ZMessManager().new GettingException(ZMessManager.ALL + 
    				"Anexos por Tipo de Solicitud");
    	}
    	return anexosPqrs;
    }
    
    @Transactional(readOnly = true)
    public List<AnexosPqr> consultarAnxsNoTipoPqr (TipoSolicitudPqr tipoSolicitudPqr)
        	throws Exception{
    	List<AnexosPqr> anexosPqrs = new ArrayList<AnexosPqr>();
    	try{
    		anexosPqrs = anexosPqrDAO.consultarAnxsNoTipoPqr(tipoSolicitudPqr);
    	}catch(Exception e){
    		throw new ZMessManager().new GettingException(ZMessManager.ALL + 
    				"Anexos por Tipo de Solicitud");
    	}
    	return anexosPqrs;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveAnexosPqr(AnexosPqr entity) throws Exception {
        try {

            if (entity.getDescripcionAnexo() == null || entity.getDescripcionAnexo().equals("")) {
                throw new ZMessManager().new EmptyFieldException("Descripción");
            }

            if ((entity.getDescripcionAnexo() != null) &&
            		(Utilities.checkWordAndCheckWithlength(entity.getDescripcionAnexo(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException("Descripción");
            }

            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException("Estado");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException("Estado");
            }

            if ((entity.getUsuarioCreador() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getUsuarioCreador(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException("Usuario Creador");
            }

            if ((entity.getUsuarioUltimaModificacion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getUsuarioUltimaModificacion(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException("Usuario Ultima Modificación");
            }

            anexosPqrDAO.save(entity);
        } catch (Exception e) {
        	throw new Exception("Error Guardando Anexo");
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteAnexosPqr(AnexosPqr entity) throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Anexos Pqr");
        }

        if (entity.getIdAnexoPqr() == null) {
            throw new ZMessManager().new EmptyFieldException("id Anexo");
        }

        List<AnexosRespuesta> anexosRespuestas = null;
        List<AnexosSolicitante> anexosSolicitantes = null;
        List<AnxsXArea> anxsXAreas = null;
        List<AnxsXTpSol> anxsXTpSols = null;

        try {
            anexosRespuestas = anexosRespuestaDAO.findByProperty("anexosPqr.idAnexoPqr",
                    entity.getIdAnexoPqr());

            if (Utilities.validationsList(anexosRespuestas) == true) {
                throw new ZMessManager().new DeletingException("anexos Respuestas");
            }

            anexosSolicitantes = anexosSolicitanteDAO.findByProperty("anexosPqr.idAnexoPqr",
                    entity.getIdAnexoPqr());

            if (Utilities.validationsList(anexosSolicitantes) == true) {
                throw new ZMessManager().new DeletingException("anexos Solicitantes");
            }

            anxsXAreas = anxsXAreaDAO.findByProperty("anexosPqr.idAnexoPqr", entity.getIdAnexoPqr());

            if (Utilities.validationsList(anxsXAreas) == true) {
                throw new ZMessManager().new DeletingException("anxsXAreas");
            }

            anxsXTpSols = anxsXTpSolDAO.findByProperty("anexosPqr.idAnexoPqr", entity.getIdAnexoPqr());

            if (Utilities.validationsList(anxsXTpSols) == true) {
                throw new ZMessManager().new DeletingException("anxsXTpSols");
            }

            anexosPqrDAO.delete(entity);
        } catch (Exception e) {
        	throw new Exception("Error Eliminando Anexo");
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateAnexosPqr(AnexosPqr entity) throws Exception {
        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("AnexosPqr");
            }

            if (entity.getDescripcionAnexo() == null) {
                throw new ZMessManager().new EmptyFieldException("Descripción");
            }

            if ((entity.getDescripcionAnexo() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getDescripcionAnexo(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException("Descripción");
            }

            if (entity.getEstadoRegistro() == null) {
                throw new ZMessManager().new EmptyFieldException("Estado");
            }

            if ((entity.getEstadoRegistro() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getEstadoRegistro(), 1) == false)) {
                throw new ZMessManager().new NotValidFormatException("Estado");
            }

            if (entity.getIdAnexoPqr() == null) {
                throw new ZMessManager().new EmptyFieldException("idAnexoPqr");
            }

            if ((entity.getIdAnexoPqr() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdAnexoPqr(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException("idAnexoPqr");
            }

            if ((entity.getUsuarioCreador() != null) &&
                    (Utilities.checkWordAndCheckWithlength(entity.getUsuarioCreador(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException("usuarioCreador");
            }

            if ((entity.getUsuarioUltimaModificacion() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getUsuarioUltimaModificacion(), 50) == false)) {
                throw new ZMessManager().new NotValidFormatException("usuario Ultima Modificacion");
            }

            anexosPqrDAO.update(entity);
        } catch (Exception e) {
        	throw new Exception("Error Modificando Anexo");
        } finally {
        }
    }

    @Transactional(readOnly = true)
    public List<AnexosPqrDTO> getDataAnexosPqr() throws Exception {
        try {
            List<AnexosPqr> anexosPqr = anexosPqrDAO.findAll();

            List<AnexosPqrDTO> anexosPqrDTO = new ArrayList<AnexosPqrDTO>();

            for (AnexosPqr anexosPqrTmp : anexosPqr) {
                AnexosPqrDTO anexosPqrDTO2 = new AnexosPqrDTO();

                anexosPqrDTO2.setIdAnexoPqr(anexosPqrTmp.getIdAnexoPqr());
                anexosPqrDTO2.setDescripcionAnexo((anexosPqrTmp.getDescripcionAnexo() != null)
                    ? anexosPqrTmp.getDescripcionAnexo() : null);
                anexosPqrDTO2.setEstadoRegistro((anexosPqrTmp.getEstadoRegistro() != null)
                    ? anexosPqrTmp.getEstadoRegistro() : null);
                anexosPqrDTO2.setFechaCreacion(anexosPqrTmp.getFechaCreacion());
                anexosPqrDTO2.setFechaUltimaModificacion(anexosPqrTmp.getFechaUltimaModificacion());
                anexosPqrDTO2.setUsuarioCreador((anexosPqrTmp.getUsuarioCreador() != null)
                    ? anexosPqrTmp.getUsuarioCreador() : null);
                anexosPqrDTO2.setUsuarioUltimaModificacion((anexosPqrTmp.getUsuarioUltimaModificacion() != null)
                    ? anexosPqrTmp.getUsuarioUltimaModificacion() : null);
                anexosPqrDTO.add(anexosPqrDTO2);
            }

            return anexosPqrDTO;
        } catch (Exception e) {
        	throw new Exception("Error Consultando Anexo");
        }
    }

    @Transactional(readOnly = true)
    public AnexosPqr getAnexosPqr(Long idAnexoPqr) throws Exception {
        AnexosPqr entity = null;

        try {
            entity = anexosPqrDAO.findById(idAnexoPqr);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Anexos Pqr");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<AnexosPqr> findPageAnexosPqr(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<AnexosPqr> entity = null;

        try {
            entity = anexosPqrDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Cuenta AnexosPqr");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberAnexosPqr() throws Exception {
        Long entity = null;

        try {
            entity = anexosPqrDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Cuenta AnexosPqr");
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
    public List<AnexosPqr> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<AnexosPqr> list = new ArrayList<AnexosPqr>();
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
                        throw new Exception(e.getMessage());
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
            list = anexosPqrDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
