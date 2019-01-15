
/**
 *date: 11.12.2018   -  time: 20:07:15
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package presenter;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

import model.CalendarModel;

/**
 * The Class CalendarPresenter.
 * 
 * @author gundy1.
 */
public class CalendarPresenter {
	
	private CalendarModel model;
	
	/**
	 * Instantiates a new calendar presenter and sets the {@value model} with a new instance of the {@code CalendarModel}.
	 */
	public CalendarPresenter() {
		this.model = new CalendarModel();
	}

	/**
	 * Navigates to the View based on the Button that triggered the Clickevent.
	 *
	 * @param e the ClickEvent
	 */
	public void buttonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate(e.getSource().getText()));
	}

	/**
	 * Gets the institution data.
	 *
	 * @return the institution data
	 */
	public String getInstitutionData() {
		return this.model.getInstitutionData();
	}
}
