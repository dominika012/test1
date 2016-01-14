package com.ibm.itacademy.animaland.web;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.ibm.itacademy.animaland.auth.DbUtilsLoginService;
import com.ibm.itacademy.animaland.auth.LoginService;

@ManagedBean
@SessionScoped
public class LoginBean {
	private LoginService loginService = new DbUtilsLoginService();
	
	private String login;
	
	private String password;
	
	private boolean isAuthenticated;
	
	public String login() {
		if(loginService.login(login, password)) {
			isAuthenticated = true;
			password = null;
			return "animals?faces-redirect=true";
		}
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, 
						"Access denied", 
						"Incorrect login or password"));
		
		return null;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAuthenticated() {
		return isAuthenticated;
	}
	
	
	
	
	
	
	
	

}
