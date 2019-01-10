package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.ui.LoadMode;

import entity.PatientEntity;
import service.PatientService;
import service.UserService;

/**
 * View for the new session site.
 * 
 * @author Luca Leuenberger
 *
 */

@Route("Patient")
@StyleSheet(value = "styles/style.css", loadMode = LoadMode.INLINE)
public class PatientView extends HorizontalLayout implements BeforeEnterObserver, AfterNavigationObserver {

	VerticalLayout layout = new VerticalLayout();
	VerticalLayout layoutTabs = new VerticalLayout();
	VerticalLayout layoutMenu = new VerticalLayout();
	VerticalLayout layoutPage = new VerticalLayout();

	private TextField lastName;
	private TextField firstName;
	private TextField birthdate;
	private TextField gender;
	private TextField address;
	private TextField city;
	private TextField nationality;
	private TextField language;
	private TextField insurance;
	private TextField phoneNumber;
	private TextField email;
	private TextField ahvNr;

	private Collection<TextField> patientDataFields;
	
	private PatientEntity currentShowedPatient;

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		if (UserService.getUser() == null) {
			event.rerouteTo("");
		}
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		this.initData();
	}

	private void initData() {
			this.currentShowedPatient = PatientService.getPatient();
		try {
			this.lastName.setValue(currentShowedPatient.getLastName());
			this.firstName.setValue(currentShowedPatient.getFirstName());
			this.birthdate.setValue(currentShowedPatient.getBirthdate().toString());
			this.gender.setValue(currentShowedPatient.getGender());
			this.address.setValue(currentShowedPatient.getAddress());
			this.city.setValue(currentShowedPatient.getCity());
			this.nationality.setValue(currentShowedPatient.getNationality());
			this.language.setValue(currentShowedPatient.getLanguage());
			this.insurance.setValue(currentShowedPatient.getInsurance());
			this.phoneNumber.setValue(currentShowedPatient.getPhoneNumber());
			this.email.setValue(currentShowedPatient.getEmail());
			this.ahvNr.setValue(currentShowedPatient.getAhvNr());
		} catch (NullPointerException e) {

		}
	}

	/**
	 * Constructor for the session site.
	 */
	public PatientView() {
		menu();
		patient();
		tabsNavigation();
		tabsDate();
		this.layoutPage.add(layoutTabs, layout);
		this.add(layoutMenu, layoutPage);
	}

	/**
	 * Vertical layout with a text field to see whose patient session it is.
	 */
	private void patient() {
		FormLayout newPatientLayout = new FormLayout();

		// The object that will be edited
		// PatientModel patientCreate = new PatientModel();

		// The fields for the Form

		this.patientDataFields = new ArrayList<>();

		this.lastName = this.createTextField();
		this.patientDataFields.add(this.lastName);
		this.firstName = this.createTextField();
		this.patientDataFields.add(this.firstName);
		this.birthdate = this.createTextField();
		this.patientDataFields.add(this.birthdate);
		this.gender = this.createTextField();
		this.patientDataFields.add(this.gender);
		this.address = this.createTextField();
		this.patientDataFields.add(this.address);
		this.city = this.createTextField();
		this.patientDataFields.add(this.city);
		this.nationality = this.createTextField();
		this.patientDataFields.add(this.nationality);
		this.language = this.createTextField();
		this.patientDataFields.add(this.language);
		this.phoneNumber = this.createTextField();
		this.patientDataFields.add(this.phoneNumber);
		this.email = this.createTextField();
		this.patientDataFields.add(this.email);
		this.insurance = this.createTextField();
		this.patientDataFields.add(this.insurance);
		this.ahvNr = this.createTextField();
		this.patientDataFields.add(this.ahvNr);

		newPatientLayout.addFormItem(lastName, "Nachname");
		newPatientLayout.addFormItem(firstName, "Vorname");
		newPatientLayout.addFormItem(birthdate, "Geburtsdatum");
		newPatientLayout.addFormItem(gender, "Geschlecht");
		newPatientLayout.addFormItem(address, "Adresse");
		newPatientLayout.addFormItem(city, "Wohnort");
		newPatientLayout.addFormItem(nationality, "Nationalität");
		newPatientLayout.addFormItem(language, "Sprache");
		newPatientLayout.addFormItem(phoneNumber, "Telefonnummer");
		newPatientLayout.addFormItem(email, "Email");
		newPatientLayout.addFormItem(insurance, "Krankenkasse");
		newPatientLayout.addFormItem(ahvNr, "AHV-Nr.");

		Button editButton = new Button("Bearbeiten", new Icon(VaadinIcon.EDIT));
		editButton.addClickListener(event -> {
			for (TextField field : this.patientDataFields) {
				field.setEnabled(true);
			}
			this.layout.add(new Button("speichern", e -> {
				Notification.show("works");
			}));
		});
		this.layout.add(newPatientLayout, editButton);
	}

	/**
	 * Vertical layout with text areas and scales for various information asked
	 * during the session.
	 */

	/**
	 * Tabs to navigate between the patients base data, sessions and diagrams.
	 */
	private void tabsNavigation() {
		HorizontalLayout layoutTabs = new HorizontalLayout();
		Tab tab1 = new Tab("Stammdaten");
		String page1 = "New Session";

		Tab tab2 = new Tab("Konsultationen");
		String page2 = "Session";

		Tab tab3 = new Tab("Diagramme");
		String page3 = "Diagram";

		Map<Tab, String> tabsAndPages = new HashMap<>();
		tabsAndPages.put(tab1, page1);
		tabsAndPages.put(tab2, page2);
		tabsAndPages.put(tab3, page3);
		Tabs tabs = new Tabs(tab1, tab2, tab3);
		tabs.setSelectedTab(tab1);
		tabs.addSelectedChangeListener(event -> {
			String selectedPage = tabsAndPages.get(tabs.getSelectedTab());
			tabs.getUI().ifPresent(ui -> ui.navigate(selectedPage));
		});

		layoutTabs.add(tabs);
		this.layoutTabs.add(layoutTabs);

	}

	/**
	 * Tabs to navigate between the different session dates.
	 */
	private void tabsDate() {
		Tabs tabs = newTabs();
		HorizontalLayout layoutTabs = new HorizontalLayout();
		tabs.addSelectedChangeListener(event -> {

		});

		layoutTabs.add(tabs);
		this.layoutTabs.add(layoutTabs);
	}

	/**
	 * Creates tabs for each date of all sessions.
	 * 
	 * @return all tabs in form of a Vaadin Tabs object.
	 */
	private Tabs newTabs() {
		Tabs tabs = new Tabs();
		for (int i = 0; i < 10; i++) { // db.getSessionCount() instead of 10
			String tab = "Tab" + i; // db.getSessionDate()
			Tab tabTemp = new Tab(tab);
			tabs.add(tabTemp);
		}
		return tabs;
	}

	private void menu() {
		Button home = new Button("Zurück zum Hautpmenü");
		home.setWidth("230px");
		home.addClickListener(e -> {
			home.getUI().ifPresent(ui -> ui.navigate("Home"));
		});

		Button patNew = new Button("Neuer Patient erfassen");
		patNew.setWidth("230px");
		patNew.addClickListener(e -> {
			patNew.getUI().ifPresent(ui -> ui.navigate("New Patient"));
		});

		Button patList = new Button("Patientenliste");
		patList.setWidth("230px");
		patList.addClickListener(e -> {
			patList.getUI().ifPresent(ui -> ui.navigate("Patientlist"));
		});

		Button calendar = new Button("Kalender");
		calendar.setWidth("230px");
		calendar.addClickListener(e -> {
			calendar.getUI().ifPresent(ui -> ui.navigate("Calendar"));
		});

		Button sesNew = new Button("Neue Konsultation");
		sesNew.setWidth("230px");
		sesNew.addClickListener(e -> {
			sesNew.getUI().ifPresent(ui -> ui.navigate("New Session"));
		});

		Button logout = new Button("Logout");
		logout.setWidth("230px");
		logout.addClickListener(e -> {
			logout.getUI().ifPresent(ui -> ui.navigate("Logout"));
		});

		VerticalLayout layout = new VerticalLayout(home, patNew, patList, calendar, sesNew, logout);
		layout.setSizeFull();
		this.layoutMenu.setWidth("250px");
		this.layoutMenu.add(layout);
	}

	private TextField createTextField() {
		TextField field = new TextField();
		field.setEnabled(false);
		return field;
	}

}