package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.MotivoReclamacionDTO;
import com.tcbuen.pqrs.presentation.businessDelegate.*;
import com.tcbuen.pqrs.utilities.*;

import org.hibernate.validator.util.GetAnnotationParameter;
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
public class MotivoReclamacionView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtDescripcionMotRecl;
    private String estadoRegistroSeleccionado;
    private InputText txtEstadoRegistro;
    private InputText txtUsuarioCreador;
    private InputText txtUsuarioUltimaModificacion;
    private InputText txtIdMotRecl;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaUltimaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<MotivoReclamacionDTO> data;
    private MotivoReclamacionDTO selectedMotivoReclamacion;
    private MotivoReclamacion entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public MotivoReclamacionView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            MotivoReclamacionDTO motivoReclamacionDTO = (MotivoReclamacionDTO) e.getObject();

            if (txtDescripcionMotRecl == null) {
                txtDescripcionMotRecl = new InputText();
            }

            txtDescripcionMotRecl.setValue(motivoReclamacionDTO.getDescripcionMotRecl());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(motivoReclamacionDTO.getEstadoRegistro());

            if (txtUsuarioCreador == null) {
                txtUsuarioCreador = new InputText();
            }

            txtUsuarioCreador.setValue(motivoReclamacionDTO.getUsuarioCreador());

            if (txtUsuarioUltimaModificacion == null) {
                txtUsuarioUltimaModificacion = new InputText();
            }

            txtUsuarioUltimaModificacion.setValue(motivoReclamacionDTO.getUsuarioUltimaModificacion());

            if (txtIdMotRecl == null) {
                txtIdMotRecl = new InputText();
            }

            txtIdMotRecl.setValue(motivoReclamacionDTO.getIdMotRecl());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(motivoReclamacionDTO.getFechaCreacion());

            if (txtFechaUltimaModificacion == null) {
                txtFechaUltimaModificacion = new Calendar();
            }

            txtFechaUltimaModificacion.setValue(motivoReclamacionDTO.getFechaUltimaModificacion());

            Long idMotRecl = FacesUtils.checkLong(txtIdMotRecl);
            entity = businessDelegatorView.getMotivoReclamacion(idMotRecl);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedMotivoReclamacion = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedMotivoReclamacion = null;

        if (txtDescripcionMotRecl != null) {
            txtDescripcionMotRecl.setValue(null);
        }

        if(estadoRegistroSeleccionado != null){
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
            Long idMotRecl = FacesUtils.checkLong(txtIdMotRecl);
            entity = (idMotRecl != null)
                ? businessDelegatorView.getMotivoReclamacion(idMotRecl) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionMotRecl.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtUsuarioCreador.setDisabled(false);
            txtUsuarioUltimaModificacion.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaUltimaModificacion.setDisabled(false);
            txtIdMotRecl.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionMotRecl.setValue(entity.getDescripcionMotRecl());
            txtDescripcionMotRecl.setDisabled(false);
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
            txtIdMotRecl.setValue(entity.getIdMotRecl());
            txtIdMotRecl.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedMotivoReclamacion = (MotivoReclamacionDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedMotivoReclamacion"));
        txtDescripcionMotRecl.setValue(selectedMotivoReclamacion.getDescripcionMotRecl());
        txtDescripcionMotRecl.setDisabled(false);
        txtEstadoRegistro.setValue(selectedMotivoReclamacion.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtFechaCreacion.setValue(selectedMotivoReclamacion.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaUltimaModificacion.setValue(selectedMotivoReclamacion.getFechaUltimaModificacion());
        txtFechaUltimaModificacion.setDisabled(false);
        txtUsuarioCreador.setValue(selectedMotivoReclamacion.getUsuarioCreador());
        txtUsuarioCreador.setDisabled(false);
        txtUsuarioUltimaModificacion.setValue(selectedMotivoReclamacion.getUsuarioUltimaModificacion());
        txtUsuarioUltimaModificacion.setDisabled(false);
        txtIdMotRecl.setValue(selectedMotivoReclamacion.getIdMotRecl());
        txtIdMotRecl.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedMotivoReclamacion == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        //    data = getData();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {     	
        	       	
			String descripcionMotRecl = txtDescripcionMotRecl.getValue()
					.toString();
			MotivoReclamacion motReclamacion = ObtenerMotReclamacion(descripcionMotRecl);

			if (motReclamacion == null) {

				if (!revizarCampos(descripcionMotRecl)) {
					return "";

				}

				entity = new MotivoReclamacion();
				// Long idMotRecl = FacesUtils.checkLong(txtIdMotRecl);
				// entity.setIdMotRecl(idMotRecl);
				entity.setDescripcionMotRecl(FacesUtils
						.checkString(txtDescripcionMotRecl));
				entity.setEstadoRegistro(estadoRegistroSeleccionado);
				// Falta agregar usuario de sesion
				entity.setUsuarioCreador("Admin");
				entity.setFechaCreacion(new Date());
				entity.setUsuarioUltimaModificacion(null);
				entity.setFechaUltimaModificacion(null);

				businessDelegatorView.saveMotivoReclamacion(entity);
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
    
    private MotivoReclamacion ObtenerMotReclamacion(String descripcionMotRecl) throws Exception {
		MotivoReclamacion entity = null;
		Object[] variables = { "descripcionMotRecl", true, descripcionMotRecl, "=" };
		List<MotivoReclamacion> motivoReclamacions = businessDelegatorView.findByCriteriaInMotivoReclamacion(variables, null, null);
				

		if (Utilities.validationsList(motivoReclamacions)) {
			entity = motivoReclamacions.get(0);
		}
		return entity;
	}
    
    public boolean revizarCampos(String descripcionMotRecl) throws Exception {

		if (descripcionMotRecl.equals("") || descripcionMotRecl.trim().equals("")) {
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
                    	
        	String descripcionMotRecl = txtDescripcionMotRecl.getValue().toString();
        	MotivoReclamacion motReclamacion= ObtenerMotReclamacion(descripcionMotRecl);
        	
            if(motReclamacion == null){
            	
            	if(!revizarCampos(descripcionMotRecl)){
            		return "";
            		
            	}
            entity.setDescripcionMotRecl(FacesUtils.checkString(txtDescripcionMotRecl));
            entity.setEstadoRegistro(estadoRegistroSeleccionado);
            entity.setUsuarioCreador(FacesUtils.checkString(txtUsuarioCreador));
            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            //Falta agregar usuario de sesion
            entity.setUsuarioUltimaModificacion("Facturación");
            entity.setFechaUltimaModificacion(new Date());
           
            businessDelegatorView.updateMotivoReclamacion(entity);
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
            selectedMotivoReclamacion = (MotivoReclamacionDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedMotivoReclamacion"));

            Long idMotRecl = new Long(selectedMotivoReclamacion.getIdMotRecl());
            entity = businessDelegatorView.getMotivoReclamacion(idMotRecl);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idMotRecl = FacesUtils.checkLong(txtIdMotRecl);
            entity = businessDelegatorView.getMotivoReclamacion(idMotRecl);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteMotivoReclamacion(entity);
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
            selectedMotivoReclamacion = (MotivoReclamacionDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedMotivoReclamacion"));

            Long idMotRecl = new Long(selectedMotivoReclamacion.getIdMotRecl());
            entity = businessDelegatorView.getMotivoReclamacion(idMotRecl);
            businessDelegatorView.deleteMotivoReclamacion(entity);
            data.remove(selectedMotivoReclamacion);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcionMotRecl,
        String estadoRegistro, Date fechaCreacion,
        Date fechaUltimaModificacion, Long idMotRecl, String usuarioCreador,
        String usuarioUltimaModificacion) throws Exception {
        try {
            entity.setDescripcionMotRecl(FacesUtils.checkString(
                    descripcionMotRecl));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaUltimaModificacion(FacesUtils.checkDate(
                    fechaUltimaModificacion));
            entity.setUsuarioCreador(FacesUtils.checkString(usuarioCreador));
            entity.setUsuarioUltimaModificacion(FacesUtils.checkString(
                    usuarioUltimaModificacion));
            businessDelegatorView.updateMotivoReclamacion(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("MotivoReclamacionView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcionMotRecl() {
        return txtDescripcionMotRecl;
    }

    public void setTxtDescripcionMotRecl(InputText txtDescripcionMotRecl) {
        this.txtDescripcionMotRecl = txtDescripcionMotRecl;
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

    public InputText getTxtIdMotRecl() {
        return txtIdMotRecl;
    }

    public void setTxtIdMotRecl(InputText txtIdMotRecl) {
        this.txtIdMotRecl = txtIdMotRecl;
    }

    public List<MotivoReclamacionDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataMotivoReclamacion();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<MotivoReclamacionDTO> motivoReclamacionDTO) {
        this.data = motivoReclamacionDTO;
    }

    public MotivoReclamacionDTO getSelectedMotivoReclamacion() {
        return selectedMotivoReclamacion;
    }

    public void setSelectedMotivoReclamacion(
        MotivoReclamacionDTO motivoReclamacion) {
        this.selectedMotivoReclamacion = motivoReclamacion;
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
