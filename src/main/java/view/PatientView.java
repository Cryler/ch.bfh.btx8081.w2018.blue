package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.NoResultException;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.ui.LoadMode;

import entity.PatientEntity;
import entity.SessionEntity;
import presenter.PatientPresenter;
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

	VerticalLayout layoutTabs = new VerticalLayout();

	VerticalLayout layoutPage = new VerticalLayout();

	private Div sessionInformationPage;

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
	private Button deleteButton;
	private Dialog dialog;

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
		this.initGraphicalContent();
	}

	private void initGraphicalContent() {
		createMenuComponents();
		createMainTabNavigation();

	}

	private void createMenuComponents() {
		Button home = this.createMenuButton("Home", new Icon(VaadinIcon.HOME));
		Button patNew = this.createMenuButton("Neuer Patient", new Icon(VaadinIcon.USER_CHECK));
		Button patList = this.createMenuButton("Patient suchen", new Icon(VaadinIcon.USERS));
		Button calendar = this.createMenuButton("Kalender", new Icon(VaadinIcon.CALENDAR));
		Button sesNew = this.createMenuButton("Neue Konsultation", new Icon(VaadinIcon.CALENDAR_USER));
		Button logout = this.createMenuButton("Logout", new Icon(VaadinIcon.POWER_OFF));
		VerticalLayout layoutMenu = new VerticalLayout();
		layoutMenu.setWidth("250px");
		layoutMenu.add(home, calendar, patNew, patList, sesNew, logout);
		this.add(layoutMenu);
	}

	private void createMainTabNavigation() {

		Tab tab1 = new Tab("Stammdaten");
		Div patientInformationPage = new Div();
		patientInformationPage.add(this.createPatientInformationForm());

		Tab tab2 = new Tab("Konsultationen");
		this.sessionInformationPage = new Div();
		this.sessionInformationPage.add(new Label("Alle Konsultationen des Patienten:"));
		sessionInformationPage.setVisible(false);

		Tab tab3 = new Tab("Diagramme");
		Div diagrammInformationPage = new Div();
		diagrammInformationPage
				.setText("Hier werden die Diagramme angezeigt - Dieser Bereich ist noch nicht implementiert.");
		diagrammInformationPage.setVisible(false);

		Map<Tab, Component> tabsToPages = new HashMap<>();
		tabsToPages.put(tab1, patientInformationPage);
		tabsToPages.put(tab2, sessionInformationPage);
		tabsToPages.put(tab3, diagrammInformationPage);
		Tabs tabs = new Tabs(tab1, tab2, tab3);
		Div pages = new Div(patientInformationPage, sessionInformationPage, diagrammInformationPage);
		Set<Component> pagesShown = Stream.of(patientInformationPage).collect(Collectors.toSet());

		tabs.addSelectedChangeListener(event -> {
			pagesShown.forEach(page -> page.setVisible(false));
			pagesShown.clear();
			Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
			selectedPage.setVisible(true);
			pagesShown.add(selectedPage);
		});

		this.layoutTabs.add(tabs, pages);
		this.add(this.layoutTabs);
	}

	private VerticalLayout createPatientInformationForm() {

		Label header = new Label("Stammdaten des Patienten: ");

		FormLayout newPatientLayout = new FormLayout();
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

		HorizontalLayout hl1 = new HorizontalLayout();
		this.editButton = this.createEditButton();
		this.saveButton = this.createSaveButton();
		this.deleteButton = this.createDeleteButton();
		this.dialog = this.createDialog();
		hl1.add(this.editButton, this.saveButton, this.deleteButton, this.dialog);

		VerticalLayout vl1 = new VerticalLayout();
		vl1.add(header, newPatientLayout, hl1);
		return vl1;
	}

	private VerticalLayout createTabsWithDatesOfSessions() {
		try {
			Tabs tabs = new Tabs();
			Div pages = new Div();
			Map<Tab, Component> tabsToPages = new HashMap<>();
			for (SessionEntity session : this.presenter.getSessionsOfPatient(this.currentShowedPatient)) {
				Tab tempTab = new Tab(session.getDate().toString());
				tabs.add(tempTab);
				Div tempPage = new Div(this.createSessionOverview(session));
				tempPage.setVisible(false);
				pages.add(tempPage);
				tabsToPages.put(tempTab, tempPage);
			}

			Collection<Component> pagesShown = new LinkedList<>();
			for (Tab tab : tabsToPages.keySet()) {
				pagesShown.add(tabsToPages.get(tab));
			}
			tabs.setSelectedIndex(0);
			tabsToPages.get(tabs.getComponentAt(0)).setVisible(true);

			tabs.addSelectedChangeListener(event -> {
				pagesShown.forEach(page -> page.setVisible(false));
				pagesShown.clear();
				Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
				selectedPage.setVisible(true);
				pagesShown.add(selectedPage);
			});

			VerticalLayout vl1 = new VerticalLayout();
			vl1.add(tabs, pages);
			return vl1;

		} catch (NoResultException e) {
			VerticalLayout vl1 = new VerticalLayout();
			vl1.add(new Label("Zu diesem Patienten wurden noch keine Einträge gemacht."));
			return vl1;
		}

	}

	private Component createSessionOverview(SessionEntity session) {
		VerticalLayout layoutSession = new VerticalLayout();
		TextArea condition = new TextArea("Zustand des Patienten/Informationen der Session:");
		condition.setValue(session.getDescription());
		condition.setEnabled(false);
		condition.setWidth("600px");
		condition.setHeight("300px");
		Label lblCraving = new Label("Craving Value: " + session.getCraving());
		layoutSession.add(condition, lblCraving);
		return layoutSession;
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
		this.sessionInformationPage.add(this.createTabsWithDatesOfSessions());
	}

	private TextField createTextField() {
		TextField field = new TextField();
		field.setEnabled(false);
		return field;
	}

	private Button createMenuButton(String value, Icon icon) {
		Button newButton = new Button(value, icon);
		newButton.addClickListener(e -> {
			this.presenter.menuButtonClicked(e);
		});
		newButton.setWidth("200px");
		return newButton;
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
		Button saveButton = new Button("Speichern", new Icon(VaadinIcon.SAFE));
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
	
	private Dialog createDialog() {
		Dialog dialog = new Dialog();
		VerticalLayout vdialog = new VerticalLayout();
		HorizontalLayout hddialog = new HorizontalLayout();
		
		dialog.add(new Label("Möcheten Sie den ausgewählten Patienten wirklich löschen?" ));
		dialog.setCloseOnEsc(false);
		dialog.setCloseOnOutsideClick(false);
		
		Button confimrButton = new Button("Bestätigen",event -> {
			this.presenter.deleteButtonClicked(this.currentShowedPatient);
			this.getUI().ifPresent(ui -> ui.navigate("Patient suchen"));
			dialog.close();
		}); 
		
		Button cancelButton = new Button("Abbruch", event -> {
			dialog.close();
		});
		hddialog.add(confimrButton,cancelButton);
		vdialog.add(hddialog);
		dialog.add(vdialog,vdialog);
		
		
		
		return dialog;
	}

	private Button createDeleteButton() {
		Button deleteButton = new Button("Entfernen", new Icon(VaadinIcon.TRASH));
		
		deleteButton.addClickListener(event -> { 
			dialog.open();
			
		});
		return deleteButton;
	}
	
	
//	private Button createDeleteButton() {
//		Button deleteButton = new Button("entfernen", new Icon(VaadinIcon.TRASH));
//		
//		deleteButton.addClickListener(event -> {
//			this.presenter.deleteButtonClicked(this.currentShowedPatient);
//			this.getUI().ifPresent(ui -> ui.navigate("Patient suchen"));
//		});
//		return deleteButton;
//	}

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