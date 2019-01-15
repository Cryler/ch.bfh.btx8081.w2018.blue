package view;

import java.time.LocalDate;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import entity.PatientEntity;
import entity.SessionEntity;
import presenter.SessionPresenter;
import service.PatientService;
import service.UserService;

/**
 * View class for the new session site.
 * 
 * @author leuel3
 * @author gundy1
 *
 */
@Route("Neue Konsultation")
public class NewSessionView extends VerticalLayout implements BeforeEnterObserver, AfterNavigationObserver {

	private SessionPresenter presenter;
	private PatientEntity patient;

	private TextField patientInfo;
	private TextArea condition;
	private RadioButtonGroup<Integer>  craving;

	private HorizontalLayout layout = new HorizontalLayout();

	/**
	 * Verifier to check if the user is properly logged in. If not, he will be redirected to the login screen. 
	 */
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		if (UserService.getUser() == null) {
			event.rerouteTo("");
		}
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		this.patient = PatientService.getPatient();
		this.patientInfo.setValue(this.patient.getLastName() + " " + this.patient.getFirstName());
	}

	/**
	 * Constructor for the new session site.
	 */
	public NewSessionView() {
		this.presenter = new SessionPresenter();
		this.initGraphicalContent();
	}

	private void initGraphicalContent() {
		this.menu();
		this.session();
		this.action();
		this.add(layout);
	}

	/**
	 * Menu bar on the left side of the screen, to quickly navigate to other sites of our application.
	 */
	private void menu() {
		VerticalLayout vl1 = new VerticalLayout();
		vl1.setWidth("250px");
		vl1.add(this.createMenuButton("Home", new Icon(VaadinIcon.HOME)));
		vl1.add(this.createMenuButton("Kalender", new Icon(VaadinIcon.CALENDAR)));
		vl1.add(this.createMenuButton("Neuer Patient", new Icon(VaadinIcon.USER_CHECK)));
		vl1.add(this.createMenuButton("Patient suchen", new Icon(VaadinIcon.USERS)));
		vl1.add(this.createMenuButton("Logout", new Icon(VaadinIcon.POWER_OFF)));
		this.layout.add(vl1);
	}

	/**
	 * Vertical layout with text areas and scales for various information asked
	 * during the session.
	 */
	private void session() {
		VerticalLayout layoutSession = new VerticalLayout();
		this.condition = new TextArea("Zustand des Patienten/Informationen der Session:");
		this.condition.setWidth("600px");
		this.condition.setHeight("300px");
		Label lblCraving = new Label("Craving Skala");
		this.craving = new RadioButtonGroup<>();
		this.craving.setItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		layoutSession.add(this.condition, lblCraving, this.craving);
		this.layout.add(layoutSession);
	}

	/**
	 * Horizontal layout with 2 buttons to save or cancel the session.
	 */
	private void action() {

		VerticalLayout vl1 = new VerticalLayout();
		this.patientInfo = new TextField();
		this.patientInfo.setEnabled(false);
		this.patientInfo.setLabel("Patient:");

		DatePicker datepicker = new DatePicker(LocalDate.now());

		HorizontalLayout hl1 = new HorizontalLayout();
		Button save = new Button("Speichern");
		save.addClickListener(e -> {
			SessionEntity entity = new SessionEntity();
			entity.setDescription(this.condition.getValue());
			entity.setCraving(this.craving.getValue());
			entity.setDate(datepicker.getValue());
			entity.setPatient(this.patient);
			this.presenter.saveButtonClicked(entity, e);
		});
		Button cancel = new Button("Abbrechen");
		cancel.addClickListener(e -> {
			cancel.getUI().ifPresent(ui -> ui.navigate("Home")); // put in right route
		});
		hl1.add(cancel, save);
		hl1.getStyle().set("padding-top", "200px");
		vl1.add(this.patientInfo, datepicker, hl1);
		this.layout.add(vl1);

	}

	private Button createMenuButton(String value, Icon icon) {
		Button newButton = new Button(value, icon);
		newButton.addClickListener(e -> {
			this.presenter.menuButtonClicked(e);
		});
		newButton.setWidth("200px");
		return newButton;
	}
}