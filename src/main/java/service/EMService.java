
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

	private static final String PERSISTENCE_UNIT_NAME = "ch.bfh.btx8081.w2018.blue";

	public static EntityManager getEM() {
		return EMService.createEM();
	}

	private static EntityManager createEM() {
		return Persistence.createEntityManagerFactory(EMService.PERSISTENCE_UNIT_NAME).createEntityManager();
	}

}
