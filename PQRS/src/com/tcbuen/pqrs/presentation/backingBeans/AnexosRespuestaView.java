package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.AnexosRespuestaDTO;
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
public class AnexosRespuestaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtAdjuntoDocumento;
    private InputText txtDocumentoReal;
    private InputText txtEstadoRegistro;
    private InputText txtNombreAnexo;
    private InputText txtNombreBusqueda;
    private InputText txtUsuarioCreador;
    private InputText txtIdAnexoPqr_AnexosPqr;
    private InputText txtIdRespSol_RespuestaSol;
    private InputText txtIdAnxResp;
    private Calendar txtFechaCreacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<AnexosRespuestaDTO> data;
    private AnexosRespuestaDTO selectedAnexosRespuesta;
    private AnexosRespuesta entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public AnexosRespuestaView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            AnexosRespuestaDTO anexosRespuestaDTO = (AnexosRespuestaDTO) e.getObject();

            if (txtAdjuntoDocumento == null) {
                txtAdjuntoDocumento = new InputText();
            }

            txtAdjuntoDocumento.setValue(anexosRespuestaDTO.getAdjuntoDocumento());

            if (txtDocumentoReal == null) {
                txtDocumentoReal = new InputText();
            }

            txtDocumentoReal.setValue(anexosRespuestaDTO.getDocumentoReal());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(anexosRespuestaDTO.getEstadoRegistro());

            if (txtNombreAnexo == null) {
                txtNombreAnexo = new InputText();
            }

            txtNombreAnexo.setValue(anexosRespuestaDTO.getNombreAnexo());

            if (txtNombreBusqueda == null) {
                txtNombreBusqueda = new InputText();
            }

            txtNombreBusqueda.setValue(anexosRespuestaDTO.getNombreBusqueda());

            if (txtUsuarioCreador == null) {
                txtUsuarioCreador = new InputText();
            }

            txtUsuarioCreador.setValue(anexosRespuestaDTO.getUsuarioCreador());

            if (txtIdAnexoPqr_AnexosPqr == null) {
                txtIdAnexoPqr_AnexosPqr = new InputText();
            }

            txtIdAnexoPqr_AnexosPqr.setValue(anexosRespuestaDTO.getIdAnexoPqr_AnexosPqr());

            if (txtIdRespSol_RespuestaSol == null) {
                txtIdRespSol_RespuestaSol = new InputText();
            }

            txtIdRespSol_RespuestaSol.setValue(anexosRespuestaDTO.getIdRespSol_RespuestaSol());

            if (txtIdAnxResp == null) {
                txtIdAnxResp = new InputText();
            }

            txtIdAnxResp.setValue(anexosRespuestaDTO.getIdAnxResp());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(anexosRespuestaDTO.getFechaCreacion());

            Long idAnxResp = FacesUtils.checkLong(txtIdAnxResp);
            entity = businessDelegatorView.getAnexosRespuesta(idAnxResp);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedAnexosRespuesta = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedAnexosRespuesta = null;

        if (txtAdjuntoDocumento != null) {
            txtAdjuntoDocumento.setValue(null);
            txtAdjuntoDocumento.setDisabled(true);
        }

        if (txtDocumentoReal != null) {
            txtDocumentoReal.setValue(null);
            txtDocumentoReal.setDisabled(true);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            txtEstadoRegistro.setDisabled(true);
        }

        if (txtNombreAnexo != null) {
            txtNombreAnexo.setValue(null);
            txtNombreAnexo.setDisabled(true);
        }

        if (txtNombreBusqueda != null) {
            txtNombreBusqueda.setValue(null);
            txtNombreBusqueda.setDisabled(true);
        }

        if (txtUsuarioCreador != null) {
            txtUsuarioCreador.setValue(null);
            txtUsuarioCreador.setDisabled(true);
        }

        if (txtIdAnexoPqr_AnexosPqr != null) {
            txtIdAnexoPqr_AnexosPqr.setValue(null);
            txtIdAnexoPqr_AnexosPqr.setDisabled(true);
        }

        if (txtIdRespSol_RespuestaSol != null) {
            txtIdRespSol_RespuestaSol.setValue(null);
            txtIdRespSol_RespuestaSol.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtIdAnxResp != null) {
            txtIdAnxResp.setValue(null);
            txtIdAnxResp.setDisabled(false);
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

    public void listener_txtId() {
        try {
            Long idAnxResp = FacesUtils.checkLong(txtIdAnxResp);
            entity = (idAnxResp != null)
                ? businessDelegatorView.getAnexosRespuesta(idAnxResp) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtAdjuntoDocumento.setDisabled(false);
            txtDocumentoReal.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtNombreAnexo.setDisabled(false);
            txtNombreBusqueda.setDisabled(false);
            txtUsuarioCreador.setDisabled(false);
            txtIdAnexoPqr_AnexosPqr.setDisabled(false);
            txtIdRespSol_RespuestaSol.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtIdAnxResp.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtAdjuntoDocumento.setValue(entity.getAdjuntoDocumento());
            txtAdjuntoDocumento.setDisabled(false);
            txtDocumentoReal.setValue(entity.getDocumentoReal());
            txtDocumentoReal.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtNombreAnexo.setValue(entity.getNombreAnexo());
            txtNombreAnexo.setDisabled(false);
            txtNombreBusqueda.setValue(entity.getNombreBusqueda());
            txtNombreBusqueda.setDisabled(false);
            txtUsuarioCreador.setValue(entity.getUsuarioCreador());
            txtUsuarioCreador.setDisabled(false);
            txtIdAnexoPqr_AnexosPqr.setValue(entity.getAnexosPqr()
                                                   .getIdAnexoPqr());
            txtIdAnexoPqr_AnexosPqr.setDisabled(false);
            txtIdRespSol_RespuestaSol.setValue(entity.getRespuestaSol()
                                                     .getIdRespSol());
            txtIdRespSol_RespuestaSol.setDisabled(false);
            txtIdAnxResp.setValue(entity.getIdAnxResp());
            txtIdAnxResp.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedAnexosRespuesta = (AnexosRespuestaDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedAnexosRespuesta"));
        txtAdjuntoDocumento.setValue(selectedAnexosRespuesta.getAdjuntoDocumento());
        txtAdjuntoDocumento.setDisabled(false);
        txtDocumentoReal.setValue(selectedAnexosRespuesta.getDocumentoReal());
        txtDocumentoReal.setDisabled(false);
        txtEstadoRegistro.setValue(selectedAnexosRespuesta.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtFechaCreacion.setValue(selectedAnexosRespuesta.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtNombreAnexo.setValue(selectedAnexosRespuesta.getNombreAnexo());
        txtNombreAnexo.setDisabled(false);
        txtNombreBusqueda.setValue(selectedAnexosRespuesta.getNombreBusqueda());
        txtNombreBusqueda.setDisabled(false);
        txtUsuarioCreador.setValue(selectedAnexosRespuesta.getUsuarioCreador());
        txtUsuarioCreador.setDisabled(false);
        txtIdAnexoPqr_AnexosPqr.setValue(selectedAnexosRespuesta.getIdAnexoPqr_AnexosPqr());
        txtIdAnexoPqr_AnexosPqr.setDisabled(false);
        txtIdRespSol_RespuestaSol.setValue(selectedAnexosRespuesta.getIdRespSol_RespuestaSol());
        txtIdRespSol_RespuestaSol.setDisabled(false);
        txtIdAnxResp.setValue(selectedAnexosRespuesta.getIdAnxResp());
        txtIdAnxResp.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedAnexosRespuesta == null) && (entity == null)) {
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
            entity = new AnexosRespuesta();

            Long idAnxResp = FacesUtils.checkLong(txtIdAnxResp);

            entity.setAdjuntoDocumento(FacesUtils.checkString(
                    txtAdjuntoDocumento));
            entity.setDocumentoReal(FacesUtils.checkBlob(txtDocumentoReal));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setIdAnxResp(idAnxResp);
            entity.setNombreAnexo(FacesUtils.checkString(txtNombreAnexo).toLowerCase());
            entity.setNombreBusqueda(FacesUtils.checkString(txtNombreBusqueda).toLowerCase());
            entity.setUsuarioCreador(FacesUtils.checkString(txtUsuarioCreador).toLowerCase());
            entity.setAnexosPqr((FacesUtils.checkLong(txtIdAnexoPqr_AnexosPqr) != null)
                ? businessDelegatorView.getAnexosPqr(FacesUtils.checkLong(
                        txtIdAnexoPqr_AnexosPqr)) : null);
            entity.setRespuestaSol((FacesUtils.checkLong(
                    txtIdRespSol_RespuestaSol) != null)
                ? businessDelegatorView.getRespuestaSol(FacesUtils.checkLong(
                        txtIdRespSol_RespuestaSol)) : null);
            businessDelegatorView.saveAnexosRespuesta(entity);
            FacesUtils.addInfoMessage("El anexo se guardó exitosamente");
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
                Long idAnxResp = new Long(selectedAnexosRespuesta.getIdAnxResp());
                entity = businessDelegatorView.getAnexosRespuesta(idAnxResp);
            }

            entity.setAdjuntoDocumento(FacesUtils.checkString(
                    txtAdjuntoDocumento));
            entity.setDocumentoReal(FacesUtils.checkBlob(txtDocumentoReal));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setNombreAnexo(FacesUtils.checkString(txtNombreAnexo).toLowerCase());
            entity.setNombreBusqueda(FacesUtils.checkString(txtNombreBusqueda).toLowerCase());
            entity.setUsuarioCreador(FacesUtils.checkString(txtUsuarioCreador).toLowerCase());
            entity.setAnexosPqr((FacesUtils.checkLong(txtIdAnexoPqr_AnexosPqr) != null)
                ? businessDelegatorView.getAnexosPqr(FacesUtils.checkLong(
                        txtIdAnexoPqr_AnexosPqr)) : null);
            entity.setRespuestaSol((FacesUtils.checkLong(
                    txtIdRespSol_RespuestaSol) != null)
                ? businessDelegatorView.getRespuestaSol(FacesUtils.checkLong(
                        txtIdRespSol_RespuestaSol)) : null);
            businessDelegatorView.updateAnexosRespuesta(entity);
            FacesUtils.addInfoMessage("El anexo se modificó exitosamente");
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedAnexosRespuesta = (AnexosRespuestaDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedAnexosRespuesta"));

            Long idAnxResp = new Long(selectedAnexosRespuesta.getIdAnxResp());
            entity = businessDelegatorView.getAnexosRespuesta(idAnxResp);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idAnxResp = FacesUtils.checkLong(txtIdAnxResp);
            entity = businessDelegatorView.getAnexosRespuesta(idAnxResp);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteAnexosRespuesta(entity);
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
            selectedAnexosRespuesta = (AnexosRespuestaDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedAnexosRespuesta"));

            Long idAnxResp = new Long(selectedAnexosRespuesta.getIdAnxResp());
            entity = businessDelegatorView.getAnexosRespuesta(idAnxResp);
            businessDelegatorView.deleteAnexosRespuesta(entity);
            data.remove(selectedAnexosRespuesta);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String adjuntoDocumento,
        Blob documentoReal, String estadoRegistro, Date fechaCreacion,
        Long idAnxResp, String nombreAnexo, String nombreBusqueda,
        String usuarioCreador, Long idAnexoPqr_AnexosPqr,
        Long idRespSol_RespuestaSol) throws Exception {
        try {
            entity.setAdjuntoDocumento(FacesUtils.checkString(adjuntoDocumento));
            entity.setDocumentoReal(FacesUtils.checkBlob(documentoReal));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setNombreAnexo(FacesUtils.checkString(nombreAnexo));
            entity.setNombreBusqueda(FacesUtils.checkString(nombreBusqueda));
            entity.setUsuarioCreador(FacesUtils.checkString(usuarioCreador));
            businessDelegatorView.updateAnexosRespuesta(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("AnexosRespuestaView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtAdjuntoDocumento() {
        return txtAdjuntoDocumento;
    }

    public void setTxtAdjuntoDocumento(InputText txtAdjuntoDocumento) {
        this.txtAdjuntoDocumento = txtAdjuntoDocumento;
    }

    public InputText getTxtDocumentoReal() {
        return txtDocumentoReal;
    }

    public void setTxtDocumentoReal(InputText txtDocumentoReal) {
        this.txtDocumentoReal = txtDocumentoReal;
    }

    public InputText getTxtEstadoRegistro() {
        return txtEstadoRegistro;
    }

    public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
        this.txtEstadoRegistro = txtEstadoRegistro;
    }

    public InputText getTxtNombreAnexo() {
        return txtNombreAnexo;
    }

    public void setTxtNombreAnexo(InputText txtNombreAnexo) {
        this.txtNombreAnexo = txtNombreAnexo;
    }

    public InputText getTxtNombreBusqueda() {
        return txtNombreBusqueda;
    }

    public void setTxtNombreBusqueda(InputText txtNombreBusqueda) {
        this.txtNombreBusqueda = txtNombreBusqueda;
    }

    public InputText getTxtUsuarioCreador() {
        return txtUsuarioCreador;
    }

    public void setTxtUsuarioCreador(InputText txtUsuarioCreador) {
        this.txtUsuarioCreador = txtUsuarioCreador;
    }

    public InputText getTxtIdAnexoPqr_AnexosPqr() {
        return txtIdAnexoPqr_AnexosPqr;
    }

    public void setTxtIdAnexoPqr_AnexosPqr(InputText txtIdAnexoPqr_AnexosPqr) {
        this.txtIdAnexoPqr_AnexosPqr = txtIdAnexoPqr_AnexosPqr;
    }

    public InputText getTxtIdRespSol_RespuestaSol() {
        return txtIdRespSol_RespuestaSol;
    }

    public void setTxtIdRespSol_RespuestaSol(
        InputText txtIdRespSol_RespuestaSol) {
        this.txtIdRespSol_RespuestaSol = txtIdRespSol_RespuestaSol;
    }

    public Calendar getTxtFechaCreacion() {
        return txtFechaCreacion;
    }

    public void setTxtFechaCreacion(Calendar txtFechaCreacion) {
        this.txtFechaCreacion = txtFechaCreacion;
    }

    public InputText getTxtIdAnxResp() {
        return txtIdAnxResp;
    }

    public void setTxtIdAnxResp(InputText txtIdAnxResp) {
        this.txtIdAnxResp = txtIdAnxResp;
    }

    public List<AnexosRespuestaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataAnexosRespuesta();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<AnexosRespuestaDTO> anexosRespuestaDTO) {
        this.data = anexosRespuestaDTO;
    }

    public AnexosRespuestaDTO getSelectedAnexosRespuesta() {
        return selectedAnexosRespuesta;
    }

    public void setSelectedAnexosRespuesta(AnexosRespuestaDTO anexosRespuesta) {
        this.selectedAnexosRespuesta = anexosRespuesta;
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
