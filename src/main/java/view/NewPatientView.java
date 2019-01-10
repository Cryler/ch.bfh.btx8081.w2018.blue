package view;

import java.time.LocalDate;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import entity.PatientEntity;
import entity.PersonEntity;
import presenter.PatientPresenter;

/**
 * View for the new patient site.
 * 
 *
 * @author Yannick Gehri
 *
 */

@Route("Neuer Patient")
public class NewPatientView extends VerticalLayout {

	private TextField lastName;
	private TextField firstName;
	private TextField address;
	private TextField nationality;
	private TextField city;
	private TextField language;
	private TextField phonenumber;
	private TextField email;
	private TextField insurance;
	private TextField ahvNr;
	private DatePicker birthdate;
	private PatientPresenter presenter;
	private ComboBox<String> gender;
	

	HorizontalLayout layout = new HorizontalLayout();

	/**
	 * Cosntructor for the new patient site..
	 */

	public NewPatientView() {
		this.presenter = new PatientPresenter();
		patientData();

		action();

		this.add(this.layout);
	}

	/**
	 * Vertical layout with label and textfield for patientdata.
	 */
	public void patientData() {

		FormLayout newPatientLayout = new FormLayout();

		// The fields for the Form

		this.lastName = new TextField();
		lastName.setValueChangeMode(ValueChangeMode.EAGER);
		this.firstName = new TextField();
		firstName.setValueChangeMode(ValueChangeMode.EAGER);
		this.birthdate = new DatePicker();
		this.gender = new ComboBox<String>();
		this.address = new TextField();
		address.setValueChangeMode(ValueChangeMode.EAGER);
		this.city = new TextField();
		city.setValueChangeMode(ValueChangeMode.EAGER);
		this.nationality = new TextField();
		nationality.setValueChangeMode(ValueChangeMode.EAGER);
		this.language = new TextField();
		language.setValueChangeMode(ValueChangeMode.EAGER);
		this.phonenumber = new TextField();
		phonenumber.setValueChangeMode(ValueChangeMode.EAGER);
		this.email = new TextField();
		email.setValueChangeMode(ValueChangeMode.EAGER);
		this.insurance = new TextField();
		insurance.setValueChangeMode(ValueChangeMode.EAGER);
		this.ahvNr = new TextField();
		ahvNr.setValueChangeMode(ValueChangeMode.EAGER);

		// Combobox for gender
		gender.setItems("weiblich", "männlich");
		gender.setPlaceholder("Geschlecht auswählen");

		newPatientLayout.addFormItem(this.lastName, "Nachname");
		newPatientLayout.addFormItem(this.firstName, "Vorname");
		newPatientLayout.addFormItem(this.birthdate, "Geburtsdatum");
		newPatientLayout.addFormItem(this.gender, "Geschlecht");
		newPatientLayout.addFormItem(this.address, "Adresse");
		newPatientLayout.addFormItem(this.city, "Wohnort");
		newPatientLayout.addFormItem(this.nationality, "Nationalität");
		newPatientLayout.addFormItem(this.language, "Sprache");
		newPatientLayout.addFormItem(this.phonenumber, "Telefonnummer");
		newPatientLayout.addFormItem(this.email, "Email");
		newPatientLayout.addFormItem(this.insurance, "Krankenkasse");
		newPatientLayout.addFormItem(this.ahvNr, "AHV-Nr.");

		this.layout.add(newPatientLayout);
		
		Binder<PersonEntity> binder = new Binder<>();
		binder.forField(email).withValidator(new EmailValidator("Dies scheint keine email Adresse zu sein!"))
		.bind(PersonEntity::getEmail, PersonEntity::setEmail);
		
		binder.forField(firstName)
		.withValidator(
				firstname -> firstname.length() >= 3,
				"Vorname muss mehr als drei Buchstaben enthalten!").asRequired("Bitte geben Sie einen Vornamen ein!")
		.bind(PersonEntity::getFirstName, PersonEntity::setFirstName);
		
		binder.forField(lastName)
		.withValidator(
				lastname -> lastname.length() >= 3,
				"Nachname muss mehr als drei Buchstaben enthalten!")
		.bind(PersonEntity::getLastName, PersonEntity::setLastName);
		
		binder.forField(birthdate).withValidator(
				birthdate -> birthdate.isBefore(LocalDate.now()),"kann nicht aelter als heute sein!")
		.bind(PersonEntity::getBirthdate,PersonEntity::setBirthdate);
		

	}

	/**
	 * Horizontal layout with 2 buttons to save or cancel the session.
	 */

	public void action() {

		VerticalLayout layoutAction = new VerticalLayout();
		HorizontalLayout layoutButtons = new HorizontalLayout();
		Button save = new Button("Speichern");
		save.addClickListener(e -> {
			PatientEntity patient = new PatientEntity();
			patient.setFirstName(this.firstName.getValue());
			patient.setLastName(this.lastName.getValue());
			patient.setBirthdate(this.birthdate.getValue());
			patient.setCity(this.city.getValue());
			patient.setEmail(this.email.getValue());
			patient.setLanguage(this.language.getValue());
			patient.setNationality(this.nationality.getValue());
			patient.setPhoneNumber(this.phonenumber.getValue());
			patient.setAhvNr(this.ahvNr.getValue());
			patient.setInsurance(this.insurance.getValue());
			patient.setGender(this.gender.getValue());
			this.presenter.saveButtonClicked(e, patient);
			this.getUI().ifPresent(ui -> ui.navigate("Home"));
		});

		Button cancel = new Button("Abbrechen");
		cancel.addClickListener(e -> {
			cancel.getUI().ifPresent(ui -> ui.navigate("Home"));
		});
		layoutButtons.add(cancel, save);

		layoutAction.add(layoutButtons);
		layoutAction.setAlignItems(Alignment.START);
		this.layout.add(layoutAction);
		this.layout.setAlignSelf(Alignment.END, layoutAction);

	}
}
