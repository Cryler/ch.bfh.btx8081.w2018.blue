package ch.bfh.btx8081.w2018.blue.zulu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import exception.InvalidPasswordException;
import exception.InvalidUsernameException;
import presenter.LoginPresenter;
import service.EMService;

/**
 * Login for the Application
 * 
 * @author yanng
 *
 */

@Route("")

public class Login extends VerticalLayout {

	private LoginPresenter presenter;
	private TextField username;
	private PasswordField password;
	private Label info;

	// TODO Authentifikationslogik muss noch implementiert werden.
	public Login() {
		this.presenter = new LoginPresenter();
		this.initUI();

	}

	private void initUI() {
		this.setAlignItems(Alignment.CENTER);
		VerticalLayout vl1 = new VerticalLayout();
		vl1.setAlignItems(Alignment.CENTER);
		vl1.setWidth("350px");

		this.username = new TextField();
		this.username.setLabel("Benutzername");

		this.password = new PasswordField();
		this.password.setLabel("Passwort");

		Button loginButton = new Button("Login");
		loginButton.addClickListener(e -> {
			if (this.username.getValue().equals("") || this.password.getValue().equals("")) {
				this.info.setText("Angaben sind unvollständig");
			} else {
				
					try {
						this.presenter.loginButtonClicked(e, this.username.getValue(), this.password.getValue());
					} catch (InvalidPasswordException | InvalidUsernameException e1) {
						this.info.setText(e1.getMessage());
						this.username.setValue("");
						this.password.setValue("");
						System.out.println(e1.getClass().getName());
					}
			}
		});

		Button registerButton = new Button("Registrieren");
		registerButton.addClickListener(e -> {
			this.presenter.registrationButtonClicked(e);
			;
		});

		HorizontalLayout hl1 = new HorizontalLayout();
		hl1.add(registerButton, loginButton);

		this.info = new Label("");

		vl1.add(this.username, this.password, hl1, this.info);
		this.add(vl1);
	}
}
