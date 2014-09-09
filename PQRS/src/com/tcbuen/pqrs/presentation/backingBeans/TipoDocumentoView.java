package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.TipoDocumentoDTO;
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
public class TipoDocumentoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtDescripcionTpDoc;
    private InputText txtEstadoRegistro;
    private InputText txtUsuarioCreador;
    private InputText txtUsuarioUltimaModificacion;
    private InputText txtIdTpDoc;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaUltimaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TipoDocumentoDTO> data;
    private TipoDocumentoDTO selectedTipoDocumento;
    private TipoDocumento entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
    private String estadoRegistroSeleccionado;

  	public TipoDocumentoView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            TipoDocumentoDTO tipoDocumentoDTO = (TipoDocumentoDTO) e.getObject();

            if (txtDescripcionTpDoc == null) {
                txtDescripcionTpDoc = new InputText();
            }

            txtDescripcionTpDoc.setValue(tipoDocumentoDTO.getDescripcionTpDoc());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(tipoDocumentoDTO.getEstadoRegistro());

            if (txtUsuarioCreador == null) {
                txtUsuarioCreador = new InputText();
            }

            txtUsuarioCreador.setValue(tipoDocumentoDTO.getUsuarioCreador());

            if (txtUsuarioUltimaModificacion == null) {
                txtUsuarioUltimaModificacion = new InputText();
            }

            txtUsuarioUltimaModificacion.setValue(tipoDocumentoDTO.getUsuarioUltimaModificacion());

            if (txtIdTpDoc == null) {
                txtIdTpDoc = new InputText();
            }

            txtIdTpDoc.setValue(tipoDocumentoDTO.getIdTpDoc());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(tipoDocumentoDTO.getFechaCreacion());

            if (txtFechaUltimaModificacion == null) {
                txtFechaUltimaModificacion = new Calendar();
            }

            txtFechaUltimaModificacion.setValue(tipoDocumentoDTO.getFechaUltimaModificacion());

            Long idTpDoc = FacesUtils.checkLong(txtIdTpDoc);
            entity = businessDelegatorView.getTipoDocumento(idTpDoc);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedTipoDocumento = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTipoDocumento = null;

        if (txtDescripcionTpDoc != null) {
            txtDescripcionTpDoc.setValue(null);
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
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
            Long idTpDoc = FacesUtils.checkLong(txtIdTpDoc);
            entity = (idTpDoc != null)
                ? businessDelegatorView.getTipoDocumento(idTpDoc) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionTpDoc.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtUsuarioCreador.setDisabled(false);
            txtUsuarioUltimaModificacion.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaUltimaModificacion.setDisabled(false);
            txtIdTpDoc.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionTpDoc.setValue(entity.getDescripcionTpDoc());
            txtDescripcionTpDoc.setDisabled(false);
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
            txtIdTpDoc.setValue(entity.getIdTpDoc());
            txtIdTpDoc.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTipoDocumento = (TipoDocumentoDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedTipoDocumento"));
        txtDescripcionTpDoc.setValue(selectedTipoDocumento.getDescripcionTpDoc());
        txtDescripcionTpDoc.setDisabled(false);
        txtEstadoRegistro.setValue(selectedTipoDocumento.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtFechaCreacion.setValue(selectedTipoDocumento.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaUltimaModificacion.setValue(selectedTipoDocumento.getFechaUltimaModificacion());
        txtFechaUltimaModificacion.setDisabled(false);
        txtUsuarioCreador.setValue(selectedTipoDocumento.getUsuarioCreador());
        txtUsuarioCreador.setDisabled(false);
        txtUsuarioUltimaModificacion.setValue(selectedTipoDocumento.getUsuarioUltimaModificacion());
        txtUsuarioUltimaModificacion.setDisabled(false);
        txtIdTpDoc.setValue(selectedTipoDocumento.getIdTpDoc());
        txtIdTpDoc.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTipoDocumento == null) && (entity == null)) {
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
        	
        	String descripcionTpDoc =txtDescripcionTpDoc.getValue().toString();
        	TipoDocumento tipoDocumento= ObtenerDocDescripcion(descripcionTpDoc);
        	
            if(tipoDocumento == null){
            	if(!revizarCampos(descripcionTpDoc)){
            		return "";
            	}
            	
            	entity = new TipoDocumento();

                //Long idTpDoc = FacesUtils.checkLong(txtIdTpDoc);
                //entity.setIdTpDoc(idTpDoc);
                
                entity.setDescripcionTpDoc(FacesUtils.checkString(txtDescripcionTpDoc));
                String estado = (estadoRegistroSeleccionado.equals("Activo"))?"A":"I";
    			entity.setEstadoRegistro(estado);
                entity.setFechaCreacion(new Date());
                entity.setFechaUltimaModificacion(null);
                entity.setUsuarioCreador("Admin");
                entity.setUsuarioUltimaModificacion(null);
                businessDelegatorView.saveTipoDocumento(entity);
                FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
                action_clear();
            }else{
            	throw new Exception("Ya existe Tipo de documento");
            }
        	
        	
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    private TipoDocumento ObtenerDocDescripcion(String descripcionTpDoc)
			throws Exception {
		TipoDocumento entity = null;
		Object[] variables = { "descripcionTpDoc", true, descripcionTpDoc, "=" };
		List<TipoDocumento> tipoDocumentos = businessDelegatorView
				.findByCriteriaInTipoDocumento(variables, null, null);

		if (Utilities.validationsList(tipoDocumentos)) {
			entity = tipoDocumentos.get(0);
		}
		return entity;
	}

    public boolean revizarCampos(String descripcionTpDoc) throws Exception {

		if (descripcionTpDoc.equals("") || descripcionTpDoc.trim().equals("")) {
			throw new Exception("Debe de ingresar una Descripcion");
		}

		if (!Utilities.isOnlyLetters2(descripcionTpDoc)) {
			throw new Exception(
					"La descripcion ingresada solo debe de contener letras");
		}
		return true;

	}
    
    public String action_modify() {
        try {
        	String descripcionTpDoc =txtDescripcionTpDoc.getValue().toString();
        	TipoDocumento tipoDocumento= ObtenerDocDescripcion(descripcionTpDoc);
        	
            if(tipoDocumento == null){
            	if(!revizarCampos(descripcionTpDoc)){
            		return "";
            	}

            entity.setDescripcionTpDoc(FacesUtils.checkString(txtDescripcionTpDoc));
            String estado = (estadoRegistroSeleccionado.equals("Activo"))?"A":"I";
			entity.setEstadoRegistro(estado);
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setFechaUltimaModificacion(new Date());
            entity.setUsuarioCreador(FacesUtils.checkString(txtUsuarioCreador));
            entity.setUsuarioUltimaModificacion("Admin-1");
            businessDelegatorView.updateTipoDocumento(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
            action_clear();
            
            }else{
            	throw new Exception("Ya existe Tipo de documento");
            }
        	
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTipoDocumento = (TipoDocumentoDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedTipoDocumento"));

            Long idTpDoc = new Long(selectedTipoDocumento.getIdTpDoc());
            entity = businessDelegatorView.getTipoDocumento(idTpDoc);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idTpDoc = FacesUtils.checkLong(txtIdTpDoc);
            entity = businessDelegatorView.getTipoDocumento(idTpDoc);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTipoDocumento(entity);
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
            selectedTipoDocumento = (TipoDocumentoDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedTipoDocumento"));

            Long idTpDoc = new Long(selectedTipoDocumento.getIdTpDoc());
            entity = businessDelegatorView.getTipoDocumento(idTpDoc);
            businessDelegatorView.deleteTipoDocumento(entity);
            data.remove(selectedTipoDocumento);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcionTpDoc,
        String estadoRegistro, Date fechaCreacion,
        Date fechaUltimaModificacion, Long idTpDoc, String usuarioCreador,
        String usuarioUltimaModificacion) throws Exception {
        try {
            entity.setDescripcionTpDoc(FacesUtils.checkString(descripcionTpDoc));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaUltimaModificacion(FacesUtils.checkDate(
                    fechaUltimaModificacion));
            entity.setUsuarioCreador(FacesUtils.checkString(usuarioCreador));
            entity.setUsuarioUltimaModificacion(FacesUtils.checkString(
                    usuarioUltimaModificacion));
            businessDelegatorView.updateTipoDocumento(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TipoDocumentoView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcionTpDoc() {
        return txtDescripcionTpDoc;
    }

    public void setTxtDescripcionTpDoc(InputText txtDescripcionTpDoc) {
        this.txtDescripcionTpDoc = txtDescripcionTpDoc;
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

    public InputText getTxtIdTpDoc() {
        return txtIdTpDoc;
    }

    public void setTxtIdTpDoc(InputText txtIdTpDoc) {
        this.txtIdTpDoc = txtIdTpDoc;
    }

    public List<TipoDocumentoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTipoDocumento();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TipoDocumentoDTO> tipoDocumentoDTO) {
        this.data = tipoDocumentoDTO;
    }

    public TipoDocumentoDTO getSelectedTipoDocumento() {
        return selectedTipoDocumento;
    }

    public void setSelectedTipoDocumento(TipoDocumentoDTO tipoDocumento) {
        this.selectedTipoDocumento = tipoDocumento;
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
