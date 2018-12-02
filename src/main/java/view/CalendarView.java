
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

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import model.calendar.CalendarWeekTile;

@Route("Kalender")
public class CalendarView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Grid<CalendarWeekTile> grid;
	private Calendar calendar;

	public CalendarView() {
		this.initView();
		this.initCalendar();
		this.initData();

	}

	private void initView() {
		this.grid = new Grid<>();
		this.grid.setSelectionMode(SelectionMode.SINGLE);
		this.grid.setHeightByRows(true);

		this.grid.addComponentColumn(CalendarWeekTile::getMonday).setHeader("Montag");
		this.grid.addComponentColumn(CalendarWeekTile::getTuesday).setHeader("Dienstag");
		this.grid.addComponentColumn(CalendarWeekTile::getWednesday).setHeader("Mittwoch");
		this.grid.addComponentColumn(CalendarWeekTile::getThursday).setHeader("Donnerstag");
		this.grid.addComponentColumn(CalendarWeekTile::getFriday).setHeader("Freitag");
		this.grid.addComponentColumn(CalendarWeekTile::getSameday).setHeader("Samstag");
		this.grid.addComponentColumn(CalendarWeekTile::getSunday).setHeader("Sonntag");

		
		VerticalLayout vl1 = new VerticalLayout();
		vl1.setWidth("300px");
		vl1.add(this.createButton("Home", new Icon(VaadinIcon.HOME)));
		vl1.add(this.createButton("Neuer Patient", new Icon(VaadinIcon.USER_CHECK)));
		vl1.add(this.createButton("Patient suchen", new Icon(VaadinIcon.USERS)));
	
		
		HorizontalLayout hl1 = new HorizontalLayout();
		hl1.setWidth("100%");
		hl1.setHeight("100%");
		hl1.add(vl1, this.grid);
		
		this.add(hl1);
	}

	private void initCalendar() {
		this.calendar = new GregorianCalendar();
		this.calendar.setFirstDayOfWeek(Calendar.MONDAY);
		LocalDate firstDayOfMonth = LocalDate.now().minusDays(LocalDate.now().getDayOfMonth() - 1);
		this.calendar.setTime(Date.valueOf(firstDayOfMonth));
		@SuppressWarnings("static-access")
		LocalDate mondayInWeek1 = firstDayOfMonth.minusDays((this.calendar.DAY_OF_WEEK + 5) % 7);
		this.calendar.setTime(Date.valueOf(mondayInWeek1));

	}

	private void initData() {
		List<CalendarWeekTile> tiles = createTiles();
		this.grid.setItems(tiles);
	}

	private List<CalendarWeekTile> createTiles() {
		ArrayList<CalendarWeekTile> tiles = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			tiles.add(new CalendarWeekTile(this.calendar.getTime()));
			this.calendar.setTimeInMillis(this.calendar.getTimeInMillis() + 604800000);
		}
		return tiles;
	}
	
	private Button createButton(String value, Icon icon) {
		Button newButton = new Button(value, icon);
		newButton.addClickListener(e -> {
			e.getSource().getUI().ifPresent(UI -> UI.navigate(e.getSource().getText()));
		});
		newButton.setWidth("200px");
		return newButton;
	}

}
