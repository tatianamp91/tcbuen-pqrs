package com.tcbuen.pqrs.presentation.backingBeans;



import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.AreasInvolucradasDTO;
import com.tcbuen.pqrs.modelo.dto.RolesDTO;
import com.tcbuen.pqrs.modelo.dto.UsuariosInternosDTO;
import com.tcbuen.pqrs.presentation.businessDelegate.*;
import com.tcbuen.pqrs.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;
import org.primefaces.event.RowEditEvent;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
@ManagedBean
@ViewScoped
public class UsuariosInternosView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtApellidos;
    private InputText txtContrasena;
	private Password passContrasena;
 //   private Password passContrasena2;
    private InputText txtCorreoElectronico;
    private String estadoRegistro;
    private InputText txtEstadoRegistro;
    private InputText txtLogin;
    private InputText txtNombres;
    private InputText txtNumeroIdentificacion;
    private InputText txtIdAreaInvolucrada_AreasInvolucradas;
    private InputText txtIdRol_Roles;
    private InputText txtIdUsuInterno;
    private Calendar txtFechaCreacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<UsuariosInternosDTO> data;
    private UsuariosInternosDTO selectedUsuariosInternos;
    private UsuariosInternos entity;
    private boolean showDialog;
    private boolean showDialog2;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
    private String estadoRegistroSeleccionado;
    private Long idAreaInvolucrada;
    private List<SelectItem> areasInvolucradas;
    private Long idRol;
    private List<SelectItem> idRoles;
    private List<Roles> roles;
    private String rol;
    private List<AreasInvolucradas> areas;
    private String area;
    private UsuariosInternos usuInternos;
    private Long idUsuInterno;
    private String password;
    //private String passContrasena;
   
    private InputText txtNombres2;
    private InputText txtApellidos2;
    private InputText txtCorreoElectronico2;
    private InputText txtNumeroIdentificacion2;
    private InputText txtLogin2;
    private CommandButton btnModificar;
    
    public UsuariosInternosView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            UsuariosInternosDTO usuariosInternosDTO = (UsuariosInternosDTO) e.getObject();

            if (txtApellidos == null) {
                txtApellidos = new InputText();
            }
            txtApellidos.setValue(usuariosInternosDTO.getApellidos());

          /*  if (txtContrasena == null) {
                txtContrasena = new InputText();
            }
            txtContrasena.setValue(usuariosInternosDTO.getContrasena());
            
            if (passContrasena == null) {
                passContrasena = new Password();
            }*/
