package br.com.fiap.bean;

import javax.faces.bean.ManagedBean;

import br.com.fiap.model.Usuario;

@ManagedBean
public class UsuarioBean {
	
	private Usuario user;

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
	public void saveUser() {
		
		System.out.println("Criando Usuario ....."+ this.user);
	}
}
