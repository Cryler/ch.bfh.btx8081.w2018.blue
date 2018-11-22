
/**
 *date: 22.11.2018   -  time: 15:15:53
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package institution;

import address.Address;

public class InstitutionPresenter {
	
	private InstitutionModel model;
	private InstitutionView view;
	
	public InstitutionPresenter() {
		this.model = new InstitutionModel();
	}
	
	public String getInstitutionName() {
		return this.model.getInstitutionName();
	}
	
	public Address getInstitutionAddress() {
		return this.model.getAddress();
	}
	
	
}
