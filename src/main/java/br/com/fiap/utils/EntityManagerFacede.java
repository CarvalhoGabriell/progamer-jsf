package br.com.fiap.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class EntityManagerFacede {
	
	private static final String PROGAMER_PERSISTENCE = "progamer-persistence";

	public static EntityManager getEntityManager() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory(PROGAMER_PERSISTENCE);
		EntityManager manager = factory.createEntityManager();
		return manager;
	}
}
