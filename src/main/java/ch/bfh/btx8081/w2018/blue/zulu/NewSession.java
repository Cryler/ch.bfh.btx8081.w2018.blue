package ch.bfh.btx8081.w2018.blue.zulu;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinRequest;

@Route("New Session")
public class NewSession extends HorizontalLayout {

//	protected void init(VaadinRequest vaadinRequest) {
//		final HorizontalLayout layout = new HorizontalLayout();
//		
//	}
	protected void init() {
		
	}
	private void patient(HorizontalLayout layout) {
		ComboBox combobox = new ComboBox("Patient auswählen");
		combobox.setItems("Leuenberger, Luca", "Gund, Yann", "Gehri, Yannick");
		
		add(combobox);
	}
	
	private void session(VerticalLayout layout) {
		
	}

	
}