
/**
 *date: 06.12.2018   -  time: 14:55:26
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "calendartile")
public class CalendarTileModel {

	@Id
	@Temporal(TemporalType.DATE)
	private Date date;
	private PatientModel patient;
	private String kommentar;
	

	public CalendarTileModel() {

	}

	public CalendarTileModel(Date date, String kommentar) {
		this.date = date;
		this.kommentar = kommentar;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getKommentar() {
		return this.kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public PatientModel getPatient() {
		return patient;
	}

	public void setPatient(PatientModel patient) {
		this.patient = patient;
	}

}
