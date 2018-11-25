
/**
 *date: 22.11.2018   -  time: 15:15:53
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package institution;

import java.util.Observable;
import java.util.Observer;

import address.Address;

public class InstitutionPresenter extends Observable implements Observer {

	private InstitutionModel model;

	public InstitutionPresenter() {

		this.model = new InstitutionModel();
		this.model.addObserver(this);
	}

	public String getInstitutionName() {
		return this.model.getInstitutionName();
	}

	public Address getInstitutionAddress() {
		return this.model.getAddress();
	}

	protected InstitutionModel getModel() {
		return this.model;
	}

	@Override
	public void update(Observable o, Object arg) {
		this.setChanged();
		this.notifyObservers();
	}
}