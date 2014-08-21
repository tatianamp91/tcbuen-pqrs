package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.SolicitudAsignadaAreaDTO;
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
public class SolicitudAsignadaAreaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtIdAreaInvolucrada_AreasInvolucradas;
    private InputText txtIdSolPqr_SolicitudPqr;
    private InputText txtIdSolAsigArea;
    private Calendar txtFechaAsignacion;
    private Calendar txtFechaRespuesta;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SolicitudAsignadaAreaDTO> data;
    private SolicitudAsignadaAreaDTO selectedSolicitudAsignadaArea;
    private SolicitudAsignadaArea entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public SolicitudAsignadaAreaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SolicitudAsignadaAreaDTO solicitudAsignadaAreaDTO = (SolicitudAsignadaAreaDTO) e.getObject();

            if (txtIdAreaInvolucrada_AreasInvolucradas == null) {
                txtIdAreaInvolucrada_AreasInvolucradas = new InputText();
            }

            txtIdAreaInvolucrada_AreasInvolucradas.setValue(solicitudAsignadaAreaDTO.getIdAreaInvolucrada_AreasInvolucradas());

            if (txtIdSolPqr_SolicitudPqr == null) {
                txtIdSolPqr_SolicitudPqr = new InputText();
            }

            txtIdSolPqr_SolicitudPqr.setValue(solicitudAsignadaAreaDTO.getIdSolPqr_SolicitudPqr());

            if (txtIdSolAsigArea == null) {
                txtIdSolAsigArea = new InputText();
            }

            txtIdSolAsigArea.setValue(solicitudAsignadaAreaDTO.getIdSolAsigArea());

            if (txtFechaAsignacion == null) {
                txtFechaAsignacion = new Calendar();
            }

            txtFechaAsignacion.setValue(solicitudAsignadaAreaDTO.getFechaAsignacion());

            if (txtFechaRespuesta == null) {
                txtFechaRespuesta = new Calendar();
            }

            txtFechaRespuesta.setValue(solicitudAsignadaAreaDTO.getFechaRespuesta());

            Long idSolAsigArea = FacesUtils.checkLong(txtIdSolAsigArea);
            entity = businessDelegatorView.getSolicitudAsignadaArea(idSolAsigArea);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSolicitudAsignadaArea = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedSolicitudAsignadaArea = null;

        if (txtIdAreaInvolucrada_AreasInvolucradas != null) {
            txtIdAreaInvolucrada_AreasInvolucradas.setValue(null);
            txtIdAreaInvolucrada_AreasInvolucradas.setDisabled(true);
        }

        if (txtIdSolPqr_SolicitudPqr != null) {
            txtIdSolPqr_SolicitudPqr.setValue(null);
            txtIdSolPqr_SolicitudPqr.setDisabled(true);
        }

        if (txtFechaAsignacion != null) {
            txtFechaAsignacion.setValue(null);
            txtFechaAsignacion.setDisabled(true);
        }

        if (txtFechaRespuesta != null) {
            txtFechaRespuesta.setValue(null);
            txtFechaRespuesta.setDisabled(true);
        }

        if (txtIdSolAsigArea != null) {
            txtIdSolAsigArea.setValue(null);
            txtIdSolAsigArea.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFechaAsignacion() {
        Date inputDate = (Date) txtFechaAsignacion.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtFechaRespuesta() {
        Date inputDate = (Date) txtFechaRespuesta.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long idSolAsigArea = FacesUtils.checkLong(txtIdSolAsigArea);
            entity = (idSolAsigArea != null)
                ? businessDelegatorView.getSolicitudAsignadaArea(idSolAsigArea)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtIdAreaInvolucrada_AreasInvolucradas.setDisabled(false);
            txtIdSolPqr_SolicitudPqr.setDisabled(false);
            txtFechaAsignacion.setDisabled(false);
            txtFechaRespuesta.setDisabled(false);
            txtIdSolAsigArea.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtFechaAsignacion.setValue(entity.getFechaAsignacion());
            txtFechaAsignacion.setDisabled(false);
            txtFechaRespuesta.setValue(entity.getFechaRespuesta());
            txtFechaRespuesta.setDisabled(false);
            txtIdAreaInvolucrada_AreasInvolucradas.setValue(entity.getAreasInvolucradas()
                                                                  .getIdAreaInvolucrada());
            txtIdAreaInvolucrada_AreasInvolucradas.setDisabled(false);
            txtIdSolPqr_SolicitudPqr.setValue(entity.getSolicitudPqr()
                                                    .getIdSolPqr());
            txtIdSolPqr_SolicitudPqr.setDisabled(false);
            txtIdSolAsigArea.setValue(entity.getIdSolAsigArea());
            txtIdSolAsigArea.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedSolicitudAsignadaArea = (SolicitudAsignadaAreaDTO) (evt.getComponent()
                                                                       .getAttributes()
                                                                       .get("selectedSolicitudAsignadaArea"));
        txtFechaAsignacion.setValue(selectedSolicitudAsignadaArea.getFechaAsignacion());
        txtFechaAsignacion.setDisabled(false);
        txtFechaRespuesta.setValue(selectedSolicitudAsignadaArea.getFechaRespuesta());
        txtFechaRespuesta.setDisabled(false);
        txtIdAreaInvolucrada_AreasInvolucradas.setValue(selectedSolicitudAsignadaArea.getIdAreaInvolucrada_AreasInvolucradas());
        txtIdAreaInvolucrada_AreasInvolucradas.setDisabled(false);
        txtIdSolPqr_SolicitudPqr.setValue(selectedSolicitudAsignadaArea.getIdSolPqr_SolicitudPqr());
        txtIdSolPqr_SolicitudPqr.setDisabled(false);
        txtIdSolAsigArea.setValue(selectedSolicitudAsignadaArea.getIdSolAsigArea());
        txtIdSolAsigArea.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedSolicitudAsignadaArea == null) && (entity == null)) {
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
            entity = new SolicitudAsignadaArea();

            Long idSolAsigArea = FacesUtils.checkLong(txtIdSolAsigArea);

            entity.setFechaAsignacion(FacesUtils.checkDate(txtFechaAsignacion));
            entity.setFechaRespuesta(FacesUtils.checkDate(txtFechaRespuesta));
            entity.setIdSolAsigArea(idSolAsigArea);
            entity.setAreasInvolucradas((FacesUtils.checkLong(
                    txtIdAreaInvolucrada_AreasInvolucradas) != null)
                ? businessDelegatorView.getAreasInvolucradas(
                    FacesUtils.checkLong(txtIdAreaInvolucrada_AreasInvolucradas))
                : null);
            entity.setSolicitudPqr((FacesUtils.checkLong(
                    txtIdSolPqr_SolicitudPqr) != null)
                ? businessDelegatorView.getSolicitudPqr(FacesUtils.checkLong(
                        txtIdSolPqr_SolicitudPqr)) : null);
            businessDelegatorView.saveSolicitudAsignadaArea(entity);
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
                Long idSolAsigArea = new Long(selectedSolicitudAsignadaArea.getIdSolAsigArea());
                entity = businessDelegatorView.getSolicitudAsignadaArea(idSolAsigArea);
            }

            entity.setFechaAsignacion(FacesUtils.checkDate(txtFechaAsignacion));
            entity.setFechaRespuesta(FacesUtils.checkDate(txtFechaRespuesta));
            entity.setAreasInvolucradas((FacesUtils.checkLong(
                    txtIdAreaInvolucrada_AreasInvolucradas) != null)
                ? businessDelegatorView.getAreasInvolucradas(
                    FacesUtils.checkLong(txtIdAreaInvolucrada_AreasInvolucradas))
                : null);
            entity.setSolicitudPqr((FacesUtils.checkLong(
                    txtIdSolPqr_SolicitudPqr) != null)
                ? businessDelegatorView.getSolicitudPqr(FacesUtils.checkLong(
                        txtIdSolPqr_SolicitudPqr)) : null);
            businessDelegatorView.updateSolicitudAsignadaArea(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSolicitudAsignadaArea = (SolicitudAsignadaAreaDTO) (evt.getComponent()
                                                                           .getAttributes()
                                                                           .get("selectedSolicitudAsignadaArea"));

            Long idSolAsigArea = new Long(selectedSolicitudAsignadaArea.getIdSolAsigArea());
            entity = businessDelegatorView.getSolicitudAsignadaArea(idSolAsigArea);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idSolAsigArea = FacesUtils.checkLong(txtIdSolAsigArea);
            entity = businessDelegatorView.getSolicitudAsignadaArea(idSolAsigArea);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSolicitudAsignadaArea(entity);
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
            selectedSolicitudAsignadaArea = (SolicitudAsignadaAreaDTO) (evt.getComponent()
                                                                           .getAttributes()
                                                                           .get("selectedSolicitudAsignadaArea"));

            Long idSolAsigArea = new Long(selectedSolicitudAsignadaArea.getIdSolAsigArea());
            entity = businessDelegatorView.getSolicitudAsignadaArea(idSolAsigArea);
            businessDelegatorView.deleteSolicitudAsignadaArea(entity);
            data.remove(selectedSolicitudAsignadaArea);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Date fechaAsignacion,
        Date fechaRespuesta, Long idSolAsigArea,
        Long idAreaInvolucrada_AreasInvolucradas, Long idSolPqr_SolicitudPqr)
        throws Exception {
        try {
            entity.setFechaAsignacion(FacesUtils.checkDate(fechaAsignacion));
            entity.setFechaRespuesta(FacesUtils.checkDate(fechaRespuesta));
            businessDelegatorView.updateSolicitudAsignadaArea(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SolicitudAsignadaAreaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtIdAreaInvolucrada_AreasInvolucradas() {
        return txtIdAreaInvolucrada_AreasInvolucradas;
    }

    public void setTxtIdAreaInvolucrada_AreasInvolucradas(
        InputText txtIdAreaInvolucrada_AreasInvolucradas) {
        this.txtIdAreaInvolucrada_AreasInvolucradas = txtIdAreaInvolucrada_AreasInvolucradas;
    }

    public InputText getTxtIdSolPqr_SolicitudPqr() {
        return txtIdSolPqr_SolicitudPqr;
    }

    public void setTxtIdSolPqr_SolicitudPqr(InputText txtIdSolPqr_SolicitudPqr) {
        this.txtIdSolPqr_SolicitudPqr = txtIdSolPqr_SolicitudPqr;
    }

    public Calendar getTxtFechaAsignacion() {
        return txtFechaAsignacion;
    }

    public void setTxtFechaAsignacion(Calendar txtFechaAsignacion) {
        this.txtFechaAsignacion = txtFechaAsignacion;
    }

    public Calendar getTxtFechaRespuesta() {
        return txtFechaRespuesta;
    }

    public void setTxtFechaRespuesta(Calendar txtFechaRespuesta) {
        this.txtFechaRespuesta = txtFechaRespuesta;
    }

    public InputText getTxtIdSolAsigArea() {
        return txtIdSolAsigArea;
    }

    public void setTxtIdSolAsigArea(InputText txtIdSolAsigArea) {
        this.txtIdSolAsigArea = txtIdSolAsigArea;
    }

    public List<SolicitudAsignadaAreaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataSolicitudAsignadaArea();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<SolicitudAsignadaAreaDTO> solicitudAsignadaAreaDTO) {
        this.data = solicitudAsignadaAreaDTO;
    }

    public SolicitudAsignadaAreaDTO getSelectedSolicitudAsignadaArea() {
        return selectedSolicitudAsignadaArea;
    }

    public void setSelectedSolicitudAsignadaArea(
        SolicitudAsignadaAreaDTO solicitudAsignadaArea) {
        this.selectedSolicitudAsignadaArea = solicitudAsignadaArea;
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
