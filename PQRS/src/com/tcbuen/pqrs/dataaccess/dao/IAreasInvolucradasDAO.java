package com.tcbuen.pqrs.dataaccess.dao;

import java.util.List;

import com.tcbuen.pqrs.dataaccess.api.Dao;
import com.tcbuen.pqrs.modelo.AreasInvolucradas;


/**
* Interface for   AreasInvolucradasDAO.
*
*/
public interface IAreasInvolucradasDAO extends Dao<AreasInvolucradas, Long> {
	
	public List<AreasInvolucradas> consultarTodasAreaXAnxs() throws Exception;
}
