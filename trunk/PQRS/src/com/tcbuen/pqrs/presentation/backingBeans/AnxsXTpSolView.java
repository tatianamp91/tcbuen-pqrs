package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.AnxsXTpSolDTO;
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
public class AnxsXTpSolView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtEsObligatorio;
    private InputText txtIdAnexoPqr_AnexosPqr;
    private InputText txtIdTpSolPqr_TipoSolicitudPqr;
    private InputText txtIdAnexoXTpSol;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<AnxsXTpSolDTO> data;
    private AnxsXTpSolDTO selectedAnxsXTpSol;
    private AnxsXTpSol entity;
    private boolean showDialog;
    private Long idAnexoPqr;
    private List<SelectItem> anexospqr;
    private Long idTpSolPqr;
    private List<SelectItem> tipoSolicitudPqr;
    private String esObligatorioSeleccionado;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public AnxsXTpSolView() {
        super();
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            AnxsXTpSolDTO anxsXTpSolDTO = (AnxsXTpSolDTO) e.getObject();

            if (txtEsObligatorio == null) {
                txtEsObligatorio = new InputText();
            }

            txtEsObligatorio.setValue(anxsXTpSolDTO.getEsObligatorio());

            if (txtIdAnexoPqr_AnexosPqr == null) {
                txtIdAnexoPqr_AnexosPqr = new InputText();
            }

            txtIdAnexoPqr_AnexosPqr.setValue(anxsXTpSolDTO.getIdAnexoPqr_AnexosPqr());

            if (txtIdTpSolPqr_TipoSolicitudPqr == null) {
                txtIdTpSolPqr_TipoSolicitudPqr = new InputText();
            }

            txtIdTpSolPqr_TipoSolicitudPqr.setValue(anxsXTpSolDTO.getIdTpSolPqr_TipoSolicitudPqr());

            if (txtIdAnexoXTpSol == null) {
                txtIdAnexoXTpSol = new InputText();
            }

            txtIdAnexoXTpSol.setValue(anxsXTpSolDTO.getIdAnexoXTpSol());

            Long idAnexoXTpSol = FacesUtils.checkLong(txtIdAnexoXTpSol);
            entity = businessDelegatorView.getAnxsXTpSol(idAnexoXTpSol);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedAnxsXTpSol = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedAnxsXTpSol = null;

        if (txtEsObligatorio != null) {
            txtEsObligatorio.setValue(null);
            txtEsObligatorio.setDisabled(true);
        }

        if (idAnexoPqr != null) {
            idAnexoPqr = null;
        }

        if (idTpSolPqr != null) {
            idTpSolPqr = null;
        }

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }
        
        data = null;
        data = getData();
        
        tipoSolicitudPqr = null;
        tipoSolicitudPqr = getTipoSolicitudPqr();
        
        anexospqr = null;
        anexospqr = getAnexospqr();

        return "";
    }

    public void listener_txtId() {
        try {
            Long idAnexoXTpSol = FacesUtils.checkLong(txtIdAnexoXTpSol);
            entity = (idAnexoXTpSol != null)
                ? businessDelegatorView.getAnxsXTpSol(idAnexoXTpSol) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEsObligatorio.setDisabled(false);
            txtIdAnexoPqr_AnexosPqr.setDisabled(false);
            txtIdTpSolPqr_TipoSolicitudPqr.setDisabled(false);
            txtIdAnexoXTpSol.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEsObligatorio.setValue(entity.getEsObligatorio());
            txtEsObligatorio.setDisabled(false);
            txtIdAnexoPqr_AnexosPqr.setValue(entity.getAnexosPqr()
                                                   .getIdAnexoPqr());
            txtIdAnexoPqr_AnexosPqr.setDisabled(false);
            txtIdTpSolPqr_TipoSolicitudPqr.setValue(entity.getTipoSolicitudPqr()
                                                          .getIdTpSolPqr());
            txtIdTpSolPqr_TipoSolicitudPqr.setDisabled(false);
            txtIdAnexoXTpSol.setValue(entity.getIdAnexoXTpSol());
            txtIdAnexoXTpSol.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedAnxsXTpSol = (AnxsXTpSolDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedAnxsXTpSol"));
        txtEsObligatorio.setValue(selectedAnxsXTpSol.getEsObligatorio());
        txtEsObligatorio.setDisabled(false);
        txtIdAnexoPqr_AnexosPqr.setValue(selectedAnxsXTpSol.getIdAnexoPqr_AnexosPqr());
        txtIdAnexoPqr_AnexosPqr.setDisabled(false);
        txtIdTpSolPqr_TipoSolicitudPqr.setValue(selectedAnxsXTpSol.getIdTpSolPqr_TipoSolicitudPqr());
        txtIdTpSolPqr_TipoSolicitudPqr.setDisabled(false);
        txtIdAnexoXTpSol.setValue(selectedAnxsXTpSol.getIdAnexoXTpSol());
        txtIdAnexoXTpSol.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedAnxsXTpSol == null) && (entity == null)) {
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
            entity = new AnxsXTpSol();

            //Long idAnexoXTpSol = FacesUtils.checkLong(txtIdAnexoXTpSol);
            //entity.setIdAnexoXTpSol(idAnexoXTpSol);            
            entity.setEsObligatorio(esObligatorioSeleccionado);           
            entity.setAnexosPqr((idAnexoPqr != null)
                ? businessDelegatorView.getAnexosPqr(idAnexoPqr) : null);
            entity.setTipoSolicitudPqr((idTpSolPqr != null)
                ? businessDelegatorView.getTipoSolicitudPqr(idTpSolPqr) : null);
            
            businessDelegatorView.saveAnxsXTpSol(entity);
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
                Long idAnexoXTpSol = new Long(selectedAnxsXTpSol.getIdAnexoXTpSol());
                entity = businessDelegatorView.getAnxsXTpSol(idAnexoXTpSol);
            }

            entity.setEsObligatorio(esObligatorioSeleccionado);           
            entity.setAnexosPqr((idAnexoPqr != null)
                ? businessDelegatorView.getAnexosPqr(idAnexoPqr) : null);
            entity.setTipoSolicitudPqr((idTpSolPqr != null)
                ? businessDelegatorView.getTipoSolicitudPqr(idTpSolPqr) : null);
            
            businessDelegatorView.updateAnxsXTpSol(entity);
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
            selectedAnxsXTpSol = (AnxsXTpSolDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedAnxsXTpSol"));

            Long idAnexoXTpSol = new Long(selectedAnxsXTpSol.getIdAnexoXTpSol());
            entity = businessDelegatorView.getAnxsXTpSol(idAnexoXTpSol);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idAnexoXTpSol = FacesUtils.checkLong(txtIdAnexoXTpSol);
            entity = businessDelegatorView.getAnxsXTpSol(idAnexoXTpSol);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteAnxsXTpSol(entity);
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
            selectedAnxsXTpSol = (AnxsXTpSolDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedAnxsXTpSol"));

            Long idAnexoXTpSol = new Long(selectedAnxsXTpSol.getIdAnexoXTpSol());
            entity = businessDelegatorView.getAnxsXTpSol(idAnexoXTpSol);
            businessDelegatorView.deleteAnxsXTpSol(entity);
            data.remove(selectedAnxsXTpSol);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String esObligatorio, Long idAnexoXTpSol,
        Long idAnexoPqr_AnexosPqr, Long idTpSolPqr_TipoSolicitudPqr)
        throws Exception {
        try {
            entity.setEsObligatorio(FacesUtils.checkString(esObligatorio));
            businessDelegatorView.updateAnxsXTpSol(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("AnxsXTpSolView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtEsObligatorio() {
        return txtEsObligatorio;
    }

    public void setTxtEsObligatorio(InputText txtEsObligatorio) {
        this.txtEsObligatorio = txtEsObligatorio;
    }

    public InputText getTxtIdAnexoPqr_AnexosPqr() {
        return txtIdAnexoPqr_AnexosPqr;
    }

    public void setTxtIdAnexoPqr_AnexosPqr(InputText txtIdAnexoPqr_AnexosPqr) {
        this.txtIdAnexoPqr_AnexosPqr = txtIdAnexoPqr_AnexosPqr;
    }

    public InputText getTxtIdTpSolPqr_TipoSolicitudPqr() {
        return txtIdTpSolPqr_TipoSolicitudPqr;
    }

    public void setTxtIdTpSolPqr_TipoSolicitudPqr(
        InputText txtIdTpSolPqr_TipoSolicitudPqr) {
        this.txtIdTpSolPqr_TipoSolicitudPqr = txtIdTpSolPqr_TipoSolicitudPqr;
    }

    public InputText getTxtIdAnexoXTpSol() {
        return txtIdAnexoXTpSol;
    }

    public void setTxtIdAnexoXTpSol(InputText txtIdAnexoXTpSol) {
        this.txtIdAnexoXTpSol = txtIdAnexoXTpSol;
    }

    public List<AnxsXTpSolDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataAnxsXTpSol();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<AnxsXTpSolDTO> anxsXTpSolDTO) {
        this.data = anxsXTpSolDTO;
    }

    public AnxsXTpSolDTO getSelectedAnxsXTpSol() {
        return selectedAnxsXTpSol;
    }

    public void setSelectedAnxsXTpSol(AnxsXTpSolDTO anxsXTpSol) {
        this.selectedAnxsXTpSol = anxsXTpSol;
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
	public Long getIdAnexoPqr() {
		return idAnexoPqr;
	}

	public void setIdAnexoPqr(Long idAnexoPqr) {
		this.idAnexoPqr = idAnexoPqr;
	}

	public List<SelectItem> getAnexospqr() {
		try {
	       	anexospqr = new ArrayList<SelectItem>();
			List<AnexosPqr> anexos = businessDelegatorView.getAnexosPqr();
	       	for (AnexosPqr anex : anexos) {
				anexospqr.add(new SelectItem(anex.getIdAnexoPqr(), anex.getDescripcionAnexo()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return anexospqr;
		
	}

	public void setAnexospqr(List<SelectItem> anexospqr) {
		this.anexospqr = anexospqr;
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
			List<TipoSolicitudPqr> tipos = businessDelegatorView.getTipoSolicitudPqr();
	       	for (TipoSolicitudPqr tipo : tipos) {
	       		tipoSolicitudPqr.add(new SelectItem(tipo.getIdTpSolPqr(), tipo.getDescTpSol()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoSolicitudPqr;
	}

	public void setTipoSolicitudPqr(List<SelectItem> tipoSolicitudPqr) {
		this.tipoSolicitudPqr = tipoSolicitudPqr;
	}

	public String getEsObligatorioSeleccionado() {
		return esObligatorioSeleccionado;
	}

	public void setEsObligatorioSeleccionado(String esObligatorioSeleccionado) {
		this.esObligatorioSeleccionado = esObligatorioSeleccionado;
	}
}