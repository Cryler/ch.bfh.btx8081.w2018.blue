
/**
 *date: 14.12.2018   -  time: 10:38:48
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * The Class EMService.
 * 
 * @author gundy1.
 */
public class EMService {

	/**
	 * Creates and returns a new {@code EntityTransaction}
	 *
	 * @return the transaction
	 */
	public static EntityTransaction getTransaction() {
		return EMSingleton.getTransaction();
	}

	/**
	 * Returns the {@code EntityManager} singleton.
	 *
	 * @return the em
	 */
	public static EntityManager getEM() {
		return EMSingleton.getEM();
	}

	/**
	 * The Class EMSingleton. This is a singleton Class.
	 */
	private static class EMSingleton {

		private static EMSingleton instance;
		/** The single {@code EntityManager} that is used in the whole application. */
		private static EntityManager em;

		/** The Constant PERSISTENCE_UNIT_NAME. */
		private static final String PERSISTENCE_UNIT_NAME = "ch.bfh.btx8081.w2018.blue";

		/**
		 * Gets the {@code EntityManager}.
		 *
		 * @return the em
		 */
		private static EntityManager getEM() {
			if (EMSingleton.instance == null) {
				EMSingleton.instance = new EMSingleton();
				EMSingleton.em = Persistence.createEntityManagerFactory(EMSingleton.PERSISTENCE_UNIT_NAME)
						.createEntityManager();
			}
			return EMSingleton.em;
		}

		/**
		 * Gets the transaction.
		 *
		 * @return the transaction
		 */
		private static EntityTransaction getTransaction() {
			if (EMSingleton.instance == null) {
				EMSingleton.instance = new EMSingleton();
				EMSingleton.em = Persistence.createEntityManagerFactory(EMSingleton.PERSISTENCE_UNIT_NAME)
						.createEntityManager();
			}
			return EMSingleton.em.getTransaction();
		}

	}
}
