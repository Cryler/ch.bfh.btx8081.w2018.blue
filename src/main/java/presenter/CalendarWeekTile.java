
/**
 *date: 30.11.2018   -  time: 09:06:10
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package presenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;

import view.CalendarTileView;

/**
 * The Class CalendarWeekTile.
 * 
 * @author gundy1
 */
@Tag("WeekTile")
public class CalendarWeekTile extends Div {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Calendar cal = new GregorianCalendar();

	/** The Constant MONTAG. */
	private static final int MONTAG = 0;

	/** The Constant DIENSTAG. */
	private static final int DIENSTAG = 1;

	/** The Constant MITTWOCH. */
	private static final int MITTWOCH = 2;

	/** The Constant DONNERSTAG. */
	private static final int DONNERSTAG = 3;

	/** The Constant FREITAG. */
	private static final int FREITAG = 4;

	/** The Constant SAMSTAG. */
	private static final int SAMSTAG = 5;

	/** The Constant SONNTAG. */
	private static final int SONNTAG = 6;

	/**
	 * The week is represented by 7 instances of {@code CalendarTileView} that are
	 * stored in the Array at the position based on the Constants.
	 */
	private ArrayList<CalendarTileView> week = new ArrayList<>();

	/**
	 * Instantiates a new calendarweektile and sets the {@value cal} on the passed
	 * date.
	 *
	 * @param date the date
	 */
	public CalendarWeekTile(Date date) {
		this.cal.setTime(date);
		this.fillWeek();
	}

	/**
	 * fills the week list with instances of {@code CalendarTileView}.
	 */
	private void fillWeek() {
		for (int i = 0; i < 7; i++) {
			this.week.add(new CalendarTileView(this.cal.getTime()));
			this.cal.set(Calendar.DAY_OF_MONTH, this.cal.get(Calendar.DAY_OF_MONTH) + 1);
		}
	}

	/**
	 * Gets the {@code CalendarTileView} stored in the week at the {@value MONTAG}.
	 *
	 * @return the monday
	 */
	public CalendarTileView getMonday() {
		return this.week.get(CalendarWeekTile.MONTAG);
	}

	/**
	 * Gets the {@code CalendarTileView} stored in the week at the
	 * {@value DIENSTAG}.
	 *
	 * @return the tuesday
	 */
	public CalendarTileView getTuesday() {
		return this.week.get(CalendarWeekTile.DIENSTAG);
	}

	/**
	 * Gets the {@code CalendarTileView} stored in the week at the
	 * {@value MITTWOCH}.
	 *
	 * @return the wednesday
	 */
	public CalendarTileView getWednesday() {
		return this.week.get(CalendarWeekTile.MITTWOCH);
	}

	/**
	 * Gets the {@code CalendarTileView} stored in the week at the
	 * {@value DONNERSTAG}.
	 *
	 * @return the thursday
	 */
	public CalendarTileView getThursday() {
		return this.week.get(CalendarWeekTile.DONNERSTAG);
	}

	/**
	 * Gets the {@code CalendarTileView} stored in the week at the {@value FREITAG}.
	 *
	 * @return the friday
	 */
	public CalendarTileView getFriday() {
		return this.week.get(CalendarWeekTile.FREITAG);
	}

	/**
	 * Gets the {@code CalendarTileView} stored in the week at the {@value SAMSTAG}.
	 *
	 * @return the sameday
	 */
	public CalendarTileView getSameday() {
		return this.week.get(CalendarWeekTile.SAMSTAG);
	}

	/**
	 * Gets the {@code CalendarTileView} stored in the week at the {@value SONNTAG}.
	 *
	 * @return the sunday
	 */
	public CalendarTileView getSunday() {
		return this.week.get(CalendarWeekTile.SONNTAG);
	}
}
