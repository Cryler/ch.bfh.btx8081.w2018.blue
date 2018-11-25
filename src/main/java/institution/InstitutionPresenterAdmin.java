
/**
 *date: 22.11.2018   -  time: 17:49:24
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package institution;

import address.Address;

public class InstitutionPresenterAdmin extends InstitutionPresenter {

	
	public InstitutionPresenterAdmin() {
				
	}

	public void setInstitutionName(String institutionName) {
		super.getModel().setInstitutionName(institutionName);
	}

	public void setInstitutionAddress(Address address) {
		super.getModel().setAddress(address);
	}

}
