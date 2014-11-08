package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.EstadisticasDTO;
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
public class EstadisticasView implements Serializable {
	private static final long serialVersionUID = 1L;	

	private List<EstadisticasDTO> data;
	private List<SelectItem> estadosPqr;
	private TipoEstadoPqr selectEstadoPqr;
	private MotivoReclamacion selectMotRecl;
	private List<SelectItem> motivosReclamacion;
	private String numeroRadicacion;
	private Date fechaInicio;
	private Date fechaFin;	
	  
	

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public EstadisticasView() {
		super();
	}
	
    public String action_clear() throws Exception{
    	try{
	  
    	}catch(Exception e){
    		throw new Exception(e);
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
			if(estadosPqr != null){
				estadosPqr = new ArrayList<SelectItem>();
				List<TipoEstadoPqr> tipoEstadoPqr = businessDelegatorView.getTipoEstadoPqr();
				if(tipoEstadoPqr != null){
					for (TipoEstadoPqr tipoEstado : tipoEstadoPqr) {
						SelectItem selectItem = new SelectItem(tipoEstado.getIdTpEstPqr(), tipoEstado.getDescripcionEstado());
						estadosPqr.add(selectItem);
					}
				}
			}
		}catch(Exception e){
			
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

	public MotivoReclamacion getSelectMotRecl() {
		return selectMotRecl;
	}

	public void setSelectMotRecl(MotivoReclamacion selectMotRecl) {
		this.selectMotRecl = selectMotRecl;
	}

	public List<SelectItem> getMotivosReclamacion() {
		try{
			if(motivosReclamacion != null){
				motivosReclamacion = new ArrayList<SelectItem>();
				List<MotivoReclamacion> motReclamacionPqr = businessDelegatorView.getMotivoReclamacion();
				if(motReclamacionPqr != null){
					for (MotivoReclamacion motivoReclamacion : motReclamacionPqr) {
						SelectItem selectItem = new SelectItem(motivoReclamacion.getIdMotRecl(), motivoReclamacion.getDescripcionMotRecl());
						motivosReclamacion.add(selectItem);
					}
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
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
	
}