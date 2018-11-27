
/**
 *date: 22.11.2018   -  time: 15:15:53
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package institution;

import java.util.Observable;
import java.util.Observer;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

import address.Address;

public class InstitutionPresenter extends Observable implements Observer {

	protected InstitutionModel model;

	public InstitutionPresenter() {

		this.model = new InstitutionModel();
		this.model.addObserver(this);
	}
	
	public void buttonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate(e.getSource().getText()));
	}	

	public String getInstitutionName() {
		return this.model.getInstitutionName();
	}

	public Address getInstitutionAddress() {
		return this.model.getAddress();
	}

	
	@Override
	public void update(Observable o, Object arg) {
		this.setChanged();
		this.notifyObservers();
	}
}