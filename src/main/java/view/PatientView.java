package view;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import presenter.PatientPresenter;
import service.LocalDateConverter;
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
	private Button editButton;
	private Button saveButton;

	private Collection<TextField> patientDataFields;

	private PatientEntity currentShowedPatient;

	private PatientPresenter presenter;

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

	/**
	 * Constructor for the session site.
	 */
	public PatientView() {
		this.presenter = new PatientPresenter();
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
		
		this.editButton = this.createEditButton();
		this.saveButton = this.createSaveButton();

		HorizontalLayout hl1 = new HorizontalLayout();
		hl1.add(this.editButton, this.saveButton);
		this.layout.add(newPatientLayout, hl1);
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
		Button home = new Button("Home",new Icon(VaadinIcon.HOME));
		home.setWidth("200px");
		home.addClickListener(e -> {
			home.getUI().ifPresent(ui -> ui.navigate("Home"));
		});

		Button patNew = new Button("Neuer Patient", new Icon(VaadinIcon.USER_CHECK));
		patNew.setWidth("200px");
		patNew.addClickListener(e -> {
			patNew.getUI().ifPresent(ui -> ui.navigate("Neuer Patient"));
		});

		Button patList = new Button("Patient suchen",new Icon(VaadinIcon.USERS));
		patList.setWidth("200px");
		patList.addClickListener(e -> {
			patList.getUI().ifPresent(ui -> ui.navigate("Patient suchen"));
		});

		Button calendar = new Button("Kalender",new Icon(VaadinIcon.CALENDAR));
		calendar.setWidth("200px");
		calendar.addClickListener(e -> {
			calendar.getUI().ifPresent(ui -> ui.navigate("Kalender"));
		});

//		Button sesNew = new Button("Neue Konsultation");
//		sesNew.setWidth("230px");
//		sesNew.addClickListener(e -> {
//			sesNew.getUI().ifPresent(ui -> ui.navigate("New Session"));
//		});

		Button logout = new Button("Logout",new Icon(VaadinIcon.POWER_OFF));
		logout.setWidth("200px");
		logout.addClickListener(e -> {
			logout.getUI().ifPresent(ui -> ui.navigate("Logout"));
		});

		VerticalLayout layout = new VerticalLayout(home, patNew, patList, calendar, logout);
		layout.setSizeFull();
		this.layoutMenu.setWidth("250px");
		this.layoutMenu.add(layout);
	}

	private TextField createTextField() {
		TextField field = new TextField();
		field.setEnabled(false);
		return field;
	}

	private Button createEditButton() {
		Button editButton = new Button("Bearbeiten", new Icon(VaadinIcon.EDIT));
		editButton.addClickListener(event -> {
			for (TextField field : this.patientDataFields) {
				field.setEnabled(true);
			}
			this.editButton.setEnabled(false);
			this.saveButton.setEnabled(true);
		});
		return editButton;
	}

	private Button createSaveButton() {
		Button saveButton = new Button("speichern");
		saveButton.addClickListener(event -> {
			this.updateCurrentPatient();
			this.presenter.editSaveButtonClicked(this.currentShowedPatient);
			for (TextField field : this.patientDataFields) {
				field.setEnabled(false);
			}
			this.saveButton.setEnabled(false);
			this.editButton.setEnabled(true);
		});
		saveButton.setEnabled(false);
		return saveButton;
	}

	private void updateCurrentPatient() {
		try {
			this.currentShowedPatient.setFirstName(this.firstName.getValue());
			this.currentShowedPatient.setLastName(this.lastName.getValue());
			this.currentShowedPatient.setBirthdate(LocalDate.parse(this.birthdate.getValue()));
			this.currentShowedPatient.setAddress(this.address.getValue());
			this.currentShowedPatient.setCity(this.city.getValue());
			this.currentShowedPatient.setGender(this.gender.getValue());
			this.currentShowedPatient.setLanguage(this.language.getValue());
			this.currentShowedPatient.setNationality(this.nationality.getValue());
			this.currentShowedPatient.setInsurance(this.insurance.getValue());
			this.currentShowedPatient.setEmail(this.email.getValue());
			this.currentShowedPatient.setAhvNr(this.ahvNr.getValue());
			this.currentShowedPatient.setPhoneNumber(this.phoneNumber.getValue());
		} catch (NullPointerException e) {

		}
	}

}