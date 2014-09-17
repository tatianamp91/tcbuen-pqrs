package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.AnexosPqrDTO;
import com.tcbuen.pqrs.presentation.businessDelegate.*;
import com.tcbuen.pqrs.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
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


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
@ManagedBean
@ViewScoped
public class AnexosPqrView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtDescripcionAnexo;
    private InputText txtEstadoRegistro;
    private String estadoRegistroSeleccionado;
    private InputText txtUsuarioCreador;
    private InputText txtUsuarioUltimaModificacion;
    private InputText txtIdAnexoPqr;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaUltimaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<AnexosPqrDTO> data;
    private AnexosPqrDTO selectedAnexosPqr;
    private AnexosPqr entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public AnexosPqrView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            AnexosPqrDTO anexosPqrDTO = (AnexosPqrDTO) e.getObject();

            if (txtDescripcionAnexo == null) {
                txtDescripcionAnexo = new InputText();
            }

            txtDescripcionAnexo.setValue(anexosPqrDTO.getDescripcionAnexo());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(anexosPqrDTO.getEstadoRegistro());

            if (txtUsuarioCreador == null) {
                txtUsuarioCreador = new InputText();
            }

            txtUsuarioCreador.setValue(anexosPqrDTO.getUsuarioCreador());

            if (txtUsuarioUltimaModificacion == null) {
                txtUsuarioUltimaModificacion = new InputText();
            }

            txtUsuarioUltimaModificacion.setValue(anexosPqrDTO.getUsuarioUltimaModificacion());

            if (txtIdAnexoPqr == null) {
                txtIdAnexoPqr = new InputText();
            }

            txtIdAnexoPqr.setValue(anexosPqrDTO.getIdAnexoPqr());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(anexosPqrDTO.getFechaCreacion());

            if (txtFechaUltimaModificacion == null) {
                txtFechaUltimaModificacion = new Calendar();
            }

            txtFechaUltimaModificacion.setValue(anexosPqrDTO.getFechaUltimaModificacion());

            Long idAnexoPqr = FacesUtils.checkLong(txtIdAnexoPqr);
            entity = businessDelegatorView.getAnexosPqr(idAnexoPqr);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedAnexosPqr = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedAnexosPqr = null;

        if (txtDescripcionAnexo != null) {
            txtDescripcionAnexo.setValue(null);
        }

        if(estadoRegistroSeleccionado != null){
        	estadoRegistroSeleccionado = null;
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
            Long idAnexoPqr = FacesUtils.checkLong(txtIdAnexoPqr);
            entity = (idAnexoPqr != null)
                ? businessDelegatorView.getAnexosPqr(idAnexoPqr) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionAnexo.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtUsuarioCreador.setDisabled(false);
            txtUsuarioUltimaModificacion.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaUltimaModificacion.setDisabled(false);
            txtIdAnexoPqr.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionAnexo.setValue(entity.getDescripcionAnexo());
            txtDescripcionAnexo.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaUltimaModificacion.setValue(entity.getFechaUltimaModificacion());
            txtFechaUltimaModificacion.setDisabled(false);
            txtUsuarioCreador.setValue(entity.getUsuarioCreador());
            txtUsuarioCreador.setDisabled(false);
            txtUsuarioUltimaModificacion.setValue(entity.getUsuarioUltimaModificacion());
            txtUsuarioUltimaModificacion.setDisabled(false);
            txtIdAnexoPqr.setValue(entity.getIdAnexoPqr());
            txtIdAnexoPqr.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedAnexosPqr = (AnexosPqrDTO) (evt.getComponent().getAttributes()
                                               .get("selectedAnexosPqr"));
        txtDescripcionAnexo.setValue(selectedAnexosPqr.getDescripcionAnexo());
        txtDescripcionAnexo.setDisabled(false);
        txtEstadoRegistro.setValue(selectedAnexosPqr.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtFechaCreacion.setValue(selectedAnexosPqr.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaUltimaModificacion.setValue(selectedAnexosPqr.getFechaUltimaModificacion());
        txtFechaUltimaModificacion.setDisabled(false);
        txtUsuarioCreador.setValue(selectedAnexosPqr.getUsuarioCreador());
        txtUsuarioCreador.setDisabled(false);
        txtUsuarioUltimaModificacion.setValue(selectedAnexosPqr.getUsuarioUltimaModificacion());
        txtUsuarioUltimaModificacion.setDisabled(false);
        txtIdAnexoPqr.setValue(selectedAnexosPqr.getIdAnexoPqr());
        txtIdAnexoPqr.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedAnexosPqr == null) && (entity == null)) {
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

			String descripcionAnexo = txtDescripcionAnexo.getValue().toString();
			AnexosPqr anexos = ObtenerAnexo(descripcionAnexo);

			if (anexos == null) {

				if (!revizarCampos(descripcionAnexo)) {
					return "";

				}

			}

			entity = new AnexosPqr();

			// Long idAnexoPqr = FacesUtils.checkLong(txtIdAnexoPqr);
			// entity.setIdAnexoPqr(idAnexoPqr);
			entity.setDescripcionAnexo(FacesUtils
					.checkString(txtDescripcionAnexo));
			entity.setEstadoRegistro(estadoRegistroSeleccionado);
			// Falta agregar usuario de sesion
			entity.setUsuarioCreador("Admin");
			entity.setFechaCreacion(new Date());
			entity.setUsuarioUltimaModificacion(null);
			entity.setFechaUltimaModificacion(null);

			businessDelegatorView.saveAnexosPqr(entity);
			FacesUtils.addInfoMessage("El anexo se guardo exitosamente");

			action_clear();
		} catch (Exception e) {
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

	private AnexosPqr ObtenerAnexo(String descripcionAnexo) throws Exception {
		AnexosPqr entity = null;
		Object[] variables = { "descripcionAnexo", true, descripcionAnexo, "=" };
		List<AnexosPqr> anexosPqr = businessDelegatorView
				.findByCriteriaInAnexosPqr(variables, null, null);

		if (Utilities.validationsList(anexosPqr)) {
			entity = anexosPqr.get(0);
		}
		return entity;
	}

	public boolean revizarCampos(String descripcionAnexo) throws Exception {

		if (descripcionAnexo.equals("") || descripcionAnexo.trim().equals("")) {
			throw new Exception("Debe de ingresar un Nombre");
		}

		if (!Utilities.isOnlyLetters2(descripcionAnexo)) {
			throw new Exception(
					"El Nombre ingresado solo debe de contener letras");
		}
		return true;

	}

	private void actulizar() {
		try {

			entity.setDescripcionAnexo(FacesUtils
					.checkString(txtDescripcionAnexo));
			entity.setEstadoRegistro(estadoRegistroSeleccionado);
			entity.setUsuarioCreador(FacesUtils.checkString(txtUsuarioCreador));
			entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
			entity.setFechaUltimaModificacion(new Date());
			// Falta agregar usuario de sesion
			entity.setUsuarioUltimaModificacion("Facturación");
			businessDelegatorView.updateAnexosPqr(entity);

			FacesUtils.addInfoMessage("El anexo se modifico exitosamente");
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public String action_modify() {
		try {

			String descripcionAnexo = txtDescripcionAnexo.getValue().toString();
			AnexosPqr anexos = ObtenerAnexo(descripcionAnexo);

			if (anexos == null) {
				if (!revizarCampos(descripcionAnexo)) {
					return "";

				}
				actulizar();
				action_clear();
			} else {

				String descripcionTemp = anexos.getDescripcionAnexo();
				Long idTemp = anexos.getIdAnexoPqr();

				if (descripcionTemp.equals(descripcionAnexo) && idTemp == entity.getIdAnexoPqr().longValue()) {

					if (!revizarCampos(descripcionAnexo)) {
						return "";

					}
					actulizar();
					action_clear();
				} else {
					throw new Exception(
							"El anexo no ha sido modificado, anexo ya existe");
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
            selectedAnexosPqr = (AnexosPqrDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedAnexosPqr"));

            Long idAnexoPqr = new Long(selectedAnexosPqr.getIdAnexoPqr());
            entity = businessDelegatorView.getAnexosPqr(idAnexoPqr);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idAnexoPqr = FacesUtils.checkLong(txtIdAnexoPqr);
            entity = businessDelegatorView.getAnexosPqr(idAnexoPqr);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteAnexosPqr(entity);
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
            selectedAnexosPqr = (AnexosPqrDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedAnexosPqr"));

            Long idAnexoPqr = new Long(selectedAnexosPqr.getIdAnexoPqr());
            entity = businessDelegatorView.getAnexosPqr(idAnexoPqr);
            businessDelegatorView.deleteAnexosPqr(entity);
            data.remove(selectedAnexosPqr);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcionAnexo,
        String estadoRegistro, Date fechaCreacion,
        Date fechaUltimaModificacion, Long idAnexoPqr, String usuarioCreador,
        String usuarioUltimaModificacion) throws Exception {
        try {
            entity.setDescripcionAnexo(FacesUtils.checkString(descripcionAnexo));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaUltimaModificacion(FacesUtils.checkDate(
                    fechaUltimaModificacion));
            entity.setUsuarioCreador(FacesUtils.checkString(usuarioCreador));
            entity.setUsuarioUltimaModificacion(FacesUtils.checkString(
                    usuarioUltimaModificacion));
            businessDelegatorView.updateAnexosPqr(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("AnexosPqrView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcionAnexo() {
        return txtDescripcionAnexo;
    }

    public void setTxtDescripcionAnexo(InputText txtDescripcionAnexo) {
        this.txtDescripcionAnexo = txtDescripcionAnexo;
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

    public InputText getTxtIdAnexoPqr() {
        return txtIdAnexoPqr;
    }

    public void setTxtIdAnexoPqr(InputText txtIdAnexoPqr) {
        this.txtIdAnexoPqr = txtIdAnexoPqr;
    }

    public List<AnexosPqrDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataAnexosPqr();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<AnexosPqrDTO> anexosPqrDTO) {
        this.data = anexosPqrDTO;
    }

    public AnexosPqrDTO getSelectedAnexosPqr() {
        return selectedAnexosPqr;
    }

    public void setSelectedAnexosPqr(AnexosPqrDTO anexosPqr) {
        this.selectedAnexosPqr = anexosPqr;
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
