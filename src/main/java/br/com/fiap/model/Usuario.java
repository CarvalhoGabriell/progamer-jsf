package br.com.fiap.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String senha;
	
//	@OneToMany(mappedBy = "user")
//	private List<Setup> setups;
//	
//	public void addSetups(Setup setup) {
//		
//		if (setups == null) {
//			
//			setups = new ArrayList<Setup>();
//		}
//		setups.add(setup);
//		setup.setUser(this);
//	}
	
	
//	public List<Setup> getSetups() {
//		return setups;
//	}
//	
//	public void setSetups(List<Setup> setups) {
//		this.setups = setups;
//	}
	
	@Temporal(TemporalType.DATE)
	private Calendar dtNascimento = Calendar.getInstance();
	
	public Usuario() {
		super();
	}

	public Usuario(String nome, String email, String senha, Calendar dtNascimento) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.dtNascimento = dtNascimento;
	}

	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", dtNascimento="
				+ dtNascimento + "]";
	}

	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}



	public Calendar getDtNascimento() {
		return dtNascimento;
	}



	public void setDtNascimento(Calendar dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
