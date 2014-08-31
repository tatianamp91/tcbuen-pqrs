package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.ParametrosPqrDTO;
import com.tcbuen.pqrs.presentation.businessDelegate.*;
import com.tcbuen.pqrs.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.RowEditEvent;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 * 
 */
@ManagedBean
@ViewScoped
public class ParametrosPqrView implements Serializable {
	private static final long serialVersionUID = 1L;
	private InputText txtDescripcionParam;
	private InputText txtEstadoRegistro;
	private InputText txtUsuarioCreador;
	private InputText txtUsuarioUltimaModificacion;
	private InputText txtValorParam;
	private InputText txtIdParam;
	private Calendar txtFechaCreacion;
	private Calendar txtFechaUltimaModificacion;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<ParametrosPqrDTO> data;
	private ParametrosPqrDTO selectedParametrosPqr;
	private ParametrosPqr entity;
	private boolean showDialog;
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;
	private String estadoRegistroSeleccionado;

	public ParametrosPqrView() {
		super();
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			ParametrosPqrDTO parametrosPqrDTO = (ParametrosPqrDTO) e
					.getObject();

			if (txtDescripcionParam == null) {
				txtDescripcionParam = new InputText();
			}

			txtDescripcionParam
					.setValue(parametrosPqrDTO.getDescripcionParam());

			if (txtEstadoRegistro == null) {
				txtEstadoRegistro = new InputText();
			}

			txtEstadoRegistro.setValue(parametrosPqrDTO.getEstadoRegistro());

			if (txtUsuarioCreador == null) {
				txtUsuarioCreador = new InputText();
			}

			txtUsuarioCreador.setValue(parametrosPqrDTO.getUsuarioCreador());

			if (txtUsuarioUltimaModificacion == null) {
				txtUsuarioUltimaModificacion = new InputText();
			}

			txtUsuarioUltimaModificacion.setValue(parametrosPqrDTO
					.getUsuarioUltimaModificacion());

			if (txtValorParam == null) {
				txtValorParam = new InputText();
			}

			txtValorParam.setValue(parametrosPqrDTO.getValorParam());

			if (txtIdParam == null) {
				txtIdParam = new InputText();
			}

			txtIdParam.setValue(parametrosPqrDTO.getIdParam());

			if (txtFechaCreacion == null) {
				txtFechaCreacion = new Calendar();
			}

			txtFechaCreacion.setValue(parametrosPqrDTO.getFechaCreacion());

			if (txtFechaUltimaModificacion == null) {
				txtFechaUltimaModificacion = new Calendar();
			}

			txtFechaUltimaModificacion.setValue(parametrosPqrDTO
					.getFechaUltimaModificacion());

			Long idParam = FacesUtils.checkLong(txtIdParam);
			entity = businessDelegatorView.getParametrosPqr(idParam);

			action_modify();
		} catch (Exception ex) {
		}
	}

	public String action_new() {
		action_clear();
		selectedParametrosPqr = null;
		setShowDialog(true);

		return "";
	}

	public String action_clear() {
		entity = null;
		selectedParametrosPqr = null;

		if (txtDescripcionParam != null) {
			txtDescripcionParam.setValue(null);
		}

		if (txtEstadoRegistro != null) {
			txtEstadoRegistro.setValue(null);
		}
		
		if (txtValorParam != null) {
			txtValorParam.setValue(null);
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
		FacesContext.getCurrentInstance().addMessage(
				"",
				new FacesMessage("Selected Date "
						+ dateFormat.format(inputDate)));
	}

	public void listener_txtFechaUltimaModificacion() {
		Date inputDate = (Date) txtFechaUltimaModificacion.getValue();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		FacesContext.getCurrentInstance().addMessage(
				"",
				new FacesMessage("Selected Date "
						+ dateFormat.format(inputDate)));
	}

	public void listener_txtId() {
		try {
			Long idParam = FacesUtils.checkLong(txtIdParam);
			entity = (idParam != null) ? businessDelegatorView
					.getParametrosPqr(idParam) : null;
		} catch (Exception e) {
			entity = null;
		}

		if (entity == null) {
			txtDescripcionParam.setDisabled(false);
			txtEstadoRegistro.setDisabled(false);
			txtUsuarioCreador.setDisabled(false);
			txtUsuarioUltimaModificacion.setDisabled(false);
			txtValorParam.setDisabled(false);
			txtFechaCreacion.setDisabled(false);
			txtFechaUltimaModificacion.setDisabled(false);
			txtIdParam.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtDescripcionParam.setValue(entity.getDescripcionParam());
			txtDescripcionParam.setDisabled(false);
			txtEstadoRegistro.setValue(entity.getEstadoRegistro());
			txtEstadoRegistro.setDisabled(false);
			txtFechaCreacion.setValue(entity.getFechaCreacion());
			txtFechaCreacion.setDisabled(false);
			txtFechaUltimaModificacion.setValue(entity
					.getFechaUltimaModificacion());
			txtFechaUltimaModificacion.setDisabled(false);
			txtUsuarioCreador.setValue(entity.getUsuarioCreador());
			txtUsuarioCreador.setDisabled(false);
			txtUsuarioUltimaModificacion.setValue(entity
					.getUsuarioUltimaModificacion());
			txtUsuarioUltimaModificacion.setDisabled(false);
			txtValorParam.setValue(entity.getValorParam());
			txtValorParam.setDisabled(false);
			txtIdParam.setValue(entity.getIdParam());
			txtIdParam.setDisabled(true);
			btnSave.setDisabled(false);

			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedParametrosPqr = (ParametrosPqrDTO) (evt.getComponent()
				.getAttributes().get("selectedParametrosPqr"));
		txtDescripcionParam.setValue(selectedParametrosPqr
				.getDescripcionParam());
		txtDescripcionParam.setDisabled(false);
		txtEstadoRegistro.setValue(selectedParametrosPqr.getEstadoRegistro());
		txtEstadoRegistro.setDisabled(false);
		txtFechaCreacion.setValue(selectedParametrosPqr.getFechaCreacion());
		txtFechaCreacion.setDisabled(false);
		txtFechaUltimaModificacion.setValue(selectedParametrosPqr
				.getFechaUltimaModificacion());
		txtFechaUltimaModificacion.setDisabled(false);
		txtUsuarioCreador.setValue(selectedParametrosPqr.getUsuarioCreador());
		txtUsuarioCreador.setDisabled(false);
		txtUsuarioUltimaModificacion.setValue(selectedParametrosPqr
				.getUsuarioUltimaModificacion());
		txtUsuarioUltimaModificacion.setDisabled(false);
		txtValorParam.setValue(selectedParametrosPqr.getValorParam());
		txtValorParam.setDisabled(false);
		txtIdParam.setValue(selectedParametrosPqr.getIdParam());
		txtIdParam.setDisabled(true);
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedParametrosPqr == null) && (entity == null)) {
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

	public String action_create() {
		try {
			entity = new ParametrosPqr();

			// Long idParam = FacesUtils.checkLong(txtIdParam);
			//entity.setIdParam(idParam);
			entity.setDescripcionParam(FacesUtils.checkString(txtDescripcionParam));
			String estado = (estadoRegistroSeleccionado.equals("Activo"))?"A":"I";
			entity.setEstadoRegistro(estado);
			//Falta agregar usuarios sesion
			entity.setUsuarioCreador("Admin");
			entity.setFechaCreacion(new Date());
			entity.setUsuarioUltimaModificacion(null);
			entity.setFechaUltimaModificacion(null);
			entity.setValorParam(FacesUtils.checkString(txtValorParam));
			
			businessDelegatorView.saveParametrosPqr(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
			action_clear();
		} catch (Exception e) {
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modify() {
		try {
			if (entity == null) {
				Long idParam = new Long(selectedParametrosPqr.getIdParam());
				entity = businessDelegatorView.getParametrosPqr(idParam);
			}

			entity.setDescripcionParam(FacesUtils.checkString(txtDescripcionParam));
			String estado = (estadoRegistroSeleccionado.equals("Activo"))?"A":"I";
			entity.setEstadoRegistro(estado);
			entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
			entity.setFechaUltimaModificacion(new Date());
			entity.setUsuarioCreador(FacesUtils.checkString(txtUsuarioCreador));
			entity.setUsuarioUltimaModificacion("Admin-1");
			entity.setValorParam(FacesUtils.checkString(txtValorParam));
			businessDelegatorView.updateParametrosPqr(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
			action_clear();
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_datatable(ActionEvent evt) {
		try {
			selectedParametrosPqr = (ParametrosPqrDTO) (evt.getComponent()
					.getAttributes().get("selectedParametrosPqr"));

			Long idParam = new Long(selectedParametrosPqr.getIdParam());
			entity = businessDelegatorView.getParametrosPqr(idParam);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Long idParam = FacesUtils.checkLong(txtIdParam);
			entity = businessDelegatorView.getParametrosPqr(idParam);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deleteParametrosPqr(entity);
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
			selectedParametrosPqr = (ParametrosPqrDTO) (evt.getComponent()
					.getAttributes().get("selectedParametrosPqr"));

			Long idParam = new Long(selectedParametrosPqr.getIdParam());
			entity = businessDelegatorView.getParametrosPqr(idParam);
			businessDelegatorView.deleteParametrosPqr(entity);
			data.remove(selectedParametrosPqr);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(String descripcionParam,
			String estadoRegistro, Date fechaCreacion,
			Date fechaUltimaModificacion, Long idParam, String usuarioCreador,
			String usuarioUltimaModificacion, String valorParam)
			throws Exception {
		try {
			entity.setDescripcionParam(FacesUtils.checkString(descripcionParam));
			entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
			entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
			entity.setFechaUltimaModificacion(FacesUtils
					.checkDate(fechaUltimaModificacion));
			entity.setUsuarioCreador(FacesUtils.checkString(usuarioCreador));
			entity.setUsuarioUltimaModificacion(FacesUtils
					.checkString(usuarioUltimaModificacion));
			entity.setValorParam(FacesUtils.checkString(valorParam));
			businessDelegatorView.updateParametrosPqr(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("ParametrosPqrView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public InputText getTxtDescripcionParam() {
		return txtDescripcionParam;
	}

	public void setTxtDescripcionParam(InputText txtDescripcionParam) {
		this.txtDescripcionParam = txtDescripcionParam;
	}

	public InputText getTxtEstadoRegistro() {
		return txtEstadoRegistro;
	}

	public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
		this.txtEstadoRegistro = txtEstadoRegistro;
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

	public InputText getTxtValorParam() {
		return txtValorParam;
	}

	public void setTxtValorParam(InputText txtValorParam) {
		this.txtValorParam = txtValorParam;
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

	public InputText getTxtIdParam() {
		return txtIdParam;
	}

	public void setTxtIdParam(InputText txtIdParam) {
		this.txtIdParam = txtIdParam;
	}

	public List<ParametrosPqrDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataParametrosPqr();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public void setData(List<ParametrosPqrDTO> parametrosPqrDTO) {
		this.data = parametrosPqrDTO;
	}

	public ParametrosPqrDTO getSelectedParametrosPqr() {
		return selectedParametrosPqr;
	}

	public void setSelectedParametrosPqr(ParametrosPqrDTO parametrosPqr) {
		this.selectedParametrosPqr = parametrosPqr;
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
