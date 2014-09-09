package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.MotivoSolicitudDTO;
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
public class MotivoSolicitudView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtDescripcionMotSol;
    private String estadoRegistroSeleccionado;
    private InputText txtEstadoRegistro;
    private InputText txtUsuarioCreador;
    private InputText txtUsuarioUltimaModificacion;
    private InputText txtIdMotSol;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaUltimaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<MotivoSolicitudDTO> data;
    private MotivoSolicitudDTO selectedMotivoSolicitud;
    private MotivoSolicitud entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public MotivoSolicitudView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            MotivoSolicitudDTO motivoSolicitudDTO = (MotivoSolicitudDTO) e.getObject();

            if (txtDescripcionMotSol == null) {
                txtDescripcionMotSol = new InputText();
            }

            txtDescripcionMotSol.setValue(motivoSolicitudDTO.getDescripcionMotSol());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(motivoSolicitudDTO.getEstadoRegistro());

            if (txtUsuarioCreador == null) {
                txtUsuarioCreador = new InputText();
            }

            txtUsuarioCreador.setValue(motivoSolicitudDTO.getUsuarioCreador());

            if (txtUsuarioUltimaModificacion == null) {
                txtUsuarioUltimaModificacion = new InputText();
            }

            txtUsuarioUltimaModificacion.setValue(motivoSolicitudDTO.getUsuarioUltimaModificacion());

            if (txtIdMotSol == null) {
                txtIdMotSol = new InputText();
            }

            txtIdMotSol.setValue(motivoSolicitudDTO.getIdMotSol());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(motivoSolicitudDTO.getFechaCreacion());

            if (txtFechaUltimaModificacion == null) {
                txtFechaUltimaModificacion = new Calendar();
            }

            txtFechaUltimaModificacion.setValue(motivoSolicitudDTO.getFechaUltimaModificacion());

            Long idMotSol = FacesUtils.checkLong(txtIdMotSol);
            entity = businessDelegatorView.getMotivoSolicitud(idMotSol);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedMotivoSolicitud = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedMotivoSolicitud = null;

        if (txtDescripcionMotSol != null) {
            txtDescripcionMotSol.setValue(null);
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
            Long idMotSol = FacesUtils.checkLong(txtIdMotSol);
            entity = (idMotSol != null)
                ? businessDelegatorView.getMotivoSolicitud(idMotSol) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionMotSol.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtUsuarioCreador.setDisabled(false);
            txtUsuarioUltimaModificacion.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaUltimaModificacion.setDisabled(false);
            txtIdMotSol.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionMotSol.setValue(entity.getDescripcionMotSol());
            txtDescripcionMotSol.setDisabled(false);
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
            txtIdMotSol.setValue(entity.getIdMotSol());
            txtIdMotSol.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedMotivoSolicitud = (MotivoSolicitudDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedMotivoSolicitud"));
        txtDescripcionMotSol.setValue(selectedMotivoSolicitud.getDescripcionMotSol());
        txtDescripcionMotSol.setDisabled(false);
        txtEstadoRegistro.setValue(selectedMotivoSolicitud.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtFechaCreacion.setValue(selectedMotivoSolicitud.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaUltimaModificacion.setValue(selectedMotivoSolicitud.getFechaUltimaModificacion());
        txtFechaUltimaModificacion.setDisabled(false);
        txtUsuarioCreador.setValue(selectedMotivoSolicitud.getUsuarioCreador());
        txtUsuarioCreador.setDisabled(false);
        txtUsuarioUltimaModificacion.setValue(selectedMotivoSolicitud.getUsuarioUltimaModificacion());
        txtUsuarioUltimaModificacion.setDisabled(false);
        txtIdMotSol.setValue(selectedMotivoSolicitud.getIdMotSol());
        txtIdMotSol.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedMotivoSolicitud == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
          //  data = getData();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
        	
        	String descripcionMotSol=txtDescripcionMotSol.getValue().toString();
        	MotivoSolicitud motivoSolicitud=ObtenerMotSolicitud(descripcionMotSol);
        	
        	if(motivoSolicitud == null){
        		
        		if(!revizarCampos(descripcionMotSol)){
        			return "";
        			
        		}
        		
        		entity = new MotivoSolicitud();

                //Long idMotSol = FacesUtils.checkLong(txtIdMotSol);
                //entity.setIdMotSol(idMotSol);
                entity.setDescripcionMotSol(FacesUtils.checkString(txtDescripcionMotSol));
                entity.setEstadoRegistro(estadoRegistroSeleccionado);
                //Falta agregar usuario de sesion
                entity.setUsuarioCreador("Admin");
                entity.setFechaCreacion(new Date());
                entity.setUsuarioUltimaModificacion(null);
                entity.setFechaUltimaModificacion(null);

                businessDelegatorView.saveMotivoSolicitud(entity);
                FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
                action_clear();
                
        	} else {
				throw new Exception("Ya existe motivo");
        		
        	}            
           
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }
    
    
    private MotivoSolicitud ObtenerMotSolicitud(String descripcionMotSol) throws Exception {
		MotivoSolicitud entity = null;
		Object[] variables = { "descripcionMotSol", true, descripcionMotSol, "=" };
		List<MotivoSolicitud> motivoSolicituds = businessDelegatorView.findByCriteriaInMotivoSolicitud(variables, null, null);
				

		if (Utilities.validationsList(motivoSolicituds)) {
			entity = motivoSolicituds.get(0);
		}
		return entity;
	}
    
    public boolean revizarCampos(String descripcionMotSol) throws Exception {

		if (descripcionMotSol.equals("") || descripcionMotSol.trim().equals("")) {
			throw new Exception("Debe de ingresar una Descripcion");
		}

	/*	if (!Utilities.isOnlyLetters2(descripcionMotRecl)) {
			throw new Exception(
					"La descripcion ingresada solo debe de contener letras");
		}*/
		return true;
	}

    public String action_modify() {
        try {
        	
        	String descripcionMotSol=txtDescripcionMotSol.getValue().toString();
        	MotivoSolicitud motivoSolicitud=ObtenerMotSolicitud(descripcionMotSol);
        	
        	if(motivoSolicitud == null){
        		
        		if(!revizarCampos(descripcionMotSol)){
        			return "";
        			
        		}      

            entity.setDescripcionMotSol(FacesUtils.checkString(txtDescripcionMotSol));
            entity.setEstadoRegistro(estadoRegistroSeleccionado);
            entity.setUsuarioCreador(FacesUtils.checkString(txtUsuarioCreador));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            //Falta agregar usuario de sesion
            entity.setUsuarioUltimaModificacion("facturación");
            entity.setFechaUltimaModificacion(new Date());
            
            businessDelegatorView.updateMotivoSolicitud(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
          
            action_clear();
            
        	}else{
        		throw new Exception("Ya existe motivo");
        	}
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedMotivoSolicitud = (MotivoSolicitudDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedMotivoSolicitud"));

            Long idMotSol = new Long(selectedMotivoSolicitud.getIdMotSol());
            entity = businessDelegatorView.getMotivoSolicitud(idMotSol);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idMotSol = FacesUtils.checkLong(txtIdMotSol);
            entity = businessDelegatorView.getMotivoSolicitud(idMotSol);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteMotivoSolicitud(entity);
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
            selectedMotivoSolicitud = (MotivoSolicitudDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedMotivoSolicitud"));

            Long idMotSol = new Long(selectedMotivoSolicitud.getIdMotSol());
            entity = businessDelegatorView.getMotivoSolicitud(idMotSol);
            businessDelegatorView.deleteMotivoSolicitud(entity);
            data.remove(selectedMotivoSolicitud);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcionMotSol,
        String estadoRegistro, Date fechaCreacion,
        Date fechaUltimaModificacion, Long idMotSol, String usuarioCreador,
        String usuarioUltimaModificacion) throws Exception {
        try {
            entity.setDescripcionMotSol(FacesUtils.checkString(
                    descripcionMotSol));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaUltimaModificacion(FacesUtils.checkDate(
                    fechaUltimaModificacion));
            entity.setUsuarioCreador(FacesUtils.checkString(usuarioCreador));
            entity.setUsuarioUltimaModificacion(FacesUtils.checkString(
                    usuarioUltimaModificacion));
            businessDelegatorView.updateMotivoSolicitud(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("MotivoSolicitudView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcionMotSol() {
        return txtDescripcionMotSol;
    }

    public void setTxtDescripcionMotSol(InputText txtDescripcionMotSol) {
        this.txtDescripcionMotSol = txtDescripcionMotSol;
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

    public InputText getTxtIdMotSol() {
        return txtIdMotSol;
    }

    public void setTxtIdMotSol(InputText txtIdMotSol) {
        this.txtIdMotSol = txtIdMotSol;
    }

    public List<MotivoSolicitudDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataMotivoSolicitud();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<MotivoSolicitudDTO> motivoSolicitudDTO) {
        this.data = motivoSolicitudDTO;
    }

    public MotivoSolicitudDTO getSelectedMotivoSolicitud() {
        return selectedMotivoSolicitud;
    }

    public void setSelectedMotivoSolicitud(MotivoSolicitudDTO motivoSolicitud) {
        this.selectedMotivoSolicitud = motivoSolicitud;
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
