
/**
 *date: 30.11.2018   -  time: 09:06:10
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import view.CalendarTileView;

@Tag("WeekTile")
public class CalendarWeekTile extends Div {

	private Calendar cal = new GregorianCalendar();
	private static final int MONTAG = 0;
	private static final int DIENSTAG = 1;
	private static final int MITTWOCH = 2;
	private static final int DONNERSTAG = 3;
	private static final int FREITAG = 4;
	private static final int SAMSTAG = 5;
	private static final int SONNTAG = 6;
	
	private int weekID;
	
	ArrayList<CalendarTileView> week = new ArrayList<>();

	public CalendarWeekTile(Date date) {
		this.cal.setTime(date);
		this.fillWeek();
		this.initValues();
	}

	private void initValues() {
		// TODO Auto-generated method stub
		//Here Comes the Database connection anf fetching the values of current existing Entrys.
	}

	public CalendarTileView getMonday() {
		return this.week.get(CalendarWeekTile.MONTAG);
	}

	public CalendarTileView getTuesday() {
		return this.week.get(CalendarWeekTile.DIENSTAG);
	}

	public CalendarTileView getWednesday() {
		return this.week.get(CalendarWeekTile.MITTWOCH);
	}

	public CalendarTileView getThursday() {
		return this.week.get(CalendarWeekTile.DONNERSTAG);
	}

	public CalendarTileView getFriday() {
		return this.week.get(CalendarWeekTile.FREITAG);
	}

	public CalendarTileView getSameday() {
		return this.week.get(CalendarWeekTile.SAMSTAG);
	}

	public CalendarTileView getSunday() {
		return this.week.get(CalendarWeekTile.SONNTAG);
	}

	private void fillWeek() {
		new HorizontalLayout();
		for (int i = 0; i < 7; i++) {
			this.week.add(new CalendarTileView(this.cal.getTime()));
			this.cal.set(Calendar.DAY_OF_MONTH, this.cal.get(Calendar.DAY_OF_MONTH) + 1);
		}

	}

}
