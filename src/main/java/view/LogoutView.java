
/**
 *date: 27.12.2018   -  time: 12:32:13
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import service.UserService;

/**
 * The Class LogoutView.
 * 
 * @author gundy1.
 */
@Route("Logout")
public class LogoutView extends VerticalLayout {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new logout view.
	 */
	public LogoutView() {
		UserService.logout();
		this.setAlignItems(Alignment.CENTER);
		Label info = new Label("Sie wurden erfolgreich abgemeldet.");
		info.getStyle().set("font-size", "200%");
		info.getStyle().set("padding-top", "150px");
	
		Button bt1 = new Button("Zum Login");
		bt1.getStyle().set("margin-top", "50px");
		bt1.addClickListener(e -> {
			bt1.getUI().ifPresent(ui -> ui.navigate(""));
		});
		this.add(info,bt1);

	}
}
