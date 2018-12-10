
/**
 *date: 20.11.2018   -  time: 15:18:02
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package view;

import java.util.Observable;
import java.util.Observer;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.ui.LoadMode;

import presenter.InstitutionPresenterAdmin;

@Route("Home")
@StyleSheet(value = "styles/style.css", loadMode = LoadMode.INLINE)
public class InstitutionView extends VerticalLayout implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InstitutionPresenterAdmin presentorAdmin;
	private TextArea addressField;

	public InstitutionView() {
		this.initView();
		this.addComponents();
		this.updateView();

	}

	private void initView() {
		this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		this.presentorAdmin = new InstitutionPresenterAdmin();
	}

	private void addComponents() {
		this.addressField = new TextArea();
		this.addressField = new TextArea();
		this.addressField.setWidth("200px");
		this.addressField.setEnabled(false);

		Button calendarButton = this.createButton("Kalender", new Icon(VaadinIcon.CALENDAR));
		Button newPatientButton = this.createButton("Neuer Patient", new Icon(VaadinIcon.USER_CHECK));
		Button searchPatientButton = this.createButton("Patient suchen", new Icon(VaadinIcon.USERS));
		Button settingsButton = this.createButton("Settings", new Icon(VaadinIcon.COG_O));

		this.add(this.addressField, calendarButton, newPatientButton, searchPatientButton, settingsButton);

	}

	private void updateView() {
		this.addressField.setValue(this.presentorAdmin.getInstitutionName() + "\n"
				+ this.presentorAdmin.getInstitutionAddress().getStreet() + " "
				+ this.presentorAdmin.getInstitutionAddress().getStreetNr() + "\n"
				+ this.presentorAdmin.getInstitutionAddress().getZipCode() + " "
				+ this.presentorAdmin.getInstitutionAddress().getCity());
	}

	private Button createButton(String value, Icon icon) {
		Button newButton = new Button(value, icon);
		newButton.addClickListener(e -> {
			this.presentorAdmin.buttonClicked(e);
		});
		newButton.setWidth("200px");
		return newButton;
	}

	@Override
	public void update(Observable o, Object arg) {
		this.updateView();
	}

}
