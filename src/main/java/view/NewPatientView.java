package view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import entity.PatientEntity;
import model.PatientModel;
import model.Person;
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
		ComboBox<String> gender = new ComboBox();
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

		newPatientLayout.addFormItem(lastName, "Nachname");
		newPatientLayout.addFormItem(firstName, "Vorname");
		newPatientLayout.addFormItem(birthdate, "Geburtsdatum");
		newPatientLayout.addFormItem(gender, "Geschlecht");
		newPatientLayout.addFormItem(address, "Adresse");
		newPatientLayout.addFormItem(city, "Wohnort");
		newPatientLayout.addFormItem(nationality, "Nationalität");
		newPatientLayout.addFormItem(language, "Sprache");
		newPatientLayout.addFormItem(phonenumber, "Telefonnummer");
		newPatientLayout.addFormItem(email, "Email");
		newPatientLayout.addFormItem(insurance, "Krankenkasse");
		newPatientLayout.addFormItem(ahvNr, "AHV-Nr.");

		this.layout.add(newPatientLayout);

	}

	/**
	 * Horizontal layout with 2 buttons to save or cancel the session.
	 */

	public void action() {

		VerticalLayout layoutAction = new VerticalLayout();
		HorizontalLayout layoutButtons = new HorizontalLayout();
		Button save = new Button("Speichern");
		save.addClickListener(e -> {

			try {
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
				this.presenter.saveButtonClicked(e, patient);
				

				this.getUI().ifPresent(ui -> ui.navigate("Home")); 

			} catch (Exception ex) {

			}

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
