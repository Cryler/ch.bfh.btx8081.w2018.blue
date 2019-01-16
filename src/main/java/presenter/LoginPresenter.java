
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


/**
 * The Class LoginPresenter.
 * @author gundy1
 */
public class LoginPresenter {

	
	/**
	 * Login button clicked. Passes the arguments to the Model that handles the login request. 
	 * If either the password or the username is incorrect the model will throw an exception, that will be passed to the view.
	 *
	 * @param e the ClickEvent
	 * @param username the username
	 * @param password the password
	 * @throws InvalidPasswordException the invalid password exception
	 * @throws InvalidUsernameException the invalid username exception
	 */
	public void loginButtonClicked(ClickEvent<Button> e, String username, String password) throws InvalidPasswordException, InvalidUsernameException{
		new UserModel().loginUser(username, password);
		e.getSource().getUI().ifPresent(ui -> ui.navigate("Home"));		
	}
	
	/**
	 * Registration button clicked. Navigates to the View for a new registration.
	 *
	 * @param e the ClickEvent
	 */
	public void registrationButtonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate("Registration"));
	}
}
