/*
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * "The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations under
 * the License.
 *
 * The Original Code is ICEfaces 1.5 open source software code, released
 * November 5, 2006. The Initial Developer of the Original Code is ICEsoft
 * Technologies Canada, Corp. Portions created by ICEsoft are Copyright (C)
 * 2004-2006 ICEsoft Technologies Canada, Corp. All Rights Reserved.
 *
 * Contributor(s): _____________________.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"
 * License), in which case the provisions of the LGPL License are
 * applicable instead of those above. If you wish to allow use of your
 * version of this file only under the terms of the LGPL License and not to
 * allow others to use your version of this file under the MPL, indicate
 * your decision by deleting the provisions above and replace them with
 * the notice and other provisions required by the LGPL License. If you do
 * not delete the provisions above, a recipient may use your version of
 * this file under either the MPL or the LGPL License."
 *
 */
package com.tcbuen.pqrs.utilities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.sql.Blob;
import javax.sql.rowset.serial.SerialBlob;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tcbuen.pqrs.exceptions.ZMessManager;

/**
 * JSF utilities.
 */
public class FacesUtils {
	/**
	 * Get servlet context.
	 * 
	 * @return the servlet context
	 */
	public static ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
	}

	public static ExternalContext getExternalContext() {
		FacesContext fc = FacesContext.getCurrentInstance();
		return fc.getExternalContext();
	}

	public static HttpSession getHttpSession(boolean create) {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(create);
	}

	public static void refresh() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ViewHandler viewHandler = application.getViewHandler();
		UIViewRoot viewRoot = viewHandler.createView(context, context
				.getViewRoot().getViewId());
		context.setViewRoot(viewRoot);

	}

	/**
	 * Get managed bean based on the bean name.
	 * 
	 * @param beanName
	 *            the bean name
	 * @return the managed bean associated with the bean name
	 */
	public static Object getManagedBean(String beanName) {

		// return
		// getValueBinding(getJsfEl(beanName)).getValue(FacesContext.getCurrentInstance());
		// ReportBean reportBean =
		// (ReportBean)facesContext.getApplication().getVariableResolver().resolveVariable(facesContext,
		// "reportBean");
		return FacesContext.getCurrentInstance().getApplication()
				.getVariableResolver().resolveVariable(
						FacesContext.getCurrentInstance(), beanName);
	}

	public static Object getManagedBeanFromSession(String beanName) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get(beanName);
	}

	/**
	 * Remove the managed bean based on the bean name.
	 * 
	 * @param beanName
	 *            the bean name of the managed bean to be removed
	 */
	public static void resetManagedBean(String beanName) {
		getValueBinding(getJsfEl(beanName)).setValue(
				FacesContext.getCurrentInstance(), null);
	}

	/**
	 * Store the managed bean inside the session scope.
	 * 
	 * @param beanName
	 *            the name of the managed bean to be stored
	 * @param managedBean
	 *            the managed bean to be stored
	 */
	public static void setManagedBeanInSession(String beanName,
			Object managedBean) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(beanName, managedBean);
	}

	/**
	 * Get parameter value from request scope.
	 * 
	 * @param name
	 *            the name of the parameter
	 * @return the parameter value
	 */
	public static String getRequestParameter(String name) {
		return (String) FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(name);
	}

	/**
	 * Add information message.
	 * 
	 * @param msg
	 *            the information message
	 */
	public static void addInfoMessage(String msg) {
		addInfoMessage(null, msg);
	}

	/**
	 * Add information message to a specific client.
	 * 
	 * @param clientId
	 *            the client id
	 * @param msg
	 *            the information message
	 */
	public static void addInfoMessage(String clientId, String msg) {
		FacesContext.getCurrentInstance().addMessage(clientId,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	/**
	 * Add error message.
	 * 
	 * @param msg
	 *            the error message
	 */
	public static void addErrorMessage(String msg) {
		addErrorMessage(null, msg);
	}

	/**
	 * Add error message to a specific client.
	 * 
	 * @param clientId
	 *            the client id
	 * @param msg
	 *            the error message
	 */
	public static void addErrorMessage(String clientId, String msg) {
		FacesContext.getCurrentInstance().addMessage(clientId,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}

	private static Application getApplication() {
		ApplicationFactory appFactory = (ApplicationFactory) FactoryFinder
				.getFactory(FactoryFinder.APPLICATION_FACTORY);
		return appFactory.getApplication();
	}

	private static ValueBinding getValueBinding(String el) {
		return getApplication().createValueBinding(el);
	}

	private static HttpServletRequest getServletRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	private static Object getElValue(String el) {
		return getValueBinding(el).getValue(FacesContext.getCurrentInstance());
	}

	private static String getJsfEl(String value) {
		return "#{" + value + "}";
	}

	/**
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
	}

	/**
	 * 
	 * @param o
	 *            Put an object in session
	 */
	public static void putinSession(String name, Object o) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		session.setAttribute(name, o);
	}

	/**
	 * 
	 * @param name
	 * @return object from session
	 */

	public static Object getfromSession(String name) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getAttribute(name);
	}

	/**
	 * 
	 * @return
	 */

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	/**
	 * 
	 * @param name
	 * @param o
	 */
	public static void putinRequest(String name, Object o) {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute(name, o);
	}

	/**
	 * 
	 * @param name
	 * @return object from request
	 */

	public static Object getfromRequest(String name) {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		return request.getAttribute(name);
	}

	public static Long checkLong(Object input) throws ZMessManager {
		if (input == null) {
			return null;
		}
		UIInput inputValue = (UIInput) input;
		if (inputValue.getValue() == null) {
			return null;
		}
		if ((inputValue.getValue()).equals("")) {
			return null;
		}
		try {
			return new Long(inputValue.getValue().toString());
		} catch (Exception e) {
			throw new ZMessManager().new NotValidFieldException(inputValue
					.getId());
		}

	}

	public static String checkString(Object input) throws ZMessManager {
		if (input == null) {
			return null;
		}
		UIInput inputValue = (UIInput) input;
		if (inputValue.getValue() == null) {
			return null;
		}
		if ((inputValue.getValue()).equals("")) {
			return null;
		}
		try {
			return new String(inputValue.getValue().toString());
		} catch (Exception e) {
			throw new ZMessManager().new NotValidFieldException(inputValue
					.getId());
		}

	}

	public static Integer checkInteger(Object input) throws ZMessManager {
		if (input == null) {
			return null;
		}
		UIInput inputValue = (UIInput) input;
		if (inputValue.getValue() == null) {
			return null;
		}
		if ((inputValue.getValue()).equals("")) {
			return null;
		}
		try {
			return new Integer(inputValue.getValue().toString());
		} catch (Exception e) {
			throw new ZMessManager().new NotValidFieldException(inputValue
					.getId());
		}
	}
	
 public static Double checkDouble(Object input) throws ZMessManager {
        if (input == null) {
            return null;
        }

        UIInput inputValue = (UIInput) input;

        if (inputValue.getValue() == null) {
            return null;
        }

        if ((inputValue.getValue()).equals("")) {
            return null;
        }

        try {
            return new Double(inputValue.getValue().toString());
        } catch (Exception e) {
            throw new ZMessManager().new NotValidFieldException(inputValue.getId());
        }
    }
    
    public static Float checkFloat(Object input) throws ZMessManager {
        if (input == null) {
            return null;
        }

        UIInput inputValue = (UIInput) input;

        if (inputValue.getValue() == null) {
            return null;
        }

        if ((inputValue.getValue()).equals("")) {
            return null;
        }

        try {
            return new Float(inputValue.getValue().toString());
        } catch (Exception e) {
            throw new ZMessManager().new NotValidFieldException(inputValue.getId());
        }
    }	

	public static Date checkDate(Object input) throws ZMessManager {
		if (input == null) {
			return null;
		}
		UIInput inputValue = (UIInput) input;
		if (inputValue.getValue() == null) {
			return null;
		}
		if ((inputValue.getValue()).equals("")) {
			return null;
		}
		try {
			return (Date) inputValue.getValue();
		} catch (Exception e) {
			throw new ZMessManager().new NotValidFieldException(inputValue
					.getId());
		}

	}

	public static BigInteger checkBigInteger(Object input) throws ZMessManager {
		if (input == null) {
			return null;
		}
		UIInput inputValue = (UIInput) input;
		if (inputValue.getValue() == null) {
			return null;
		}
		if ((inputValue.getValue()).equals("")) {
			return null;
		}
		try {
			return new BigInteger(inputValue.getValue().toString());
		} catch (Exception e) {
			throw new ZMessManager().new NotValidFieldException(inputValue
					.getId());
		}

	}

	public static BigDecimal checkBigDecimal(Object input) throws ZMessManager {
		if (input == null) {
			return null;
		}
		UIInput inputValue = (UIInput) input;
		if (inputValue.getValue() == null) {
			return null;
		}
		if ((inputValue.getValue()).equals("")) {
			return null;
		}
		try {
			return new BigDecimal(inputValue.getValue().toString());
		} catch (Exception e) {
			throw new ZMessManager().new NotValidFieldException(inputValue
					.getId());
		}
	}
	
	public static Blob checkBlob(Object input){
		
		Blob blob = null;
		byte[] bytesFile = null;
		
		
		if (input == null) {
			return null;
		}
		UIInput inputValue = (UIInput) input;
		if (inputValue.getValue() == null) {
			return null;
		}
		if ((inputValue.getValue()).equals("")) {
			return null;
		}
		try {
			bytesFile=((String)inputValue.getValue()).getBytes();
			blob=new SerialBlob(bytesFile);
		} catch (Exception e) {
			throw new ZMessManager().new NotValidFieldException(inputValue.getId());
		}
		return blob;
		
	}
}
