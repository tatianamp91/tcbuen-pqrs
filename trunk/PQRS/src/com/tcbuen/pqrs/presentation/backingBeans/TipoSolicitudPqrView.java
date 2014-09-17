package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.TipoSolicitudPqrDTO;
import com.tcbuen.pqrs.presentation.businessDelegate.*;
import com.tcbuen.pqrs.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
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
public class TipoSolicitudPqrView implements Serializable {
	private static final long serialVersionUID = 1L;
	private InputText txtDescTpSol;
	private String estadoRegistroSeleccionado;
	private InputText txtEstadoRegistro;
	private InputText txtUsuarioCreador;
	private InputText txtUsuarioUltimaModificacion;
	private InputText txtIdTpSolPqr;
	private Calendar txtFechaCreacion;
	private Calendar txtFechaUltimaModificacion;
	private CommandButton btnSave;
	private CommandButton btnModify;
	private CommandButton btnDelete;
	private CommandButton btnClear;
	private List<TipoSolicitudPqrDTO> data;
	private TipoSolicitudPqrDTO selectedTipoSolicitudPqr;
	private TipoSolicitudPqr entity;
	private boolean showDialog;
	private DualListModel<MotivoReclamacion> motivosReclamacion;
	private List<MotivoReclamacion> motivosReclamacionSource;
	private List<MotivoReclamacion> motivosReclamacionTarget;
	private List<MotivoReclamacion> motivosReclamacionTargetCopia;
	private DualListModel<MotivoSolicitud> motivosSolicitud;
	private List<MotivoSolicitud> motivosSolicitudSource;
	private List<MotivoSolicitud> motivosSolicitudTarget;
	private List<MotivoSolicitud> motivosSolicitudTargetCopia;
	private DualListModel<AnexosPqr> anexosPqr;
	private List<AnexosPqr> anexosPqrSource;
	private List<AnexosPqr> anexosPqrTarget;
	private List<AnexosPqr> anexosPqrTargetCopia;
	private TipoSolicitudPqr tipoSolicitudPqr;
	private Long idTpSolPqr;
	private Boolean boton;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public TipoSolicitudPqrView() {
		super();
	}

