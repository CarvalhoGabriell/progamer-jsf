package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Setup;
import br.com.fiap.utils.EntityManagerFacede;

public class SetupDAO {
	private EntityManager manager= EntityManagerFacede.getEntityManager();
	
	public void save(Setup setup) {
		
		manager.getTransaction().begin();
		manager.persist(setup);
		manager.getTransaction().commit();
		manager.close();
	}
	
	// pegar todos os setups cadastrados no banco
	public List<Setup> getAll() {
		
		String strSQL = "SELECT s FROM Setup s";
		TypedQuery<Setup> query = manager.createQuery(strSQL, Setup.class);
		return query.getResultList();
	}

	public Setup findById(Long id) {
		
		return manager.find(Setup.class, id);
	}

	public void updateSetup(Setup setup) {
		manager.getTransaction().begin();
		manager.merge(setup);
		manager.flush();
		manager.getTransaction().commit();
		manager.close();
		
	}
	
	
	public void deleteySetup(Long id) {
		manager.getTransaction().begin();
		Setup setup = manager.find(Setup.class, id);
		manager.remove(setup);
		System.out.println("Deletando Setup ID "+setup.getId()+" --- NOME: "+setup.getName());
		
		manager.getTransaction().commit();
		manager.close();
	}
	
}
