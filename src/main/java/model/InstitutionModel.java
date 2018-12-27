
/**
 *date: 22.11.2018   -  time: 15:15:37
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entity.Address;
import entity.InstitutionEntity;
import entity.UserEntity;
import service.EMService;
import service.UserService;

/**
 * This class stores and represents the {@code Institution} in the DB. It
 * contains a name and a {@code Address}.
 * 
 * 
 * @author yanng
 *
 */

public class InstitutionModel {

	private EntityManager em;
	private EntityTransaction transaction;

	/**
	 * The {@value institutionID} is set to 1 because there should only be one
	 * institution at a given time.
	 * 
	 */

	public String getInstitutionName() {
		UserEntity currentUser = UserService.getUser();
		try {
			return currentUser.getInstitution().getInstitutionName();
		} catch (NullPointerException e) {
			return "Default_Name";
		}
	}
	
	

	public Address getInstitutionAddress() {
		UserEntity currentUser = UserService.getUser();
		try {
			return currentUser.getInstitution().getInstitutionAddress();
		} catch (NullPointerException e) {
			return this.createDefaultAddress();
		}

	}


	private Address createDefaultAddress() {
		Address defaultAddress = new Address();
		defaultAddress.setStreet("Default_Street");
		defaultAddress.setStreetNr(1);
		defaultAddress.setCity("Default_City");
		defaultAddress.setZipCode(1234);
		return defaultAddress;
	}

}
