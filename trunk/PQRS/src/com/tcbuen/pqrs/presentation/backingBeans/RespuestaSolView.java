package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.RespuestaSolDTO;
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
public class RespuestaSolView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtDescObservacion;
    private InputText txtEstadoRegistro;
    private InputText txtUsuarioCreador;
    private InputText txtUsuarioUltimaModificacion;
    private InputText txtValorReclamacion;
    private InputText txtIdSolAsigArea_SolicitudAsignadaArea;
    private InputText txtIdRespSol;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaUltimaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<RespuestaSolDTO> data;
    private RespuestaSolDTO selectedRespuestaSol;
    private RespuestaSol entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public RespuestaSolView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            RespuestaSolDTO respuestaSolDTO = (RespuestaSolDTO) e.getObject();

            if (txtDescObservacion == null) {
                txtDescObservacion = new InputText();
            }

            txtDescObservacion.setValue(respuestaSolDTO.getDescObservacion());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(respuestaSolDTO.getEstadoRegistro());

            if (txtUsuarioCreador == null) {
                txtUsuarioCreador = new InputText();
            }

            txtUsuarioCreador.setValue(respuestaSolDTO.getUsuarioCreador());

            if (txtUsuarioUltimaModificacion == null) {
                txtUsuarioUltimaModificacion = new InputText();
            }

            txtUsuarioUltimaModificacion.setValue(respuestaSolDTO.getUsuarioUltimaModificacion());

            if (txtValorReclamacion == null) {
                txtValorReclamacion = new InputText();
            }

            txtValorReclamacion.setValue(respuestaSolDTO.getValorReclamacion());

            if (txtIdSolAsigArea_SolicitudAsignadaArea == null) {
                txtIdSolAsigArea_SolicitudAsignadaArea = new InputText();
            }

            txtIdSolAsigArea_SolicitudAsignadaArea.setValue(respuestaSolDTO.getIdSolAsigArea_SolicitudAsignadaArea());

            if (txtIdRespSol == null) {
                txtIdRespSol = new InputText();
            }

            txtIdRespSol.setValue(respuestaSolDTO.getIdRespSol());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(respuestaSolDTO.getFechaCreacion());

            if (txtFechaUltimaModificacion == null) {
                txtFechaUltimaModificacion = new Calendar();
            }

            txtFechaUltimaModificacion.setValue(respuestaSolDTO.getFechaUltimaModificacion());

            Long idRespSol = FacesUtils.checkLong(txtIdRespSol);
            entity = businessDelegatorView.getRespuestaSol(idRespSol);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedRespuestaSol = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedRespuestaSol = null;

        if (txtDescObservacion != null) {
            txtDescObservacion.setValue(null);
            txtDescObservacion.setDisabled(true);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtUsuarioCreador != null) {
            txtUsuarioCreador.setValue(null);
            txtUsuarioCreador.setDisabled(true);
        }

        if (txtUsuarioUltimaModificacion != null) {
            txtUsuarioUltimaModificacion.setValue(null);
            txtUsuarioUltimaModificacion.setDisabled(true);
        }

        if (txtValorReclamacion != null) {
            txtValorReclamacion.setValue(null);
            txtValorReclamacion.setDisabled(true);
        }

        if (txtIdSolAsigArea_SolicitudAsignadaArea != null) {
            txtIdSolAsigArea_SolicitudAsignadaArea.setValue(null);
            txtIdSolAsigArea_SolicitudAsignadaArea.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaUltimaModificacion != null) {
            txtFechaUltimaModificacion.setValue(null);
            txtFechaUltimaModificacion.setDisabled(true);
        }

        if (txtIdRespSol != null) {
            txtIdRespSol.setValue(null);
            txtIdRespSol.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

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
            Long idRespSol = FacesUtils.checkLong(txtIdRespSol);
            entity = (idRespSol != null)
                ? businessDelegatorView.getRespuestaSol(idRespSol) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescObservacion.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtUsuarioCreador.setDisabled(false);
            txtUsuarioUltimaModificacion.setDisabled(false);
            txtValorReclamacion.setDisabled(false);
            txtIdSolAsigArea_SolicitudAsignadaArea.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaUltimaModificacion.setDisabled(false);
            txtIdRespSol.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescObservacion.setValue(entity.getDescObservacion());
            txtDescObservacion.setDisabled(false);
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
            txtValorReclamacion.setValue(entity.getValorReclamacion());
            txtValorReclamacion.setDisabled(false);
            txtIdSolAsigArea_SolicitudAsignadaArea.setValue(entity.getSolicitudAsignadaArea()
                                                                  .getIdSolAsigArea());
            txtIdSolAsigArea_SolicitudAsignadaArea.setDisabled(false);
            txtIdRespSol.setValue(entity.getIdRespSol());
            txtIdRespSol.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedRespuestaSol = (RespuestaSolDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedRespuestaSol"));
        txtDescObservacion.setValue(selectedRespuestaSol.getDescObservacion());
        txtDescObservacion.setDisabled(false);
        txtEstadoRegistro.setValue(selectedRespuestaSol.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtFechaCreacion.setValue(selectedRespuestaSol.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaUltimaModificacion.setValue(selectedRespuestaSol.getFechaUltimaModificacion());
        txtFechaUltimaModificacion.setDisabled(false);
        txtUsuarioCreador.setValue(selectedRespuestaSol.getUsuarioCreador());
        txtUsuarioCreador.setDisabled(false);
        txtUsuarioUltimaModificacion.setValue(selectedRespuestaSol.getUsuarioUltimaModificacion());
        txtUsuarioUltimaModificacion.setDisabled(false);
        txtValorReclamacion.setValue(selectedRespuestaSol.getValorReclamacion());
        txtValorReclamacion.setDisabled(false);
        txtIdSolAsigArea_SolicitudAsignadaArea.setValue(selectedRespuestaSol.getIdSolAsigArea_SolicitudAsignadaArea());
        txtIdSolAsigArea_SolicitudAsignadaArea.setDisabled(false);
        txtIdRespSol.setValue(selectedRespuestaSol.getIdRespSol());
        txtIdRespSol.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedRespuestaSol == null) && (entity == null)) {
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
            entity = new RespuestaSol();

            Long idRespSol = FacesUtils.checkLong(txtIdRespSol);

            entity.setDescObservacion(FacesUtils.checkString(txtDescObservacion));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaUltimaModificacion(FacesUtils.checkDate(
                    txtFechaUltimaModificacion));
            entity.setIdRespSol(idRespSol);
            entity.setUsuarioCreador(FacesUtils.checkString(txtUsuarioCreador));
            entity.setUsuarioUltimaModificacion(FacesUtils.checkString(
                    txtUsuarioUltimaModificacion));
            entity.setValorReclamacion(FacesUtils.checkDouble(
                    txtValorReclamacion));
            entity.setSolicitudAsignadaArea((FacesUtils.checkLong(
                    txtIdSolAsigArea_SolicitudAsignadaArea) != null)
                ? businessDelegatorView.getSolicitudAsignadaArea(
                    FacesUtils.checkLong(txtIdSolAsigArea_SolicitudAsignadaArea))
                : null);
            businessDelegatorView.saveRespuestaSol(entity);
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
                Long idRespSol = new Long(selectedRespuestaSol.getIdRespSol());
                entity = businessDelegatorView.getRespuestaSol(idRespSol);
            }

            entity.setDescObservacion(FacesUtils.checkString(txtDescObservacion));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaUltimaModificacion(FacesUtils.checkDate(
                    txtFechaUltimaModificacion));
            entity.setUsuarioCreador(FacesUtils.checkString(txtUsuarioCreador));
            entity.setUsuarioUltimaModificacion(FacesUtils.checkString(
                    txtUsuarioUltimaModificacion));
            entity.setValorReclamacion(FacesUtils.checkDouble(
                    txtValorReclamacion));
            entity.setSolicitudAsignadaArea((FacesUtils.checkLong(
                    txtIdSolAsigArea_SolicitudAsignadaArea) != null)
                ? businessDelegatorView.getSolicitudAsignadaArea(
                    FacesUtils.checkLong(txtIdSolAsigArea_SolicitudAsignadaArea))
                : null);
            businessDelegatorView.updateRespuestaSol(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedRespuestaSol = (RespuestaSolDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedRespuestaSol"));

            Long idRespSol = new Long(selectedRespuestaSol.getIdRespSol());
            entity = businessDelegatorView.getRespuestaSol(idRespSol);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idRespSol = FacesUtils.checkLong(txtIdRespSol);
            entity = businessDelegatorView.getRespuestaSol(idRespSol);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteRespuestaSol(entity);
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
            selectedRespuestaSol = (RespuestaSolDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedRespuestaSol"));

            Long idRespSol = new Long(selectedRespuestaSol.getIdRespSol());
            entity = businessDelegatorView.getRespuestaSol(idRespSol);
            businessDelegatorView.deleteRespuestaSol(entity);
            data.remove(selectedRespuestaSol);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descObservacion,
        String estadoRegistro, Date fechaCreacion,
        Date fechaUltimaModificacion, Long idRespSol, String usuarioCreador,
        String usuarioUltimaModificacion, Double valorReclamacion,
        Long idSolAsigArea_SolicitudAsignadaArea) throws Exception {
        try {
            entity.setDescObservacion(FacesUtils.checkString(descObservacion));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaUltimaModificacion(FacesUtils.checkDate(
                    fechaUltimaModificacion));
            entity.setUsuarioCreador(FacesUtils.checkString(usuarioCreador));
            entity.setUsuarioUltimaModificacion(FacesUtils.checkString(
                    usuarioUltimaModificacion));
            entity.setValorReclamacion(FacesUtils.checkDouble(valorReclamacion));
            businessDelegatorView.updateRespuestaSol(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("RespuestaSolView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescObservacion() {
        return txtDescObservacion;
    }

    public void setTxtDescObservacion(InputText txtDescObservacion) {
        this.txtDescObservacion = txtDescObservacion;
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

    public InputText getTxtValorReclamacion() {
        return txtValorReclamacion;
    }

    public void setTxtValorReclamacion(InputText txtValorReclamacion) {
        this.txtValorReclamacion = txtValorReclamacion;
    }

    public InputText getTxtIdSolAsigArea_SolicitudAsignadaArea() {
        return txtIdSolAsigArea_SolicitudAsignadaArea;
    }

    public void setTxtIdSolAsigArea_SolicitudAsignadaArea(
        InputText txtIdSolAsigArea_SolicitudAsignadaArea) {
        this.txtIdSolAsigArea_SolicitudAsignadaArea = txtIdSolAsigArea_SolicitudAsignadaArea;
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

    public InputText getTxtIdRespSol() {
        return txtIdRespSol;
    }

    public void setTxtIdRespSol(InputText txtIdRespSol) {
        this.txtIdRespSol = txtIdRespSol;
    }

    public List<RespuestaSolDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataRespuestaSol();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<RespuestaSolDTO> respuestaSolDTO) {
        this.data = respuestaSolDTO;
    }

    public RespuestaSolDTO getSelectedRespuestaSol() {
        return selectedRespuestaSol;
    }

    public void setSelectedRespuestaSol(RespuestaSolDTO respuestaSol) {
        this.selectedRespuestaSol = respuestaSol;
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
}
