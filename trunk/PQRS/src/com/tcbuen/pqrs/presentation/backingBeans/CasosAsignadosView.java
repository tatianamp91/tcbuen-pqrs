package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.SolicitudDTO;
import com.tcbuen.pqrs.presentation.businessDelegate.*;
import com.tcbuen.pqrs.utilities.FacesUtils;

import java.io.InputStream;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;


@ManagedBean
@ViewScoped
public class CasosAsignadosView implements Serializable {
	private static final long serialVersionUID = 1L;	

	private SolicitudDTO selectedSolicitudPqr;
	private List<SolicitudPqr> solicitudesSer;
	private List<SolicitudDTO> solicitudesArea;
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
    private Long idRespuesta;
    private List<AnexosRespuesta> anexosRespuestas;
    private String observacion;
    private boolean obser;
    private AreasInvolucradas area;
    private boolean showDialogAnexos;
    private StreamedContent download;
    private List<Boolean> adjuntos;
    private Long estado;
    private SelectBooleanCheckbox check;
    
    private static Session session;
	private static ServiceRegistry serviceRegistry;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public CasosAsignadosView() throws Exception {
		super();
	}
	
	public void limpiar() throws Exception{
		observacion = null;
		file = null;
		uploadedFiles = null;
		idArea = null;
		adjuntos = null;
		action_clear();
		solicitudPqr = null;
		selectedSol = null;
		infoSolicitante = null;
		anexosPqr = null;
		respuestaSol = null;
		idRespuesta = null;
		anexosRespuestas = null;
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
		anexosPqr = businessDelegatorView.consultarAnxsXArea(area);
		adjuntos = new ArrayList<Boolean>();
		for (int i= 0; i<anexosPqr.size(); i++){
			adjuntos.add(false);
		}
		setShowDialog(true);
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	public void anexos() throws Exception {
		try{
			if(idRespuesta != null){
				anexosRespuestas = businessDelegatorView.consultarAnexosRespuesta(idRespuesta);
				setShowDialogAnexos(true);
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}

	
    public String action_closeDialog() throws Exception{
	    setShowDialog(false);
	    action_clear();
        return "";
    }
    
    public String action_clear() throws Exception{
    	try{
	        solicitudesSer = null;
	        solicitudesSer = getSolicitudesSer();
	        solicitudesArea = null;
	        solicitudesArea = getSolicitudesArea();
    	}catch(Exception e){
    		throw new Exception(e);
    	}

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
	        	check = new SelectBooleanCheckbox();
	        	check.setValue(true);
	        	getCheck();
	        	adjuntos.set(index, true);
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
			AreasInvolucradas a = businessDelegatorView.getAreasInvolucradas(idArea);
			SolicitudPqr sol = businessDelegatorView.getSolicitudPqr(selectedSol);
			SolicitudAsignadaArea solicitudAsignadaArea = new SolicitudAsignadaArea();
			solicitudAsignadaArea.setFechaAsignacion(new Date());
			solicitudAsignadaArea.setFechaRespuesta(new Date());
			solicitudAsignadaArea.setSolicitudPqr(sol);
			solicitudAsignadaArea.setAreasInvolucradas(a);
			
			return solicitudAsignadaArea;
			
		}catch(Exception e){
			throw new Exception (e);
		}
	}
	
	public RespuestaSol respuestaSolicitud() throws Exception{
		try{	
			HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	        long usuario =  Long.parseLong(httpSession.getAttribute("usuario").toString());
	        UsuariosInternos usu = businessDelegatorView.getUsuariosInternos(usuario);
	        
			RespuestaSol respuestaSol = new RespuestaSol();
			respuestaSol.setDescObservacion(observacion);
			respuestaSol.setValorReclamacion(1D);
			respuestaSol.setEstadoRegistro("A");
			respuestaSol.setFechaCreacion(new Date());
			respuestaSol.setUsuarioCreador(usu.getLogin());
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
				HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		        long usuario =  Long.parseLong(httpSession.getAttribute("usuario").toString());
		        UsuariosInternos usu = businessDelegatorView.getUsuariosInternos(usuario);
	    		
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
	    				anexoRespuesta.setUsuarioCreador(usu.getLogin());
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
	 
	public String respuestaCliente () throws Exception{
		try{
			idArea = area.getIdAreaInvolucrada();
			boolean valida = accionGuardarRespuesta();
			if(valida){
				businessDelegatorView.send(solicitudPqr.getInfoSolicitante().getCorreoElectronico(),"Respuesta Solicitud", observacion);
				setShowDialog(false);
				estado = 3L;
				cambiarEstadoSol();
				FacesUtils.addInfoMessage("Se envio respuesta correctamente");
			}
		} catch (Exception e) {
	        FacesUtils.addErrorMessage(e.getMessage());
		}
		return null;
	}
	
	public boolean accionGuardarRespuesta() throws Exception {
		try{			
			if (idArea != -1 && idArea != null) {
				SolicitudAsignadaArea solicitudAsignadaArea = solicitudArea();
				RespuestaSol respuestaSol = respuestaSolicitud();
				List<AnexosRespuesta> anexosRespuestas = null;
				if (anexos) {
					int size = 0;
					if (uploadedFiles != null) {
						if(uploadedFiles.size() > 0){
							size = uploadedFiles.size();
						}
					}
					if (size == (anexosPqr.size()) && size != 0) {
						anexosRespuestas = anexosRespuesta();
					}else{
						throw new Exception("Los anexos no estan completos");
					}
				}
				businessDelegatorView.saveRespuestaSolicitud(solicitudAsignadaArea, respuestaSol, anexosRespuestas);
				FacesUtils.addInfoMessage("La Respuesta se guardó correctamente");
				estado = 2L;
				cambiarEstadoSol();
				action_closeDialog();
				limpiar();
				return true;
			} else {
				throw new Exception("El area no puede ser vacia");
			}
		}catch(Exception e){
			 FacesUtils.addErrorMessage(e.getMessage());
		}
		return false;
	}
	
	public void cambiarEstadoSol () throws Exception{
		try{
			TipoEstadoPqr tipoEstadoPqr = businessDelegatorView.getTipoEstadoPqr(estado);
			solicitudPqr.setTipoEstadoPqr(tipoEstadoPqr);
			businessDelegatorView.updateSolicitudPqr(solicitudPqr);
		}catch(Exception e){
			 FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	public void cargarArea() throws Exception{
		try{
			HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		    long usuario =  Long.parseLong(httpSession.getAttribute("usuario").toString());
		    UsuariosInternos usu = businessDelegatorView.getUsuariosInternos(usuario);
		    area = usu.getAreasInvolucradas();        
		}catch(Exception e){
			throw new Exception (e);
		}
	}
	
	public void download(AnexosRespuesta anexos) throws Exception {		
		InputStream stream = (InputStream) anexos.getDocumentoReal();
        download = new DefaultStreamedContent(stream, "", anexos.getNombreAnexo());
	}
	
	public StreamedContent fileDownload(AnexosRespuesta respuestaAnexos) {
		StreamedContent file = null;
		try {
			initSession();
			AnexosRespuesta imagen = (AnexosRespuesta) session.get(
					AnexosRespuesta.class, respuestaAnexos.getIdAnxResp());

			Blob blob = new SerialBlob(imagen.getDocumentoReal());
			byte[] blobBytes = blob.getBytes(1, (int) blob.length());

			InputStream stream = new ByteArrayInputStream(blobBytes);

			file = new DefaultStreamedContent(stream,
					respuestaAnexos.getNombreBusqueda(), respuestaAnexos.getNombreAnexo());
			endSession();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			endSession();
		}

		return file;
	}
	
	private static void initSession() {
		Configuration configuration = new Configuration().configure();
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();

		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		session = sessionFactory.openSession();
		session.beginTransaction();
	}

	private static void endSession() {
		session.getTransaction().commit();
		session.close();

		StandardServiceRegistryBuilder.destroy(serviceRegistry);
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

	public List<SolicitudPqr> getSolicitudesSer() throws Exception {
		try{
			if(solicitudesSer == null){
				cargarArea();
				solicitudesSer = businessDelegatorView.consultarSolicitudes(area.getIdAreaInvolucrada());
			}
		}catch(Exception e){
			throw new Exception (e);
		}
		return solicitudesSer;
	}

	public void setSolicitudesSer(List<SolicitudPqr> solicitudesSer) {
		this.solicitudesSer = solicitudesSer;
	}	

	public List<SolicitudDTO> getSolicitudesArea() throws Exception {
		try{       
			if(solicitudesArea == null){
				cargarArea(); 
				solicitudesArea = businessDelegatorView.consultarAsignacion(area);
			}
		}catch(Exception e){
			throw new Exception (e);
		}
		return solicitudesArea;
	}

	public void setSolicitudesArea(List<SolicitudDTO> solicitudesArea) {
		this.solicitudesArea = solicitudesArea;
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
					for (AreasInvolucradas a: areas) {
						if(a.getIdAreaInvolucrada() != area.getIdAreaInvolucrada()){
							if(!a.getNombreArea().trim().equals("admin")){
								areasInvolucradas.add(new SelectItem(a.getIdAreaInvolucrada(), a.getNombreArea()));
							}
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
	public AreasInvolucradas getArea() {
		return area;
	}
	public void setArea(AreasInvolucradas area) {
		this.area = area;
	}
	public boolean isShowDialogAnexos() {
		return showDialogAnexos;
	}
	public void setShowDialogAnexos(boolean showDialogAnexos) {
		this.showDialogAnexos = showDialogAnexos;
	}
	public Long getIdRespuesta() {
		return idRespuesta;
	}
	public void setIdRespuesta(Long idRespuesta) {
		this.idRespuesta = idRespuesta;
	}
	public StreamedContent getDownload() {
		return download;
	}
	public void setDownload(StreamedContent download) {
		this.download = download;
	}
	public List<Boolean> getAdjuntos() {
		return adjuntos;
	}
	public void setAdjuntos(List<Boolean> adjuntos) {
		this.adjuntos = adjuntos;
	}
	public Long getEstado() {
		return estado;
	}
	public void setEstado(Long estado) {
		this.estado = estado;
	}
	public SelectBooleanCheckbox getCheck() {
		return check;
	}
	public void setCheck(SelectBooleanCheckbox check) {
		this.check = check;
	}	
}