package ch.bfh.btx8081.w2018.blue.zulu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import backend.Backend;
import presenter.InstitutionPresenter;
import presenter.InstitutionPresenterAdmin;
/**
 * Login for the Application
 * 
 * @author yanng
 *
 */


@Route("")
public class Login extends VerticalLayout {

	
	//TODO Authentifikationslogik muss noch implementiert werden.
	public Login() {

		this.setAlignItems(Alignment.CENTER);
		
		
		Button loginButton = new Button("Login");
		loginButton.addClickListener(e -> {
			new Backend();
			loginButton.getUI().ifPresent(ui -> ui.navigate("Home"));
		});

		TextField username = new TextField();
		username.setPlaceholder("Benutzername eingeben");

		PasswordField password = new PasswordField();
		password.setPlaceholder("Passwort");
		
		add(username, password, loginButton);
	}
}