//            passContrasena.setValue(usuariosInternosDTO.getContrasena());

            if (txtCorreoElectronico == null) {
                txtCorreoElectronico = new InputText();
            }
            txtCorreoElectronico.setValue(usuariosInternosDTO.getCorreoElectronico());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }
            txtEstadoRegistro.setValue(usuariosInternosDTO.getEstadoRegistro());

            if (txtLogin == null) {
                txtLogin = new InputText();
            }
            txtLogin.setValue(usuariosInternosDTO.getLogin());

            if (txtNombres == null) {
                txtNombres = new InputText();
            }
            txtNombres.setValue(usuariosInternosDTO.getNombres());

            if (txtNumeroIdentificacion == null) {
                txtNumeroIdentificacion = new InputText();
            }
            txtNumeroIdentificacion.setValue(usuariosInternosDTO.getNumeroIdentificacion());

            if (txtIdAreaInvolucrada_AreasInvolucradas == null) {
                txtIdAreaInvolucrada_AreasInvolucradas = new InputText();
            }
            txtIdAreaInvolucrada_AreasInvolucradas.setValue(usuariosInternosDTO.getIdAreaInvolucrada_AreasInvolucradas());

            if (txtIdRol_Roles == null) {
                txtIdRol_Roles = new InputText();
            }
            txtIdRol_Roles.setValue(usuariosInternosDTO.getIdRol_Roles());

            if (txtIdUsuInterno == null) {
                txtIdUsuInterno = new InputText();
            }
            txtIdUsuInterno.setValue(usuariosInternosDTO.getIdUsuInterno());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }
            txtFechaCreacion.setValue(usuariosInternosDTO.getFechaCreacion());

            Long idUsuInterno = FacesUtils.checkLong(txtIdUsuInterno);
            entity = businessDelegatorView.getUsuariosInternos(idUsuInterno);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedUsuariosInternos = null;
        setShowDialog(true);

        return "";
    }  
    
    public String action_edit() {
		try {
						
			Long id = idUsuInterno;
			UsuariosInternos usuarioPorId = obtenerIdUsuario(id);
			
			txtNombres2.setValue(usuarioPorId.getNombres());
			txtApellidos2.setValue(usuarioPorId.getApellidos());
			txtCorreoElectronico2.setValue(usuarioPorId.getCorreoElectronico());
			setEstadoRegistroSeleccionado(usuarioPorId.getEstadoRegistro());
			txtNumeroIdentificacion2.setValue(usuarioPorId.getNumeroIdentificacion());
			txtLogin2.setValue(usuarioPorId.getLogin());
			idAreaInvolucrada = usuarioPorId.getAreasInvolucradas().getIdAreaInvolucrada();
			idRol = usuarioPorId.getRoles().getIdRol();
			
			setShowDialog2(true);		
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}
    
    public String action_modify_edit(){
    	try {
    		
    		entity = businessDelegatorView.getUsuariosInternos(idUsuInterno);
    		
    		Long id = idUsuInterno;
			UsuariosInternos usuarioPorId = obtenerIdUsuario(id);
			
    		String nombres = txtNombres2.getValue().toString();
			String apellidos = txtApellidos2.getValue().toString();
    		String numeroIdentificacion = getTxtNumeroIdentificacion2().getValue().toString();
    		String email = txtCorreoElectronico2.getValue().toString();
			String login = txtLogin2.getValue().toString();
			idAreaInvolucrada = usuarioPorId.getAreasInvolucradas().getIdAreaInvolucrada();
			idRol = usuarioPorId.getRoles().getIdRol();
			String estado = usuarioPorId.getEstadoRegistro();
			AreasInvolucradas area = businessDelegatorView.getAreasInvolucradas((idAreaInvolucrada));
			String areaInvolucrada = area.getNombreArea();
			Roles roles = businessDelegatorView.getRoles(idRol);
			String rol = roles.getNombreRol();
			
			if (usuarioPorId != null) {
				String contrasena = usuarioPorId.getContrasena().toString();
				setPassword(contrasena);
				String contrasenaUsuario = getPassword();
				System.out.println(contrasenaUsuario);

				if (!revizarCampos(nombres, apellidos, numeroIdentificacion,
						login, email, contrasena, areaInvolucrada, rol, estado)) {
					return "";
				}
				
				UsuariosInternos usuario = ObtenerNumeroIdentificacionUsuarios(numeroIdentificacion);
				if (usuario != null) {
					Long idUsuarioPorCedula = usuario.getIdUsuInterno()
							.longValue();
					if (idUsuarioPorCedula != id) {
						throw new Exception(
								"El número de Identificacion ya existe, ingrese uno nuevo");
					}
				}
				
				UsuariosInternos usuarioPorLogin = ObtenerCuentaUsuarios(login);
				if (usuarioPorLogin != null) {
					Long idUsuarioPorLogin = usuarioPorLogin.getIdUsuInterno()
							.longValue();
					if (idUsuarioPorLogin != id) {
						throw new Exception(
								"El Login del usuario ya existe, ingrese uno nuevo");
					}
				}
				
				UsuariosInternos usuarioPorEmail = ObtenerEmailUsuarios(email);
				if (usuarioPorEmail != null) {
					Long idUsuarioPorEmail = usuarioPorEmail.getIdUsuInterno()
							.longValue();
					if (idUsuarioPorEmail != id) {
						throw new Exception(
								"El Email del usuario ya existe, ingrese uno nuevo");
					}
				}
				
				
				entity.setNumeroIdentificacion(numeroIdentificacion);
				entity.setNombres(FacesUtils.checkString(txtNombres2));
				entity.setApellidos(FacesUtils.checkString(txtApellidos2));
				entity.setCorreoElectronico(FacesUtils
						.checkString(txtCorreoElectronico2));
				entity.setLogin(FacesUtils.checkString(txtLogin2));
			//	entity.setContrasena(FacesUtils.checkString(txtContrasena));
				entity.setContrasena(contrasenaUsuario);
				entity.setEstadoRegistro(estado);
				entity.setFechaCreacion(new Date());
				entity.setIdUsuInterno(id);
				entity.setAreasInvolucradas((idAreaInvolucrada != null) ? businessDelegatorView
						.getAreasInvolucradas((idAreaInvolucrada)) : null);

				entity.setRoles((idRol != null) ? businessDelegatorView
						.getRoles(idRol) : null);

				businessDelegatorView.updateUsuariosInternos(entity);
				FacesUtils.addInfoMessage("Registro Modificado");
				action_clear();
			}else {
				throw new Exception(" ");
			}
			
			
		} catch (Exception e) {
			action_clear();
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}
    	return "";
    }
    
    public String action_clear() {
        entity = null;
        selectedUsuariosInternos = null;

        if (txtApellidos != null) {
            txtApellidos.setValue(null);
        }

        /*if (txtContrasena != null) {
            txtContrasena.setValue(null);
        }*/
        
        if (passContrasena != null) {
            passContrasena.setValue(null);
        }

        if (txtCorreoElectronico != null) {
            txtCorreoElectronico.setValue(null);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
        }

        if (txtLogin != null) {
            txtLogin.setValue(null);
        }

        if (txtNombres != null) {
            txtNombres.setValue(null);
        }

        if (txtNumeroIdentificacion != null) {
            txtNumeroIdentificacion.setValue(null);
        }

        if (txtIdAreaInvolucrada_AreasInvolucradas != null) {
            txtIdAreaInvolucrada_AreasInvolucradas.setValue(null);
        }

        if (txtIdRol_Roles != null) {
            txtIdRol_Roles.setValue(null);
        }
        
        if (btnSave != null) {
            btnSave.setDisabled(false);
        }
        
        if (btnModificar != null) {
        	btnModificar.setDisabled(false);
        }
        
        data = null;
        data = getData();
        areasInvolucradas = null;
        areasInvolucradas = getAreasInvolucradas();
        idRoles = null;
        idRoles = getIdRoles();
        
        return "";
    }

    public void listener_txtFechaCreacion() {
        Date inputDate = (Date) txtFechaCreacion.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long idUsuInterno = FacesUtils.checkLong(txtIdUsuInterno);
            entity = (idUsuInterno != null)
                ? businessDelegatorView.getUsuariosInternos(idUsuInterno) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtApellidos.setDisabled(false);
         //   txtContrasena.setDisabled(false);
            passContrasena.setDisabled(false);
            txtCorreoElectronico.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtLogin.setDisabled(false);
            txtNombres.setDisabled(false);
            txtNumeroIdentificacion.setDisabled(false);
            txtIdAreaInvolucrada_AreasInvolucradas.setDisabled(false);
            txtIdRol_Roles.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtIdUsuInterno.setDisabled(false);
            btnSave.setDisabled(false);
            btnModificar.setDisabled(false);
        } else {
            txtApellidos.setValue(entity.getApellidos());
            txtApellidos.setDisabled(false);
         //   txtContrasena.setValue(entity.getContrasena());
         //   txtContrasena.setDisabled(false);
            passContrasena.setValue(entity.getContrasena());
            passContrasena.setDisabled(false);
            txtCorreoElectronico.setValue(entity.getCorreoElectronico());
            txtCorreoElectronico.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtLogin.setValue(entity.getLogin());
            txtLogin.setDisabled(false);
            txtNombres.setValue(entity.getNombres());
            txtNombres.setDisabled(false);
            txtNumeroIdentificacion.setValue(entity.getNumeroIdentificacion());
            txtNumeroIdentificacion.setDisabled(false);
            txtIdAreaInvolucrada_AreasInvolucradas.setValue(entity.getAreasInvolucradas()
                                                                  .getNombreArea());
            txtIdAreaInvolucrada_AreasInvolucradas.setDisabled(false);
            txtIdRol_Roles.setValue(entity.getRoles().getNombreRol());
            txtIdRol_Roles.setDisabled(false);
            txtIdUsuInterno.setValue(entity.getIdUsuInterno());
            txtIdUsuInterno.setDisabled(true);
            btnSave.setDisabled(false);
            btnModificar.setDisabled(false);
            
            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
    	
        selectedUsuariosInternos = (UsuariosInternosDTO) (evt.getComponent()
                                                             .getAttributes()
                                                             .get("selectedUsuariosInternos"));
        txtApellidos.setValue(selectedUsuariosInternos.getApellidos());
        txtApellidos.setDisabled(false);
       /* txtContrasena.setValue(selectedUsuariosInternos.getContrasena());
        txtContrasena.setDisabled(false);*/
        passContrasena.setValue(selectedUsuariosInternos.getContrasena());
        passContrasena.setDisabled(false);
        txtCorreoElectronico.setValue(selectedUsuariosInternos.getCorreoElectronico());
        txtCorreoElectronico.setDisabled(false);
        txtEstadoRegistro.setValue(selectedUsuariosInternos.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtFechaCreacion.setValue(selectedUsuariosInternos.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtLogin.setValue(selectedUsuariosInternos.getLogin());
        txtLogin.setDisabled(false);
        txtNombres.setValue(selectedUsuariosInternos.getNombres());
        txtNombres.setDisabled(false);
        txtNumeroIdentificacion.setValue(selectedUsuariosInternos.getNumeroIdentificacion());
        txtNumeroIdentificacion.setDisabled(false);
        txtIdAreaInvolucrada_AreasInvolucradas.setValue(selectedUsuariosInternos.getIdAreaInvolucrada_AreasInvolucradas());
        txtIdAreaInvolucrada_AreasInvolucradas.setDisabled(false);
        txtIdRol_Roles.setValue(selectedUsuariosInternos.getIdRol_Roles());
        txtIdRol_Roles.setDisabled(false);
        txtIdUsuInterno.setValue(selectedUsuariosInternos.getIdUsuInterno());
        txtIdUsuInterno.setDisabled(true);
        btnSave.setDisabled(false);
        btnModificar.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedUsuariosInternos == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }
    
    public boolean revizarCampos(String nombres,String apellidos,String numeroIdentificacion,String login,String email,String contrasena,
    		String areaInvolucrada,String rol,String estado)throws Exception{
    	
    	if (nombres.equals("") || nombres.trim().equals("")) {
			throw new Exception(
			"Debe de ingresar un Nombre");
		}
    	
    	if (!Utilities.isOnlyLetters2(nombres)) {
			throw new Exception(
			"El Nombre ingresado solo debe de contener letras");
		}
    	
    	if (apellidos.equals("") || apellidos.trim().equals("")) {
			throw new Exception(
			"Debe de ingresar un Apellido");
		}
    	
    	if (!Utilities.isOnlyLetters2(apellidos)) {
			throw new Exception(
			"Los Apellidos ingresados solo deben de contener letras");
		}
    	
    	if (numeroIdentificacion.equals("") || numeroIdentificacion.trim().equals("")) {
			throw new Exception(
			"El Numero de Identificacion es de caracter obligatorio");
		}
    	
    	if (!Utilities.soloNumeros(numeroIdentificacion)) {
			throw new Exception(
			"El Numero de Identificacion debe ser totalmente numerico");
		}
    	
    	if (login.equals("") || login.trim().equals("")) {
			throw new Exception(
			"Debe de ingresar un Login deseado");
		}
    	
    	if (email.equals("") || email.trim().equals("")) {
			throw new Exception(
			"El Correo Electronico es de caracter obligatorio");
		}
    	
    	if (!Utilities.correElectronico(email)) {
			throw new Exception(
			"El Correo Electronico debe tener el siguiente formato \"xxx@xxx.xxx\"");
		}
    	
    	if (contrasena.equals("") || contrasena.trim().equals("")) {
			throw new Exception(
			"La Constraseña es de caracter obligatorio");
		}
    	
    	if (areaInvolucrada.equals("") || areaInvolucrada.trim().equals("")) {
			throw new Exception(
			"El Area Involucrada es de caracter obligatorio");
		}
    	
    	if (rol.equals("") || rol.trim().equals("")) {
			throw new Exception(
			"El Rol es de caracter obligatorio");
		}
    	
    	if (estado.equals("") || estado.trim().equals("")) {
			throw new Exception(
			"El Estado es de caracter obligatorio");
		}
		return true;
    }

	public String action_create() {
		try {			
			if (idAreaInvolucrada != null && idRol != null) {
				AreasInvolucradas area = businessDelegatorView.getAreasInvolucradas((idAreaInvolucrada));
				Roles roles = businessDelegatorView.getRoles(idRol);
				String nombres = txtNombres.getValue().toString().toUpperCase();
				String apellidos = txtApellidos.getValue().toString().toUpperCase();
				String numeroIdentificacion = txtNumeroIdentificacion.getValue().toString().toUpperCase();
				String login = txtLogin.getValue().toString().toUpperCase();
				String email = txtCorreoElectronico.getValue().toString().toUpperCase();
				String contrasena = getPassword().toUpperCase();
				String areaInvolucrada = area.getNombreArea().toUpperCase();
				String rol = roles.getNombreRol().toUpperCase();
				String estados = getEstadoRegistroSeleccionado().toUpperCase();

				if (!revizarCampos(nombres, apellidos, numeroIdentificacion,
						login, email, contrasena, areaInvolucrada, rol, estados)) {
					return "";
				}

				UsuariosInternos usuarioLogin = ObtenerCuentaUsuarios(login);
				UsuariosInternos usuarioNumeroIdentifiacion = ObtenerNumeroIdentificacionUsuarios(numeroIdentificacion);
				UsuariosInternos usuarioEmail = ObtenerEmailUsuarios(email);

				if (usuarioNumeroIdentifiacion == null && usuarioLogin == null && usuarioEmail == null) {
					
					entity = new UsuariosInternos();
					entity.setNumeroIdentificacion(FacesUtils
							.checkString(txtNumeroIdentificacion).toUpperCase());
					entity.setNombres(FacesUtils.checkString(txtNombres).toUpperCase());
					entity.setApellidos(FacesUtils.checkString(txtApellidos).toUpperCase());
					entity.setCorreoElectronico(FacesUtils
							.checkString(txtCorreoElectronico).toUpperCase());
					entity.setLogin(FacesUtils.checkString(txtLogin).toUpperCase());
					entity.setContrasena(contrasena);
					System.out.println(contrasena);
					entity.setEstadoRegistro(estadoRegistroSeleccionado.toUpperCase());
					entity.setFechaCreacion(new Date());
					entity.setAreasInvolucradas((idAreaInvolucrada != null) ? businessDelegatorView
							.getAreasInvolucradas((idAreaInvolucrada)) : null);

					entity.setRoles((idRol != null) ? businessDelegatorView
							.getRoles(idRol) : null);

					businessDelegatorView.saveUsuariosInternos(entity);
					FacesUtils
							.addInfoMessage("La información se guardó exitosamente");
				} else {

					throw new Exception(
							"El Numero de Identificacion y/o el Login y/o el Email ya existen. Por "
									+ "favor ingreselos nuevamente");
				}
			
			}else{
				throw new Exception("Debe seleccionar un Area Involucrada y/o Rol");
			}
			action_clear();
		} catch (Exception e) {
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}
	
	private UsuariosInternos ObtenerCuentaUsuarios(String login) throws Exception {
		UsuariosInternos entity = null;
		Object[] variables = { "login", true, login, "=" };
		List<UsuariosInternos> usuarioConsultadas = businessDelegatorView.findByCriteriaInUsuariosInternos(variables, null, null);
		if (Utilities.validationsList(usuarioConsultadas)) {
			entity = usuarioConsultadas.get(0);
		}
		return entity;
	}
	
	private UsuariosInternos ObtenerEmailUsuarios(String email) throws Exception {
		UsuariosInternos entity = null;
		Object[] variables = { "correoElectronico", true, email, "=" };
		List<UsuariosInternos> usuarioConsultadas = businessDelegatorView.findByCriteriaInUsuariosInternos(variables, null, null);
		if (Utilities.validationsList(usuarioConsultadas)) {
			entity = usuarioConsultadas.get(0);
		}
		return entity;
	}
	
	private UsuariosInternos ObtenerNumeroIdentificacionUsuarios(String numeroIdentificacion) throws Exception {
		UsuariosInternos entity = null;
		Object[] variables = { "numeroIdentificacion", true, numeroIdentificacion, "=" };
		List<UsuariosInternos> usuarioConsultadas = businessDelegatorView.findByCriteriaInUsuariosInternos(variables, null, null);
		if (Utilities.validationsList(usuarioConsultadas)) {
			entity = usuarioConsultadas.get(0);
		}
		return entity;
	}
	
	private UsuariosInternos obtenerIdUsuario(Long id) throws Exception {
		UsuariosInternos entity = null;
		Object[] variables = { "idUsuInterno", true, id, "=" };
		List<UsuariosInternos> usuarioConsultadas = businessDelegatorView.findByCriteriaInUsuariosInternos(variables, null, null);
		if (Utilities.validationsList(usuarioConsultadas)) {
			entity = usuarioConsultadas.get(0);
		}
		return entity;
	}

	public String action_modify() {
		try {

			String numeroIdentificacion = txtNumeroIdentificacion.getValue().toString();
			String nombres = txtNombres.getValue().toString();
			String apellidos = txtApellidos.getValue().toString();
			Long id = entity.getIdUsuInterno().longValue();
			String email = txtCorreoElectronico.getValue().toString();
			String login = txtLogin.getValue().toString();
			AreasInvolucradas area = businessDelegatorView.getAreasInvolucradas((idAreaInvolucrada));
			String areaInvolucrada = area.getNombreArea();
			Roles roles = businessDelegatorView.getRoles(idRol);
			String rol = roles.getNombreRol();
			String estado = getEstadoRegistroSeleccionado();

			UsuariosInternos usuarioPorId = obtenerIdUsuario(id);
			
			

			if (usuarioPorId != null) {

				String contrasena = usuarioPorId.getContrasena().toString();

				if (!revizarCampos(nombres, apellidos, numeroIdentificacion,
						login, email, contrasena, areaInvolucrada, rol, estado)) {
					return "";
				}

				UsuariosInternos usuario = ObtenerNumeroIdentificacionUsuarios(numeroIdentificacion);
				if (usuario != null) {
					Long idUsuarioPorCedula = usuario.getIdUsuInterno()
							.longValue();
					if (idUsuarioPorCedula != id) {
						throw new Exception(
								"El número de Identificacion ya existe, ingrese uno nuevo");
					}
				}

				UsuariosInternos usuarioPorLogin = ObtenerCuentaUsuarios(login);
				if (usuarioPorLogin != null) {
					Long idUsuarioPorLogin = usuarioPorLogin.getIdUsuInterno()
							.longValue();
					if (idUsuarioPorLogin != id) {
						throw new Exception(
								"El Login del usuario ya existe, ingrese uno nuevo");
					}
				}

				UsuariosInternos usuarioPorEmail = ObtenerEmailUsuarios(email);
				if (usuarioPorEmail != null) {
					Long idUsuarioPorEmail = usuarioPorEmail.getIdUsuInterno()
							.longValue();
					if (idUsuarioPorEmail != id) {
						throw new Exception(
								"El Email del usuario ya existe, ingrese uno nuevo");
					}
				}

				actualizar();
				action_clear();
			} else {
				throw new Exception(" ");
			}

		} catch (Exception e) {
			action_clear();
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
			
		}
		return "";
	}
	
	
    public void actualizar(){
    	try {
    		entity.setNumeroIdentificacion(FacesUtils
					.checkString(txtNumeroIdentificacion));
			entity.setNombres(FacesUtils.checkString(txtNombres));
			entity.setApellidos(FacesUtils.checkString(txtApellidos));
			entity.setCorreoElectronico(FacesUtils
					.checkString(txtCorreoElectronico));
			entity.setLogin(FacesUtils.checkString(txtLogin));
		//	entity.setContrasena(FacesUtils.checkString(txtContrasena));
			entity.setContrasena(FacesUtils.checkString(passContrasena));
			entity.setEstadoRegistro(estadoRegistroSeleccionado);
			entity.setFechaCreacion(new Date());

			entity.setAreasInvolucradas((idAreaInvolucrada != null) ? businessDelegatorView
					.getAreasInvolucradas((idAreaInvolucrada)) : null);

			entity.setRoles((idRol != null) ? businessDelegatorView
					.getRoles(idRol) : null);

			businessDelegatorView.updateUsuariosInternos(entity);
			FacesUtils.addInfoMessage("");

		} catch (Exception e) {
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}
    }
  
    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedUsuariosInternos = (UsuariosInternosDTO) (evt.getComponent()
                                                                 .getAttributes()
                                                                 .get("selectedUsuariosInternos"));

            Long idUsuInterno = new Long(selectedUsuariosInternos.getIdUsuInterno());
            entity = businessDelegatorView.getUsuariosInternos(idUsuInterno);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idUsuInterno = FacesUtils.checkLong(txtIdUsuInterno);
            entity = businessDelegatorView.getUsuariosInternos(idUsuInterno);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteUsuariosInternos(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();
        return "";
    }
    
    public String action_closeDialog2() {
        setShowDialog2(false);
        action_clear();
        return "";
    }

    public String actionDeleteDataTableEditable(ActionEvent evt) {
        try {
            selectedUsuariosInternos = (UsuariosInternosDTO) (evt.getComponent()
                                                                 .getAttributes()
                                                                 .get("selectedUsuariosInternos"));

            Long idUsuInterno = new Long(selectedUsuariosInternos.getIdUsuInterno());
            entity = businessDelegatorView.getUsuariosInternos(idUsuInterno);
            businessDelegatorView.deleteUsuariosInternos(entity);
            data.remove(selectedUsuariosInternos);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String apellidos, String contrasena,
        String correoElectronico, String estadoRegistro, Date fechaCreacion,
        Long idUsuInterno, String login, String nombres,
        String numeroIdentificacion, Long idAreaInvolucrada_AreasInvolucradas,
        Long idRol_Roles) throws Exception {
        try {
            entity.setApellidos(FacesUtils.checkString(apellidos));
            entity.setContrasena(FacesUtils.checkString(contrasena));
            entity.setCorreoElectronico(FacesUtils.checkString(
                    correoElectronico));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setLogin(FacesUtils.checkString(login));
            entity.setNombres(FacesUtils.checkString(nombres));
            entity.setNumeroIdentificacion(FacesUtils.checkString(
                    numeroIdentificacion));
            businessDelegatorView.updateUsuariosInternos(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("UsuariosInternosView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtApellidos() {
        return txtApellidos;
    }

    public void setTxtApellidos(InputText txtApellidos) {
        this.txtApellidos = txtApellidos;
    }

    public InputText getTxtContrasena() {
        return txtContrasena;
    }

    public void setTxtContrasena(InputText txtContrasena) {
        this.txtContrasena = txtContrasena;
    }

    public InputText getTxtCorreoElectronico() {
        return txtCorreoElectronico;
    }

    public void setTxtCorreoElectronico(InputText txtCorreoElectronico) {
        this.txtCorreoElectronico = txtCorreoElectronico;
    }

    public InputText getTxtEstadoRegistro() {
        return txtEstadoRegistro;
    }

    public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
        this.txtEstadoRegistro = txtEstadoRegistro;
    }

    public InputText getTxtLogin() {
        return txtLogin;
    }

    public void setTxtLogin(InputText txtLogin) {
        this.txtLogin = txtLogin;
    }

    public InputText getTxtNombres() {
        return txtNombres;
    }

    public void setTxtNombres(InputText txtNombres) {
        this.txtNombres = txtNombres;
    }

    public InputText getTxtNumeroIdentificacion() {
        return txtNumeroIdentificacion;
    }

    public void setTxtNumeroIdentificacion(InputText txtNumeroIdentificacion) {
        this.txtNumeroIdentificacion = txtNumeroIdentificacion;
    }

    public InputText getTxtIdAreaInvolucrada_AreasInvolucradas() {
        return txtIdAreaInvolucrada_AreasInvolucradas;
    }

    public void setTxtIdAreaInvolucrada_AreasInvolucradas(
        InputText txtIdAreaInvolucrada_AreasInvolucradas) {
        this.txtIdAreaInvolucrada_AreasInvolucradas = txtIdAreaInvolucrada_AreasInvolucradas;
    }

    public InputText getTxtIdRol_Roles() {
        return txtIdRol_Roles;
    }

    public void setTxtIdRol_Roles(InputText txtIdRol_Roles) {
        this.txtIdRol_Roles = txtIdRol_Roles;
    }

    public Calendar getTxtFechaCreacion() {
        return txtFechaCreacion;
    }

    public void setTxtFechaCreacion(Calendar txtFechaCreacion) {
        this.txtFechaCreacion = txtFechaCreacion;
    }

    public InputText getTxtIdUsuInterno() {
        return txtIdUsuInterno;
    }

    public void setTxtIdUsuInterno(InputText txtIdUsuInterno) {
        this.txtIdUsuInterno = txtIdUsuInterno;
    }

    public List<UsuariosInternosDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataUsuariosInternos();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<UsuariosInternosDTO> usuariosInternosDTO) {
        this.data = usuariosInternosDTO;
    }

    public UsuariosInternosDTO getSelectedUsuariosInternos() {
        return selectedUsuariosInternos;
    }

    public void setSelectedUsuariosInternos(
        UsuariosInternosDTO usuariosInternos) {
        this.selectedUsuariosInternos = usuariosInternos;
    }

    public CommandButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(CommandButton btnSave) {
        this.btnSave = btnSave;
    }

    public CommandButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(CommandButton btnModify) {
        this.btnModify = btnModify;
    }

    public CommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(CommandButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public CommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(CommandButton btnClear) {
        this.btnClear = btnClear;
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }
    
    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
       

	public boolean isShowDialog2() {
		return showDialog2;
	}

	public void setShowDialog2(boolean showDialog2) {
		this.showDialog2 = showDialog2;
	}

	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	public List<Roles> getRoles() {
        try {
            if (roles == null) {
                roles = businessDelegatorView.getRoles();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<AreasInvolucradas> getAreas() {
        try {
            if (areas == null) {
                areas = businessDelegatorView.getAreasInvolucradas();                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return areas;
	}

	public void setAreas(List<AreasInvolucradas> areas) {
		this.areas = areas;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEstadoRegistroSeleccionado() {
		return estadoRegistroSeleccionado;
	}

	public void setEstadoRegistroSeleccionado(String estadoRegistroSeleccionado) {
		this.estadoRegistroSeleccionado = estadoRegistroSeleccionado;
	}

	public Long getIdAreaInvolucrada() {
		return idAreaInvolucrada;
	}

	public void setIdAreaInvolucrada(Long idAreaInvolucrada) {
		this.idAreaInvolucrada = idAreaInvolucrada;
	}
	
	public List<SelectItem> getAreasInvolucradas() {
		try {
	       	areasInvolucradas = new ArrayList<SelectItem>();
			List<AreasInvolucradas> areas = businessDelegatorView.getAreasInvolucradas();
	       	for (AreasInvolucradas area : areas) {
				areasInvolucradas.add(new SelectItem(area.getIdAreaInvolucrada(), area.getNombreArea()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return areasInvolucradas;
	}

	public void setAreasInvolucradas(List<SelectItem> areasInvolucradas) {
		this.areasInvolucradas = areasInvolucradas;
	}
	
	
	
	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public List<SelectItem> getIdRoles() {
		try {
	       	idRoles = new ArrayList<SelectItem>();
			List<Roles> roles = businessDelegatorView.getRoles();
	       	for (Roles rol : roles) {
				idRoles.add(new SelectItem(rol.getIdRol(), rol.getNombreRol()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return idRoles;
	}

	public void setIdRoles(List<SelectItem> idRoles) {
		this.idRoles = idRoles;
	}

	public UsuariosInternos getUsuInternos() {
		return usuInternos;
	}

	public void setUsuInternos(UsuariosInternos usuInternos) {
		this.usuInternos = usuInternos;
	}

	public Long getIdUsuInterno() {
		return idUsuInterno;
	}

	public void setIdUsuInterno(Long idUsuInterno) {
		this.idUsuInterno = idUsuInterno;	
	}

	public Password getPassContrasena() {
		return passContrasena;
	}

	public void setPassContrasena(Password passContrasena) {
		this.passContrasena = passContrasena;
	}

//	public Password getPassContrasena2() {
//		return passContrasena2;
//	}
//
//	public void setPassContrasena2(Password passContrasena2) {
//		this.passContrasena2 = passContrasena2;
//	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public InputText getTxtNombres2() {
		return txtNombres2;
	}

	public void setTxtNombres2(InputText txtNombres2) {
		this.txtNombres2 = txtNombres2;
	}

	public InputText getTxtApellidos2() {
		return txtApellidos2;
	}

	public void setTxtApellidos2(InputText txtApellidos2) {
		this.txtApellidos2 = txtApellidos2;
	}

	public InputText getTxtCorreoElectronico2() {
		return txtCorreoElectronico2;
	}

	public void setTxtCorreoElectronico2(InputText txtCorreoElectronico2) {
		this.txtCorreoElectronico2 = txtCorreoElectronico2;
	}

	public InputText getTxtNumeroIdentificacion2() {
		return txtNumeroIdentificacion2;
	}

	public void setTxtNumeroIdentificacion2(InputText txtNumeroIdentificacion2) {
		this.txtNumeroIdentificacion2 = txtNumeroIdentificacion2;
	}

	public InputText getTxtLogin2() {
		return txtLogin2;
	}

	public void setTxtLogin2(InputText txtLogin2) {
		this.txtLogin2 = txtLogin2;
	}

	public CommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}	
}
