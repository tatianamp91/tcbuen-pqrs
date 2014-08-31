package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.exceptions.*;
import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.AnxsXAreaDTO;
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
public class AnxsXAreaView implements Serializable {
    private static final long serialVersionUID = 1L;
    private InputText txtEsObligatorio;
    private InputText txtIdAnexoPqr_AnexosPqr;
    private InputText txtIdAreaInvolucrada_AreasInvolucradas;
    private InputText txtIdAnxXArea;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<AnxsXAreaDTO> data;
    private AnxsXAreaDTO selectedAnxsXArea;
    private AnxsXArea entity;
    private boolean showDialog;
    private Long idAnexoPqr;
    private List<SelectItem> anexospqr;
    private Long idAreaInvolucrada;
    private List<SelectItem> areasInvolucradas;
    private String esObligatorioSeleccionado;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public AnxsXAreaView() {
        super();
        
    }

    public void rowEventListener(RowEditEvent e) {
        try {
            AnxsXAreaDTO anxsXAreaDTO = (AnxsXAreaDTO) e.getObject();

            if (txtEsObligatorio == null) {
                txtEsObligatorio = new InputText();
            }

            txtEsObligatorio.setValue(anxsXAreaDTO.getEsObligatorio());

            if (txtIdAnexoPqr_AnexosPqr == null) {
                txtIdAnexoPqr_AnexosPqr = new InputText();
            }

            txtIdAnexoPqr_AnexosPqr.setValue(anxsXAreaDTO.getIdAnexoPqr_AnexosPqr());

            if (txtIdAreaInvolucrada_AreasInvolucradas == null) {
                txtIdAreaInvolucrada_AreasInvolucradas = new InputText();
            }

            txtIdAreaInvolucrada_AreasInvolucradas.setValue(anxsXAreaDTO.getIdAreaInvolucrada_AreasInvolucradas());

            if (txtIdAnxXArea == null) {
                txtIdAnxXArea = new InputText();
            }

            txtIdAnxXArea.setValue(anxsXAreaDTO.getIdAnxXArea());

            Long idAnxXArea = FacesUtils.checkLong(txtIdAnxXArea);
            entity = businessDelegatorView.getAnxsXArea(idAnxXArea);

            action_modify();
        } catch (Exception ex) {
        }
    }

    public String action_new() {
        action_clear();
        selectedAnxsXArea = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedAnxsXArea = null;

        if (txtEsObligatorio != null) {
            txtEsObligatorio.setValue(null);
            txtEsObligatorio.setDisabled(true);
        }

        if (idAnexoPqr != null) {
            idAnexoPqr = null;
        }

        if (idAreaInvolucrada != null) {
            idAreaInvolucrada = null;
        }

        if (btnSave != null) {
            btnSave.setDisabled(false);
        }
        
        data = null;
        data = getData();
        
        areasInvolucradas = null;
        areasInvolucradas = getAreasInvolucradas();
        
        anexospqr = null;
        anexospqr = getAnexospqr();

        return "";
    }

    public void listener_txtId() {
        try {
            Long idAnxXArea = FacesUtils.checkLong(txtIdAnxXArea);
            entity = (idAnxXArea != null)
                ? businessDelegatorView.getAnxsXArea(idAnxXArea) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtEsObligatorio.setDisabled(false);
            txtIdAnexoPqr_AnexosPqr.setDisabled(false);
            txtIdAreaInvolucrada_AreasInvolucradas.setDisabled(false);
            txtIdAnxXArea.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtEsObligatorio.setValue(entity.getEsObligatorio());
            txtEsObligatorio.setDisabled(false);
            txtIdAnexoPqr_AnexosPqr.setValue(entity.getAnexosPqr()
                                                   .getIdAnexoPqr());
            txtIdAnexoPqr_AnexosPqr.setDisabled(false);
            txtIdAreaInvolucrada_AreasInvolucradas.setValue(entity.getAreasInvolucradas()
                                                                  .getIdAreaInvolucrada());
            txtIdAreaInvolucrada_AreasInvolucradas.setDisabled(false);
            txtIdAnxXArea.setValue(entity.getIdAnxXArea());
            txtIdAnxXArea.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedAnxsXArea = (AnxsXAreaDTO) (evt.getComponent().getAttributes()
                                               .get("selectedAnxsXArea"));
        txtEsObligatorio.setValue(selectedAnxsXArea.getEsObligatorio());
        txtEsObligatorio.setDisabled(false);
        txtIdAnexoPqr_AnexosPqr.setValue(selectedAnxsXArea.getIdAnexoPqr_AnexosPqr());
        txtIdAnexoPqr_AnexosPqr.setDisabled(false);
        txtIdAreaInvolucrada_AreasInvolucradas.setValue(selectedAnxsXArea.getIdAreaInvolucrada_AreasInvolucradas());
        txtIdAreaInvolucrada_AreasInvolucradas.setDisabled(false);
        txtIdAnxXArea.setValue(selectedAnxsXArea.getIdAnxXArea());
        txtIdAnxXArea.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedAnxsXArea == null) && (entity == null)) {
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
            entity = new AnxsXArea();

            //Long idAnxXArea = FacesUtils.checkLong(txtIdAnxXArea);
            //entity.setIdAnxXArea(idAnxXArea);
            entity.setEsObligatorio(esObligatorioSeleccionado);           
            entity.setAnexosPqr((idAnexoPqr != null)
                ? businessDelegatorView.getAnexosPqr(idAnexoPqr) : null);
            entity.setAreasInvolucradas((idAreaInvolucrada != null)
                ? businessDelegatorView.getAreasInvolucradas((idAreaInvolucrada)): null);
            
            businessDelegatorView.saveAnxsXArea(entity);
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
                Long idAnxXArea = new Long(selectedAnxsXArea.getIdAnxXArea());
                entity = businessDelegatorView.getAnxsXArea(idAnxXArea);
            }
            
