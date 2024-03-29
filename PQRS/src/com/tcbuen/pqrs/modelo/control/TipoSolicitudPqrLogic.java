package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.dataaccess.dao.*;
import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.TipoSolicitudPqrDTO;
import com.tcbuen.pqrs.presentation.businessDelegate.BusinessDelegatorView;
import com.tcbuen.pqrs.utilities.FacesUtils;
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
@Service("TipoSolicitudPqrLogic")
public class TipoSolicitudPqrLogic implements ITipoSolicitudPqrLogic {
    /**
     * DAO injected by Spring that manages TipoSolicitudPqr entities
     *
     */
    @Autowired
    private ITipoSolicitudPqrDAO tipoSolicitudPqrDAO;

    /**
    * DAO injected by Spring that manages AnxsXTpSol entities
    *
    */
    @Autowired
    private IAnxsXTpSolDAO anxsXTpSolDAO;

    /**
    * DAO injected by Spring that manages MotReclXTpSol entities
    *
    */
    @Autowired
    private IMotReclXTpSolDAO motReclXTpSolDAO;

    /**
    * DAO injected by Spring that manages MotSolXTpSol entities
    *
    */
    @Autowired
    private IMotSolXTpSolDAO motSolXTpSolDAO;

    /**
    * DAO injected by Spring that manages SolicitudPqr entities
    *
    */
    @Autowired
    private ISolicitudPqrDAO solicitudPqrDAO;
    
    @Autowired
    private IMotReclXTpSolLogic motReclXTpSolLogic;
    
    @Autowired
    private IMotivoReclamacionLogic motivoReclamacionLogic;
    
    @Autowired
    private IMotSolXTpSolLogic motSolXTpSolLogic;
    
    @Autowired
    private IMotivoSolicitudLogic motivoSolicitudLogic;
    
    @Autowired
    private IAnxsXTpSolLogic anxsXTpSolLogic;
    
    @Autowired
    private IAnexosPqrLogic anexosPqrLogic;

