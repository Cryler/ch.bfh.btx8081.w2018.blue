package ch.bfh.btx8081.w2018.blue.zulu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import service.EMService;
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
		
		
		VerticalLayout vl1 = new VerticalLayout();
		vl1.setAlignItems(Alignment.CENTER);
		vl1.setWidth("300px");
		
		Button loginButton = new Button("Login");
		
		loginButton.addClickListener(e -> {
			loginButton.getUI().ifPresent(ui -> ui.navigate("Home"));
		});

		TextField username = new TextField();
		username.setLabel("Benutzername");
		
		
		PasswordField password = new PasswordField();
		password.setLabel("Passwort");
		
		
		vl1.add(username, password, loginButton);
		this.add(vl1);
	}
}
