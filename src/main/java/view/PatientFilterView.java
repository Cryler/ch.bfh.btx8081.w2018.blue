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
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
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
		VerticalLayout vlMenu = this.createMenu();
		VerticalLayout vlBody = this.patientFilter();
		patientFilter();
		this.layoutPage.add(layout);
		this.add(vlMenu,vlBody, layoutMenu, layoutPage);
		this.setAlignItems(Alignment.CENTER);
		

	}
	

	public VerticalLayout patientFilter() {

		Grid<PatientEntity> grid = new Grid<>();
		ListDataProvider<PatientEntity> dataProvider = new ListDataProvider<>(presenter.getPatientData());

		grid.setDataProvider(dataProvider);

		List<ValueProvider<PatientEntity, String>> valueProviders = new ArrayList<>();

		valueProviders.add(patient -> patient.getFirstName());
		valueProviders.add(patient -> patient.getLastName());
		valueProviders.add(patient -> String.valueOf(patient.getBirthdate()));
		valueProviders.add(patient -> patient.getAddress());
		valueProviders.add(patient -> patient.getCity());

		grid.addColumn(PatientEntity::getFirstName).setHeader("Vorname");
		grid.addColumn(PatientEntity::getLastName).setHeader("Nachname");
		grid.addColumn(PatientEntity::getBirthdate).setHeader("Geburtsdatum");
		grid.addColumn(PatientEntity::getAddress).setHeader("Adresse");
		grid.addColumn(PatientEntity::getCity).setHeader("Wohnort");

		HeaderRow filterRow = grid.appendHeaderRow();

		Iterator<ValueProvider<PatientEntity, String>> iterator2 = valueProviders.iterator();

		grid.getColumns().forEach(column -> {
			TextField field = new TextField();
			ValueProvider<PatientEntity, String> valueProvider = iterator2.next();

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

				PatientEntity selectedPerson = event.getFirstSelectedItem().get();
				PatientService.setPatient(selectedPerson);
				grid.getUI().ifPresent(ui -> ui.navigate("Patient"));

			} catch (NoSuchElementException e) {

			}

		});

		grid.setWidth("800px");
		grid.setHeight("800px");
//		this.layout.add(grid);
		
		Label info = new Label("Alle erfassten Patienten: ");
		info.getStyle().set("font-size", "200%");
		
		VerticalLayout vlBody = new VerticalLayout();
		vlBody.getStyle().set("magrin-top", "50px");
		vlBody.add(info,grid);
		
		return vlBody;

	}
	
	
	
	public VerticalLayout createMenu() {
		VerticalLayout vlMenu = new VerticalLayout();
		vlMenu.setWidth("250px");
		vlMenu.add(this.createMenuButton("Home", new Icon(VaadinIcon.HOME)));
		vlMenu.add(this.createMenuButton("Kalender", new Icon(VaadinIcon.CALENDAR)));
		vlMenu.add(this.createMenuButton("Neuer Patient", new Icon(VaadinIcon.USERS)));
		vlMenu.add(this.createMenuButton("Logout", new Icon(VaadinIcon.POWER_OFF)));
		return vlMenu;
	}
	
	private Button createMenuButton(String value, Icon icon) {
		Button newButton = new Button(value, icon);
		newButton.addClickListener(e -> {
			this.presenter.menuButtonClicked(e);
		});
		newButton.setWidth("200px");
		return newButton;
	}

//	private void menu() {
//		Button home = new Button("Zurück zum Hautpmenü");
//		home.setWidth("230px");
//		home.addClickListener(e -> {
//			home.getUI().ifPresent(ui -> ui.navigate("Home"));
//		});
//
//		Button patNew = new Button("Neuer Patient erfassen");
//		patNew.setWidth("230px");
//		patNew.addClickListener(e -> {
//			patNew.getUI().ifPresent(ui -> ui.navigate("New Patient"));
//		});
//
//		Button calendar = new Button("Kalender");
//		calendar.setWidth("230px");
//		calendar.addClickListener(e -> {
//			calendar.getUI().ifPresent(ui -> ui.navigate("Calendar"));
//		});
//
//		Button logout = new Button("Logout");
//		logout.setWidth("230px");
//		logout.addClickListener(e -> {
//			logout.getUI().ifPresent(ui -> ui.navigate("Logout"));
//		});
//
//		VerticalLayout layout = new VerticalLayout(home, patNew, calendar, logout);
//		layout.setSizeFull();
//		this.layoutMenu.setWidth("250px");
//		this.layoutMenu.add(layout);
//	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		if (UserService.getUser() == null) {
			event.rerouteTo("");
		}
	}

}
//