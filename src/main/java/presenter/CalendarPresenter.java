
/**
 *date: 11.12.2018   -  time: 20:07:15
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package presenter;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

import model.CalendarModel;

public class CalendarPresenter {
	
	private CalendarModel model;
	
	public CalendarPresenter() {
		this.model = new CalendarModel();
	}

	public void buttonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate(e.getSource().getText()));
	}

	public String getInstitutionData() {
		return this.model.getInstitutionData();
	}
}
