package com.kimby.clinica.web.entities;

import java.io.Serializable;

public class Usuario implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private String usuario;
	private String senha;

	
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsusario(String ususario) {
		this.usuario = ususario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
