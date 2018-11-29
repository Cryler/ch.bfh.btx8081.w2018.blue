package view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.Route;

import model.PatientModel;

/**
 * View for the new patient site.  
 * 
 *
 * @author Yannick Gehri
 *
 */

@Route("Patient suchen")

public class SearchPatientView extends VerticalLayout {
	
	public SearchPatientView() {
		//searchData();
		
//		HorizontalLayout actions = new HorizontalLayout();
//		this.add(this.layout);
	}

	public void searchData() {
		Grid<PatientModel> grid = new Grid<>();
		ListDataProvider<PatientModel> dataProvider = new ListDataProvider<>(createItems());
		grid.setDataProvider(dataProvider);
		
		List<ValueProvider<PatientModel, String>> valueProviders = new ArrayList<>();
		valueProviders.add(PatientModel::getName);
		valueProviders.add(person -> String.valueOf(person.getAge()));
		valueProviders.add(person -> person.getAddress().getStreet());
		valueProviders.add(person -> person.getAddress().getPostalCode());
		
		Iterator<ValueProvider<PatientModel, String>> iterator = valueProviders
		        .iterator();

		grid.addColumn(iterator.next()).setHeader("Name");
		grid.addColumn(iterator.next()).setHeader("Age");
		grid.addColumn(iterator.next()).setHeader("Street");
		grid.addColumn(iterator.next()).setHeader("Postal Code");

		HeaderRow filterRow = grid.appendHeaderRow();

		Iterator<ValueProvider<PatientModel, String>> iterator2 = valueProviders
		        .iterator();

		grid.getColumns().forEach(column -> {
		    TextField field = new TextField();
		    ValueProvider<PatientModel, String> valueProvider = iterator2.next();

		    field.addValueChangeListener(event -> dataProvider
		            .addFilter(person -> StringUtils.containsIgnoreCase(
		                    valueProvider.apply(person), field.getValue())));

		    field.setValueChangeMode(ValueChangeMode.EAGER);

		    filterRow.getCell(column).setComponent(field);
		    field.setSizeFull();
		    field.setPlaceholder("Filter");
		});

		
	}
	
}
