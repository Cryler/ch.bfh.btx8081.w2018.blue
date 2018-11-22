
/**
 *date: 22.11.2018   -  time: 17:49:24
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package institution;

import address.Address;

public class InstitutionPresenterAdmin extends InstitutionPresenter {

	private InstitutionModel model;
	private InstitutionView view;

	public InstitutionPresenterAdmin(InstitutionView view) {
		super(view);
		this.model = super.getModel();
		this.view = super.getView();
	}

	public void setInstitutionName(String institutionName) {
		this.model.setInstitutionName(institutionName);
	}

	public void setInstitutionAddress(Address address) {
		this.model.setAddress(address);
	}

}
