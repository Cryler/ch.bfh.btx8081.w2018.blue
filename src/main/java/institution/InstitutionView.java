
/**
 *date: 20.11.2018   -  time: 15:18:02
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package institution;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.internal.BeforeEnterHandler;

@Route("Home")
public class InstitutionView extends VerticalLayout {

	private InstitutionPresenterAdmin presentorAdmin;
	private Label zuluLabel;
	private Label institutionName;
	private Label institutionStreet;
	private Label institutionCity;

	public InstitutionView() {

		// Layoutsetting for Whole Screen.
		HorizontalLayout topSection = new HorizontalLayout();
		VerticalLayout addressSection = new VerticalLayout();
		HorizontalLayout institutionNameSection = new HorizontalLayout();

		// Creating the Labels for the Top Section with Institution Name and Address.
		this.zuluLabel = new Label("ZULU: ");

		this.institutionName = new Label();
		this.institutionStreet = new Label();
		this.institutionCity = new Label();
		Button settingsButton = new Button("Settings", new Icon(VaadinIcon.COG_O));
		settingsButton.addClickListener(e -> {
			
			settingsButton.getUI().ifPresent(ui -> ui.navigate("Settings"));
		});
		settingsButton.setWidth("200px");

		// Adding all Components to the Layouts.
		institutionNameSection.add(this.zuluLabel, this.institutionName);
		addressSection.add(institutionNameSection, this.institutionStreet, this.institutionCity);
		topSection.add(addressSection, settingsButton);

		// Creating all Buttons for the lower part of the Screen.
		Button calendarButton = new Button("Calendar");
		Button newPatientButton = new Button("Neuer Patient");
		Button searchPatientButton = new Button("Patient suchen");

		this.add(topSection, calendarButton, newPatientButton, searchPatientButton);
		this.init();

	}

	public void init() {
		this.presentorAdmin = new InstitutionPresenterAdmin(this);
		this.institutionName.setText(this.presentorAdmin.getInstitutionName());
		this.institutionStreet.setText(this.presentorAdmin.getInstitutionAddress().getStreet() + " "
				+ this.presentorAdmin.getInstitutionAddress().getStreetNr());
		this.institutionCity.setText(this.presentorAdmin.getInstitutionAddress().getZipCode() + " "
				+ this.presentorAdmin.getInstitutionAddress().getCity());
	}

}
