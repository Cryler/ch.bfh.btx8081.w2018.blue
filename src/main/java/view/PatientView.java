 package view;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import entity.PatientEntity;
import entity.PersonEntity;
import model.PatientModel;
import service.PatientService;
import service.UserService;

/**
 * View for the new session site.
 * 
 * @author Luca Leuenberger
 *
 */

@Route("Patient")
public class PatientView extends HorizontalLayout implements BeforeEnterObserver, AfterNavigationObserver {

	HorizontalLayout layout = new HorizontalLayout();
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
		PersonEntity patient = PatientService.getPatient();
		this.lastName.setValue(patient.getLastName());
		this.firstName.setValue(patient.getFirstName());
		this.birthdate.setValue(patient.getBirthdate().toString());
		this.gender.setValue(patient.getGender());
		this.address.setValue(patient.getAddress());
		this.city.setValue(patient.getCity());
		this.nationality.setValue(patient.getNationality());
		this.language.setValue(patient.getLanguage());
		this.insurance.setValue("");
		this.phoneNumber.setValue(patient.getPhoneNumber());
		this.email.setValue(patient.getEmail());
		this.ahvNr.setValue("");
		
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
		
		//The fields for the Form
				
		this.lastName = new TextField();		
		this.firstName = new TextField();		
		this.birthdate = new TextField();
		this.gender = new TextField();
		this.address = new TextField();		
		this.city = new TextField();		
		this.nationality = new TextField();		
		this.language = new TextField();		
		this.phoneNumber = new TextField();		
		this.email = new TextField();	
		this.insurance = new TextField();		
		this.ahvNr = new TextField();
	
		
	
		
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
		
		this.layout.add(newPatientLayout);
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

	
}