package patient;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.router.Route;

/**
 * View for the new patient site.  
 * 
 *
 * @author Yannick Gehri
 *
 */

@Route("New Patient")
public class NewPatient extends VerticalLayout {
	
	HorizontalLayout layout = new HorizontalLayout();
	
	/**
	 * Cosntructor for the new session site. 
	 */
	
	public NewPatient() {
//		session();
		patientData();
//		action();
		this.add(layout);
	}

/**
 * Vertical layout with label and textfield for patientdata. 
 */
	public void patientData() {
//		VerticalLayout layoutPatientData = new VerticalLayout();
//		Label lbNewPatient = new Label("Neuer Patient erstellen: ");
//		TextField tfLastName = new TextField("Nachname: ");
//		
//		layoutPatientData.add(lbNewPatient);
//		layoutPatientData.add(tfLastName);
//		layoutPatientData.setAlignItems(Alignment.START);
//		this.layout.add(layoutPatientData);
		
		
		FormLayout newPatientLayout = new FormLayout();
		Binder<Patient> binder = new Binder<>();
		
		// The object that will be edited
		Patient patientCreate = new Patient();
		
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
		NativeButton save = new NativeButton("Speichern");
		NativeButton cancel = new NativeButton("Abbrechen");
		
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
		
		// Button bar
		HorizontalLayout actions = new HorizontalLayout();
		actions.add(save,cancel);
		save.getStyle().set("marginRight", "10px");
		
		// Birthdate
		//binder.bind(birthDate, Patient::getBirthDate, Patient::setBirthDate);
		
		
		this.layout.add(newPatientLayout);
		
	}
}

