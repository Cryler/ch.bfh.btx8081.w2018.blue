
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

public class SessionPresenter {

	public void menuButtonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate(e.getSource().getText()));

	}
	
	public void saveButtonClicked(SessionEntity entity, ClickEvent<Button> e) {
		new SessionModel().setSession(entity);
		e.getSource().getUI().ifPresent(ui -> ui.navigate("Patient"));
	}
}
