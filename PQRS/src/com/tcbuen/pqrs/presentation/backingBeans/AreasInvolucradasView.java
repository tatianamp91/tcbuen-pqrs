package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.AreasInvolucradasDTO;
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
public class AreasInvolucradasView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtEstadoRegistro;
	private String estadoRegistroSeleccionado;
    private InputText txtNombreArea;
    private InputText txtUsuarioCreador;
    private InputText txtUsuarioUltimaModificacion;
    private InputText txtIdAreaInvolucrada;
    private Calendar txtFechaCreacion;
    private Calendar txtFechaUltimaModificacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<AreasInvolucradasDTO> data;
    private AreasInvolucradasDTO selectedAreasInvolucradas;
    private AreasInvolucradas entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public AreasInvolucradasView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            AreasInvolucradasDTO areasInvolucradasDTO = (AreasInvolucradasDTO) e.getObject();

            if (txtEstadoRegistro == null) {
                txtEstadoRegistro = new InputText();
            }

            txtEstadoRegistro.setValue(areasInvolucradasDTO.getEstadoRegistro());

            if (txtNombreArea == null) {
                txtNombreArea = new InputText();
            }

            txtNombreArea.setValue(areasInvolucradasDTO.getNombreArea());

            if (txtUsuarioCreador == null) {
                txtUsuarioCreador = new InputText();
            }

            txtUsuarioCreador.setValue(areasInvolucradasDTO.getUsuarioCreador());

            if (txtUsuarioUltimaModificacion == null) {
                txtUsuarioUltimaModificacion = new InputText();
            }

            txtUsuarioUltimaModificacion.setValue(areasInvolucradasDTO.getUsuarioUltimaModificacion());

            if (txtIdAreaInvolucrada == null) {
                txtIdAreaInvolucrada = new InputText();
            }

            txtIdAreaInvolucrada.setValue(areasInvolucradasDTO.getIdAreaInvolucrada());

            if (txtFechaCreacion == null) {
                txtFechaCreacion = new Calendar();
            }

            txtFechaCreacion.setValue(areasInvolucradasDTO.getFechaCreacion());

            if (txtFechaUltimaModificacion == null) {
                txtFechaUltimaModificacion = new Calendar();
            }

            txtFechaUltimaModificacion.setValue(areasInvolucradasDTO.getFechaUltimaModificacion());

            Long idAreaInvolucrada = FacesUtils.checkLong(txtIdAreaInvolucrada);
            entity = businessDelegatorView.getAreasInvolucradas(idAreaInvolucrada);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedAreasInvolucradas = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedAreasInvolucradas = null;

        if (estadoRegistroSeleccionado != null) {
            estadoRegistroSeleccionado = null;
        }

        if (txtNombreArea != null) {
            txtNombreArea.setValue(null);
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
            Long idAreaInvolucrada = FacesUtils.checkLong(txtIdAreaInvolucrada);
            entity = (idAreaInvolucrada != null)
                ? businessDelegatorView.getAreasInvolucradas(idAreaInvolucrada)
                : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEstadoRegistro.setDisabled(false);
            txtNombreArea.setDisabled(false);
            txtUsuarioCreador.setDisabled(false);
            txtUsuarioUltimaModificacion.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtFechaUltimaModificacion.setDisabled(false);
            txtIdAreaInvolucrada.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEstadoRegistro.setValue(entity.getEstadoRegistro());
            txtEstadoRegistro.setDisabled(false);
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtFechaUltimaModificacion.setValue(entity.getFechaUltimaModificacion());
            txtFechaUltimaModificacion.setDisabled(false);
            txtNombreArea.setValue(entity.getNombreArea());
            txtNombreArea.setDisabled(false);
            txtUsuarioCreador.setValue(entity.getUsuarioCreador());
            txtUsuarioCreador.setDisabled(false);
            txtUsuarioUltimaModificacion.setValue(entity.getUsuarioUltimaModificacion());
            txtUsuarioUltimaModificacion.setDisabled(false);
            txtIdAreaInvolucrada.setValue(entity.getIdAreaInvolucrada());
            txtIdAreaInvolucrada.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedAreasInvolucradas = (AreasInvolucradasDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedAreasInvolucradas"));
        txtEstadoRegistro.setValue(selectedAreasInvolucradas.getEstadoRegistro());
        txtEstadoRegistro.setDisabled(false);
        txtFechaCreacion.setValue(selectedAreasInvolucradas.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtFechaUltimaModificacion.setValue(selectedAreasInvolucradas.getFechaUltimaModificacion());
        txtFechaUltimaModificacion.setDisabled(false);
        txtNombreArea.setValue(selectedAreasInvolucradas.getNombreArea());
        txtNombreArea.setDisabled(false);
        txtUsuarioCreador.setValue(selectedAreasInvolucradas.getUsuarioCreador());
        txtUsuarioCreador.setDisabled(false);
        txtUsuarioUltimaModificacion.setValue(selectedAreasInvolucradas.getUsuarioUltimaModificacion());
        txtUsuarioUltimaModificacion.setDisabled(false);
        txtIdAreaInvolucrada.setValue(selectedAreasInvolucradas.getIdAreaInvolucrada());
        txtIdAreaInvolucrada.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedAreasInvolucradas == null) && (entity == null)) {
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
			//

			// Long idAreaInvolucrada =
			// FacesUtils.checkLong(txtIdAreaInvolucrada);
			// entity.setIdAreaInvolucrada(idAreaInvolucrada);

			String nombreArea = txtNombreArea.getValue().toString();
			
			AreasInvolucradas area= ObtenerArea(nombreArea);
			
			if (area == null) {								
				
				if (!revizarCampos(nombreArea)) {
					return "";
				}
				entity = new AreasInvolucradas();
				entity.setNombreArea(FacesUtils.checkString(txtNombreArea));
				String estado = (estadoRegistroSeleccionado
						.equals("Activo")) ? "A" : "I";
				entity.setEstadoRegistro(estado);
				//entity.setEstadoRegistro(estadoRegistroSeleccionado);
				// Falta agregar usuario de sesion
				entity.setUsuarioCreador("Admin");
				entity.setFechaCreacion(new Date());
				entity.setUsuarioUltimaModificacion(null);
				entity.setFechaUltimaModificacion(null);
				businessDelegatorView.saveAreasInvolucradas(entity);
				FacesUtils.addInfoMessage("El área se guardo exitosamente");			

				action_clear();
			} else {

				throw new Exception("El nombre del area ya existe");
			}

		} catch (Exception e) {
			entity = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return "";
	}
	
	public boolean revizarCampos(String nombreArea) throws Exception {

		if (nombreArea.equals("") || nombreArea.trim().equals("")) {
			throw new Exception("Debe de ingresar un Nombre");
		}

		if (!Utilities.isOnlyLetters2(nombreArea)) {
			throw new Exception(
					"El Nombre ingresado solo debe de contener letras");
		}
		return true;

	}

	private AreasInvolucradas ObtenerArea(String nombreArea) throws Exception {
		AreasInvolucradas entity = null;
		Object[] variables = { "nombreArea", true, nombreArea, "=" };
		List<AreasInvolucradas> areasInvolucradas = businessDelegatorView
				.findByCriteriaInAreasInvolucradas(variables, null, null);

		if (Utilities.validationsList(areasInvolucradas)) {
			entity = areasInvolucradas.get(0);
		}
		return entity;
	}
	
	
	public void actualizar(){
		try {
			
			  entity.setNombreArea(FacesUtils.checkString(txtNombreArea));
		         entity.setEstadoRegistro(estadoRegistroSeleccionado);
		         entity.setUsuarioCreador(FacesUtils.checkString(txtUsuarioCreador));
		         entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
		         //Falta agregar usuario de sesion
		         entity.setUsuarioUltimaModificacion("Facturación");
		         entity.setFechaUltimaModificacion(new Date());           
		       
		         
		         businessDelegatorView.updateAreasInvolucradas(entity);
		         FacesUtils.addInfoMessage("El área se guardo exitosamente");
		} catch (Exception e) {
			
			data = null;
			FacesUtils.addErrorMessage(e.getMessage());
		}
	   
		
	}

	public String action_modify() {
		try {

			String nombreArea = txtNombreArea.getValue().toString();
			AreasInvolucradas area = ObtenerArea(nombreArea);

			if (area == null) {
				
				if (!revizarCampos(nombreArea)) {
					return "";
				}

				actualizar();
				action_clear();
			} else {
				String nombreTemp = area.getNombreArea();
				String estadoTemp = area.getEstadoRegistro();
				
				if((nombreTemp.equals(nombreArea) && 
						!estadoTemp.equals(estadoRegistroSeleccionado))){
					
					if (!revizarCampos(nombreArea)) {
						return "";
					}
					
					actualizar();
					action_clear();
				}else{
					throw new Exception("El Rol NO ha sido modificado");
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
            selectedAreasInvolucradas = (AreasInvolucradasDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedAreasInvolucradas"));

            Long idAreaInvolucrada = new Long(selectedAreasInvolucradas.getIdAreaInvolucrada());
            entity = businessDelegatorView.getAreasInvolucradas(idAreaInvolucrada);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idAreaInvolucrada = FacesUtils.checkLong(txtIdAreaInvolucrada);
            entity = businessDelegatorView.getAreasInvolucradas(idAreaInvolucrada);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteAreasInvolucradas(entity);
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
            selectedAreasInvolucradas = (AreasInvolucradasDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedAreasInvolucradas"));

            Long idAreaInvolucrada = new Long(selectedAreasInvolucradas.getIdAreaInvolucrada());
            entity = businessDelegatorView.getAreasInvolucradas(idAreaInvolucrada);
            businessDelegatorView.deleteAreasInvolucradas(entity);
            data.remove(selectedAreasInvolucradas);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String estadoRegistro,
        Date fechaCreacion, Date fechaUltimaModificacion,
        Long idAreaInvolucrada, String nombreArea, String usuarioCreador,
        String usuarioUltimaModificacion) throws Exception {
        try {
            entity.setEstadoRegistro(FacesUtils.checkString(estadoRegistro));
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            entity.setFechaUltimaModificacion(FacesUtils.checkDate(
                    fechaUltimaModificacion));
            entity.setNombreArea(FacesUtils.checkString(nombreArea));
            entity.setUsuarioCreador(FacesUtils.checkString(usuarioCreador));
            entity.setUsuarioUltimaModificacion(FacesUtils.checkString(
                    usuarioUltimaModificacion));
            businessDelegatorView.updateAreasInvolucradas(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("AreasInvolucradasView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEstadoRegistro() {
        return txtEstadoRegistro;
    }

    public void setTxtEstadoRegistro(InputText txtEstadoRegistro) {
        this.txtEstadoRegistro = txtEstadoRegistro;
    }

    public InputText getTxtNombreArea() {
        return txtNombreArea;
    }

    public void setTxtNombreArea(InputText txtNombreArea) {
        this.txtNombreArea = txtNombreArea;
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

    public InputText getTxtIdAreaInvolucrada() {
        return txtIdAreaInvolucrada;
    }

    public void setTxtIdAreaInvolucrada(InputText txtIdAreaInvolucrada) {
        this.txtIdAreaInvolucrada = txtIdAreaInvolucrada;
    }

    public List<AreasInvolucradasDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataAreasInvolucradas();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<AreasInvolucradasDTO> areasInvolucradasDTO) {
        this.data = areasInvolucradasDTO;
    }

    public AreasInvolucradasDTO getSelectedAreasInvolucradas() {
        return selectedAreasInvolucradas;
    }

    public void setSelectedAreasInvolucradas(
        AreasInvolucradasDTO areasInvolucradas) {
        this.selectedAreasInvolucradas = areasInvolucradas;
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
