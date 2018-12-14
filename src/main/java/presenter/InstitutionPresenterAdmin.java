
/**
 *date: 22.11.2018   -  time: 17:49:24
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package presenter;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

import model.Address;
import model.InstitutionModelAdmin;

public class InstitutionPresenterAdmin extends InstitutionPresenter {

	public InstitutionPresenterAdmin() {
		super();
	}

	public void settingsButtonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate("Settings"));
	}

	public void setInstitutionName(String institutionName) {
		InstitutionModelAdmin model = new InstitutionModelAdmin();
		model.setInstitutionName(institutionName);
	}

	public void setInstitutionAddress(Address newAddress) {
		InstitutionModelAdmin model = new InstitutionModelAdmin();
		model.setInstitutionAddress(newAddress);
	}
}
