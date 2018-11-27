
/**
 *date: 22.11.2018   -  time: 16:26:39
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package institution;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import address.Address;
import backend.Backend;

@Route("Settings")
public class SettingsView extends VerticalLayout {

	private InstitutionPresenterAdmin presenter;

	private TextField actualNameOfInstitution;
	private TextField actualStreetOfInstitution;
	private TextField actualStreetNrOfInstitution;
	private TextField actualZipCodeOfInstitution;
	private TextField actualCityOfInstitution;

	public SettingsView() {
		this.initView();
		this.addComponents();
		this.initData();
	}

	private void initView() {

		this.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		this.setSizeFull();
	}

	private void addComponents() {

		HorizontalLayout bottomSection = new HorizontalLayout();

		Label nameOfInstitution = this.createLabel("Name of Institution: ");
		this.actualNameOfInstitution = this.createTextfield();
		HorizontalLayout nameLayout = new HorizontalLayout();
		nameLayout.add(nameOfInstitution, this.actualNameOfInstitution);

		Label streetOfInstitution = this.createLabel("Street: ");
		this.actualStreetOfInstitution = this.createTextfield();
		HorizontalLayout streetLayout = new HorizontalLayout();
		streetLayout.add(streetOfInstitution, this.actualStreetOfInstitution);

		Label streetNrOfInstitution = this.createLabel("Streetnumber: ");
		this.actualStreetNrOfInstitution = this.createTextfield();
		HorizontalLayout streetNrLayout = new HorizontalLayout();
		streetNrLayout.add(streetNrOfInstitution, this.actualStreetNrOfInstitution);

		Label zipCodeOfInstitution = this.createLabel("Zip Code: ");
		this.actualZipCodeOfInstitution = this.createTextfield();
		HorizontalLayout zipLayout = new HorizontalLayout();
		zipLayout.add(zipCodeOfInstitution, this.actualZipCodeOfInstitution);

		Label cityOfInstitution = this.createLabel("City: ");
		this.actualCityOfInstitution = this.createTextfield();
		HorizontalLayout cityLayout = new HorizontalLayout();
		cityLayout.add(cityOfInstitution, this.actualCityOfInstitution);

		Button saveButton = this.createButton("Speichern");
		saveButton.addClickListener(e -> {
			Address newAddress = new Address(this.actualStreetOfInstitution.getValue(),
					Integer.valueOf(this.actualStreetNrOfInstitution.getValue()),
					Integer.valueOf(this.actualZipCodeOfInstitution.getValue()),
					this.actualCityOfInstitution.getValue());
			this.presenter.setInstitutionName(this.actualNameOfInstitution.getValue());
			this.presenter.setInstitutionAddress(newAddress);
			saveButton.getUI().ifPresent(ui -> ui.navigate("Home"));
		});

		Button cancelButton = this.createButton("Abbrechen");
		cancelButton.addClickListener(e -> {
			cancelButton.getUI().ifPresent(ui -> ui.navigate("Home"));
		});

		bottomSection.add(cancelButton, saveButton);
		bottomSection.setWidth(nameLayout.getWidth());
	
		this.add(nameLayout, streetLayout, streetNrLayout, zipLayout, cityLayout, bottomSection);
		
	}

	public void initData() {
		this.presenter = Backend.getInstitutionPresenterAdminInstance();
		this.actualNameOfInstitution.setValue(this.presenter.getInstitutionName());
		this.actualStreetOfInstitution.setValue(this.presenter.getInstitutionAddress().getStreet());
		this.actualStreetNrOfInstitution
				.setValue(Integer.toString(this.presenter.getInstitutionAddress().getStreetNr()));
		this.actualZipCodeOfInstitution.setValue(Integer.toString(this.presenter.getInstitutionAddress().getZipCode()));
		this.actualCityOfInstitution.setValue(this.presenter.getInstitutionAddress().getCity());
	}

	private TextField createTextfield() {
		TextField newTextfield = new TextField();
		new TextField().setWidth("100px");
		return newTextfield;
	}

	private Label createLabel(String value) {
		Label newLabel = new Label(value);
		newLabel.setWidth("100px");
		return newLabel;
	}

	private Button createButton(String value) {
		Button newButton = new Button(value);
		newButton.setWidth("150px");
		return newButton;
	}
}