    @Transactional(readOnly = true)
    public List<TipoSolicitudPqr> getTipoSolicitudPqr()
        throws Exception {
        List<TipoSolicitudPqr> list = new ArrayList<TipoSolicitudPqr>();

        try {
            list = tipoSolicitudPqrDAO.findAll();
        } catch (Exception e) {
            throw new ZMessManager().new GettingException(ZMessManager.ALL +
                "TipoSolicitudPqr");
        } finally {
        }

        return list;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveTipoSolicitudPqr(TipoSolicitudPqr entity)
        throws Exception {
        try {
        	/*
            if (entity.getIdTpSolPqr() == null) {
                throw new ZMessManager().new EmptyFieldException("idTpSolPqr");
            }

            if ((entity.getIdTpSolPqr() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdTpSolPqr(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idTpSolPqr");
            }
            
            if (getTipoSolicitudPqr(entity.getIdTpSolPqr()) != null) {
                throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
            }
            */
            if (entity.getDescTpSol() == null) {
                throw new ZMessManager().new EmptyFieldException("descTpSol");
            }

            if ((entity.getDescTpSol() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescTpSol(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descTpSol");
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

            tipoSolicitudPqrDAO.save(entity);
        } catch (Exception e) {
        	throw new Exception("Error Guardando Tipo de Solicitud");
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteTipoSolicitudPqr(TipoSolicitudPqr entity)
        throws Exception {
        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("TipoSolicitudPqr");
        }

        if (entity.getIdTpSolPqr() == null) {
            throw new ZMessManager().new EmptyFieldException("idTpSolPqr");
        }

        List<AnxsXTpSol> anxsXTpSols = null;
        List<MotReclXTpSol> motReclXTpSols = null;
        List<MotSolXTpSol> motSolXTpSols = null;
        List<SolicitudPqr> solicitudPqrs = null;

        try {
            anxsXTpSols = anxsXTpSolDAO.findByProperty("tipoSolicitudPqr.idTpSolPqr",
                    entity.getIdTpSolPqr());

            if (Utilities.validationsList(anxsXTpSols) == true) {
                throw new ZMessManager().new DeletingException("anxsXTpSols");
            }

            motReclXTpSols = motReclXTpSolDAO.findByProperty("tipoSolicitudPqr.idTpSolPqr",
                    entity.getIdTpSolPqr());

            if (Utilities.validationsList(motReclXTpSols) == true) {
                throw new ZMessManager().new DeletingException("motReclXTpSols");
            }

            motSolXTpSols = motSolXTpSolDAO.findByProperty("tipoSolicitudPqr.idTpSolPqr",
                    entity.getIdTpSolPqr());

            if (Utilities.validationsList(motSolXTpSols) == true) {
                throw new ZMessManager().new DeletingException("motSolXTpSols");
            }

            solicitudPqrs = solicitudPqrDAO.findByProperty("tipoSolicitudPqr.idTpSolPqr",
                    entity.getIdTpSolPqr());

            if (Utilities.validationsList(solicitudPqrs) == true) {
                throw new ZMessManager().new DeletingException("solicitudPqrs");
            }

            tipoSolicitudPqrDAO.delete(entity);
        } catch (Exception e) {
        	throw new Exception("Error eliminando Tipo de Solicitud");
        } finally {
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateTipoSolicitudPqr(TipoSolicitudPqr entity)
        throws Exception {
        try {
            if (entity == null) {
                throw new ZMessManager().new NullEntityExcepcion(
                    "TipoSolicitudPqr");
            }

            if (entity.getDescTpSol() == null) {
                throw new ZMessManager().new EmptyFieldException("descTpSol");
            }

            if ((entity.getDescTpSol() != null) &&
                    (Utilities.checkWordAndCheckWithlength(
                        entity.getDescTpSol(), 200) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "descTpSol");
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

            if (entity.getIdTpSolPqr() == null) {
                throw new ZMessManager().new EmptyFieldException("idTpSolPqr");
            }

            if ((entity.getIdTpSolPqr() != null) &&
                    (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +
                        entity.getIdTpSolPqr(), 10, 0) == false)) {
                throw new ZMessManager().new NotValidFormatException(
                    "idTpSolPqr");
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

            tipoSolicitudPqrDAO.update(entity);
        } catch (Exception e) {
        	throw new Exception("Error Modificando Tipo de Solicitud");
        } finally {
        }
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void save_mot_recl_mot_sol_anxs_x_tipo(TipoSolicitudPqr tipoSol, List<MotivoReclamacion> motivosReclamacionTargetCopia,
    		List<MotivoReclamacion> motivosReclamacionTarget, List<MotivoSolicitud> motivosSolicitudTargetCopia,
			List<MotivoSolicitud> motivosSolicitudTarget, List<AnexosPqr> anexosPqrTargetCopia,
			List<AnexosPqr> anexosPqrTarget, String esObligatorioSeleccionado) throws Exception {
		try {
			
			//Elimina Motivos de Reaclamacion x Tipo de Solicitud
			if (motivosReclamacionTargetCopia != null) {
				List<MotReclXTpSol> motReclXTpSol = motReclXTpSolLogic.getMotReclXTpSol();
				for (MotivoReclamacion mtrc : motivosReclamacionTargetCopia) {
					for (MotReclXTpSol motRecl : motReclXTpSol) {
						if (mtrc.getIdMotRecl() == motRecl.getMotivoReclamacion().getIdMotRecl()
								&& motRecl.getTipoSolicitudPqr().getIdTpSolPqr() == tipoSol.getIdTpSolPqr()) {
							motReclXTpSolLogic.deleteMotReclXTpSol(motRecl);
						}
					}
				}
			}
			
			//Guarda Motivos de Reclamacion x Tipo de Solicitud
			for (Object object : motivosReclamacionTarget) {
				String value = (String) object;
				MotivoReclamacion motRecl = motivoReclamacionLogic.getMotivoReclamacion(Long.parseLong(value)); 
				MotReclXTpSol motReclXTpSol = new MotReclXTpSol();
				motReclXTpSol.setMotivoReclamacion(motRecl);
				motReclXTpSol.setTipoSolicitudPqr(tipoSol);

				motReclXTpSolLogic.saveMotReclXTpSol(motReclXTpSol);
			}
			
			//Elimina Moivos de Solicitud x Tipo de Solicitud
			if (motivosSolicitudTargetCopia != null) {
				List<MotSolXTpSol> motSolXTpSol = motSolXTpSolLogic.getMotSolXTpSol();
				for (MotivoSolicitud mtsc : motivosSolicitudTargetCopia) {
					for (MotSolXTpSol motSol : motSolXTpSol) {
						if (mtsc.getIdMotSol() == motSol.getMotivoSolicitud().getIdMotSol()
								&& motSol.getTipoSolicitudPqr().getIdTpSolPqr() == tipoSol.getIdTpSolPqr()) {
							motSolXTpSolLogic.deleteMotSolXTpSol(motSol);
						}
					}
				}
			}
			
			//Guarda Motivos de Solicitud x Tipo de Solicitud
			for (Object object : motivosSolicitudTarget) {
				String value = (String) object;
				MotivoSolicitud motSol = motivoSolicitudLogic.getMotivoSolicitud(Long.parseLong(value));
				MotSolXTpSol motSolxTpSol = new MotSolXTpSol();
				motSolxTpSol.setMotivoSolicitud(motSol);
				motSolxTpSol.setTipoSolicitudPqr(tipoSol);

				motSolXTpSolLogic.saveMotSolXTpSol(motSolxTpSol);
			}
			
			//Elimina Anexos x Tipo de Solicitud
			if (anexosPqrTargetCopia != null) {
				List<AnxsXTpSol> anxsXTpSol = anxsXTpSolLogic.getAnxsXTpSol();
				for (AnexosPqr anxc : anexosPqrTargetCopia) {
					for (AnxsXTpSol anxs : anxsXTpSol) {
						if (anxc.getIdAnexoPqr() == anxs.getAnexosPqr().getIdAnexoPqr()
								&& anxs.getTipoSolicitudPqr().getIdTpSolPqr() == tipoSol.getIdTpSolPqr()) {
							anxsXTpSolLogic.deleteAnxsXTpSol(anxs);
						}
					}
				}
			}
			
			//Guarda Anexos x Tipo de Solicitud
			for (Object object : anexosPqrTarget) {
				String value = (String) object;
				AnexosPqr anexPqr = anexosPqrLogic.getAnexosPqr(Long.parseLong(value));
				AnxsXTpSol anxsXTpSol = new AnxsXTpSol();
				anxsXTpSol.setEsObligatorio(esObligatorioSeleccionado);
				anxsXTpSol.setAnexosPqr(anexPqr);
				anxsXTpSol.setTipoSolicitudPqr(tipoSol);

				anxsXTpSolLogic.saveAnxsXTpSol(anxsXTpSol);
			}
		} catch (Exception e) {
        	throw new Exception("Error Creando - Modificando Tipo de Solicitud");
        } 
	}

    @Transactional(readOnly = true)
    public List<TipoSolicitudPqrDTO> getDataTipoSolicitudPqr()
        throws Exception {
        try {
            List<TipoSolicitudPqr> tipoSolicitudPqr = tipoSolicitudPqrDAO.findAll();

            List<TipoSolicitudPqrDTO> tipoSolicitudPqrDTO = new ArrayList<TipoSolicitudPqrDTO>();

            for (TipoSolicitudPqr tipoSolicitudPqrTmp : tipoSolicitudPqr) {
                TipoSolicitudPqrDTO tipoSolicitudPqrDTO2 = new TipoSolicitudPqrDTO();

                tipoSolicitudPqrDTO2.setIdTpSolPqr(tipoSolicitudPqrTmp.getIdTpSolPqr());
                tipoSolicitudPqrDTO2.setDescTpSol((tipoSolicitudPqrTmp.getDescTpSol() != null)
                    ? tipoSolicitudPqrTmp.getDescTpSol() : null);
                tipoSolicitudPqrDTO2.setEstadoRegistro((tipoSolicitudPqrTmp.getEstadoRegistro() != null)
                    ? tipoSolicitudPqrTmp.getEstadoRegistro() : null);
                tipoSolicitudPqrDTO2.setFechaCreacion(tipoSolicitudPqrTmp.getFechaCreacion());
                tipoSolicitudPqrDTO2.setFechaUltimaModificacion(tipoSolicitudPqrTmp.getFechaUltimaModificacion());
                tipoSolicitudPqrDTO2.setUsuarioCreador((tipoSolicitudPqrTmp.getUsuarioCreador() != null)
                    ? tipoSolicitudPqrTmp.getUsuarioCreador() : null);
                tipoSolicitudPqrDTO2.setUsuarioUltimaModificacion((tipoSolicitudPqrTmp.getUsuarioUltimaModificacion() != null)
                    ? tipoSolicitudPqrTmp.getUsuarioUltimaModificacion() : null);
                tipoSolicitudPqrDTO.add(tipoSolicitudPqrDTO2);
            }

            return tipoSolicitudPqrDTO;
        } catch (Exception e) {
        	throw new Exception("Error Consultando Tipo de Soliciud");
        }
    }

    @Transactional(readOnly = true)
    public TipoSolicitudPqr getTipoSolicitudPqr(Long idTpSolPqr)
        throws Exception {
        TipoSolicitudPqr entity = null;

        try {
            entity = tipoSolicitudPqrDAO.findById(idTpSolPqr);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException("Tipo de Solicitud");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public List<TipoSolicitudPqr> findPageTipoSolicitudPqr(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception {
        List<TipoSolicitudPqr> entity = null;

        try {
            entity = tipoSolicitudPqrDAO.findPage(sortColumnName,
                    sortAscending, startRow, maxResults);
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "Cuenta Tipo Solicitud");
        } finally {
        }

        return entity;
    }

    @Transactional(readOnly = true)
    public Long findTotalNumberTipoSolicitudPqr() throws Exception {
        Long entity = null;

        try {
            entity = tipoSolicitudPqrDAO.count();
        } catch (Exception e) {
            throw new ZMessManager().new FindingException(
                "Cuenta Tipo de Solicitud");
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
    public List<TipoSolicitudPqr> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception {
        List<TipoSolicitudPqr> list = new ArrayList<TipoSolicitudPqr>();
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
            list = tipoSolicitudPqrDAO.findByCriteria(where);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
        }

        return list;
    }
}
