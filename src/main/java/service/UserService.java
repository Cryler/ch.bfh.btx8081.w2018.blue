
/**
 *date: 27.12.2018   -  time: 14:09:28
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entity.UserEntity;

public class UserService {

	private static String username;
	private static EntityManager em;
	private static EntityTransaction transaction;

	public UserService(String user) {
		UserService.username = user;
	}

	public static UserEntity getUser() {
		UserService.em = EMService.getEM();
		UserService.transaction = em.getTransaction();
		UserService.transaction.begin();
		Query q = UserService.em.createNativeQuery(
				"select * from usertable where usertable.username = '" + UserService.username + "'", UserEntity.class);
		UserEntity entity = (UserEntity) q.getSingleResult();
		UserService.em.flush();
		UserService.transaction.commit();
		return entity;
	}
}
