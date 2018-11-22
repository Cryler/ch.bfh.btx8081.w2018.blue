
/**
 *date: 22.11.2018   -  time: 15:15:53
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package institution;

import address.Address;

public class InstitutionPresenter {

	protected InstitutionModel model;
	private InstitutionView view;

	public InstitutionPresenter(InstitutionView view) {
		
		this.view = view;
		this.model = new InstitutionModel(this);

	}

	public String getInstitutionName() {
		return this.model.getInstitutionName();
	}

	public Address getInstitutionAddress() {
		return this.model.getAddress();
	}

	public InstitutionModel getModel() {
		return this.model;
	}

	public InstitutionView getView() {
		return this.view;
	}

}
