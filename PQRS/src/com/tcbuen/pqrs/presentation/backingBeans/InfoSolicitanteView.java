package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.InfoSolicitanteDTO;
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
public class InfoSolicitanteView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtCorreoElectronico;
    private InputText txtNombreContacto;
    private InputText txtNombreEmpresa;
    private InputText txtNumeroCelular;
    private InputText txtNumeroIdentificacion;
    private InputText txtTelefonoFijo;
    private InputText txtIdTpDoc_TipoDocumento;
    private InputText txtIdInfoSolicitante;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<InfoSolicitanteDTO> data;
    private InfoSolicitanteDTO selectedInfoSolicitante;
    private InfoSolicitante entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public InfoSolicitanteView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            InfoSolicitanteDTO infoSolicitanteDTO = (InfoSolicitanteDTO) e.getObject();

            if (txtCorreoElectronico == null) {
                txtCorreoElectronico = new InputText();
            }

            txtCorreoElectronico.setValue(infoSolicitanteDTO.getCorreoElectronico());

            if (txtNombreContacto == null) {
                txtNombreContacto = new InputText();
            }

            txtNombreContacto.setValue(infoSolicitanteDTO.getNombreContacto());

            if (txtNombreEmpresa == null) {
                txtNombreEmpresa = new InputText();
            }

            txtNombreEmpresa.setValue(infoSolicitanteDTO.getNombreEmpresa());

            if (txtNumeroCelular == null) {
                txtNumeroCelular = new InputText();
            }

            txtNumeroCelular.setValue(infoSolicitanteDTO.getNumeroCelular());

            if (txtNumeroIdentificacion == null) {
                txtNumeroIdentificacion = new InputText();
            }

            txtNumeroIdentificacion.setValue(infoSolicitanteDTO.getNumeroIdentificacion());

            if (txtTelefonoFijo == null) {
                txtTelefonoFijo = new InputText();
            }

            txtTelefonoFijo.setValue(infoSolicitanteDTO.getTelefonoFijo());

            if (txtIdTpDoc_TipoDocumento == null) {
                txtIdTpDoc_TipoDocumento = new InputText();
            }

            txtIdTpDoc_TipoDocumento.setValue(infoSolicitanteDTO.getIdTpDoc_TipoDocumento());

            if (txtIdInfoSolicitante == null) {
                txtIdInfoSolicitante = new InputText();
            }

            txtIdInfoSolicitante.setValue(infoSolicitanteDTO.getIdInfoSolicitante());

            Long idInfoSolicitante = FacesUtils.checkLong(txtIdInfoSolicitante);
            entity = businessDelegatorView.getInfoSolicitante(idInfoSolicitante);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedInfoSolicitante = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedInfoSolicitante = null;

        if (txtCorreoElectronico != null) {
            txtCorreoElectronico.setValue(null);
            txtCorreoElectronico.setDisabled(true);
        }

        if (txtNombreContacto != null) {
            txtNombreContacto.setValue(null);
            txtNombreContacto.setDisabled(true);
        }

        if (txtNombreEmpresa != null) {
            txtNombreEmpresa.setValue(null);
            txtNombreEmpresa.setDisabled(true);
        }

        if (txtNumeroCelular != null) {
            txtNumeroCelular.setValue(null);
            txtNumeroCelular.setDisabled(true);
        }

        if (txtNumeroIdentificacion != null) {
            txtNumeroIdentificacion.setValue(null);
            txtNumeroIdentificacion.setDisabled(true);
        }

        if (txtTelefonoFijo != null) {
            txtTelefonoFijo.setValue(null);
            txtTelefonoFijo.setDisabled(true);
        }

        if (txtIdTpDoc_TipoDocumento != null) {
            txtIdTpDoc_TipoDocumento.setValue(null);
            txtIdTpDoc_TipoDocumento.setDisabled(true);
        }

        if (txtIdInfoSolicitante != null) {
            txtIdInfoSolicitante.setValue(null);
            txtIdInfoSolicitante.setDisabled(false);
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
            Long idInfoSolicitante = FacesUtils.checkLong(txtIdInfoSolicitante);
            entity = (idInfoSolicitante != null)
                ? businessDelegatorView.getInfoSolicitante(idInfoSolicitante)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCorreoElectronico.setDisabled(false);
            txtNombreContacto.setDisabled(false);
            txtNombreEmpresa.setDisabled(false);
            txtNumeroCelular.setDisabled(false);
            txtNumeroIdentificacion.setDisabled(false);
            txtTelefonoFijo.setDisabled(false);
            txtIdTpDoc_TipoDocumento.setDisabled(false);
            txtIdInfoSolicitante.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCorreoElectronico.setValue(entity.getCorreoElectronico());
            txtCorreoElectronico.setDisabled(false);
            txtNombreContacto.setValue(entity.getNombreContacto());
            txtNombreContacto.setDisabled(false);
            txtNombreEmpresa.setValue(entity.getNombreEmpresa());
            txtNombreEmpresa.setDisabled(false);
            txtNumeroCelular.setValue(entity.getNumeroCelular());
            txtNumeroCelular.setDisabled(false);
            txtNumeroIdentificacion.setValue(entity.getNumeroIdentificacion());
            txtNumeroIdentificacion.setDisabled(false);
            txtTelefonoFijo.setValue(entity.getTelefonoFijo());
            txtTelefonoFijo.setDisabled(false);
            txtIdTpDoc_TipoDocumento.setValue(entity.getTipoDocumento()
                                                    .getIdTpDoc());
            txtIdTpDoc_TipoDocumento.setDisabled(false);
            txtIdInfoSolicitante.setValue(entity.getIdInfoSolicitante());
            txtIdInfoSolicitante.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedInfoSolicitante = (InfoSolicitanteDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedInfoSolicitante"));
        txtCorreoElectronico.setValue(selectedInfoSolicitante.getCorreoElectronico());
        txtCorreoElectronico.setDisabled(false);
        txtNombreContacto.setValue(selectedInfoSolicitante.getNombreContacto());
        txtNombreContacto.setDisabled(false);
        txtNombreEmpresa.setValue(selectedInfoSolicitante.getNombreEmpresa());
        txtNombreEmpresa.setDisabled(false);
        txtNumeroCelular.setValue(selectedInfoSolicitante.getNumeroCelular());
        txtNumeroCelular.setDisabled(false);
        txtNumeroIdentificacion.setValue(selectedInfoSolicitante.getNumeroIdentificacion());
        txtNumeroIdentificacion.setDisabled(false);
        txtTelefonoFijo.setValue(selectedInfoSolicitante.getTelefonoFijo());
        txtTelefonoFijo.setDisabled(false);
        txtIdTpDoc_TipoDocumento.setValue(selectedInfoSolicitante.getIdTpDoc_TipoDocumento());
        txtIdTpDoc_TipoDocumento.setDisabled(false);
        txtIdInfoSolicitante.setValue(selectedInfoSolicitante.getIdInfoSolicitante());
        txtIdInfoSolicitante.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedInfoSolicitante == null) && (entity == null)) {
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
            entity = new InfoSolicitante();

            Long idInfoSolicitante = FacesUtils.checkLong(txtIdInfoSolicitante);

            entity.setCorreoElectronico(FacesUtils.checkString(
                    txtCorreoElectronico));
            entity.setIdInfoSolicitante(idInfoSolicitante);
            entity.setNombreContacto(FacesUtils.checkString(txtNombreContacto));
            entity.setNombreEmpresa(FacesUtils.checkString(txtNombreEmpresa));
            entity.setNumeroCelular(FacesUtils.checkString(txtNumeroCelular));
            entity.setNumeroIdentificacion(FacesUtils.checkString(
                    txtNumeroIdentificacion));
            entity.setTelefonoFijo(FacesUtils.checkString(txtTelefonoFijo));
            entity.setTipoDocumento((FacesUtils.checkLong(
                    txtIdTpDoc_TipoDocumento) != null)
                ? businessDelegatorView.getTipoDocumento(FacesUtils.checkLong(
                        txtIdTpDoc_TipoDocumento)) : null);
            businessDelegatorView.saveInfoSolicitante(entity);
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
                Long idInfoSolicitante = new Long(selectedInfoSolicitante.getIdInfoSolicitante());
                entity = businessDelegatorView.getInfoSolicitante(idInfoSolicitante);
            }

            entity.setCorreoElectronico(FacesUtils.checkString(
                    txtCorreoElectronico));
            entity.setNombreContacto(FacesUtils.checkString(txtNombreContacto));
            entity.setNombreEmpresa(FacesUtils.checkString(txtNombreEmpresa));
            entity.setNumeroCelular(FacesUtils.checkString(txtNumeroCelular));
            entity.setNumeroIdentificacion(FacesUtils.checkString(
                    txtNumeroIdentificacion));
            entity.setTelefonoFijo(FacesUtils.checkString(txtTelefonoFijo));
            entity.setTipoDocumento((FacesUtils.checkLong(
                    txtIdTpDoc_TipoDocumento) != null)
                ? businessDelegatorView.getTipoDocumento(FacesUtils.checkLong(
                        txtIdTpDoc_TipoDocumento)) : null);
            businessDelegatorView.updateInfoSolicitante(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedInfoSolicitante = (InfoSolicitanteDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedInfoSolicitante"));

            Long idInfoSolicitante = new Long(selectedInfoSolicitante.getIdInfoSolicitante());
            entity = businessDelegatorView.getInfoSolicitante(idInfoSolicitante);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idInfoSolicitante = FacesUtils.checkLong(txtIdInfoSolicitante);
            entity = businessDelegatorView.getInfoSolicitante(idInfoSolicitante);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteInfoSolicitante(entity);
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
            selectedInfoSolicitante = (InfoSolicitanteDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedInfoSolicitante"));

            Long idInfoSolicitante = new Long(selectedInfoSolicitante.getIdInfoSolicitante());
            entity = businessDelegatorView.getInfoSolicitante(idInfoSolicitante);
            businessDelegatorView.deleteInfoSolicitante(entity);
            data.remove(selectedInfoSolicitante);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String correoElectronico,
        Long idInfoSolicitante, String nombreContacto, String nombreEmpresa,
        String numeroCelular, String numeroIdentificacion, String telefonoFijo,
        Long idTpDoc_TipoDocumento) throws Exception {
        try {
            entity.setCorreoElectronico(FacesUtils.checkString(
                    correoElectronico));
            entity.setNombreContacto(FacesUtils.checkString(nombreContacto));
            entity.setNombreEmpresa(FacesUtils.checkString(nombreEmpresa));
            entity.setNumeroCelular(FacesUtils.checkString(numeroCelular));
            entity.setNumeroIdentificacion(FacesUtils.checkString(
                    numeroIdentificacion));
            entity.setTelefonoFijo(FacesUtils.checkString(telefonoFijo));
            businessDelegatorView.updateInfoSolicitante(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("InfoSolicitanteView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCorreoElectronico() {
        return txtCorreoElectronico;
    }

    public void setTxtCorreoElectronico(InputText txtCorreoElectronico) {
        this.txtCorreoElectronico = txtCorreoElectronico;
    }

    public InputText getTxtNombreContacto() {
        return txtNombreContacto;
    }

    public void setTxtNombreContacto(InputText txtNombreContacto) {
        this.txtNombreContacto = txtNombreContacto;
    }

    public InputText getTxtNombreEmpresa() {
        return txtNombreEmpresa;
    }

    public void setTxtNombreEmpresa(InputText txtNombreEmpresa) {
        this.txtNombreEmpresa = txtNombreEmpresa;
    }

    public InputText getTxtNumeroCelular() {
        return txtNumeroCelular;
    }

    public void setTxtNumeroCelular(InputText txtNumeroCelular) {
        this.txtNumeroCelular = txtNumeroCelular;
    }

    public InputText getTxtNumeroIdentificacion() {
        return txtNumeroIdentificacion;
    }

    public void setTxtNumeroIdentificacion(InputText txtNumeroIdentificacion) {
        this.txtNumeroIdentificacion = txtNumeroIdentificacion;
    }

    public InputText getTxtTelefonoFijo() {
        return txtTelefonoFijo;
    }

    public void setTxtTelefonoFijo(InputText txtTelefonoFijo) {
        this.txtTelefonoFijo = txtTelefonoFijo;
    }

    public InputText getTxtIdTpDoc_TipoDocumento() {
        return txtIdTpDoc_TipoDocumento;
    }

    public void setTxtIdTpDoc_TipoDocumento(InputText txtIdTpDoc_TipoDocumento) {
        this.txtIdTpDoc_TipoDocumento = txtIdTpDoc_TipoDocumento;
    }

    public InputText getTxtIdInfoSolicitante() {
        return txtIdInfoSolicitante;
    }

    public void setTxtIdInfoSolicitante(InputText txtIdInfoSolicitante) {
        this.txtIdInfoSolicitante = txtIdInfoSolicitante;
    }

    public List<InfoSolicitanteDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataInfoSolicitante();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<InfoSolicitanteDTO> infoSolicitanteDTO) {
        this.data = infoSolicitanteDTO;
    }

    public InfoSolicitanteDTO getSelectedInfoSolicitante() {
        return selectedInfoSolicitante;
    }

    public void setSelectedInfoSolicitante(InfoSolicitanteDTO infoSolicitante) {
        this.selectedInfoSolicitante = infoSolicitante;
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
