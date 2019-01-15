
/**
 *date: 22.11.2018   -  time: 17:49:24
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package presenter;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

import entity.Address;
import model.InstitutionModelAdmin;

/**
 * The Class InstitutionPresenterAdmin.
 * @author gundy1.
 */
public class InstitutionPresenterAdmin extends InstitutionPresenter {

	
	public InstitutionPresenterAdmin() {
		super();
	}

	/**
	 * Navigates to the View based on the Button that triggered the Clickevent.
	 *
	 * @param e the ClickEvent
	 */
	public void settingsButtonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate("Settings"));
	}

	/**
	 * Sets the institution name.
	 *
	 * @param institutionName the new institution name
	 */
	public void setInstitutionName(String institutionName) {
		InstitutionModelAdmin model = new InstitutionModelAdmin();
		model.setInstitutionName(institutionName);
	}

	/**
	 * Sets the institution address.
	 *
	 * @param newAddress the new institution address
	 */
	public void setInstitutionAddress(Address newAddress) {
		InstitutionModelAdmin model = new InstitutionModelAdmin();
		model.setInstitutionAddress(newAddress);
	}
}
