
/**
 *date: 22.11.2018   -  time: 15:15:37
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package model;

import entity.Address;
import entity.UserEntity;
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

	/**
	 * The {@value institutionID} is set to 1 because there should only be one
	 * institution at a given time.
	 * @throws NoUserLoggedInException 
	 * 
	 */

	public String getInstitutionName(){		
		try {
			UserEntity currentUser = UserService.getUser();
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
