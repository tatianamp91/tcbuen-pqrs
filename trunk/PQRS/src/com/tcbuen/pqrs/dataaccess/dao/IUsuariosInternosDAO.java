package com.tcbuen.pqrs.dataaccess.dao;

import com.tcbuen.pqrs.dataaccess.api.Dao;
import com.tcbuen.pqrs.modelo.UsuariosInternos;


/**
* Interface for   UsuariosInternosDAO.
*
*/
public interface IUsuariosInternosDAO extends Dao<UsuariosInternos, Long> {
	
	public UsuariosInternos consultarLoginContrasena(String login, String contrasena) throws Exception;
}
