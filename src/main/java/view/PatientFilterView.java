package view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import entity.PatientEntity;
import entity.PersonEntity;
import model.PatientModel;
import presenter.PatientPresenter;
import service.PatientService;
import service.UserService;

@Route("Patient suchen")

public class PatientFilterView extends HorizontalLayout implements BeforeEnterObserver {

	private PatientPresenter presenter;

	HorizontalLayout layout = new HorizontalLayout();
	VerticalLayout layoutMenu = new VerticalLayout();
	VerticalLayout layoutPage = new VerticalLayout();

	public PatientFilterView() {
		this.presenter = new PatientPresenter();
		menu();
		patientFilter();
		this.layoutPage.add(layout);
		this.add(layoutMenu, layoutPage);

	}

//	public void patientFilter() {
//
//		Grid<PatientEntity> grid = new Grid<>();
//		grid.setItems(presenter.getPatientData());
//		
//		grid.addColumn(PatientEntity::getFirstName).setHeader("Vorname");
//		grid.addColumn(PatientEntity::getLastName).setHeader("Nachname");
//		grid.addColumn(PatientEntity::getBirthdate).setHeader("Geburtsdatum");
//
//		
//		grid.setWidth("600px");
//		grid.setHeight("400px");
//		this.layout.add(grid);
//	}

	public void patientFilter() {


		Grid<PatientEntity> grid = new Grid<>();
		ListDataProvider<PatientEntity> dataProvider = new ListDataProvider<>(presenter.getPatientData());

		grid.setDataProvider(dataProvider);

		List<ValueProvider<PersonEntity, String>> valueProviders = new ArrayList<>();
//		valueProviders.add(PersonEntity::getFirstName);
//		valueProviders.add(PersonEntity::getLastName);
//		valueProviders.add(PersonEntity::getFirstName);
//		valueProviders.add(PersonEntity::getAddress);
//		valueProviders.add(PersonEntity::getCity);

		valueProviders.add(patient -> patient.getFirstName());
		valueProviders.add(patient -> patient.getLastName());
		valueProviders.add(patient -> String.valueOf(patient.getBirthdate()));
		valueProviders.add(patient -> patient.getAddress());
		valueProviders.add(patient -> patient.getCity());

		// Iterator<ValueProvider<PersonEntity, String>> iterator =
		// valueProviders.iterator();

		grid.addColumn(PatientEntity::getFirstName).setHeader("Vorname");
		grid.addColumn(PatientEntity::getLastName).setHeader("Nachname");
		grid.addColumn(PatientEntity::getBirthdate).setHeader("Geburtsdatum");
		grid.addColumn(PatientEntity::getAddress).setHeader("Adresse");
		grid.addColumn(PatientEntity::getCity).setHeader("Wohnort");

//		grid.addColumn((Renderer<PatientEntity>) iterator.next()).setHeader("Vorname");
//		grid.addColumn((Renderer<PatientEntity>) iterator.next()).setHeader("Nachname");
//		grid.addColumn((Renderer<PatientEntity>) iterator.next()).setHeader("Geburtsdatum");
//		grid.addColumn((Renderer<PatientEntity>) iterator.next()).setHeader("Adresse");
//		
		HeaderRow filterRow = grid.appendHeaderRow();

		Iterator<ValueProvider<PersonEntity, String>> iterator2 = valueProviders.iterator();

		grid.getColumns().forEach(column -> {
			TextField field = new TextField();
			ValueProvider<PersonEntity, String> valueProvider = iterator2.next();

			field.addValueChangeListener(event -> dataProvider.addFilter(
					patient -> StringUtils.containsIgnoreCase(valueProvider.apply(patient), field.getValue())));

			field.setValueChangeMode(ValueChangeMode.EAGER);

			filterRow.getCell(column).setComponent(field);
			field.setSizeFull();
			field.setPlaceholder("Filter");
		});

		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.addSelectionListener(event -> {
			try {

				PersonEntity selectedPerson = event.getFirstSelectedItem().get();
				PatientService.setPatient(selectedPerson);
				grid.getUI().ifPresent(ui -> ui.navigate("Patient"));
				

			} catch (NoSuchElementException e) {

			}

		});

		grid.setWidth("800px");
		grid.setHeight("800px");
		this.layout.add(grid);

	}

	private void menu() {
		Button home = new Button("Zurück zum Hautpmenü");
		home.setWidth("230px");
		home.addClickListener(e -> {
			home.getUI().ifPresent(ui -> ui.navigate("Home"));
		});

		Button patNew = new Button("Neuer Patient erfassen");
		patNew.setWidth("230px");
		patNew.addClickListener(e -> {
			patNew.getUI().ifPresent(ui -> ui.navigate("New Patient"));
		});

		Button calendar = new Button("Kalender");
		calendar.setWidth("230px");
		calendar.addClickListener(e -> {
			calendar.getUI().ifPresent(ui -> ui.navigate("Calendar"));
		});

		Button logout = new Button("Logout");
		logout.setWidth("230px");
		logout.addClickListener(e -> {
			logout.getUI().ifPresent(ui -> ui.navigate("Logout"));
		});

		VerticalLayout layout = new VerticalLayout(home, patNew, calendar, logout);
		layout.setSizeFull();
		this.layoutMenu.setWidth("250px");
		this.layoutMenu.add(layout);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		if (UserService.getUser() == null) {
			event.rerouteTo("");
		}
	}

}
//