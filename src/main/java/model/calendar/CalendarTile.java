
/**
 *date: 29.11.2018   -  time: 14:42:45
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package model.calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.shared.ui.LoadMode;

@SuppressWarnings("serial")
@Tag("Tile")
@StyleSheet(value = "styles/style.css", loadMode = LoadMode.INLINE)
public class CalendarTile extends Div {

	private SimpleDateFormat dateformatter = new SimpleDateFormat("dd.MM");

	public CalendarTile(Date date) {

		HorizontalLayout hl1 = new HorizontalLayout();
		hl1.setAlignItems(Alignment.CENTER);
		hl1.add(this.createLabel(date));
		hl1.add(new Button(new Icon(VaadinIcon.PLUS_CIRCLE_O), e -> {
			Notification.show(this.dateformatter.format(date));
		}));

		HorizontalLayout hl2 = new HorizontalLayout();
		hl2.setAlignItems(Alignment.CENTER);
		TextArea area = new TextArea();
		area.setWidth("150px");
		area.setEnabled(false);
		hl2.add(area);

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
}
