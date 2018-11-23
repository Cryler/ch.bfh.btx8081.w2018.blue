package ch.bfh.btx8081.w2018.blue.zulu;


import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("New Session")
public class NewSession extends HorizontalLayout {

	final HorizontalLayout layout = new HorizontalLayout();
	
	public NewSession() {
		this.layout.setAlignItems(Alignment.CENTER);
		session();
		patient();
		this.add(layout);
		
 }
		
	private void patient() {
		ComboBox<String> combobox = new ComboBox<String>("Patient auswählen:");
		combobox.setItems("Leuenberger, Luca", "Gund, Yann", "Gehri, Yannick"); //TODO Connect with patientlist
		
		this.layout.add(combobox);
	
	}
	
	private void session() {
		VerticalLayout layout = new VerticalLayout();
		TextField condition = new TextField();
		condition.setWidth("500px");
		condition.setHeight("200px");
		layout.add(condition);
		RadioButtonGroup<Integer> craving = new RadioButtonGroup<>();
		craving.setItems(1,2,3,4,5,6,7,8,9,10);
//		craving.addValueChangeListener(e -> {
//			  e.getValue();
//		});
		      
		layout.add(craving);
		this.layout.add(layout);

	}

	
}