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
	
	private Usuario user = new Usuario();
	FacesContext context = FacesContext.getCurrentInstance();

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
	public void saveUser() {
		new UsuarioDAO().saveUser(user);
		System.out.println("Criando Usuario ....."+ this.user);
		context.addMessage(null,
				new FacesMessage("Usuário Cadastrado com sucesso!"));
	}
	
	
	public List<Usuario> getUsers() {
		return new UsuarioDAO().getAllUsers();
	}
	
	public String  login() {
		
		user = new UsuarioDAO().verifyExist(user);
		if (user != null) {
			context.getExternalContext().getSessionMap().put("user", user);
			return "index?faces-redirect=true";
		}
		
		context
			.getExternalContext()
			.getFlash()
			.setKeepMessages(true);
		context
		.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Inválido!", ""));
		
		return "login?faces-redirect=true";
	}
	
	public String logout() {
		context.getExternalContext().getSessionMap().remove("user");

		return "login?faces-redirect=true";
	}
	
}
/*
 * tonico@eu.com
 * 12345
 */
