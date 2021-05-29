package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Usuario;
import br.com.fiap.utils.EntityManagerFacede;

public class UsuarioDAO {
	
	public void saveUser(Usuario user) {
		EntityManager manager= EntityManagerFacede.getEntityManager();
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();
		manager.close();
	}
	
	public List<Usuario> getAllUsers() {
		EntityManager manager= EntityManagerFacede.getEntityManager();
		String strSQL = "SELECT s FROM Usuario s";
		TypedQuery<Usuario> query = manager.createQuery(strSQL, Usuario.class);
		return query.getResultList();
	}

	public Usuario findById(Long id) {
		EntityManager manager= EntityManagerFacede.getEntityManager();
		return manager.find(Usuario.class, id);
	}
	
	public void updateUser(Usuario user) {
		EntityManager manager= EntityManagerFacede.getEntityManager();
		manager.getTransaction().begin();
		manager.merge(user);
		manager.flush();
		manager.getTransaction().commit();
		manager.close();
	}
	
	public void delete(Long id) {
		EntityManager manager= EntityManagerFacede.getEntityManager();
		Usuario user = manager.find(Usuario.class, id);
		manager.getTransaction().begin();
		user = manager.merge(user);
		manager.remove(user);
		manager.flush();
		System.out.println("Deletando Usuario ID "+user.getId()+" --- NOME: "+user.getNome()+" EMAIL: "+user.getEmail());
		
		manager.getTransaction().commit();
		manager.close();
	}

	public Usuario verifyExist(Usuario usuario) {
		
		EntityManager manager= EntityManagerFacede.getEntityManager();
		
		TypedQuery<Usuario> query = manager.createQuery("SELECT u FROM Usuario u WHERE u.email =:email AND u.senha =:senha", 
				Usuario.class);
		query.setParameter("email", usuario.getEmail());
		query.setParameter("senha", usuario.getSenha());
		try {
			usuario = query.getSingleResult();
		} catch (NoResultException e) {
			
			return null;
		}
		return usuario;
	}
}
