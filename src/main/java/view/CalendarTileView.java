
/**
 *date: 29.11.2018   -  time: 14:42:45
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package view;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.ui.LoadMode;

import model.CalendarTileModel;

@Tag("Tile")
@StyleSheet(value = "styles/style.css", loadMode = LoadMode.INLINE)
public class CalendarTileView extends Div {

	private SimpleDateFormat dateformatter = new SimpleDateFormat("dd.MM");

	private Date date;
	private CalendarTileModel model;
	private TextArea info;

	public CalendarTileView(Date date) {
		this.date = date;
		this.initView();
	}
	
	public CalendarTileView(Date date, CalendarTileModel model) {
		this.date = date;
		this.model = model;
		this.initView();
		this.update();
	}

	private void initView() {
		HorizontalLayout hl1 = new HorizontalLayout();
		hl1.setAlignItems(Alignment.CENTER);
		hl1.add(this.createLabel(date));
		hl1.add(new Button(new Icon(VaadinIcon.PLUS_CIRCLE_O), e -> {
			Dialog dialog = this.createDialog();
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

	private Label createLabel(Date date) {
		Label label = new Label(this.dateformatter.format(date));
		label.getStyle().set("fontSize", "15px");
		return label;
	}

	private Dialog createDialog() {
		Dialog dialog = new Dialog();
		TextField patientField = new TextField();
		patientField.setLabel("Patient");

		TextArea area = new TextArea();
		area.setLabel("Kommentar");

		Button saveButton = new Button("speichern");
		saveButton.addClickListener(event -> {
			this.model = new CalendarTileModel(this.date, area.getValue());
			this.update();
			dialog.close();
		});
		VerticalLayout vl1 = new VerticalLayout();
		vl1.add(patientField, area, saveButton);
		dialog.add(vl1);
		return dialog;
	}

	private void update() {
		this.info.setValue(this.model.getKommentar());
	}
}