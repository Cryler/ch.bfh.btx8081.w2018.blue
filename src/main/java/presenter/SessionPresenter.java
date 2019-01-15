
/**
 *date: 11.01.2019   -  time: 14:45:35
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package presenter;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

import entity.SessionEntity;
import model.SessionModel;

/**
 * The Class SessionPresenter.
 * @author gundy1.
 */
public class SessionPresenter {

	/**
	 * Navigates to the View based on the Button that triggered the Clickevent.
	 *
	 * @param e the ClickEvent
	 */
	public void menuButtonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate(e.getSource().getText()));

	}
	
	/**
	 * Save button clicked. Passes the Data of the new Session to the model, that will store it into the db.
	 *
	 * @param entity the entity
	 * @param e the ClickEvent
	 */
	public void saveButtonClicked(SessionEntity entity, ClickEvent<Button> e) {
		new SessionModel().setSession(entity);
		e.getSource().getUI().ifPresent(ui -> ui.navigate("Patient"));
	}
}
