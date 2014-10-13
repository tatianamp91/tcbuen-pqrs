package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.SolicitudPqrDTO;
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
import javax.faces.model.SelectItem;


/**
 * @author Zathura Code Generator http://code.google.com/p/zathura
 *
 */
@ManagedBean
@ViewScoped
public class SolicitudPqrView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtDescripcionCaso;
    private InputText txtNombreAgenciaAduana;
    private InputText txtNombreCliente;
    private InputText txtNumeroRadicacion;
    private InputText txtSolicitudARealizar;
    private InputText txtUsuarioCreador;
    private InputText txtUsuarioUltimaModificacion;
    private InputText txtIdInfoSolicitante_InfoSolicitante;
    private InputText txtIdTpEstPqr_TipoEstadoPqr;
    private InputText txtIdTpSolPqr_TipoSolicitudPqr;
    private InputText txtIdSolPqr;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaUltimaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<SolicitudPqrDTO> data;
    private SolicitudPqrDTO selectedSolicitudPqr;
    private SolicitudPqr entity;
    private String motSolicitud;
    private List<String> motivoSolicitud;
    private String motReclamacion;
    private List<String> motivoReclamacion;
    private TipoSolicitudPqr tipoSolicitudPqr;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public SolicitudPqrView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            SolicitudPqrDTO solicitudPqrDTO = (SolicitudPqrDTO) e.getObject();

            if (txtDescripcionCaso == null) {
                txtDescripcionCaso = new InputText();
            }

            txtDescripcionCaso.setValue(solicitudPqrDTO.getDescripcionCaso());

            if (txtNombreAgenciaAduana == null) {
                txtNombreAgenciaAduana = new InputText();
            }

            txtNombreAgenciaAduana.setValue(solicitudPqrDTO.getNombreAgenciaAduana());

            if (txtNombreCliente == null) {
                txtNombreCliente = new InputText();
            }

            txtNombreCliente.setValue(solicitudPqrDTO.getNombreCliente());

            if (txtNumeroRadicacion == null) {
                txtNumeroRadicacion = new InputText();
            }

            txtNumeroRadicacion.setValue(solicitudPqrDTO.getNumeroRadicacion());

            if (txtSolicitudARealizar == null) {
                txtSolicitudARealizar = new InputText();
            }

            txtSolicitudARealizar.setValue(solicitudPqrDTO.getSolicitudARealizar());

            if (txtUsuarioCreador == null) {
                txtUsuarioCreador = new InputText();
            }

            txtUsuarioCreador.setValue(solicitudPqrDTO.getUsuarioCreador());

            if (txtUsuarioUltimaModificacion == null) {
                txtUsuarioUltimaModificacion = new InputText();
            }

            txtUsuarioUltimaModificacion.setValue(solicitudPqrDTO.getUsuarioUltimaModificacion());

            if (txtIdInfoSolicitante_InfoSolicitante == null) {
                txtIdInfoSolicitante_InfoSolicitante = new InputText();
            }

            txtIdInfoSolicitante_InfoSolicitante.setValue(solicitudPqrDTO.getIdInfoSolicitante_InfoSolicitante());

            if (txtIdTpEstPqr_TipoEstadoPqr == null) {
                txtIdTpEstPqr_TipoEstadoPqr = new InputText();
            }

            txtIdTpEstPqr_TipoEstadoPqr.setValue(solicitudPqrDTO.getIdTpEstPqr_TipoEstadoPqr());

            if (txtIdTpSolPqr_TipoSolicitudPqr == null) {
                txtIdTpSolPqr_TipoSolicitudPqr = new InputText();
            }

            txtIdTpSolPqr_TipoSolicitudPqr.setValue(solicitudPqrDTO.getIdTpSolPqr_TipoSolicitudPqr());

            if (txtIdSolPqr == null) {
                txtIdSolPqr = new InputText();
            }

            txtIdSolPqr.setValue(solicitudPqrDTO.getIdSolPqr());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(solicitudPqrDTO.getFechaCreacion());

            if (txtFechaUltimaModificacion == null) {
                txtFechaUltimaModificacion = new Calendar();
            }

            txtFechaUltimaModificacion.setValue(solicitudPqrDTO.getFechaUltimaModificacion());

            Long idSolPqr = FacesUtils.checkLong(txtIdSolPqr);
            entity = businessDelegatorView.getSolicitudPqr(idSolPqr);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedSolicitudPqr = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedSolicitudPqr = null;

        if (txtDescripcionCaso != null) {
            txtDescripcionCaso.setValue(null);
            txtDescripcionCaso.setDisabled(true);
        }

        if (txtNombreAgenciaAduana != null) {
            txtNombreAgenciaAduana.setValue(null);
            txtNombreAgenciaAduana.setDisabled(true);
        }

        if (txtNombreCliente != null) {
            txtNombreCliente.setValue(null);
            txtNombreCliente.setDisabled(true);
        }

        if (txtNumeroRadicacion != null) {
            txtNumeroRadicacion.setValue(null);
            txtNumeroRadicacion.setDisabled(true);
        }

        if (txtSolicitudARealizar != null) {
            txtSolicitudARealizar.setValue(null);
            txtSolicitudARealizar.setDisabled(true);
        }

        if (txtUsuarioCreador != null) {
            txtUsuarioCreador.setValue(null);
            txtUsuarioCreador.setDisabled(true);
        }

        if (txtUsuarioUltimaModificacion != null) {
            txtUsuarioUltimaModificacion.setValue(null);
            txtUsuarioUltimaModificacion.setDisabled(true);
        }

        if (txtIdInfoSolicitante_InfoSolicitante != null) {
            txtIdInfoSolicitante_InfoSolicitante.setValue(null);
            txtIdInfoSolicitante_InfoSolicitante.setDisabled(true);
        }

        if (txtIdTpEstPqr_TipoEstadoPqr != null) {
            txtIdTpEstPqr_TipoEstadoPqr.setValue(null);
            txtIdTpEstPqr_TipoEstadoPqr.setDisabled(true);
        }

        if (txtIdTpSolPqr_TipoSolicitudPqr != null) {
            txtIdTpSolPqr_TipoSolicitudPqr.setValue(null);
            txtIdTpSolPqr_TipoSolicitudPqr.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtFechaUltimaModificacion != null) {
            txtFechaUltimaModificacion.setValue(null);
            txtFechaUltimaModificacion.setDisabled(true);
        }

        if (txtIdSolPqr != null) {
            txtIdSolPqr.setValue(null);
            txtIdSolPqr.setDisabled(false);
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
            Long idSolPqr = FacesUtils.checkLong(txtIdSolPqr);
            entity = (idSolPqr != null)
                ? businessDelegatorView.getSolicitudPqr(idSolPqr) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionCaso.setDisabled(false);
            txtNombreAgenciaAduana.setDisabled(false);
            txtNombreCliente.setDisabled(false);
            txtNumeroRadicacion.setDisabled(false);
            txtSolicitudARealizar.setDisabled(false);
            txtUsuarioCreador.setDisabled(false);
            txtUsuarioUltimaModificacion.setDisabled(false);
            txtIdInfoSolicitante_InfoSolicitante.setDisabled(false);
            txtIdTpEstPqr_TipoEstadoPqr.setDisabled(false);
            txtIdTpSolPqr_TipoSolicitudPqr.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaUltimaModificacion.setDisabled(false);
            txtIdSolPqr.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionCaso.setValue(entity.getDescripcionCaso());
            txtDescripcionCaso.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaUltimaModificacion.setValue(entity.getFechaUltimaModificacion());
            txtFechaUltimaModificacion.setDisabled(false);
            txtNombreAgenciaAduana.setValue(entity.getNombreAgenciaAduana());
            txtNombreAgenciaAduana.setDisabled(false);
            txtNombreCliente.setValue(entity.getNombreCliente());
            txtNombreCliente.setDisabled(false);
            txtNumeroRadicacion.setValue(entity.getNumeroRadicacion());
            txtNumeroRadicacion.setDisabled(false);
            txtSolicitudARealizar.setValue(entity.getSolicitudARealizar());
            txtSolicitudARealizar.setDisabled(false);
            txtUsuarioCreador.setValue(entity.getUsuarioCreador());
            txtUsuarioCreador.setDisabled(false);
            txtUsuarioUltimaModificacion.setValue(entity.getUsuarioUltimaModificacion());
            txtUsuarioUltimaModificacion.setDisabled(false);
            txtIdInfoSolicitante_InfoSolicitante.setValue(entity.getInfoSolicitante()
                                                                .getIdInfoSolicitante());
            txtIdInfoSolicitante_InfoSolicitante.setDisabled(false);
            txtIdTpEstPqr_TipoEstadoPqr.setValue(entity.getTipoEstadoPqr()
                                                       .getIdTpEstPqr());
            txtIdTpEstPqr_TipoEstadoPqr.setDisabled(false);
            txtIdTpSolPqr_TipoSolicitudPqr.setValue(entity.getTipoSolicitudPqr()
                                                          .getIdTpSolPqr());
            txtIdTpSolPqr_TipoSolicitudPqr.setDisabled(false);
            txtIdSolPqr.setValue(entity.getIdSolPqr());
            txtIdSolPqr.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedSolicitudPqr = (SolicitudPqrDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedSolicitudPqr"));
        txtDescripcionCaso.setValue(selectedSolicitudPqr.getDescripcionCaso());
        txtDescripcionCaso.setDisabled(false);
        txtFechaCreacion.setValue(selectedSolicitudPqr.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaUltimaModificacion.setValue(selectedSolicitudPqr.getFechaUltimaModificacion());
        txtFechaUltimaModificacion.setDisabled(false);
        txtNombreAgenciaAduana.setValue(selectedSolicitudPqr.getNombreAgenciaAduana());
        txtNombreAgenciaAduana.setDisabled(false);
        txtNombreCliente.setValue(selectedSolicitudPqr.getNombreCliente());
        txtNombreCliente.setDisabled(false);
        txtNumeroRadicacion.setValue(selectedSolicitudPqr.getNumeroRadicacion());
        txtNumeroRadicacion.setDisabled(false);
        txtSolicitudARealizar.setValue(selectedSolicitudPqr.getSolicitudARealizar());
        txtSolicitudARealizar.setDisabled(false);
        txtUsuarioCreador.setValue(selectedSolicitudPqr.getUsuarioCreador());
        txtUsuarioCreador.setDisabled(false);
        txtUsuarioUltimaModificacion.setValue(selectedSolicitudPqr.getUsuarioUltimaModificacion());
        txtUsuarioUltimaModificacion.setDisabled(false);
        txtIdInfoSolicitante_InfoSolicitante.setValue(selectedSolicitudPqr.getIdInfoSolicitante_InfoSolicitante());
        txtIdInfoSolicitante_InfoSolicitante.setDisabled(false);
        txtIdTpEstPqr_TipoEstadoPqr.setValue(selectedSolicitudPqr.getIdTpEstPqr_TipoEstadoPqr());
        txtIdTpEstPqr_TipoEstadoPqr.setDisabled(false);
        txtIdTpSolPqr_TipoSolicitudPqr.setValue(selectedSolicitudPqr.getIdTpSolPqr_TipoSolicitudPqr());
        txtIdTpSolPqr_TipoSolicitudPqr.setDisabled(false);
        txtIdSolPqr.setValue(selectedSolicitudPqr.getIdSolPqr());
        txtIdSolPqr.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedSolicitudPqr == null) && (entity == null)) {
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
            entity = new SolicitudPqr();

            Long idSolPqr = FacesUtils.checkLong(txtIdSolPqr);

            entity.setDescripcionCaso(FacesUtils.checkString(txtDescripcionCaso).toLowerCase());
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaUltimaModificacion(FacesUtils.checkDate(
                    txtFechaUltimaModificacion));
            entity.setIdSolPqr(idSolPqr);
            entity.setNombreAgenciaAduana(FacesUtils.checkString(
                    txtNombreAgenciaAduana).toLowerCase());
            entity.setNombreCliente(FacesUtils.checkString(txtNombreCliente).toLowerCase());
            entity.setNumeroRadicacion(FacesUtils.checkString(
                    txtNumeroRadicacion));
            entity.setSolicitudARealizar(FacesUtils.checkString(
                    txtSolicitudARealizar));
            entity.setUsuarioCreador(FacesUtils.checkString(txtUsuarioCreador).toLowerCase());
            entity.setUsuarioUltimaModificacion(FacesUtils.checkString(
                    txtUsuarioUltimaModificacion));
            entity.setInfoSolicitante((FacesUtils.checkLong(
                    txtIdInfoSolicitante_InfoSolicitante) != null)
                ? businessDelegatorView.getInfoSolicitante(FacesUtils.checkLong(
                        txtIdInfoSolicitante_InfoSolicitante)) : null);
            entity.setTipoEstadoPqr((FacesUtils.checkLong(
                    txtIdTpEstPqr_TipoEstadoPqr) != null)
                ? businessDelegatorView.getTipoEstadoPqr(FacesUtils.checkLong(
                        txtIdTpEstPqr_TipoEstadoPqr)) : null);
            entity.setTipoSolicitudPqr((FacesUtils.checkLong(
                    txtIdTpSolPqr_TipoSolicitudPqr) != null)
                ? businessDelegatorView.getTipoSolicitudPqr(
                    FacesUtils.checkLong(txtIdTpSolPqr_TipoSolicitudPqr)) : null);
            businessDelegatorView.saveSolicitudPqr(entity);
            FacesUtils.addInfoMessage("Se guardo la solicitud exitosamente");
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
                Long idSolPqr = new Long(selectedSolicitudPqr.getIdSolPqr());
                entity = businessDelegatorView.getSolicitudPqr(idSolPqr);
            }

            entity.setDescripcionCaso(FacesUtils.checkString(txtDescripcionCaso).toLowerCase());
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaUltimaModificacion(FacesUtils.checkDate(
                    txtFechaUltimaModificacion));
            entity.setNombreAgenciaAduana(FacesUtils.checkString(
                    txtNombreAgenciaAduana).toLowerCase());
            entity.setNombreCliente(FacesUtils.checkString(txtNombreCliente).toLowerCase());
            entity.setNumeroRadicacion(FacesUtils.checkString(
                    txtNumeroRadicacion));
            entity.setSolicitudARealizar(FacesUtils.checkString(
                    txtSolicitudARealizar));
            entity.setUsuarioCreador(FacesUtils.checkString(txtUsuarioCreador));
            entity.setUsuarioUltimaModificacion(FacesUtils.checkString(
                    txtUsuarioUltimaModificacion));
            entity.setInfoSolicitante((FacesUtils.checkLong(
                    txtIdInfoSolicitante_InfoSolicitante) != null)
                ? businessDelegatorView.getInfoSolicitante(FacesUtils.checkLong(
                        txtIdInfoSolicitante_InfoSolicitante)) : null);
            entity.setTipoEstadoPqr((FacesUtils.checkLong(
                    txtIdTpEstPqr_TipoEstadoPqr) != null)
                ? businessDelegatorView.getTipoEstadoPqr(FacesUtils.checkLong(
                        txtIdTpEstPqr_TipoEstadoPqr)) : null);
            entity.setTipoSolicitudPqr((FacesUtils.checkLong(
                    txtIdTpSolPqr_TipoSolicitudPqr) != null)
                ? businessDelegatorView.getTipoSolicitudPqr(
                    FacesUtils.checkLong(txtIdTpSolPqr_TipoSolicitudPqr)) : null);
            businessDelegatorView.updateSolicitudPqr(entity);
            FacesUtils.addInfoMessage("Se modifico la solicitud exitosamente");
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedSolicitudPqr = (SolicitudPqrDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedSolicitudPqr"));

            Long idSolPqr = new Long(selectedSolicitudPqr.getIdSolPqr());
            entity = businessDelegatorView.getSolicitudPqr(idSolPqr);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idSolPqr = FacesUtils.checkLong(txtIdSolPqr);
            entity = businessDelegatorView.getSolicitudPqr(idSolPqr);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteSolicitudPqr(entity);
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
            selectedSolicitudPqr = (SolicitudPqrDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedSolicitudPqr"));

            Long idSolPqr = new Long(selectedSolicitudPqr.getIdSolPqr());
            entity = businessDelegatorView.getSolicitudPqr(idSolPqr);
            businessDelegatorView.deleteSolicitudPqr(entity);
            data.remove(selectedSolicitudPqr);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcionCaso,
        Date fechaCreacion, Date fechaUltimaModificacion, Long idSolPqr,
        String nombreAgenciaAduana, String nombreCliente,
        String numeroRadicacion, String solicitudARealizar,
        String usuarioCreador, String usuarioUltimaModificacion,
        Long idInfoSolicitante_InfoSolicitante, Long idTpEstPqr_TipoEstadoPqr,
        Long idTpSolPqr_TipoSolicitudPqr) throws Exception {
        try {
            entity.setDescripcionCaso(FacesUtils.checkString(descripcionCaso));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaUltimaModificacion(FacesUtils.checkDate(
                    fechaUltimaModificacion));
            entity.setNombreAgenciaAduana(FacesUtils.checkString(
                    nombreAgenciaAduana));
            entity.setNombreCliente(FacesUtils.checkString(nombreCliente));
            entity.setNumeroRadicacion(FacesUtils.checkString(numeroRadicacion));
            entity.setSolicitudARealizar(FacesUtils.checkString(
                    solicitudARealizar));
            entity.setUsuarioCreador(FacesUtils.checkString(usuarioCreador));
            entity.setUsuarioUltimaModificacion(FacesUtils.checkString(
                    usuarioUltimaModificacion));
            businessDelegatorView.updateSolicitudPqr(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("SolicitudPqrView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcionCaso() {
        return txtDescripcionCaso;
    }

    public void setTxtDescripcionCaso(InputText txtDescripcionCaso) {
        this.txtDescripcionCaso = txtDescripcionCaso;
    }

    public InputText getTxtNombreAgenciaAduana() {
        return txtNombreAgenciaAduana;
    }

    public void setTxtNombreAgenciaAduana(InputText txtNombreAgenciaAduana) {
        this.txtNombreAgenciaAduana = txtNombreAgenciaAduana;
    }

    public InputText getTxtNombreCliente() {
        return txtNombreCliente;
    }

    public void setTxtNombreCliente(InputText txtNombreCliente) {
        this.txtNombreCliente = txtNombreCliente;
    }

    public InputText getTxtNumeroRadicacion() {
        return txtNumeroRadicacion;
    }

    public void setTxtNumeroRadicacion(InputText txtNumeroRadicacion) {
        this.txtNumeroRadicacion = txtNumeroRadicacion;
    }

    public InputText getTxtSolicitudARealizar() {
        return txtSolicitudARealizar;
    }

    public void setTxtSolicitudARealizar(InputText txtSolicitudARealizar) {
        this.txtSolicitudARealizar = txtSolicitudARealizar;
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

    public InputText getTxtIdInfoSolicitante_InfoSolicitante() {
        return txtIdInfoSolicitante_InfoSolicitante;
    }

    public void setTxtIdInfoSolicitante_InfoSolicitante(
        InputText txtIdInfoSolicitante_InfoSolicitante) {
        this.txtIdInfoSolicitante_InfoSolicitante = txtIdInfoSolicitante_InfoSolicitante;
    }

    public InputText getTxtIdTpEstPqr_TipoEstadoPqr() {
        return txtIdTpEstPqr_TipoEstadoPqr;
    }

    public void setTxtIdTpEstPqr_TipoEstadoPqr(
        InputText txtIdTpEstPqr_TipoEstadoPqr) {
        this.txtIdTpEstPqr_TipoEstadoPqr = txtIdTpEstPqr_TipoEstadoPqr;
    }

    public InputText getTxtIdTpSolPqr_TipoSolicitudPqr() {
        return txtIdTpSolPqr_TipoSolicitudPqr;
    }

    public void setTxtIdTpSolPqr_TipoSolicitudPqr(
        InputText txtIdTpSolPqr_TipoSolicitudPqr) {
        this.txtIdTpSolPqr_TipoSolicitudPqr = txtIdTpSolPqr_TipoSolicitudPqr;
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

    public InputText getTxtIdSolPqr() {
        return txtIdSolPqr;
    }

    public void setTxtIdSolPqr(InputText txtIdSolPqr) {
        this.txtIdSolPqr = txtIdSolPqr;
    }

    public List<SolicitudPqrDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataSolicitudPqr();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<SolicitudPqrDTO> solicitudPqrDTO) {
        this.data = solicitudPqrDTO;
    }

    public SolicitudPqrDTO getSelectedSolicitudPqr() {
        return selectedSolicitudPqr;
    }

    public void setSelectedSolicitudPqr(SolicitudPqrDTO solicitudPqr) {
        this.selectedSolicitudPqr = solicitudPqr;
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

	public String getMotSolicitud() {
		return motSolicitud;
	}

	public void setMotSolicitud(String motSolicitud) {
		this.motSolicitud = motSolicitud;
	}

	public List<String> getMotivoSolicitud() {
		try{
			motivoSolicitud = new ArrayList<String>();
			List<MotivoSolicitud> motSol = businessDelegatorView.consultarMotSolXTipoPqr(tipoSolicitudPqr);
			for (MotivoSolicitud motSolicitud : motSol) {
				motivoSolicitud.add(motSolicitud.getDescripcionMotSol());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return motivoSolicitud;
	}

	public void setMotivoSolicitud(List<String> motivoSolicitud) {
		this.motivoSolicitud = motivoSolicitud;
	}

	public String getMotReclamacion() {
		return motReclamacion;
	}

	public void setMotReclamacion(String motReclamacion) {
		this.motReclamacion = motReclamacion;
	}

	public List<String> getMotivoReclamacion() {
		try{
			motivoReclamacion = new ArrayList<String>();
			List<MotivoReclamacion> motRecl = businessDelegatorView.consultarMotReclXTipoPqr(tipoSolicitudPqr);
			for (MotivoReclamacion motReclamacion : motRecl) {
				motivoReclamacion.add(motReclamacion.getDescripcionMotRecl());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return motivoReclamacion;
	}

	public void setMotivoReclamacion(List<String> motivoReclamacion) {
		this.motivoReclamacion = motivoReclamacion;
	}

	public TipoSolicitudPqr getTipoSolicitudPqr() {
		return tipoSolicitudPqr;
	}

	public void setTipoSolicitudPqr(TipoSolicitudPqr tipoSolicitudPqr) {
		this.tipoSolicitudPqr = tipoSolicitudPqr;
	}
}
