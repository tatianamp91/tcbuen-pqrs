package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.dataaccess.dao.*;
import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.AnxsXAreaDTO;
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
@Service("AnxsXAreaLogic")
public class AnxsXAreaLogic implements IAnxsXAreaLogic {
    /**
     * DAO injected by Spring that manages AnxsXArea entities
     *
     */
    @Autowired
    private IAnxsXAreaDAO anxsXAreaDAO;

    /**
    * Logic injected by Spring that manages AnexosPqr entities
    *
    */
    @Autowired
    IAnexosPqrLogic logicAnexosPqr1;

    /**
    * Logic injected by Spring that manages AreasInvolucradas entities
    *
    */
    @Autowired
    IAreasInvolucradasLogic logicAreasInvolucradas2;
    
    @Autowired
    IAnexosPqrLogic anexosPqrLogic;

    @Transactional(readOnly = true)
    public List<AnxsXArea> getAnxsXArea() throws Exception {
        List<AnxsXArea> list = new ArrayList<AnxsXArea>();

        try {
            list = anxsXAreaDAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "AnxsXArea");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveAnxsXArea(AnxsXArea entity) throws Exception {
        try {
        	/*
            if (getAnxsXArea(entity.getIdAnxXArea()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }	
            if (entity.getIdAnxXArea() == null) {
                throw new ZMessManager().new EmptyFieldException("idAnxXArea");
            }

            if ((entity.getIdAnxXArea() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdAnxXArea(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idAnxXArea");
            }*/
            if (entity.getAnexosPqr() == null) {
                throw new ZMessManager().new ForeignException("anexosPqr");
            }

            if (entity.getAreasInvolucradas() == null) {
                throw new ZMessManager().new ForeignException(
                    "areasInvolucradas");
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

            anxsXAreaDAO.save(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteAnxsXArea(AnxsXArea entity) throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("AnxsXArea");
        }

        if (entity.getIdAnxXArea() == null) {
            throw new ZMessManager().new EmptyFieldException("idAnxXArea");
        }

        try {
            anxsXAreaDAO.delete(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateAnxsXArea(AnxsXArea entity) throws Exception {
        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion("AnxsXArea");
            }

            if (entity.getAnexosPqr() == null) {
                throw new ZMessManager().new ForeignException("anexosPqr");
            }

            if (entity.getAreasInvolucradas() == null) {
                throw new ZMessManager().new ForeignException(
                    "areasInvolucradas");
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

            if (entity.getIdAnxXArea() == null) {
                throw new ZMessManager().new EmptyFieldException("idAnxXArea");
            }

            if ((entity.getIdAnxXArea() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdAnxXArea(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idAnxXArea");
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

            anxsXAreaDAO.update(entity);
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void save_anxs_x_area(AreasInvolucradas areasInvolucradas,
			List<AnexosPqr> anexosPqrTargetCopia, List<AnexosPqr> anexosPqrTarget,
			String esObligatorioSeleccionado) throws Exception {
		try {
			
			//Elimina Anexos x Area
			if (anexosPqrTargetCopia != null) {
				List<AnxsXArea> anxsXArea = getAnxsXArea();
				for (AnexosPqr anxc : anexosPqrTargetCopia) {
					for (AnxsXArea anxs : anxsXArea) {
						if (anxc.getIdAnexoPqr() == anxs.getAnexosPqr().getIdAnexoPqr()
								&& anxs.getAreasInvolucradas().getIdAreaInvolucrada() == areasInvolucradas.getIdAreaInvolucrada()) {
							deleteAnxsXArea(anxs);
						}
					}
				}
			}
			
			//Guarda Anexos x Area
			for (Object object : anexosPqrTarget) {
				String value = (String) object;
				AnexosPqr anexPqr = anexosPqrLogic.getAnexosPqr(Long.parseLong(value));
				AnxsXArea anxsXArea = new AnxsXArea();
				anxsXArea.setEsObligatorio(esObligatorioSeleccionado);
				anxsXArea.setAnexosPqr(anexPqr);
				anxsXArea.setAreasInvolucradas(areasInvolucradas);

				saveAnxsXArea(anxsXArea);
			}
		} catch (Exception e) {
        	throw new Exception("Error Creando - Modificando Area - Anexo");
        } finally {
        }
	}

    @Transactional(readOnly = true)
    public List<AnxsXAreaDTO> getDataAnxsXArea() throws Exception {
        try {
            List<AnxsXArea> anxsXArea = anxsXAreaDAO.findAll();

            List<AnxsXAreaDTO> anxsXAreaDTO = new ArrayList<AnxsXAreaDTO>();

            for (AnxsXArea anxsXAreaTmp : anxsXArea) {
                AnxsXAreaDTO anxsXAreaDTO2 = new AnxsXAreaDTO();

                anxsXAreaDTO2.setIdAnxXArea(anxsXAreaTmp.getIdAnxXArea());
                anxsXAreaDTO2.setEsObligatorio((anxsXAreaTmp.getEsObligatorio() != null)
                    ? anxsXAreaTmp.getEsObligatorio() : null);
                anxsXAreaDTO2.setIdAnexoPqr_AnexosPqr((anxsXAreaTmp.getAnexosPqr()
                                                                   .getIdAnexoPqr() != null)
                    ? anxsXAreaTmp.getAnexosPqr().getIdAnexoPqr() : null);
                anxsXAreaDTO2.setIdAreaInvolucrada_AreasInvolucradas((anxsXAreaTmp.getAreasInvolucradas()
                                                                                  .getIdAreaInvolucrada() != null)
                    ? anxsXAreaTmp.getAreasInvolucradas().getIdAreaInvolucrada()
                    : null);
                anxsXAreaDTO.add(anxsXAreaDTO2);
            }

            return anxsXAreaDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public AnxsXArea getAnxsXArea(Long idAnxXArea) throws Exception {
        AnxsXArea entity = null;

        try {
            entity = anxsXAreaDAO.findById(idAnxXArea);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("AnxsXArea");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<AnxsXArea> findPageAnxsXArea(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception {
        List<AnxsXArea> entity = null;

        try {
            entity = anxsXAreaDAO.findPage(sortColumnName, sortAscending,
                    startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("AnxsXArea Count");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberAnxsXArea() throws Exception {
        Long entity = null;

        try {
            entity = anxsXAreaDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("AnxsXArea Count");
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
    public List<AnxsXArea> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<AnxsXArea> list = new ArrayList<AnxsXArea>();
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
            list = anxsXAreaDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
