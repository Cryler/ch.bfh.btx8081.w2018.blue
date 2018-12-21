
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
import exception.InvalidPasswordException;
import exception.InvalidUsernameException;
import presenter.RegistrationPresenter;


@Route("Registration")
public class RegistrationView extends VerticalLayout {

	private TextField username;
	private TextField email;
	private PasswordField password;
	private PasswordField passwortRepeat;
	private RegistrationPresenter presenter;
	private Label info;


	public RegistrationView() {
		this.presenter = new RegistrationPresenter();
		this.setAlignItems(Alignment.CENTER);
		this.addVisualComponents();

	}

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

	private TextField createTextfield(String fieldName) {
		TextField newField = new TextField();
		newField.setLabel(fieldName);
		return newField;
	}

	private PasswordField createPasswordField(String fieldName) {
		PasswordField newField = new PasswordField();
		newField.setLabel(fieldName);
		return newField;
	}

	private Button createSaveButton() {
		Button saveButton = new Button("Registrieren");
		
		saveButton.addClickListener( e -> {
			// Todo if Benutzername oder Email existiert
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

	private Button createCancelButton() {
		Button cancelButton = new Button("Abbrechen");
		cancelButton.addClickListener(e -> {
			this.presenter.cancelButtonClicked(e);
		});
		return cancelButton;
	}

}
