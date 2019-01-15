
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

/**
 * The Class RegistrationPresenter.
 * 
 * @author gundy1.
 */
public class RegistrationPresenter {

	private UserModel model;

	public RegistrationPresenter() {
		this.model = new UserModel();
	}

	/**
	 * Save button clicked. Passes the Information of the new User to the model. If
	 * either the username or the email already exists in the db an Exception will
	 * be thrown. This Exception will be passed to the view where it will be handled.
	 *
	 * @param e    the Clickevent
	 * @param user the new user
	 * @throws InvalidUsernameException the invalid username exception
	 * @throws InvalidEmailException    the invalid email exception
	 */
	public void saveButtonClicked(ClickEvent<Button> e, UserEntity user)
			throws InvalidUsernameException, InvalidEmailException {
		this.model.setUser(user);
		e.getSource().getUI().ifPresent(ui -> ui.navigate("Home"));
	}

	/**
	 * Cancel button clicked. Navigates back to the root view
	 *
	 * @param e the Clickevent.
	 */
	public void cancelButtonClicked(ClickEvent<Button> e) {
		e.getSource().getUI().ifPresent(ui -> ui.navigate(""));
	}
}
