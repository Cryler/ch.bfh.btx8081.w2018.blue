
/**
 *date: 29.11.2018   -  time: 14:42:45
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package model.calendar;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import javafx.scene.text.Text;

@Tag("Tile")
public class CalendarTile extends Div {

	private SimpleDateFormat dateformatter = new SimpleDateFormat("dd.MM");
	private String date;

	public CalendarTile(Date date) {

		VerticalLayout vl1 = new VerticalLayout();
		vl1.setAlignItems(Alignment.START);
		vl1.setHeight("100px");
		
		
		
		vl1.add(new Label(this.dateformatter.format(date)));
		vl1.add(new Button(" ",e -> {
			Notification.show(this.dateformatter.format(date));
		}));
		this.add(vl1);

	}
}
