package entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity class for the sessions.
 * 
 * @author leuel3
 *
 */
@Entity
@Table(name = "session")
public class SessionEntity {
	
	
	@Id
	@GeneratedValue
	private String sessionId;
	private String description;
	private LocalDate date;
	private int craving;
	@ManyToOne(cascade=CascadeType.REMOVE)
	private PatientEntity patient;


	public SessionEntity() {		
	}

	/**
	 * Gets the session ID.
	 * 
	 * @return the session ID.
	 */
	public String getSessionId() {
		return sessionId;
	}
	
	/**
	 * Gets the description of the session.
	 * 
	 * @return the description of the session.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the current session. 
	 * 
	 * @param description the new description of the session.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the date of the session.
	 * 
	 * @return date of the session.
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Sets the date of the session.
	 * 
	 * @param date the date you want to set.
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	/**
	 * Gets the craving number of the session.
	 * 
	 * @return the craving number of the session.
	 */
	public int getCraving() {
		return craving;
	}

	/**
	 * Sets the craving number of the session.
	 * 
	 * @param craving the craving of the session.
	 */
	public void setCraving(int craving) {
		this.craving = craving;
	}
	
	/**
	 * Gets the patient of the session.
	 * 
	 * @return the patient of the session.
	 */
	public PatientEntity getPatient() {
		return this.patient;
	}
	
	/**
	 * Sets the patient of the session.
	 * 
	 * @param patient the patient this session belongs to.
	 */
	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}
}
