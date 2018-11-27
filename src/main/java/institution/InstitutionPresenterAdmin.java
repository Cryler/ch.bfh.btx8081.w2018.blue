
/**
 *date: 22.11.2018   -  time: 17:49:24
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package institution;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

import address.Address;

public class InstitutionPresenterAdmin extends InstitutionPresenter {

	public InstitutionPresenterAdmin() {

	}

	public void settingsButtonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate("Settings"));
	}

	public void setInstitutionName(String institutionName) {
		super.model.setInstitutionName(institutionName);
	}

	public void setInstitutionAddress(Address address) {
		super.model.setAddress(address);
	}

}
