
/**
 *date: 29.11.2018   -  time: 14:42:45
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package view;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.NoResultException;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.shared.ui.LoadMode;

import entity.CalendarTileEntity;
import presenter.CalendarTilePresenter;

@Tag("Tile")
@StyleSheet(value = "styles/style.css", loadMode = LoadMode.INLINE)
public class CalendarTileView extends Div {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SimpleDateFormat dateformatter = new SimpleDateFormat("dd.MM");

	private TextArea info;
	private Label dateLabel;
	private CalendarTilePresenter presenter;

		
	public CalendarTileView(Date date) {		
		this.initView(date);
		this.initValues(date);
	}
	private void initView(Date date) {
		HorizontalLayout hl1 = new HorizontalLayout();
		hl1.setAlignItems(Alignment.CENTER);
		hl1.add(this.createLabel());
		hl1.add(new Button(new Icon(VaadinIcon.PLUS_CIRCLE_O), e -> {
			Dialog dialog = this.createDialog(date);
			dialog.open();
		}));

		HorizontalLayout hl2 = new HorizontalLayout();
		hl2.setAlignItems(Alignment.CENTER);
		this.info = new TextArea();
		this.info.setWidth("150px");
		this.info.setEnabled(false);
		hl2.add(this.info);

		VerticalLayout vl1 = new VerticalLayout();
		vl1.setHeight("100px");
		vl1.setAlignItems(Alignment.STRETCH);
		vl1.setMargin(false);
		vl1.setPadding(false);
		vl1.add(hl1, hl2);

		this.add(vl1);
	}

	private Label createLabel() {
		this.dateLabel = new Label();
		return this.dateLabel;
	}
	
	private void initValues(Date date) {
		this.presenter = new CalendarTilePresenter(date);
		CalendarTileEntity data = this.presenter.getDataOfEntry();
		this.info.setValue(data.getPatient());
		this.dateLabel.setText(this.dateformatter.format(date));
	}

	private Dialog createDialog(Date date) {
		Dialog dialog = new Dialog();	
		CalendarTileEntity data = this.presenter.getDataOfEntry();
		
		ComboBox<String> patientField = new ComboBox<>("Patient");
		try {
			patientField.setItems(this.presenter.getPatientNames());
			if(!data.getPatient().equals("")) {
				patientField.setValue(data.getPatient());
			}			
		}catch (NoResultException e) {
			patientField.setPlaceholder("Keine Patienten erfasst.");
		}	

		TextArea commentField = new TextArea();
		commentField.setValue(data.getKommentar());
		commentField.setLabel("Kommentar");

		Button saveButton = new Button("speichern");
		saveButton.addClickListener(event -> {
			this.presenter.setDataOfEntry(patientField.getValue(), commentField.getValue());
			this.initValues(date);
			dialog.close();
		});
		Button cancelButton = new Button("Abbrechen");
		cancelButton.addClickListener(event -> {
			dialog.close();
		});
		HorizontalLayout hl2 = new HorizontalLayout();
		hl2.add(saveButton, cancelButton);
		VerticalLayout vl1 = new VerticalLayout();
		vl1.add(patientField, commentField, hl2);
		dialog.add(vl1);
		return dialog;
	}
}