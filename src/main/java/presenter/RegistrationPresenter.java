
/**
 *date: 21.12.2018   -  time: 13:01:17
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package presenter;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

import entity.UserEntity;
import exception.InvalidEmailException;
import exception.InvalidUsernameException;
import model.UserModel;

public class RegistrationPresenter {

	
	private UserModel model;
	
	public RegistrationPresenter() {
		
		this.model = new UserModel();
	}
	
	
	public void saveButtonClicked(ClickEvent<Button> e, UserEntity user) throws InvalidUsernameException, InvalidEmailException{
		this.model.setUser(user);		
		e.getSource().getUI().ifPresent(ui -> ui.navigate("Home"));
	}
	
	
	
	public void cancelButtonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate(""));
	}
}
