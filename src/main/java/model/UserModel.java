
/**
 *date: 21.12.2018   -  time: 13:19:56
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entity.UserEntity;
import exception.InvalidEmailException;
import exception.InvalidPasswordException;
import exception.InvalidUsernameException;
import service.EMService;

public class UserModel {

	private EntityManager em;
	private EntityTransaction transaction;

	public void setUser(UserEntity user) throws InvalidUsernameException, InvalidEmailException {
		this.em = EMService.getEM();
		this.transaction = EMService.getTransaction();
		this.transaction.begin();
		try {
			Query q = this.em.createNativeQuery(
					"select * from usertable where usertable.username = '" + user.getUsername() + "'",
					UserEntity.class);
			q.getSingleResult();
			throw new InvalidUsernameException("Username existiert bereits");
		} catch (NoResultException e) {
			try {
				Query q2 = this.em.createNativeQuery(
						"select * from usertable where usertable.email = '" + user.getEmail() + "'", UserEntity.class);
				q2.getSingleResult();
				throw new InvalidEmailException("Für diese Email wurde bereits ein Benutzer registriert.");
			} catch (NoResultException e1) {
			}
			em.persist(user);
		} finally {
			this.closeConnection();
		}
	}

	public void loginUser(String username, String password) throws InvalidUsernameException, InvalidPasswordException {
		this.em = EMService.getEM();
		this.transaction = EMService.getTransaction();
		this.transaction.begin();
		try {
			Query q = this.em.createNativeQuery("select * from usertable where usertable.username = '" + username + "'",
					UserEntity.class);
			UserEntity entity = (UserEntity) q.getSingleResult();
			if(!entity.getPassword().equals(password)) {
				throw new InvalidPasswordException("Das Passwort ist nicht korrekt.");
			}			
		} catch (NoResultException e) {
			throw new InvalidUsernameException("Benutzername existiert nicht.");
		} finally {
			this.closeConnection();
		}
	}

	private void closeConnection() {
		this.em.flush();
		this.transaction.commit();
	}
}
