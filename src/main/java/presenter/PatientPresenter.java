package presenter;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

import entity.PatientEntity;
import entity.UserEntity;
import exception.InvalidEmailException;
import exception.InvalidPatientException;
import exception.InvalidUsernameException;
import model.PatientModel;
import model.UserModel;

public class PatientPresenter {
private PatientModel model;
	
	public PatientPresenter() {
		
		this.model = new PatientModel();
	}
	
	
	public void saveButtonClicked(ClickEvent<Button> e, PatientEntity patient) throws InvalidUsernameException, InvalidEmailException{
		this.model.setPatient(patient);		
		e.getSource().getUI().ifPresent(ui -> ui.navigate("Home"));
	}
	
	
	
	public void cancelButtonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate(""));
	}
	

}
                