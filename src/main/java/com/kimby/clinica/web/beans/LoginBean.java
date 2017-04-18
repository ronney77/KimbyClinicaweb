package com.kimby.clinica.web.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.kimby.clinica.web.entities.Usuario;

@ManagedBean(name="Login")
@ViewScoped
public class LoginBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	
	
	
	
	public void logar()
	{
		
	}
	
	
	public Usuario getUsuario() 
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario) 
	{
		this.usuario = usuario;
	}
}
