
/**
 *date: 27.12.2018   -  time: 11:02:43
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package presenter;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;

import exception.InvalidPasswordException;
import exception.InvalidUsernameException;
import model.UserModel;

public class LoginPresenter {

	
	public void loginButtonClicked(ClickEvent<Button> e, String username, String password) throws InvalidPasswordException, InvalidUsernameException{
		UserModel model = new UserModel();
		model.loginUser(username, password);
		e.getSource().getUI().ifPresent(ui -> ui.navigate("Home"));
		
	}
	
	public void registrationButtonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate("Registration"));
	}
}
