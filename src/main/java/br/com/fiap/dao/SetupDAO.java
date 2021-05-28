package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Setup;
import br.com.fiap.model.Usuario;
import br.com.fiap.utils.EntityManagerFacede;

public class SetupDAO {

	public void save(Setup setup) {
		EntityManager manager= EntityManagerFacede.getEntityManager();
		manager.getTransaction().begin();
		manager.persist(setup);
		manager.getTransaction().commit();
		manager.close();
	}
	
	// pegar todos os setups cadastrados no banco
	public List<Setup> getAll() {
		EntityManager manager= EntityManagerFacede.getEntityManager();

		String strSQL = "SELECT s FROM Setup s";
		TypedQuery<Setup> query = manager.createQuery(strSQL, Setup.class);
		return query.getResultList();
	}
	
	public List<Setup> getOne(Usuario user) {
		EntityManager manager= EntityManagerFacede.getEntityManager();

		String strSQL = "select s from Setup s where ID_LOGIN_USER =: id";
		System.out.println(strSQL);
		TypedQuery<Setup> query = manager.createQuery(strSQL, Setup.class);
		
		query.setParameter("id", user.getId());
		return query.getResultList();
	}

	public Setup findById(Long id) {
		EntityManager manager= EntityManagerFacede.getEntityManager();
		
		return manager.find(Setup.class, id);
	}

	public void updateSetup(Setup setup) {
		EntityManager manager= EntityManagerFacede.getEntityManager();
		
		manager.getTransaction().begin();
		manager.merge(setup);
		manager.flush();
		manager.getTransaction().commit();
		manager.close();
		
	}
	
	
	public void deleteSetup(Setup setup) {
        EntityManager manager= EntityManagerFacede.getEntityManager();
        
        manager.getTransaction().begin();
        setup = manager.merge(setup);
        manager.remove(setup);
        manager.flush();
        System.out.println("Deletando Setup ID "+setup.getId()+" --- NOME: "+setup.getName());
        
        manager.getTransaction().commit();
        manager.close();
    }
	
}
