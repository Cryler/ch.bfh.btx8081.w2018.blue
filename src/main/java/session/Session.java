package session;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

/**
 * View for the new session site.
 * 
 * @author Luca Leuenberger
 *
 */
@Route("Session")
public class Session extends VerticalLayout {

	HorizontalLayout layout = new HorizontalLayout();
	VerticalLayout layoutTabs = new VerticalLayout();

	/**
	 * Cosntructor for the new session site.
	 */
	public Session() {

		session();
		patient();
		tabsNavigation();
		tabsDate();
		this.add(layoutTabs, layout);
	}

	/**
	 * Vertical layout with a combobox to select whose patient session it is.
	 */
	private void patient() {
		VerticalLayout layoutPatient = new VerticalLayout();
		ComboBox<String> combobox = new ComboBox<String>("Patient auswählen:");
		combobox.setItems("Leuenberger, Luca", "Gund, Yann", "Gehri, Yannick"); // TODO Connect with patientlist
		combobox.setPlaceholder("Auswählen");
		layoutPatient.add(combobox);
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
	 * Horizontal layout with 2 buttons to save or cancel the session.
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

		tabs.addSelectedChangeListener(event -> {
			String selectedPage = tabsAndPages.get(tabs.getSelectedTab());
			tabs.getUI().ifPresent(ui -> ui.navigate(selectedPage));
		});

		layoutTabs.add(tabs);
		this.layoutTabs.add(layoutTabs);

	}
	
	private void tabsDate() {
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

		tabs.addSelectedChangeListener(event -> {
			String selectedPage = tabsAndPages.get(tabs.getSelectedTab());
			tabs.getUI().ifPresent(ui -> ui.navigate(selectedPage));
		});

		layoutTabs.add(tabs);
		this.layoutTabs.add(layoutTabs);

	}
}