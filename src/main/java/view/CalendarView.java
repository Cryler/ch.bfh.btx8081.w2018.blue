
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

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

import presenter.CalendarPresenter;
import presenter.CalendarWeekTile;

@Route("Kalender")

public class CalendarView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CalendarPresenter presenter;
	private TextArea area;

	private Grid<CalendarWeekTile> grid;
	private Calendar calendar;

	public CalendarView() {
		this.initView();
		this.updateLabelOfInstitution();
		this.initCalendar();
		this.createTilesOfCalendar();
		this.loadDataFromDB();
	}

	private void initView() {
		this.presenter = new CalendarPresenter();
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
		vl1.add(this.createTextArea());
		vl1.add(this.createButton("Home", new Icon(VaadinIcon.HOME)));
		vl1.add(this.createButton("Neuer Patient", new Icon(VaadinIcon.USER_CHECK)));
		vl1.add(this.createButton("Patient suchen", new Icon(VaadinIcon.USERS)));

		HorizontalLayout hl1 = new HorizontalLayout();
		hl1.setWidth("100%");
		hl1.setHeight("100%");
		hl1.add(vl1, this.grid);

		this.add(hl1);

	}

	// Sets the Date of the Calendar on the Monday of the Week in wich the first of
	// the actual Month is.
	private void initCalendar() {
		this.calendar = new GregorianCalendar();
		this.calendar.set(Calendar.DAY_OF_MONTH, 1);
		while (!this.calendar.getTime().toString().substring(0, 3).equals("Mon")) {
			this.theDayBefore();
		}

	}

	// Hilfsmethoden

	private void createTilesOfCalendar() {
		List<CalendarWeekTile> tiles = createTiles();
		for(CalendarWeekTile weekTile : tiles) {
			weekTile.loadData();
		}
		this.grid.setItems(tiles);
		System.out.println("Calendar-initData-End");
	}
	
	
	

	private void updateLabelOfInstitution() {
		this.area.setValue(this.presenter.getInstitutionData());
	}
	
	
	
	

	private List<CalendarWeekTile> createTiles() {
		ArrayList<CalendarWeekTile> tiles = new ArrayList<>();

		for (int i = 0; i < 6; i++) {
			tiles.add(new CalendarWeekTile(this.calendar.getTime()));
			this.calendar.set(Calendar.WEEK_OF_YEAR, this.calendar.get(Calendar.WEEK_OF_YEAR) + 1);

		}
		return tiles;
	}
	
	
	

	private Button createButton(String value, Icon icon) {
		Button newButton = new Button(value, icon);
		newButton.addClickListener(e -> {
			this.presenter.buttonClicked(e);
		});
		newButton.setWidth("200px");
		return newButton;
	}
	
	
	

	private TextArea createTextArea() {
		this.area = new TextArea();
		this.area.setWidth("200px");
		this.area.setEnabled(false);
		return this.area;
	}
	
	
	

	private void theDayBefore() {
		this.calendar.set(Calendar.DATE, this.calendar.get(Calendar.DATE) - 1);
	}

}
