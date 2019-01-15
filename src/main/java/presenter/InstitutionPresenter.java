
/**
 *date: 22.11.2018   -  time: 15:15:53
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package presenter;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

import entity.Address;
import model.InstitutionModel;
import view.InstitutionViewInterface;


/**
 * The Class InstitutionPresenter.
 * @author gundy1.
 */
public class InstitutionPresenter implements InstitutionViewInterface {


	/**
	 * Navigates to the View based on the Button that triggered the Clickevent.
	 *
	 * @param e the ClickEvent
	 */	
	public void buttonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate(e.getSource().getText()));
	}

	/**
	 * Gets the institution name.
	 *
	 * @return the institution name
	 */
	public String getInstitutionName() {
		InstitutionModel model = new InstitutionModel();
		return model.getInstitutionName();
	}

	/**
	 * Gets the institution address.
	 *
	 * @return the institution address
	 */
	public Address getInstitutionAddress() {
		InstitutionModel model = new InstitutionModel();
		return model.getInstitutionAddress();
	}
	

}