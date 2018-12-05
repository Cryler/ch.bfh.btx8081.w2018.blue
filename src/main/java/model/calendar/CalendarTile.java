
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

@SuppressWarnings("serial")
@Tag("Tile")
public class CalendarTile extends Div {

	private SimpleDateFormat dateformatter = new SimpleDateFormat("dd.MM");

	public CalendarTile(Date date) {

		VerticalLayout vl1 = new VerticalLayout();
		vl1.setAlignItems(Alignment.START);
		vl1.setMargin(false);
		vl1.setPadding(false);
		vl1.setHeight("100px");
		vl1.add(new Label(this.dateformatter.format(date)));
		vl1.add(new Button(new Icon(VaadinIcon.PLUS_CIRCLE_O), e -> {
			Notification.show(this.dateformatter.format(date));
		}));

		VerticalLayout vl2 = new VerticalLayout();
		vl2.setAlignItems(Alignment.START);
		vl2.setMargin(false);
		vl2.setPadding(false);
		TextArea area = new TextArea();
		area.setHeight("90px");
		area.setEnabled(false);

		vl2.add(area);

		HorizontalLayout hl1 = new HorizontalLayout();

		hl1.setAlignItems(Alignment.STRETCH);
		hl1.setMargin(false);
		hl1.setPadding(false);
		hl1.add(vl1, vl2);

		this.add(hl1);

	}
}
