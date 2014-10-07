package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.RolesDTO;
import com.tcbuen.pqrs.presentation.businessDelegate.*;
import com.tcbuen.pqrs.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.RowEditEvent;

import java.io.Serializable;
import java.sql.*;
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
public class RolesView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtEstadoRegistro;
    private InputText txtNombreRol;
	private String estadoRegistroSeleccionado;
    private InputText txtUsuarioCreador;
    private InputText txtUsuarioUltimaModificacion;
    private InputText txtIdRol;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaUltimaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<RolesDTO> data;
    private RolesDTO selectedRoles;
    private Roles entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public RolesView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            RolesDTO rolesDTO = (RolesDTO) e.getObject();

            if (txtEstadoRegistro == null) {
            	txtEstadoRegistro = new InputText();
            }
            txtEstadoRegistro.setValue(rolesDTO.getEstadoRegistro());

            if (txtNombreRol == null) {
                txtNombreRol = new InputText();
            }
            txtNombreRol.setValue(rolesDTO.getNombreRol());

            if (txtUsuarioCreador == null) {
                txtUsuarioCreador = new InputText();
            }
            txtUsuarioCreador.setValue(rolesDTO.getUsuarioCreador());

            if (txtUsuarioUltimaModificacion == null) {
                txtUsuarioUltimaModificacion = new InputText();
            }
            txtUsuarioUltimaModificacion.setValue(rolesDTO.getUsuarioUltimaModificacion());

            if (txtIdRol == null) {
                txtIdRol = new InputText();
            }
            txtIdRol.setValue(rolesDTO.getIdRol());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }
            txtFechaCreacion.setValue(rolesDTO.getFechaCreacion());

            if (txtFechaUltimaModificacion == null) {
                txtFechaUltimaModificacion = new Calendar();
            }
            txtFechaUltimaModificacion.setValue(rolesDTO.getFechaUltimaModificacion());

            Long idRol = FacesUtils.checkLong(txtIdRol);
            entity = businessDelegatorView.getRoles(idRol);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedRoles = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedRoles = null;
        
        if(estadoRegistroSeleccionado != null){
        	estadoRegistroSeleccionado = null;
        }

        if (txtNombreRol != null) {
            txtNombreRol.setValue(null);
        }

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }
        data = null;
        data = getData();
        return "";
    }

    public void listener_txtFechaCreacion() {
        Date inputDate = (Date) txtFechaCreacion.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechaUltimaModificacion() {
        Date inputDate = (Date) txtFechaUltimaModificacion.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long idRol = FacesUtils.checkLong(txtIdRol);
            entity = (idRol != null) ? businessDelegatorView.getRoles(idRol)
                                     : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstadoRegistro.setDisabled(false);
            txtNombreRol.setDisabled(false);
            txtUsuarioCreador.setDisabled(false);
            txtUsuarioUltimaModificacion.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaUltimaModificacion.setDisabled(false);
            txtIdRol.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaUltimaModificacion.setValue(entity.getFechaUltimaModificacion());
            txtFechaUltimaModificacion.setDisabled(false);
            txtNombreRol.setValue(entity.getNombreRol());
            txtNombreRol.setDisabled(false);
            txtUsuarioCreador.setValue(entity.getUsuarioCreador());
            txtUsuarioCreador.setDisabled(false);
            txtUsuarioUltimaModificacion.setValue(entity.getUsuarioUltimaModificacion());
            txtUsuarioUltimaModificacion.setDisabled(false);
            txtIdRol.setValue(entity.getIdRol());
            txtIdRol.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedRoles = (RolesDTO) (evt.getComponent().getAttributes()
                                       .get("selectedRoles"));
        txtEstadoRegistro.setValue(selectedRoles.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtFechaCreacion.setValue(selectedRoles.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaUltimaModificacion.setValue(selectedRoles.getFechaUltimaModificacion());
        txtFechaUltimaModificacion.setDisabled(false);
        txtNombreRol.setValue(selectedRoles.getNombreRol());
        txtNombreRol.setDisabled(false);
        txtUsuarioCreador.setValue(selectedRoles.getUsuarioCreador());
        txtUsuarioCreador.setDisabled(false);
        txtUsuarioUltimaModificacion.setValue(selectedRoles.getUsuarioUltimaModificacion());
        txtUsuarioUltimaModificacion.setDisabled(false);
        txtIdRol.setValue(selectedRoles.getIdRol());
        txtIdRol.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedRoles == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }
            data = null;
            data = getData();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }
        return "";
    }

	public String action_create() {
		try {

			String nombreRol = txtNombreRol.getValue().toString().toLowerCase();
			Roles nombre = ObtenerRoles(nombreRol);

			if (nombre == null) {

				if (!revizarCampos(nombreRol)) {
					return "";

				}
				entity = new Roles();

				// Long idRol = FacesUtils.checkLong(txtIdRol);
				// entity.setIdRol(idRol);
				entity.setNombreRol(FacesUtils.checkString(txtNombreRol).toLowerCase());
				entity.setEstadoRegistro(estadoRegistroSeleccionado);
				// Falta agregar usuario de sesion
				entity.setUsuarioCreador("Admin");
				entity.setFechaCreacion(new Date());
				entity.setFechaUltimaModificacion(null);
				entity.setUsuarioUltimaModificacion(null);

				businessDelegatorView.saveRoles(entity);
				FacesUtils.addInfoMessage("El rol se guardo exitosamente");

				action_clear();

			} else {
				throw new Exception("Ya existe Rol");
			}
		} catch (Exception e) {
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	private Roles ObtenerRoles(String nombreRol) throws Exception {
		Roles entity = null;
		Object[] variables = { "nombreRol", true, nombreRol, "=" };
		List<Roles> roles = businessDelegatorView.findByCriteriaInRoles(
				variables, null, null);

		if (Utilities.validationsList(roles)) {
			entity = roles.get(0);
		}
		return entity;
	}

	public boolean revizarCampos(String nombreRol) throws Exception {

/*		if (nombreRol.equals("") || nombreRol.trim().equals("")) {
			throw new Exception("Debe de ingresar una descripción");
		}

		if (!Utilities.isOnlyLetters2(nombreRol)) {
			throw new Exception("El Rol ingresado solo debe de contener letras");
		}*/
		return true;

	}
	
	public void actualizar(){
		
		try {
			entity.setNombreRol(FacesUtils.checkString(txtNombreRol).toLowerCase());
			entity.setEstadoRegistro(estadoRegistroSeleccionado);
			entity.setUsuarioCreador(FacesUtils
					.checkString(txtUsuarioCreador));
			entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
			// Falta agregar usuario de sesion
			entity.setUsuarioUltimaModificacion("Facturación");
			entity.setFechaUltimaModificacion(new Date());

			businessDelegatorView.updateRoles(entity);
			FacesUtils
					.addInfoMessage("El rol se modifico exitosamente");

		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}
		
			}

	public String action_modify() {
		try {
			String nombreRol = txtNombreRol.getValue().toString().toLowerCase();
			Roles nombre = ObtenerRoles(nombreRol);
			
			if (nombre == null) {
				if (!revizarCampos(nombreRol)) {
					return "";
				}
				actualizar();
				action_clear();
			}else{
				String nombreTemp = nombre.getNombreRol().toLowerCase();
				Long idTemp = nombre.getIdRol();
				
				if ((nombreTemp.equals(nombreRol) && idTemp == entity.getIdRol().longValue())){
					if (!revizarCampos(nombreRol)) {
						return "";
					}
					actualizar();
					action_clear();
				}else{
					throw new Exception("El Rol no ha sido modificado, ya existe el rol");
				}
			}
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}
    
    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedRoles = (RolesDTO) (evt.getComponent().getAttributes().get("selectedRoles"));

            Long idRol = new Long(selectedRoles.getIdRol());
            entity = businessDelegatorView.getRoles(idRol);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idRol = FacesUtils.checkLong(txtIdRol);
            entity = businessDelegatorView.getRoles(idRol);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteRoles(entity);
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

    public String actionDeleteDataTableEditable(ActionEvent evt) {
        try {
            selectedRoles = (RolesDTO) (evt.getComponent().getAttributes().get("selectedRoles"));

            Long idRol = new Long(selectedRoles.getIdRol());
            entity = businessDelegatorView.getRoles(idRol);
            businessDelegatorView.deleteRoles(entity);
            data.remove(selectedRoles);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estadoRegistro,
        Date fechaCreacion, Date fechaUltimaModificacion, Long idRol,
        String nombreRol, String usuarioCreador,
        String usuarioUltimaModificacion) throws Exception {
        try {
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaUltimaModificacion(FacesUtils.checkDate(
                    fechaUltimaModificacion));
            entity.setNombreRol(FacesUtils.checkString(nombreRol));
            entity.setUsuarioCreador(FacesUtils.checkString(usuarioCreador));
            entity.setUsuarioUltimaModificacion(FacesUtils.checkString(
                    usuarioUltimaModificacion));
            businessDelegatorView.updateRoles(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("RolesView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEstadoRegistro() {
        return txtEstadoRegistro;
    }

    public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
        this.txtEstadoRegistro = txtEstadoRegistro;
    }

    public InputText getTxtNombreRol() {
        return txtNombreRol;
    }

    public void setTxtNombreRol(InputText txtNombreRol) {
        this.txtNombreRol = txtNombreRol;
    }

    public InputText getTxtUsuarioCreador() {
        return txtUsuarioCreador;
    }

    public void setTxtUsuarioCreador(InputText txtUsuarioCreador) {
        this.txtUsuarioCreador = txtUsuarioCreador;
    }

    public InputText getTxtUsuarioUltimaModificacion() {
        return txtUsuarioUltimaModificacion;
    }

    public void setTxtUsuarioUltimaModificacion(
        InputText txtUsuarioUltimaModificacion) {
        this.txtUsuarioUltimaModificacion = txtUsuarioUltimaModificacion;
    }

    public Calendar getTxtFechaCreacion() {
        return txtFechaCreacion;
    }

    public void setTxtFechaCreacion(Calendar txtFechaCreacion) {
        this.txtFechaCreacion = txtFechaCreacion;
    }

    public Calendar getTxtFechaUltimaModificacion() {
        return txtFechaUltimaModificacion;
    }

    public void setTxtFechaUltimaModificacion(
        Calendar txtFechaUltimaModificacion) {
        this.txtFechaUltimaModificacion = txtFechaUltimaModificacion;
    }

    public InputText getTxtIdRol() {
        return txtIdRol;
    }

    public void setTxtIdRol(InputText txtIdRol) {
        this.txtIdRol = txtIdRol;
    }

    public List<RolesDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataRoles();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<RolesDTO> rolesDTO) {
        this.data = rolesDTO;
    }

    public RolesDTO getSelectedRoles() {
        return selectedRoles;
    }

    public void setSelectedRoles(RolesDTO roles) {
        this.selectedRoles = roles;
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

	public String getEstadoRegistroSeleccionado() {
		return estadoRegistroSeleccionado;
	}

	public void setEstadoRegistroSeleccionado(String estadoRegistroSeleccionado) {
		this.estadoRegistroSeleccionado = estadoRegistroSeleccionado;
	}
}