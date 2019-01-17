package view;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import entity.PatientEntity;
import presenter.PatientPresenter;
import service.UserService;
import service.CountriesService;


/**
 * View for the new patient site.
 * 
 *
 * @author gehry1
 *
 */

@Route("Neuer Patient")
public class NewPatientView extends HorizontalLayout implements BeforeEnterObserver {

	/** The last name. */
	private TextField lastName;
	
	/** The first name. */
	private TextField firstName;
	
	/** The address. */
	private TextField address;
	
	/** The nationality. */
	private ComboBox<String> nationality;
	
	/** The city. */
	private TextField city;
	
	/** The language. */
	private ComboBox<String> language;
	
	/** The phonenumber. */
	private TextField phonenumber;
	
	/** The email. */
	private TextField email;
	
	/** The insurance. */
	private TextField insurance;
	
	/** The ahv nr. */
	private TextField ahvNr;
	
	/** The birthdate. */
	private DatePicker birthdate;
	
	/** The presenter. */
	private PatientPresenter presenter;
	
	/** The gender. */
	private ComboBox<String> gender;
	
	/** The binder. */
	private Binder<PatientEntity> binder;
	
	/** The info label. */
	private Label infoLabel;
	
	/** The hint label. */
	private Label hintLabel;
	

	/* (non-Javadoc)
	 * @see com.vaadin.flow.router.internal.BeforeEnterHandler#beforeEnter(com.vaadin.flow.router.BeforeEnterEvent)
	 */
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		if (UserService.getUser() == null) {
			event.rerouteTo("");
		}
	}

	/**
	 * Cosntructor for the new patient site..
	 */

	public NewPatientView() {
		this.presenter = new PatientPresenter();

		VerticalLayout vlMenu = this.createMenu();
		VerticalLayout vlBody = this.patientData();

		this.add(vlMenu, vlBody);
		
	}

	/**
	 * Vertical layout with label and textfield for patientdata.
	 *
	 * @return the vertical layout
	 */
	public VerticalLayout patientData() {

		FormLayout newPatientLayout = new FormLayout();

		// The fields for the Form
		this.lastName = new TextField();
		lastName.setValueChangeMode(ValueChangeMode.EAGER);
		lastName.setAutofocus(true);
		this.firstName = new TextField();
		firstName.setValueChangeMode(ValueChangeMode.EAGER);
		this.birthdate = new DatePicker();
		this.gender = new ComboBox<String>();
		this.address = new TextField();
		address.setValueChangeMode(ValueChangeMode.EAGER);
		this.city = new TextField();
		city.setValueChangeMode(ValueChangeMode.EAGER);
		this.nationality = new ComboBox<String>();
		this.language = new ComboBox<String>();
		this.phonenumber = new TextField();
		phonenumber.setPlaceholder("0041 xx xxx xx xx");
		phonenumber.setValueChangeMode(ValueChangeMode.EAGER);
		this.email = new TextField();
		email.setValueChangeMode(ValueChangeMode.EAGER);
		this.insurance = new TextField();
		insurance.setValueChangeMode(ValueChangeMode.EAGER);
		this.ahvNr = new TextField();
		ahvNr.setPlaceholder("756.xxxx.xxxx.xx");
		ahvNr.setValueChangeMode(ValueChangeMode.EAGER);
		this.infoLabel = new Label();
		this.hintLabel = new Label("*Pflichtfelder");
		
		
		

		// Combobox for gender, nationality, language
		gender.setItems("weiblich", "männlich");
		gender.setPlaceholder("Geschlecht auswählen");
		
		nationality.setItems(CountriesService.getAllCountries());
		language.setItems(CountriesService.getAllLanguges());

		// add the fields to the form
		newPatientLayout.addFormItem(this.lastName, "Nachname*");
		newPatientLayout.addFormItem(this.firstName, "Vorname*");
		newPatientLayout.addFormItem(this.birthdate, "Geburtsdatum*");
		newPatientLayout.addFormItem(this.gender, "Geschlecht*");
		newPatientLayout.addFormItem(this.address, "Adresse");
		newPatientLayout.addFormItem(this.city, "Wohnort");
		newPatientLayout.addFormItem(this.nationality, "Nationalität*");
		newPatientLayout.addFormItem(this.language, "Sprache*");
		newPatientLayout.addFormItem(this.phonenumber, "Telefonnummer*");
		newPatientLayout.addFormItem(this.email, "Email*");
		newPatientLayout.addFormItem(this.insurance, "Krankenkasse");
		newPatientLayout.addFormItem(this.ahvNr, "AHV-Nr.*");
		

		
		//binder for the formfields
		this.binder = new Binder<>();
		binder.forField(email).withValidator(new EmailValidator("ungültige E-Mail-Adresse!"))
				.bind(PatientEntity::getEmail, PatientEntity::setEmail);

		binder.forField(firstName).asRequired("Es wurde kein Vorname eingegeben!").bind(PatientEntity::getFirstName,
				PatientEntity::setFirstName);

		binder.forField(lastName).withValidator(new StringLengthValidator("Es wurde kein Nachname eingegeben!", 1, null))
				.bind(PatientEntity::getLastName, PatientEntity::setLastName);

		binder.forField(birthdate)
				.withValidator(birthdate -> birthdate.isBefore(LocalDate.now()), "Ungültiges Geburtsdatum!")
				.bind(PatientEntity::getBirthdate, PatientEntity::setBirthdate);

		binder.forField(phonenumber)
				.withValidator(new RegexpValidator("ungültige Telefonnummer!",
						"(\\d{4})\\s?(\\d{2})\\s?(\\d{3})\\s?(\\d{2})\\s?(\\d{2})"))
				.asRequired("Bitte geben Sie eine Telefonnummer ein!")
				.bind(PatientEntity::getPhoneNumber, PatientEntity::setPhoneNumber);

		binder.forField(ahvNr)
				.withValidator(new RegexpValidator("ungültige AHV Nummer!",
						"((\\b756)\\.(\\d{4})\\.(\\d{4})\\.(\\d{2}))"))
				.bind(PatientEntity::getAhvNr, PatientEntity::setAhvNr);

		binder.forField(gender).asRequired("Kein Geschlecht ausgewählt!").bind(PatientEntity::getGender,
				PatientEntity::setGender);
		
		binder.forField(nationality).asRequired("Keine Nationalität ausgewählt!").bind(PatientEntity::getNationality,PatientEntity::setNationality);
		
		binder.forField(language).asRequired("Keine Nationalität ausgewählt!").bind(PatientEntity::getLanguage,PatientEntity::setLanguage);

		lastName.setRequiredIndicatorVisible(true);
		firstName.setRequiredIndicatorVisible(true);
		birthdate.setRequiredIndicatorVisible(true);
		phonenumber.setRequiredIndicatorVisible(true);
		ahvNr.setRequiredIndicatorVisible(true);
		gender.setRequiredIndicatorVisible(true);

		//Title label
		Label info = new Label("Neuer Patient erfassen:");
		info.getStyle().set("font-size", "200%");

		VerticalLayout vlBody = new VerticalLayout();
		vlBody.getStyle().set("magrin-top", "50px");
		vlBody.add(info, hintLabel, newPatientLayout, this.createButtons(), infoLabel);

		return vlBody;
	}

	/**
	 * Horizontal layout with 2 buttons to save or cancel the session.
	 *
	 * @return the horizontal layout
	 */

	public HorizontalLayout createButtons() {

		HorizontalLayout layoutButtons = new HorizontalLayout();
		Button save = new Button("Speichern");
		save.addClickListener(e -> {
			PatientEntity patient = new PatientEntity();
			if (binder.writeBeanIfValid(patient)) {

				patient.setFirstName(this.firstName.getValue());
				patient.setLastName(this.lastName.getValue());
				patient.setBirthdate(this.birthdate.getValue());
				patient.setAddress(this.address.getValue());
				patient.setCity(this.city.getValue());
				patient.setEmail(this.email.getValue());
				patient.setLanguage(this.language.getValue());
				patient.setNationality(this.nationality.getValue());
				patient.setPhoneNumber(this.phonenumber.getValue());
				patient.setAhvNr(this.ahvNr.getValue());
				patient.setInsurance(this.insurance.getValue());
				patient.setGender(this.gender.getValue());
				this.presenter.saveButtonClicked(e, patient);
				

			} else {
				BinderValidationStatus<PatientEntity> validate = binder.validate();
				String errorText = validate.getFieldValidationStatuses().stream()
						.filter(BindingValidationStatus::isError).map(BindingValidationStatus::getMessage)
						.map(Optional::get).distinct().collect(Collectors.joining(", "));
				infoLabel.setText("Es ist ein Fehler aufgetreten: " + errorText);
			}

		});

		Button cancel = new Button("Abbrechen");
		cancel.addClickListener(e -> {
			cancel.getUI().ifPresent(ui -> ui.navigate("Home"));
		});
		layoutButtons.add(cancel, save);
		return layoutButtons;
	}

	/**
	 * Creates the menu.
	 *
	 * @return the vertical layout
	 */
	public VerticalLayout createMenu() {
		VerticalLayout vlMenu = new VerticalLayout();
		vlMenu.setWidth("250px");
		vlMenu.add(this.createMenuButton("Home", new Icon(VaadinIcon.HOME)));
		vlMenu.add(this.createMenuButton("Kalender", new Icon(VaadinIcon.CALENDAR)));
		vlMenu.add(this.createMenuButton("Patient suchen", new Icon(VaadinIcon.USERS)));
		vlMenu.add(this.createMenuButton("Logout", new Icon(VaadinIcon.POWER_OFF)));
		return vlMenu;
	}

	/**
	 * Creates the menu button.
	 *
	 * @param value the value
	 * @param icon the icon
	 * @return the button
	 */
	private Button createMenuButton(String value, Icon icon) {
		Button newButton = new Button(value, icon);
		newButton.addClickListener(e -> {
			this.presenter.menuButtonClicked(e);
		});
		newButton.setWidth("200px");
		return newButton;
	}

}