            entity.setEsObligatorio(esObligatorioSeleccionado);           
            entity.setAnexosPqr((idAnexoPqr != null)
                ? businessDelegatorView.getAnexosPqr(idAnexoPqr) : null);
            entity.setAreasInvolucradas((idAreaInvolucrada != null)
                ? businessDelegatorView.getAreasInvolucradas((idAreaInvolucrada)): null);
            
            businessDelegatorView.updateAnxsXArea(entity);
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
            selectedAnxsXArea = (AnxsXAreaDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedAnxsXArea"));

            Long idAnxXArea = new Long(selectedAnxsXArea.getIdAnxXArea());
            entity = businessDelegatorView.getAnxsXArea(idAnxXArea);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long idAnxXArea = FacesUtils.checkLong(txtIdAnxXArea);
            entity = businessDelegatorView.getAnxsXArea(idAnxXArea);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteAnxsXArea(entity);
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
            selectedAnxsXArea = (AnxsXAreaDTO) (evt.getComponent()
                                                   .getAttributes()
                                                   .get("selectedAnxsXArea"));

            Long idAnxXArea = new Long(selectedAnxsXArea.getIdAnxXArea());
            entity = businessDelegatorView.getAnxsXArea(idAnxXArea);
            businessDelegatorView.deleteAnxsXArea(entity);
            data.remove(selectedAnxsXArea);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modifyWitDTO(String esObligatorio, Long idAnxXArea,
        Long idAnexoPqr_AnexosPqr, Long idAreaInvolucrada_AreasInvolucradas)
        throws Exception {
        try {
            entity.setEsObligatorio(FacesUtils.checkString(esObligatorio));
            businessDelegatorView.updateAnxsXArea(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("AnxsXAreaView").requestRender();
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

    public InputText getTxtIdAreaInvolucrada_AreasInvolucradas() {
        return txtIdAreaInvolucrada_AreasInvolucradas;
    }

    public void setTxtIdAreaInvolucrada_AreasInvolucradas(
        InputText txtIdAreaInvolucrada_AreasInvolucradas) {
        this.txtIdAreaInvolucrada_AreasInvolucradas = txtIdAreaInvolucrada_AreasInvolucradas;
    }

    public InputText getTxtIdAnxXArea() {
        return txtIdAnxXArea;
    }

    public void setTxtIdAnxXArea(InputText txtIdAnxXArea) {
        this.txtIdAnxXArea = txtIdAnxXArea;
    }

    public List<AnxsXAreaDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataAnxsXArea();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<AnxsXAreaDTO> anxsXAreaDTO) {
        this.data = anxsXAreaDTO;
    }

    public AnxsXAreaDTO getSelectedAnxsXArea() {
        return selectedAnxsXArea;
    }

    public void setSelectedAnxsXArea(AnxsXAreaDTO anxsXArea) {
        this.selectedAnxsXArea = anxsXArea;
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

	public Long getIdAreaInvolucrada() {
		
		return idAreaInvolucrada;
	}

	public void setIdAreaInvolucrada(Long idAreaInvolucrada) {
		this.idAreaInvolucrada = idAreaInvolucrada;
	}

	public List<SelectItem> getAreasInvolucradas() {
		try {
	       	areasInvolucradas = new ArrayList<SelectItem>();
			List<AreasInvolucradas> areas = businessDelegatorView.getAreasInvolucradas();
	       	for (AreasInvolucradas area : areas) {
				areasInvolucradas.add(new SelectItem(area.getIdAreaInvolucrada(), area.getNombreArea()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return areasInvolucradas;
	}

	public void setAreasInvolucradas(List<SelectItem> areasInvolucradas) {
		this.areasInvolucradas = areasInvolucradas;
	}

	public String getEsObligatorioSeleccionado() {
		return esObligatorioSeleccionado;
	}

	public void setEsObligatorioSeleccionado(String esObligatorioSeleccionado) {
		this.esObligatorioSeleccionado = esObligatorioSeleccionado;
	}
}