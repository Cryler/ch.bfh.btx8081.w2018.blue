
/**
 *date: 11.12.2018   -  time: 20:44:35
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "calendar")
public class CalendarModel {

	@Id
	@GeneratedValue
	private int calendarID;
	@OneToOne
	private InstitutionModel institution;

	private CalendarWeekTile week1;
	private CalendarWeekTile week2;
	private CalendarWeekTile week3;
	private CalendarWeekTile week4;
	private CalendarWeekTile week5;
	private CalendarWeekTile week6;

	public CalendarModel() {

	}

	public InstitutionModel getInstitution() {
		return this.institution;
	}

	public void setInstitution(InstitutionModel institution) {
		this.institution = institution;
	}

	public CalendarWeekTile getWeek1() {
		return this.week1;
	}

	public void setWeek1(CalendarWeekTile week1) {
		this.week1 = week1;
	}

	public CalendarWeekTile getWeek2() {
		return week2;
	}

	public void setWeek2(CalendarWeekTile week2) {
		this.week2 = week2;
	}

	public CalendarWeekTile getWeek3() {
		return week3;
	}

	public void setWeek3(CalendarWeekTile week3) {
		this.week3 = week3;
	}

	public CalendarWeekTile getWeek4() {
		return week4;
	}

	public void setWeek4(CalendarWeekTile week4) {
		this.week4 = week4;
	}

	public CalendarWeekTile getWeek5() {
		return week5;
	}

	public void setWeek5(CalendarWeekTile week5) {
		this.week5 = week5;
	}

	public CalendarWeekTile getWeek6() {
		return week6;
	}

	public void setWeek6(CalendarWeekTile week6) {
		this.week6 = week6;
	}
}
