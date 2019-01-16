package ch.bfh.btx8081.w2018.blue.zulu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import exception.InvalidPasswordException;
import exception.InvalidUsernameException;
import presenter.LoginPresenter;


/**
 * Login and Root-Screen for the Application Zulu.
 *
 * @author gundy1
 */

@Route("")
public class Login extends VerticalLayout {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The presenter that handles the user inputs of this view. */
	private LoginPresenter presenter;
	
	
	/** The UI Components that let the user fill in his autorification data.*/
	private TextField username;	
	private PasswordField password;	
	private Label info;

	/**
	 * Instantiates a new Presenter for this view.
	 * Instantiates a new login UI.
	 */
	public Login() {
		this.presenter = new LoginPresenter();
		this.initUI();
	}

	/**
	 * Instantiates the UI with all graphic components.
	 */
	private void initUI() {
		
		this.setAlignItems(Alignment.CENTER);
		this.setSizeFull();
		
		
		
		VerticalLayout vl1 = new VerticalLayout();
		vl1.setAlignItems(Alignment.CENTER);
		vl1.getStyle().set("margin-top", "100px");
		vl1.setWidth("350px");
		vl1.getStyle().set("background-color",	"rgb(245, 245, 245)");
		vl1.getStyle().set("border-radius", "10px");

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
