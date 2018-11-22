
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
import com.vaadin.flow.router.Route;

@Route("Home")
public class InstitutionView extends VerticalLayout {

	public InstitutionView() {
		
		
		HorizontalLayout topSection = new HorizontalLayout();
		VerticalLayout addressSection = new VerticalLayout();
		HorizontalLayout institutionNameSection = new HorizontalLayout();
		
		Label zuluLabel = new Label("ZULU: ");
		Label institutionName = new Label("DefaultInstitution");
		Label institutionStreet = new Label("Defaultstreet 50");
		Label institutionCity = new Label("3000 Defaultcity");
		Button settingsButton = new Button("Settings", new Icon(VaadinIcon.COG_O));
		settingsButton.setWidth("200px");
		
		
		institutionNameSection.add(zuluLabel, institutionName);
		addressSection.add(institutionNameSection, institutionStreet, institutionCity);
		topSection.add(addressSection, settingsButton);
	
		
		
		
		
		
		
		
		Button calendarButton = new Button("Calendar");

		Button newPatientButton = new Button("Neuer Patient");

		Button searchPatientButton = new Button("Patient suchen");

		this.add(topSection, calendarButton, newPatientButton, searchPatientButton);
	}

}
