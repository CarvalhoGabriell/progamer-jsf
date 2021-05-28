package br.com.fiap.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.SetupDAO;
import br.com.fiap.model.Setup;
import br.com.fiap.model.Usuario;

@Named
@RequestScoped
public class SetupBean {
	
	private Setup setup = new Setup();
	private Usuario usuario = new Usuario();
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Setup getSetup() {
		return setup;
	}

	public void setSetup(Setup setup) {
		this.setup = setup;
	}


	public void save() {
		new SetupDAO().save(setup);
		System.out.println("Cadastrando e Salvando..."+ this.setup);
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("Setup Cadastrado com Sucesso!"));
	}
	
	public List<Setup> getSetups() {
		return new SetupDAO().getAll();
	}
	
	public List<Setup> getallSetups() {
		return new SetupDAO().getOne(usuario);
	}
	
}
