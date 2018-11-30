
/**
 *date: 29.11.2018   -  time: 14:41:12
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package view;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.GregorianCalendar;
import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.Route;

import model.calendar.CalendarWeekTile;

@Route("Kalender")
public class CalendarView extends Grid<CalendarWeekTile> {

	private Calendar calendar;

	public CalendarView() {
		this.initView();
		this.initCalendar();
		this.initData();

	}

	private void initView() {
		this.setSelectionMode(SelectionMode.SINGLE);
		this.setHeightByRows(true);

		this.addComponentColumn(CalendarWeekTile::getMonday).setHeader("Montag");
		this.addComponentColumn(CalendarWeekTile::getTuesday).setHeader("Dienstag");
		this.addComponentColumn(CalendarWeekTile::getWednesday).setHeader("Mittwoch");
		this.addComponentColumn(CalendarWeekTile::getThursday).setHeader("Donnerstag");
		this.addComponentColumn(CalendarWeekTile::getFriday).setHeader("Freitag");
		this.addComponentColumn(CalendarWeekTile::getSameday).setHeader("Samstag");
		this.addComponentColumn(CalendarWeekTile::getSunday).setHeader("Sonntag");

	}

	private void initCalendar() {
		this.calendar = new GregorianCalendar();
		this.calendar.setFirstDayOfWeek(Calendar.MONDAY);
		LocalDate firstDayOfMonth = LocalDate.now().minusDays(LocalDate.now().getDayOfMonth() - 1);
		this.calendar.setTime(Date.valueOf(firstDayOfMonth));
		LocalDate mondayInWeek1 = firstDayOfMonth.minusDays((this.calendar.DAY_OF_WEEK + 3) % 7);
		this.calendar.setTime(Date.valueOf(mondayInWeek1));

	}

	private void initData() {
		List<CalendarWeekTile> tiles = createTiles();
		this.setItems(tiles);
	}

	private List<CalendarWeekTile> createTiles() {
		ArrayList<CalendarWeekTile> tiles = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			tiles.add(new CalendarWeekTile(this.calendar.getTime()));
			this.calendar.setTimeInMillis(this.calendar.getTimeInMillis() + 604800000);
		}
		return tiles;
	}

}
