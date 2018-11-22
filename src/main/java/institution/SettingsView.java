
/**
 *date: 22.11.2018   -  time: 16:26:39
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package institution;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import address.Address;

@Route("Settings")
public class SettingsView extends VerticalLayout {

	private InstitutionPresenterAdmin presenter;
	private TextField actualNameOfInstitution;
	private TextField actualStreetOfInstitution;
	private TextField actualStreetNrOfInstitution;
	private TextField actualZipCodeOfInstitution;
	private TextField actualCityOfInstitution;

	public SettingsView() {

		HorizontalLayout mainSections = new HorizontalLayout();
		VerticalLayout leftSection = new VerticalLayout();
		VerticalLayout rightSection = new VerticalLayout();
		HorizontalLayout bottomSection = new HorizontalLayout();

		Label nameOfInstitution = new Label("Name of Institution: ");
		Label streetOfInstitution = new Label("Street: ");
		Label streetNrOfInstitution = new Label("Streetnumber: ");
		Label zipCodeOfInstitution = new Label("Zip Code: ");
		Label cityOfInstitution = new Label("City: ");
		leftSection.add(nameOfInstitution, streetOfInstitution, streetNrOfInstitution, zipCodeOfInstitution,
				cityOfInstitution);

		this.actualNameOfInstitution = new TextField();
		this.actualStreetOfInstitution = new TextField();
		this.actualStreetNrOfInstitution = new TextField();
		this.actualZipCodeOfInstitution = new TextField();
		this.actualCityOfInstitution = new TextField();
		rightSection.add(this.actualNameOfInstitution, this.actualStreetOfInstitution, this.actualStreetNrOfInstitution,
				this.actualZipCodeOfInstitution, this.actualCityOfInstitution);

		mainSections.add(leftSection, rightSection);

		Button saveButton = new Button("Save");
		saveButton.addClickListener(e -> {
			Address newAddress = new Address(this.actualStreetOfInstitution.getValue(),
					Integer.valueOf(this.actualStreetNrOfInstitution.getValue()),
					Integer.valueOf(this.actualZipCodeOfInstitution.getValue()),
					this.actualCityOfInstitution.getValue());
			this.presenter.setInstitutionName(this.actualNameOfInstitution.getValue());
			this.presenter.setInstitutionAddress(newAddress);
			saveButton.getUI().ifPresent(ui -> ui.navigate("Home"));
		});

		bottomSection.add(saveButton);
		this.add(mainSections, bottomSection);

	}

	public void init(InstitutionPresenterAdmin presenter) {
		this.presenter = presenter;
		this.actualNameOfInstitution.setValue(this.presenter.getInstitutionName());
		this.actualStreetOfInstitution.setValue(this.presenter.getInstitutionAddress().getStreet());
		this.actualStreetNrOfInstitution
				.setValue(Integer.toString(this.presenter.getInstitutionAddress().getStreetNr()));
		this.actualZipCodeOfInstitution.setValue(Integer.toString(this.presenter.getInstitutionAddress().getZipCode()));
		this.actualCityOfInstitution.setValue(this.presenter.getInstitutionAddress().getCity());
	}

	
}
