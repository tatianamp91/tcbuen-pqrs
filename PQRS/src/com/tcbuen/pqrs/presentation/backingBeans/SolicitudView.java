package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.presentation.businessDelegate.*;
import com.tcbuen.pqrs.utilities.*;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.crypto.AEADBadTagException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.imageio.stream.FileImageOutputStream;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

@ManagedBean
@ViewScoped
public class SolicitudView implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean anexos;
    private boolean solRealizar;
    private boolean sol;
    private boolean recl;
	private Blob blob;
    
    //Tab 1
    private Long idTipoDocumento;
    private List<SelectItem> tipoDocumento;
    private String numeroIdentificacion;
    private String maskNit;
    private InputText txtNombreEmpresa;
    private InputText txtNombreContacto;    
    private InputText txtCorreoElectronico;
    private String numeroCelular;    
    private String telefonoFijo;
    private CommandButton limpiarInfosol;
    private CommandButton siguienteInfoSol;
    
    //Tab2
    private Long idTipoSolicitud;
    private List<TipoSolicitudPqr> tipoSolicitud;
    private TipoSolicitudPqr tipoSolicitudPqr;
    private CommandButton atrasTipoSol;
    private CommandButton siguienteTipoSol;
    
    //tab 3
    private String descTpSol;
    private String descripcionParametro;
    private List<AnexosPqr> anexosPqrs;
    private CommandButton atrasInstructivo;
    private CommandButton siguienteInstructivo;     
    
    //Tab 4
    private Long idTipoEstado;
    private InputText txtNombreCliente;
    private InputText txtNombreAgenciaAduana;
    private String descripcionCaso;
    private String solicitudARealizar;
    private String motSolicitud;
    private List<String> motivoSolicitud;
    private String motReclamacion;
    private List<String> motivoReclamacion;
    private UploadedFile file;
    private List<UploadedFile> uploadedFiles;
    private List<AnexosSolicitante> anexosSolicitantes;
    private CommandButton atrasSolicitud;
    private CommandButton limpiarSolicitud;
    private CommandButton guardarSolicitud;   
    
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public SolicitudView() {
        super();
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
    public String onFlowProcess(FlowEvent event) {
    	try{			
			if(idTipoSolicitud != null){
	    		tipoSolicitudPqr = businessDelegatorView.getTipoSolicitudPqr(idTipoSolicitud);
	    		if(tipoSolicitudPqr != null){
	    			descTpSol = tipoSolicitudPqr.getDescTpSol().toLowerCase();
					ParametrosPqr descripcion = obtenerParametro(descTpSol);
					if(descripcion != null){
						setDescripcionParametro(descripcion.getValorParam());
					}else{
						setDescripcionParametro(null);
					}
					anexosPqrs = consultarAnexosPqrs(tipoSolicitudPqr);
					if(anexosPqrs.size()>0){
						anexos = true;
					}else{
						anexos = false;
					}
					ParametrosPqr campo  = obtenerParametro("solicitud a realizar");
					if(campo != null){
						if(descTpSol.equals(campo.getValorParam())){
							solRealizar = true;
						}else{
							solRealizar = false;
						}
					}
					txtNombreCliente.setValue(txtNombreContacto.getValue());
		    		motivoSolicitud = consultarMotivoSolicitud(tipoSolicitudPqr);
		    		if(motivoSolicitud.size()>0){
		    			sol = true;
		    		}else{
		    			sol = false;
		    		}
		    		motivoReclamacion = consultarMotivoReclamacion(tipoSolicitudPqr);
		    		if(motivoReclamacion.size()>0){
		    			recl = true;
		    		}else{
		    			recl = false;
		    		}
		    		ParametrosPqr parametro = obtenerParametro(("tipo estado inicial pqr").toLowerCase());
		    		if(parametro != null){
			    		TipoEstadoPqr tipoEstadoPqr = obtenerEstado(parametro.getValorParam().toLowerCase());
			    		if(tipoEstadoPqr != null){
			    			idTipoEstado = tipoEstadoPqr.getIdTpEstPqr();
			    		}
		    		}
	    		}
			}
		    return event.getNewStep();
    	}catch(Exception e){
    		FacesUtils.addErrorMessage(e.getMessage());
    	}
    	return "";
    }
    
    public String action_clear_infoSol(){
    	idTipoDocumento = null;
        tipoDocumento = null;
        tipoDocumento = getTipoDocumento();
        numeroIdentificacion = null;
        maskNit = "";
        txtNombreEmpresa.setValue(null);
        txtNombreContacto.setValue(null);    
        txtCorreoElectronico.setValue(null);
        numeroCelular = null;    
        telefonoFijo = null;
        
        uploadedFiles = null;
        blob = null;
        idTipoSolicitud = null;
        idTipoEstado = null;
        txtNombreCliente.setValue(null);
        txtNombreAgenciaAduana.setValue(null);
        tipoSolicitud = null;
        tipoSolicitud = getTipoSolicitud();
        tipoSolicitudPqr = null;
        descTpSol = null;
        descripcionParametro = null;
        anexosPqrs = null;        
        descripcionCaso = null;
        solicitudARealizar = null;        
        motSolicitud = null;
        motivoSolicitud = null;
        motReclamacion = null;
        motivoReclamacion = null;
        
        return "";
    }
   
    public String action_clear_formulario() {
        uploadedFiles = null;
        blob = null;
        txtNombreAgenciaAduana.setValue(null);
        descripcionCaso = null;
        solicitudARealizar = null;
        motSolicitud = null;
        motivoSolicitud = null;
        motivoSolicitud = consultarMotivoSolicitud(tipoSolicitudPqr);        
        motReclamacion = null;
        motivoReclamacion = null;
        motivoReclamacion = consultarMotivoReclamacion(tipoSolicitudPqr);

        return "";
    }
    
    public void upload(FileUploadEvent event) {
    	file = event.getFile();
    	if((uploadedFiles.size()) < (anexosPqrs.size())){		
	        if(file != null) {
	        	int index = 0;
	        	if(uploadedFiles == null){
		        	uploadedFiles = new ArrayList<UploadedFile>();
	        	}
	        	index = uploadedFiles.size();
	        	uploadedFiles.add(index,file);
	        	file = null;
	        }
    	}
    }

    public String action_save() {
        try {
        	String nombreAgenciaAduana = (txtNombreAgenciaAduana.getValue()) != null ? txtNombreAgenciaAduana.getValue().toString() : null;
        	
        	if(revizarCamposSolicitud(nombreAgenciaAduana, motSolicitud, motReclamacion, descripcionCaso, solicitudARealizar)){
        		InfoSolicitante infoSol = null;
        		SolicitudPqr solicitudPqr = null;
        		MotSolSelect motSolSelect = null;
        		MotReclSelect motReclSelect = null;
        		SolicitudAsignadaArea solicitudAsignadaArea = null;
        		List<AnexosSolicitante> anexosSolicitantes = null;
        		
	        	infoSol = action_create_infoSol();
	        	solicitudPqr = action_create_solicitudPqr();
	        	if(sol){
	        		motSolSelect = action_create_motSolSelect();
	        	}
	        	motReclSelect = action_create_motReclSelect();
	        	if(solRealizar){
	        		solicitudAsignadaArea = action_create_areaAsignada();
	        	}
	        	if(anexos){
	        		if((uploadedFiles.size()) == (anexosPqrs.size())){
	        			anexosSolicitantes = action_create_anexosSolicitante();
	        		}else{
	        			throw new Exception ("Los anexos no estan completos");
	        		}
	        	}
	        	
	        	businessDelegatorView.saveSolicitud(infoSol, solicitudPqr, motSolSelect, motReclSelect, solicitudAsignadaArea, anexosSolicitantes);
	        	FacesUtils.addInfoMessage("La Solicitud fue enviada correctamente");
        	}
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }
        return "";
    }

    public InfoSolicitante action_create_infoSol() {
    	InfoSolicitante infoSol = new InfoSolicitante();
        try {        	           
            infoSol.setTipoDocumento((idTipoDocumento != null)
                ? businessDelegatorView.getTipoDocumento(idTipoDocumento) : null);
            infoSol.setNumeroIdentificacion(numeroIdentificacion);
            infoSol.setNombreEmpresa(FacesUtils.checkString(txtNombreEmpresa));
            infoSol.setNombreContacto(FacesUtils.checkString(txtNombreContacto));
            infoSol.setCorreoElectronico(FacesUtils.checkString(txtCorreoElectronico));         
            infoSol.setNumeroCelular(numeroCelular);
            infoSol.setTelefonoFijo(telefonoFijo);          
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }
        return infoSol;
    }   
    
    public SolicitudPqr action_create_solicitudPqr() {
    	SolicitudPqr solicitudPqr = new SolicitudPqr();
    	try{          	    	
	    	solicitudPqr.setNumeroRadicacion("1");//Pendiente
	    	solicitudPqr.setNombreCliente(FacesUtils.checkString(txtNombreCliente));
	    	solicitudPqr.setNombreAgenciaAduana(FacesUtils.checkString(txtNombreAgenciaAduana));
	    	solicitudPqr.setDescripcionCaso(descripcionCaso);
	    	solicitudPqr.setSolicitudARealizar(solicitudARealizar);
	    	solicitudPqr.setUsuarioCreador("Admin"); //pendiente
	    	solicitudPqr.setFechaCreacion(new Date());
	    	solicitudPqr.setUsuarioUltimaModificacion(null);
	    	solicitudPqr.setFechaUltimaModificacion(null);
	    	solicitudPqr.setInfoSolicitante(null);	    	
	    	solicitudPqr.setTipoEstadoPqr(idTipoEstado != null
	            ? businessDelegatorView.getTipoEstadoPqr(idTipoEstado) : null);	    	
	    	solicitudPqr.setTipoSolicitudPqr(idTipoSolicitud != null
	            ? businessDelegatorView.getTipoSolicitudPqr(idTipoSolicitud) : null);
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }
    	return solicitudPqr;
    }
    
    public MotSolSelect action_create_motSolSelect() {
    	MotSolSelect motSolSelect = new MotSolSelect();
    	try{   
    		MotivoSolicitud motivoSolicitud = obtenerMotSol(motSolicitud);

    		motSolSelect.setMotivoSolicitud(motivoSolicitud != null
                ? businessDelegatorView.getMotivoSolicitud(motivoSolicitud.getIdMotSol()) : null);
    		motSolSelect.setSolicitudPqr(null);   		
	    } catch (Exception e) {
	        FacesUtils.addErrorMessage(e.getMessage());
	    }
		return motSolSelect;
    }
    
    public MotReclSelect action_create_motReclSelect() {
    	MotReclSelect motReclSelect = new MotReclSelect();
    	try{    
    		MotivoReclamacion motivoReclamacion = obtenerMotRecl(motReclamacion);
    		
    		motReclSelect.setMotivoReclamacion(motivoReclamacion != null
                ? businessDelegatorView.getMotivoReclamacion(motivoReclamacion.getIdMotRecl()) : null);
    		motReclSelect.setSolicitudPqr(null);
	    } catch (Exception e) {
	        FacesUtils.addErrorMessage(e.getMessage());
	    }
		return motReclSelect;
    }
    
    public SolicitudAsignadaArea action_create_areaAsignada() {
    	SolicitudAsignadaArea solicitudAsignadaArea = new SolicitudAsignadaArea();
    	try{
    		ParametrosPqr parametrosPqr = obtenerParametro(("area incial asignada").toLowerCase());
    		AreasInvolucradas areaInvolucrada = obtenerArea(parametrosPqr.getValorParam().toLowerCase());
    		
    		solicitudAsignadaArea.setFechaAsignacion(new Date());
    		solicitudAsignadaArea.setFechaRespuesta(null);
    		solicitudAsignadaArea.setAreasInvolucradas(areaInvolucrada != null
                ? businessDelegatorView.getAreasInvolucradas(areaInvolucrada.getIdAreaInvolucrada()) : null);
    		solicitudAsignadaArea.setSolicitudPqr(null);	
	    } catch (Exception e) {
	        FacesUtils.addErrorMessage(e.getMessage());
	    }
		return solicitudAsignadaArea;
    }
    
    public List<AnexosSolicitante> action_create_anexosSolicitante() {
    	try{
    		int index = 0;
    		anexosSolicitantes = new ArrayList<AnexosSolicitante>();
    		for (UploadedFile uploadedF : uploadedFiles) {
    			file = uploadedF;
    		    if (file != null) {
    		    	byte[] imageInByte = file.getContents();
    		    	FileImageOutputStream imageOutput;
    				imageOutput = new FileImageOutputStream(new File(file.getFileName()));
    				imageOutput.write(imageInByte, 0, imageInByte.length);
    				imageOutput.close();
    		
    				blob = new SerialBlob(imageInByte);

    				AnexosSolicitante anexosSolicitante = new AnexosSolicitante();					
    				anexosSolicitante.setDocumentoReal(blob);
    				anexosSolicitante.setNombreAnexo(file.getFileName());
    				anexosSolicitante.setNombreBusqueda(file.getFileName());
    				anexosSolicitante.setEstadoRegistro("A");
    				anexosSolicitante.setAdjuntoDocumento("S");
    				AnexosPqr anexo = businessDelegatorView.getAnexosPqr(anexosPqrs.get(index).getIdAnexoPqr());
    				anexosSolicitante.setAnexosPqr(anexo);
    				anexosSolicitante.setFechaCreacion(new Date());
    				anexosSolicitante.setUsuarioCreador("Solicitante");
    				anexosSolicitante.setFechaUltimaModificacion(null);
    				anexosSolicitante.setUsuarioUltimaModificacion(null);
    				
    				anexosSolicitantes.add(anexosSolicitante);
    				index = index + 1;
    		    }
    	    }
	    } catch (Exception e) {
	        FacesUtils.addErrorMessage(e.getMessage());
	    }
		return anexosSolicitantes;
    }
    
	private ParametrosPqr obtenerParametro(String parametro) throws Exception {
		ParametrosPqr parametrosPqr = null;
		Object[] variables = { "descripcionParam", true, parametro, "=" };
		List<ParametrosPqr> parametrosPqrs = businessDelegatorView
				.findByCriteriaInParametrosPqr(variables, null, null);
		if (Utilities.validationsList(parametrosPqrs)) {
			parametrosPqr = parametrosPqrs.get(0);
		}
		return parametrosPqr;
	}
	
	private TipoEstadoPqr obtenerEstado(String estado) throws Exception {
		TipoEstadoPqr tipoEstadoPqr = null;
		Object[] variables = { "descripcionEstado", true, estado, "=" };
		List<TipoEstadoPqr> estadosPqrs = businessDelegatorView
				.findByCriteriaInTipoEstadoPqr(variables, null, null);
		if (Utilities.validationsList(estadosPqrs)) {
			tipoEstadoPqr = estadosPqrs.get(0);
		}
		return tipoEstadoPqr;
	}
	
	private MotivoSolicitud obtenerMotSol(String motSol) throws Exception {
		MotivoSolicitud motivoSolicitud = null;
		Object[] variables = { "descripcionMotSol", true, motSol, "=" };
		List<MotivoSolicitud> motivosSolicitud = businessDelegatorView
				.findByCriteriaInMotivoSolicitud(variables, null, null);
		if (Utilities.validationsList(motivosSolicitud)) {
			motivoSolicitud = motivosSolicitud.get(0);
		}
		return motivoSolicitud;
	}
	
	private MotivoReclamacion obtenerMotRecl(String motRecl) throws Exception {
		MotivoReclamacion motivoReclamacion = null;
		Object[] variables = { "descripcionMotRecl", true, motRecl, "=" };
		List<MotivoReclamacion> motivosReclamacion = businessDelegatorView
				.findByCriteriaInMotivoReclamacion(variables, null, null);
		if (Utilities.validationsList(motivosReclamacion)) {
			motivoReclamacion = motivosReclamacion.get(0);
		}
		return motivoReclamacion;
	}
	
	private AreasInvolucradas obtenerArea(String area) throws Exception {
		AreasInvolucradas areaInvolucrada = null;
		Object[] variables = { "nombreArea", true, area, "=" };
		List<AreasInvolucradas> areasInvolucradas = businessDelegatorView
				.findByCriteriaInAreasInvolucradas(variables, null, null);
		if (Utilities.validationsList(areasInvolucradas)) {
			areaInvolucrada = areasInvolucradas.get(0);
		}
		return areaInvolucrada;
	}
	
	public List<String> consultarMotivoSolicitud(TipoSolicitudPqr tipoSol) {
		try{
			motivoSolicitud = new ArrayList<String>();
			List<MotivoSolicitud> motSol = businessDelegatorView.consultarMotSolXTipoPqr(tipoSol);
			if(motSol != null){
				for (MotivoSolicitud motSolicitud : motSol) {
					motivoSolicitud.add(motSolicitud.getDescripcionMotSol());
				}
			}
		}catch(Exception e){
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return motivoSolicitud;
	}
	
	public List<String> consultarMotivoReclamacion(TipoSolicitudPqr tipoSol) {
		try{
			motivoReclamacion = new ArrayList<String>();
			List<MotivoReclamacion> motRecl = businessDelegatorView.consultarMotReclXTipoPqr(tipoSol);
			if(motRecl != null){
				for (MotivoReclamacion motReclamacion : motRecl) {
					motivoReclamacion.add(motReclamacion.getDescripcionMotRecl());
				}
			}
		}catch(Exception e){
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return motivoReclamacion;
	}
	
	public List<AnexosPqr> consultarAnexosPqrs(TipoSolicitudPqr tipoSolicitud) {
		try {
	       	anexosPqrs = new ArrayList<AnexosPqr>();
			anexosPqrs = businessDelegatorView.consultarAnxsXTipoPqr(tipoSolicitud);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return anexosPqrs;
	}
    
	public boolean revizarCamposInfoSol(Long tipoDoc, String nombreContacto, String nombreEmpresa,
			String numeroIdentificacion, String numeroCelular,
			String telefonoFijo, String email) throws Exception{
		
		if (tipoDoc == null) {
			throw new Exception("Debe de seleccionar un tipo de documento");
		}
		
		if (numeroIdentificacion == null || numeroIdentificacion.equals("") || numeroIdentificacion.trim().equals("")) {
			throw new Exception("Debe de ingresar un Numero de Identificación");
		}
		if(maskNit.equals("")){
			if(numeroIdentificacion != null){
				if (!Utilities.soloNumeros(numeroIdentificacion)) {
					throw new Exception("El Número de Identificaciún debe ser totalmente numerico");
				}
			}
		}
		if (nombreEmpresa == null || nombreEmpresa.equals("") || nombreEmpresa.trim().equals("")) {
			throw new Exception("Debe de ingresar un Nombre de empresa");
		}
		if (nombreContacto == null || nombreContacto.equals("") || nombreContacto.trim().equals("")) {
			throw new Exception("Debe de ingresar un Nombre de contacto");
		}
		if (email == null || email.equals("") || email.trim().equals("")) {
			throw new Exception("El Correo Electronico es de caracter obligatorio");
		}
		if (!Utilities.correElectronico(email)) {
			throw new Exception("El Correo Electronico debe tener el siguiente formato \"xxx@xxx.xxx\"");
		}		
		if (numeroCelular == null || numeroCelular.equals("") || numeroCelular.trim().equals("")) {
			throw new Exception("Debe de ingresar un Numero celular");
		}
		if (telefonoFijo == null || telefonoFijo.equals("") || telefonoFijo.trim().equals("")) {
			throw new Exception("Debe de ingresar un Numero fijo");
		}
		return true;		
	}
	
	public boolean revizarCamposSolicitud(String nombreAgenciaAduana, String motSolicitud, String motReclamacion,
			String descripcionCaso, String solicitudARealizar) throws Exception{
		
		if (nombreAgenciaAduana == null || nombreAgenciaAduana.equals("") || nombreAgenciaAduana.trim().equals("")) {
			throw new Exception("Debe de ingresar el nombre de agencia de aduana");
		}
				
		if (sol && (motSolicitud == null || motSolicitud.equals(""))) {
			throw new Exception("Debe de seleccionar un motivo de Solicitud");
		}
		
		if (recl && (motReclamacion == null || motReclamacion.equals(""))) {
			throw new Exception("Debe de seleccionar un motivo de reclamación");
		}
		
		if (descripcionCaso == null || descripcionCaso.equals("") || descripcionCaso.trim().equals("")) {
			throw new Exception("Debe de ingresar la descripción del caso");
		}
		
		if (solRealizar && (solicitudARealizar == null || solicitudARealizar.equals("") || solicitudARealizar.trim().equals(""))) {
			throw new Exception("Debe de ingresar la solicitud a realizar");
		}		
		return true;		
	}
	
	// Get & Set
	public Long getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(Long idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public List<SelectItem> getTipoDocumento() {
		try {
			if(tipoDocumento == null){
		       	tipoDocumento = new ArrayList<SelectItem>();
				List<TipoDocumento> tiposDocumentos = businessDelegatorView.getTipoDocumento();
				if(tiposDocumentos != null){
			       	for (TipoDocumento tDoc : tiposDocumentos) {
						tipoDocumento.add(new SelectItem(tDoc.getIdTpDoc(), tDoc.getDescripcionTpDoc()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tipoDocumento;
	}

	public void setTipoDocumento(List<SelectItem> tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getMaskNit() {
		return maskNit;
	}

	public void setMaskNit(String maskNit) {
		this.maskNit = maskNit;
	}

	public InputText getTxtNombreEmpresa() {
		return txtNombreEmpresa;
	}

	public void setTxtNombreEmpresa(InputText txtNombreEmpresa) {
		this.txtNombreEmpresa = txtNombreEmpresa;
	}

	public InputText getTxtNombreContacto() {
		return txtNombreContacto;
	}

	public void setTxtNombreContacto(InputText txtNombreContacto) {
		this.txtNombreContacto = txtNombreContacto;
	}

	public InputText getTxtCorreoElectronico() {
		return txtCorreoElectronico;
	}

	public void setTxtCorreoElectronico(InputText txtCorreoElectronico) {
		this.txtCorreoElectronico = txtCorreoElectronico;
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

	public CommandButton getLimpiarInfosol() {
		return limpiarInfosol;
	}

	public void setLimpiarInfosol(CommandButton limpiarInfosol) {
		this.limpiarInfosol = limpiarInfosol;
	}

	public CommandButton getSiguienteInfoSol() {
		return siguienteInfoSol;
	}

	public void setSiguienteInfoSol(CommandButton siguienteInfoSol) {
		this.siguienteInfoSol = siguienteInfoSol;
	}

	public Long getIdTipoSolicitud() {
		return idTipoSolicitud;
	}

	public void setIdTipoSolicitud(Long idTipoSolicitud) {
		this.idTipoSolicitud = idTipoSolicitud;
	}

	public String getDescTpSol() {
		return descTpSol;
	}

	public void setDescTpSol(String descTpSol) {
		this.descTpSol = descTpSol;
	}

	public CommandButton getAtrasTipoSol() {
		return atrasTipoSol;
	}

	public void setAtrasTipoSol(CommandButton atrasTipoSol) {
		this.atrasTipoSol = atrasTipoSol;
	}

	public CommandButton getSiguienteTipoSol() {
		return siguienteTipoSol;
	}

	public void setSiguienteTipoSol(CommandButton siguienteTipoSol) {
		this.siguienteTipoSol = siguienteTipoSol;
	}

	public String getDescripcionParametro() {
		return descripcionParametro;
	}

	public void setDescripcionParametro(String descripcionParametro) {
		this.descripcionParametro = descripcionParametro;
	}

	public void setAnexosPqrs(List<AnexosPqr> anexosPqrs) {
		this.anexosPqrs = anexosPqrs;
	}

	public CommandButton getLimpiarInstructivo() {
		return atrasInstructivo;
	}

	public void setAtrasInstructivo(CommandButton atrasInstructivo) {
		this.atrasInstructivo = atrasInstructivo;
	}

	public CommandButton getSiguienteInstructivo() {
		return siguienteInstructivo;
	}

	public void setSiguienteInstructivo(CommandButton siguienteInstructivo) {
		this.siguienteInstructivo = siguienteInstructivo;
	}

	public List<TipoSolicitudPqr> getTipoSolicitud() {
		try {
			if(tipoSolicitud == null){
		       	tipoSolicitud = new ArrayList<TipoSolicitudPqr>();
				tipoSolicitud = businessDelegatorView.getTipoSolicitudPqr();
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
		return tipoSolicitud;
	}

	public void setTipoSolicitud(List<TipoSolicitudPqr> tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public InputText getTxtNombreCliente() {
		return txtNombreCliente;
	}

	public void setTxtNombreCliente(InputText txtNombreCliente) {
		this.txtNombreCliente = txtNombreCliente;
	}

	public InputText getTxtNombreAgenciaAduana() {
		return txtNombreAgenciaAduana;
	}

	public void setTxtNombreAgenciaAduana(InputText txtNombreAgenciaAduana) {
		this.txtNombreAgenciaAduana = txtNombreAgenciaAduana;
	}

	public String getDescripcionCaso() {
		return descripcionCaso;
	}

	public void setDescripcionCaso(String descripcionCaso) {
		this.descripcionCaso = descripcionCaso;
	}

	public String getSolicitudARealizar() {
		return solicitudARealizar;
	}

	public void setSolicitudARealizar(String solicitudARealizar) {
		this.solicitudARealizar = solicitudARealizar;
	}

	public String getMotSolicitud() {
		return motSolicitud;
	}

	public void setMotSolicitud(String motSolicitud) {
		this.motSolicitud = motSolicitud;
	}

	public List<String> getMotivoSolicitud() {
		return motivoSolicitud;
	}

	public void setMotivoSolicitud(List<String> motivoSolicitud) {
		this.motivoSolicitud = motivoSolicitud;
	}

	public String getMotReclamacion() {
		return motReclamacion;
	}

	public void setMotReclamacion(String motReclamacion) {
		this.motReclamacion = motReclamacion;
	}

	public List<String> getMotivoReclamacion() {
		return motivoReclamacion;
	}

	public void setMotivoReclamacion(List<String> motivoReclamacion) {
		this.motivoReclamacion = motivoReclamacion;
	}

	public CommandButton getAtrasSolicitud() {
		return atrasSolicitud;
	}

	public void setAtrasSolicitud(CommandButton atrasSolicitud) {
		this.atrasSolicitud = atrasSolicitud;
	}

	public CommandButton getGuardarSolicitud() {
		return guardarSolicitud;
	}

	public void setGuardarSolicitud(CommandButton guardarSolicitud) {
		this.guardarSolicitud = guardarSolicitud;
	}

	public CommandButton getAtrasInstructivo() {
		return atrasInstructivo;
	}

	public TipoSolicitudPqr getTipoSolicitudPqr() {
		return tipoSolicitudPqr;
	}

	public void setTipoSolicitudPqr(TipoSolicitudPqr tipoSolicitudPqr) {
		this.tipoSolicitudPqr = tipoSolicitudPqr;
	}

	public CommandButton getLimpiarSolicitud() {
		return limpiarSolicitud;
	}

	public void setLimpiarSolicitud(CommandButton limpiarSolicitud) {
		this.limpiarSolicitud = limpiarSolicitud;
	}

	public Long getIdTipoEstado() {
		return idTipoEstado;
	}

	public void setIdTipoEstado(Long idTipoEstado) {
		this.idTipoEstado = idTipoEstado;
	}

	public List<AnexosPqr> getAnexosPqrs() {
		return anexosPqrs;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(
			IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public List<UploadedFile> getUploadedFiles() {
		return uploadedFiles;
	}
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void setUploadedFiles(List<UploadedFile> uploadedFiles) {
		this.uploadedFiles = uploadedFiles;
	}

	public Blob getBlob() {
		return blob;
	}

	public void setBlob(Blob blob) {
		this.blob = blob;
	}

	public boolean isAnexos() {
		return anexos;
	}

	public void setAnexos(boolean anexos) {
		this.anexos = anexos;
	}

	public boolean isSolRealizar() {
		return solRealizar;
	}

	public void setSolRealizar(boolean solRealizar) {
		this.solRealizar = solRealizar;
	}

	public boolean isSol() {
		return sol;
	}

	public void setSol(boolean sol) {
		this.sol = sol;
	}
		
	public boolean isRecl() {
		return recl;
	}

	public void setRecl(boolean recl) {
		this.recl = recl;
	}

	public List<AnexosSolicitante> getAnexosSolicitantes() {
		return anexosSolicitantes;
	}

	public void setAnexosSolicitantes(List<AnexosSolicitante> anexosSolicitantes) {
		this.anexosSolicitantes = anexosSolicitantes;
	}
}