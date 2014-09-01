package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.MotSolXTpSolDTO;
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
public class MotSolXTpSolView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtIdMotSol_MotivoSolicitud;
    private InputText txtIdTpSolPqr_TipoSolicitudPqr;
    private InputText txtIdMotSolXTpSol;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<MotSolXTpSolDTO> data;
    private MotSolXTpSolDTO selectedMotSolXTpSol;
    private MotSolXTpSol entity;
    private boolean showDialog;
    private Long idMotSol;
    private List<SelectItem> motivoSolicitud;
    private Long idTpSolPqr;
    private List<SelectItem> tipoSolicitudPqr;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public MotSolXTpSolView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            MotSolXTpSolDTO motSolXTpSolDTO = (MotSolXTpSolDTO) e.getObject();

            if (txtIdMotSol_MotivoSolicitud == null) {
                txtIdMotSol_MotivoSolicitud = new InputText();
            }

            txtIdMotSol_MotivoSolicitud.setValue(motSolXTpSolDTO.getIdMotSol_MotivoSolicitud());

            if (txtIdTpSolPqr_TipoSolicitudPqr == null) {
                txtIdTpSolPqr_TipoSolicitudPqr = new InputText();
            }

            txtIdTpSolPqr_TipoSolicitudPqr.setValue(motSolXTpSolDTO.getIdTpSolPqr_TipoSolicitudPqr());

            if (txtIdMotSolXTpSol == null) {
                txtIdMotSolXTpSol = new InputText();
            }

            txtIdMotSolXTpSol.setValue(motSolXTpSolDTO.getIdMotSolXTpSol());

            Long idMotSolXTpSol = FacesUtils.checkLong(txtIdMotSolXTpSol);
            entity = businessDelegatorView.getMotSolXTpSol(idMotSolXTpSol);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedMotSolXTpSol = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedMotSolXTpSol = null;

        if (txtIdMotSol_MotivoSolicitud != null) {
            txtIdMotSol_MotivoSolicitud.setValue(null);
            txtIdMotSol_MotivoSolicitud.setDisabled(true);
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
        
        motivoSolicitud = null;
        motivoSolicitud = getMotivoSolicitud();
        
        tipoSolicitudPqr = null;
        tipoSolicitudPqr = getTipoSolicitudPqr();

        return "";
    }

    public void listener_txtId() {
        try {
            Long idMotSolXTpSol = FacesUtils.checkLong(txtIdMotSolXTpSol);
            entity = (idMotSolXTpSol != null)
                ? businessDelegatorView.getMotSolXTpSol(idMotSolXTpSol) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtIdMotSol_MotivoSolicitud.setDisabled(false);
            txtIdTpSolPqr_TipoSolicitudPqr.setDisabled(false);
            txtIdMotSolXTpSol.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtIdMotSol_MotivoSolicitud.setValue(entity.getMotivoSolicitud()
                                                       .getIdMotSol());
            txtIdMotSol_MotivoSolicitud.setDisabled(false);
            txtIdTpSolPqr_TipoSolicitudPqr.setValue(entity.getTipoSolicitudPqr()
                                                          .getIdTpSolPqr());
            txtIdTpSolPqr_TipoSolicitudPqr.setDisabled(false);
            txtIdMotSolXTpSol.setValue(entity.getIdMotSolXTpSol());
            txtIdMotSolXTpSol.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedMotSolXTpSol = (MotSolXTpSolDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedMotSolXTpSol"));
        txtIdMotSol_MotivoSolicitud.setValue(selectedMotSolXTpSol.getIdMotSol_MotivoSolicitud());
        txtIdMotSol_MotivoSolicitud.setDisabled(false);
        txtIdTpSolPqr_TipoSolicitudPqr.setValue(selectedMotSolXTpSol.getIdTpSolPqr_TipoSolicitudPqr());
        txtIdTpSolPqr_TipoSolicitudPqr.setDisabled(false);
        txtIdMotSolXTpSol.setValue(selectedMotSolXTpSol.getIdMotSolXTpSol());
        txtIdMotSolXTpSol.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedMotSolXTpSol == null) && (entity == null)) {
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
            entity = new MotSolXTpSol();

            //Long idMotSolXTpSol = FacesUtils.checkLong(txtIdMotSolXTpSol);
           //entity.setIdMotSolXTpSol(idMotSolXTpSol);
            entity.setMotivoSolicitud((idMotSol != null)
                ? businessDelegatorView.getMotivoSolicitud(idMotSol) : null);
            entity.setTipoSolicitudPqr((idTpSolPqr != null)
                ? businessDelegatorView.getTipoSolicitudPqr(idTpSolPqr) : null);
            
            businessDelegatorView.saveMotSolXTpSol(entity);
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
                Long idMotSolXTpSol = new Long(selectedMotSolXTpSol.getIdMotSolXTpSol());
                entity = businessDelegatorView.getMotSolXTpSol(idMotSolXTpSol);
            }

            entity.setMotivoSolicitud((idMotSol != null)
                    ? businessDelegatorView.getMotivoSolicitud(idMotSol) : null);
                entity.setTipoSolicitudPqr((idTpSolPqr != null)
                    ? businessDelegatorView.getTipoSolicitudPqr(idTpSolPqr) : null);
            
            businessDelegatorView.updateMotSolXTpSol(entity);
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
            selectedMotSolXTpSol = (MotSolXTpSolDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedMotSolXTpSol"));

            Long idMotSolXTpSol = new Long(selectedMotSolXTpSol.getIdMotSolXTpSol());
            entity = businessDelegatorView.getMotSolXTpSol(idMotSolXTpSol);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idMotSolXTpSol = FacesUtils.checkLong(txtIdMotSolXTpSol);
            entity = businessDelegatorView.getMotSolXTpSol(idMotSolXTpSol);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteMotSolXTpSol(entity);
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
            selectedMotSolXTpSol = (MotSolXTpSolDTO) (evt.getComponent()
                                                         .getAttributes()
                                                         .get("selectedMotSolXTpSol"));

            Long idMotSolXTpSol = new Long(selectedMotSolXTpSol.getIdMotSolXTpSol());
            entity = businessDelegatorView.getMotSolXTpSol(idMotSolXTpSol);
            businessDelegatorView.deleteMotSolXTpSol(entity);
            data.remove(selectedMotSolXTpSol);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(Long idMotSolXTpSol,
        Long idMotSol_MotivoSolicitud, Long idTpSolPqr_TipoSolicitudPqr)
        throws Exception {
        try {
            businessDelegatorView.updateMotSolXTpSol(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("MotSolXTpSolView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtIdMotSol_MotivoSolicitud() {
        return txtIdMotSol_MotivoSolicitud;
    }

    public void setTxtIdMotSol_MotivoSolicitud(
        InputText txtIdMotSol_MotivoSolicitud) {
        this.txtIdMotSol_MotivoSolicitud = txtIdMotSol_MotivoSolicitud;
    }

    public InputText getTxtIdTpSolPqr_TipoSolicitudPqr() {
        return txtIdTpSolPqr_TipoSolicitudPqr;
    }

    public void setTxtIdTpSolPqr_TipoSolicitudPqr(
        InputText txtIdTpSolPqr_TipoSolicitudPqr) {
        this.txtIdTpSolPqr_TipoSolicitudPqr = txtIdTpSolPqr_TipoSolicitudPqr;
    }

    public InputText getTxtIdMotSolXTpSol() {
        return txtIdMotSolXTpSol;
    }

    public void setTxtIdMotSolXTpSol(InputText txtIdMotSolXTpSol) {
        this.txtIdMotSolXTpSol = txtIdMotSolXTpSol;
    }

    public List<MotSolXTpSolDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataMotSolXTpSol();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<MotSolXTpSolDTO> motSolXTpSolDTO) {
        this.data = motSolXTpSolDTO;
    }

    public MotSolXTpSolDTO getSelectedMotSolXTpSol() {
        return selectedMotSolXTpSol;
    }

    public void setSelectedMotSolXTpSol(MotSolXTpSolDTO motSolXTpSol) {
        this.selectedMotSolXTpSol = motSolXTpSol;
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

	public Long getIdMotSol() {
		return idMotSol;
	}
	public void setIdMotSol(Long idMotSol) {
		this.idMotSol = idMotSol;
	}

	public List<SelectItem> getMotivoSolicitud() {
		try {
			motivoSolicitud = new ArrayList<SelectItem>();
			List<MotivoSolicitud> motivoSol = businessDelegatorView.getMotivoSolicitud();
	       	for (MotivoSolicitud motivoSo : motivoSol) {
				motivoSolicitud.add(new SelectItem(motivoSo.getIdMotSol(), motivoSo.getDescripcionMotSol()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return motivoSolicitud;
	}

	public void setMotivoSolicitud(List<SelectItem> motivoSolicitud) {
		this.motivoSolicitud = motivoSolicitud;
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
