
/**
 *date: 29.11.2018   -  time: 14:41:12
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EventListener;
import java.util.GregorianCalendar;
import java.util.List;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.grid.GridSelectionModel;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import net.bytebuddy.asm.Advice.This;
import presenter.CalendarPresenter;
import presenter.CalendarWeekTile;
import service.UserService;

@Route("Kalender")

public class CalendarView extends VerticalLayout implements BeforeEnterObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CalendarPresenter presenter;
	private TextArea area;
	private Label month;
	private Date firstOfMonth = new Date();

	private Grid<CalendarWeekTile> grid;
	private Calendar calendar;
	
	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		if (UserService.getUser() == null) {
			event.rerouteTo("");
		}
	}

	public CalendarView() {
		this.initView();
		this.updateLabelOfInstitution();
		this.initCalendar();
		this.createRowsOfCalendar();

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


		
		
		HorizontalLayout hl2 = new HorizontalLayout();
		hl2.setWidth("100%");		
		hl2.add(this.createCalendarNavigationButton(false, new Icon(VaadinIcon.ARROW_CIRCLE_LEFT_O)));
		this.month = new Label();
		this.month.getStyle().set("margin-top", "10px");
		hl2.add(this.month);
		hl2.add(this.createCalendarNavigationButton(true, new Icon(VaadinIcon.ARROW_CIRCLE_RIGHT_O)));		
		hl2.setFlexGrow(2, this.month);

		VerticalLayout vl1 = new VerticalLayout();
		vl1.setWidth("250px");
		vl1.add(this.createTextArea());
		vl1.add(this.createMenuButton("Home", new Icon(VaadinIcon.HOME)));
		vl1.add(this.createMenuButton("Neuer Patient", new Icon(VaadinIcon.USER_CHECK)));
		vl1.add(this.createMenuButton("Patient suchen", new Icon(VaadinIcon.USERS)));
		vl1.add(this.createMenuButton("Logout", new Icon(VaadinIcon.POWER_OFF)));
		vl1.add(hl2);

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
		this.month.setText(this.parseMonth(this.calendar.get(Calendar.MONTH)));
		this.firstOfMonth = this.calendar.getTime();
		while (!this.calendar.getTime().toString().substring(0, 3).equals("Mon")) {
			this.theDayBefore();
		}
		
	}

	private void setCalendarDate(boolean nextMonth) {
		if (nextMonth) {
			this.calendar.set(Calendar.MONTH, this.calendar.get(Calendar.MONTH )+1);
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

	// Hilfsmethoden

	private void createRowsOfCalendar() {
		List<CalendarWeekTile> tiles = createTiles();
		this.grid.setItems(tiles);
	}

	private List<CalendarWeekTile> createTiles() {
		ArrayList<CalendarWeekTile> tiles = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			tiles.add(new CalendarWeekTile(this.calendar.getTime()));
			this.calendar.set(Calendar.DAY_OF_YEAR, this.calendar.get(Calendar.DAY_OF_YEAR) + 7);

		}
		this.calendar.setTime(this.firstOfMonth);
		return tiles;
	}

	private Button createMenuButton(String value, Icon icon) {
		Button newButton = new Button(value, icon);
		newButton.addClickListener(e -> {
			this.presenter.buttonClicked(e);
		});
		newButton.setWidth("200px");
		return newButton;
	}

	private Button createCalendarNavigationButton(boolean nextMonth, Icon icon) {
		Button newButton = new Button(icon);
		newButton.addClickListener(e -> {
			this.setCalendarDate(nextMonth);
		});
		return newButton;
	}

	private TextArea createTextArea() {
		this.area = new TextArea();
		this.area.setWidth("200px");
		this.area.setEnabled(false);
		return this.area;
	}

	private void updateLabelOfInstitution() {
		this.area.setValue(this.presenter.getInstitutionData());
	}
	
	private String parseMonth(int i) {
		String[] months = {"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"};
		return months[i];
	}

	private void theDayBefore() {
		this.calendar.set(Calendar.DATE, this.calendar.get(Calendar.DATE) - 1);
	}

	
}
