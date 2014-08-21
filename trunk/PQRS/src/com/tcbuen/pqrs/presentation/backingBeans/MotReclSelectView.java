package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.MotReclSelectDTO;
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
public class MotReclSelectView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtIdMotRecl_MotivoReclamacion;
    private InputText txtIdSolPqr_SolicitudPqr;
    private InputText txtIdMotReclSelect;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<MotReclSelectDTO> data;
    private MotReclSelectDTO selectedMotReclSelect;
    private MotReclSelect entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public MotReclSelectView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            MotReclSelectDTO motReclSelectDTO = (MotReclSelectDTO) e.getObject();

            if (txtIdMotRecl_MotivoReclamacion == null) {
                txtIdMotRecl_MotivoReclamacion = new InputText();
            }

            txtIdMotRecl_MotivoReclamacion.setValue(motReclSelectDTO.getIdMotRecl_MotivoReclamacion());

            if (txtIdSolPqr_SolicitudPqr == null) {
                txtIdSolPqr_SolicitudPqr = new InputText();
            }

            txtIdSolPqr_SolicitudPqr.setValue(motReclSelectDTO.getIdSolPqr_SolicitudPqr());

            if (txtIdMotReclSelect == null) {
                txtIdMotReclSelect = new InputText();
            }

            txtIdMotReclSelect.setValue(motReclSelectDTO.getIdMotReclSelect());

            Long idMotReclSelect = FacesUtils.checkLong(txtIdMotReclSelect);
            entity = businessDelegatorView.getMotReclSelect(idMotReclSelect);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedMotReclSelect = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedMotReclSelect = null;

        if (txtIdMotRecl_MotivoReclamacion != null) {
            txtIdMotRecl_MotivoReclamacion.setValue(null);
            txtIdMotRecl_MotivoReclamacion.setDisabled(true);
        }

        if (txtIdSolPqr_SolicitudPqr != null) {
            txtIdSolPqr_SolicitudPqr.setValue(null);
            txtIdSolPqr_SolicitudPqr.setDisabled(true);
        }

        if (txtIdMotReclSelect != null) {
            txtIdMotReclSelect.setValue(null);
            txtIdMotReclSelect.setDisabled(false);
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
            Long idMotReclSelect = FacesUtils.checkLong(txtIdMotReclSelect);
            entity = (idMotReclSelect != null)
                ? businessDelegatorView.getMotReclSelect(idMotReclSelect) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtIdMotRecl_MotivoReclamacion.setDisabled(false);
            txtIdSolPqr_SolicitudPqr.setDisabled(false);
            txtIdMotReclSelect.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtIdMotRecl_MotivoReclamacion.setValue(entity.getMotivoReclamacion()
                                                          .getIdMotRecl());
            txtIdMotRecl_MotivoReclamacion.setDisabled(false);
            txtIdSolPqr_SolicitudPqr.setValue(entity.getSolicitudPqr()
                                                    .getIdSolPqr());
            txtIdSolPqr_SolicitudPqr.setDisabled(false);
            txtIdMotReclSelect.setValue(entity.getIdMotReclSelect());
            txtIdMotReclSelect.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedMotReclSelect = (MotReclSelectDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedMotReclSelect"));
        txtIdMotRecl_MotivoReclamacion.setValue(selectedMotReclSelect.getIdMotRecl_MotivoReclamacion());
        txtIdMotRecl_MotivoReclamacion.setDisabled(false);
        txtIdSolPqr_SolicitudPqr.setValue(selectedMotReclSelect.getIdSolPqr_SolicitudPqr());
        txtIdSolPqr_SolicitudPqr.setDisabled(false);
        txtIdMotReclSelect.setValue(selectedMotReclSelect.getIdMotReclSelect());
        txtIdMotReclSelect.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedMotReclSelect == null) && (entity == null)) {
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
            entity = new MotReclSelect();

            Long idMotReclSelect = FacesUtils.checkLong(txtIdMotReclSelect);

            entity.setIdMotReclSelect(idMotReclSelect);
            entity.setMotivoReclamacion((FacesUtils.checkLong(
                    txtIdMotRecl_MotivoReclamacion) != null)
                ? businessDelegatorView.getMotivoReclamacion(
                    FacesUtils.checkLong(txtIdMotRecl_MotivoReclamacion)) : null);
            entity.setSolicitudPqr((FacesUtils.checkLong(
                    txtIdSolPqr_SolicitudPqr) != null)
                ? businessDelegatorView.getSolicitudPqr(FacesUtils.checkLong(
                        txtIdSolPqr_SolicitudPqr)) : null);
            businessDelegatorView.saveMotReclSelect(entity);
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
                Long idMotReclSelect = new Long(selectedMotReclSelect.getIdMotReclSelect());
                entity = businessDelegatorView.getMotReclSelect(idMotReclSelect);
            }

            entity.setMotivoReclamacion((FacesUtils.checkLong(
                    txtIdMotRecl_MotivoReclamacion) != null)
                ? businessDelegatorView.getMotivoReclamacion(
                    FacesUtils.checkLong(txtIdMotRecl_MotivoReclamacion)) : null);
            entity.setSolicitudPqr((FacesUtils.checkLong(
                    txtIdSolPqr_SolicitudPqr) != null)
                ? businessDelegatorView.getSolicitudPqr(FacesUtils.checkLong(
                        txtIdSolPqr_SolicitudPqr)) : null);
            businessDelegatorView.updateMotReclSelect(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedMotReclSelect = (MotReclSelectDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedMotReclSelect"));

            Long idMotReclSelect = new Long(selectedMotReclSelect.getIdMotReclSelect());
            entity = businessDelegatorView.getMotReclSelect(idMotReclSelect);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idMotReclSelect = FacesUtils.checkLong(txtIdMotReclSelect);
            entity = businessDelegatorView.getMotReclSelect(idMotReclSelect);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteMotReclSelect(entity);
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
            selectedMotReclSelect = (MotReclSelectDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedMotReclSelect"));

            Long idMotReclSelect = new Long(selectedMotReclSelect.getIdMotReclSelect());
            entity = businessDelegatorView.getMotReclSelect(idMotReclSelect);
            businessDelegatorView.deleteMotReclSelect(entity);
            data.remove(selectedMotReclSelect);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long idMotReclSelect,
        Long idMotRecl_MotivoReclamacion, Long idSolPqr_SolicitudPqr)
        throws Exception {
        try {
            businessDelegatorView.updateMotReclSelect(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("MotReclSelectView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtIdMotRecl_MotivoReclamacion() {
        return txtIdMotRecl_MotivoReclamacion;
    }

    public void setTxtIdMotRecl_MotivoReclamacion(
        InputText txtIdMotRecl_MotivoReclamacion) {
        this.txtIdMotRecl_MotivoReclamacion = txtIdMotRecl_MotivoReclamacion;
    }

    public InputText getTxtIdSolPqr_SolicitudPqr() {
        return txtIdSolPqr_SolicitudPqr;
    }

    public void setTxtIdSolPqr_SolicitudPqr(InputText txtIdSolPqr_SolicitudPqr) {
        this.txtIdSolPqr_SolicitudPqr = txtIdSolPqr_SolicitudPqr;
    }

    public InputText getTxtIdMotReclSelect() {
        return txtIdMotReclSelect;
    }

    public void setTxtIdMotReclSelect(InputText txtIdMotReclSelect) {
        this.txtIdMotReclSelect = txtIdMotReclSelect;
    }

    public List<MotReclSelectDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataMotReclSelect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<MotReclSelectDTO> motReclSelectDTO) {
        this.data = motReclSelectDTO;
    }

    public MotReclSelectDTO getSelectedMotReclSelect() {
        return selectedMotReclSelect;
    }

    public void setSelectedMotReclSelect(MotReclSelectDTO motReclSelect) {
        this.selectedMotReclSelect = motReclSelect;
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
