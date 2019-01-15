
/**
 *date: 29.11.2018   -  time: 14:41:12
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import presenter.CalendarPresenter;
import presenter.CalendarWeekTile;
import service.UserService;

/**
 * The Class CalendarView.
 * 
 * @author gundy1.
 */
@Route("Kalender")

public class CalendarView extends VerticalLayout implements BeforeEnterObserver {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The presenter that handles all userinputs in the Calendar View. */
	private CalendarPresenter presenter;

	/** The area that displays the Name and the Address of the Institution. */
	private TextArea area;

	/** The month that is currently shown on the Calendar. */
	private Label month;

	/**
	 * The first Day of month. This is stored because its used in the calculations
	 */
	private Date firstOfMonth = new Date();

	/** The grid of all CalendarEntitys. */
	private Grid<CalendarWeekTile> grid;

	/** The calendar on which the Calendar entity's are based. */
	private Calendar calendar;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.flow.router.internal.BeforeEnterHandler#beforeEnter(com.vaadin.
	 * flow.router.BeforeEnterEvent)
	 */
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		if (UserService.getUser() == null) {
			event.rerouteTo("");
		}
	}

	/**
	 * Instantiates a new calendar view. First the graphical components are created.
	 * Then all Values get updated, and last all Tiles of the Grid are created.
	 */
	public CalendarView() {
		this.presenter = new CalendarPresenter();
		this.initView();
		this.updateLabelOfInstitution();
		this.initCalendar();
		this.createRowsOfCalendar();

	}

	/**
	 * Creates all graphical Components of the {@code CalendarView}.
	 */
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
		vl1.setWidth("250px");

		vl1.add(this.createTextArea());
		vl1.add(this.createMenuButton("Home", new Icon(VaadinIcon.HOME)));
		vl1.add(this.createMenuButton("Neuer Patient", new Icon(VaadinIcon.USER_CHECK)));
		vl1.add(this.createMenuButton("Patient suchen", new Icon(VaadinIcon.USERS)));
		vl1.add(this.createMenuButton("Logout", new Icon(VaadinIcon.POWER_OFF)));
		HorizontalLayout hl2 = new HorizontalLayout();
		hl2.setWidth("100%");
		hl2.add(this.createCalendarNavigationButton(false, new Icon(VaadinIcon.ARROW_CIRCLE_LEFT_O)));
		this.month = new Label();
		this.month.getStyle().set("margin-top", "10px");
		hl2.add(this.month);
		hl2.add(this.createCalendarNavigationButton(true, new Icon(VaadinIcon.ARROW_CIRCLE_RIGHT_O)));
		hl2.setFlexGrow(2, this.month);
		vl1.add(hl2);

		HorizontalLayout hl1 = new HorizontalLayout();
		hl1.setWidth("100%");
		hl1.setHeight("100%");
		hl1.add(vl1, this.grid);

		this.add(hl1);

	}

	/**
	 * Inits the calendar. Sets The Calendar on the first Day of the Month. Sets the
	 * Label on the current Month Then it searches the Monday in the Week the first
	 * day of the Month is in.
	 * 
	 * Based on This Date the CalendarTiles for a Week will be created.
	 */
	private void initCalendar() {
		this.calendar = new GregorianCalendar();
		this.calendar.set(Calendar.DAY_OF_MONTH, 1);
		this.month.setText(this.parseMonth(this.calendar.get(Calendar.MONTH)));
		this.firstOfMonth = this.calendar.getTime();
		while (!this.calendar.getTime().toString().substring(0, 3).equals("Mon")) {
			this.theDayBefore();
		}

	}

	/**
	 * Sets the calendar date to one Month before or one Month in the future. Then
	 * it Sets the Calendar to the first Day of the month, then it searches the
	 * Monday in this week an sets the date of the calendar on that date.
	 *
	 * @param nextMonth the new calendar date
	 */
	private void setCalendarDate(boolean nextMonth) {
		if (nextMonth) {
			this.calendar.set(Calendar.MONTH, this.calendar.get(Calendar.MONTH) + 1);
			this.month.setText(this.parseMonth(this.calendar.get(Calendar.MONTH)));
			this.firstOfMonth = this.calendar.getTime();
			while (!this.calendar.getTime().toString().substring(0, 3).equals("Mon")) {
				this.theDayBefore();
			}
		} else {
			this.calendar.set(Calendar.MONTH, this.calendar.get(Calendar.MONTH) - 1);
			this.month.setText(this.parseMonth(this.calendar.get(Calendar.MONTH)));
			this.firstOfMonth = this.calendar.getTime();
			while (!this.calendar.getTime().toString().substring(0, 3).equals("Mon")) {
				this.theDayBefore();
			}
		}
		this.createRowsOfCalendar();
	}


	/**
	 * Creates the rows of calendar. For every Week one Row is created.
	 *
	 */
	private void createRowsOfCalendar() {
		List<CalendarWeekTile> tiles = createTiles();
		this.grid.setItems(tiles);
	}

	/**
	 * Creates the Rows of the Calendar. For every Week one Row is created.
	 *
	 * @return the list
	 */
	private List<CalendarWeekTile> createTiles() {
		ArrayList<CalendarWeekTile> tiles = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			tiles.add(new CalendarWeekTile(this.calendar.getTime()));
			this.calendar.set(Calendar.DAY_OF_YEAR, this.calendar.get(Calendar.DAY_OF_YEAR) + 7);

		}
		this.calendar.setTime(this.firstOfMonth);
		return tiles;
	}

	/**
	 * Creates a button in the menu that navigates to the next view.
	 *
	 * @param value the value
	 * @param icon  the icon
	 * @return the button
	 */
	private Button createMenuButton(String value, Icon icon) {
		Button newButton = new Button(value, icon);
		newButton.addClickListener(e -> {
			this.presenter.buttonClicked(e);
		});
		newButton.setWidth("200px");
		return newButton;
	}

	/**
	 * Creates the calendar navigation button, that allows to switch between the months of the year.
	 *
	 * @param nextMonth the next month
	 * @param icon      the icon
	 * @return the button
	 */
	private Button createCalendarNavigationButton(boolean nextMonth, Icon icon) {
		Button newButton = new Button(icon);
		newButton.addClickListener(e -> {
			this.setCalendarDate(nextMonth);
		});
		return newButton;
	}

	/**
	 * Creates the text area that displays the Address and the name of the institution..
	 *
	 * @return the text area
	 */
	private TextArea createTextArea() {
		this.area = new TextArea();
		this.area.setWidth("200px");
		this.area.setEnabled(false);
		return this.area;
	}

	/**
	 * Update label of institution.
	 */
	private void updateLabelOfInstitution() {
		this.area.setValue(this.presenter.getInstitutionData());
	}

	/**
	 * Parses the Value of the label that displays the current showed month.
	 *
	 * @param i the i
	 * @return the string
	 */
	private String parseMonth(int i) {
		String[] months = { "Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September",
				"Oktober", "November", "Dezember" };
		return months[i];
	}

	/**
	 * Sets the calendar on the Day before the current one.
	 */
	private void theDayBefore() {
		this.calendar.set(Calendar.DATE, this.calendar.get(Calendar.DATE) - 1);
	}

}
