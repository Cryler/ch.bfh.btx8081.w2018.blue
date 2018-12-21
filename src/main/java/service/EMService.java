
/**
 *date: 14.12.2018   -  time: 10:38:48
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EMService {

	public static EntityTransaction getTransaction() {
		return EMSingleton.getTransaction();
	}

	public static EntityManager getEM() {
		return EMSingleton.getEM();
	}

	private static class EMSingleton {

		private static EntityManager em;
		private static final String PERSISTENCE_UNIT_NAME = "ch.bfh.btx8081.w2018.blue";
		private static EMSingleton instance;

		private EMSingleton() {
		}

		private static EntityTransaction getTransaction() {
			if (EMSingleton.instance == null) {
				EMSingleton.instance = new EMSingleton();
				EMSingleton.em = Persistence.createEntityManagerFactory(EMSingleton.PERSISTENCE_UNIT_NAME)
						.createEntityManager();
				}
			return EMSingleton.em.getTransaction();
		}

		private static EntityManager getEM() {
			if (EMSingleton.instance == null) {
				EMSingleton.instance = new EMSingleton();
				EMSingleton.em = Persistence.createEntityManagerFactory(EMSingleton.PERSISTENCE_UNIT_NAME)
						.createEntityManager();
				System.out.println("Singleton created");
			}
			return EMSingleton.em;
		}
	}
}
