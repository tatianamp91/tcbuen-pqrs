package com.tcbuen.pqrs.presentation.backingBeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import oracle.jdbc.proxy.annotation.SetDelegate;

import org.primefaces.component.inputtext.InputText;

import com.tcbuen.pqrs.modelo.MotivoReclamacion;
import com.tcbuen.pqrs.modelo.SolicitudPqr;
import com.tcbuen.pqrs.modelo.TipoEstadoPqr;
import com.tcbuen.pqrs.modelo.dto.EstadisticasDTO;
import com.tcbuen.pqrs.presentation.businessDelegate.IBusinessDelegatorView;
import com.tcbuen.pqrs.utilities.FacesUtils;
import com.tcbuen.pqrs.utilities.Utilities;


@ManagedBean
@ViewScoped
public class EstadisticasView implements Serializable {
	private static final long serialVersionUID = 1L;	

	private List<EstadisticasDTO> data;
	private List<SelectItem> estadosPqr;
	private TipoEstadoPqr selectEstadoPqr;
	private String descripcionMotivoReclamacion;
	private List<SelectItem> motivosReclamacion;
	private String numeroRadicacion;
	private Date fechaInicio;
	private Date fechaFin;	
	private Long idMotivoReclamacion;
	private Long idEstado;
	private InputText txtNumeroRadicacion;
	private List<SolicitudPqr> solicitudPqr;
		

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public EstadisticasView() {
		super();
	}
	
    public String action_clear() throws Exception{
    	try{
    		data = null;
    		descripcionMotivoReclamacion = null;
    		numeroRadicacion = null;
    		fechaInicio = null;
    		fechaFin = null;
    		idMotivoReclamacion = null;
    		idEstado = null;
    		txtNumeroRadicacion.setValue("");
	  
    	}catch(Exception e){
    		throw new Exception(e);
    	}

        return "";
    }
    
    private TipoEstadoPqr obtenerTipoEstadoPorId(Long idEstadoSolicitud) throws Exception{
    	TipoEstadoPqr entity = null;
    	Object[] variables = { "idTpEstPqr", true, idEstadoSolicitud, "=" };
    	List<TipoEstadoPqr> tipoEstados = businessDelegatorView.findByCriteriaInTipoEstadoPqr(variables, null, null);
    	
    	if (Utilities.validationsList(tipoEstados)) {
			entity = tipoEstados.get(0);
		}
    	return entity;
    }
    
