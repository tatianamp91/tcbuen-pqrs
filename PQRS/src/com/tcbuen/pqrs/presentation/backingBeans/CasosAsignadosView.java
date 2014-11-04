package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.SolicitudDTO;
import com.tcbuen.pqrs.presentation.businessDelegate.*;
import com.tcbuen.pqrs.utilities.FacesUtils;

import java.io.File;
import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.imageio.stream.FileImageOutputStream;
import javax.sql.rowset.serial.SerialBlob;

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
	private Blob blob;
	private SolicitudPqr solicitudPqr;
	private InfoSolicitante infoSolicitante;
	private Long idArea;
	private List<SelectItem> areasInvolucradas;
	private List<AnexosPqr> anexosPqr;
    private UploadedFile file;
    private List<UploadedFile> uploadedFiles;
    private boolean anexos;
    private List<RespuestaSol> respuestaSol;
    private List<AnexosRespuesta> anexosRespuestas;
    private String observacion;
    private boolean obser;
    
	

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
			respuestaSol = businessDelegatorView.consultarRespuestasSolicitud(solicitudPqr.getIdSolPqr());
			if(respuestaSol.size() > 0){
				setObser(true);
			}else{
				setObser(false);
			}			
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
	
	public SolicitudAsignadaArea solicitudArea() throws Exception{
		try{
			AreasInvolucradas area = businessDelegatorView.getAreasInvolucradas(idArea);
			SolicitudPqr sol = businessDelegatorView.getSolicitudPqr(selectedSol);
			SolicitudAsignadaArea solicitudAsignadaArea = new SolicitudAsignadaArea();
			solicitudAsignadaArea.setFechaAsignacion(new Date());
			solicitudAsignadaArea.setFechaRespuesta(new Date());
			solicitudAsignadaArea.setSolicitudPqr(sol);
			solicitudAsignadaArea.setAreasInvolucradas(area);
			
			return solicitudAsignadaArea;
			
		}catch(Exception e){
			throw new Exception (e);
		}
	}
	
	public RespuestaSol respuestaSolicitud() throws Exception{
		try{			
			RespuestaSol respuestaSol = new RespuestaSol();
			respuestaSol.setDescObservacion(observacion);
			respuestaSol.setValorReclamacion(1D);
			respuestaSol.setEstadoRegistro("A");
			respuestaSol.setFechaCreacion(new Date());
			respuestaSol.setUsuarioCreador("Admin");
			respuestaSol.setFechaUltimaModificacion(null);
			respuestaSol.setUsuarioUltimaModificacion(null);		
			respuestaSol.setSolicitudAsignadaArea(null);
			
			return respuestaSol;
		}catch(Exception e){
			throw new Exception (e);
		}
	}
	
	 public List<AnexosRespuesta> anexosRespuesta() {
		 List<AnexosRespuesta> anexosRespuesta = new ArrayList<AnexosRespuesta>();
	    	try{
	    		int index = 0;
	    		for (UploadedFile uploadedF : uploadedFiles) {
	    			file = uploadedF;
	    		    if (file != null) {
	    		    	byte[] imageInByte = file.getContents();
	    		    	FileImageOutputStream imageOutput;
	    				imageOutput = new FileImageOutputStream(new File(file.getFileName()));
	    				imageOutput.write(imageInByte, 0, imageInByte.length);
	    				imageOutput.close();
	    		
	    				blob = new SerialBlob(imageInByte);

	    				AnexosRespuesta anexoRespuesta = new AnexosRespuesta();
	    				anexoRespuesta.setDocumentoReal(blob);
	    				anexoRespuesta.setNombreAnexo(file.getFileName());
	    				anexoRespuesta.setNombreBusqueda(file.getFileName());
	    				anexoRespuesta.setEstadoRegistro("A");
	    				anexoRespuesta.setAdjuntoDocumento("S");
	    				AnexosPqr anexo = businessDelegatorView.getAnexosPqr(anexosPqr.get(index).getIdAnexoPqr());
	    				anexoRespuesta.setAnexosPqr(anexo);
	    				anexoRespuesta.setFechaCreacion(new Date());
	    				anexoRespuesta.setUsuarioCreador("Solicitante");
	    				anexoRespuesta.setRespuestaSol(null);
	    				
	    				anexosRespuesta.add(anexoRespuesta);
	    				index = index + 1;
	    		    }
	    	    }
		    } catch (Exception e) {
		        FacesUtils.addErrorMessage(e.getMessage());
		    }
			return anexosRespuesta;
	    }
	
	public void accionGuardarRespuesta() throws Exception {
		try{			
			SolicitudAsignadaArea solicitudAsignadaArea = solicitudArea();
			RespuestaSol respuestaSol = respuestaSolicitud();
			List<AnexosRespuesta> anexosRespuestas = null;
			if(anexos){
            	int size = 0;
            	if(uploadedFiles != null){
            		size = uploadedFiles.size();
            	}
        		if(size == (anexosPqr.size())){
        			anexosRespuestas = anexosRespuesta();
        		}else{
        			throw new Exception ("Los anexos no estan completos");
        		}
        	}			
			businessDelegatorView.saveRespuestaSolicitud(solicitudAsignadaArea, respuestaSol, anexosRespuestas);
			FacesUtils.addInfoMessage("La Respuesta se guardó correctamente");
		}catch(Exception e){
			throw new Exception (e);
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

	public List<RespuestaSol> getRespuestaSol() {
		return respuestaSol;
	}

	public void setRespuestaSol(List<RespuestaSol> respuestaSol) {
		this.respuestaSol = respuestaSol;
	}

	public List<AnexosRespuesta> getAnexosRespuestas() {
		return anexosRespuestas;
	}

	public void setAnexosRespuestas(List<AnexosRespuesta> anexosRespuestas) {
		this.anexosRespuestas = anexosRespuestas;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Long getIdArea() {
		return idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

	public Blob getBlob() {
		return blob;
	}

	public void setBlob(Blob blob) {
		this.blob = blob;
	}

	public boolean isObser() {
		return obser;
	}

	public void setObser(boolean obser) {
		this.obser = obser;
	}
}