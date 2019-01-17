package presenter;

import java.util.Collection;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;

import entity.PatientEntity;
import entity.SessionEntity;
import model.PatientModel;
import model.SessionModel;


/**
 * The Class PatientPresenter.
 * 
 * @author gehry1
 * 
 */
public class PatientPresenter {

	/** The model. */
	private PatientModel model;
	
	/** The notification. */
	private Notification notification;
	
	/**
	 * Instantiates a new patient presenter.
	 */
	public PatientPresenter() {		
		this.model = new PatientModel();
	}
	
	/**
	 * Navigates to the View based on the Button that triggered the Clickevent.
	 *
	 * @param e the ClickEvent
	 */
	public void menuButtonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate(e.getSource().getText()));
	}
	
	/**
	 * Edits the save button clicked.
	 * 
	 *
	 * @param patient the patient
	 */
	public void editSaveButtonClicked(PatientEntity patient) {
		this.model.setPatient(patient);
	}
	
	/**
	 * Delete button clicked.
	 *
	 * @param patient the patient
	 */
	@SuppressWarnings("static-access")
	public void deleteButtonClicked(PatientEntity patient) {
		this.model.deletePatient(patient);
		this.notification = new Notification("Patient wurde gelöscht", 10000);
		notification.show("Patient wurde gelöscht");
		
	}
	
	/**
	 * Save button clicked. Passes the Data of the new PatientView to the model, that will store it into the db.
	 *
	 * @param e the ClickEvent
	 * @param patient the patientEntity
	 */
	@SuppressWarnings("static-access")
	public void saveButtonClicked(ClickEvent<Button> e, PatientEntity patient) {
		this.model.setPatient(patient);	
		this.notification = new Notification("Patient wurde gespeichert", 10000);
		notification.show("Patient wurde gespeichert");
		e.getSource().getUI().ifPresent(ui -> ui.navigate("Patient"));
	}	
	
	/**
	 * Cancel button clicked.
	 *
	 * @param e the ClickEvent
	 */
	public void cancelButtonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate(""));
	}
	
	/**
	 * Gets the patient data.
	 *
	 * @return the patient data
	 */
	public Collection<PatientEntity> getPatientData() {
		return model.getPatient();
	}
	
	/**
	 * Gets the sessions of patient.
	 *
	 * @param patient the patient
	 * @return the sessions of patient
	 */
	public Collection<SessionEntity> getSessionsOfPatient(PatientEntity patient){
		return new SessionModel().getAllSessionsFromPatient(patient);
	}
}
                