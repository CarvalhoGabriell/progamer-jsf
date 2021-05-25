package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Usuario;
import br.com.fiap.utils.EntityManagerFacede;

public class UsuarioDAO {
	
	private EntityManager manager= EntityManagerFacede.getEntityManager();
	public void saveUser(Usuario user) {
		
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();
		manager.close();
	}
	
	public List<Usuario> getAllUsers() {
		
		String strSQL = "SELECT s FROM Usuario s";
		TypedQuery<Usuario> query = manager.createQuery(strSQL, Usuario.class);
		return query.getResultList();
	}

	public Usuario findById(Long id) {
		
		return manager.find(Usuario.class, id);
	}
	
	public void updateUser(Usuario user) {
		
		manager.getTransaction().begin();
		manager.merge(user);
		manager.flush();
		manager.getTransaction().commit();
		manager.close();
	}
	
	public void delete(Long id) {
		
		manager.getTransaction().begin();
		Usuario user = manager.find(Usuario.class, id);
		manager.remove(user);
		System.out.println("Deletando Usuario ID "+user.getId()+" --- NOME: "+user.getNome()+" EMAIL: "+user.getEmail());
		
		manager.getTransaction().commit();
		manager.close();
	}
}
