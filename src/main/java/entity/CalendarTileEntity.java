
/**
 *date: 19.12.2018   -  time: 16:18:27
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "calendartile")
public class CalendarTileEntity {

	@Id
	@Temporal(TemporalType.DATE)
	private Date date;
	private String patient;
	private String kommentar;
	
	public CalendarTileEntity() {
		
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

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

}
