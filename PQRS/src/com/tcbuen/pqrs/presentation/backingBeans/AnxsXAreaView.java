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
import org.primefaces.model.DualListModel;

import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
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
    private List<AreasInvolucradas> areas;
    private AnxsXAreaDTO selectedAnxsXArea;
    private AnxsXArea entity;
    private boolean showDialog;
    private Long idAnexoPqr;
    private Long idAreaInvolucrada;
    private List<SelectItem> areasInvolucradas;
    private String esObligatorioSeleccionado;
    private DualListModel<AnexosPqr> anexosPqr;
	private List<AnexosPqr> anexosPqrSource;
	private List<AnexosPqr> anexosPqrTarget;
	private List<AnexosPqr> anexosPqrTargetCopia;
	private AreasInvolucradas areaInvolucrada;
	private Boolean boton;
	
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public AnxsXAreaView() {
        super();        
    }
    
	@PostConstruct
	public void init() {
		try {
			consultarElementosNuevo();
			anexosPqrTargetCopia = null;
			data = getData();
			setBoton(false);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
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
        	FacesUtils.addErrorMessage(ex.getMessage());
        }
    }
    
	public String consultarElementosNuevo() {
		try {
			anexosPqrSource = new ArrayList<AnexosPqr>();
			anexosPqrTarget = new ArrayList<AnexosPqr>();
			anexosPqrSource = businessDelegatorView.consultarAnexos();
			anexosPqrTargetCopia = null;
			anexosPqr = new DualListModel<AnexosPqr>(anexosPqrSource, anexosPqrTarget);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}
	
	public String consultarElementosModificar(AreasInvolucradas area) {
		try {
			anexosPqrSource = new ArrayList<AnexosPqr>();
			anexosPqrTarget = new ArrayList<AnexosPqr>();
			anexosPqrSource = businessDelegatorView.consultarAnxsNoArea(area);
			anexosPqrTarget = businessDelegatorView.consultarAnxsXArea(area);
			anexosPqrTargetCopia = anexosPqrTarget;
			anexosPqr = new DualListModel<AnexosPqr>(anexosPqrSource, anexosPqrTarget);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

    public String action_new() {
    	action_clear();
	    selectedAnxsXArea = null;	        
	    if(areasInvolucradas.size() > 0){
	    	setShowDialog(true);
	    }else{
	    	FacesUtils.addInfoMessage("No hay areas pendientes por configurar");
    	}
        return "";
    }
    
	public String action_edit() {
		try {
			areaInvolucrada = businessDelegatorView.getAreasInvolucradas(idAreaInvolucrada);
			consultarElementosModificar(areaInvolucrada);
			setShowDialog(true);
			setBoton(true);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return "";
	}

    public String action_clear() {
        entity = null;
        selectedAnxsXArea = null;
              
        data = null;
	    data = getData();
	    areas = null;
	    areas = getAreas();
	    areasInvolucradas = null;
	    idAreaInvolucrada = null;
	    setBoton(false);
	    areasInvolucradas = getAreasInvolucradas();	
		consultarElementosNuevo();

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
            if (!boton) {
            	if(idAreaInvolucrada != null && anexosPqr.getTarget().size() > 0){
	                action_crear();
	                FacesUtils.addInfoMessage("Los anexos del area se guardaron exitosamente");
	                setShowDialog(false);
            	}else{
            		consultarElementosNuevo();
            		FacesUtils.addInfoMessage("Debe seleccionar area y por lo menos un anexo");
            	}
            } else {
            	action_crear();
            	FacesUtils.addInfoMessage("Los anexos del area se modificaron exitosamente");
            	setShowDialog(false);
            }
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }
        return "";
    }
    
    public String action_crear(){
	    try {
			AreasInvolucradas areas = businessDelegatorView.getAreasInvolucradas(idAreaInvolucrada);
			anexosPqrTarget = anexosPqr.getTarget();
			esObligatorioSeleccionado = "S";
			
			businessDelegatorView.save_anxs_x_area(areas, anexosPqrTargetCopia,
					anexosPqrTarget, esObligatorioSeleccionado);            
	        action_clear();
	    } catch (Exception e) {
	        entity = null;
	        FacesUtils.addErrorMessage(e.getMessage());
	    }
    	
    	return "";
    }

    public String action_create() {
        try {
            entity = new AnxsXArea();
            entity.setEsObligatorio(esObligatorioSeleccionado);           
            entity.setAnexosPqr((idAnexoPqr != null)
                ? businessDelegatorView.getAnexosPqr(idAnexoPqr) : null);
            entity.setAreasInvolucradas((idAreaInvolucrada != null)
                ? businessDelegatorView.getAreasInvolucradas((idAreaInvolucrada)): null);
            
            businessDelegatorView.saveAnxsXArea(entity);            
            FacesUtils.addInfoMessage("El anexo se guardó exitosamente");            
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
            FacesUtils.addInfoMessage("El anexo se modifico exitosamente");
            
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

	public Long getIdAreaInvolucrada() {		
		return idAreaInvolucrada;
	}

	public void setIdAreaInvolucrada(Long idAreaInvolucrada) {
		this.idAreaInvolucrada = idAreaInvolucrada;
	}

	public List<SelectItem> getAreasInvolucradas() {
		try {
			if(areasInvolucradas == null){
		       	areasInvolucradas = new ArrayList<SelectItem>();
		       	List<AreasInvolucradas> areas = new ArrayList<AreasInvolucradas>();
		       	if(!boton){
					areas = businessDelegatorView.consultarNoAreaXAnxs();
		       	}else{
		       		areas = businessDelegatorView.getAreasInvolucradas();
		       	}
		       	for (AreasInvolucradas area : areas) {
					areasInvolucradas.add(new SelectItem(area.getIdAreaInvolucrada(), area.getNombreArea()));
				}
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

	public List<AnexosPqr> getAnexosPqrSource() {
		return anexosPqrSource;
	}
	public DualListModel<AnexosPqr> getAnexosPqr() {
		return anexosPqr;
	}
	
	public void setAnexosPqr(DualListModel<AnexosPqr> anexosPqr) {
		this.anexosPqr = anexosPqr;
	}	

	public void setAnexosPqrSource(List<AnexosPqr> anexosPqrSource) {
		this.anexosPqrSource = anexosPqrSource;
	}

	public List<AnexosPqr> getAnexosPqrTarget() {
		return anexosPqrTarget;
	}

	public void setAnexosPqrTarget(List<AnexosPqr> anexosPqrTarget) {
		this.anexosPqrTarget = anexosPqrTarget;
	}

	public List<AnexosPqr> getAnexosPqrTargetCopia() {
		return anexosPqrTargetCopia;
	}

	public void setAnexosPqrTargetCopia(List<AnexosPqr> anexosPqrTargetCopia) {
		this.anexosPqrTargetCopia = anexosPqrTargetCopia;
	}

	public Boolean getBoton() {
		return boton;
	}

	public void setBoton(Boolean boton) {
		this.boton = boton;
	}

	public List<AreasInvolucradas> getAreas() {
		try{
			if(areas == null){
				areas = businessDelegatorView.consultarTodasAreaXAnxs();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return areas;
	}

	public void setAreas(List<AreasInvolucradas> areas) {
		this.areas = areas;
	}

	public AreasInvolucradas getAreaInvolucrada() {
		return areaInvolucrada;
	}

	public void setAreaInvolucrada(AreasInvolucradas areaInvolucrada) {
		this.areaInvolucrada = areaInvolucrada;
	}		
}