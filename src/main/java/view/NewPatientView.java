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

import model.PatientModel;
import model.Person;

/**
 * View for the new patient site.  
 * 
 *
 * @author Yannick Gehri
 *
 */

@Route("Neuer Patient")
public class NewPatientView extends VerticalLayout {
	
	HorizontalLayout layout = new HorizontalLayout();
	
	/**
	 * Cosntructor for the new patient site.. 
	 */
	
	public NewPatientView() {
		patientData();
		action();
		HorizontalLayout actions = new HorizontalLayout();
		

		
		this.add(this.layout);
	}

/**
 * Vertical layout with label and textfield for patientdata. 
 */
	public void patientData() {
		
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
		

		
		// Button bar
//		HorizontalLayout actions = new HorizontalLayout();
//		actions.add(save,cancel);
//		save.getStyle().set("marginRight", "10px");
		
		// Birthdate
		//binder.bind(birthDate, Patient::getBirthDate, Patient::setBirthDate);
		
		
//		binder.forField(lastName).bind(PatientModel::getLastName,PatientModel::setLastName);
//		
//		PatientModel patient = new PatientModel();
//		
//		binder.readBean(patient);
//		binder.bind(lastName,
//				patient -> )
		
		
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
			
			save.getUI().ifPresent(ui -> ui.navigate("Home")); // put in right route
		});
		Button cancel = new Button("Abbrechen");
		cancel.addClickListener(e -> {
			cancel.getUI().ifPresent(ui -> ui.navigate("Home")); // put in right route
		});
		layoutButtons.add(cancel, save);

		layoutAction.add(layoutButtons);
		layoutAction.setAlignItems(Alignment.START);
		this.layout.add(layoutAction);
		this.layout.setAlignSelf(Alignment.END, layoutAction);
		
	}
}

