package br.com.fiap.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.model.Usuario;

public class UsuarioDAO {
	
	
	public void saveUser(Usuario user) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("progamer-persistence");
		EntityManager manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();
		
		manager.close();
	}
}
