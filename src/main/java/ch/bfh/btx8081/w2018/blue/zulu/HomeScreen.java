
/**
 *date: 20.11.2018   -  time: 15:18:02
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package ch.bfh.btx8081.w2018.blue.zulu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("Home")
public class HomeScreen extends VerticalLayout {

	public HomeScreen() {
		Button calendarButton = new Button("Calendar");

		Button newPatientButton = new Button("Neuer Patient");

		Button searchPatientButton = new Button("Patient suchen");

		this.add(calendarButton, newPatientButton, searchPatientButton);
	}

}
