package com.tcbuen.pqrs.presentation.backingBeans;

import com.tcbuen.pqrs.modelo.*;
import com.tcbuen.pqrs.modelo.dto.SolicitudDTO;
import com.tcbuen.pqrs.presentation.businessDelegate.*;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class CasosAsignadosView implements Serializable {
	private static final long serialVersionUID = 1L;	

	private List<SolicitudDTO> data;
	private SolicitudDTO selectedSolicitudPqr;

	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;

	public CasosAsignadosView() {
		super();
	}

	public List<SolicitudDTO> getData() throws Exception {
		try{
			if(data == null){
				AreasInvolucradas area = businessDelegatorView.getAreasInvolucradas(1L);
				if(area != null){
					data = businessDelegatorView.consultarAsignacion(area);
				}
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
}