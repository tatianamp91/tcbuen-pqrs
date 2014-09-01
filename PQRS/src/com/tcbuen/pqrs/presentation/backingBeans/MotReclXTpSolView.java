package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.MotReclXTpSolDTO;
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
public class MotReclXTpSolView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtIdMotRecl_MotivoReclamacion;
    private InputText txtIdTpSolPqr_TipoSolicitudPqr;
    private InputText txtIdMotReclXTpSol;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<MotReclXTpSolDTO> data;
    private MotReclXTpSolDTO selectedMotReclXTpSol;
    private MotReclXTpSol entity;
    private boolean showDialog;
    private Long idMotRecl;
    private List<SelectItem> motivoReclamacion;
    private Long idTpSolPqr;
    private List<SelectItem> tipoSolicitudPqr;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public MotReclXTpSolView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            MotReclXTpSolDTO motReclXTpSolDTO = (MotReclXTpSolDTO) e.getObject();

            if (txtIdMotRecl_MotivoReclamacion == null) {
                txtIdMotRecl_MotivoReclamacion = new InputText();
            }

            txtIdMotRecl_MotivoReclamacion.setValue(motReclXTpSolDTO.getIdMotRecl_MotivoReclamacion());

            if (txtIdTpSolPqr_TipoSolicitudPqr == null) {
                txtIdTpSolPqr_TipoSolicitudPqr = new InputText();
            }

            txtIdTpSolPqr_TipoSolicitudPqr.setValue(motReclXTpSolDTO.getIdTpSolPqr_TipoSolicitudPqr());

            if (txtIdMotReclXTpSol == null) {
                txtIdMotReclXTpSol = new InputText();
            }

            txtIdMotReclXTpSol.setValue(motReclXTpSolDTO.getIdMotReclXTpSol());

            Long idMotReclXTpSol = FacesUtils.checkLong(txtIdMotReclXTpSol);
            entity = businessDelegatorView.getMotReclXTpSol(idMotReclXTpSol);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedMotReclXTpSol = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedMotReclXTpSol = null;

        if (txtIdMotRecl_MotivoReclamacion != null) {
            txtIdMotRecl_MotivoReclamacion.setValue(null);
            txtIdMotRecl_MotivoReclamacion.setDisabled(true);
        }

        if (txtIdTpSolPqr_TipoSolicitudPqr != null) {
            txtIdTpSolPqr_TipoSolicitudPqr.setValue(null);
            txtIdTpSolPqr_TipoSolicitudPqr.setDisabled(true);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }
        
        data = null;
        data = getData();
        
        motivoReclamacion = null;
        motivoReclamacion = getMotivoReclamacion();
        
        tipoSolicitudPqr = null;
        tipoSolicitudPqr = getTipoSolicitudPqr();

        return "";
    }

    public void listener_txtId() {
        try {
            Long idMotReclXTpSol = FacesUtils.checkLong(txtIdMotReclXTpSol);
            entity = (idMotReclXTpSol != null)
                ? businessDelegatorView.getMotReclXTpSol(idMotReclXTpSol) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtIdMotRecl_MotivoReclamacion.setDisabled(false);
            txtIdTpSolPqr_TipoSolicitudPqr.setDisabled(false);
            txtIdMotReclXTpSol.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtIdMotRecl_MotivoReclamacion.setValue(entity.getMotivoReclamacion()
                                                          .getIdMotRecl());
            txtIdMotRecl_MotivoReclamacion.setDisabled(false);
            txtIdTpSolPqr_TipoSolicitudPqr.setValue(entity.getTipoSolicitudPqr()
                                                          .getIdTpSolPqr());
            txtIdTpSolPqr_TipoSolicitudPqr.setDisabled(false);
            txtIdMotReclXTpSol.setValue(entity.getIdMotReclXTpSol());
            txtIdMotReclXTpSol.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedMotReclXTpSol = (MotReclXTpSolDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedMotReclXTpSol"));
        txtIdMotRecl_MotivoReclamacion.setValue(selectedMotReclXTpSol.getIdMotRecl_MotivoReclamacion());
        txtIdMotRecl_MotivoReclamacion.setDisabled(false);
        txtIdTpSolPqr_TipoSolicitudPqr.setValue(selectedMotReclXTpSol.getIdTpSolPqr_TipoSolicitudPqr());
        txtIdTpSolPqr_TipoSolicitudPqr.setDisabled(false);
        txtIdMotReclXTpSol.setValue(selectedMotReclXTpSol.getIdMotReclXTpSol());
        txtIdMotReclXTpSol.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedMotReclXTpSol == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new MotReclXTpSol();

            //Long idMotReclXTpSol = FacesUtils.checkLong(txtIdMotReclXTpSol);
            //entity.setIdMotReclXTpSol(idMotReclXTpSol);
            entity.setMotivoReclamacion((idMotRecl != null)
                ? businessDelegatorView.getMotivoReclamacion(idMotRecl) : null);
            entity.setTipoSolicitudPqr((idTpSolPqr != null)
                ? businessDelegatorView.getTipoSolicitudPqr(idTpSolPqr) : null);
            
            businessDelegatorView.saveMotReclXTpSol(entity);
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
                Long idMotReclXTpSol = new Long(selectedMotReclXTpSol.getIdMotReclXTpSol());
                entity = businessDelegatorView.getMotReclXTpSol(idMotReclXTpSol);
            }

            entity.setMotivoReclamacion((idMotRecl != null)
                    ? businessDelegatorView.getMotivoReclamacion(idMotRecl) : null);
                entity.setTipoSolicitudPqr((idTpSolPqr != null)
                    ? businessDelegatorView.getTipoSolicitudPqr(idTpSolPqr) : null);
                    
            businessDelegatorView.updateMotReclXTpSol(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
            
            action_clear();
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedMotReclXTpSol = (MotReclXTpSolDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedMotReclXTpSol"));

            Long idMotReclXTpSol = new Long(selectedMotReclXTpSol.getIdMotReclXTpSol());
            entity = businessDelegatorView.getMotReclXTpSol(idMotReclXTpSol);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idMotReclXTpSol = FacesUtils.checkLong(txtIdMotReclXTpSol);
            entity = businessDelegatorView.getMotReclXTpSol(idMotReclXTpSol);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteMotReclXTpSol(entity);
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
            selectedMotReclXTpSol = (MotReclXTpSolDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedMotReclXTpSol"));

            Long idMotReclXTpSol = new Long(selectedMotReclXTpSol.getIdMotReclXTpSol());
            entity = businessDelegatorView.getMotReclXTpSol(idMotReclXTpSol);
            businessDelegatorView.deleteMotReclXTpSol(entity);
            data.remove(selectedMotReclXTpSol);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long idMotReclXTpSol,
        Long idMotRecl_MotivoReclamacion, Long idTpSolPqr_TipoSolicitudPqr)
        throws Exception {
        try {
            businessDelegatorView.updateMotReclXTpSol(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("MotReclXTpSolView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtIdMotRecl_MotivoReclamacion() {
        return txtIdMotRecl_MotivoReclamacion;
    }

    public void setTxtIdMotRecl_MotivoReclamacion(
        InputText txtIdMotRecl_MotivoReclamacion) {
        this.txtIdMotRecl_MotivoReclamacion = txtIdMotRecl_MotivoReclamacion;
    }

    public InputText getTxtIdTpSolPqr_TipoSolicitudPqr() {
        return txtIdTpSolPqr_TipoSolicitudPqr;
    }

    public void setTxtIdTpSolPqr_TipoSolicitudPqr(
        InputText txtIdTpSolPqr_TipoSolicitudPqr) {
        this.txtIdTpSolPqr_TipoSolicitudPqr = txtIdTpSolPqr_TipoSolicitudPqr;
    }

    public InputText getTxtIdMotReclXTpSol() {
        return txtIdMotReclXTpSol;
    }

    public void setTxtIdMotReclXTpSol(InputText txtIdMotReclXTpSol) {
        this.txtIdMotReclXTpSol = txtIdMotReclXTpSol;
    }

    public List<MotReclXTpSolDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataMotReclXTpSol();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<MotReclXTpSolDTO> motReclXTpSolDTO) {
        this.data = motReclXTpSolDTO;
    }

    public MotReclXTpSolDTO getSelectedMotReclXTpSol() {
        return selectedMotReclXTpSol;
    }

    public void setSelectedMotReclXTpSol(MotReclXTpSolDTO motReclXTpSol) {
        this.selectedMotReclXTpSol = motReclXTpSol;
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

	public Long getIdMotRecl() {
		return idMotRecl;
	}

	public void setIdMotRecl(Long idMotRecl) {
		this.idMotRecl = idMotRecl;
	}

	public List<SelectItem> getMotivoReclamacion() {
		try {
			motivoReclamacion = new ArrayList<SelectItem>();
			List<MotivoReclamacion> motivoRecl = businessDelegatorView.getMotivoReclamacion();
	       	for (MotivoReclamacion motivoRec : motivoRecl) {
				motivoReclamacion.add(new SelectItem(motivoRec.getIdMotRecl(), motivoRec.getDescripcionMotRecl()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return motivoReclamacion;
	}

	public void setMotivoReclamacion(List<SelectItem> motivoReclamacion) {
		this.motivoReclamacion = motivoReclamacion;
	}

	public Long getIdTpSolPqr() {
		return idTpSolPqr;
	}

	public void setIdTpSolPqr(Long idTpSolPqr) {
		this.idTpSolPqr = idTpSolPqr;
	}

	public List<SelectItem> getTipoSolicitudPqr() {
		try {
			tipoSolicitudPqr = new ArrayList<SelectItem>();
			List<TipoSolicitudPqr> tipoSolicitud = businessDelegatorView.getTipoSolicitudPqr();
	       	for (TipoSolicitudPqr tiposoli : tipoSolicitud) {
				tipoSolicitudPqr.add(new SelectItem(tiposoli.getIdTpSolPqr(), tiposoli.getDescTpSol()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoSolicitudPqr;
	}

	public void setTipoSolicitudPqr(List<SelectItem> tipoSolicitudPqr) {
		this.tipoSolicitudPqr = tipoSolicitudPqr;
	}
    
}
