package com.tcbuen.pqrs.modelo.control;

import com.tcbuen.pqrs.dataaccess.dao.*;
import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.RolesDTO;
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
@Service("RolesLogic")
public class RolesLogic implements IRolesLogic {
	/**
	 * DAO injected by Spring that manages Roles entities
	 * 
	 */
	@Autowired
	private IRolesDAO rolesDAO;

	/**
	 * DAO injected by Spring that manages UsuariosInternos entities
	 * 
	 */
	@Autowired
	private IUsuariosInternosDAO usuariosInternosDAO;

	@Transactional(readOnly = true)
	public List<Roles> getRoles() throws Exception {
		List<Roles> list = new ArrayList<Roles>();

		try {
			list = rolesDAO.findAll();
		} catch (Exception e) {
			throw new ZMessManager().new GettingException(ZMessManager.ALL
					+ "Roles");
		} finally {
		}

		return list;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveRoles(Roles entity) throws Exception {
		try {

			/*
			 if (entity.getIdRol() == null) { 
			 	throw new ZMessManager().new EmptyFieldException("idRol"); }
			 
			 if ((entity.getIdRol() != null) && (Utilities.checkNumberAndCheckWithPrecisionAndScale("" +entity.getIdRol(), 10, 0) == false)) {
			 	throw new ZMessManager().new NotValidFormatException("idRol");
			 }
			 
			 if (getRoles(entity.getIdRol()) != null) {
			 	throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY); 
			 }
			 */
			if (entity.getNombreRol() == null) {
				throw new ZMessManager().new EmptyFieldException("nombreRol");
			}

			if ((entity.getNombreRol() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getNombreRol(), 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"nombreRol");
			}

			if (entity.getEstadoRegistro() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"estadoRegistro");
			}

			if ((entity.getEstadoRegistro() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getEstadoRegistro(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"estadoRegistro");
			}

			if ((entity.getUsuarioCreador() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getUsuarioCreador(), 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuarioCreador");
			}

			if ((entity.getUsuarioUltimaModificacion() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getUsuarioUltimaModificacion(), 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuarioUltimaModificacion");
			}

			rolesDAO.save(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteRoles(Roles entity) throws Exception {
		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Roles");
		}

		if (entity.getIdRol() == null) {
			throw new ZMessManager().new EmptyFieldException("idRol");
		}

		List<UsuariosInternos> usuariosInternoses = null;

		try {
			usuariosInternoses = usuariosInternosDAO.findByProperty(
					"roles.idRol", entity.getIdRol());

			if (Utilities.validationsList(usuariosInternoses) == true) {
				throw new ZMessManager().new DeletingException(
						"usuariosInternoses");
			}

			rolesDAO.delete(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateRoles(Roles entity) throws Exception {
		try {
			if (entity == null) {
				throw new ZMessManager().new NullEntityExcepcion("Roles");
			}

			if (entity.getEstadoRegistro() == null) {
				throw new ZMessManager().new EmptyFieldException(
						"estadoRegistro");
			}

			if ((entity.getEstadoRegistro() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getEstadoRegistro(), 1) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"estadoRegistro");
			}

			if (entity.getIdRol() == null) {
				throw new ZMessManager().new EmptyFieldException("idRol");
			}

			if ((entity.getIdRol() != null)
					&& (Utilities.checkNumberAndCheckWithPrecisionAndScale(""
							+ entity.getIdRol(), 10, 0) == false)) {
				throw new ZMessManager().new NotValidFormatException("idRol");
			}

			if (entity.getNombreRol() == null) {
				throw new ZMessManager().new EmptyFieldException("nombreRol");
			}

			if ((entity.getNombreRol() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getNombreRol(), 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"nombreRol");
			}

			if ((entity.getUsuarioCreador() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getUsuarioCreador(), 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuarioCreador");
			}

			if ((entity.getUsuarioUltimaModificacion() != null)
					&& (Utilities.checkWordAndCheckWithlength(
							entity.getUsuarioUltimaModificacion(), 50) == false)) {
				throw new ZMessManager().new NotValidFormatException(
						"usuarioUltimaModificacion");
			}

			rolesDAO.update(entity);
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	@Transactional(readOnly = true)
	public List<RolesDTO> getDataRoles() throws Exception {
		try {
			List<Roles> roles = rolesDAO.findAll();

			List<RolesDTO> rolesDTO = new ArrayList<RolesDTO>();

			for (Roles rolesTmp : roles) {
				RolesDTO rolesDTO2 = new RolesDTO();

				rolesDTO2.setIdRol(rolesTmp.getIdRol());
				rolesDTO2
						.setEstadoRegistro((rolesTmp.getEstadoRegistro() != null) ? rolesTmp
								.getEstadoRegistro() : null);
				rolesDTO2.setFechaCreacion(rolesTmp.getFechaCreacion());
				rolesDTO2.setFechaUltimaModificacion(rolesTmp
						.getFechaUltimaModificacion());
				rolesDTO2
						.setNombreRol((rolesTmp.getNombreRol() != null) ? rolesTmp
								.getNombreRol() : null);
				rolesDTO2
						.setUsuarioCreador((rolesTmp.getUsuarioCreador() != null) ? rolesTmp
								.getUsuarioCreador() : null);
				rolesDTO2.setUsuarioUltimaModificacion((rolesTmp
						.getUsuarioUltimaModificacion() != null) ? rolesTmp
						.getUsuarioUltimaModificacion() : null);
				rolesDTO.add(rolesDTO2);
			}

			return rolesDTO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = true)
	public Roles getRoles(Long idRol) throws Exception {
		Roles entity = null;

		try {
			entity = rolesDAO.findById(idRol);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Roles");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public List<Roles> findPageRoles(String sortColumnName,
			boolean sortAscending, int startRow, int maxResults)
			throws Exception {
		List<Roles> entity = null;

		try {
			entity = rolesDAO.findPage(sortColumnName, sortAscending, startRow,
					maxResults);
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Roles Count");
		} finally {
		}

		return entity;
	}

	@Transactional(readOnly = true)
	public Long findTotalNumberRoles() throws Exception {
		Long entity = null;

		try {
			entity = rolesDAO.count();
		} catch (Exception e) {
			throw new ZMessManager().new FindingException("Roles Count");
		} finally {
		}

		return entity;
	}

	/**
	 * 
	 * @param varibles
	 *            este arreglo debera tener:
	 * 
	 *            [0] = String variable = (String) varibles[i]; representa como
	 *            se llama la variable en el pojo
	 * 
	 *            [1] = Boolean booVariable = (Boolean) varibles[i + 1];
	 *            representa si el valor necesita o no ''(comillas simples)usado
	 *            para campos de tipo string
	 * 
	 *            [2] = Object value = varibles[i + 2]; representa el valor que
	 *            se va a buscar en la BD
	 * 
	 *            [3] = String comparator = (String) varibles[i + 3]; representa
	 *            que tipo de busqueda voy a hacer.., ejemplo: where
	 *            nombre=william o where nombre<>william, en este campo iria el
	 *            tipo de comparador que quiero si es = o <>
	 * 
	 *            Se itera de 4 en 4..., entonces 4 registros del arreglo
	 *            representan 1 busqueda en un campo, si se ponen mas pues el
	 *            continuara buscando en lo que se le ingresen en los otros 4
	 * 
	 * 
	 * @param variablesBetween
	 * 
	 *            la diferencia son estas dos posiciones
	 * 
	 *            [0] = String variable = (String) varibles[j]; la variable ne
	 *            la BD que va a ser buscada en un rango
	 * 
	 *            [1] = Object value = varibles[j + 1]; valor 1 para buscar en
	 *            un rango
	 * 
	 *            [2] = Object value2 = varibles[j + 2]; valor 2 para buscar en
	 *            un rango ejempolo: a > 1 and a < 5 --> 1 seria value y 5 seria
	 *            value2
	 * 
	 *            [3] = String comparator1 = (String) varibles[j + 3];
	 *            comparador 1 ejemplo: a comparator1 1 and a < 5
	 * 
	 *            [4] = String comparator2 = (String) varibles[j + 4];
	 *            comparador 2 ejemplo: a comparador1>1 and a comparador2<5 (el
	 *            original: a > 1 and a < 5) *
	 * @param variablesBetweenDates
	 *            (en este caso solo para mysql) [0] = String variable =
	 *            (String) varibles[k]; el nombre de la variable que hace
	 *            referencia a una fecha
	 * 
	 *            [1] = Object object1 = varibles[k + 2]; fecha 1 a
	 *            comparar(deben ser dates)
	 * 
	 *            [2] = Object object2 = varibles[k + 3]; fecha 2 a
	 *            comparar(deben ser dates)
	 * 
	 *            esto hace un between entre las dos fechas.
	 * 
	 * @return lista con los objetos que se necesiten
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<Roles> findByCriteria(Object[] variables,
			Object[] variablesBetween, Object[] variablesBetweenDates)
			throws Exception {
		List<Roles> list = new ArrayList<Roles>();
		String where = new String();
		String tempWhere = new String();

		if (variables != null) {
			for (int i = 0; i < variables.length; i++) {
				if ((variables[i] != null) && (variables[i + 1] != null)
						&& (variables[i + 2] != null)
						&& (variables[i + 3] != null)) {
					String variable = (String) variables[i];
					Boolean booVariable = (Boolean) variables[i + 1];
					Object value = variables[i + 2];
					String comparator = (String) variables[i + 3];

					if (booVariable.booleanValue()) {
						tempWhere = (tempWhere.length() == 0) ? ("(model."
								+ variable + " " + comparator + " \'" + value + "\' )")
								: (tempWhere + " AND (model." + variable + " "
										+ comparator + " \'" + value + "\' )");
					} else {
						tempWhere = (tempWhere.length() == 0) ? ("(model."
								+ variable + " " + comparator + " " + value + " )")
								: (tempWhere + " AND (model." + variable + " "
										+ comparator + " " + value + " )");
					}
				}

				i = i + 3;
			}
		}

		if (variablesBetween != null) {
			for (int j = 0; j < variablesBetween.length; j++) {
				if ((variablesBetween[j] != null)
						&& (variablesBetween[j + 1] != null)
						&& (variablesBetween[j + 2] != null)
						&& (variablesBetween[j + 3] != null)
						&& (variablesBetween[j + 4] != null)) {
					String variable = (String) variablesBetween[j];
					Object value = variablesBetween[j + 1];
					Object value2 = variablesBetween[j + 2];
					String comparator1 = (String) variablesBetween[j + 3];
					String comparator2 = (String) variablesBetween[j + 4];
					tempWhere = (tempWhere.length() == 0) ? ("(" + value + " "
							+ comparator1 + " " + variable + " and " + variable
							+ " " + comparator2 + " " + value2 + " )")
							: (tempWhere + " AND (" + value + " " + comparator1
									+ " " + variable + " and " + variable + " "
									+ comparator2 + " " + value2 + " )");
				}

				j = j + 4;
			}
		}

		if (variablesBetweenDates != null) {
			for (int k = 0; k < variablesBetweenDates.length; k++) {
				if ((variablesBetweenDates[k] != null)
						&& (variablesBetweenDates[k + 1] != null)
						&& (variablesBetweenDates[k + 2] != null)) {
					String variable = (String) variablesBetweenDates[k];
					Object object1 = variablesBetweenDates[k + 1];
					Object object2 = variablesBetweenDates[k + 2];
					String value = null;
					String value2 = null;

					try {
						Date date1 = (Date) object1;
						Date date2 = (Date) object2;
						value = Utilities
								.formatDateWithoutTimeInAStringForBetweenWhere(date1);
						value2 = Utilities
								.formatDateWithoutTimeInAStringForBetweenWhere(date2);
					} catch (Exception e) {
						list = null;
						throw e;
					}

					tempWhere = (tempWhere.length() == 0) ? ("(model."
							+ variable + " between \'" + value + "\' and \'"
							+ value2 + "\')") : (tempWhere + " AND (model."
							+ variable + " between \'" + value + "\' and \'"
							+ value2 + "\')");
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
			list = rolesDAO.findByCriteria(where);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} finally {
		}

		return list;
	}
}
