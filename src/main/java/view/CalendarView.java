
/**
 *date: 29.11.2018   -  time: 14:41:12
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.Route;

import model.CalendarTile;
import model.CalendarTile.MyCalendarTile;

@Route("Kalender")
public class CalendarView extends Grid<CalendarTile> {

	private Calendar calendar;

	public CalendarView() {
		this.initView();
		this.initData();

	}

	private void initView() {
		this.setSelectionMode(SelectionMode.SINGLE);

		this.addColumn(CalendarTile::getDay).setHeader("Montag");
		this.addColumn(new LocalDateTimeRenderer<>(CalendarTile::getDate, "dd/MM")).setHeader("Purchase date and time");
	
		this.setItemDetailsRenderer(new ComponentRenderer<>(MyCalendarTile::new));
	}

	private void initData() {
		this.calendar = new GregorianCalendar();
		List<CalendarTile> tiles = createTiles();
		this.setItems(tiles);
	}

	private List<CalendarTile> createTiles() {
		ArrayList<CalendarTile> tiles = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			this.calendar.set(2018, 01, i);
			tiles.add(new CalendarTile(this.calendar.getTime()));
		}
		return tiles;
	}

}
