package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.InfoSolicitanteDTO;
import com.tcbuen.pqrs.presentation.businessDelegate.*;
import com.tcbuen.pqrs.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
public class InfoSolicitanteView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtCorreoElectronico;
    private InputText txtNombreContacto;
    private InputText txtNombreEmpresa;
    private String numeroCelular;
    private InputText txtNumeroCelular;
    private String maskNit;
    private String numeroIdentificacion;
    private InputText txtNumeroIdentificacion;
    private String telefonoFijo;
    private InputText txtTelefonoFijo;
    private InputText txtIdTpDoc_TipoDocumento;
    private InputText txtIdInfoSolicitante;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private CommandButton btnSiguiente;
    private List<InfoSolicitanteDTO> data;
    private InfoSolicitanteDTO selectedInfoSolicitante;
    private InfoSolicitante entity;
    private boolean showDialog;
    private boolean instructivo;
    private boolean solicitud;
    private boolean siguiente;
    private Long idTipoDocumento;
    private List<SelectItem> tipoDocumento;
    private Long idTipoSolicitud;
    private String descTpSol;
    private List<TipoSolicitudPqr> tipoSolicitud;
    private List<AnexosPqr> anexosPqrs;
    private String descripcionParametro;
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
        	FacesUtils.addErrorMessage(ex.getMessage());
        }
    }
    
    public void cambiarMaskNit() {
    	try{
	    	TipoDocumento tDoc = businessDelegatorView.getTipoDocumento(idTipoDocumento);
	    	if(tDoc.getDescripcionTpDoc().equals("nit")||tDoc.getDescripcionTpDoc().equals("Nit")||
	    			tDoc.getDescripcionTpDoc().equals("NIT")){
	    		maskNit = "999.999.999-9";
	    	}else{
	    		maskNit ="";
	    	}
    	}catch(Exception e){
    		FacesUtils.addErrorMessage(e.getMessage());
    	}
    }
    
    public String action_new() {
        action_clear();
        selectedInfoSolicitante = null;
        setShowDialog(true);
        setInstructivo(true);

        return "";
    }
    
    public String action_instructivo() {
    	try{
    		if(idTipoSolicitud != null){
		    	TipoSolicitudPqr tSol = businessDelegatorView.getTipoSolicitudPqr(idTipoSolicitud);
		    	descTpSol = tSol.getDescTpSol();
		    	anexosPqrs = getAnexosPqrs(idTipoSolicitud);
				ParametrosPqr parametros = ObtenerParametro(descTpSol);				
				setDescripcionParametro(parametros.getValorParam());
		        setInstructivo(true);
    		}else{
    			throw new Exception("Debe Seleccionar un Tipo de Solicitud");
    		}
    	}catch (Exception e){
    		FacesUtils.addErrorMessage(e.getMessage());
    	}

        return "";
    }
    
	private ParametrosPqr ObtenerParametro(String parametro) throws Exception {
		ParametrosPqr entity = null;
		Object[] variables = { "descripcionParam", true, parametro, "=" };
		List<ParametrosPqr> parametrosPqrs = businessDelegatorView
				.findByCriteriaInParametrosPqr(variables, null, null);

		if (Utilities.validationsList(parametrosPqrs)) {
			entity = parametrosPqrs.get(0);
		}
		return entity;
	}
    
    public String action_solicitud() {
        setSolicitud(true);
        return "";
    }
    
    public String action_clear() {
        entity = null;
        selectedInfoSolicitante = null;

        if (txtCorreoElectronico != null) {
            txtCorreoElectronico.setValue(null);
        }

        if (txtNombreContacto != null) {
            txtNombreContacto.setValue(null);
        }

        if (txtNombreEmpresa != null) {
            txtNombreEmpresa.setValue(null);
        }

        if (numeroCelular != null) {
        	numeroCelular = "";
        }

        if (numeroIdentificacion != null) {
            numeroIdentificacion = "";
        }

        if (telefonoFijo != null) {
            telefonoFijo = "";
        }

        if (txtIdTpDoc_TipoDocumento != null) {
            txtIdTpDoc_TipoDocumento.setValue(null);
        }
        
        if (idTipoDocumento != null) {
        	idTipoDocumento = null;
        }
        
        tipoDocumento = null;
        tipoDocumento = getTipoDocumento();
        
        tipoSolicitud = null;
        idTipoSolicitud = null;
        anexosPqrs = null;
        maskNit = "";
        setSiguiente(false);
        
        setShowDialog(false);
        setInstructivo(false);
        setSolicitud(false);

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
            btnSiguiente.setDisabled(true);
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
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {        	
        	String nombreContacto= txtNombreContacto.getValue().toString();
			String nombreEmpresa=txtNombreEmpresa.getValue().toString();
			String email = txtCorreoElectronico.getValue().toString();

			if (!revizarCampos(nombreContacto,nombreEmpresa,numeroIdentificacion,numeroCelular, telefonoFijo, email)){								
				return "";
			}
            entity = new InfoSolicitante();
            entity.setTipoDocumento((idTipoDocumento != null)
                ? businessDelegatorView.getTipoDocumento(idTipoDocumento) : null);
            entity.setNumeroIdentificacion(numeroIdentificacion);
            entity.setNombreEmpresa(FacesUtils.checkString(txtNombreEmpresa));
            entity.setNombreContacto(FacesUtils.checkString(txtNombreContacto));
            entity.setCorreoElectronico(FacesUtils.checkString(txtCorreoElectronico));         
            entity.setNumeroCelular(numeroCelular);
            entity.setTelefonoFijo(telefonoFijo);
            businessDelegatorView.saveInfoSolicitante(entity);
            FacesUtils.addInfoMessage("La información ha sido guardada exitosamente");
            btnClear.setDisabled(true);
            btnSave.setDisabled(true);
            setSiguiente(true);
            //action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }    
    
	public boolean revizarCampos(String nombreContacto, String nombreEmpresa,
			String numeroIdentificacion, String numeroCelular,
			String telefonoFijo, String email) throws Exception{
		
		if (numeroIdentificacion.equals("")
				|| numeroIdentificacion.trim().equals("")) {
			throw new Exception(
					"Debe de ingresar un Numero de Identificación");
		}
		if(maskNit.equals("")){
			if(numeroIdentificacion != null){
				if (!Utilities.soloNumeros(numeroIdentificacion)) {
					throw new Exception(
							"El Número de Identificaciún debe ser totalmente numerico");
				}
			}
		}

		if (nombreContacto.equals("") || nombreContacto.trim().equals("")) {
			throw new Exception("Debe de ingresar un Nombre de contacto");
		}

		if (nombreEmpresa.equals("") || nombreEmpresa.trim().equals("")) {
			throw new Exception("Debe de ingresar un Nombre de empresa");
		}

		if (email.equals("") || email.trim().equals("")) {
			throw new Exception(
					"El Correo Electronico es de caracter obligatorio");
		}

		if (!Utilities.correElectronico(email)) {
			throw new Exception(
					"El Correo Electronico debe tener el siguiente formato \"xxx@xxx.xxx\"");
		}
		
		if (numeroCelular.equals("") || numeroCelular.trim().equals("")) {
			throw new Exception("Debe de ingresar un Numero celular");
		}
		/*
		if (!Utilities.soloNumeros(numeroCelular)) {
			throw new Exception(
					"El Numero celular debe ser totalmente numerico");
		}*/

		if (telefonoFijo.equals("") || telefonoFijo.trim().equals("")) {
			throw new Exception("Debe de ingresar un Numero fijo");
		}
		/*
		if (!Utilities.soloNumeros(telefonoFijo)) {
			throw new Exception("El Numero fijo debe ser totalmente numerico");
		}	
		*/
		return true;		
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
            entity.setTipoDocumento((idTipoDocumento != null)
                    ? businessDelegatorView.getTipoDocumento(idTipoDocumento) : null);
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
        idTipoSolicitud = null;
        return "";
    }

    public String action_closeDialog_solicitud() {
        setShowDialog(false);
        setInstructivo(false);
        setSolicitud(false);
        idTipoSolicitud = null;

        return "";
    }
    
    public String action_closeDialog_instructivo() {
        setShowDialog(false);
        setInstructivo(false);
        setSolicitud(true);

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

	public Long getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(Long idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public List<SelectItem> getTipoDocumento() {
		try {
		       	tipoDocumento = new ArrayList<SelectItem>();
				List<TipoDocumento> tiposDocumentos = businessDelegatorView.getTipoDocumento();
		       	for (TipoDocumento tDoc : tiposDocumentos) {
					tipoDocumento.add(new SelectItem(tDoc.getIdTpDoc(), tDoc.getDescripcionTpDoc()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tipoDocumento;	
	}

	public void setTipoDocumento(List<SelectItem> tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Long getIdTipoSolicitud() {
		return idTipoSolicitud;
	}

	public void setIdTipoSolicitud(Long idTipoSolicitud) {
		this.idTipoSolicitud = idTipoSolicitud;
	}

	public List<TipoSolicitudPqr> getTipoSolicitud() {
		try {
	       	tipoSolicitud = new ArrayList<TipoSolicitudPqr>();
			tipoSolicitud = businessDelegatorView.getTipoSolicitudPqr();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoSolicitud;
	}

	public void setTipoSolicitud(List<TipoSolicitudPqr> tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public List<AnexosPqr> getAnexosPqrs(long idTipoSolicitud) {
		try {
			TipoSolicitudPqr tSolPqr = businessDelegatorView.getTipoSolicitudPqr(idTipoSolicitud);
	       	anexosPqrs = new ArrayList<AnexosPqr>();
			anexosPqrs = businessDelegatorView.consultarAnxsXTipoPqr(tSolPqr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return anexosPqrs;
	}

	public void setAnexosPqrs(List<AnexosPqr> anexosPqrs) {
		this.anexosPqrs = anexosPqrs;
	}

	public boolean isInstructivo() {
		return instructivo;
	}

	public void setInstructivo(boolean instructivo) {
		this.instructivo = instructivo;
	}

	public boolean isSolicitud() {
		return solicitud;
	}

	public void setSolicitud(boolean solicitud) {
		this.solicitud = solicitud;
	}

	public List<AnexosPqr> getAnexosPqrs() {
		return anexosPqrs;
	}

	public String getDescTpSol() {
		return descTpSol;
	}

	public void setDescTpSol(String descTpSol) {
		this.descTpSol = descTpSol;
	}

	public CommandButton getBtnSiguiente() {
		return btnSiguiente;
	}

	public void setBtnSiguiente(CommandButton btnSiguiente) {
		this.btnSiguiente = btnSiguiente;
	}

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public String getTelefonoFijo() {
		return telefonoFijo;
	}
	
	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public String getMaskNit() {
		return maskNit;
	}

	public void setMaskNit(String maskNit) {
		this.maskNit = maskNit;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public boolean isSiguiente() {
		return siguiente;
	}
	
	public void setSiguiente(boolean siguiente) {
		this.siguiente = siguiente;
	}

	public String getDescripcionParametro() {
		return descripcionParametro;
	}
	
	public void setDescripcionParametro(String descripcionParametro) {
		this.descripcionParametro = descripcionParametro;
	}
	
}
