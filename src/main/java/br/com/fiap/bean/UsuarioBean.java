package br.com.fiap.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.model.Usuario;

@Named
@RequestScoped
public class UsuarioBean {
	
	private Usuario user;

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
	public void saveUser() {
		new UsuarioDAO().saveUser(this.user);
		System.out.println("Criando Usuario ....."+ this.user);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Usuário Cadastrado com sucesso!"));
	}
	
	
	public List<Usuario> getUsers() {
		return new UsuarioDAO().getAllUsers();
	}
}
