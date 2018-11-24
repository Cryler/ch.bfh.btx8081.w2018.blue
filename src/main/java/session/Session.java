package session;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

/**
 * View for the new session site. 
 * 
 * @author Luca Leuenberger
 *
 */
@Route("Session")
public class Session extends VerticalLayout {
	
	HorizontalLayout layout = new HorizontalLayout();

	/**
	 * Cosntructor for the new session site. 
	 */
	public Session() {
		session();
		patient();
		action();
		this.add(layout);
	}

	/**
	 * Vertical layout with a combobox to select whose patient session it is. 
	 */
	private void patient() {
		VerticalLayout layoutPatient = new VerticalLayout();
		ComboBox<String> combobox = new ComboBox<String>("Patient auswählen:");
		combobox.setItems("Leuenberger, Luca", "Gund, Yann", "Gehri, Yannick"); // TODO Connect with patientlist
		combobox.setPlaceholder("Auswählen");
		layoutPatient.add(combobox);
		layoutPatient.setAlignItems(Alignment.START);
		this.layout.add(layoutPatient);
	}

	/**
	 * Vertical layout with text areas and scales for various information asked during the session. 
	 */
	private void session() {
		VerticalLayout layoutSession = new VerticalLayout();
		TextArea condition = new TextArea("Zustand des Patienten/Informationen der Session:");
		condition.setWidth("600px");
		condition.setHeight("300px");
		Label lblCraving = new Label("Craving Skala");
		RadioButtonGroup<Integer> craving = new RadioButtonGroup<>();
		craving.setItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//		craving.addValueChangeListener(e -> {
//			  e.getValue();
//		});
		layoutSession.add(condition, lblCraving, craving);
		this.layout.add(layoutSession);
		this.layout.setAlignSelf(Alignment.CENTER, layoutSession);
	}

	/**
	 * Horizontal layout with 2 buttons to save or cancel the session.
	 */
	private void action() {
		VerticalLayout layoutAction = new VerticalLayout();
		HorizontalLayout layoutButtons = new HorizontalLayout();
		Button save = new Button("Speichern");
		save.addClickListener(e -> {
			save.getUI().ifPresent(ui -> ui.navigate("patient sessions")); // put in right route
		});
		Button cancel = new Button("Abbrechen");
		cancel.addClickListener(e -> {
			cancel.getUI().ifPresent(ui -> ui.navigate("Home")); // put in right route
		});
		layoutButtons.add(cancel, save);

		layoutAction.add(layoutButtons);
		layoutAction.setAlignItems(Alignment.START);
		this.layout.add(layoutAction);
		this.layout.setAlignSelf(Alignment.END, layoutAction);
	}
}