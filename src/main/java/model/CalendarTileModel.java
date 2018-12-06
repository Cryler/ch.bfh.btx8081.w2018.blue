
/**
 *date: 06.12.2018   -  time: 14:55:26
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package model;

import java.sql.Time;
import java.util.Date;

import com.vaadin.flow.component.textfield.TextArea;

public class CalendarTileModel {
	private int id;
	private Date date;
	private PatientModel patient;
	private String kommentar;

	public CalendarTileModel() {

	}

	public CalendarTileModel(Date date,  String kommentar) {
		this.date = date;
		this.kommentar = kommentar;
	}

	public Date getDate() {
		return this.date;
	}

	public String getKommentar() {
		return this.kommentar;
	}

}
