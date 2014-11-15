package com.tcbuen.pqrs.security;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;

import com.tcbuen.pqrs.modelo.UsuariosInternos;
import com.tcbuen.pqrs.presentation.businessDelegate.IBusinessDelegatorView;
import com.tcbuen.pqrs.utilities.FacesUtils;
 
 
@ManagedBean
@ViewScoped
public class Authentication implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{BusinessDelegatorView}")
	private IBusinessDelegatorView businessDelegatorView;
	private String rol;
	private InputText txtUsuario;
	private Password txtContraseña;
	private CommandButton btnIngresar;
	
	public Authentication() {
        super();
    }
     
    public String autenticacionAplicacion() throws Exception {
    	try{
    		String usuario = txtUsuario.getValue().toString();
    		String contrasena = txtContraseña.getValue().toString();
    		
    		UsuariosInternos usuarioBD = businessDelegatorView.consultarLoginContrasena(usuario, contrasena);	
	        if(usuarioBD != null){
				if(usuarioBD.getRoles().getNombreRol().trim().equals("admin") 
						&& usuarioBD.getAreasInvolucradas().getNombreArea().trim().equals("admin")){
					rol = "Admin";
				}else {
					rol = "Area";
				}
	        }else{
	        	throw new Exception("Error en autenticación");
	        }
	        
	        if(!rol.trim().equals("")){
	        	HttpSession httpSession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	        	if(usuarioBD != null){
	        		httpSession.setAttribute("usuario", usuarioBD.getIdUsuInterno());
	        	}
	        }
		}catch (Exception e) {
			FacesUtils.addErrorMessage((e.getMessage()));
		}
    	return rol;
    } 
    
    public IBusinessDelegatorView getBusinessDelegatorView() {
		return businessDelegatorView;
	}
	public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
		this.businessDelegatorView = businessDelegatorView;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public InputText getTxtUsuario() {
		return txtUsuario;
	}
	public void setTxtUsuario(InputText txtUsuario) {
		this.txtUsuario = txtUsuario;
	}
	public Password getTxtContraseña() {
		return txtContraseña;
	}
	public void setTxtContraseña(Password txtContraseña) {
		this.txtContraseña = txtContraseña;
	}
	public CommandButton getBtnIngresar() {
		return btnIngresar;
	}
	public void setBtnIngresar(CommandButton btnIngresar) {
		this.btnIngresar = btnIngresar;
	}
}