
/**
 *date: 27.12.2018   -  time: 14:09:28
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entity.UserEntity;

/**
 * The Class UserService.
 * 
 * @author gundy1.
 */
public class UserService {

	//TODO Diese klasse noch in ein schönes Singleton umbauen!
	
	
	private static String username;
	
	private static EntityManager em;

	private static EntityTransaction transaction;

	/**
	 * Instantiates a new user service and stores the username of the currently logged user in the {@value username}.
	 *
	 * @param user the user
	 */
	public UserService(String user) {
		UserService.username = user;
	}
	
	/**
	 * Clears the {@value username}.
	 */
	public static void logout() {
		UserService.username = null;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public static UserEntity getUser() {
		UserService.em = EMService.getEM();
		UserService.transaction = em.getTransaction();
		UserService.transaction.begin();
		try {
			Query q = UserService.em.createNativeQuery(
					"select * from usertable where usertable.username = '" + UserService.username + "'", UserEntity.class);
			UserEntity entity = (UserEntity) q.getSingleResult();
			return entity;
		}catch (NoResultException e) {
			return null;
		}finally {
			UserService.closeConnection();
		}
	}
	
	/**
	 * Closes the current connection to the db.
	 */
	private static void closeConnection() {
		UserService.em.flush();
		UserService.transaction.commit();
	}
}
