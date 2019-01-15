
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
 * The Class InstitutionModel.
 * 
 * @author gundy1.
 */
public class InstitutionModel {

	/**
	 * Gets the institution name. If there is a {@code NullPointerException} no
	 * institution has been Initiated. In this case a default Name is created.
	 *
	 * @return the institution name
	 */
	public String getInstitutionName() {
		UserEntity currentUser = UserService.getUser();
		try {
			return currentUser.getInstitution().getInstitutionName();
		} catch (NullPointerException e) {
			return "Default_Name";
		}
	}

	/**
	 * Gets the institution address.If there is a {@code NullPointerException} no
	 * institution has been Initiated. In this case a default Address is created.
	 *
	 * @return the institution address
	 */
	public Address getInstitutionAddress() {
		UserEntity currentUser = UserService.getUser();
		try {
			return currentUser.getInstitution().getInstitutionAddress();
		} catch (NullPointerException e) {
			return this.createDefaultAddress();
		}
	}

	/**
	 * Creates the default address.
	 *
	 * @return the address
	 */
	private Address createDefaultAddress() {
		Address defaultAddress = new Address();
		defaultAddress.setStreet("Default_Street");
		defaultAddress.setStreetNr(1);
		defaultAddress.setCity("Default_City");
		defaultAddress.setZipCode(1234);
		return defaultAddress;
	}

}
