package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.MotSolSelectDTO;
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
public class MotSolSelectView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtIdMotSol_MotivoSolicitud;
    private InputText txtIdSolPqr_SolicitudPqr;
    private InputText txtIdMotSolSelected;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<MotSolSelectDTO> data;
    private MotSolSelectDTO selectedMotSolSelect;
    private MotSolSelect entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public MotSolSelectView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            MotSolSelectDTO motSolSelectDTO = (MotSolSelectDTO) e.getObject();

            if (txtIdMotSol_MotivoSolicitud == null) {
                txtIdMotSol_MotivoSolicitud = new InputText();
            }

            txtIdMotSol_MotivoSolicitud.setValue(motSolSelectDTO.getIdMotSol_MotivoSolicitud());

            if (txtIdSolPqr_SolicitudPqr == null) {
                txtIdSolPqr_SolicitudPqr = new InputText();
            }

            txtIdSolPqr_SolicitudPqr.setValue(motSolSelectDTO.getIdSolPqr_SolicitudPqr());

            if (txtIdMotSolSelected == null) {
                txtIdMotSolSelected = new InputText();
            }

            txtIdMotSolSelected.setValue(motSolSelectDTO.getIdMotSolSelected());

            Long idMotSolSelected = FacesUtils.checkLong(txtIdMotSolSelected);
            entity = businessDelegatorView.getMotSolSelect(idMotSolSelected);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedMotSolSelect = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedMotSolSelect = null;

        if (txtIdMotSol_MotivoSolicitud != null) {
            txtIdMotSol_MotivoSolicitud.setValue(null);
            txtIdMotSol_MotivoSolicitud.setDisabled(true);
        }

        if (txtIdSolPqr_SolicitudPqr != null) {
            txtIdSolPqr_SolicitudPqr.setValue(null);
            txtIdSolPqr_SolicitudPqr.setDisabled(true);
        }

        if (txtIdMotSolSelected != null) {
            txtIdMotSolSelected.setValue(null);
            txtIdMotSolSelected.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtId() {
        try {
            Long idMotSolSelected = FacesUtils.checkLong(txtIdMotSolSelected);
            entity = (idMotSolSelected != null)
                ? businessDelegatorView.getMotSolSelect(idMotSolSelected) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtIdMotSol_MotivoSolicitud.setDisabled(false);
            txtIdSolPqr_SolicitudPqr.setDisabled(false);
            txtIdMotSolSelected.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtIdMotSol_MotivoSolicitud.setValue(entity.getMotivoSolicitud()
                                                       .getIdMotSol());
            txtIdMotSol_MotivoSolicitud.setDisabled(false);
            txtIdSolPqr_SolicitudPqr.setValue(entity.getSolicitudPqr()
                                                    .getIdSolPqr());
            txtIdSolPqr_SolicitudPqr.setDisabled(false);
            txtIdMotSolSelected.setValue(entity.getIdMotSolSelected());
            txtIdMotSolSelected.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedMotSolSelect = (MotSolSelectDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedMotSolSelect"));
        txtIdMotSol_MotivoSolicitud.setValue(selectedMotSolSelect.getIdMotSol_MotivoSolicitud());
        txtIdMotSol_MotivoSolicitud.setDisabled(false);
        txtIdSolPqr_SolicitudPqr.setValue(selectedMotSolSelect.getIdSolPqr_SolicitudPqr());
        txtIdSolPqr_SolicitudPqr.setDisabled(false);
        txtIdMotSolSelected.setValue(selectedMotSolSelect.getIdMotSolSelected());
        txtIdMotSolSelected.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedMotSolSelect == null) && (entity == null)) {
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
            entity = new MotSolSelect();

            Long idMotSolSelected = FacesUtils.checkLong(txtIdMotSolSelected);

            entity.setIdMotSolSelected(idMotSolSelected);
            entity.setMotivoSolicitud((FacesUtils.checkLong(
                    txtIdMotSol_MotivoSolicitud) != null)
                ? businessDelegatorView.getMotivoSolicitud(FacesUtils.checkLong(
                        txtIdMotSol_MotivoSolicitud)) : null);
            entity.setSolicitudPqr((FacesUtils.checkLong(
                    txtIdSolPqr_SolicitudPqr) != null)
                ? businessDelegatorView.getSolicitudPqr(FacesUtils.checkLong(
                        txtIdSolPqr_SolicitudPqr)) : null);
            businessDelegatorView.saveMotSolSelect(entity);
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
                Long idMotSolSelected = new Long(selectedMotSolSelect.getIdMotSolSelected());
                entity = businessDelegatorView.getMotSolSelect(idMotSolSelected);
            }

            entity.setMotivoSolicitud((FacesUtils.checkLong(
                    txtIdMotSol_MotivoSolicitud) != null)
                ? businessDelegatorView.getMotivoSolicitud(FacesUtils.checkLong(
                        txtIdMotSol_MotivoSolicitud)) : null);
            entity.setSolicitudPqr((FacesUtils.checkLong(
                    txtIdSolPqr_SolicitudPqr) != null)
                ? businessDelegatorView.getSolicitudPqr(FacesUtils.checkLong(
                        txtIdSolPqr_SolicitudPqr)) : null);
            businessDelegatorView.updateMotSolSelect(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedMotSolSelect = (MotSolSelectDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedMotSolSelect"));

            Long idMotSolSelected = new Long(selectedMotSolSelect.getIdMotSolSelected());
            entity = businessDelegatorView.getMotSolSelect(idMotSolSelected);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idMotSolSelected = FacesUtils.checkLong(txtIdMotSolSelected);
            entity = businessDelegatorView.getMotSolSelect(idMotSolSelected);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteMotSolSelect(entity);
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
            selectedMotSolSelect = (MotSolSelectDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedMotSolSelect"));

            Long idMotSolSelected = new Long(selectedMotSolSelect.getIdMotSolSelected());
            entity = businessDelegatorView.getMotSolSelect(idMotSolSelected);
            businessDelegatorView.deleteMotSolSelect(entity);
            data.remove(selectedMotSolSelect);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long idMotSolSelected,
        Long idMotSol_MotivoSolicitud, Long idSolPqr_SolicitudPqr)
        throws Exception {
        try {
            businessDelegatorView.updateMotSolSelect(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("MotSolSelectView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtIdMotSol_MotivoSolicitud() {
        return txtIdMotSol_MotivoSolicitud;
    }

    public void setTxtIdMotSol_MotivoSolicitud(
        InputText txtIdMotSol_MotivoSolicitud) {
        this.txtIdMotSol_MotivoSolicitud = txtIdMotSol_MotivoSolicitud;
    }

    public InputText getTxtIdSolPqr_SolicitudPqr() {
        return txtIdSolPqr_SolicitudPqr;
    }

    public void setTxtIdSolPqr_SolicitudPqr(InputText txtIdSolPqr_SolicitudPqr) {
        this.txtIdSolPqr_SolicitudPqr = txtIdSolPqr_SolicitudPqr;
    }

    public InputText getTxtIdMotSolSelected() {
        return txtIdMotSolSelected;
    }

    public void setTxtIdMotSolSelected(InputText txtIdMotSolSelected) {
        this.txtIdMotSolSelected = txtIdMotSolSelected;
    }

    public List<MotSolSelectDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataMotSolSelect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<MotSolSelectDTO> motSolSelectDTO) {
        this.data = motSolSelectDTO;
    }

    public MotSolSelectDTO getSelectedMotSolSelect() {
        return selectedMotSolSelect;
    }

    public void setSelectedMotSolSelect(MotSolSelectDTO motSolSelect) {
        this.selectedMotSolSelect = motSolSelect;
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
