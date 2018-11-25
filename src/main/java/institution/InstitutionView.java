
/**
 *date: 20.11.2018   -  time: 15:18:02
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package institution;

import java.util.Observable;
import java.util.Observer;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

import backend.Backend;

@Route("Home")
public class InstitutionView extends VerticalLayout implements Observer {

	private static InstitutionPresenterAdmin presentorAdmin;
	private TextArea address;
	private int i = 0;

	public InstitutionView() {
		this.initView();
		this.initPresentor();
		this.addComponents();
		this.updateView();

	}

	private void initView() {
		this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
	}

	private void addComponents() {
		HorizontalLayout homeLayout = new HorizontalLayout();
		VerticalLayout leftSection = new VerticalLayout();
		VerticalLayout rightSection = new VerticalLayout();

		Button settingsButton = new Button("Settings", new Icon(VaadinIcon.COG_O));
		settingsButton.setWidth("200px");
		settingsButton.addClickListener(e -> {
			settingsButton.getUI().ifPresent(ui -> ui.navigate("Settings"));
		});

		rightSection.add(settingsButton);

		this.address = new TextArea();
		this.address.setWidth("300px");

		Button calendarButton = new Button("Calendar");
		Button newPatientButton = new Button("Neuer Patient");
		Button searchPatientButton = new Button("Patient suchen");

		leftSection.add(this.address, calendarButton, newPatientButton, searchPatientButton);

		homeLayout.add(leftSection, rightSection);
		this.add(homeLayout);

	}

	private void updateView() {
		this.address.setValue(
				presentorAdmin.getInstitutionName() + "\n" + presentorAdmin.getInstitutionAddress().getStreet() + " "
						+ presentorAdmin.getInstitutionAddress().getStreetNr() + "\n"
						+ presentorAdmin.getInstitutionAddress().getZipCode() + " "
						+ presentorAdmin.getInstitutionAddress().getCity());
	}

	public void initPresentor() {
		presentorAdmin = Backend.getInstitutionPresenterAdminInstance();
		presentorAdmin.addObserver(this);

	}

	@Override
	public void update(Observable o, Object arg) {
		this.updateView();
	}

}
