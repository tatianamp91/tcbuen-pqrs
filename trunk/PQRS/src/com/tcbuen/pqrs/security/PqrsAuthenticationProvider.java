package com.tcbuen.pqrs.security;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.tcbuen.pqrs.modelo.UsuariosInternos;
import com.tcbuen.pqrs.presentation.businessDelegate.IBusinessDelegatorView;

@Scope("singleton")
@Component("PqrsAuthenticationProvider")
public class PqrsAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	IBusinessDelegatorView businessDelegatorView;
     
    /**
     * Implementacion de la seguridad propia
     */
    @Override
    public Authentication authenticate(Authentication authentication)throws AuthenticationException {
    	try{
         
	        String name = authentication.getName();
	        String password = authentication.getCredentials().toString();
	        
	        UsuariosInternos usuario = businessDelegatorView.consultarLoginContrasena(name, password);
	        if(usuario != null){
		        HttpSession httpSession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		        httpSession.setAttribute("usuario", usuario.getIdUsuInterno());
		    
	        	final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
	            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
	            final UserDetails principal = new User(name, password, grantedAuths);
	            final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
	            return auth;
	        }           
        }catch(Exception e){
        	try {
				throw new Exception(e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        }
    	return null;
    }
 
    /**
     * Este metodo se llama primero cuando se autentica un usuario
     */
    @Override
    public boolean supports(Class<?> authentication) {
         return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
 
}