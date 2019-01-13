
/**
 *date: 19.12.2018   -  time: 16:18:27
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "calendartile")
public class CalendarTileEntity {

	@Id
	@Temporal(TemporalType.DATE)
	private Date date;
	@ManyToOne(cascade=CascadeType.REMOVE)
	private PatientEntity patient;
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

	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}

}
