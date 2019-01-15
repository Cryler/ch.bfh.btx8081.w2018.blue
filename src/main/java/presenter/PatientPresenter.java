package presenter;

import java.util.Collection;
import java.util.List;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import entity.PatientEntity;
import entity.SessionEntity;
import exception.InvalidEmailException;
import exception.InvalidUsernameException;
import model.PatientModel;
import model.SessionModel;


public class PatientPresenter {

	private PatientModel model;
	private Notification notification;
	
	public PatientPresenter() {		
		this.model = new PatientModel();
	}
	
	public void menuButtonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate(e.getSource().getText()));
	}
	
	public void editSaveButtonClicked(PatientEntity patient) {
		this.model.setPatient(patient);
	}
	
	public void deleteButtonClicked(PatientEntity patient) {
		this.model.deletePatient(patient);
		this.notification = new Notification("Patient wurde gelöscht", 10000);
		notification.show("Patient wurde gelöscht");
		
	}
	
	public void saveButtonClicked(ClickEvent<Button> e, PatientEntity patient) {
		this.model.setPatient(patient);	
		this.notification = new Notification("Patient wurde gespeichert", 10000);
		notification.show("Patient wurde gespeichert");
		e.getSource().getUI().ifPresent(ui -> ui.navigate("Patient"));
	}	
	
	public void cancelButtonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate(""));
	}
	
	public Collection<PatientEntity> getPatientData() {
		return model.getPatient();
	}
	
	public Collection<SessionEntity> getSessionsOfPatient(PatientEntity patient){
		return new SessionModel().getAllSessionsFromPatient(patient);
	}
}
                