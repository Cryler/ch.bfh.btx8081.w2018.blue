package ch.bfh.btx8081.w2018.blue.zulu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
/**
 * 
 * @author Luca Leuenberger
 *
 */
@Route("New Session")
public class NewSession extends HorizontalLayout {

	final HorizontalLayout layout = new HorizontalLayout();
	VerticalLayout layoutPatient = new VerticalLayout();

	public NewSession() { 
		session();
		patient();
		action();
		this.add(layout, layoutPatient);

	}

	private void patient() {
		ComboBox<String> combobox = new ComboBox<String>("Patient auswählen:");
		combobox.setItems("Leuenberger, Luca", "Gund, Yann", "Gehri, Yannick"); // TODO Connect with patientlist
		
		this.layoutPatient.add(combobox);

	}

	private void session() {
		VerticalLayout layout = new VerticalLayout();
		TextArea condition = new TextArea("Zustand des Patienten/Informationen der Session:");
		condition.setWidth("500px");
		condition.setHeight("300px");
		Label lblCraving = new Label("Craving Skala");
		RadioButtonGroup<Integer> craving = new RadioButtonGroup<>();
		craving.setItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//		craving.addValueChangeListener(e -> {
//			  e.getValue();
//		});
		layout.add(condition, lblCraving, craving);
		this.layout.add(layout);
	}

	
	private void action() {
		HorizontalLayout layout = new HorizontalLayout();
		Button save = new Button("Speichern");
		save.addClickListener(e -> {
			save.getUI().ifPresent(ui -> ui.navigate("patient sessions")); // put in right route
		});
		Button cancel = new Button("Abbrechen");
		cancel.addClickListener(e -> {
			cancel.getUI().ifPresent(ui -> ui.navigate("Home")); // put in right route
		});
		layout.add(cancel, save);
		layout.setDefaultVerticalComponentAlignment(Alignment.START);
		layoutPatient.setHorizontalComponentAlignment(Alignment.START, layout);
		this.layoutPatient.add(layout);
	}

}