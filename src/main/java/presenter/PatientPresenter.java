package presenter;

import java.util.Collection;
import java.util.List;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

import entity.PatientEntity;
import exception.InvalidEmailException;
import exception.InvalidUsernameException;
import model.PatientModel;


public class PatientPresenter {

	private PatientModel model;
	
	public PatientPresenter() {		
		this.model = new PatientModel();
	}
	
	public void editSaveButtonClicked(PatientEntity patient) {
		this.model.setPatient(patient);
	}
	
	public void saveButtonClicked(ClickEvent<Button> e, PatientEntity patient) {
		this.model.setPatient(patient);		
		e.getSource().getUI().ifPresent(ui -> ui.navigate("Patient"));
	}	
	
	public void cancelButtonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate(""));
	}
	
	public Collection<PatientEntity> getPatientData() {
		return model.getPatient();
	}
}
                