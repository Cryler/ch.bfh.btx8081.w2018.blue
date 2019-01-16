
/**
 *date: 20.11.2018   -  time: 15:18:02
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.ui.LoadMode;

import presenter.InstitutionPresenterAdmin;
import service.UserService;

/**
 * this Class represents the Homescreen of our Application. The
 * {@code InstitutionView} connects all parts of the UI with Navigaton Buttons.
 * 
 * @author gundy1
 *
 */

@Route("Home")
@StyleSheet(value = "styles/style.css", loadMode = LoadMode.INLINE)
public class InstitutionView extends VerticalLayout implements BeforeEnterObserver {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	private InstitutionPresenterAdmin presentorAdmin;
	
	private TextArea infoArea;

	/* (non-Javadoc)
	 * @see com.vaadin.flow.router.internal.BeforeEnterHandler#beforeEnter(com.vaadin.flow.router.BeforeEnterEvent)
	 */
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		if (UserService.getUser() == null) {
			event.rerouteTo("");
		}
	}

	/**
	 * Instantiates a new institution view. First all graphical components are created, then all Data is initialized.
	 */
	public InstitutionView() {
		
		this.initView();
		this.addComponents();
		this.updateView();
	}

	/**
	 * Inits the view.
	 */
	private void initView() {
		this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		this.getStyle().set("margin-top", "100px");
		this.presentorAdmin = new InstitutionPresenterAdmin();
	}

	/**
	 * Adds the graphical components.
	 */
	private void addComponents() {
		VerticalLayout vl1 = new VerticalLayout();
		vl1.setWidth("230px");
		vl1.getStyle().set("background-color",	"rgb(245, 245, 245)");
		vl1.getStyle().set("border-radius", "10px");		
		
		this.infoArea = new TextArea();
		this.infoArea = new TextArea();
		this.infoArea.setWidth("200px");
		this.infoArea.setEnabled(false);

		Button calendarButton = this.createButton("Kalender", new Icon(VaadinIcon.CALENDAR));
		Button newPatientButton = this.createButton("Neuer Patient", new Icon(VaadinIcon.USER_CHECK));
		Button searchPatientButton = this.createButton("Patient suchen", new Icon(VaadinIcon.USERS));
		Button settingsButton = this.createButton("Settings", new Icon(VaadinIcon.COG));
		Button logoutButton = this.createButton("Logout", new Icon(VaadinIcon.POWER_OFF));

		vl1.add(this.infoArea, calendarButton, newPatientButton, searchPatientButton, settingsButton, logoutButton);
		this.add(vl1);
	}

	/**
	 * Updates the view with the current Data of the institution.
	 */
	private void updateView() {
		if (UserService.getUser() != null) {
			this.infoArea.setValue(
					this.presentorAdmin.getInstitutionName() + "\nBenutzer: " + UserService.getUser().getUsername());
		}
	}

	/**
	 * Creates the button.
	 *
	 * @param value the value
	 * @param icon the icon
	 * @return the button
	 */
	private Button createButton(String value, Icon icon) {
		Button newButton = new Button(value, icon);
		newButton.addClickListener(e -> {
			this.presentorAdmin.buttonClicked(e);
		});
		newButton.setWidth("200px");
		return newButton;
	}

}