    public String action_consultar(){
		try {
			Long idReclamacion = getIdMotivoReclamacion();
			Long idEstado = getIdEstado();
			Date fechaRadicacionInicio = getFechaInicio();
			Date fechaRadicacionFin = getFechaFin();
			String numeroRadicacion = txtNumeroRadicacion.getValue().toString();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			if (fechaRadicacionInicio != null && fechaRadicacionFin == null	|| fechaRadicacionInicio == null && fechaRadicacionFin != null) {
				throw new Exception("Para realizar una busqueda por fecha, es necesario ingresar "
						+ "Fecha Desde y Fecha Hasta");
			}
			//si solo intenta buscar una solicitud por numero de radicacion
			if (!numeroRadicacion.equals("")) {
				data = null;
				data = businessDelegatorView.consultarSolicitudNumeroRadicacion(numeroRadicacion);
			}else{
				//Si solo intenta buscar una solicitud por motivo de reclamacion
				if (idReclamacion != null && idEstado == null && fechaRadicacionInicio == null && fechaRadicacionFin == null) {
					data = businessDelegatorView.consultarSolicitudMotivoReclamacion(idReclamacion);
				}
				
				//si solo intenta buscar una solicitud por estado
				if (idEstado != null && idReclamacion == null && fechaRadicacionInicio == null && fechaRadicacionFin == null) {
					TipoEstadoPqr tipoEstado = obtenerTipoEstadoPorId(idEstado);
					data = businessDelegatorView.consultarSolicitudPorEstado(tipoEstado.getDescripcionEstado());
				}
				
				//si solo intenta buscar una solicitud por fecha inicio y fecha fin
				if (fechaRadicacionInicio != null && fechaRadicacionFin != null && idReclamacion == null && idEstado == null) {
					String fechaInicioFormateada = sdf.format(fechaRadicacionInicio);
					String fechaFinFormateada = sdf.format(fechaRadicacionFin);
					data = businessDelegatorView.consultarSolicitudPorFecha(fechaInicioFormateada, fechaFinFormateada);
				}
				
				//si solo intenta buscar una solicitud por motivo de reclamacion y estado
				if(idReclamacion != null && idEstado != null && fechaRadicacionInicio == null && fechaRadicacionFin == null){
					TipoEstadoPqr tipoEstado = obtenerTipoEstadoPorId(idEstado);
					data = businessDelegatorView.consultarSolicitudMotivoReclamacionEstado(idReclamacion, tipoEstado.getDescripcionEstado());
				}
				
				//Si solo intenta buscar una solicitud por estado, fecha inicio y fecha fin
				if (idEstado != null && fechaRadicacionInicio != null && fechaRadicacionFin != null && idReclamacion == null) {
					TipoEstadoPqr tipoEstado = obtenerTipoEstadoPorId(idEstado);
					String fechaInicioFormateada = sdf.format(fechaRadicacionInicio);
					String fechaFinFormateada = sdf.format(fechaRadicacionFin);
					data = businessDelegatorView.consultarSolicitudPorEstadoYFechas(tipoEstado.getDescripcionEstado(), fechaInicioFormateada, fechaFinFormateada);
				}
				
				//Si solo intenta buscar una solicitud por motivo de reclamacion, fecha inicio y fecha fin
				if (idReclamacion != null && fechaRadicacionInicio != null && fechaRadicacionFin != null && idEstado == null) {
					String fechaInicioFormateada = sdf.format(fechaRadicacionInicio);
					String fechaFinFormateada = sdf.format(fechaRadicacionFin);
					data = businessDelegatorView.consultarSolicitudPorReclamacionYFechas(idReclamacion, fechaInicioFormateada, fechaFinFormateada);
				}
				
				//Si solo intenta buscar una solicitud por motivo de reclamacion, estado y fechas
				if (idReclamacion != null && idEstado != null && fechaRadicacionInicio != null && fechaRadicacionFin != null) {
					TipoEstadoPqr tipoEstado = obtenerTipoEstadoPorId(idEstado);
					String fechaInicioFormateada = sdf.format(fechaRadicacionInicio);
					String fechaFinFormateada = sdf.format(fechaRadicacionFin);
					data = businessDelegatorView.consultarSolicitudPorReclamacionEstadoYFechas(idReclamacion, tipoEstado.getDescripcionEstado(), fechaInicioFormateada, fechaFinFormateada);
				}
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
    	return "";
    }

    
    public List<EstadisticasDTO> getData() {
		return data;
	}

	public void setData(List<EstadisticasDTO> data) {
		this.data = data;
	}

	public List<SelectItem> getEstadosPqr() {
		try{
			estadosPqr = new ArrayList<SelectItem>();
			List<TipoEstadoPqr> tipoEstados = businessDelegatorView.getTipoEstadoPqr();
			for (TipoEstadoPqr tipoEstado : tipoEstados) {
					estadosPqr.add(new SelectItem(tipoEstado.getIdTpEstPqr(),tipoEstado.getDescripcionEstado()));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return estadosPqr;
	}

	public void setEstadosPqr(List<SelectItem> estadosPqr) {
		this.estadosPqr = estadosPqr;
	}

	public TipoEstadoPqr getSelectEstadoPqr() {
		return selectEstadoPqr;
	}

	public void setSelectEstadoPqr(TipoEstadoPqr selectEstadoPqr) {
		this.selectEstadoPqr = selectEstadoPqr;
	}

	public List<SelectItem> getMotivosReclamacion() {
		try{
			motivosReclamacion = new ArrayList<SelectItem>();
			List<MotivoReclamacion> motivoReclamacion = businessDelegatorView.getMotivoReclamacion();
			for (MotivoReclamacion reclamacion : motivoReclamacion) {
				motivosReclamacion.add(new SelectItem(reclamacion.getIdMotRecl(),reclamacion.getDescripcionMotRecl()));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return motivosReclamacion;
	}

	public void setMotivosReclamacion(List<SelectItem> motivosReclamacion) {
		this.motivosReclamacion = motivosReclamacion;
	}

	public String getNumeroRadicacion() {
		return numeroRadicacion;
	}

	public void setNumeroRadicacion(String numeroRadicacion) {
		this.numeroRadicacion = numeroRadicacion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}

	public void setBusinessDelegatorView(
			IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}

	public Long getIdMotivoReclamacion() {
		return idMotivoReclamacion;
	}

	public void setIdMotivoReclamacion(Long idMotivoReclamacion) {
		this.idMotivoReclamacion = idMotivoReclamacion;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public InputText getTxtNumeroRadicacion() {
		return txtNumeroRadicacion;
	}

	public void setTxtNumeroRadicacion(InputText txtNumeroRadicacion) {
		this.txtNumeroRadicacion = txtNumeroRadicacion;
	}

	public List<SolicitudPqr> getSolicitudPqr() {
		return solicitudPqr;
	}

	public void setSolicitudPqr(List<SolicitudPqr> solicitudPqr) {
		this.solicitudPqr = solicitudPqr;
	}

	public String getDescripcionMotivoReclamacion() {
		return descripcionMotivoReclamacion;
	}

	public void setDescripcionMotivoReclamacion(String descripcionMotivoReclamacion) {
		this.descripcionMotivoReclamacion = descripcionMotivoReclamacion;
	}
	
	
}