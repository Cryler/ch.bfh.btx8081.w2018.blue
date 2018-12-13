
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

import presenter.CalendarWeekTile;

@Entity
@Table(name = "calendar")
public class CalendarModel {

	@Id
	@GeneratedValue
	private int calendarID;
	@OneToOne
	private InstitutionModel institution;

	public CalendarModel() {

	}

	public InstitutionModel getInstitution() {
		return this.institution;
	}

	public void setInstitution(InstitutionModel institution) {
		this.institution = institution;
	}
//
//	public CalendarWeekTileModel getWeek1() {
//		return this.week1;
//	}
//
//	public void setWeek1(CalendarWeekTileModel week1) {
//		this.week1 = week1;
//	}
//
//	public CalendarWeekTileModel getWeek2() {
//		return week2;
//	}
//
//	public void setWeek2(CalendarWeekTileModel week2) {
//		this.week2 = week2;
//	}
//
//	public CalendarWeekTileModel getWeek3() {
//		return week3;
//	}
//
//	public void setWeek3(CalendarWeekTileModel week3) {
//		this.week3 = week3;
//	}
//
//	public CalendarWeekTileModel getWeek4() {
//		return week4;
//	}
//
//	public void setWeek4(CalendarWeekTileModel week4) {
//		this.week4 = week4;
//	}
//
//	public CalendarWeekTileModel getWeek5() {
//		return week5;
//	}
//
//	public void setWeek5(CalendarWeekTileModel week5) {
//		this.week5 = week5;
//	}
//
//	public CalendarWeekTileModel getWeek6() {
//		return week6;
//	}
//
//	public void setWeek6(CalendarWeekTileModel week6) {
//		this.week6 = week6;
//	}
}
