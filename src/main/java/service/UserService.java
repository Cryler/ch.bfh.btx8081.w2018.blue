
/**
 *date: 27.12.2018   -  time: 14:09:28
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package service;

import entity.UserEntity;

/**
 * The Class UserService.
 * 
 * @author gundy1.
 */
public class UserService {

	/** The user. */
	private static UserEntity user;

	/**
	 * Instantiates a new user service and stores the username of the currently logged user in the {@value username}.
	 *
	 * @param entity the entity
	 */
	public UserService(UserEntity entity) {	
		if(UserService.user == null) {
			UserSingleton.logout();
			UserService.user = entity;
		}
	}
	
	/**
	 * Gets the current user.
	 *
	 * @return the user
	 */
	public static UserEntity getUser() {
		return UserSingleton.getUser();
	}
	
	/**
	 * Clears the value of the singleton.
	 */
	public static void logout() {
		UserSingleton.logout();
		UserService.user = null;
	}	
	
	/**
	 * Gets the user for singleton.
	 *
	 * @return the user for singleton
	 */
	private static UserEntity getUserForSingleton() {
		return UserService.user;
	}
	
	

	
	/**
	 * The Class UserSingleton that holds the reference on the current logged in user.
	 */
	private static class UserSingleton{
		
		/** The instance. */
		private static UserSingleton instance;
		
		/** The user that is logged 	in. */
		private static UserEntity user;
		
		/**
		 * Gets the current user.
		 *
		 * @return the user
		 */
		private static UserEntity getUser() {
			if(UserSingleton.instance == null) {
				UserSingleton.instance = new UserSingleton();
				UserSingleton.user = UserService.getUserForSingleton();
			}
			return UserSingleton.user;
		}
		
		/**
		 * Logout, clears the reference on the current logged in user.
		 */
		private static void logout() {
			UserSingleton.user = null;
			UserSingleton.instance = null;
		}
	}
}