	@PostConstruct
	public void init() {
		try {
			consultarElementosNuevo();
			btnModify.setDisabled(false);
			motivosReclamacionTargetCopia = null;
			motivosSolicitudTargetCopia = null;
			anexosPqrTargetCopia = null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void rowEventListener(RowEditEvent e) {
		try {
			TipoSolicitudPqrDTO tipoSolicitudPqrDTO = (TipoSolicitudPqrDTO) e
					.getObject();

			if (txtDescTpSol == null) {
				txtDescTpSol = new InputText();
			}
			txtDescTpSol.setValue(tipoSolicitudPqrDTO.getDescTpSol());

			if (txtEstadoRegistro == null) {
				txtEstadoRegistro = new InputText();
			}
			txtEstadoRegistro.setValue(tipoSolicitudPqrDTO.getEstadoRegistro());

			if (txtUsuarioCreador == null) {
				txtUsuarioCreador = new InputText();
			}
			txtUsuarioCreador.setValue(tipoSolicitudPqrDTO.getUsuarioCreador());

			if (txtUsuarioUltimaModificacion == null) {
				txtUsuarioUltimaModificacion = new InputText();
			}
			txtUsuarioUltimaModificacion.setValue(tipoSolicitudPqrDTO
					.getUsuarioUltimaModificacion());

			if (txtIdTpSolPqr == null) {
				txtIdTpSolPqr = new InputText();
			}
			txtIdTpSolPqr.setValue(tipoSolicitudPqrDTO.getIdTpSolPqr());

			if (txtFechaCreacion == null) {
				txtFechaCreacion = new Calendar();
			}
			txtFechaCreacion.setValue(tipoSolicitudPqrDTO.getFechaCreacion());

			if (txtFechaUltimaModificacion == null) {
				txtFechaUltimaModificacion = new Calendar();
			}
			txtFechaUltimaModificacion.setValue(tipoSolicitudPqrDTO
					.getFechaUltimaModificacion());

			Long idTpSolPqr = FacesUtils.checkLong(txtIdTpSolPqr);
			entity = businessDelegatorView.getTipoSolicitudPqr(idTpSolPqr);

			action_modify();
		} catch (Exception ex) {
			FacesUtils.addErrorMessage(ex.getMessage());
		}
	}

	public String consultarElementosNuevo() {
		try {
			motivosReclamacionSource = new ArrayList<MotivoReclamacion>();
			motivosReclamacionTarget = new ArrayList<MotivoReclamacion>();
			List<MotivoReclamacion> mrs = businessDelegatorView.getMotivoReclamacion();
			for (MotivoReclamacion motRecl : mrs) {
				if (motRecl.getEstadoRegistro().equals("A")) {
					motivosReclamacionSource.add(motRecl);
				}
			}
			motivosReclamacionTargetCopia = null;
			motivosReclamacion = new DualListModel<MotivoReclamacion>(motivosReclamacionSource, motivosReclamacionTarget);

			motivosSolicitudSource = new ArrayList<MotivoSolicitud>();
			motivosSolicitudTarget = new ArrayList<MotivoSolicitud>();
			List<MotivoSolicitud> mss = businessDelegatorView.getMotivoSolicitud();
			for (MotivoSolicitud motSol : mss) {
				if (motSol.getEstadoRegistro().equals("A")) {
					motivosSolicitudSource.add(motSol);
				}
			}
			motivosSolicitudTargetCopia = null;
			motivosSolicitud = new DualListModel<MotivoSolicitud>(motivosSolicitudSource, motivosSolicitudTarget);

			anexosPqrSource = new ArrayList<AnexosPqr>();
			anexosPqrTarget = new ArrayList<AnexosPqr>();
			List<AnexosPqr> aps = businessDelegatorView.getAnexosPqr();
			for (AnexosPqr anexo : aps) {
				if (anexo.getEstadoRegistro().equals("A")) {
					anexosPqrSource.add(anexo);
				}
			}
			anexosPqrTargetCopia = null;
			anexosPqr = new DualListModel<AnexosPqr>(anexosPqrSource, anexosPqrTarget);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public String consultarElementosModificar(TipoSolicitudPqr tipoSolicitudPqr) {
		try {
			motivosReclamacionSource = new ArrayList<MotivoReclamacion>();
			motivosReclamacionTarget = new ArrayList<MotivoReclamacion>();
			motivosReclamacionSource = businessDelegatorView.consultarMotReclNoTipoPqr(tipoSolicitudPqr);
			motivosReclamacionTarget = businessDelegatorView.consultarMotReclXTipoPqr(tipoSolicitudPqr);
			motivosReclamacionTargetCopia = motivosReclamacionTarget;
			motivosReclamacion = new DualListModel<MotivoReclamacion>(motivosReclamacionSource, motivosReclamacionTarget);

			motivosSolicitudSource = new ArrayList<MotivoSolicitud>();
			motivosSolicitudTarget = new ArrayList<MotivoSolicitud>();
			motivosSolicitudSource = businessDelegatorView.consultarMotSolNoTipoPqr(tipoSolicitudPqr);
			motivosSolicitudTarget = businessDelegatorView.consultarMotSolXTipoPqr(tipoSolicitudPqr);
			motivosSolicitudTargetCopia = motivosSolicitudTarget;
			motivosSolicitud = new DualListModel<MotivoSolicitud>(motivosSolicitudSource, motivosSolicitudTarget);

			anexosPqrSource = new ArrayList<AnexosPqr>();
			anexosPqrTarget = new ArrayList<AnexosPqr>();
			anexosPqrSource = businessDelegatorView.consultarAnxsNoTipoPqr(tipoSolicitudPqr);
			anexosPqrTarget = businessDelegatorView.consultarAnxsXTipoPqr(tipoSolicitudPqr);
			anexosPqrTargetCopia = anexosPqrTarget;
			anexosPqr = new DualListModel<AnexosPqr>(anexosPqrSource, anexosPqrTarget);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public String action_new() {
		action_clear();
		selectedTipoSolicitudPqr = null;
		setShowDialog(true);
		consultarElementosNuevo();
		setBoton(false);
		return "";
	}

	public String action_edit() {
		try {
			setShowDialog(true);
			setBoton(true);
			tipoSolicitudPqr = businessDelegatorView.getTipoSolicitudPqr(idTpSolPqr);
			consultarElementosModificar(tipoSolicitudPqr);
			txtDescTpSol.setValue(tipoSolicitudPqr.getDescTpSol());
			txtEstadoRegistro.setValue(tipoSolicitudPqr.getEstadoRegistro());
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public String action_clear() {
		entity = null;
		selectedTipoSolicitudPqr = null;

		if (txtDescTpSol != null) {
			txtDescTpSol.setValue(null);
		}

		if (estadoRegistroSeleccionado != null) {
			estadoRegistroSeleccionado = null;
		}

		data = null;
		data = getData();
		setBoton(false);
		consultarElementosNuevo();

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
			Long idTpSolPqr = FacesUtils.checkLong(txtIdTpSolPqr);
			entity = (idTpSolPqr != null) ? businessDelegatorView
					.getTipoSolicitudPqr(idTpSolPqr) : null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
			entity = null;
		}

		if (entity == null) {
			txtDescTpSol.setDisabled(false);
			txtEstadoRegistro.setDisabled(false);
			txtUsuarioCreador.setDisabled(false);
			txtUsuarioUltimaModificacion.setDisabled(false);
			txtFechaCreacion.setDisabled(false);
			txtFechaUltimaModificacion.setDisabled(false);
			txtIdTpSolPqr.setDisabled(false);
			btnSave.setDisabled(false);
		} else {
			txtDescTpSol.setValue(entity.getDescTpSol());
			txtDescTpSol.setDisabled(false);
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
			txtIdTpSolPqr.setValue(entity.getIdTpSolPqr());
			txtIdTpSolPqr.setDisabled(true);
			btnSave.setDisabled(false);

			if (btnDelete != null) {
				btnDelete.setDisabled(false);
			}
		}
	}

	public String action_edit(ActionEvent evt) {
		selectedTipoSolicitudPqr = (TipoSolicitudPqrDTO) (evt.getComponent()
				.getAttributes().get("selectedTipoSolicitudPqr"));
		txtDescTpSol.setValue(selectedTipoSolicitudPqr.getDescTpSol());
		txtDescTpSol.setDisabled(false);
		txtEstadoRegistro
				.setValue(selectedTipoSolicitudPqr.getEstadoRegistro());
		txtEstadoRegistro.setDisabled(false);
		txtFechaCreacion.setValue(selectedTipoSolicitudPqr.getFechaCreacion());
		txtFechaCreacion.setDisabled(false);
		txtFechaUltimaModificacion.setValue(selectedTipoSolicitudPqr
				.getFechaUltimaModificacion());
		txtFechaUltimaModificacion.setDisabled(false);
		txtUsuarioCreador
				.setValue(selectedTipoSolicitudPqr.getUsuarioCreador());
		txtUsuarioCreador.setDisabled(false);
		txtUsuarioUltimaModificacion.setValue(selectedTipoSolicitudPqr
				.getUsuarioUltimaModificacion());
		txtUsuarioUltimaModificacion.setDisabled(false);
		txtIdTpSolPqr.setValue(selectedTipoSolicitudPqr.getIdTpSolPqr());
		txtIdTpSolPqr.setDisabled(true);
		btnSave.setDisabled(false);
		setShowDialog(true);

		return "";
	}

	public String action_save() {
		try {
			if ((selectedTipoSolicitudPqr == null) && (entity == null)) {
				action_create();
			} else {
				action_modify();
			}
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

	public String action_create() {
		try {
			entity = new TipoSolicitudPqr();

			entity.setDescTpSol(FacesUtils.checkString(txtDescTpSol));
			entity.setEstadoRegistro(estadoRegistroSeleccionado);
			// Falta agregar usuario de sesion
			entity.setUsuarioCreador("Admin");
			entity.setFechaCreacion(new Date());
			entity.setUsuarioUltimaModificacion(null);
			entity.setFechaUltimaModificacion(null);

			businessDelegatorView.saveTipoSolicitudPqr(entity);
			TipoSolicitudPqr tipoSol = businessDelegatorView.getTipoSolicitudPqr(entity.getIdTpSolPqr());
			motivosReclamacionTarget = motivosReclamacion.getTarget();
			motivosSolicitudTarget = motivosSolicitud.getTarget();
			anexosPqrTarget = anexosPqr.getTarget();
			
			businessDelegatorView.save_mot_recl_mot_sol_anxs_x_tipo( tipoSol, motivosReclamacionTargetCopia,
		    		motivosReclamacionTarget, motivosSolicitudTargetCopia,
					motivosSolicitudTarget, anexosPqrTargetCopia,
					anexosPqrTarget);
			
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
				entity = businessDelegatorView.getTipoSolicitudPqr(idTpSolPqr);
			}
			entity.setDescTpSol(FacesUtils.checkString(txtDescTpSol));
			entity.setEstadoRegistro(estadoRegistroSeleccionado);
			entity.setUsuarioCreador(entity.getUsuarioCreador());
			entity.setFechaCreacion(entity.getFechaCreacion());
			// Falta agregar usuario de sesion
			entity.setUsuarioUltimaModificacion("Facturación");
			entity.setFechaUltimaModificacion(new Date());

			businessDelegatorView.updateTipoSolicitudPqr(entity);
			TipoSolicitudPqr tipoSol = businessDelegatorView.getTipoSolicitudPqr(entity.getIdTpSolPqr());
			motivosReclamacionTarget = motivosReclamacion.getTarget();
			motivosSolicitudTarget = motivosSolicitud.getTarget();
			anexosPqrTarget = anexosPqr.getTarget();
			
			businessDelegatorView.save_mot_recl_mot_sol_anxs_x_tipo( tipoSol, motivosReclamacionTargetCopia,
		    		motivosReclamacionTarget, motivosSolicitudTargetCopia,
					motivosSolicitudTarget, anexosPqrTargetCopia,
					anexosPqrTarget);

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
			selectedTipoSolicitudPqr = (TipoSolicitudPqrDTO) (evt
					.getComponent().getAttributes()
					.get("selectedTipoSolicitudPqr"));

			Long idTpSolPqr = new Long(selectedTipoSolicitudPqr.getIdTpSolPqr());
			entity = businessDelegatorView.getTipoSolicitudPqr(idTpSolPqr);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_delete_master() {
		try {
			Long idTpSolPqr = FacesUtils.checkLong(txtIdTpSolPqr);
			entity = businessDelegatorView.getTipoSolicitudPqr(idTpSolPqr);
			action_delete();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public void action_delete() throws Exception {
		try {
			businessDelegatorView.deleteTipoSolicitudPqr(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
			data = null;
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public String action_closeDialog() {
		setShowDialog(false);
		action_clear();

		return "";
	}

	public String actionDeleteDataTableEditable(ActionEvent evt) {
		try {
			selectedTipoSolicitudPqr = (TipoSolicitudPqrDTO) (evt
					.getComponent().getAttributes()
					.get("selectedTipoSolicitudPqr"));

			Long idTpSolPqr = new Long(selectedTipoSolicitudPqr.getIdTpSolPqr());
			entity = businessDelegatorView.getTipoSolicitudPqr(idTpSolPqr);
			businessDelegatorView.deleteTipoSolicitudPqr(entity);
			data.remove(selectedTipoSolicitudPqr);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
			action_clear();
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	public String action_modifyWitDTO(String descTpSol, String estadoRegistro,
			Date fechaCreacion, Date fechaUltimaModificacion, Long idTpSolPqr,
			String usuarioCreador, String usuarioUltimaModificacion)
			throws Exception {
		try {
			entity.setDescTpSol(FacesUtils.checkString(descTpSol));
			entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
			entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
			entity.setFechaUltimaModificacion(FacesUtils
					.checkDate(fechaUltimaModificacion));
			entity.setUsuarioCreador(FacesUtils.checkString(usuarioCreador));
			entity.setUsuarioUltimaModificacion(FacesUtils
					.checkString(usuarioUltimaModificacion));
			businessDelegatorView.updateTipoSolicitudPqr(entity);
			FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
		} catch (Exception e) {
			// renderManager.getOnDemandRenderer("TipoSolicitudPqrView").requestRender();
			FacesUtils.addErrorMessage(e.getMessage());
			throw e;
		}

		return "";
	}

	public InputText getTxtDescTpSol() {
		return txtDescTpSol;
	}

	public void setTxtDescTpSol(InputText txtDescTpSol) {
		this.txtDescTpSol = txtDescTpSol;
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

	public InputText getTxtIdTpSolPqr() {
		return txtIdTpSolPqr;
	}

	public void setTxtIdTpSolPqr(InputText txtIdTpSolPqr) {
		this.txtIdTpSolPqr = txtIdTpSolPqr;
	}

	public List<TipoSolicitudPqrDTO> getData() {
		try {
			if (data == null) {
				data = businessDelegatorView.getDataTipoSolicitudPqr();
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return data;
	}

	public void setData(List<TipoSolicitudPqrDTO> tipoSolicitudPqrDTO) {
		this.data = tipoSolicitudPqrDTO;
	}

	public TipoSolicitudPqrDTO getSelectedTipoSolicitudPqr() {
		return selectedTipoSolicitudPqr;
	}

	public void setSelectedTipoSolicitudPqr(TipoSolicitudPqrDTO tipoSolicitudPqr) {
		this.selectedTipoSolicitudPqr = tipoSolicitudPqr;
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

	public DualListModel<MotivoReclamacion> getMotivosReclamacion() {
		return motivosReclamacion;
	}

	public void setMotivosReclamacion(
			DualListModel<MotivoReclamacion> motivosReclamacion) {
		this.motivosReclamacion = motivosReclamacion;
	}

	public DualListModel<MotivoSolicitud> getMotivosSolicitud() {
		return motivosSolicitud;
	}

	public void setMotivosSolicitud(
			DualListModel<MotivoSolicitud> motivosSolicitud) {
		this.motivosSolicitud = motivosSolicitud;
	}

	public DualListModel<AnexosPqr> getAnexosPqr() {
		return anexosPqr;
	}

	public void setAnexosPqr(DualListModel<AnexosPqr> anexosPqr) {
		this.anexosPqr = anexosPqr;
	}

	public List<MotivoReclamacion> getMotivosReclamacionSource() {
		return motivosReclamacionSource;
	}

	public void setMotivosReclamacionSource(
			List<MotivoReclamacion> motivosReclamacionSource) {
		this.motivosReclamacionSource = motivosReclamacionSource;
	}

	public List<MotivoReclamacion> getMotivosReclamacionTarget() {
		return motivosReclamacionTarget;
	}

	public void setMotivosReclamacionTarget(
			List<MotivoReclamacion> motivosReclamacionTarget) {
		this.motivosReclamacionTarget = motivosReclamacionTarget;
	}

	public List<MotivoSolicitud> getMotivosSolicitudSource() {
		return motivosSolicitudSource;
	}

	public void setMotivosSolicitudSource(
			List<MotivoSolicitud> motivosSolicitudSource) {
		this.motivosSolicitudSource = motivosSolicitudSource;
	}

	public List<MotivoSolicitud> getMotivosSolicitudTarget() {
		return motivosSolicitudTarget;
	}

	public void setMotivosSolicitudTarget(
			List<MotivoSolicitud> motivosSolicitudTarget) {
		this.motivosSolicitudTarget = motivosSolicitudTarget;
	}

	public List<AnexosPqr> getAnexosPqrSource() {
		return anexosPqrSource;
	}

	public void setAnexosPqrSource(List<AnexosPqr> anexosPqrSource) {
		this.anexosPqrSource = anexosPqrSource;
	}

	public List<AnexosPqr> getAnexosPqrTarget() {
		return anexosPqrTarget;
	}

	public void setAnexosPqrTarget(List<AnexosPqr> anexosPqrTarget) {
		this.anexosPqrTarget = anexosPqrTarget;
	}

	public TipoSolicitudPqr getTipoSolicitudPqr() {
		return tipoSolicitudPqr;
	}

	public void setTipoSolicitudPqr(TipoSolicitudPqr tipoSolicitudPqr) {
		this.tipoSolicitudPqr = tipoSolicitudPqr;
	}

	public Long getIdTpSolPqr() {
		return idTpSolPqr;
	}

	public void setIdTpSolPqr(Long idTpSolPqr) {
		this.idTpSolPqr = idTpSolPqr;
	}

	public Boolean getBoton() {
		return boton;
	}

	public void setBoton(Boolean boton) {
		this.boton = boton;
	}

	public List<MotivoReclamacion> getMotivosReclamacionTargetCopia() {
		return motivosReclamacionTargetCopia;
	}

	public void setMotivosReclamacionTargetCopia(
			List<MotivoReclamacion> motivosReclamacionTargetCopia) {
		this.motivosReclamacionTargetCopia = motivosReclamacionTargetCopia;
	}

	public List<MotivoSolicitud> getMotivosSolicitudTargetCopia() {
		return motivosSolicitudTargetCopia;
	}

	public void setMotivosSolicitudTargetCopia(
			List<MotivoSolicitud> motivosSolicitudTargetCopia) {
		this.motivosSolicitudTargetCopia = motivosSolicitudTargetCopia;
	}

	public List<AnexosPqr> getAnexosPqrTargetCopia() {
		return anexosPqrTargetCopia;
	}

	public void setAnexosPqrTargetCopia(List<AnexosPqr> anexosPqrTargetCopia) {
		this.anexosPqrTargetCopia = anexosPqrTargetCopia;
	}

}