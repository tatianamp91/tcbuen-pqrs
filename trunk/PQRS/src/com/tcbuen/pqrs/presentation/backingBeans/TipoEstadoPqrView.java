package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.TipoEstadoPqrDTO;
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
public class TipoEstadoPqrView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtDescripcionEstado;
    private InputText txtEstadoRegistro;
    private InputText txtUsuarioCreador;
    private InputText txtUsuarioUltimaModificacion;
    private InputText txtIdTpEstPqr;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaUltimaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TipoEstadoPqrDTO> data;
    private TipoEstadoPqrDTO selectedTipoEstadoPqr;
    private TipoEstadoPqr entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;
    private String estadoRegistroSeleccionado;

   
	public TipoEstadoPqrView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            TipoEstadoPqrDTO tipoEstadoPqrDTO = (TipoEstadoPqrDTO) e.getObject();

            if (txtDescripcionEstado == null) {
                txtDescripcionEstado = new InputText();
            }

            txtDescripcionEstado.setValue(tipoEstadoPqrDTO.getDescripcionEstado());

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(tipoEstadoPqrDTO.getEstadoRegistro());

            if (txtUsuarioCreador == null) {
                txtUsuarioCreador = new InputText();
            }

            txtUsuarioCreador.setValue(tipoEstadoPqrDTO.getUsuarioCreador());

            if (txtUsuarioUltimaModificacion == null) {
                txtUsuarioUltimaModificacion = new InputText();
            }

            txtUsuarioUltimaModificacion.setValue(tipoEstadoPqrDTO.getUsuarioUltimaModificacion());

            if (txtIdTpEstPqr == null) {
                txtIdTpEstPqr = new InputText();
            }

            txtIdTpEstPqr.setValue(tipoEstadoPqrDTO.getIdTpEstPqr());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(tipoEstadoPqrDTO.getFechaCreacion());

            if (txtFechaUltimaModificacion == null) {
                txtFechaUltimaModificacion = new Calendar();
            }

            txtFechaUltimaModificacion.setValue(tipoEstadoPqrDTO.getFechaUltimaModificacion());

            Long idTpEstPqr = FacesUtils.checkLong(txtIdTpEstPqr);
            entity = businessDelegatorView.getTipoEstadoPqr(idTpEstPqr);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedTipoEstadoPqr = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTipoEstadoPqr = null;

        if (txtDescripcionEstado != null) {
            txtDescripcionEstado.setValue(null);
            
        }

        if (txtEstadoRegistro != null) {
            txtEstadoRegistro.setValue(null);
            
        }

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
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
            Long idTpEstPqr = FacesUtils.checkLong(txtIdTpEstPqr);
            entity = (idTpEstPqr != null)
                ? businessDelegatorView.getTipoEstadoPqr(idTpEstPqr) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtDescripcionEstado.setDisabled(false);
            txtEstadoRegistro.setDisabled(false);
            txtUsuarioCreador.setDisabled(false);
            txtUsuarioUltimaModificacion.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaUltimaModificacion.setDisabled(false);
            txtIdTpEstPqr.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtDescripcionEstado.setValue(entity.getDescripcionEstado());
            txtDescripcionEstado.setDisabled(false);
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
            txtIdTpEstPqr.setValue(entity.getIdTpEstPqr());
            txtIdTpEstPqr.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTipoEstadoPqr = (TipoEstadoPqrDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedTipoEstadoPqr"));
        txtDescripcionEstado.setValue(selectedTipoEstadoPqr.getDescripcionEstado());
        txtDescripcionEstado.setDisabled(false);
        txtEstadoRegistro.setValue(selectedTipoEstadoPqr.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtFechaCreacion.setValue(selectedTipoEstadoPqr.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaUltimaModificacion.setValue(selectedTipoEstadoPqr.getFechaUltimaModificacion());
        txtFechaUltimaModificacion.setDisabled(false);
        txtUsuarioCreador.setValue(selectedTipoEstadoPqr.getUsuarioCreador());
        txtUsuarioCreador.setDisabled(false);
        txtUsuarioUltimaModificacion.setValue(selectedTipoEstadoPqr.getUsuarioUltimaModificacion());
        txtUsuarioUltimaModificacion.setDisabled(false);
        txtIdTpEstPqr.setValue(selectedTipoEstadoPqr.getIdTpEstPqr());
        txtIdTpEstPqr.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTipoEstadoPqr == null) && (entity == null)) {
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
        	
        	String descripcionEstado=txtDescripcionEstado.getValue().toString();
        	TipoEstadoPqr tipoEstadoPqr= ObtenerDesTipoEstado(descripcionEstado);
        	
        	if(tipoEstadoPqr == null){
        		
        		if(!revizarCampos(descripcionEstado)){
        			return "";
        			
        		}
        	
            entity = new TipoEstadoPqr();

            //Long idTpEstPqr = FacesUtils.checkLong(txtIdTpEstPqr);

            //entity.setIdTpEstPqr(idTpEstPqr);
            entity.setDescripcionEstado(FacesUtils.checkString(txtDescripcionEstado));
            entity.setEstadoRegistro(estadoRegistroSeleccionado);
            //Falta agregar usuarios sesion
            entity.setUsuarioCreador("Admin");
			entity.setFechaCreacion(new Date());
			entity.setUsuarioUltimaModificacion(null);
			entity.setFechaUltimaModificacion(null);
     
			businessDelegatorView.saveTipoEstadoPqr(entity);
            FacesUtils.addInfoMessage("El tipo de estado se guardo exitosamente");
            action_clear();
            
        	} else {
        		throw new Exception("Ya existe estado");
        	}
				
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }
    
    private TipoEstadoPqr ObtenerDesTipoEstado(String descripcionEstado) throws Exception {
		TipoEstadoPqr entity = null;
		Object[] variables = { "descripcionEstado", true, descripcionEstado, "=" };
		List<TipoEstadoPqr> tipoEstadoPqrs = businessDelegatorView.findByCriteriaInTipoEstadoPqr(variables, null, null);
				

		if (Utilities.validationsList(tipoEstadoPqrs)) {
			entity = tipoEstadoPqrs.get(0);
		}
		return entity;
	}
    
    public boolean revizarCampos(String descripcionEstado) throws Exception {

		if (descripcionEstado.equals("") || descripcionEstado.trim().equals("")) {
			throw new Exception("Debe de ingresar una Descripcion");
		}

/*		if (!Utilities.isOnlyLetters2(descripcionEstado)) {
			throw new Exception(
					"La descripcion ingresada solo debe de contener letras");
		}*/
		return true;

	}
    

    private void actualizar(){
    	try {
    		
    		 entity.setDescripcionEstado(FacesUtils.checkString(txtDescripcionEstado));
    		 entity.setEstadoRegistro(estadoRegistroSeleccionado);
             entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
             entity.setFechaUltimaModificacion(new Date());
             entity.setUsuarioCreador(FacesUtils.checkString(txtUsuarioCreador));
             entity.setUsuarioUltimaModificacion("Admin-1");
             businessDelegatorView.updateTipoEstadoPqr(entity);
             FacesUtils.addInfoMessage("El tipo de estado se modifico exitosamente");
			
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}
    }
    
	public String action_modify() {
		try {

			String descripcionEstado = txtDescripcionEstado.getValue().toString();
			TipoEstadoPqr tipoEstadoPqr = ObtenerDesTipoEstado(descripcionEstado);

			if (tipoEstadoPqr == null) {

				if (!revizarCampos(descripcionEstado)) {
					return "";

				}
				actualizar();
				action_clear();

			} else {

				String descripcionTemp = tipoEstadoPqr.getDescripcionEstado();
				Long idTemp = tipoEstadoPqr.getIdTpEstPqr();

				if ((descripcionTemp.equals(descripcionEstado) && idTemp == entity.getIdTpEstPqr().longValue())) {

					if (!revizarCampos(descripcionEstado)) {
						return "";

					}
					actualizar();
					action_clear();
				} else {
					throw new Exception("El estado no ha sido modificado, ya existe estado");
				}
			}
		} catch (Exception e) {
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTipoEstadoPqr = (TipoEstadoPqrDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedTipoEstadoPqr"));

            Long idTpEstPqr = new Long(selectedTipoEstadoPqr.getIdTpEstPqr());
            entity = businessDelegatorView.getTipoEstadoPqr(idTpEstPqr);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idTpEstPqr = FacesUtils.checkLong(txtIdTpEstPqr);
            entity = businessDelegatorView.getTipoEstadoPqr(idTpEstPqr);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTipoEstadoPqr(entity);
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
            selectedTipoEstadoPqr = (TipoEstadoPqrDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedTipoEstadoPqr"));

            Long idTpEstPqr = new Long(selectedTipoEstadoPqr.getIdTpEstPqr());
            entity = businessDelegatorView.getTipoEstadoPqr(idTpEstPqr);
            businessDelegatorView.deleteTipoEstadoPqr(entity);
            data.remove(selectedTipoEstadoPqr);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String descripcionEstado,
        String estadoRegistro, Date fechaCreacion,
        Date fechaUltimaModificacion, Long idTpEstPqr, String usuarioCreador,
        String usuarioUltimaModificacion) throws Exception {
        try {
            entity.setDescripcionEstado(FacesUtils.checkString(
                    descripcionEstado));
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaUltimaModificacion(FacesUtils.checkDate(
                    fechaUltimaModificacion));
            entity.setUsuarioCreador(FacesUtils.checkString(usuarioCreador));
            entity.setUsuarioUltimaModificacion(FacesUtils.checkString(
                    usuarioUltimaModificacion));
            businessDelegatorView.updateTipoEstadoPqr(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TipoEstadoPqrView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtDescripcionEstado() {
        return txtDescripcionEstado;
    }

    public void setTxtDescripcionEstado(InputText txtDescripcionEstado) {
        this.txtDescripcionEstado = txtDescripcionEstado;
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

    public InputText getTxtIdTpEstPqr() {
        return txtIdTpEstPqr;
    }

    public void setTxtIdTpEstPqr(InputText txtIdTpEstPqr) {
        this.txtIdTpEstPqr = txtIdTpEstPqr;
    }

    public List<TipoEstadoPqrDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTipoEstadoPqr();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TipoEstadoPqrDTO> tipoEstadoPqrDTO) {
        this.data = tipoEstadoPqrDTO;
    }

    public TipoEstadoPqrDTO getSelectedTipoEstadoPqr() {
        return selectedTipoEstadoPqr;
    }

    public void setSelectedTipoEstadoPqr(TipoEstadoPqrDTO tipoEstadoPqr) {
        this.selectedTipoEstadoPqr = tipoEstadoPqr;
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
