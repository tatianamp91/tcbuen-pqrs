package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.SolicitudDTO;
import com.tcbuen.pqrs.presentation.businessDelegate.*;
import com.tcbuen.pqrs.utilities.FacesUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;


@ManagedBean
@ViewScoped
public class CasosAsignadosView implements Serializable {
	private static final long serialVersionUID = 1L;	

	private List<SolicitudDTO> data;
	private SolicitudDTO selectedSolicitudPqr;
	private List<SolicitudPqr> solicitudes;
	private Long selectedSol;
	private boolean showDialog;
	private SolicitudPqr solicitudPqr;
	private InfoSolicitante infoSolicitante;
	private List<SelectItem> areasInvolucradas;
	private List<AnexosPqr> anexosPqr;
    private UploadedFile file;
    private List<UploadedFile> uploadedFiles;
    private boolean anexos;
	

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public CasosAsignadosView() {
		super();
	}
	
	public void administrarCaso() throws Exception {
		try{
		solicitudPqr = businessDelegatorView.getSolicitudPqr(selectedSol);
		if(solicitudPqr != null){
			infoSolicitante = businessDelegatorView.getInfoSolicitante(solicitudPqr.getInfoSolicitante().getIdInfoSolicitante());
		}
		AreasInvolucradas area = businessDelegatorView.getAreasInvolucradas(1L);
		anexosPqr = businessDelegatorView.consultarAnxsXArea(area);
		setShowDialog(true);
		}catch(Exception e){
			throw new Exception(e);
		}
	}

	
    public String action_closeDialog() {
        setShowDialog(false);

        return "";
    }
	
	public String onFlowProcess(FlowEvent event) throws Exception {
    	try{
    		if(anexosPqr.size()>0){
				anexos = true;
			}else{
				anexos = false;
			}
    		return event.getNewStep();
    	}catch(Exception e){
    		FacesUtils.addErrorMessage(e.getMessage()); 
    	}
    	return "";
	}
	
	public void upload(FileUploadEvent event) {
    	try{
    	file = null;
    	int size = 0;
    	file = event.getFile();
    	if(uploadedFiles != null){
    		size = uploadedFiles.size();
    	}
    	if(size < (anexosPqr.size())){		
	        if(file != null) {
	        	int index = 0;
	        	if(uploadedFiles == null){
		        	uploadedFiles = new ArrayList<UploadedFile>();
	        	}
	        	index = uploadedFiles.size();
	        	uploadedFiles.add(index,file);
	        	file = null;
	        	FacesUtils.addInfoMessage("El anexo fue adjuntado correctamente");
	        }
    	}
    	}catch(Exception e){
    		FacesUtils.addInfoMessage("El anexo no pudo ser adjuntado");
    	}
    	
    }
	
	public List<SolicitudDTO> getData() throws Exception {
		try{
			if(data == null){
				/*
				AreasInvolucradas area = businessDelegatorView.getAreasInvolucradas(1L);
				if(area != null){
					data = businessDelegatorView.consultarAsignacion(area);
				}*/
			}
		}catch(Exception e){
			throw new Exception (e);
		}
		return data;
	}

	public void setData(List<SolicitudDTO> data) {
		this.data = data;
	}

	public SolicitudDTO getSelectedSolicitudPqr() {
		return selectedSolicitudPqr;
	}

	public void setSelectedSolicitudPqr(SolicitudDTO selectedSolicitudPqr) {
		this.selectedSolicitudPqr = selectedSolicitudPqr;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(
			IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public List<SolicitudPqr> getSolicitudes() throws Exception {
		try{
			if(solicitudes == null){
				solicitudes = businessDelegatorView.consultarSolicitudes(1L);
			}
		}catch(Exception e){
			throw new Exception (e);
		}
		return solicitudes;
	}

	public void setSolicitudes(List<SolicitudPqr> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public Long getSelectedSol() {
		return selectedSol;
	}

	public void setSelectedSol(Long selectedSol) {
		this.selectedSol = selectedSol;
	}

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}

	public InfoSolicitante getInfoSolicitante() {
		return infoSolicitante;
	}

	public void setInfoSolicitante(InfoSolicitante infoSolicitante) {
		this.infoSolicitante = infoSolicitante;
	}

	public SolicitudPqr getSolicitudPqr() {
		return solicitudPqr;
	}

	public void setSolicitudPqr(SolicitudPqr solicitudPqr) {
		this.solicitudPqr = solicitudPqr;
	}

	public List<SelectItem> getAreasInvolucradas() throws Exception {
		try{
			if(areasInvolucradas == null){
				areasInvolucradas = new ArrayList<SelectItem>();
				List<AreasInvolucradas> areas = businessDelegatorView.getAreasInvolucradas();
				if(areas != null){
					for (AreasInvolucradas area : areas) {
						if(area.getIdAreaInvolucrada() != 1L){
							areasInvolucradas.add(new SelectItem(area.getIdAreaInvolucrada(), area.getNombreArea()));
						}
					}
				}
			}
		}catch(Exception e){
			throw new Exception (e);
		}
		return areasInvolucradas;
	}

	public void setAreasInvolucradas(List<SelectItem> areasInvolucradas) {
		this.areasInvolucradas = areasInvolucradas;
	}

	public List<AnexosPqr> getAnexosPqr() {
		return anexosPqr;
	}

	public void setAnexosPqr(List<AnexosPqr> anexosPqr) {
		this.anexosPqr = anexosPqr;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<UploadedFile> getUploadedFiles() {
		return uploadedFiles;
	}

	public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
		this.uploadedFiles = uploadedFiles;
	}

	public boolean isAnexos() {
		return anexos;
	}

	public void setAnexos(boolean anexos) {
		this.anexos = anexos;
	}
	
}