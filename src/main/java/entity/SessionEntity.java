package entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

	public String getSessionId() {
		return sessionId;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public int getCraving() {
		return craving;
	}

	public void setCraving(int craving) {
		this.craving = craving;
	}
	public PatientEntity getPatient() {
		return this.patient;
	}
	
	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}
}
