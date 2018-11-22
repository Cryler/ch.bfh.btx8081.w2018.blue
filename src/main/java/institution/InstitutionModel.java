
/**
 *date: 22.11.2018   -  time: 15:15:37
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package institution;

import address.Address;

public class InstitutionModel {

	private String institutionName;
	private Address address;

	public InstitutionModel() {

	}

	public String getInstitutionName() {
		return this.institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
