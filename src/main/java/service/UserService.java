
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

public class UserService {

	private static String username;
	private static EntityManager em;
	private static EntityTransaction transaction;

	public UserService(String user) {
		UserService.username = user;
	}
	
	public static void logout() {
		UserService.username = null;
	}

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
	private static void closeConnection() {
		UserService.em.flush();
		UserService.transaction.commit();
	}
}
