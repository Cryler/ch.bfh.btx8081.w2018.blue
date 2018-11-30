
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
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import javafx.scene.text.Text;

@Tag(value = "test")
public class CalendarTile extends Div {

	private SimpleDateFormat dateformatter = new SimpleDateFormat("dd.MM");
	private String date;

	public CalendarTile(Date date) {

		VerticalLayout vl1 = new VerticalLayout();
		vl1.setHeight("100px");
		this.date = this.dateformatter.format(date);
		vl1.add(new Label(this.date));
		this.add(vl1);

	}

	public static class MyCalendarTile extends Div {
		public MyCalendarTile(CalendarTile tile) {
			HorizontalLayout hl1 = new HorizontalLayout();
			VerticalLayout vl1 = new VerticalLayout();
			VerticalLayout vl2 = new VerticalLayout();
			vl1.add(new Icon(VaadinIcon.AIRPLANE));
			vl1.add(new Label("Datum: "));
			vl1.setAlignItems(Alignment.END);
			vl2.add(new Icon(VaadinIcon.CHEVRON_RIGHT_SMALL));
			vl2.add(new Label("Just Some testing"));
			hl1.add(vl1, vl2);
			this.add(hl1);
			hl1.setAlignItems(Alignment.CENTER);
		}
	}
}
