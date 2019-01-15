
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class CalendarTileEntity that represents a single Entry in the Calendar.
 * 
 * @author gundy1
 */
@Entity
@Table(name = "calendartile")
public class CalendarTileEntity {

	/** The date of the entry. */
	@Id
	@Temporal(TemporalType.DATE)
	private Date date;
	
	/** The patient ID as a foreign Key.  */
	@ManyToOne(cascade=CascadeType.REMOVE)
	private PatientEntity patient;
	private String kommentar;
	
	
	public CalendarTileEntity() {		
	}

	/**
	 * Gets the date of the Entry.
	 *
	 * @return the date of the Entry
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date of the Entry.
	 *
	 * @param date the new date of the Entry
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the comment of the Entry.
	 *
	 * @return the comment of the Entry
	 */
	public String getKommentar() {
		return this.kommentar;
	}

	/**
	 * Sets the comment of the Entry.
	 *
	 * @param kommentar the new comment of the Entry
	 */
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	/**
	 * Gets the patient of the Entry.
	 *
	 * @return the patient of the Entry
	 */
	public PatientEntity getPatient() {
		return patient;
	}

	/**
	 * Sets the patient of the Entry.
	 *
	 * @param patient the new patient of the Entry
	 */
	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}

}
