package view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import entity.PatientEntity;
import model.PatientModel;
import presenter.PatientPresenter;

//@Route("Patient Filter")
public class PatientFilterView extends VerticalLayout {

	private PatientPresenter presenter;

	HorizontalLayout layout = new HorizontalLayout();

	public PatientFilterView() {
		this.presenter = new PatientPresenter();

		patientFilter();
		this.add(layout);
	}

	public void patientFilter() {

		Grid<PatientEntity> grid = new Grid<>();
		grid.setItems(presenter.getPatientData());
		
		grid.addColumn(PatientEntity::getFirstName).setHeader("Vorname");
		grid.addColumn(PatientEntity::getLastName).setHeader("Nachname");

		
		
		this.layout.add(grid);
	}

}
 //