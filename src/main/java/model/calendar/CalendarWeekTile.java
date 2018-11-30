
/**
 *date: 30.11.2018   -  time: 09:06:10
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package model.calendar;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

@Tag("WeekTile")
public class CalendarWeekTile extends Div{
	
	private Calendar cal = new GregorianCalendar();
	private HorizontalLayout hl1 ;
	ArrayList<CalendarTile> week = new ArrayList<>();

	public CalendarWeekTile(Date date) {
		this.cal.setTime(date);
		this.fillWeek();
		
	}


	public CalendarTile getMonday() {
		return this.week.get(0);
	}

	public CalendarTile getTuesday() {
		return this.week.get(1);
	}

	public CalendarTile getWednesday() {
		return this.week.get(2);
	}

	public CalendarTile getThursday() {
		return this.week.get(3);
	}

	public CalendarTile getFriday() {
		return this.week.get(4);
	}

	public CalendarTile getSameday() {
		return this.week.get(5);
	}

	public CalendarTile getSunday() {
		return this.week.get(6);
	}

	private void fillWeek() {
		this.hl1 = new HorizontalLayout();
		for (int i = 0; i < 7; i++) {
			this.week.add(new CalendarTile(this.cal.getTime()));
			this.cal.setTimeInMillis(this.cal.getTimeInMillis()+86400000);
		}
		
	}
	
}
