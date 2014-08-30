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
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public TipoSolicitudPqrView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            TipoSolicitudPqrDTO tipoSolicitudPqrDTO = (TipoSolicitudPqrDTO) e.getObject();

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

            txtUsuarioUltimaModificacion.setValue(tipoSolicitudPqrDTO.getUsuarioUltimaModificacion());

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

            txtFechaUltimaModificacion.setValue(tipoSolicitudPqrDTO.getFechaUltimaModificacion());

            Long idTpSolPqr = FacesUtils.checkLong(txtIdTpSolPqr);
            entity = businessDelegatorView.getTipoSolicitudPqr(idTpSolPqr);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedTipoSolicitudPqr = null;
        setShowDialog(true);

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
            Long idTpSolPqr = FacesUtils.checkLong(txtIdTpSolPqr);
            entity = (idTpSolPqr != null)
                ? businessDelegatorView.getTipoSolicitudPqr(idTpSolPqr) : null;
        } catch (Exception e) {
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
            txtFechaUltimaModificacion.setValue(entity.getFechaUltimaModificacion());
            txtFechaUltimaModificacion.setDisabled(false);
            txtUsuarioCreador.setValue(entity.getUsuarioCreador());
            txtUsuarioCreador.setDisabled(false);
            txtUsuarioUltimaModificacion.setValue(entity.getUsuarioUltimaModificacion());
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
                                                             .getAttributes()
                                                             .get("selectedTipoSolicitudPqr"));
        txtDescTpSol.setValue(selectedTipoSolicitudPqr.getDescTpSol());
        txtDescTpSol.setDisabled(false);
        txtEstadoRegistro.setValue(selectedTipoSolicitudPqr.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtFechaCreacion.setValue(selectedTipoSolicitudPqr.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaUltimaModificacion.setValue(selectedTipoSolicitudPqr.getFechaUltimaModificacion());
        txtFechaUltimaModificacion.setDisabled(false);
        txtUsuarioCreador.setValue(selectedTipoSolicitudPqr.getUsuarioCreador());
        txtUsuarioCreador.setDisabled(false);
        txtUsuarioUltimaModificacion.setValue(selectedTipoSolicitudPqr.getUsuarioUltimaModificacion());
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

            data = null;
            data = getData();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new TipoSolicitudPqr();

            //Long idTpSolPqr = FacesUtils.checkLong(txtIdTpSolPqr);
            //entity.setIdTpSolPqr(idTpSolPqr);
            
            entity.setDescTpSol(FacesUtils.checkString(txtDescTpSol));
            String estado = (estadoRegistroSeleccionado.equals("Activo"))?"A":"I";
            entity.setEstadoRegistro(estado);
            //Falta agregar usuario de sesion
            entity.setUsuarioCreador("Admin");
            entity.setFechaCreacion(new Date());
            entity.setUsuarioUltimaModificacion(null);
            entity.setFechaUltimaModificacion(null);            
            
            businessDelegatorView.saveTipoSolicitudPqr(entity);
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
                Long idTpSolPqr = new Long(selectedTipoSolicitudPqr.getIdTpSolPqr());
                entity = businessDelegatorView.getTipoSolicitudPqr(idTpSolPqr);
            }

            entity.setDescTpSol(FacesUtils.checkString(txtDescTpSol));
            String estado = (estadoRegistroSeleccionado.equals("Activo"))?"A":"I";
            entity.setEstadoRegistro(estado);
            entity.setUsuarioCreador(FacesUtils.checkString(txtUsuarioCreador));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            //Falta agregar usuario de sesion
            entity.setUsuarioUltimaModificacion("Facturación");
            entity.setFechaUltimaModificacion(new Date());            
     
            businessDelegatorView.updateTipoSolicitudPqr(entity);
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
            selectedTipoSolicitudPqr = (TipoSolicitudPqrDTO) (evt.getComponent()
                                                                 .getAttributes()
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
            selectedTipoSolicitudPqr = (TipoSolicitudPqrDTO) (evt.getComponent()
                                                                 .getAttributes()
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
            entity.setFechaUltimaModificacion(FacesUtils.checkDate(
                    fechaUltimaModificacion));
            entity.setUsuarioCreador(FacesUtils.checkString(usuarioCreador));
            entity.setUsuarioUltimaModificacion(FacesUtils.checkString(
                    usuarioUltimaModificacion));
            businessDelegatorView.updateTipoSolicitudPqr(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TipoSolicitudPqrView").requestRender();
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
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TipoSolicitudPqrDTO> tipoSolicitudPqrDTO) {
        this.data = tipoSolicitudPqrDTO;
    }

    public TipoSolicitudPqrDTO getSelectedTipoSolicitudPqr() {
        return selectedTipoSolicitudPqr;
    }

    public void setSelectedTipoSolicitudPqr(
        TipoSolicitudPqrDTO tipoSolicitudPqr) {
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
}
