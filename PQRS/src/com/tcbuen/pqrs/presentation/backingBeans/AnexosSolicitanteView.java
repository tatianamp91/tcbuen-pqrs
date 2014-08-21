package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.AnexosSolicitanteDTO;
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
public class AnexosSolicitanteView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtAdjuntoDocumento;
    private InputText txtApruebaAnexo;
    private InputText txtDocumentoReal;
    private InputText txtEstadoRegistro;
    private InputText txtNombreAnexo;
    private InputText txtNombreBusqueda;
    private InputText txtRazonRechazo;
    private InputText txtUsuarioCreador;
    private InputText txtUsuarioUltimaModificacion;
    private InputText txtIdAnexoPqr_AnexosPqr;
    private InputText txtIdSolPqr_SolicitudPqr;
    private InputText txtIdAnxSolicitante;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaUltimaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<AnexosSolicitanteDTO> data;
    private AnexosSolicitanteDTO selectedAnexosSolicitante;
    private AnexosSolicitante entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public AnexosSolicitanteView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            AnexosSolicitanteDTO anexosSolicitanteDTO = (AnexosSolicitanteDTO) e.getObject();

            if (txtAdjuntoDocumento == null) {
                txtAdjuntoDocumento = new InputText();
            }

            txtAdjuntoDocumento.setValue(anexosSolicitanteDTO.getAdjuntoDocumento());

            if (txtApruebaAnexo == null) {
                txtApruebaAnexo = new InputText();
            }

            txtApruebaAnexo.setValue(anexosSolicitanteDTO.getApruebaAnexo());

            if (txtDocumentoReal == null) {
                txtDocumentoReal = new InputText();
            }

            txtDocumentoReal.setValue(anexosSolicitanteDTO.getDocumentoReal());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(anexosSolicitanteDTO.getEstadoRegistro());

            if (txtNombreAnexo == null) {
                txtNombreAnexo = new InputText();
            }

            txtNombreAnexo.setValue(anexosSolicitanteDTO.getNombreAnexo());

            if (txtNombreBusqueda == null) {
                txtNombreBusqueda = new InputText();
            }

            txtNombreBusqueda.setValue(anexosSolicitanteDTO.getNombreBusqueda());

            if (txtRazonRechazo == null) {
                txtRazonRechazo = new InputText();
            }

            txtRazonRechazo.setValue(anexosSolicitanteDTO.getRazonRechazo());

            if (txtUsuarioCreador == null) {
                txtUsuarioCreador = new InputText();
            }

            txtUsuarioCreador.setValue(anexosSolicitanteDTO.getUsuarioCreador());

            if (txtUsuarioUltimaModificacion == null) {
                txtUsuarioUltimaModificacion = new InputText();
            }

            txtUsuarioUltimaModificacion.setValue(anexosSolicitanteDTO.getUsuarioUltimaModificacion());

            if (txtIdAnexoPqr_AnexosPqr == null) {
                txtIdAnexoPqr_AnexosPqr = new InputText();
            }

            txtIdAnexoPqr_AnexosPqr.setValue(anexosSolicitanteDTO.getIdAnexoPqr_AnexosPqr());

            if (txtIdSolPqr_SolicitudPqr == null) {
                txtIdSolPqr_SolicitudPqr = new InputText();
            }

            txtIdSolPqr_SolicitudPqr.setValue(anexosSolicitanteDTO.getIdSolPqr_SolicitudPqr());

            if (txtIdAnxSolicitante == null) {
                txtIdAnxSolicitante = new InputText();
            }

            txtIdAnxSolicitante.setValue(anexosSolicitanteDTO.getIdAnxSolicitante());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(anexosSolicitanteDTO.getFechaCreacion());

            if (txtFechaUltimaModificacion == null) {
                txtFechaUltimaModificacion = new Calendar();
            }

            txtFechaUltimaModificacion.setValue(anexosSolicitanteDTO.getFechaUltimaModificacion());

            Long idAnxSolicitante = FacesUtils.checkLong(txtIdAnxSolicitante);
            entity = businessDelegatorView.getAnexosSolicitante(idAnxSolicitante);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedAnexosSolicitante = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedAnexosSolicitante = null;

        if (txtAdjuntoDocumento != null) {
            txtAdjuntoDocumento.setValue(null);
            txtAdjuntoDocumento.setDisabled(true);
        }

        if (txtApruebaAnexo != null) {
            txtApruebaAnexo.setValue(null);
            txtApruebaAnexo.setDisabled(true);
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

        if (txtRazonRechazo != null) {
            txtRazonRechazo.setValue(null);
            txtRazonRechazo.setDisabled(true);
        }

        if (txtUsuarioCreador != null) {
            txtUsuarioCreador.setValue(null);
            txtUsuarioCreador.setDisabled(true);
        }

        if (txtUsuarioUltimaModificacion != null) {
            txtUsuarioUltimaModificacion.setValue(null);
            txtUsuarioUltimaModificacion.setDisabled(true);
        }

        if (txtIdAnexoPqr_AnexosPqr != null) {
            txtIdAnexoPqr_AnexosPqr.setValue(null);
            txtIdAnexoPqr_AnexosPqr.setDisabled(true);
        }

        if (txtIdSolPqr_SolicitudPqr != null) {
            txtIdSolPqr_SolicitudPqr.setValue(null);
            txtIdSolPqr_SolicitudPqr.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaUltimaModificacion != null) {
            txtFechaUltimaModificacion.setValue(null);
            txtFechaUltimaModificacion.setDisabled(true);
        }

        if (txtIdAnxSolicitante != null) {
            txtIdAnxSolicitante.setValue(null);
            txtIdAnxSolicitante.setDisabled(false);
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
            Long idAnxSolicitante = FacesUtils.checkLong(txtIdAnxSolicitante);
            entity = (idAnxSolicitante != null)
                ? businessDelegatorView.getAnexosSolicitante(idAnxSolicitante)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtAdjuntoDocumento.setDisabled(false);
            txtApruebaAnexo.setDisabled(false);
            txtDocumentoReal.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtNombreAnexo.setDisabled(false);
            txtNombreBusqueda.setDisabled(false);
            txtRazonRechazo.setDisabled(false);
            txtUsuarioCreador.setDisabled(false);
            txtUsuarioUltimaModificacion.setDisabled(false);
            txtIdAnexoPqr_AnexosPqr.setDisabled(false);
            txtIdSolPqr_SolicitudPqr.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaUltimaModificacion.setDisabled(false);
            txtIdAnxSolicitante.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtAdjuntoDocumento.setValue(entity.getAdjuntoDocumento());
            txtAdjuntoDocumento.setDisabled(false);
            txtApruebaAnexo.setValue(entity.getApruebaAnexo());
            txtApruebaAnexo.setDisabled(false);
            txtDocumentoReal.setValue(entity.getDocumentoReal());
            txtDocumentoReal.setDisabled(false);
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaUltimaModificacion.setValue(entity.getFechaUltimaModificacion());
            txtFechaUltimaModificacion.setDisabled(false);
            txtNombreAnexo.setValue(entity.getNombreAnexo());
            txtNombreAnexo.setDisabled(false);
            txtNombreBusqueda.setValue(entity.getNombreBusqueda());
            txtNombreBusqueda.setDisabled(false);
            txtRazonRechazo.setValue(entity.getRazonRechazo());
            txtRazonRechazo.setDisabled(false);
            txtUsuarioCreador.setValue(entity.getUsuarioCreador());
            txtUsuarioCreador.setDisabled(false);
            txtUsuarioUltimaModificacion.setValue(entity.getUsuarioUltimaModificacion());
            txtUsuarioUltimaModificacion.setDisabled(false);
            txtIdAnexoPqr_AnexosPqr.setValue(entity.getAnexosPqr()
                                                   .getIdAnexoPqr());
            txtIdAnexoPqr_AnexosPqr.setDisabled(false);
            txtIdSolPqr_SolicitudPqr.setValue(entity.getSolicitudPqr()
                                                    .getIdSolPqr());
            txtIdSolPqr_SolicitudPqr.setDisabled(false);
            txtIdAnxSolicitante.setValue(entity.getIdAnxSolicitante());
            txtIdAnxSolicitante.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedAnexosSolicitante = (AnexosSolicitanteDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedAnexosSolicitante"));
        txtAdjuntoDocumento.setValue(selectedAnexosSolicitante.getAdjuntoDocumento());
        txtAdjuntoDocumento.setDisabled(false);
        txtApruebaAnexo.setValue(selectedAnexosSolicitante.getApruebaAnexo());
        txtApruebaAnexo.setDisabled(false);
        txtDocumentoReal.setValue(selectedAnexosSolicitante.getDocumentoReal());
        txtDocumentoReal.setDisabled(false);
        txtEstadoRegistro.setValue(selectedAnexosSolicitante.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtFechaCreacion.setValue(selectedAnexosSolicitante.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaUltimaModificacion.setValue(selectedAnexosSolicitante.getFechaUltimaModificacion());
        txtFechaUltimaModificacion.setDisabled(false);
        txtNombreAnexo.setValue(selectedAnexosSolicitante.getNombreAnexo());
        txtNombreAnexo.setDisabled(false);
        txtNombreBusqueda.setValue(selectedAnexosSolicitante.getNombreBusqueda());
        txtNombreBusqueda.setDisabled(false);
        txtRazonRechazo.setValue(selectedAnexosSolicitante.getRazonRechazo());
        txtRazonRechazo.setDisabled(false);
        txtUsuarioCreador.setValue(selectedAnexosSolicitante.getUsuarioCreador());
        txtUsuarioCreador.setDisabled(false);
        txtUsuarioUltimaModificacion.setValue(selectedAnexosSolicitante.getUsuarioUltimaModificacion());
        txtUsuarioUltimaModificacion.setDisabled(false);
        txtIdAnexoPqr_AnexosPqr.setValue(selectedAnexosSolicitante.getIdAnexoPqr_AnexosPqr());
        txtIdAnexoPqr_AnexosPqr.setDisabled(false);
        txtIdSolPqr_SolicitudPqr.setValue(selectedAnexosSolicitante.getIdSolPqr_SolicitudPqr());
        txtIdSolPqr_SolicitudPqr.setDisabled(false);
        txtIdAnxSolicitante.setValue(selectedAnexosSolicitante.getIdAnxSolicitante());
        txtIdAnxSolicitante.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedAnexosSolicitante == null) && (entity == null)) {
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
            entity = new AnexosSolicitante();

            Long idAnxSolicitante = FacesUtils.checkLong(txtIdAnxSolicitante);

            entity.setAdjuntoDocumento(FacesUtils.checkString(
                    txtAdjuntoDocumento));
            entity.setApruebaAnexo(FacesUtils.checkString(txtApruebaAnexo));
            entity.setDocumentoReal(FacesUtils.checkBlob(txtDocumentoReal));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaUltimaModificacion(FacesUtils.checkDate(
                    txtFechaUltimaModificacion));
            entity.setIdAnxSolicitante(idAnxSolicitante);
            entity.setNombreAnexo(FacesUtils.checkString(txtNombreAnexo));
            entity.setNombreBusqueda(FacesUtils.checkString(txtNombreBusqueda));
            entity.setRazonRechazo(FacesUtils.checkString(txtRazonRechazo));
            entity.setUsuarioCreador(FacesUtils.checkString(txtUsuarioCreador));
            entity.setUsuarioUltimaModificacion(FacesUtils.checkString(
                    txtUsuarioUltimaModificacion));
            entity.setAnexosPqr((FacesUtils.checkLong(txtIdAnexoPqr_AnexosPqr) != null)
                ? businessDelegatorView.getAnexosPqr(FacesUtils.checkLong(
                        txtIdAnexoPqr_AnexosPqr)) : null);
            entity.setSolicitudPqr((FacesUtils.checkLong(
                    txtIdSolPqr_SolicitudPqr) != null)
                ? businessDelegatorView.getSolicitudPqr(FacesUtils.checkLong(
                        txtIdSolPqr_SolicitudPqr)) : null);
            businessDelegatorView.saveAnexosSolicitante(entity);
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
                Long idAnxSolicitante = new Long(selectedAnexosSolicitante.getIdAnxSolicitante());
                entity = businessDelegatorView.getAnexosSolicitante(idAnxSolicitante);
            }

            entity.setAdjuntoDocumento(FacesUtils.checkString(
                    txtAdjuntoDocumento));
            entity.setApruebaAnexo(FacesUtils.checkString(txtApruebaAnexo));
            entity.setDocumentoReal(FacesUtils.checkBlob(txtDocumentoReal));
            entity.setEstadoRegistro(FacesUtils.checkString(txtEstadoRegistro));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaUltimaModificacion(FacesUtils.checkDate(
                    txtFechaUltimaModificacion));
            entity.setNombreAnexo(FacesUtils.checkString(txtNombreAnexo));
            entity.setNombreBusqueda(FacesUtils.checkString(txtNombreBusqueda));
            entity.setRazonRechazo(FacesUtils.checkString(txtRazonRechazo));
            entity.setUsuarioCreador(FacesUtils.checkString(txtUsuarioCreador));
            entity.setUsuarioUltimaModificacion(FacesUtils.checkString(
                    txtUsuarioUltimaModificacion));
            entity.setAnexosPqr((FacesUtils.checkLong(txtIdAnexoPqr_AnexosPqr) != null)
                ? businessDelegatorView.getAnexosPqr(FacesUtils.checkLong(
                        txtIdAnexoPqr_AnexosPqr)) : null);
            entity.setSolicitudPqr((FacesUtils.checkLong(
                    txtIdSolPqr_SolicitudPqr) != null)
                ? businessDelegatorView.getSolicitudPqr(FacesUtils.checkLong(
                        txtIdSolPqr_SolicitudPqr)) : null);
            businessDelegatorView.updateAnexosSolicitante(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedAnexosSolicitante = (AnexosSolicitanteDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedAnexosSolicitante"));

            Long idAnxSolicitante = new Long(selectedAnexosSolicitante.getIdAnxSolicitante());
            entity = businessDelegatorView.getAnexosSolicitante(idAnxSolicitante);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idAnxSolicitante = FacesUtils.checkLong(txtIdAnxSolicitante);
            entity = businessDelegatorView.getAnexosSolicitante(idAnxSolicitante);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteAnexosSolicitante(entity);
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
            selectedAnexosSolicitante = (AnexosSolicitanteDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedAnexosSolicitante"));

            Long idAnxSolicitante = new Long(selectedAnexosSolicitante.getIdAnxSolicitante());
            entity = businessDelegatorView.getAnexosSolicitante(idAnxSolicitante);
            businessDelegatorView.deleteAnexosSolicitante(entity);
            data.remove(selectedAnexosSolicitante);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String adjuntoDocumento,
        String apruebaAnexo, Blob documentoReal, String estadoRegistro,
        Date fechaCreacion, Date fechaUltimaModificacion,
        Long idAnxSolicitante, String nombreAnexo, String nombreBusqueda,
        String razonRechazo, String usuarioCreador,
        String usuarioUltimaModificacion, Long idAnexoPqr_AnexosPqr,
        Long idSolPqr_SolicitudPqr) throws Exception {
        try {
            entity.setAdjuntoDocumento(FacesUtils.checkString(adjuntoDocumento));
            entity.setApruebaAnexo(FacesUtils.checkString(apruebaAnexo));
            entity.setDocumentoReal(FacesUtils.checkBlob(documentoReal));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaUltimaModificacion(FacesUtils.checkDate(
                    fechaUltimaModificacion));
            entity.setNombreAnexo(FacesUtils.checkString(nombreAnexo));
            entity.setNombreBusqueda(FacesUtils.checkString(nombreBusqueda));
            entity.setRazonRechazo(FacesUtils.checkString(razonRechazo));
            entity.setUsuarioCreador(FacesUtils.checkString(usuarioCreador));
            entity.setUsuarioUltimaModificacion(FacesUtils.checkString(
                    usuarioUltimaModificacion));
            businessDelegatorView.updateAnexosSolicitante(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("AnexosSolicitanteView").requestRender();
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

    public InputText getTxtApruebaAnexo() {
        return txtApruebaAnexo;
    }

    public void setTxtApruebaAnexo(InputText txtApruebaAnexo) {
        this.txtApruebaAnexo = txtApruebaAnexo;
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

    public InputText getTxtRazonRechazo() {
        return txtRazonRechazo;
    }

    public void setTxtRazonRechazo(InputText txtRazonRechazo) {
        this.txtRazonRechazo = txtRazonRechazo;
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

    public InputText getTxtIdAnexoPqr_AnexosPqr() {
        return txtIdAnexoPqr_AnexosPqr;
    }

    public void setTxtIdAnexoPqr_AnexosPqr(InputText txtIdAnexoPqr_AnexosPqr) {
        this.txtIdAnexoPqr_AnexosPqr = txtIdAnexoPqr_AnexosPqr;
    }

    public InputText getTxtIdSolPqr_SolicitudPqr() {
        return txtIdSolPqr_SolicitudPqr;
    }

    public void setTxtIdSolPqr_SolicitudPqr(InputText txtIdSolPqr_SolicitudPqr) {
        this.txtIdSolPqr_SolicitudPqr = txtIdSolPqr_SolicitudPqr;
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

    public InputText getTxtIdAnxSolicitante() {
        return txtIdAnxSolicitante;
    }

    public void setTxtIdAnxSolicitante(InputText txtIdAnxSolicitante) {
        this.txtIdAnxSolicitante = txtIdAnxSolicitante;
    }

    public List<AnexosSolicitanteDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataAnexosSolicitante();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<AnexosSolicitanteDTO> anexosSolicitanteDTO) {
        this.data = anexosSolicitanteDTO;
    }

    public AnexosSolicitanteDTO getSelectedAnexosSolicitante() {
        return selectedAnexosSolicitante;
    }

    public void setSelectedAnexosSolicitante(
        AnexosSolicitanteDTO anexosSolicitante) {
        this.selectedAnexosSolicitante = anexosSolicitante;
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
