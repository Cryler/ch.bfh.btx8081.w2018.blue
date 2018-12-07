 package view;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import model.PatientModel;

/**
 * View for the new session site.
 * 
 * @author Luca Leuenberger
 *
 */

@Route("Patient")
public class PatientView extends HorizontalLayout {

	HorizontalLayout layout = new HorizontalLayout();
	VerticalLayout layoutTabs = new VerticalLayout();
	VerticalLayout layoutMenu = new VerticalLayout();
	VerticalLayout layoutPage = new VerticalLayout();

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
		Binder<PatientModel> binder = new Binder<>();
		
		// The object that will be edited
		// PatientModel patientCreate = new PatientModel();
		
		//The fields for the Form
				
		TextField lastName = new TextField();
		lastName.setValueChangeMode(ValueChangeMode.EAGER);
		TextField firstName = new TextField();
		firstName.setValueChangeMode(ValueChangeMode.EAGER);
		DatePicker birthDate = new DatePicker();
		ComboBox<String> gender = new ComboBox();
		TextField address = new TextField();
		address.setValueChangeMode(ValueChangeMode.EAGER);
		TextField city = new TextField();
		city.setValueChangeMode(ValueChangeMode.EAGER);
		TextField nationality = new TextField();
		nationality.setValueChangeMode(ValueChangeMode.EAGER);
		TextField language = new TextField();
		language.setValueChangeMode(ValueChangeMode.EAGER);
		TextField phoneNumber = new TextField();
		phoneNumber.setValueChangeMode(ValueChangeMode.EAGER);
		TextField email = new TextField();
		email.setValueChangeMode(ValueChangeMode.EAGER);
		TextField insurance = new TextField();
		insurance.setValueChangeMode(ValueChangeMode.EAGER);
		TextField ahvNr = new TextField();
		ahvNr.setValueChangeMode(ValueChangeMode.EAGER);
		
		
		//Combobox for gender
		gender.setItems("weiblich","männlich");
		gender.setPlaceholder("Geschlecht auswählen");
		
		newPatientLayout.addFormItem(lastName, "Nachname");
		newPatientLayout.addFormItem(firstName, "Vorname");
		newPatientLayout.addFormItem(birthDate, "Geburtsdatum");
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