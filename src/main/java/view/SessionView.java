package view;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import service.UserService;

/**
 * View for the new session site.
 * 
 * @author Luca Leuenberger
 *
 */

@Route("Session")
public class SessionView extends HorizontalLayout implements BeforeEnterObserver {

	HorizontalLayout layout = new HorizontalLayout();
	VerticalLayout layoutTabs = new VerticalLayout();
	VerticalLayout layoutMenu = new VerticalLayout();
	VerticalLayout layoutPage = new VerticalLayout();

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		if (UserService.getUser() == null) {
			event.rerouteTo("");
		}
	}

	/**
	 * Constructor for the session site.
	 */
	public SessionView() {
		menu();
		session();
		patient();
		tabsNavigation();
		tabsDate();
		this.layoutPage.add(layoutTabs, layout);
		this.add(layoutMenu, layoutPage);
	}

	/**
	 * Vertical layout with a text field to see whose patient session it is.
	 */
	private void patient() {
		VerticalLayout layoutPatient = new VerticalLayout();
		TextField patient = new TextField("Patient:"); // connect with db and session so the right patient will appear
		patient.setPlaceholder("Müller, Hans");
		patient.setWidth("300px");
		layoutPatient.add(patient);
		layoutPatient.setAlignItems(Alignment.START);
		this.layout.add(layoutPatient);
	}

	/**
	 * Vertical layout with text areas and scales for various information asked
	 * during the session.
	 */
	private void session() {
		VerticalLayout layoutSession = new VerticalLayout();
		TextArea condition = new TextArea("Zustand des Patienten/Informationen der Session:");
		condition.setWidth("600px");
		condition.setHeight("300px");
		Label lblCraving = new Label("Craving Skala");
		RadioButtonGroup<Integer> craving = new RadioButtonGroup<>();
		craving.setItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//		craving.addValueChangeListener(e -> {
//			  e.getValue();
//		});
		layoutSession.add(condition, lblCraving, craving);
		this.layout.add(layoutSession);
		this.layout.setAlignSelf(Alignment.CENTER, layoutSession);
	}

	/**
	 * Tabs to navigate between the patients base data, sessions and diagrams.
	 */
	private void tabsNavigation() {
		HorizontalLayout layoutTabs = new HorizontalLayout();
		Tab tab1 = new Tab("Stammdaten");
		String page1 = "New Session";

		Tab tab2 = new Tab("Konsultationen");
		String page2 = "Session";

		Tab tab3 = new Tab("Diagramme");
		String page3 = "Diagram";

		Map<Tab, String> tabsAndPages = new HashMap<>();
		tabsAndPages.put(tab1, page1);
		tabsAndPages.put(tab2, page2);
		tabsAndPages.put(tab3, page3);
		Tabs tabs = new Tabs(tab1, tab2, tab3);
		tabs.setSelectedTab(tab2);
		tabs.addSelectedChangeListener(event -> {
			String selectedPage = tabsAndPages.get(tabs.getSelectedTab());
			tabs.getUI().ifPresent(ui -> ui.navigate(selectedPage));
		});

		layoutTabs.add(tabs);
		this.layoutTabs.add(layoutTabs);

	}

	/**
	 * Tabs to navigate between the different session dates.
	 */
	private void tabsDate() {
		Tabs tabs = newTabs();
		HorizontalLayout layoutTabs = new HorizontalLayout();
		tabs.addSelectedChangeListener(event -> {
			
		});

		layoutTabs.add(tabs);
		this.layoutTabs.add(layoutTabs);
	}

	/**
	 * Creates tabs for each date of all sessions.
	 * 
	 * @return all tabs in form of a Vaadin Tabs object.
	 */
	private Tabs newTabs() {
		Tabs tabs = new Tabs();
		for (int i = 0; i < 10; i++) { // db.getSessionCount() instead of 10
			String tab = "Tab" + i; // db.getSessionDate()
			Tab tabTemp = new Tab(tab);
			tabs.add(tabTemp);
		}
		return tabs;
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
			patNew.getUI().ifPresent(ui -> ui.navigate("Neuer Patient"));
		});

		Button patList = new Button("Patientenliste");
		patList.setWidth("230px");
		patList.addClickListener(e -> {
			patList.getUI().ifPresent(ui -> ui.navigate("Patient suchen"));
		});

		Button calendar = new Button("Kalender");
		calendar.setWidth("230px");
		calendar.addClickListener(e -> {
			calendar.getUI().ifPresent(ui -> ui.navigate("Kalender"));
		});

		Button sesNew = new Button("Neue Konsultation");
		sesNew.setWidth("230px");
		sesNew.addClickListener(e -> {
			sesNew.getUI().ifPresent(ui -> ui.navigate("Neue Session"));
		});

		Button logout = new Button("Logout");
		logout.setWidth("230px");
		logout.addClickListener(e -> {
			logout.getUI().ifPresent(ui -> ui.navigate("Logout"));
		});

		VerticalLayout layout = new VerticalLayout(home, patNew, patList, calendar, sesNew, logout);
		layout.setSizeFull();
		this.layoutMenu.setWidth("250px");
		this.layoutMenu.add(layout);
	}
}