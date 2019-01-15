
/**
 *date: 21.12.2018   -  time: 10:47:47
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import entity.UserEntity;
import exception.InvalidEmailException;
import exception.InvalidUsernameException;
import presenter.RegistrationPresenter;

/**
 * The Class RegistrationView.
 * 
 * @author gundy1.
 */
@Route("Registration")
public class RegistrationView extends VerticalLayout {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Label info;

	private TextField username;

	private TextField email;

	private PasswordField password;

	private PasswordField passwortRepeat;

	/** The presenter that handles all inputs from the user in the view. */
	private RegistrationPresenter presenter;

	/**
	 * Instantiates a new registration view.
	 */
	public RegistrationView() {
		this.presenter = new RegistrationPresenter();
		this.setAlignItems(Alignment.CENTER);
		this.addVisualComponents();

	}

	/**
	 * Adds the visual components.
	 */
	private void addVisualComponents() {
		this.username = this.createTextfield("Benutzername");
		this.add(this.username);
		this.email = this.createTextfield("E-Mail");
		this.add(this.email);
		this.password = this.createPasswordField("Passwort");
		this.add(this.password);
		this.passwortRepeat = this.createPasswordField("Passwort wiederholen");
		this.add(this.passwortRepeat);

		HorizontalLayout hl1 = new HorizontalLayout();
		hl1.add(this.createCancelButton());
		hl1.add(this.createSaveButton());
		this.add(hl1);

		this.info = new Label("");
		this.add(info);

	}

	/**
	 * Creates the textfield.
	 *
	 * @param fieldName the field name
	 * @return the text field
	 */
	private TextField createTextfield(String fieldName) {
		TextField newField = new TextField();
		newField.setLabel(fieldName);
		return newField;
	}

	/**
	 * Creates the password field.
	 *
	 * @param fieldName the field name
	 * @return the password field
	 */
	private PasswordField createPasswordField(String fieldName) {
		PasswordField newField = new PasswordField();
		newField.setLabel(fieldName);
		return newField;
	}

	/**
	 * Creates the save button. This Method already checks if the password is equals
	 * to the repeated password.
	 *
	 * @return the button
	 */
	private Button createSaveButton() {
		Button saveButton = new Button("Registrieren");

		saveButton.addClickListener(e -> {
			if (this.password.getValue().equals(this.passwortRepeat.getValue()) == false) {
				this.info.setText("Passwort wurde nicht gleich geschrieben!");
				this.password.setValue("");
				this.passwortRepeat.setValue("");
			} else {
				try {
					UserEntity user = new UserEntity();
					user.setUsername(this.username.getValue());
					user.setEmail(this.email.getValue());
					user.setPassword(this.password.getValue());
					this.presenter.saveButtonClicked(e, user);
				} catch (InvalidUsernameException | InvalidEmailException e1) {
					this.info.setText(e1.getMessage());
				}
			}
		});

		return saveButton;
	}

	/**
	 * Creates the cancel button.
	 *
	 * @return the button
	 */
	private Button createCancelButton() {
		Button cancelButton = new Button("Abbrechen");
		cancelButton.addClickListener(e -> {
			this.presenter.cancelButtonClicked(e);
		});
		return cancelButton;
	}

}
